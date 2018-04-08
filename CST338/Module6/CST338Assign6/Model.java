import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// this should be a new class defining our 2 hands and the cards
public class Model
{
   public static final int MAX_CARDS = 50;
   
   private JLabel[] _computerLabels;
   private JButton[] _humanButtons;

   // constructor
   public Model()
   {
   }
   
   public JButton[] getHumanButtons()
   {
      return _humanButtons;
   }
   public boolean setHumanButtons(JButton[] humanButtons)
   {
      _humanButtons = humanButtons;
      return true;
   }
   
   public JLabel[] getComputerLabels()
   {
      return _computerLabels;
   }
   public boolean setComputerLabels(JLabel[] computerLabels)
   {
      _computerLabels = computerLabels;
      return true;
   }
}

