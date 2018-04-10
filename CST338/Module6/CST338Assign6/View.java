import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// This class will control the positioning of the panels and cards of the GUI
class View extends JFrame implements ActionListener
{
   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;

   private int _height;
   private int _width;

   // constructor
   public View(String title, int width, int height)
   {
      // base constructor call
      super(title);
      
      // track the display values
      _width = width;
      _height = height;
      
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
   
   // build the display from the labels and buttons
   public void SetDisplay(Model model)
   {
      // ADD LABELS TO PANELS -----------------------------------------
      for (JLabel label : model.getComputerLabels())
      {
	 pnlComputerHand.add(label);
      }
      for (JButton button : model.getHumanButtons())
      {
	 pnlHumanHand.add(button);
      }
      
      // BUILD game - add the 2 cards for the play buttons and the 'i cannot play' button
      ShowPlayArea(model);

      // show everything to the user
      setVisible(true);
   }
   
   // rebuild the middle play area
   public void ShowPlayArea(Model model)
   {
      pnlPlayArea.removeAll();
      
      JButton[] playButtons = model.getPlayButtons();
      
      // loop through the play labels and add them to the panel
      for (int i = 0; i < playButtons.length; i++)
      {
	 pnlPlayArea.add(playButtons[i]);
      }
      
      // check for a play fail record
      if (model.getComputerPlayFailed())
      {
	 pnlPlayArea.add(new JLabel(""));
	 pnlPlayArea.add(new JLabel("Computer Cannot Play"));
	 pnlPlayArea.add(new JLabel(""));
      }
      else
      {
	 pnlPlayArea.add(new JLabel(""));
	 pnlPlayArea.add(new JLabel(""));
	 pnlPlayArea.add(new JLabel(""));
      }
      
      // should update to remove the computerplayfailed

      setVisible(true);
   }
   
   @Override
   public void actionPerformed(ActionEvent arg0)
   {
      System.exit(0);
   }
}
