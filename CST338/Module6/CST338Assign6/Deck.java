import java.util.Arrays;
import java.util.Collections;

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

