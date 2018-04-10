// Defines the hand entity used by the model
// Hand contains Decks and Cards
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
