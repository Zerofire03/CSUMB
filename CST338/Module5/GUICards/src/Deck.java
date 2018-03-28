import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 */

/**
 * @author cholmes
 *
 */
class Deck
{
   public final int MAX_CARDS = 6 * 56;
   private static Card[] masterPack;
   private Card[] cards;
   private int topCard;
   private int numPacks;

   //Allocate master pack
   private static void allocateMasterPack()
   {
      char[] cardValue = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'X'};
      masterPack = new Card [56];
      for( int i = 0; i < 14; i++ ){
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
      cards = new Card[ numPacks * 56 ];
      for( int i = 0; i < numPacks; i++ ){
         for( int j = 0; j < 56; j++ ){
            cards[ (i * 56) + j ] = masterPack[j];
         }
      }
      topCard = cards.length;
   }

   //Re-populate cards[] with the standard 56 x numPacks cards
   public void init(int numPacks){
      this.numPacks = numPacks;
      cards = new Card[ numPacks * 56 ];
      for( int i = 0; i < numPacks; i++ ){
         for( int j = 0; j < 56; j++ ){
            cards[ (i * 56) + j ] = masterPack[j];
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
   
   //Adds a card to the deck. If there are too many instances of the card already, returns false
   public boolean addCard( Card card )
   {
      List<Card> cardsList = Arrays.asList(cards);
      int numInstancesOfCard = Collections.frequency(cardsList, card);
      if( numInstancesOfCard > numPacks )
      {
         return false;
      }
      else
      {
         Arrays.copyOf(cards, cards.length + 1);
         topCard = cards.length;
         cards[topCard - 1] = card;
         return true;
      }
   }
   
   //Removes a specific card from the deck. Puts the top card in it's place. Returns false if card not availible
   public boolean removeCard( Card card )
   {
      if( Arrays.asList(cards).contains(card) )
      {
         int cardPos = Arrays.asList(cards).indexOf(card);
         cards[cardPos] = cards[topCard - 1];
         Arrays.copyOf(cards, cards.length-1);
         topCard = cards.length;
         return true;
      }
      else
      {
         return false;
      }
   }
   
   //Puts all of the cards in the deck into their order according to their values
   public void sort()
   {
      
   }
   
   //Return the number of cards remaining in the deck
   public int getNumCards()
   {
      return cards.length - topCard;
   }

}
