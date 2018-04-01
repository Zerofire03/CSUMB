import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author chris
 *
 */
public class phase3 
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
   static JLabel[] computerLabelsBack = new JLabel[NUM_CARDS_PER_HAND];
   
   // store the winning cards
   static JLabel[] winnings = new JLabel[NUM_CARDS_PER_HAND];
   
   static CardGameFramework highCardGame = null;
   static CardTable myCardTable = null;
   
   
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
         int index = i;
         computerLabelsBack[i] = new JLabel(GUICard.getBackCardIcon());
         
         JLabel humanLabel = new JLabel(GUICard.getIcon(highCardGame.getHand(0).inspectCard(i)));
         humanLabels[i] = humanLabel;
         
         JLabel computerLabel = new JLabel(GUICard.getIcon(highCardGame.getHand(1).inspectCard(i)));
         computerLabels[i] = computerLabel;
         
         JButton humanButton = new JButton(GUICard.getIcon(highCardGame.getHand(0).inspectCard(i)));
         humanButton.addActionListener( new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               playLabelText[0] = new JLabel("Computer", JLabel.CENTER );
               playLabelText[1] = new JLabel("You", JLabel.CENTER );
                     
               //myCardTable.pnlPlayArea.add(playedCardLabels[0]);
               //Add the card the player selected to the played panel
               
               //Remove all items from the pnlPlayArea
               myCardTable.pnlPlayArea.removeAll();
               
               //Add the cards played to the pnlPlayArea
               myCardTable.pnlPlayArea.add(computerLabel);
               myCardTable.pnlPlayArea.add(humanLabel);
               
               
               //Add text to pnlPlayArea
               myCardTable.pnlPlayArea.add(playLabelText[0]);
               myCardTable.pnlPlayArea.add(playLabelText[1]);
               
               //Set player card clicked visible to false to remove from panel
               humanButton.setVisible(false);
               
               //Set computer card visible to false to remove from panel
               computerLabelsBack[index].setVisible(false);
               
             //Remove the cards from the hand
               if(Card.checkValueRanks(highCardGame.getHand(0).playCard(index + 1),
                     highCardGame.getHand(1).playCard(index + 1)))
               {
                  JOptionPane.showMessageDialog(myCardTable,"You Win!");
               }
               else
               {
                  JOptionPane.showMessageDialog(myCardTable,"Computer Wins.");
               }
               
               
               myCardTable.setVisible(true);
            }
         });
         
         
         humanButtons[i] = humanButton;
         
         
         
      }

      
   // ADD LABELS TO PANELS -----------------------------------------
      //code goes here ...
      for( int i = 0; i < NUM_CARDS_PER_HAND; i++ )
      {
         myCardTable.pnlHumanHand.add(humanButtons[i]);
         myCardTable.pnlComputerHand.add(computerLabelsBack[i]);
      }
   
   // show everything to the user
      myCardTable.setVisible(true);
      
   }
}
