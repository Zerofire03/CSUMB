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
   private static int playsAvailable = 0;

   private Card[] unusedCardsPerPack; // an array holding the cards not used
   // in the game. e.g. pinochle does not
   // use cards 2-8 of any suit

   private int numCardsPerHand;
   private int numPlayers;
   private Model _model;
   private View _view;
   
   public Controller(int numPacks, int numJokersPerPack, int numUnusedCardsPerPack, Card[] unusedCardsPerPack,
	 int numPlayers, int numCardsPerHand, Model model, View view)
   {
      super(numPacks, numJokersPerPack, numUnusedCardsPerPack, unusedCardsPerPack, numPlayers, 
	    numCardsPerHand);
      
      // record the view and model
      _model = model;
      _view = view;
      
      this.numPlayers = numPlayers;
      this.numCardsPerHand = numCardsPerHand;

      // instantiate game tracking variables
      winnings = new Card[numCardsPerHand];
      playsAvailable = numCardsPerHand;

      GUICard.loadCardIcons();

      // prepare deck and shuffle
      newGame();
      deal();

      // Set model hands
      SetModelHands();
      
      // Build the Hands for the computer and user
      BuildHands();
   }

   // constructor overload/default for game like bridge
   public Controller()
   {
      this(1, 0, 0, null, 4, 13, null, null);
   }

   // populate the model
   public void SetModelHands()
   {
      // get the hands from controller and populate the model
      Hand[] hands = new Hand[numPlayers];
      for (int i = 0; i < numPlayers; i++)
      {
	 hands[i] = getHand(i);
      }
      _model.setHands(hands);
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
      
      
   }

   //
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
	 Card playCard = _model.getUserHand().inspectCard(i);
	 humanButton = new JButton(GUICard.getIcon(playCard));

	 // attempting a new listener function
	 humanButton.addActionListener(new ActionListener()
	 {

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	       String actionCommand = e.getActionCommand();

	       playsAvailable--;

	       // testing - show button index output
	       System.out.println("Button clicked - " + actionCommand);
	       System.out.println("Human card: " + playCard.toString());

	       // pull a card at random for the computer
	       Card computerCard = _model.getComputerHand().playCard();

	       // testing testing
	       System.out.println("Computer card: " + computerCard.toString());

	       // clear and repopulate the play area panel
	       _view.pnlPlayArea.removeAll();
	       _view.pnlPlayArea.add(new JLabel(GUICard.getIcon(computerCard), JLabel.CENTER));
	       _view.pnlPlayArea.add(new JLabel(" ", JLabel.CENTER));
	       _view.pnlPlayArea.add(new JLabel(GUICard.getIcon(playCard), JLabel.CENTER));

	       // do the testing
	       if (playCard.compareTo(computerCard) > 0)
	       {
		  winnings[numWins] = computerCard;
		  numWins++;

		  // human wins
		  _view.pnlPlayArea.add(new JLabel("Computer Lost!", JLabel.CENTER));
		  _view.pnlPlayArea.add(new JLabel("Win Total:" + numWins, JLabel.CENTER));
		  _view.pnlPlayArea.add(new JLabel("You Won!", JLabel.CENTER));
	       } 
	       else if (playCard.compareTo(computerCard) == 0)
	       {
		  // tie
		  _view.pnlPlayArea.add(new JLabel("Computer Tie!", JLabel.CENTER));
		  _view.pnlPlayArea.add(new JLabel("Win Total:" + numWins, JLabel.CENTER));
		  _view.pnlPlayArea.add(new JLabel("You Tie!", JLabel.CENTER));
	       } 
	       else if (playCard.compareTo(computerCard) < 0)
	       {
		  // computer wins
		  _view.pnlPlayArea.add(new JLabel("Computer Won!", JLabel.CENTER));
		  _view.pnlPlayArea.add(new JLabel("Win Total:" + numWins, JLabel.CENTER));
		  _view.pnlPlayArea.add(new JLabel("You Lost!", JLabel.CENTER));
	       }

	       // remove the cards from display using counter of plays
	       // pull the computer item from top component
	       _view.pnlComputerHand.getComponent(playsAvailable).setVisible(false);

	       // pull the original button from the actionevent
	       ((JButton) e.getSource()).setVisible(false);

	       // show the updated display
	       _view.setVisible(true);
	    }
	 });

	 humanButtons[i] = humanButton;
      }

      // ADD LABELS TO PANELS -----------------------------------------
      for (JLabel label : computerLabels)
      {
	 _view.pnlComputerHand.add(label);
      }
      for (JButton button : humanButtons)
      {
	 _view.pnlHumanHand.add(button);
      }

      // show everything to the user
      _view.setVisible(true);
   }

   // return a new random card
   static Card generateRandomCard()
   {
      int cardVal = (int) (Math.random() * Card.valuRanks.length);
      int suitVal = (int) (Math.random() * Card.Suit.values().length);

      return new Card(Card.valuRanks[cardVal], Card.Suit.values()[suitVal]);
   }
}