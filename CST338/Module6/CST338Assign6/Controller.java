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
      
      // show the select card message
      UpdateDisplayFlags(true, false, false, false);
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
	 JButton playButton = new JButton(GUICard.getIcon(card));

	 playButton.addActionListener(new ActionListener()
	 {
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	       // error checking - user needs a card first
	       if (_model.getUserPlayCard() == null)
	       {
		  // TESTING TESTING
		  System.out.println("User has not selected a card from the hand");
		  
		  // show the deck selector
		  UpdateDisplayFlags(false, false, true, false);
	       }
	       
	       // user has a card selected - do work	       
	       // validate that the selected card and hand fit rules
	       int deckValue = GUICard.valueAsInt(card);
	       int handValue = GUICard.valueAsInt(_model.getUserPlayCard());
	       
	       // make sure this is within the set values
	       if (Math.abs(deckValue - handValue) > 1)
	       {
		  UpdateDisplayFlags(false, false, false, true);
	       }
	       
	       // remove the button from the user display and add another
	       /*
	       // pull the computer item from top component
	       myCardTable.pnlComputerHand.getComponent(playsAvailable).setVisible(false);
	       
	       // pull the original button from the actionevent
	       ((JButton)e.getSource()).setVisible(false);
	       */
	       
	       //////////////
	       // STOPPED HERE
	       //////////////

	       // update the play panel display item to the selected button
	       JButton button = (JButton)e.getSource();
	       button = _model.getUserCardButton();
	       
	       // need to update the hand to replace the used card with a new card from the deck
	       // do this by index?
	       
	       
	       // run the computer evaluation
	       PlayComputerHand();
	       
	       // reset the display
	       UpdateDisplayFlags(true, false, false, false);
	       
	       // TESTING TESTING
	       System.out.println(card.toString());
	    }
	 });
	 
	 playButtons[i] = playButton;
      }
      
      // can't play card
      playButtons[2] = new JButton("I cannot play");
      playButtons[2].addActionListener(new ActionListener()
      {
	 @Override
	 public void actionPerformed(ActionEvent e)
	 {
	    System.out.println("User can't play");
	    
	    _model.addUserPlayFail();
	    
	    // let the computer play
	    PlayComputerHand();
	 }
      });
      
      _model.setPlayButtons(playButtons);
      
      _view.ShowPlayArea(_model);
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
	       // play this selected card
	       // store this card as the play card
	       _model.setUserPlayCard(playCard);
	       _model.setComputerPlayFailed(false);
	       _model.setUserCardButton((JButton)e.getSource());
	       
	       // show the select deck message
	       UpdateDisplayFlags(false, true, false, false);
	       
	       _view.ShowPlayArea(_model);
	    }
	 });

	 humanButtons[i] = humanButton;
      }

      _model.setComputerLabels(computerLabels);
      _model.setHumanButtons(humanButtons);
      
      _view.SetDisplay(_model);
   }

   private void UpdateDisplayFlags(boolean selectCard, boolean selectDeck, boolean deckError,
	 boolean deckCardError)
   {
      _model.setShowSelectCard(selectCard);
      _model.setShowSelectDeck(selectDeck);
      _model.setShowDeckError(deckError);
      _model.setShowDeckCardError(deckCardError);
   }
   
   
   // don't know if this will stay since the code will require the play area buttons and some event
   //	based objects
   public boolean PlayComputerHand()
   {
      return true;
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