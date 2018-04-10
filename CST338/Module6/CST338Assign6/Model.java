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
   private JButton _userCardButton;
   
   private Card _userPlayCard;
   
   private int _computerPlayFail;
   private int _userPlayFail;
   
   private boolean _computerPlayFailed;
   
   private boolean _showSelectDeck;
   private boolean _showSelectCard;
   private boolean _showDeckError;
   private boolean _showDeckCardError;

   // constructor
   public Model(int playsAvailable)
   {
      _computerLabels = new JLabel[0];
      _playButtons = new JButton[0];
      _humanButtons = new JButton[0];
      
      _computerPlayFail = 0;
      _userPlayFail = 0;
   }
   
   public Card getUserPlayCard()
   {
      return _userPlayCard;
   }
   public Boolean setUserPlayCard(Card userPlayCard)
   {
      _userPlayCard = userPlayCard;
      return true;
   }
   
   public boolean getShowSelectDeck()
   {
      return _showSelectDeck;
   }
   public boolean setShowSelectDeck(boolean showSelectDeck)
   {
      _showSelectDeck = showSelectDeck;
      return true;
   }
   
   public boolean getShowSelectCard()
   {
      return _showSelectCard;
   }
   public boolean setShowSelectCard(boolean showSelectCard)
   {
      _showSelectCard = showSelectCard;
      return true;
   }
   
   public boolean getShowDeckError()
   {
      return _showDeckError;
   }
   public boolean setShowDeckError(boolean showDeckError)
   {
      _showDeckError = showDeckError;
      return true;
   }
   
   public boolean getShowDeckCardError()
   {
      return _showDeckCardError; 
   }
   public boolean setShowDeckCardError(boolean showDeckCardError)
   {
      _showDeckCardError = showDeckCardError;
      return true;
   }
   
   public JButton getUserCardButton()
   {
      return _userCardButton;
   }
   public boolean setUserCardButton(JButton userCardButton)
   {
      _userCardButton = userCardButton;
      return true;
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
   
   public boolean getComputerPlayFailed()
   {
      return _computerPlayFailed;
   }
   public boolean setComputerPlayFailed(boolean computerPlayFailed)
   {
      _computerPlayFailed = computerPlayFailed;
      return true;
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

