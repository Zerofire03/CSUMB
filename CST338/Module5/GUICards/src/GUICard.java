import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * 
 */

/**
 * @author cholmes
 *
 */
public class GUICard
{
   private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K + joker
   private static Icon iconBack;
   static boolean iconsLoaded = false;

   //Loads all cards from files into 2d array
   static void loadCardIcons()
   {
      if(!iconsLoaded)
      {
         for( int suit = 0; suit < 4; suit ++ )
         {
            for( int card = 0; card < 14; card++ )
            {
               iconCards[card][suit] = new ImageIcon("src/images/" + turnIntIntoCardValue( card + 1 ) + turnIntIntoCardSuit( suit ) + ".gif");               
            }
         }
         iconBack = new ImageIcon("src/images/BK.gif");
         iconsLoaded = true;
      }
   }
   
   //
   
   private static String turnIntIntoCardValue(int k)
   {
      // an idea for a helper method (do it differently if you wish)
      switch( k )
      {
         case 1:
            return "A";
         case 2:
            return "2";
         case 3:
            return "3";
         case 4:
            return "4";
         case 5:
            return "5";
         case 6:
            return "6";
         case 7:
            return "7";
         case 8:
            return "8";
         case 9:
            return "9";
         case 10:
            return "T";
         case 11:
            return "J";
         case 12:
            return "Q";
         case 13:
            return "K";
         case 14:
            return "X";
         default:
            return "";
      }
   }
   
   // turns 0 - 3 into "C", "D", "H", "S"
   private static String turnIntIntoCardSuit(int j)
   {
      // an idea for another helper method (do it differently if you wish)
      switch( j )
      {
         case 0:
            return "C";
         case 1:
            return "D";
         case 2:
            return "H";
         case 3:
            return "S";
         default:
            return "";
      }
   }

   //This method takes a Card object from the client, and returns the Icon for that card
   static public Icon getIcon(Card card)
   {
      return iconCards[Card.valueAsInt(card)][Card.suitAsInt(card)];
   }
   
   //This method returns the back the cards
   static public Icon getBackCardIcon()
   {
      return iconBack;
   }
}
