import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 */

/**
 * @author chris
 *
 */
public class phase3 implements ActionListener
{
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   static int numPacksPerDeck = 1;
   static int numJokersPerPack = 0;
   static int numUnusedCardsPerPack = 0;
   static Card[] unusedCardsPerPack = null;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];  
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 
   static JButton[] humanButtons = new JButton[NUM_CARDS_PER_HAND];
   
   // store the winning cards
   static JLabel[] winnings = new JLabel[NUM_CARDS_PER_HAND];
   
   static CardGameFramework highCardGame = null;
   static CardTable myCardTable = null;
   
   public void actionPerformed(ActionEvent e)
   {
      /**
      for( int i = 0; i < NUM_PLAYERS; i++ )
      {
         playedCardLabels[i] = new JLabel(GUICard.getIcon(generateRandomCard()));
         
         if( i == 0 )
         {
            playLabelText[i] = new JLabel("Computer", JLabel.CENTER );
         }
         else
         {
            playLabelText[i] = new JLabel("You", JLabel.CENTER );
         }
      }
      myCardTable.pnlPlayArea.add(playedCardLabels[0]);
      myCardTable.pnlPlayArea.add(playedCardLabels[1]);
      myCardTable.pnlPlayArea.add(playLabelText[0]);
      myCardTable.pnlPlayArea.add(playLabelText[1]);
      **/
   }
   
   public static void main(String[] args)
   {
      CardGameFramework highCardGame = new CardGameFramework( 
         numPacksPerDeck, numJokersPerPack,  
         numUnusedCardsPerPack, unusedCardsPerPack, 
         NUM_PLAYERS, NUM_CARDS_PER_HAND);
     
      if(!highCardGame.deal())
      {
         System.out.println("Not enough cards");
      }
      
      
   // establish main frame in which program will run
      CardTable myCardTable 
         = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
      myCardTable.setSize(800, 600);
      myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // show everything to the user
      myCardTable.setVisible(true);
      
   // CREATE LABELS ----------------------------------------------------
      //Generate humanLabels array and computerLabels array
      for( int i = 0; i < NUM_CARDS_PER_HAND; i++ )
      {
         //humanLabels[i] = new JLabel(GUICard.getIcon(highCardGame.getHand(0).inspectCard(i)));
         humanButtons[i] = new JButton(GUICard.getIcon(highCardGame.getHand(1).inspectCard(i)));
         humanButtons[i].setActionCommand(String.valueOf(i));
         computerLabels[i] = new JLabel(GUICard.getBackCardIcon());
      }

      
   // ADD LABELS TO PANELS -----------------------------------------
      //code goes here ...
      for( int i = 0; i < NUM_CARDS_PER_HAND; i++ )
      {
         myCardTable.pnlHumanHand.add(humanButtons[i]);
         myCardTable.pnlComputerHand.add(computerLabels[i]);
      }
   
   // show everything to the user
      myCardTable.setVisible(true);
      
   }
}
