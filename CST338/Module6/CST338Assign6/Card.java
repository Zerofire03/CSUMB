import java.util.ArrayList;
import java.util.Arrays;

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