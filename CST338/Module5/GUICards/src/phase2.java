import javax.swing.*;



public class phase2
{
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];  
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 
   
   static Card generateRandomCard()
   {
      Deck deck = new Deck();
      deck.shuffle();
      return deck.dealCard();
   }
   
   
   public static void main(String[] args)
   {
      //int k;
      //Icon tempIcon;
      
      
      // establish main frame in which program will run
      CardTable myCardTable 
         = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
      myCardTable.setSize(800, 600);
      myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // show everything to the user
      myCardTable.setVisible(true);

      // CREATE LABELS ----------------------------------------------------
      //Generate humanLabels array and computerLabels array
      for( int i = 0; i < NUM_CARDS_PER_HAND; i++ )
      {
         humanLabels[i] = new JLabel(GUICard.getIcon(generateRandomCard()));
         computerLabels[i] = new JLabel(GUICard.getBackCardIcon());
      }
      
      //playedCardLabels[0] = new JLabel(GUICard.getIcon(new Card('A', Card.Suit.spades)));
      
  
      // ADD LABELS TO PANELS -----------------------------------------
      //code goes here ...
      for( int i = 0; i < NUM_CARDS_PER_HAND; i++ )
      {
         myCardTable.pnlHumanHand.add(humanLabels[i]);
         myCardTable.pnlComputerHand.add(computerLabels[i]);
      }
      
      // and two random cards in the play region (simulating a computer/hum ply)
      //code goes here ...
      for( int i = 0; i < NUM_PLAYERS; i++ )
      {
         playedCardLabels[i] = new JLabel(GUICard.getIcon(generateRandomCard()));
         
         if( i == 0 )
         {
            playLabelText[i] = new JLabel("Computer", JLabel.CENTER );
         }
         else
         {
            playLabelText[i] = new JLabel("You", JLabel.CENTER );
         }
      }
      myCardTable.pnlPlayArea.add(playedCardLabels[0]);
      myCardTable.pnlPlayArea.add(playedCardLabels[1]);
      myCardTable.pnlPlayArea.add(playLabelText[0]);
      myCardTable.pnlPlayArea.add(playLabelText[1]);
      
      
      
      // show everything to the user
      myCardTable.setVisible(true);
   }
}