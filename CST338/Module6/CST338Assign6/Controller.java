import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

// class CardGameFramework ----------------------------------------------------
public class Controller extends CardGameFramework
{
   private static int MAX_CARDS_PER_HAND = 56;
   private static final int MAX_PLAYERS = 50;

   private static Card[] winnings = null;
   private static int numWins = 0;
   
   private static final int COMPUTER_HAND = 0;
   private static final int USER_HAND = 0;

   private Card[] unusedCardsPerPack; // an array holding the cards not used
   // in the game. e.g. pinochle does not
   // use cards 2-8 of any suit

   private int numCardsPerHand;
   private int numPlayers;
   private Model _model;
   private View _view;
   
   public Controller(int numPacks, int numJokersPerPack, int numUnusedCardsPerPack, Card[] unusedCardsPerPack,
	 int numPlayers, int numCardsPerHand, String title, int width, int height)
   {
      super(numPacks, numJokersPerPack, numUnusedCardsPerPack, unusedCardsPerPack, numPlayers, 
	    numCardsPerHand);
      
      // record the view and model
      _model = new Model(numCardsPerHand);
      _view = new View(title, width, height);
      
      this.numPlayers = numPlayers;
      this.numCardsPerHand = numCardsPerHand;

      // instantiate game tracking variables
      winnings = new Card[numCardsPerHand];

      GUICard.loadCardIcons();

      // prepare deck and shuffle
      newGame();
      deal();
      
      // put the first play cards on the table
      setupPlayCards();
   }

   // constructor overload/default for game like bridge
   public Controller()
   {
      this(1, 0, 0, null, 4, 13, "Card Table", 800, 600);
   }
   
   // execute the game
   public void RunGame()
   {
      // check for max cards
      if (numCardsPerHand > MAX_CARDS_PER_HAND)
      {
         System.out.println(
               "numCardsPerHand: " + numCardsPerHand + " was above max: " + MAX_CARDS_PER_HAND + ".  Using the max.");
         this.numCardsPerHand = MAX_CARDS_PER_HAND;
      } 
      else
      {
         this.numCardsPerHand = numCardsPerHand;
      }

      // check for max players
      if (numPlayers > MAX_PLAYERS)
      {
         System.out.println("numPlayers: " + numPlayers + " was above max: " + MAX_PLAYERS + ".  Using the max.");
         this.numPlayers = MAX_PLAYERS;
      } 
      else
      {
         this.numPlayers = numPlayers;
      }
      
      // Build the Hands for the computer and user
      BuildHands();
      
      setupPlayCards();
   }
   
   // grab the 2 cards, populate the game play array
   public void setupPlayCards()
   {
      JButton[] playButtons = new JButton[3];
      
      // loop through card decks
      for (int i = 0; i < playButtons.length - 1; i++)
      {
	 Card card = getCardFromDeck();
	 playButtons[i] = new JButton(GUICard.getIcon(card));
	 
	 playButtons[i].addActionListener(new ActionListener()
	 {
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	       // set up the event code for the deck button
	       // the prior set card should replace this one
	       // remove the item from the user display
	       // pull another card from the deck for the user
	       
	       // play the computer side
	       // can computer play? - loop through cards looking for a playable card
	       //	remove the card from computer hand
	       //	pull another card from deck
	       // if not do the 'can't play' event
	       
	    }
	 });
      }
      
      // can't play card
      playButtons[2] = new JButton("I cannot play");
      playButtons[2].addActionListener(new ActionListener()
      {
	 @Override
	 public void actionPerformed(ActionEvent e)
	 {
	    
	 }
      });
      
      _model.setPlayButtons(playButtons);
   }
   
   // Build the display elements and push to view then start the game
   public void BuildHands()
   {
      // display controls
      JLabel[] computerLabels = new JLabel[numCardsPerHand];
      JButton[] humanButtons = new JButton[numCardsPerHand];

      // computer cards
      for (int i = 0; i < numCardsPerHand; i++)
      {
	 computerLabels[i] = new JLabel(GUICard.getBackCardIcon());
      }

      // human cards
      JButton humanButton = null;
      for (int i = 0; i < numCardsPerHand; i++)
      {
	 Card playCard = getHand(USER_HAND).inspectCard(i);
	 humanButton = new JButton(GUICard.getIcon(playCard));

	 // attempting a new listener function
	 humanButton.addActionListener(new ActionListener()
	 {

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	       String actionCommand = e.getActionCommand();
	       
	       String computerResult = "", userResult = "", winTotal = "";

	       /*
	       // testing - show button index output
	       System.out.println("Button clicked - " + actionCommand);
	       System.out.println("Human card: " + playCard.toString());
	       */

	       
	       
	       // pull a card at random for the computer
	       Card computerCard = getHand(COMPUTER_HAND).playCard();

	       /*
	       // testing testing
	       System.out.println("Computer card: " + computerCard.toString());
	       */

	       JLabel[] playLabels = new JLabel[6];
	       
	       // create the play labels
	       playLabels[0] = new JLabel(GUICard.getIcon(computerCard), JLabel.CENTER);
	       playLabels[1] = new JLabel(" ", JLabel.CENTER);
	       playLabels[2] = new JLabel(GUICard.getIcon(playCard), JLabel.CENTER);
	       
	       // do the testing
	       if (playCard.compareTo(computerCard) > 0)
	       {
		  winnings[numWins] = computerCard;
		  numWins++;

		  // human wins
		  computerResult = "Computer Lost!";
		  winTotal = "Win Total:" + numWins;
		  userResult = "You Won!";
	       } 
	       else if (playCard.compareTo(computerCard) == 0)
	       {
		  // tie
		  computerResult = "Computer Tie!";
		  winTotal = "Win Total:" + numWins;
		  userResult = "You Tie!";
	       } 
	       else if (playCard.compareTo(computerCard) < 0)
	       {
		  // computer wins
		  computerResult = "Computer Won!";
		  winTotal = "Win Total:" + numWins;
		  userResult = "You Lost!";
	       }
	       
	       playLabels[3] = new JLabel(computerResult, JLabel.CENTER);
	       playLabels[4] = new JLabel(winTotal, JLabel.CENTER);
	       playLabels[5] = new JLabel(userResult, JLabel.CENTER);
	       
	       /*
	       // testing - wins
	       System.out.println("numWins: " + numWins);
	       for (int i = 0; i < winnings.length; i++)
	       {
		  if (winnings[i] != null)
		  {
		     System.out.println("winnings[" + i + "]: " + winnings[i].toString());
		  }
	       }
	       */
	       
	       //_view.ShowPlayArea(_model, (JButton)e.getSource());
	    }
	 });

	 humanButtons[i] = humanButton;
      }

      _model.setComputerLabels(computerLabels);
      _model.setHumanButtons(humanButtons);
      
      _view.SetDisplay(_model);
   }

   // user can't play
   public void userCannotPlay()
   {
      
   }
   
   // computer can't play
   public void computerCannotPlay()
   {
      
   }
   
}