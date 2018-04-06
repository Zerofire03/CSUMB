import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;

// this should be a new class defining our 2 hands and the cards
public class Model
{
   public static final int COMPUTER_HAND = 0;
   public static final int USER_HAND = 1;
   public static final int MAX_CARDS = 50;
   
   private int numPlayers = 0;
   private Hand[] hands = null;

   // constructor
   public Model()
   {
   }
   public Model(int numPlayers, Hand[] hands)
   {
      this.numPlayers = numPlayers;
      this.hands = hands;
   }
   
   public Hand getComputerHand()
   {
      return hands[COMPUTER_HAND];
   }
   public Hand getUserHand()
   {
      return hands[USER_HAND];
   }
   
   public boolean setHands(Hand[] hands)
   {
      this.hands = hands;
      return true;
   }
   public Hand[] getHands()
   {
      return hands;
   }
   
   public int getNumPlayers()
   {
      return numPlayers;
   }
   
   // reset the hands in the model
   public void resetHands()
   {
      for (int i = 0; i < numPlayers; i++)
      {
	 hands[i].resetHand();
      }
   }
}

