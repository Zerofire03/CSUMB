import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;

// This class will control the positioning of the panels and cards of the GUI
class View extends JFrame implements ActionListener
{
   static int MAX_CARDS_PER_HAND = 56;
   static int MAX_PLAYERS = 2; // for now, we only allow 2 person games

   private int numCardsPerHand;
   private int numPlayers;

   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;

   // store the winning cards
   static Icon[] winnings;
   static int numWins = 0;
   
   private int _height;
   private int _width;

   // accessors for private vars
   public int NumCardsPerHand()
   {
      return numCardsPerHand;
   }

   public int NumPlayers()
   {
      return numPlayers;
   }

   // constructor
   public View(String title, int numCardsPerHand, int numPlayers, int width, int height)
   {
      // base constructor call
      super(title);
      
      setSize(width, height);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // check for max cards
      if (numCardsPerHand > MAX_CARDS_PER_HAND)
      {
         System.out.println(
               "numCardsPerHand: " + numCardsPerHand + " was above max: " + MAX_CARDS_PER_HAND + ".  Using the max.");
         this.numCardsPerHand = MAX_CARDS_PER_HAND;
      } 
      else
      {
         this.numCardsPerHand = numCardsPerHand;
      }

      // check for max players
      if (numPlayers > MAX_PLAYERS)
      {
         System.out.println("numPlayers: " + numPlayers + " was above max: " + MAX_PLAYERS + ".  Using the max.");
         this.numPlayers = MAX_PLAYERS;
      } 
      else
      {
         this.numPlayers = numPlayers;
      }

      setLayout(new BorderLayout());
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
      pnlPlayArea.setLayout(new GridLayout(2, 3));
      add(pnlPlayArea, "Center");
   }
   
   @Override
   public void actionPerformed(ActionEvent arg0)
   {
      System.exit(0);
   }
}
