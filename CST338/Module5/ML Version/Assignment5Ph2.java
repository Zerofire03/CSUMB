import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.lang.Math;

public class Assignment5Ph2
{
   // static for the 57 icons and their corresponding labels
   // normally we would not have a separate label for each card, but
   // if we want to display all at once using labels, we need to.

   static final int NUM_CARD_IMAGES = 57; // 52 + 4 jokers + 1 back-of-card image
   static Icon[] icon = new ImageIcon[NUM_CARD_IMAGES];

   static final String[] CARDS = new String[] {"A","2","3","4","5","6","7","8","9","T","J","Q","K","X"};
   static final String[] SUITS = new String[] {"C","D","H","S"};
   static final String CARD_EXT = ".gif";
   static final String BACK_OF_CARD = "BK";

   static final String BASE_PATH = "images/";
   
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];  
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 

   static void loadCardIcons()
   {
      // build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif", etc.)
      // in a SHORT loop.  For each file name, read it in and use it to
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

   // a simple main to throw all the JLabels out there for the world to see
   public static void main(String[] args)
   {
      GUICard.loadCardIcons();
      
      // establish main frame in which program will run
      CardTable myCardTable = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
      myCardTable.setSize(800, 600);
      //myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // CREATE LABELS ----------------------------------------------------
      // computer cards
      for (int i = 0; i < NUM_CARDS_PER_HAND; i++)
      {
	 computerLabels[i] = new JLabel(GUICard.getBackCardIcon());
      }
      
      // human cards
      for (int i = 0; i < NUM_CARDS_PER_HAND; i++)
      {
	 humanLabels[i] = new JLabel(GUICard.getIcon(generateRandomCard()));
      }
  
      // ADD LABELS TO PANELS -----------------------------------------
      for (JLabel label : computerLabels)
      {
	 myCardTable.pnlComputerHand.add(label);
      }
      for (JLabel label : humanLabels)
      {
	 myCardTable.pnlHumanHand.add(label);
      }
      
      // and two random cards in the play region (simulating a computer/hum ply)
      //playedCardLabels[0] = new JLabel(GUICard.getIcon(generateRandomCard()), JLabel.CENTER);
      //playedCardLabels[1] = new JLabel(GUICard.getIcon(generateRandomCard()), JLabel.CENTER);
      
      playedCardLabels[0] = new JLabel(GUICard.getIcon(generateRandomCard()), JLabel.CENTER);
      playedCardLabels[1] = new JLabel(GUICard.getIcon(generateRandomCard()), JLabel.CENTER);
      
      myCardTable.pnlPlayArea.add(playedCardLabels[0]);
      myCardTable.pnlPlayArea.add(playedCardLabels[1]);
      myCardTable.pnlPlayArea.add(new JLabel("Computer", JLabel.CENTER));
      myCardTable.pnlPlayArea.add(new JLabel("You", JLabel.CENTER));
      
      // show everything to the user
      myCardTable.setVisible(true);
   }
   
   // return a new random card
   static Card generateRandomCard() {
      int cardVal = (int)(Math.random() * Card.valuRanks.length);
      int suitVal = (int)(Math.random() * Card.Suit.values().length);
      
      return new Card(Card.valuRanks[cardVal], Card.Suit.values()[suitVal]);
   }
}

// This class will control the positioning of the panels and cards of the GUI
class CardTable extends JFrame
{
   static int MAX_CARDS_PER_HAND = 56;
   static int MAX_PLAYERS = 2;  // for now, we only allow 2 person games

   private int numCardsPerHand;
   private int numPlayers;

   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;

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
	 System.out.println("numCardsPerHand: " + numCardsPerHand + " was above max: " + MAX_CARDS_PER_HAND + ".  Using the max.");
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
      
      setLayout( new BorderLayout() );
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
      pnlPlayArea.setLayout(new GridLayout(2,2));
      add(pnlPlayArea, "Center");
   }
}

// manages the reading and building of the card image Icons
class GUICard
{
   private static final String[] CARDS = new String[] {"A","2","3","4","5","6","7","8","9","T","J","Q","K","X"};
   private static final String[] SUITS = new String[] {"C","D","H","S"};
   private static final String CARD_EXT = ".gif";
   private static final String BACK_OF_CARD = "BK";

   private static final String BASE_PATH = "images/";

   private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K + joker
   private static Icon iconBack;
   static boolean iconsLoaded = false;

   static void loadCardIcons()
   {
      // build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif", etc.)
      // in a SHORT loop.  For each file name, read it in and use it to
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
class Card{
   private char _value;
   private Suit _suit;
   private boolean _errorFlag;
   private static final ArrayList<String> VALID_VALUES = new ArrayList<>(Arrays.asList("A","2","3","4","5","6","7","8","9","T","J","Q","K","X"));

// put the order of the card values in here with the smallest first, include 'X' for a joker
   public static char[] valuRanks = new char[] {'2','3','4','5','6','7','8','9','T','J','Q','K','A','X'};
   
   public enum Suit{
      clubs,
      diamonds,
      hearts,
      spades
   }
   
   // constructors
   public Card(){
      Set('A', Suit.spades);
   }

   public Card(char value, Suit suit){
      Set(value, suit);
   }
   
   // Accessors
   public char getValue(){
      return _value;
   }
   public boolean setValue(char value){
      
      if (!VALID_VALUES.contains(String.valueOf(value)))
      {
	 return false;
      }
      
      _value = value;
      
      return true;
   }

   public Suit getSuit(){
      return _suit;
   }
   public boolean setSuit(Suit suit){
      _suit = suit;
      
      return true;
   }

   public boolean getErrorFlag(){
      return _errorFlag;
   }
   /* -- should not have accessor
   public void setErrorFlag(boolean errorFlag){
      _errorFlag = errorFlag;
   }
   */

   // standard functions
   public String toString(){
      if (_errorFlag == true){
	 return "** invalid **";
      }
      return _value + " of " + _suit;
   }

   // validate all public elements and return true if they are a match
   public boolean equals(Card card){
      if (this._value == card.getValue() && this._suit == card.getSuit() && this._errorFlag == card.getErrorFlag()){
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

      return curr - cVal;
   }

   // validate incoming param values
   private boolean isValid(char value, Suit suit){
      if (!VALID_VALUES.contains(String.valueOf(value)) || suit == null){
	 return false;
      }
      return true;
   }
   
   // public methods
   // validate and populate the internal variables
   public boolean Set(char value, Suit suit){
      if (!isValid(value, suit)){
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
	    if (cards[h-1].compareTo(cards[h]) > 0)
	    {
	       // swap values
	       temp = cards[h-1];
	       cards[h-1] = cards[h];
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

class Hand{
   public static final int MAX_CARDS = 50;
   private Card[] _myCards;
   private int _numCards;

   // constructor
   public Hand(){
      _myCards = new Card[MAX_CARDS];
      _numCards = 0;
   }

   // accessors
   public boolean setNumCards(int numCards){

      if (numCards > MAX_CARDS)
      {
	 return false;
      }

      _numCards = numCards;
      return true;
   }

   public int getNumCards(){
      return _numCards;
   }

   // public methods
   // clear the hand
   public void resetHand(){
      _myCards = new Card[MAX_CARDS];
      _numCards = 0;
   }

   // add a card to the hand
   public boolean takeCard(Card card){
      // test for max cards
      if (_numCards == MAX_CARDS){
	 return false;
      }
      _myCards[_numCards] = card;
      _numCards++;
      return true;
   }

   // take the top card from the hand
   public Card playCard(){
      Card retCard = _myCards[_numCards-1];
      _myCards[_numCards-1] = null;
      _numCards--;

      return retCard;
   }

   // show the entire hand
   public String toString(){
      String retStr = "Hand = ( ";

      for (int i = 0; i < _numCards; i++){
	 if (i > 0){
	    retStr += ", ";
	 }
	 retStr += _myCards[i].getValue() + " of " + _myCards[i].getSuit();
      }
      retStr += " )";
      return retStr;
   }

   public Card inspectCard(int k){
      if (k > _numCards){
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

class Deck{
   public final int MAX_CARDS = 6 * 52;
   private static Card[] masterPack;
   private Card[] cards;
   private int topCard;
   private int numPacks;

   //Allocate master pack
   private static void allocateMasterPack(){
      char[] cardValue = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K','X'};
      masterPack = new Card [56];
      for( int i = 0; i < 13; i++ ){
	 masterPack[ 0 + ( i * 4 ) ] = new Card( cardValue[i], Card.Suit.hearts );
	 masterPack[ 1 + ( i * 4 ) ] = new Card( cardValue[i], Card.Suit.spades );
	 masterPack[ 2 + ( i * 4 ) ] = new Card( cardValue[i], Card.Suit.clubs );
	 masterPack[ 3 + ( i * 4 ) ] = new Card( cardValue[i], Card.Suit.diamonds );
      }
   }

   //Constructor no params
   public Deck(){
      allocateMasterPack();
      numPacks = 1;
      cards = masterPack;
      topCard = cards.length;
   }

   //Constructor params
   public Deck(int numPacks){
      allocateMasterPack();
      this.numPacks = numPacks;
      cards = new Card[ numPacks * 52 ];
      for( int i = 0; i < numPacks; i++ ){
	 for( int j = 0; j < 52; j++ ){
	    cards[ (i * 52) + j ] = masterPack[j];
	 }
      }
      topCard = cards.length;
   }
   
   //Accessor for the int of the topCard
   public int getTopCard(){
      return topCard;
   }
   
   // retrieve the number of cards in the deck
   public int getNumCards()
   {
      return topCard;
   }

   // methods

   //Re-populate cards[] with the standard 52 x numPacks cards
   public void init(int numPacks){
      this.numPacks = numPacks;
      cards = new Card[ numPacks * 52 ];
      for( int i = 0; i < numPacks; i++ ){
	 for( int j = 0; j < 52; j++ ){
	    cards[ (i * 52) + j ] = masterPack[j];
	 }
      }
      topCard = cards.length;
   }
   
   //Mixes up the cards using a standard random number
   public void shuffle(){
      Collections.shuffle(Arrays.asList(cards));
   }

   //Returns and removes the card in the top occupied position of cards[]
   public Card dealCard(){
      // check for no more cards
      if (topCard == 0){
	 return null;
      }

      topCard -= 1;
      return cards[topCard];
   }
   
   //make sure that there are not too many instances of the card in the deck if you add it.
   // 	Return false if there will be too many.  It should put the card on the top of the deck.
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
   //		Put the current top card into its place.
   //		Be sure the card you need is actually still in the deck, if not return false.
   public boolean removeCard(Card card)
   {
      for (int x = 0; x < topCard; x++)
      {
	 // check for match.  overwrite with topcard if found
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

