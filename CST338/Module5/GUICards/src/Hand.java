import java.util.Arrays;

/**
 * 
 */

/**
 * @author cholmes
 *
 */
class Hand
{
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
      Arrays.copyOf(_myCards, _numCards);

      return retCard;
   }
   
   // take the card from the index in hand
   public Card playCard(int cardIndex){
      Card retCard = _myCards[cardIndex-1];
      _myCards[cardIndex-1] = _myCards[_numCards-1];
      _numCards--;
      Arrays.copyOf(_myCards, _numCards);
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
   
   //will sort the hand by calling the arraySort() method in the Card class
   public void sort()
   {
      Card.arraySort(_myCards, _numCards);
   }

}
