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
   private static final int USER_HAND = 1;

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

      // get the icons
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
      setupPlayCards();
      BuildHands();
   }

   // grab the 2 cards, populate the game play array
   public void setupPlayCards()
   {
      // first run, take cards from deck
      if (_model.leftDeckButton == null || _model.rightDeckButton == null)
      {
	 // build the left button
	 Card card = getCardFromDeck();
	 _model.leftDeckButton = new JButton(GUICard.getIcon(card));
	 _model.leftDeckCard = card;
	 
	 // build the right button
	 card = getCardFromDeck();
	 _model.rightDeckButton = new JButton(GUICard.getIcon(card));
	 _model.rightDeckCard = card;
	 
	 _model.userCannotPlayButton = new JButton("I cannot play");
	 _model.userCannotPlayButton.addActionListener(new ActionListener()
	 {
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	       System.out.println("User can't play");

	       _model.addUserPlayFail();

	       // let the computer play
	       PlayComputerHand();

	       _view.ShowPlayArea(_model);
	    }
	 });
      }
      
      // add the event handling - buttons may have changed
      AddEventHandlingToLeftPlayButton();
      AddEventHandlingToRightPlayButton();

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

         // FIXFIXFIX: check for an existing listener before adding
         AddEventHandlingToUserCardButton(humanButton, playCard);

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
   
   // loop through the buttons in the array passed in (computer or user) - return the index
   //	this index will be the same as the index in the hand 
   private int GetUserHandSelectedIndex(JButton[] buttons, JButton button)
   {
      for (int i = 0; i < buttons.length; i++)
      {
	 if (button == buttons[i])
	 {
	    return i;
	 }
      }
      
      return -1;
   }

   // add the event logic to the button
   // this method will be called separately for the left and right play deck buttons
   private void AddEventHandlingToLeftPlayButton()
   {
      _model.leftDeckButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            // TESTING TESTING
            System.out.println("Left deck button clicked");
            
            // error checking - user needs a card first
            if (_model.getUserPlayCard() == null)
            {
               // TESTING TESTING
               System.out.println("User has not selected a card from the hand");

               // show the deck selector
               UpdateDisplayFlags(false, false, true, false);
               
               // stop processing event
               return;
            }

            // user has a card selected - do work
            // validate that the selected card and hand fit rules
            int deckValue = GUICard.valueAsInt(_model.leftDeckCard);
            int handValue = GUICard.valueAsInt(_model.getUserPlayCard());

            // make sure this is within the set values
            // ensure we handle the 0-13 or 13-0 cases
            if (Math.abs(deckValue - handValue) != 1 && Math.abs(deckValue - handValue) - 13 != 0)
            {
               UpdateDisplayFlags(false, false, false, true);
               _view.ShowPlayArea(_model);
               return;
            }

            // update the play panel display item to the selected button - references work??
            // create a new button from the user card
            _model.leftDeckCard = _model.getUserPlayCard();
            _model.leftDeckButton = new JButton(GUICard.getIcon(_model.leftDeckCard));
            AddEventHandlingToLeftPlayButton();
            
            // make sure button is visible
            _model.leftDeckButton.setVisible(true);

            // remove the button from the array at the index.
            // play the card in the hand at the index
            // add another card to the hand if provided
            int index = _model.getUserHandSelectedIndex();
            Card userCard = getHand(USER_HAND).playCard(index);
            
            // attempt to add a card to the user hand
            Card newCard = getCardFromDeck();
            
            // check for empty deck - add card to hand if good
            if (!newCard.getErrorFlag())
            {
               getHand(USER_HAND).takeCard(newCard);
               
               // TESTING TESTING
               System.out.println("Card added to hand :" + newCard);
            }
            
            // set the user cards to null
            _model.setUserHandSelectedIndex(-1);
            _model.setUserCardButton(null);
            _model.setUserPlayCard(null);
            
            // run the computer evaluation
            PlayComputerHand();

            // reset the display
            UpdateDisplayFlags(true, false, false, false);

            // create the new hand displays
            BuildHands();
            
            _view.ShowPlayArea(_model);
         }
      });
   }
   
   // add event handling to the right deck button
   private void AddEventHandlingToRightPlayButton()
   {
      _model.rightDeckButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            // TESTING TESTING
            System.out.println("Right deck button clicked");
            
            // error checking - user needs a card first
            if (_model.getUserPlayCard() == null)
            {
               // TESTING TESTING
               System.out.println("User has not selected a card from the hand");

               // show the deck selector
               UpdateDisplayFlags(false, false, true, false);
               
               // stop processing event
               return;
            }

            // user has a card selected - do work
            // validate that the selected card and hand fit rules
            int deckValue = GUICard.valueAsInt(_model.rightDeckCard);
            int handValue = GUICard.valueAsInt(_model.getUserPlayCard());

            // make sure this is within the set values
            // ensure we handle the 0-13 or 13-0 cases
            if (Math.abs(deckValue - handValue) != 1 && Math.abs(deckValue - handValue) - 13 != 0)
            {
               UpdateDisplayFlags(false, false, false, true);
               _view.ShowPlayArea(_model);
               return;
            }

            // update the play panel display item to the selected button - references work??
            // create a new button from the user card
            _model.rightDeckCard = _model.getUserPlayCard();
            _model.rightDeckButton = new JButton(GUICard.getIcon(_model.rightDeckCard));
            AddEventHandlingToRightPlayButton();

            // make sure button is visible
            _model.rightDeckButton.setVisible(true);

            // remove the button from the array at the index.
            // play the card in the hand at the index
            // add another card to the hand if provided
            int index = _model.getUserHandSelectedIndex();
            Card userCard = getHand(USER_HAND).playCard(index);
            
            // attempt to add a card to the user hand
            Card newCard = getCardFromDeck();
            
            // check for empty deck - add card to hand if good
            if (!newCard.getErrorFlag())
            {
               getHand(USER_HAND).takeCard(newCard);
               
               // TESTING TESTING
               System.out.println("Card added to hand :" + newCard);
            }
            
            // set the user cards to null
            _model.setUserHandSelectedIndex(-1);
            _model.setUserCardButton(null);
            _model.setUserPlayCard(null);
            
            // run the computer evaluation
            PlayComputerHand();

            // reset the display
            UpdateDisplayFlags(true, false, false, false);

            // create the new hand displays
            BuildHands();
            
            _view.ShowPlayArea(_model);
         }
      });
   }
   
   private void AddEventHandlingToUserCardButton(JButton button, Card card)
   {
      // attempting a new listener function
      button.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            // TESTING TESTING
            System.out.println("User selected card: " + card);
            
            // play this selected card
            // store this card as the play card
            _model.setUserPlayCard(card);
            _model.setComputerPlayFailed(false);
            _model.setUserCardButton((JButton)e.getSource());

            int index = GetUserHandSelectedIndex(_model.getHumanButtons(), (JButton)e.getSource());

            if (index >= 0)
            {
     	  	  _model.setUserHandSelectedIndex(index);
            }
            else
            {
     	  	  // there was some sort of error
     	   	  System.out.println("Error occurred when searching for user selected index.");
            }

            // show the select deck message
            UpdateDisplayFlags(false, true, false, false);

            _view.ShowPlayArea(_model);
         }
      });
   }

   // don't know if this will stay since the code will require the play area buttons and some event
   //	based objects
   private boolean PlayComputerHand()
   {
      // TESTING TESTING
      //System.out.println("Computer plays a card");
      Hand computerHand = getHand(COMPUTER_HAND);
      System.out.println("Computer " + computerHand );
      Boolean cardPlayed = false;
      
      // loop through the cards in the computer hand and see if one fits the right deck
      int rightDeckValue = GUICard.valueAsInt(_model.rightDeckCard);
      for( int i = 0; i < computerHand.getNumCards(); i++ )
      {
         //System.out.println("Checking right deck " + i + " of " + computerHand.getNumCards());
         if(Math.abs(rightDeckValue - GUICard.valueAsInt(computerHand.inspectCard(i))) == 1)
         //if(Math.abs(rightDeckValue - GUICard.valueAsInt(computerHand.inspectCard(i))) == 1 || 
         //      Math.abs(rightDeckValue - GUICard.valueAsInt(computerHand.inspectCard(i))) == 0)
         {
            // update the play panel display item to the selected button
            _model.rightDeckButton = new JButton(GUICard.getIcon(computerHand.inspectCard(i)));
            _model.rightDeckCard = computerHand.playCard(i);
            //System.out.println("Computer played card " + computerHand.inspectCard(i) + " at index " + i);
            
            // make sure button is visible
            _model.rightDeckButton.setVisible(true);

            // remove the button from the array at the index.
            // play the card in the hand at the index
            // add another card to the hand if provided
            //int index = _model.getUserHandSelectedIndex();
            //Card userCard = getHand(USER_HAND).playCard(index);
            
            // attempt to add a card to the user hand
            Card newCard = getCardFromDeck();
            
            // check for empty deck - add card to hand if good
            if (!newCard.getErrorFlag())
            {
               getHand(COMPUTER_HAND).takeCard(newCard);
               
               // TESTING TESTING
               System.out.println("Card added to hand :" + newCard);
            }
            
            // clear the user selections in the model
            //_model.setUserHandSelectedIndex(-1);
            //_model.setUserCardButton(null);
            cardPlayed = true;
            break;
         }
         
      }
      
      // if not found on right, try the left
      if(!cardPlayed)
      {
         int leftDeckValue = GUICard.valueAsInt(_model.leftDeckCard);
         for( int i = 0; i < computerHand.getNumCards(); i++ )
         {
            //System.out.println("Checking left deck " + i + " of " + computerHand.getNumCards());
            if(Math.abs(leftDeckValue - GUICard.valueAsInt(computerHand.inspectCard(i))) == 1)
            //if(Math.abs(rightDeckValue - GUICard.valueAsInt(computerHand.inspectCard(i))) == 1 || 
            //      Math.abs(rightDeckValue - GUICard.valueAsInt(computerHand.inspectCard(i))) == 0)
            {
               // update the play panel display item to the selected button
               _model.leftDeckButton = new JButton(GUICard.getIcon(computerHand.inspectCard(i)));
               _model.leftDeckCard = computerHand.playCard(i);
               //System.out.println("Computer played card " + computerHand.inspectCard(i) + " at index " + i);
               
               // make sure button is visible
               _model.rightDeckButton.setVisible(true);
   
               // remove the button from the array at the index.
               // play the card in the hand at the index
               // add another card to the hand if provided
               //int index = _model.getUserHandSelectedIndex();
               //Card userCard = getHand(USER_HAND).playCard(index);
               
               // attempt to add a card to the user hand
               Card newCard = getCardFromDeck();
               
               // check for empty deck - add card to hand if good
               if (!newCard.getErrorFlag())
               {
                  getHand(COMPUTER_HAND).takeCard(newCard);
                  
                  // TESTING TESTING
                  System.out.println("Card added to hand :" + newCard);
               }
               
               // clear the user selections in the model
               //_model.setUserHandSelectedIndex(-1);
               //_model.setUserCardButton(null);
               cardPlayed = true;
               break;
            }
            
         }
      }
      
      // if not found at all, increment the cannot play and reset the display
      if(!cardPlayed)
      {
         System.out.println("Computer couldn't play.");
         _model.addComputerPlayFail();
      }
      
      // reset the display
      UpdateDisplayFlags(true, false, false, false);
      
      // create the new hand displays
      //BuildHands();
      
      _view.ShowPlayArea(_model);
      
      return true;
   }
}