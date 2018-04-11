/*
Michael Loeser
Chris Holmes
Chris Buckey
Patrick Gonzalez

Assignment 6 - Phase 3
 */

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.lang.Math;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CST338Assign6Ph3
{
   // static for the 57 icons and their corresponding labels
   // normally we would not have a separate label for each card, but
   // if we want to display all at once using labels, we need to.

   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;

   // a simple main to throw all the JLabels out there for the world to see
   public static void main(String[] args)
   {
      int numPacksPerDeck = 1;
      int numJokersPerPack = 4;
      int numUnusedCardsPerPack = 0;
      Card[] unusedCardsPerPack = null;

      // set up for the BUILD game
      // need to populate the deck
      //		shuffle the deck
      // grab 2 cards at random
      // place the 2 cards as JButtons on the play area
      //	 add another new button for user selection "I cannot play"
      // computer goes first
      // 	computer places a card from it's hand following the game rules
      //			if computer plays, pull another card from the deck for them and put into hand
      //		OR computer shows label "I cannot play"
      // user gets a turn
      //		user selects a card from the hand to play
      //			if plays, pull another card from the deck and put into hand
      //		OR user selects "I cannot play"
      // count the "I cannot play" selections
      // continue playing until the deck is gone



      // Model
      // View - setup view
      // Controller (Model, View)
      // View.show
      Controller highCardGame = new Controller(numPacksPerDeck, numJokersPerPack, numUnusedCardsPerPack,
	    unusedCardsPerPack, NUM_PLAYERS, NUM_CARDS_PER_HAND, "Card Table", 800, 600);
      highCardGame.RunGame();

      GameTimer timer = new GameTimer();
      timer.start();
   }
}

/**** From Module 3 *****/
class Card
{
   private char _value;
   private Suit _suit;
   private boolean _errorFlag;
   private static final ArrayList<String> VALID_VALUES = new ArrayList<>(
	 Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "X"));

   // put the order of the card values in here with the smallest first, include 'X'
   // for a joker
   public static char[] valuRanks = new char[]
	 { '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A', 'X' };

   public enum Suit
   {
      clubs, diamonds, hearts, spades
   }

   // constructors
   public Card()
   {
      Set('A', Suit.spades);
   }

   public Card(char value, Suit suit)
   {
      Set(value, suit);
   }

   // Accessors
   public char getValue()
   {
      return _value;
   }

   public boolean setValue(char value)
   {

      if (!VALID_VALUES.contains(String.valueOf(value)))
      {
	 return false;
      }

      _value = value;

      return true;
   }

   public Suit getSuit()
   {
      return _suit;
   }

   public boolean setSuit(Suit suit)
   {
      _suit = suit;

      return true;
   }

   public boolean getErrorFlag()
   {
      return _errorFlag;
   }

   // standard functions
   public String toString()
   {
      if (_errorFlag == true)
      {
	 return "** invalid **";
      }
      return _value + " of " + _suit;
   }

   // validate all public elements and return true if they are a match
   public boolean equals(Card card)
   {
      if (this._value == card.getValue() && this._suit == card.getSuit() && this._errorFlag == card.getErrorFlag())
      {
	 return true;
      }
      return false;
   }

   // compare the current card to an incoming card.
   // return neg number if current index value is less
   // return 0 if equal
   // return pos number if current index value is more
   public int compareTo(Card card)
   {
      int curr = getIntFromCardValue(_value);
      int cVal = getIntFromCardValue(card.getValue());
      int resultVal = curr - cVal;

      // spades (high), hearts, clubs, diamonds
      if (resultVal == 0)
      {
	 // cards are the same, check the suits
	 switch (_suit)
	 {
	 case spades:
	    return 1;
	 case hearts:
	    if (card.getSuit() == Suit.spades)
	    {
	       return -1;
	    }
	    if (card.getSuit() == Suit.hearts)
	    {
	       return 0;
	    }
	    return 1;
	 case clubs:
	    if (card.getSuit() == Suit.spades || card.getSuit() == Suit.hearts)
	    {
	       return -1;
	    }
	    if (card.getSuit() == Suit.clubs)
	    {
	       return 0;
	    }
	    return 1;
	 case diamonds:
	    if (card.getSuit() == Suit.spades || card.getSuit() == Suit.hearts || card.getSuit() == Suit.clubs)
	    {
	       return -1;
	    }
	    if (card.getSuit() == Suit.diamonds)
	    {
	       return 0;
	    }
	    return 1;
	 }
      }

      // not a match, return card value test
      return resultVal;
   }

   // validate incoming param values
   private boolean isValid(char value, Suit suit)
   {
      if (!VALID_VALUES.contains(String.valueOf(value)) || suit == null)
      {
	 return false;
      }
      return true;
   }

   // public methods
   // validate and populate the internal variables
   public boolean Set(char value, Suit suit)
   {
      if (!isValid(value, suit))
      {
	 _errorFlag = true;
	 return false;
      }
      _value = value;
      _suit = suit;
      _errorFlag = false;

      return true;
   }

   public static String getStrFromSuit(Suit suit)
   {
      switch (suit.toString())
      {
      case "clubs":
	 return "C";
      case "hearts":
	 return "H";
      case "spades":
	 return "S";
      case "diamonds":
	 return "D";
      }

      return "";
   }

   public static Suit getSuitFromChar(char c)
   {
      switch (c)
      {
      case 'C':
	 return Suit.clubs;
      case 'H':
	 return Suit.hearts;
      case 'S':
	 return Suit.spades;
      case 'D':
	 return Suit.diamonds;
      }

      return Suit.spades;
   }

   // will sort the incoming array of cards using a bubble sort routine
   static void arraySort(Card[] cards, int arraySize)
   {
      // do bubble sort
      Card temp = null;

      // outer loop for the array
      for (int i = 0; i < arraySize; i++)
      {
	 // test that value against the rest of the array
	 for (int h = 0; h < (arraySize - i); h++)
	 {
	    // if earlier index is greater than current index, switch values
	    if (cards[h - 1].compareTo(cards[h]) > 0)
	    {
	       // swap values
	       temp = cards[h - 1];
	       cards[h - 1] = cards[h];
	       cards[h] = temp;
	    }
	 }
      }
   }

   // loop through the valuRanks array and return the index for comparison
   private static int getIntFromCardValue(char value)
   {
      for (int i = 0; i < valuRanks.length; i++)
      {
	 if (value == valuRanks[i])
	 {
	    return i;
	 }
      }

      // value not found
      return -1;
   }
}

// standard provided framework class
class CardGameFramework
{
   private static final int MAX_PLAYERS = 50;

   private int numPlayers;
   private int numPacks;            // # standard 52-card packs per deck
   // ignoring jokers or unused cards
   private int numJokersPerPack;    // if 2 per pack & 3 packs per deck, get 6
   private int numUnusedCardsPerPack;  // # cards removed from each pack
   private int numCardsPerHand;        // # cards to deal each player
   private Deck deck;               // holds the initial full deck and gets
   // smaller (usually) during play
   private Hand[] hand;             // one Hand for each player
   private Card[] unusedCardsPerPack;   // an array holding the cards not used
   // in the game.  e.g. pinochle does not
   // use cards 2-8 of any suit

   public CardGameFramework( int numPacks, int numJokersPerPack,
	 int numUnusedCardsPerPack,  Card[] unusedCardsPerPack,
	 int numPlayers, int numCardsPerHand)
   {
      int k;

      // filter bad values
      if (numPacks < 1 || numPacks > 6)
	 numPacks = 1;
      if (numJokersPerPack < 0 || numJokersPerPack > 4)
	 numJokersPerPack = 0;
      if (numUnusedCardsPerPack < 0 || numUnusedCardsPerPack > 50) //  > 1 card
	 numUnusedCardsPerPack = 0;
      if (numPlayers < 1 || numPlayers > MAX_PLAYERS)
	 numPlayers = 4;
      // one of many ways to assure at least one full deal to all players
      if  (numCardsPerHand < 1 ||
	    numCardsPerHand >  numPacks * (52 - numUnusedCardsPerPack)
	    / numPlayers )
	 numCardsPerHand = numPacks * (52 - numUnusedCardsPerPack) / numPlayers;

      // allocate
      this.unusedCardsPerPack = new Card[numUnusedCardsPerPack];
      this.hand = new Hand[numPlayers];
      for (k = 0; k < numPlayers; k++)
	 this.hand[k] = new Hand();
      deck = new Deck(numPacks);

      // assign to members
      this.numPacks = numPacks;
      this.numJokersPerPack = numJokersPerPack;
      this.numUnusedCardsPerPack = numUnusedCardsPerPack;
      this.numPlayers = numPlayers;
      this.numCardsPerHand = numCardsPerHand;
      for (k = 0; k < numUnusedCardsPerPack; k++)
	 this.unusedCardsPerPack[k] = unusedCardsPerPack[k];

      // prepare deck and shuffle
      newGame();
   }

   // constructor overload/default for game like bridge
   public CardGameFramework()
   {
      this(1, 0, 0, null, 4, 13);
   }

   public Hand getHand(int k)
   {
      // hands start from 0 like arrays

      // on error return automatic empty hand
      if (k < 0 || k >= numPlayers)
	 return new Hand();

      return hand[k];
   }

   public Card getCardFromDeck() { return deck.dealCard(); }

   public int getNumCardsRemainingInDeck() { return deck.getNumCards(); }

   public void newGame()
   {
      int k, j;

      // clear the hands
      for (k = 0; k < numPlayers; k++)
	 hand[k].resetHand();

      // restock the deck
      deck.init(numPacks);

      // remove unused cards
      for (k = 0; k < numUnusedCardsPerPack; k++)
	 deck.removeCard( unusedCardsPerPack[k] );

      // add jokers
      for (k = 0; k < numPacks; k++)
	 for ( j = 0; j < numJokersPerPack; j++)
	    deck.addCard( new Card('X', Card.Suit.values()[j]) );

      // shuffle the cards
      deck.shuffle();
   }

   public boolean deal()
   {
      // returns false if not enough cards, but deals what it can
      int k, j;
      boolean enoughCards;

      // clear all hands
      for (j = 0; j < numPlayers; j++)
	 hand[j].resetHand();

      enoughCards = true;
      for (k = 0; k < numCardsPerHand && enoughCards ; k++)
      {
	 for (j = 0; j < numPlayers; j++)
	    if (deck.getNumCards() > 0)
	       hand[j].takeCard( deck.dealCard() );
	    else
	    {
	       enoughCards = false;
	       break;
	    }
      }

      return enoughCards;
   }

   void sortHands()
   {
      int k;

      for (k = 0; k < numPlayers; k++)
	 hand[k].sort();
   }

   Card playCard(int playerIndex, int cardIndex)
   {
      // returns bad card if either argument is bad
      if (playerIndex < 0 ||  playerIndex > numPlayers - 1 ||
	    cardIndex < 0 || cardIndex > numCardsPerHand - 1)
      {
	 //Creates a card that does not work
	 return new Card('M', Card.Suit.spades);      
      }

      // return the card played
      return hand[playerIndex].playCard(cardIndex);

   }


   boolean takeCard(int playerIndex)
   {
      // returns false if either argument is bad
      if (playerIndex < 0 || playerIndex > numPlayers - 1)
	 return false;

      // Are there enough Cards?
      if (deck.getNumCards() <= 0)
	 return false;

      return hand[playerIndex].takeCard(deck.dealCard());
   }
}


//class CardGameFramework ----------------------------------------------------
class Controller extends CardGameFramework
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
	 /*
      System.out.println(
            "numCardsPerHand: " + numCardsPerHand + " was above max: " + MAX_CARDS_PER_HAND + ".  Using the max.");
	  */
	 this.numCardsPerHand = MAX_CARDS_PER_HAND;
      } 
      else
      {
	 this.numCardsPerHand = numCardsPerHand;
      }

      // check for max players
      if (numPlayers > MAX_PLAYERS)
      {
	 //System.out.println("numPlayers: " + numPlayers + " was above max: " + MAX_PLAYERS + ".  Using the max.");
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
      if (_model.getLeftDeckButton() == null || _model.getRightDeckButton() == null)
      {
	 // build the left button
	 Card card = getCardFromDeck();
	 _model.setLeftDeckButton(new JButton(GUICard.getIcon(card)));
	 _model.setLeftDeckCard(card);

	 // build the right button
	 card = getCardFromDeck();
	 _model.setRightDeckButton(new JButton(GUICard.getIcon(card)));
	 _model.setRightDeckCard(card);

	 _model.setUserCannotPlayButton(new JButton("I cannot play"));
	 _model.getUserCannotPlayButton().addActionListener(new ActionListener()
	 {
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	       //System.out.println("User can't play");

	       _model.addUserPlayFail();

	       // let the computer play
	       PlayComputerHand(true);

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
      _model.getLeftDeckButton().addActionListener(new ActionListener()
      {
	 @Override
	 public void actionPerformed(ActionEvent e)
	 {
	    // TESTING TESTING
	    //System.out.println("Left deck button clicked");

	    // error checking - user needs a card first
	    if (_model.getUserPlayCard() == null)
	    {
	       // TESTING TESTING
	       //System.out.println("User has not selected a card from the hand");

	       // show the deck selector
	       UpdateDisplayFlags(false, false, true, false);

	       // stop processing event
	       return;
	    }

	    // user has a card selected - do work
	    // validate that the selected card and hand fit rules
	    int deckValue = GUICard.valueAsInt(_model.getLeftDeckCard());
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
	    _model.setLeftDeckCard(_model.getUserPlayCard());
	    _model.setLeftDeckButton(new JButton(GUICard.getIcon(_model.getLeftDeckCard())));
	    AddEventHandlingToLeftPlayButton();

	    // make sure button is visible
	    _model.getLeftDeckButton().setVisible(true);

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
	       //System.out.println("Card added to hand :" + newCard);
	    }

	    // set the user cards to null
	    _model.setUserHandSelectedIndex(-1);
	    _model.setUserCardButton(null);
	    _model.setUserPlayCard(null);

	    // run the computer evaluation
	    PlayComputerHand(false);

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
      _model.getRightDeckButton().addActionListener(new ActionListener()
      {
	 @Override
	 public void actionPerformed(ActionEvent e)
	 {
	    // TESTING TESTING
	    //System.out.println("Right deck button clicked");

	    // error checking - user needs a card first
	    if (_model.getUserPlayCard() == null)
	    {
	       // TESTING TESTING
	       //System.out.println("User has not selected a card from the hand");

	       // show the deck selector
	       UpdateDisplayFlags(false, false, true, false);

	       // stop processing event
	       return;
	    }

	    // user has a card selected - do work
	    // validate that the selected card and hand fit rules
	    int deckValue = GUICard.valueAsInt(_model.getRightDeckCard());
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
	    _model.setRightDeckCard(_model.getUserPlayCard());
	    _model.setRightDeckButton(new JButton(GUICard.getIcon(_model.getRightDeckCard())));
	    AddEventHandlingToRightPlayButton();

	    // make sure button is visible
	    _model.getRightDeckButton().setVisible(true);

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
	       //System.out.println("Card added to hand :" + newCard);
	    }

	    // set the user cards to null
	    _model.setUserHandSelectedIndex(-1);
	    _model.setUserCardButton(null);
	    _model.setUserPlayCard(null);

	    // run the computer evaluation
	    PlayComputerHand(false);

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
	    //System.out.println("User selected card: " + card);

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
	       //System.out.println("Error occurred when searching for user selected index.");
	    }

	    // show the select deck message
	    UpdateDisplayFlags(false, true, false, false);

	    _view.ShowPlayArea(_model);
	 }
      });
   }

   // don't know if this will stay since the code will require the play area buttons and some event
   //	based objects
   private boolean PlayComputerHand(boolean userPlayFailed)
   {
      // TESTING TESTING
      //System.out.println("Computer plays a card");
      Hand computerHand = getHand(COMPUTER_HAND);
      //System.out.println("Computer " + computerHand );
      Boolean cardPlayed = false;

      // loop through the cards in the computer hand and see if one fits the right deck
      int rightDeckValue = GUICard.valueAsInt(_model.getRightDeckCard());
      for( int i = 0; i < computerHand.getNumCards(); i++ )
      {
	 //System.out.println("Checking right deck " + i + " of " + computerHand.getNumCards());
	 //if(Math.abs(rightDeckValue - GUICard.valueAsInt(computerHand.inspectCard(i))) == 1)
	 if(Math.abs(rightDeckValue - GUICard.valueAsInt(computerHand.inspectCard(i))) == 1 || 
	       Math.abs(rightDeckValue - GUICard.valueAsInt(computerHand.inspectCard(i))) == 0)
	 {
	    // update the play panel display item to the selected button
	    _model.setRightDeckButton(new JButton(GUICard.getIcon(computerHand.inspectCard(i))));
	    _model.setRightDeckCard(computerHand.playCard(i));
	    AddEventHandlingToRightPlayButton();

	    //System.out.println("Computer played card " + computerHand.inspectCard(i) + " at index " + i);

	    // make sure button is visible
	    _model.getRightDeckButton().setVisible(true);

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
	       //System.out.println("Card added to hand :" + newCard);
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
	 int leftDeckValue = GUICard.valueAsInt(_model.getLeftDeckCard());
	 for( int i = 0; i < computerHand.getNumCards(); i++ )
	 {
	    //System.out.println("Checking left deck " + i + " of " + computerHand.getNumCards());
	    //if(Math.abs(leftDeckValue - GUICard.valueAsInt(computerHand.inspectCard(i))) == 1)
	    if(Math.abs(leftDeckValue - GUICard.valueAsInt(computerHand.inspectCard(i))) == 1 || 
		  Math.abs(leftDeckValue - GUICard.valueAsInt(computerHand.inspectCard(i))) == 0)
	    {
	       // update the play panel display item to the selected button
	       _model.setLeftDeckButton(new JButton(GUICard.getIcon(computerHand.inspectCard(i))));
	       _model.setLeftDeckCard(computerHand.playCard(i));
	       AddEventHandlingToLeftPlayButton();
	       //System.out.println("Computer played card " + computerHand.inspectCard(i) + " at index " + i);

	       // make sure button is visible
	       _model.getLeftDeckButton().setVisible(true);

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
		  //System.out.println("Card added to hand :" + newCard);
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
	 //System.out.println("Computer couldn't play.");
	 _model.addComputerPlayFail();
	 if(userPlayFailed)
	 {
	    // update the play panel display item to the selected button
	    Card newCard = getCardFromDeck();

	    // test for empty deck - game over
	    if (newCard == null)
	    {
	       if (_model.getComputerPlayFail() < _model.getUserPlayFail())
	       {
		  _model.setShowComputerWon(true);
		  _model.setShowUserWon(false);
	       }
	       else
	       {
		  _model.setShowComputerWon(false);
		  _model.setShowUserWon(true);        	  
	       }

	       _view.ShowPlayArea(_model);

	       // game is over!
	       return true;
	    }

	    _model.setLeftDeckButton(new JButton(GUICard.getIcon(newCard)));
	    _model.setLeftDeckCard(newCard);
	    AddEventHandlingToLeftPlayButton();

	    newCard = getCardFromDeck();
	    _model.setRightDeckButton(new JButton(GUICard.getIcon(newCard)));
	    _model.setRightDeckCard(newCard);
	    AddEventHandlingToRightPlayButton();
	    //System.out.println("Computer played card " + computerHand.inspectCard(i) + " at index " + i);

	    // make sure button is visible
	    _model.getRightDeckButton().setVisible(true);

	 }
      }

      // reset the display
      UpdateDisplayFlags(true, false, false, false);

      // create the new hand displays
      //BuildHands();

      _view.ShowPlayArea(_model);

      return true;
   }
}

class Deck
{
   public final int MAX_CARDS = 6 * 56;
   private static Card[] masterPack;
   private Card[] cards;
   private int topCard;
   private int numPacks;

   // Allocate master pack
   private static void allocateMasterPack()
   {
      char[] cardValue =
	 { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'X' };
      masterPack = new Card[56];
      for (int i = 0; i < 14; i++)
      {
	 masterPack[0 + (i * 4)] = new Card(cardValue[i], Card.Suit.hearts);
	 masterPack[1 + (i * 4)] = new Card(cardValue[i], Card.Suit.spades);
	 masterPack[2 + (i * 4)] = new Card(cardValue[i], Card.Suit.clubs);
	 masterPack[3 + (i * 4)] = new Card(cardValue[i], Card.Suit.diamonds);
      }
   }

   // Constructor no params
   public Deck()
   {
      allocateMasterPack();
      numPacks = 1;
      cards = masterPack;
      topCard = cards.length;
   }

   // Constructor params
   // updated to support the jokers - 56 card packs
   public Deck(int numPacks)
   {
      allocateMasterPack();
      this.numPacks = numPacks;
      cards = new Card[numPacks * 56];
      for (int i = 0; i < numPacks; i++)
      {
	 for (int j = 0; j < 56; j++)
	 {
	    cards[(i * 56) + j] = masterPack[j];
	 }
      }
      topCard = cards.length;
   }

   // Accessor for the int of the topCard
   public int getTopCard()
   {
      return topCard;
   }

   // retrieve the number of cards in the deck
   public int getNumCards()
   {
      return topCard;
   }

   // methods

   // Re-populate cards[] with the standard 56 x numPacks cards - updated for
   // jokers
   public void init(int numPacks)
   {
      this.numPacks = numPacks;
      cards = new Card[numPacks * 56];
      for (int i = 0; i < numPacks; i++)
      {
	 for (int j = 0; j < 56; j++)
	 {
	    cards[(i * 56) + j] = masterPack[j];
	 }
      }
      topCard = cards.length;
   }

   // Mixes up the cards using a standard random number
   public void shuffle()
   {
      Collections.shuffle(Arrays.asList(cards));
   }

   // Returns and removes the card in the top occupied position of cards[]
   public Card dealCard()
   {
      // check for no more cards
      if (topCard == 0)
      {
	 return null;
      }

      topCard -= 1;
      return cards[topCard];
   }

   // make sure that there are not too many instances of the card in the deck if
   // you add it.
   // Return false if there will be too many. It should put the card on the top of
   // the deck.
   public boolean addCard(Card card)
   {
      // check for the max number of instances before adding
      // there can be one of the matching cards per pack
      int counter = 0;
      for (int x = 0; x < topCard; x++)
      {
	 // test for match
	 if (cards[x].equals(card))
	 {
	    counter++;
	 }

	 // check for max and drop out if reached
	 if (counter >= numPacks)
	 {
	    return false;
	 }
      }

      cards[topCard] = card;
      topCard++;
      return true;
   }

   // you are looking to remove a specific card from the deck.
   // Put the current top card into its place.
   // Be sure the card you need is actually still in the deck, if not return false.
   public boolean removeCard(Card card)
   {
      for (int x = 0; x < topCard; x++)
      {
	 // check for match. overwrite with topcard if found
	 if (cards[x].equals(card))
	 {
	    // replace with topcard
	    cards[x] = dealCard();
	    return true;
	 }
      }

      // card not found
      return false;
   }

   // use the Card arraySort method and pass the top card value;
   public void sort()
   {
      Card.arraySort(cards, topCard);
   }
}

class GameTimer extends Thread
{
   @Override
   public void run()
   {
      TheTimer theTimer = new TheTimer();
      try
      {
	 start();	 
      }
      catch (IllegalThreadStateException ex)
      {
	 // do nothing with this
      }
   }

   public void doNothing(int milliseconds)
   {
      try
      {
	 Thread.sleep(milliseconds);
      }
      catch(InterruptedException e)
      {
	 System.out.println("Unexpected interrupt");
	 System.exit(0);
      }
   }
}

class StopWatch extends JPanel
{
   // Variable Declaration
   //private Timer myTimer1;
   private Timer myTimer1;
   public static final int PAUSE = 100;   //time step in milliseconds
   public static final int TENTH_SEC = 1000;

   private Font myClockFont;

   private JButton startBtn;
   private JLabel timeLbl;
   private JPanel topPanel, bottomPanel;

   private int clockTick; 
   private String clockTimeString;
   private int minutes, seconds;

   public StopWatch()
   {
      // Variable initialization
      clockTick = 0; 

      clockTimeString = String.format("%02d:%02d", minutes, seconds);
      myClockFont = new Font("Serif", Font.PLAIN, 50);

      timeLbl = new JLabel();
      timeLbl.setFont(myClockFont);
      timeLbl.setText(clockTimeString);

      startBtn = new JButton("Start/Stop");


      myTimer1 = new Timer(TENTH_SEC, new ActionListener() {
	 @Override  
	 public void actionPerformed(ActionEvent evt) {
	    clockTick++;
	    if(clockTick/60 == 1)
	    {
	       seconds = 0;
	       minutes += 1;
	       clockTick = 0;
	    }
	    else
	    {
	       seconds = clockTick;
	    }
	    timeLbl.setText(String.format("%02d:%02d", minutes, seconds));
	 }

      });

      startBtn.addActionListener(new ActionListener(){
	 @Override
	 public void actionPerformed(ActionEvent evt) {
	    if (!myTimer1.isRunning()) {
	       myTimer1.start();
	    }
	    else if (myTimer1.isRunning())
	    {
	       myTimer1.stop();
	    }

	 }

      });
   }//end of StopWatch constructor

   public void launchStopWatch()
   {
      topPanel = new JPanel();
      topPanel.setBackground(Color.gray);
      bottomPanel = new JPanel();
      bottomPanel.setBackground(Color.blue);
      topPanel.add(timeLbl);
      bottomPanel.add(startBtn);

      this.setLayout(new BorderLayout());

      add(topPanel, BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }//end of launchClock
}

class TheTimer extends JFrame
{
   public TheTimer()
   {
      super("My Stop Watch");
      setLocation(800, 520);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container myPane = getContentPane();

      StopWatch StopWatch1 = new StopWatch();
      StopWatch1.launchStopWatch();
      myPane.add(StopWatch1);
      pack();
      setVisible(true);
   }
}

//manages the reading and building of the card image Icons
class GUICard
{
   private static final String[] CARDS = new String[]
	 { "A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "X" };
   private static final String[] SUITS = new String[]
	 { "C", "D", "H", "S" };
   private static final String CARD_EXT = ".gif";
   private static final String BACK_OF_CARD = "BK";

   private static final String BASE_PATH = "images/";

   private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K + joker
   private static Icon iconBack;
   static boolean iconsLoaded = false;

   static void loadCardIcons()
   {
      // build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif", etc.)
      // in a SHORT loop. For each file name, read it in and use it to
      // instantiate each of the 57 Icons in the icon[] array.

      if (iconsLoaded)
      {
	 return;
      }

      // loop through the suits
      int counterSuit = 0;
      int counterCard = 0;
      for (String suit : SUITS)
      {
	 // loop through the cards
	 for (String card : CARDS)
	 {
	    ImageIcon i = new ImageIcon(BASE_PATH + card + suit + CARD_EXT, card + suit);
	    iconCards[counterCard][counterSuit] = i;
	    counterCard++;
	 }
	 counterCard = 0;
	 counterSuit++;
      }

      // add the single backofcard image
      ImageIcon bk = new ImageIcon(BASE_PATH + BACK_OF_CARD + CARD_EXT, BACK_OF_CARD);
      iconBack = bk;

      iconsLoaded = true;
   }

   // turns 0 - 13 into "A", "2", "3", ... "Q", "K", "X"
   // an idea for a helper method (do it differently if you wish)
   static String turnIntIntoCardValue(int k)
   {
      // check for invalid index
      if (k < 0 || k > 14)
      {
	 return "";
      }

      return CARDS[k];
   }

   // turns 0 - 3 into "C", "D", "H", "S"
   static String turnIntIntoCardSuit(int j)
   {
      // an idea for another helper method (do it differently if you wish)

      // check for invalid index
      if (j < 0 || j > 3)
      {
	 return "";
      }

      return SUITS[j];
   }

   // retrieve the iconimage from the array based on card values
   static Icon getIcon(Card card)
   {
      // test for error in card
      if (card.getErrorFlag())
      {
	 return new ImageIcon();
      }

      return iconCards[valueAsInt(card)][suitAsInt(card)];
   }

   // retrieve the card back icon image
   static Icon getBackCardIcon()
   {
      return iconBack;
   }

   // retrieve the index value of the card value in the list
   static int valueAsInt(Card card)
   {
      for (int i = 0; i < CARDS.length; i++)
      {
	 if (CARDS[i].equals(String.valueOf(card.getValue())))
	 {
	    return i;
	 }
      }

      return 0;
   }

   static int suitAsInt(Card card)
   {
      for (int i = 0; i < SUITS.length; i++)
      {
	 // convert suit enum values to string then test string with array
	 if (SUITS[i].equals(Card.getStrFromSuit(card.getSuit())))
	 {
	    return i;
	 }
      }

      return 0;
   }
}

//Defines the hand entity used by the model
//Hand contains Decks and Cards
class Hand
{
   public final int MAX_CARDS = 6 * 56;
   private Card[] _myCards;
   private int _numCards;

   public Hand()
   {
      _myCards = new Card[MAX_CARDS];
      _numCards = 0;
   }

   // accessors
   public boolean setNumCards(int numCards)
   {

      if (numCards > MAX_CARDS)
      {
	 return false;
      }

      _numCards = numCards;
      return true;
   }

   public int getNumCards()
   {
      return _numCards;
   }

   // public methods
   // clear the hand
   public void resetHand()
   {
      _myCards = new Card[MAX_CARDS];
      _numCards = 0;
   }

   // add a card to the hand
   public boolean takeCard(Card card)
   {
      // test for max cards
      if (_numCards == MAX_CARDS)
      {
	 return false;
      }
      _myCards[_numCards] = card;
      _numCards++;
      return true;
   }

   // take the top card from the hand
   public Card playCard()
   {
      Card retCard = _myCards[_numCards - 1];
      _myCards[_numCards - 1] = null;
      _numCards--;

      return retCard;
   }

   // new method for Assign5 Ph3 - apply a specific card from the hand
   // updated to work with BUILD
   public Card playCard(int cardIndex)
   {
      if (_numCards == 0 ) //error
      {
	 //Creates a card that does not work
	 return new Card('M', Card.Suit.spades);
      }
      //Decreases numCards.
      Card card = _myCards[cardIndex];

      _numCards--;
      for(int i = cardIndex; i < _numCards; i++)
      {
	 _myCards[i] = _myCards[i+1];
      }

      _myCards[_numCards] = null;

      return card;
   }

   // show the entire hand
   public String toString()
   {
      String retStr = "Hand = ( ";

      for (int i = 0; i < _numCards; i++)
      {
	 if (i > 0)
	 {
	    retStr += ", ";
	 }
	 retStr += _myCards[i].getValue() + " of " + _myCards[i].getSuit();
      }
      retStr += " )";
      return retStr;
   }

   public Card inspectCard(int k)
   {
      if (k > _numCards)
      {
	 Card errCard = new Card();

	 // give an invalid value to ensure the error flag is set
	 errCard.Set('Y', Card.Suit.spades);
	 return errCard;
      }

      return _myCards[k];
   }

   public void sort()
   {
      Card.arraySort(_myCards, _numCards);
   }
}


//this should be a new class defining our 2 hands and the cards
class Model
{
   public static final int MAX_CARDS = 50;

   private JLabel[] _computerLabels;
   private JButton[] _humanButtons;
   //private JButton[] _playButtons;
   private JButton _userCardButton;

   private Card _userPlayCard;
   private int _userHandSelectedIndex;

   private int _computerPlayFail;
   private int _userPlayFail;

   private boolean _computerPlayFailed;

   private boolean _showSelectDeck;
   private boolean _showSelectCard;
   private boolean _showDeckError;
   private boolean _showDeckCardError;
   private boolean _showComputerWon;
   private boolean _showUserWon;

   private Card _leftDeckCard;
   private Card _rightDeckCard;
   private JButton _leftDeckButton;
   private JButton _rightDeckButton;

   private JButton _userCannotPlayButton;

   // constructor
   public Model(int playsAvailable)
   {
      _computerLabels = new JLabel[0];
      //_playButtons = new JButton[0];
      _humanButtons = new JButton[0];

      _computerPlayFail = 0;
      _userPlayFail = 0;
   }

   public Card getLeftDeckCard()
   {
      return _leftDeckCard;
   }
   public boolean setLeftDeckCard(Card leftDeckCard)
   {
      _leftDeckCard = leftDeckCard;
      return true;
   }

   public JButton getLeftDeckButton()
   {
      return _leftDeckButton;
   }
   public boolean setLeftDeckButton(JButton leftDeckButton)
   {
      _leftDeckButton = leftDeckButton;
      return true;
   }

   public Card getRightDeckCard()
   {
      return _rightDeckCard;
   }
   public boolean setRightDeckCard(Card rightDeckCard)
   {
      _rightDeckCard = rightDeckCard;
      return true;
   }

   public JButton getRightDeckButton()
   {
      return _rightDeckButton;
   }
   public boolean setRightDeckButton(JButton rightDeckButton)
   {
      _rightDeckButton = rightDeckButton;
      return true;
   }

   public JButton getUserCannotPlayButton()
   {
      return _userCannotPlayButton;
   }
   public boolean setUserCannotPlayButton(JButton userCannotPlayButton)
   {
      _userCannotPlayButton = userCannotPlayButton;
      return true;
   }

   public Card getUserPlayCard()
   {
      return _userPlayCard;
   }
   public Boolean setUserPlayCard(Card userPlayCard)
   {
      _userPlayCard = userPlayCard;
      return true;
   }

   public int getUserHandSelectedIndex()
   {
      return _userHandSelectedIndex;
   }
   public boolean setUserHandSelectedIndex(int index)
   {
      _userHandSelectedIndex = index;
      return true;
   }

   public boolean getShowSelectDeck()
   {
      return _showSelectDeck;
   }
   public boolean setShowSelectDeck(boolean showSelectDeck)
   {
      _showSelectDeck = showSelectDeck;
      return true;
   }

   public boolean getShowSelectCard()
   {
      return _showSelectCard;
   }
   public boolean setShowSelectCard(boolean showSelectCard)
   {
      _showSelectCard = showSelectCard;
      return true;
   }

   public boolean getShowDeckError()
   {
      return _showDeckError;
   }
   public boolean setShowDeckError(boolean showDeckError)
   {
      _showDeckError = showDeckError;
      return true;
   }

   public boolean getShowDeckCardError()
   {
      return _showDeckCardError; 
   }
   public boolean setShowDeckCardError(boolean showDeckCardError)
   {
      _showDeckCardError = showDeckCardError;
      return true;
   }

   public boolean getShowComputerWon()
   {
      return _showComputerWon;
   }
   public boolean setShowComputerWon(boolean showComputerWon)
   {
      _showComputerWon = showComputerWon;
      return true;
   }

   public boolean getShowUserWon()
   {
      return _showUserWon;
   }
   public boolean setShowUserWon(boolean showUserWon)
   {
      _showUserWon = showUserWon;
      return true;
   }

   public JButton getUserCardButton()
   {
      return _userCardButton;
   }
   public boolean setUserCardButton(JButton userCardButton)
   {
      _userCardButton = userCardButton;
      return true;
   }

   // getters and setters
   public JButton[] getHumanButtons()
   {
      return _humanButtons;
   }
   public boolean setHumanButtons(JButton[] humanButtons)
   {
      _humanButtons = humanButtons;
      return true;
   }

   public JLabel[] getComputerLabels()
   {
      return _computerLabels;
   }
   public boolean setComputerLabels(JLabel[] computerLabels)
   {
      _computerLabels = computerLabels;
      return true;
   }

   /*
public JButton[] getPlayButtons()
{
   return _playButtons;
}
public boolean setPlayButtons(JButton[] playButtons)
{
   _playButtons = playButtons;
   return true;
}
    */

   public boolean getComputerPlayFailed()
   {
      return _computerPlayFailed;
   }
   public boolean setComputerPlayFailed(boolean computerPlayFailed)
   {
      _computerPlayFailed = computerPlayFailed;
      return true;
   }

   // new tracking variables for "I cannot play" selections
   public int getUserPlayFail()
   {
      return _userPlayFail;
   }
   public Boolean addUserPlayFail()
   {
      _userPlayFail++;
      return true;
   }

   public int getComputerPlayFail()
   {
      return _computerPlayFail;
   }
   public Boolean addComputerPlayFail()
   {
      _computerPlayFail++;
      return true;
   }
}


//This class will control the positioning of the panels and cards of the GUI
class View extends JFrame implements ActionListener
{
   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;

   private int _height;
   private int _width;

   // constructor
   public View(String title, int width, int height)
   {
      // base constructor call
      super(title);

      // track the display values
      _width = width;
      _height = height;

      setSize(width, height);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      setLayout(new BorderLayout());
      pnlComputerHand = new JPanel();
      pnlComputerHand.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Computer Hand"));
      pnlComputerHand.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
      add(pnlComputerHand, "North");
      pnlHumanHand = new JPanel();
      pnlHumanHand.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Your Hand"));
      pnlHumanHand.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
      add(pnlHumanHand, "South");
      pnlPlayArea = new JPanel();
      pnlPlayArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Playing Area"));
      pnlPlayArea.setLayout(new GridLayout(2, 3));
      add(pnlPlayArea, "Center");
   }

   // build the display from the labels and buttons
   public void SetDisplay(Model model)
   {
      pnlComputerHand.removeAll();
      pnlHumanHand.removeAll();

      // ADD LABELS TO PANELS -----------------------------------------
      for (JLabel label : model.getComputerLabels())
      {
	 pnlComputerHand.add(label);
      }
      for (JButton button : model.getHumanButtons())
      {
	 pnlHumanHand.add(button);
      }

      // BUILD game - add the 2 cards for the play buttons and the 'i cannot play' button
      ShowPlayArea(model);

      // show everything to the user
      setVisible(true);
   }

   // rebuild the middle play area
   public void ShowPlayArea(Model model)
   {
      pnlPlayArea.removeAll();

      pnlPlayArea.add(model.getLeftDeckButton());
      pnlPlayArea.add(model.getRightDeckButton());
      pnlPlayArea.add(model.getUserCannotPlayButton());

      // check for a play fail record
      if (model.getComputerPlayFailed())
      {
	 pnlPlayArea.add(new JLabel("Computer Fails: " + model.getComputerPlayFail(), JLabel.CENTER));
	 pnlPlayArea.add(new JLabel("Computer Cannot Play"));
	 pnlPlayArea.add(new JLabel("User Fails: " + model.getUserPlayFail(), JLabel.CENTER));
      }
      else
      {
	 pnlPlayArea.add(new JLabel("Computer Fails: " + model.getComputerPlayFail(), JLabel.CENTER));
	 pnlPlayArea.add(new JLabel(GetDisplayMessage(model), JLabel.CENTER));
	 pnlPlayArea.add(new JLabel("User Fails: " + model.getUserPlayFail(), JLabel.CENTER));
      }

      setVisible(true);
   }

   public String GetDisplayMessage(Model model)
   {
      if (model.getShowSelectDeck())
      {
	 return "Select a deck";
      }
      else if (model.getShowSelectCard())
      {
	 return "Select a card from hand";
      }
      else if (model.getShowDeckError())
      {
	 return "Select a card before a deck";
      }
      else if (model.getShowDeckCardError())
      {
	 return "Selected card can't be added";
      }
      else if (model.getShowComputerWon())
      {
	 return "The Computer Won!";
      }
      else if (model.getShowUserWon())
      {
	 return "You Won!";
      }

      return "";
   }

   @Override
   public void actionPerformed(ActionEvent arg0)
   {
      System.exit(0);
   }
}



