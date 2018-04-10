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
   private JButton[] _playButtons;
   private JLabel[] _playLabels;
   
   private int _playsAvailable;
   private int _computerPlayFail;
   private int _userPlayFail;

   // constructor
   public Model(int playsAvailable)
   {
      _computerLabels = new JLabel[0];
      _playButtons = new JButton[0];
      _humanButtons = new JButton[0];
      
      _playsAvailable = playsAvailable;
      
      _computerPlayFail = 0;
      _userPlayFail = 0;
   }
   
   // getters and setters
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
   
   public JButton[] getPlayButtons()
   {
      return _playButtons;
   }
   public boolean setPlayButtons(JButton[] playButtons)
   {
      _playButtons = playButtons;
      return true;
   }
   
   public JLabel[] getPlayLabels()
   {
      return _playLabels;
   }
   public boolean setPlayLabels(JLabel[] playLabels)
   {
      _playLabels = playLabels;
      return true;
   }
   
   public int getPlaysAvailable()
   {
      return _playsAvailable;
   }
   public boolean setPlaysAvailable(int playsAvailable)
   {
      _playsAvailable = playsAvailable;
      return true;
   }
   
   public int reducePlaysAvailable()
   {
      _playsAvailable--;
      return _playsAvailable;
   }
   
   // new tracking variables for "I cannot play" selections
   public int getUserPlayFail()
   {
      return _userPlayFail;
   }
   public Boolean addUserPlayFail()
   {
      _userPlayFail++;
      return true;
   }

   public int getComputerPlayFail()
   {
      return _computerPlayFail;
   }
   public Boolean addComputerPlayFail()
   {
      _computerPlayFail++;
      return true;
   }
}

