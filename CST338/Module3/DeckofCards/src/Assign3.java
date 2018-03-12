
public class Assign3
{

   public static void main(String[] args)
   {
      Card testCardDefault = new Card();
      System.out.println( testCardDefault.toString() );
      
      Card testCardInvalid = new Card( 'Y', Card.Suit.clubs );
      System.out.println( testCardInvalid.toString() );
      
      Card testCardValid = new Card( 'J', Card.Suit.spades );
      System.out.println( testCardValid.toString() );
      
      System.out.println("");
      testCardInvalid.set( 'Q', Card.Suit.spades );
      System.out.println( testCardInvalid.toString() );
      
      testCardValid.set( 'Y', Card.Suit.clubs );
      System.out.println( testCardValid.toString() );

   }

}



class Card
{
   
   char value;
   Suit suit;
   boolean errorFlag;
   
   //Suit enum
   public enum Suit
   {
      clubs, diamonds, hearts, spades;
   }
   
   //Default constructor no values
   Card()
   {
      value = 'A';
      suit = Suit.spades;
   }
   
   //Default constructor all values
   Card( char value, Suit suit )
   {
      set( value, suit );
   }
   
   //Private helper method that return true or false depending on the legality of parameters
   private boolean isValid( char value, Suit suit )
   {
      char[] validValue = new char[] {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
      boolean valid = false;
      for ( char c : validValue )
      {
         if ( c == value )
         {
            valid = true;
            break;
         }
      }
      return valid;
   }
   
   //Mutator that accepts the legal values. Bad values set error flag to true
   public boolean set( char value, Suit suit )
   {
      if ( isValid( value, suit) )
      {
         this.value = value;
         this.suit = suit;
         errorFlag = false;
      }
      else
      {
         this.value = value;
         this.suit = suit;
         errorFlag = true;
      }
      return true;
   }
   
   //Returns a string of the car based on whether the error flag is set
   public String toString()
   {
      if( errorFlag )
      {
         return "** illegal **";
      }
      else
      {
         return value + " of " + suit;
      }
   }
   
   //Accessor for suit
   public Suit getSuit()
   {
      return suit;
   }
   
   //Accessor for value
   public char getValue()
   {
      return value;
   }
   
   //Accessor for errorFlag
   public boolean getErrorFlag()
   {
      return errorFlag;
   }
   
}

class Hand
{
   public int MAX_CARDS = 100;
   Card[] myCards;
   int numCards;
   
   //Default constructor
   Hand()
   {
      myCards = new Card[0];
      numCards = 0;
   }
   
   //Remove all cards from the hand
   public void resetHand()
   {
      myCards = new Card[0];
      numCards = 0;
   }
   
   //Adds a card to the next position in the myCards array
   public boolean takeCard( Card card )
   {
      if ( numCards + 1 <= MAX_CARDS )
      {
         Card[] temp = new Card[ numCards + 1 ];
         for ( int i =0; i <= numCards; i++ )
         {
            
         }
         
         return true;
      }
      else
      {
         return false;
      }
   }
}
