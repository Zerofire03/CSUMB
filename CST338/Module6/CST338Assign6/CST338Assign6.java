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

      // set up for the BUILD game
      // need to populate the deck
      //		shuffle the deck
      // grab 2 cards at random
      // place the 2 cards as JButtons on the play area
      //	 add another new button for user selection "I cannot play"
      // computer goes first
      // 	computer places a card from it's hand following the game rules
      //			if computer plays, pull another card from the deck for them and put into hand
      //		OR computer shows label "I cannot play"
      // user gets a turn
      //		user selects a card from the hand to play
      //			if plays, pull another card from the deck and put into hand
      //		OR user selects "I cannot play"
      // count the "I cannot play" selections
      // continue playing until the deck is gone
      

      
      // Model
      // View - setup view
      // Controller (Model, View)
      // View.show
      Controller highCardGame = new Controller(numPacksPerDeck, numJokersPerPack, numUnusedCardsPerPack,
            unusedCardsPerPack, NUM_PLAYERS, NUM_CARDS_PER_HAND, "Card Table", 800, 600);

      highCardGame.RunGame();
   }
}









