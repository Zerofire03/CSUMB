/*
Michael Loeser
Chris Holmes
Chris Buckey
Patrick Gonzalez

Assignment 5 - Phase 3
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.lang.Math;

public class CST338Assign6
{
   // static for the 57 icons and their corresponding labels
   // normally we would not have a separate label for each card, but
   // if we want to display all at once using labels, we need to.

   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;

   // a simple main to throw all the JLabels out there for the world to see
   public static void main(String[] args)
   {
      int numPacksPerDeck = 1;
      int numJokersPerPack = 4;
      int numUnusedCardsPerPack = 0;
      Card[] unusedCardsPerPack = null;

      // Model
      // View - setup view
      // Controller (Model, View)
      // View.show
      Controller highCardGame = new Controller(numPacksPerDeck, numJokersPerPack, numUnusedCardsPerPack,
            unusedCardsPerPack, NUM_PLAYERS, NUM_CARDS_PER_HAND, "Card Table", 800, 600);

      highCardGame.RunGame();
   }
}









