/*
 * Assignment 3: Decks of Cards
 * By:   Christopher Holmes
 *       Micheal Loeser
 *       Patrick Gonzalez
 *       Christopher Buckey
 */

import java.util.*;

public class Assign3 {
   
   public static void main(String[] args){

      /** Test Phase 1 **/
      System.out.println("**** Phase 1 testing ****");

      Card card1 = new Card('A', Card.Suit.spades);
      Card card2 = new Card('R', Card.Suit.clubs);
      Card card3 = new Card('9', Card.Suit.hearts);

      System.out.println(card1.toString());
      System.out.println(card2.toString());
      System.out.println(card3.toString());

      /** Test Phase 2 **/
      System.out.println("");
      System.out.println("**** Phase 2 testing ****");

      Card ph2Card1 = new Card('K', Card.Suit.diamonds);
      Card ph2Card2 = new Card('5', Card.Suit.clubs);
      Card ph2Card3 = new Card('T', Card.Suit.hearts);
      Card ph2Card4 = new Card('J', Card.Suit.spades);
      Card ph2Card5 = new Card('3', Card.Suit.clubs);

      Hand testHand = new Hand();

      // add cards to the hand - creates duplicate entries
      while (testHand.getNumCards() < testHand.MAX_CARDS) {
         testHand.takeCard(ph2Card1);
         testHand.takeCard(ph2Card2);
         testHand.takeCard(ph2Card3);
         testHand.takeCard(ph2Card4);
         testHand.takeCard(ph2Card5);
         }

      // show the full hand
      System.out.println(testHand.toString());
      System.out.println("");

      // play all cards in the hand
      while (testHand.getNumCards() > 0){
         Card pCard = testHand.playCard();
         System.out.println("Played card: " + pCard.toString());
      }

      System.out.println("");

      // validate the empty hand
      System.out.println(testHand.toString());

      /*****Test Phase 3******/
      System.out.println("");
      System.out.println("**** Phase 3 testing ****");

      Deck myDeck = new Deck(2);
   
      do{
         System.out.println(myDeck.dealCard().toString());
      } while(myDeck.getTopCard() != 0);
      
      System.out.println("");

      myDeck.init(2);
      myDeck.shuffle();
   
      do{
         System.out.println(myDeck.dealCard().toString());
      } while(myDeck.getTopCard() != 0);
   
      System.out.println("");

      Deck myDeckSingle = new Deck();
      do{
         System.out.println(myDeckSingle.dealCard().toString());
      } while(myDeckSingle.getTopCard() != 0);
   
      System.out.println("");

      myDeckSingle.init(1);
      myDeckSingle.shuffle();
   
      do{
         System.out.println(myDeckSingle.dealCard().toString());
      } while(myDeckSingle.getTopCard() != 0);

      // Phase 4: The deck and hand class
      System.out.println("");
      System.out.println("***** Phase 4 *****");
   
      int numHands;
      do{
         System.out.println("How many hands? (1-10, please):");
         numHands = keyboard.nextInt();
      } while(numHands <= 0 || numHands > 10);
      keyboard.close();

      // instantiate a single-pack Deck object without shuffling
      Deck theDeck = new Deck();

      // Declare an array for the number of hands/players
      Hand[] playerHand = new Hand[numHands];

      for (int i = 0; i < numHands; i++){
         playerHand[i] = new Hand();
      }

       // Give each Player/Hand a card from the deck
      do{
         for (int i = 0; i < numHands; i++){
            Card tmpCard = theDeck.dealCard();
             // test to be sure deck has issued a card.  if not, break
               if (tmpCard != null){
                  playerHand[i].takeCard(tmpCard);
               }
               else{
                  break;
               }
         }
      } while(theDeck.getTopCard() != 0);
      
      System.out.println();
      System.out.println("Here are the players hands from an unshuffled deck:");
   
      for (int i = 0; i < numHands; i++){
         System.out.print(playerHand[i].toString());
         System.out.println();
      }

      // Reset the deck and shuffle it
      theDeck.init(1);
      theDeck.shuffle();

      // Clear the players hands
      for (int i = 0; i < numHands; i++){
         playerHand[i].resetHand();
      }

      // Pass out shuffled deck
      do{
         for (int i = 0; i < numHands; i++){
               Card tmpCard = theDeck.dealCard();
            // test to be sure deck has issued a card.  if not, break
            if (tmpCard != null){
               playerHand[i].takeCard(tmpCard);
            }
            else{
               break;
            }
         }
      } while(theDeck.getTopCard() != 0);

      System.out.println();
      System.out.println("Here are the players hands from a shuffled deck:");  
      for (int i = 0; i < numHands; i++){
         System.out.print(playerHand[i].toString());
         System.out.println();
      }

   }
   static Scanner keyboard = new Scanner(System.in);
}

class Card{
   private char _value;
   private Suit _suit;
   private boolean _errorFlag;
   private static final ArrayList<String> VALID_VALUES = new ArrayList<>(Arrays.asList("A","1","2","3","4","5","6","7","8","9","T","J","Q","K"));

   public char getValue(){
      return _value;
   }

   public void setValue(char value){
      _value = value;
   }

   public Suit getSuit(){
      return _suit;
   }

   public void setSuit(Suit suit){
      _suit = suit;
   }

   public boolean getErrorFlag(){
      return _errorFlag;
   }

   public void setErrorFlag(boolean errorFlag){
      _errorFlag = errorFlag;
   }

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

   public String toString(){
      if (_errorFlag == true){
         return "** invalid **";
      }
      return _value + " of " + _suit;
   }

   // validate and populate the internal variables
   public void Set(char value, Suit suit){
      if (!isValid(value, suit)){
         _errorFlag = true;
         return;
      }
      _value = value;
      _suit = suit;
      _errorFlag = false;
   }

   // validate all public elements and return true if they are a match
   public boolean equals(Card card){
      if (this._value == card.getValue() && this._suit == card.getSuit() && this._errorFlag == card.getErrorFlag()){
         return true;
      }
      return false;
   }

   // validate incoming param values
   private boolean isValid(char value, Suit suit){
      if (!VALID_VALUES.contains(String.valueOf(value)) || suit == null){
         return false;
      }
      return true;
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
   public void setNumCards(int numCards){
      _numCards = numCards;
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
         errCard.setErrorFlag(true);
         return errCard;
      }
      return _myCards[k];
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
      char[] cardValue = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
      masterPack = new Card [52];
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

   //Accessor for the int of the topCard
   public int getTopCard(){
      return topCard;
   }
}

/*************** OUTPUT **************
**** Phase 1 testing ****
A of spades
** invalid **
9 of hearts

**** Phase 2 testing ****
Hand = ( K of diamonds, 5 of clubs, T of hearts, J of spades, 3 of clubs, K of diamonds, 5 of clubs, T of hearts, J of spades, 3 of clubs, K of diamonds, 5 of clubs, T of hearts, J of spades, 3 of clubs, K of diamonds, 5 of clubs, T of hearts, J of spades, 3 of clubs, K of diamonds, 5 of clubs, T of hearts, J of spades, 3 of clubs, K of diamonds, 5 of clubs, T of hearts, J of spades, 3 of clubs, K of diamonds, 5 of clubs, T of hearts, J of spades, 3 of clubs, K of diamonds, 5 of clubs, T of hearts, J of spades, 3 of clubs, K of diamonds, 5 of clubs, T of hearts, J of spades, 3 of clubs, K of diamonds, 5 of clubs, T of hearts, J of spades, 3 of clubs )

Played card: 3 of clubs
Played card: J of spades
Played card: T of hearts
Played card: 5 of clubs
Played card: K of diamonds
Played card: 3 of clubs
Played card: J of spades
Played card: T of hearts
Played card: 5 of clubs
Played card: K of diamonds
Played card: 3 of clubs
Played card: J of spades
Played card: T of hearts
Played card: 5 of clubs
Played card: K of diamonds
Played card: 3 of clubs
Played card: J of spades
Played card: T of hearts
Played card: 5 of clubs
Played card: K of diamonds
Played card: 3 of clubs
Played card: J of spades
Played card: T of hearts
Played card: 5 of clubs
Played card: K of diamonds
Played card: 3 of clubs
Played card: J of spades
Played card: T of hearts
Played card: 5 of clubs
Played card: K of diamonds
Played card: 3 of clubs
Played card: J of spades
Played card: T of hearts
Played card: 5 of clubs
Played card: K of diamonds
Played card: 3 of clubs
Played card: J of spades
Played card: T of hearts
Played card: 5 of clubs
Played card: K of diamonds
Played card: 3 of clubs
Played card: J of spades
Played card: T of hearts
Played card: 5 of clubs
Played card: K of diamonds
Played card: 3 of clubs
Played card: J of spades
Played card: T of hearts
Played card: 5 of clubs
Played card: K of diamonds

Hand = (  )

**** Phase 3 testing ****
K of diamonds
K of clubs
K of spades
K of hearts
Q of diamonds
Q of clubs
Q of spades
Q of hearts
J of diamonds
J of clubs
J of spades
J of hearts
T of diamonds
T of clubs
T of spades
T of hearts
9 of diamonds
9 of clubs
9 of spades
9 of hearts
8 of diamonds
8 of clubs
8 of spades
8 of hearts
7 of diamonds
7 of clubs
7 of spades
7 of hearts
6 of diamonds
6 of clubs
6 of spades
6 of hearts
5 of diamonds
5 of clubs
5 of spades
5 of hearts
4 of diamonds
4 of clubs
4 of spades
4 of hearts
3 of diamonds
3 of clubs
3 of spades
3 of hearts
2 of diamonds
2 of clubs
2 of spades
2 of hearts
A of diamonds
A of clubs
A of spades
A of hearts
K of diamonds
K of clubs
K of spades
K of hearts
Q of diamonds
Q of clubs
Q of spades
Q of hearts
J of diamonds
J of clubs
J of spades
J of hearts
T of diamonds
T of clubs
T of spades
T of hearts
9 of diamonds
9 of clubs
9 of spades
9 of hearts
8 of diamonds
8 of clubs
8 of spades
8 of hearts
7 of diamonds
7 of clubs
7 of spades
7 of hearts
6 of diamonds
6 of clubs
6 of spades
6 of hearts
5 of diamonds
5 of clubs
5 of spades
5 of hearts
4 of diamonds
4 of clubs
4 of spades
4 of hearts
3 of diamonds
3 of clubs
3 of spades
3 of hearts
2 of diamonds
2 of clubs
2 of spades
2 of hearts
A of diamonds
A of clubs
A of spades
A of hearts

A of hearts
Q of clubs
K of clubs
8 of diamonds
3 of hearts
8 of hearts
K of hearts
2 of clubs
2 of hearts
5 of clubs
T of diamonds
J of diamonds
4 of diamonds
K of clubs
Q of diamonds
K of diamonds
2 of diamonds
K of spades
4 of diamonds
A of diamonds
T of clubs
6 of spades
6 of diamonds
T of hearts
5 of spades
4 of hearts
7 of clubs
K of spades
6 of clubs
Q of diamonds
6 of diamonds
8 of hearts
Q of spades
Q of hearts
K of hearts
J of spades
3 of spades
8 of spades
4 of spades
T of diamonds
7 of hearts
7 of hearts
8 of clubs
T of hearts
2 of spades
5 of clubs
9 of clubs
8 of clubs
5 of spades
3 of hearts
Q of clubs
4 of clubs
7 of diamonds
A of diamonds
A of clubs
5 of diamonds
7 of spades
J of diamonds
A of spades
6 of spades
T of clubs
Q of spades
4 of hearts
6 of clubs
7 of clubs
9 of clubs
9 of diamonds
5 of hearts
3 of diamonds
J of hearts
9 of hearts
A of clubs
2 of diamonds
6 of hearts
J of clubs
A of hearts
3 of diamonds
4 of clubs
2 of spades
7 of spades
3 of spades
3 of clubs
8 of diamonds
9 of hearts
6 of hearts
J of clubs
5 of hearts
9 of spades
9 of diamonds
7 of diamonds
T of spades
3 of clubs
Q of hearts
5 of diamonds
8 of spades
K of diamonds
2 of hearts
4 of spades
J of hearts
2 of clubs
T of spades
A of spades
J of spades
9 of spades

K of diamonds
K of clubs
K of spades
K of hearts
Q of diamonds
Q of clubs
Q of spades
Q of hearts
J of diamonds
J of clubs
J of spades
J of hearts
T of diamonds
T of clubs
T of spades
T of hearts
9 of diamonds
9 of clubs
9 of spades
9 of hearts
8 of diamonds
8 of clubs
8 of spades
8 of hearts
7 of diamonds
7 of clubs
7 of spades
7 of hearts
6 of diamonds
6 of clubs
6 of spades
6 of hearts
5 of diamonds
5 of clubs
5 of spades
5 of hearts
4 of diamonds
4 of clubs
4 of spades
4 of hearts
3 of diamonds
3 of clubs
3 of spades
3 of hearts
2 of diamonds
2 of clubs
2 of spades
2 of hearts
A of diamonds
A of clubs
A of spades
A of hearts

6 of hearts
8 of spades
4 of hearts
T of diamonds
2 of hearts
Q of diamonds
A of clubs
3 of diamonds
5 of spades
T of spades
J of spades
7 of diamonds
4 of spades
2 of diamonds
A of hearts
A of spades
9 of clubs
8 of clubs
8 of hearts
7 of clubs
3 of clubs
K of diamonds
8 of diamonds
7 of hearts
9 of hearts
6 of spades
9 of spades
J of diamonds
3 of hearts
4 of diamonds
6 of diamonds
K of clubs
A of diamonds
J of hearts
T of hearts
7 of spades
4 of clubs
2 of spades
3 of spades
J of clubs
5 of diamonds
K of spades
T of clubs
K of hearts
Q of hearts
9 of diamonds
Q of spades
5 of hearts
5 of clubs
6 of clubs
2 of clubs
Q of clubs

***** Phase 4 *****
How many hands? (1-10, please):
6

Here are the players hands from an unshuffled deck:
Hand = ( K of diamonds, Q of spades, T of diamonds, 9 of spades, 7 of diamonds, 6 of spades, 4 of diamonds, 3 of spades, A of diamonds )
Hand = ( K of clubs, Q of hearts, T of clubs, 9 of hearts, 7 of clubs, 6 of hearts, 4 of clubs, 3 of hearts, A of clubs )
Hand = ( K of spades, J of diamonds, T of spades, 8 of diamonds, 7 of spades, 5 of diamonds, 4 of spades, 2 of diamonds, A of spades )
Hand = ( K of hearts, J of clubs, T of hearts, 8 of clubs, 7 of hearts, 5 of clubs, 4 of hearts, 2 of clubs, A of hearts )
Hand = ( Q of diamonds, J of spades, 9 of diamonds, 8 of spades, 6 of diamonds, 5 of spades, 3 of diamonds, 2 of spades )
Hand = ( Q of clubs, J of hearts, 9 of clubs, 8 of hearts, 6 of clubs, 5 of hearts, 3 of clubs, 2 of hearts )

Here are the players hands from a shuffled deck:
Hand = ( 3 of clubs, 4 of diamonds, 2 of hearts, 8 of spades, 9 of spades, J of spades, 7 of diamonds, 5 of diamonds, 6 of spades )
Hand = ( 5 of spades, 6 of diamonds, 3 of hearts, 9 of diamonds, 9 of hearts, Q of spades, 5 of clubs, K of hearts, 9 of clubs )
Hand = ( T of diamonds, T of spades, 3 of diamonds, A of spades, A of clubs, 4 of clubs, T of clubs, 2 of diamonds, 2 of clubs )
Hand = ( A of diamonds, Q of hearts, J of hearts, T of hearts, 2 of spades, 6 of clubs, 4 of hearts, K of spades, 3 of spades )
Hand = ( 8 of hearts, 8 of clubs, 8 of diamonds, 4 of spades, Q of diamonds, J of diamonds, 7 of hearts, J of clubs )
Hand = ( Q of clubs, 5 of hearts, K of diamonds, A of hearts, 7 of clubs, K of clubs, 6 of hearts, 7 of spades )

 **************************************/