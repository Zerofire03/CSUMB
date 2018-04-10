
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameTimer extends Thread
{
   @Override
   public void run()
   {
      TheTimer theTimer = new TheTimer();
   }
   
   public void doNothing(int milliseconds)
   {
      try
      {
         Thread.sleep(milliseconds);
      }
      catch(InterruptedException e)
      {
         System.out.println("Unexpected interrupt");
         System.exit(0);
      }
   }
}

class StopWatch extends JPanel
{
   // Variable Declaration
   //private Timer myTimer1;
   private Timer myTimer1;
   public static final int PAUSE = 100;   //time step in milliseconds
   public static final int TENTH_SEC = 1000;

   private Font myClockFont;

   private JButton startBtn;
   private JLabel timeLbl;
   private JPanel topPanel, bottomPanel;

   private int clockTick; 
   private String clockTimeString;
   private int minutes, seconds;

   public StopWatch()
   {
      // Variable initialization
      clockTick = 0; 

      clockTimeString = String.format("%02d:%02d", minutes, seconds);
      myClockFont = new Font("Serif", Font.PLAIN, 50);

      timeLbl = new JLabel();
      timeLbl.setFont(myClockFont);
      timeLbl.setText(clockTimeString);

      startBtn = new JButton("Start/Stop");
      

      myTimer1 = new Timer(TENTH_SEC, new ActionListener() {
         @Override  
         public void actionPerformed(ActionEvent evt) {
            clockTick++;
            if(clockTick/60 == 1)
            {
               seconds = 0;
               minutes += 1;
               clockTick = 0;
            }
            else
            {
               seconds = clockTick;
            }
            timeLbl.setText(String.format("%02d:%02d", minutes, seconds));
          }

      });
      
      startBtn.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent evt) {
            if (!myTimer1.isRunning()) {
               myTimer1.start();
            }
            else if (myTimer1.isRunning())
            {
               myTimer1.stop();
            }
               
         }

      });
   }//end of StopWatch constructor

   public void launchStopWatch()
   {
      topPanel = new JPanel();
      topPanel.setBackground(Color.gray);
      bottomPanel = new JPanel();
      bottomPanel.setBackground(Color.blue);
      topPanel.add(timeLbl);
      bottomPanel.add(startBtn);

      this.setLayout(new BorderLayout());
      
      add(topPanel, BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }//end of launchClock
}

class TheTimer extends JFrame
{
   public TheTimer()
   {
      super("My Stop Watch");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container myPane = getContentPane();

      StopWatch StopWatch1 = new StopWatch();
      StopWatch1.launchStopWatch();
      myPane.add(StopWatch1);
      pack();
      setVisible(true);
   }
}