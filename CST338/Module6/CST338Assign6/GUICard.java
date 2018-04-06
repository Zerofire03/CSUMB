import javax.swing.Icon;
import javax.swing.ImageIcon;

// manages the reading and building of the card image Icons
public class GUICard
{
   private static final String[] CARDS = new String[]
         { "A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "X" };
   private static final String[] SUITS = new String[]
         { "C", "D", "H", "S" };
   private static final String CARD_EXT = ".gif";
   private static final String BACK_OF_CARD = "BK";

   private static final String BASE_PATH = "images/";

   private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K + joker
   private static Icon iconBack;
   static boolean iconsLoaded = false;

   static void loadCardIcons()
   {
      // build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif", etc.)
      // in a SHORT loop. For each file name, read it in and use it to
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
      if (k < 0 || k > 14)
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