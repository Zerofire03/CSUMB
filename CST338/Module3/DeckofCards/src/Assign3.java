import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Assign3
{
   public static void main(String[] args)
   {
      int MAX_CARDS = 100;
      Card testCard1 = new Card();
      System.out.println( testCard1.toString() );
      
      Card testCard2 = new Card( '5', Card.Suit.clubs );
      System.out.println( testCard2.toString() );
      
      Card testCard3 = new Card( 'J', Card.Suit.spades );
      System.out.println( testCard3.toString() );
      
      Card testCard4 = new Card( '7', Card.Suit.hearts );
      System.out.println( testCard4.toString() );
      
      Card testCard5 = new Card( '2', Card.Suit.diamonds );
      System.out.println( testCard5.toString() );
      
      Hand myHand = new Hand();
      
      
      for( int i = 0; i < MAX_CARDS; i++ ) 
      {
         int mod = 1 % 5;
         switch(mod)
         {
            case 1: myHand.takeCard(testCard1);
            case 2: myHand.takeCard(testCard2);
            case 3: myHand.takeCard(testCard3);
            case 4: myHand.takeCard(testCard4);
            case 5: myHand.takeCard(testCard5);
         }
      }
      
      System.out.println("Hand Full");
      System.out.println("After deal");
      System.out.println(myHand.toString());
      
      System.out.println("\nTesting inspectCard()");
      System.out.println(myHand.inspectCard(10).toString());
      System.out.println(myHand.inspectCard(105).toString());
      System.out.println("");
      
      for( int i = 0; i < MAX_CARDS; i++ ) 
      {
         System.out.print("Playing ");
         System.out.print(myHand.playCard().toString());
         System.out.println("");
      }
      
      System.out.println("\nAfter playing all cards");
      System.out.println(myHand.toString());
      
      Deck myDeck = new Deck(2);
      do
      {
         Card temp = myDeck.dealCard();
         //(myDeck.dealCard()).toString();
         temp.toString();
      }while(myDeck.getTopCard() != 0);

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
         for ( int i =0; i < numCards; i++ )
         {
            temp[i] = myCards[i];
         }
         temp[numCards] = card;
         numCards += 1;
         myCards = temp;
         return true;
      }
      else
      {
         return false;
      }
   }
   
   //Returns and removes the card in the top position of the array
   public Card playCard()
   {
      Card returned = myCards[ numCards - 1 ];
      numCards -= 1;
      Card[] temp = new Card [ numCards ];
      for ( int i = 0; i < numCards; i++ )
      {
         temp[i] = myCards[i];
      }
      myCards = temp;
      return returned;     
   }
   
   //Returns a string of the entire hand
   public String toString()
   {
      StringBuilder temp = new StringBuilder();
      temp.append("Hand = (");
      for ( Card value : myCards )
      {
         temp.append(value);
         temp.append(", ");
      }
      temp.append(")");
      String returned = temp.toString();
      return returned;
   }
   
   //Accessor for numCards
   public int getNumCards()
   {
      return numCards;
   }
   
   //Accessor for an individual card
   public Card inspectCard(int k)
   {
      if ( k < 0 | k > numCards )
      {
         return new Card('Y', Card.Suit.spades);
      }
      else
      {
         return myCards[k];
      }
   }
}

class Deck
{
   public final int MAX_CARDS = 6 * 52;
   private static Card[] masterPack;
   private Card[] cards;
   private int topCard;
   private int numPacks;
   
   //Allocate master pack
   private static void allocateMasterPack()
   {
      char[] cardValue = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
      masterPack = new Card [52];
      for( int i = 0; i < 13; i++ )
      {
         masterPack[ 0 + ( i * 4 ) ] = new Card( cardValue[i], Card.Suit.hearts );
         masterPack[ 1 + ( i * 4 ) ] = new Card( cardValue[i], Card.Suit.spades );
         masterPack[ 2 + ( i * 4 ) ] = new Card( cardValue[i], Card.Suit.clubs );
         masterPack[ 3 + ( i * 4 ) ] = new Card( cardValue[i], Card.Suit.diamonds );
      }
   }
   
   //Constructor no params
   public Deck()
   {
      allocateMasterPack();
      numPacks = 1;
      cards = masterPack;
      topCard = cards.length;
   }
   
   //Constructor params
   public Deck(int numPacks)
   {
      allocateMasterPack();
      this.numPacks = numPacks;
      cards = new Card[ numPacks * 52 ];
      for( int i = 0; i < numPacks; i++ )
      {
         for( int j = 0; j < 52; j++ )
         {
            cards[ (i * 52) + j ] = masterPack[j];
         }
      }
      topCard = cards.length;
   }
   
   //Re-populate cards[] with the standard 52 x numPacks cards
   public void init(int numPacks)
   {
      this.numPacks = numPacks;
      cards = new Card[ numPacks * 52 ];
      for( int i = 0; i < numPacks; i++ )
      {
         for( int j = 0; j < 52; j++ )
         {
            cards[ (i * 52) + j ] = masterPack[j];
         }
      }
      topCard = cards.length;
   }
   
   //Mixes up the cards using a standard random number
   public void shuffle()
   {
      Collections.shuffle(Arrays.asList(cards));
   }
   
   //Returns and removes the card in the top occupied position of cards[]
   public Card dealCard()
   {
      int temp = topCard - 1;
      topCard -= 1;
      return cards[temp];
   }
   
   //Accessor for the int of the topCard
   public int getTopCard()
   {
      return topCard;
   }
}