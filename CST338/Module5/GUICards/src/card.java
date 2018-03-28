import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 */

/**
 * @author cholmes
 *
 */
class Card
{
   private char _value;
   private Suit _suit;
   private boolean _errorFlag;
   private static final ArrayList<String> VALID_VALUES = new ArrayList<>(Arrays.asList("A","1","2","3","4","5","6","7","8","9","T","J","Q","K", "X"));
   public static char[] valuRanks = {'A', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'X'};

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
   public Card()
   {
      _value = 'A';
      _suit = Suit.spades;
   }

   public Card(char value, Suit suit){
      Set(value, suit);
      GUICard.loadCardIcons();
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
   
   //will sort the incoming array of cards using a bubble sort routine
   public static void arraySort(Card[] cards, int arraySize)
   {
      for( int i = 0; i < arraySize; i++ )
      {
         for( int j = 0; j < arraySize - i - 1; j++ )
         {
            if( checkValueRanks(cards[j], cards[j+1]) )
            {
               Card temp = cards[j];
               cards[j] = cards[j+1];
               cards[j+1] = temp;
            }
         }
      }
   }
   
   //Returns a boolean. Returns true if card1 is higher ranks than card 2
   private static boolean checkValueRanks(Card card1, Card card2)
   {
      if( Arrays.asList(valuRanks).indexOf(card1.getValue()) > Arrays.asList(valuRanks).indexOf(card2.getValue()) )
      {
         return true;
      }
      else
      {
         return false;
      }
      
   }
   
   //Returns the value of the card as an int
   public static int valueAsInt(Card card)
   {
      switch(card._value)
      {
         case 'A':
            return 0;
         case '2':
            return 1;
         case '3':
            return 2;
         case '4':
            return 3;
         case '5':
            return 4;
         case '6':
            return 5;
         case '7':
            return 6;
         case '8':
            return 7;
         case '9':
            return 8;
         case 'T':
            return 9;
         case 'J':
            return 10;
         case 'Q':
            return 11;
         case 'K':
            return 12;
         case 'X':
            return 13;
         default:
            return 14;               
      }
   }
   
   
   //Returns the suit of the card as an int
   public static int suitAsInt(Card card)
   {
      switch(card._suit)
      {
         case clubs:
            return 0;
         case diamonds:
            return 1;
         case spades:
            return 3;
         case hearts:
            return 2;
         default:
            return 4;
      }
   }
   
}
