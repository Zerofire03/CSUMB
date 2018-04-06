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
   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;

   // store the winning cards
   static Icon[] winnings;
   static int numWins = 0;
   
   private int _height;
   private int _width;

   // constructor
   public View(String title, int width, int height)
   {
      // base constructor call
      super(title);
      
      setSize(width, height);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
