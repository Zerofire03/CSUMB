import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author cholmes
 *
 */
public class CardTable extends JFrame
{
   static int MAX_CARDS_PER_HAND = 56;
   static int MAX_PLAYERS = 2;  // for now, we only allow 2 person games
   
   private int numCardsPerHand;
   private int numPlayers;
   
   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;
   
   //Constructor for CardTable
   CardTable(String title, int numCardsPerHand, int numPlayers)
   {
      //Check to see if the number of players is greater than the max number allowed
      if( numPlayers > MAX_PLAYERS )
      {
         this.numPlayers = MAX_PLAYERS;
      }
      else
      {
         this.numPlayers = numPlayers;
      }
      
      //Check to see if the number of cards per hand is greater than the max cards per hand
      if( numCardsPerHand > MAX_CARDS_PER_HAND )
      {
         this.numCardsPerHand = numCardsPerHand;
      }
      else
      {
         this.numCardsPerHand = numCardsPerHand;
      }
      
      //frmMyWindow = new JFrame(title);
      setTitle(title);
      //setLayout(new GridLayout(3,1));
      setLayout( new BorderLayout() );
      pnlComputerHand = new JPanel();
      pnlComputerHand.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Computer Hand"));
      pnlComputerHand.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
      add(pnlComputerHand, "North");
      pnlHumanHand = new JPanel();
      pnlHumanHand.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Your Hand"));
      pnlHumanHand.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
      add(pnlHumanHand, "South");
      pnlPlayArea = new JPanel();
      pnlPlayArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Playing Area"));
      pnlPlayArea.setLayout(new GridLayout(2,2));
      add(pnlPlayArea, "Center");
   }
   
   //Accessor for numCardsPerHand
   public int getNumCardsPerHand()
   {
      return numCardsPerHand;
   }
   
   //Accessor for numPLayers
   public int getNumPlayers()
   {
      return numPlayers;
   }
}
