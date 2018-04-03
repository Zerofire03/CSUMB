
/*
Michael Loeser
Assignment 5 - Phase 3
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.lang.Math;

public class Assignment5Ph3
{
   // static for the 57 icons and their corresponding labels
   // normally we would not have a separate label for each card, but
   // if we want to display all at once using labels, we need to.

   static final int NUM_CARD_IMAGES = 57; // 52 + 4 jokers + 1 back-of-card image
   static Icon[] icon = new ImageIcon[NUM_CARD_IMAGES];

   static final String[] CARDS = new String[]
   { "A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "X" };
   static final String[] SUITS = new String[]
   { "C", "D", "H", "S" };
   static final String CARD_EXT = ".gif";
   static final String BACK_OF_CARD = "BK";

   static final String BASE_PATH = "images/";

   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   // static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JButton[] humanButtons = new JButton[NUM_CARDS_PER_HAND];
   static JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
   static JLabel[] playLabelText = new JLabel[NUM_PLAYERS];

   static Card[] winnings = new Card[NUM_CARDS_PER_HAND];
   static int numWins = 0;
   static int playsAvailable = NUM_CARDS_PER_HAND;

   private static int HUMAN_HAND = 1;
   private static int COMPUTER_HAND = 0;

   // a simple main to throw all the JLabels out there for the world to see
   public static void main(String[] args)
   {
      int numPacksPerDeck = 1;
      int numJokersPerPack = 4;
      int numUnusedCardsPerPack = 0;
      Card[] unusedCardsPerPack = null;

      CardGameFramework highCardGame = new CardGameFramework(numPacksPerDeck, numJokersPerPack, numUnusedCardsPerPack,
            unusedCardsPerPack, NUM_PLAYERS, NUM_CARDS_PER_HAND);

      GUICard.loadCardIcons();

      // deal the game
      if (!highCardGame.deal())
      {
         System.out.println("HighCardGame.deal was unable to deal out enough cards");
      }

      // establish main frame in which program will run
      CardTable myCardTable = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
      myCardTable.setSize(800, 600);
      // myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // CREATE LABELS ----------------------------------------------------
      // computer cards
      for (int i = 0; i < NUM_CARDS_PER_HAND; i++)
      {
         computerLabels[i] = new JLabel(GUICard.getBackCardIcon());
      }

      // human cards
      JButton humanButton = null;
      for (int i = 0; i < NUM_CARDS_PER_HAND; i++)
      {
         Card playCard = highCardGame.getHand(HUMAN_HAND).inspectCard(i);
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
               Card computerCard = highCardGame.getHand(COMPUTER_HAND).playCard();

               // testing testing
               System.out.println("Computer card: " + computerCard.toString());

               // clear and repopulate the play area panel
               myCardTable.pnlPlayArea.removeAll();
               myCardTable.pnlPlayArea.add(new JLabel(GUICard.getIcon(computerCard), JLabel.CENTER));
               myCardTable.pnlPlayArea.add(new JLabel(" ", JLabel.CENTER));
               myCardTable.pnlPlayArea.add(new JLabel(GUICard.getIcon(playCard), JLabel.CENTER));

               // do the testing
               if (playCard.compareTo(computerCard) > 0)
               {
                  winnings[numWins] = computerCard;
                  numWins++;

                  // human wins
                  myCardTable.pnlPlayArea.add(new JLabel("Computer Lost!", JLabel.CENTER));
                  myCardTable.pnlPlayArea.add(new JLabel("Win Total:" + numWins, JLabel.CENTER));
                  myCardTable.pnlPlayArea.add(new JLabel("You Won!", JLabel.CENTER));
               } 
               else if (playCard.compareTo(computerCard) == 0)
               {
                  // tie
                  myCardTable.pnlPlayArea.add(new JLabel("Computer Tie!", JLabel.CENTER));
                  myCardTable.pnlPlayArea.add(new JLabel("Win Total:" + numWins, JLabel.CENTER));
                  myCardTable.pnlPlayArea.add(new JLabel("You Tie!", JLabel.CENTER));
               } 
               else if (playCard.compareTo(computerCard) < 0)
               {
                  // computer wins
                  myCardTable.pnlPlayArea.add(new JLabel("Computer Won!", JLabel.CENTER));
                  myCardTable.pnlPlayArea.add(new JLabel("Win Total:" + numWins, JLabel.CENTER));
                  myCardTable.pnlPlayArea.add(new JLabel("You Lost!", JLabel.CENTER));
               }

               // remove the cards from display using counter of plays
               // pull the computer item from top component
               myCardTable.pnlComputerHand.getComponent(playsAvailable).setVisible(false);

               // pull the original button from the actionevent
               ((JButton) e.getSource()).setVisible(false);

               // show the updated display
               myCardTable.setVisible(true);
            }
         });

         humanButtons[i] = humanButton;
      }

      // ADD LABELS TO PANELS -----------------------------------------
      for (JLabel label : computerLabels)
      {
         myCardTable.pnlComputerHand.add(label);
      }
      for (JButton button : humanButtons)
      {
         myCardTable.pnlHumanHand.add(button);
      }

      // show everything to the user
      myCardTable.setVisible(true);
   }

   static void loadCardIcons()
   {
      // build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif", etc.)
      // in a SHORT loop. For each file name, read it in and use it to
      // instantiate each of the 57 Icons in the icon[] array.

      // loop through the suits
      int counter = 0;
      for (String suit : SUITS)
      {
         // loop through the cards
         for (String card : CARDS)
         {
            ImageIcon i = new ImageIcon(BASE_PATH + card + suit + CARD_EXT, card + suit);
            icon[counter] = i;
            counter++;
         }
      }

      // add the single backofcard image
      ImageIcon bk = new ImageIcon(BASE_PATH + BACK_OF_CARD + CARD_EXT, BACK_OF_CARD);
      icon[counter] = bk;
   }

   // turns 0 - 13 into "A", "2", "3", ... "Q", "K", "X"
   static String turnIntIntoCardValue(int k)
   {
      // an idea for a helper method (do it differently if you wish)

      // check for invalid index
      if (k < 0 || k > 13)
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

   // return a new random card
   static Card generateRandomCard()
   {
      int cardVal = (int) (Math.random() * Card.valuRanks.length);
      int suitVal = (int) (Math.random() * Card.Suit.values().length);

      return new Card(Card.valuRanks[cardVal], Card.Suit.values()[suitVal]);
   }
}

// This class will control the positioning of the panels and cards of the GUI
class CardTable extends JFrame
{
   static int MAX_CARDS_PER_HAND = 56;
   static int MAX_PLAYERS = 2; // for now, we only allow 2 person games

   private int numCardsPerHand;
   private int numPlayers;

   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;

   // store the winning cards
   static Icon[] winnings;
   static int numWins = 0;

   // accessors for private vars
   public int NumCardsPerHand()
   {
      return numCardsPerHand;
   }

   public int NumPlayers()
   {
      return numPlayers;
   }

   // constructor
   public CardTable(String title, int numCardsPerHand, int numPlayers)
   {
      // base constructor call
      super(title);

      // check for max cards
      if (numCardsPerHand > MAX_CARDS_PER_HAND)
      {
         System.out.println(
               "numCardsPerHand: " + numCardsPerHand + " was above max: " + MAX_CARDS_PER_HAND + ".  Using the max.");
         this.numCardsPerHand = MAX_CARDS_PER_HAND;
      } else
      {
         this.numCardsPerHand = numCardsPerHand;
      }

      // check for max players
      if (numPlayers > MAX_PLAYERS)
      {
         System.out.println("numPlayers: " + numPlayers + " was above max: " + MAX_PLAYERS + ".  Using the max.");
         this.numPlayers = MAX_PLAYERS;
      } else
      {
         this.numPlayers = numPlayers;
      }

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
}

// manages the reading and building of the card image Icons
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
   /*
    * -- should not have accessor public void setErrorFlag(boolean errorFlag){
    * _errorFlag = errorFlag; }
    */

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

class Hand
{
   public static final int MAX_CARDS = 50;
   private Card[] _myCards;
   private int _numCards;

   // constructor
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
   public Card playCard(int cardIndex)
   {
      // check for max number of cards
      if (_myCards.length < cardIndex)
      {
         return null;
      }

      Card retCard = _myCards[cardIndex - 1];
      _myCards[cardIndex - 1] = null;
      _numCards--;

      return retCard;
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

// class CardGameFramework ----------------------------------------------------
class CardGameFramework
{
   private static final int MAX_PLAYERS = 50;

   private int numPlayers;
   private int numPacks; // # standard 52-card packs per deck
   // ignoring jokers or unused cards
   private int numJokersPerPack; // if 2 per pack & 3 packs per deck, get 6
   private int numUnusedCardsPerPack; // # cards removed from each pack
   private int numCardsPerHand; // # cards to deal each player
   private Deck deck; // holds the initial full deck and gets
   // smaller (usually) during play
   private Hand[] hand; // one Hand for each player
   private Card[] unusedCardsPerPack; // an array holding the cards not used
   // in the game. e.g. pinochle does not
   // use cards 2-8 of any suit

   public CardGameFramework(int numPacks, int numJokersPerPack, int numUnusedCardsPerPack, Card[] unusedCardsPerPack,
         int numPlayers, int numCardsPerHand)
   {
      int k;

      // filter bad values
      if (numPacks < 1 || numPacks > 6)
         numPacks = 1;
      if (numJokersPerPack < 0 || numJokersPerPack > 4)
         numJokersPerPack = 0;
      if (numUnusedCardsPerPack < 0 || numUnusedCardsPerPack > 50) // > 1 card
         numUnusedCardsPerPack = 0;
      if (numPlayers < 1 || numPlayers > MAX_PLAYERS)
         numPlayers = 4;
      // one of many ways to assure at least one full deal to all players
      if (numCardsPerHand < 1 || numCardsPerHand > numPacks * (52 - numUnusedCardsPerPack) / numPlayers)
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

   public Card getCardFromDeck()
   {
      return deck.dealCard();
   }

   public int getNumCardsRemainingInDeck()
   {
      return deck.getNumCards();
   }

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
         deck.removeCard(unusedCardsPerPack[k]);

      // add jokers
      for (k = 0; k < numPacks; k++)
         for (j = 0; j < numJokersPerPack; j++)
            deck.addCard(new Card('X', Card.Suit.values()[j]));

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
      for (k = 0; k < numCardsPerHand && enoughCards; k++)
      {
         for (j = 0; j < numPlayers; j++)
            if (deck.getNumCards() > 0)
               hand[j].takeCard(deck.dealCard());
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
      if (playerIndex < 0 || playerIndex > numPlayers - 1 || cardIndex < 0 || cardIndex > numCardsPerHand - 1)
      {
         // Creates a card that does not work
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

class EndingListener implements ActionListener
{

   @Override
   public void actionPerformed(ActionEvent arg0)
   {
      System.exit(0);
   }

}
