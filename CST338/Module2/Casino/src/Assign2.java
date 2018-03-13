import java.util.*;
import java.lang.Math;

public class Assign2
{
   //Create global scanner to read in from command line
   static Scanner sc = new Scanner(System.in);
   public static void main( String[] args )
   {
      //Create triplestring variable and a variable to hold the bet
      TripleString result = new TripleString();
      int bet;
      do
      {
         //Obtain bet from user and check if it's equal to 0
         bet = getBet();
         if ( bet != 0 )
         {
            //Run the slot machine, calculate the result, and store winnings
            result = pull();
            int multiplier = getPayMultiplier( result );
            int winnings = multiplier * bet;
            display ( result, winnings );
            //If amount of runs is exceded, displays winnings from all rounds, closes scanner and program
            if ( !(result.saveWinnings( winnings ) ))
            {
               System.out.println( "Max number of pulls exceeded. Game over." );
               System.out.print(result.displayWinnings());
               sc.close();
               System.exit(0);
            }
         }
      }while( bet != 0 ); //Loop to continue running as long as 0 isn't entered
      System.out.print(result.displayWinnings());
      //Close out the scanner
      sc.close();
      
   }
   
   //Prompts the user for a bet and returns a valid bet
   static int getBet()
   {
      int bet;
      //Prompts the user to enter a bet between 1-100. Continues to loop until valid bet entered
      do
      {
         System.out.println("How much would you like to bet (1 - 100); or 0 to quit?");
         bet = sc.nextInt();
      }while( bet < 0 | bet > 100);
      return bet;
   }
   
   //Returns a random string bassed on the predetermined probabilities
   static String randString()
   {
      //Generate a random number between 1-1000. Convert to int, return based on probablitly
      Double randomNumD = Math.random() * 1000;
      int randomNum = randomNumD.intValue();
      if ( randomNum <= 500 )
      {
         return "BAR";
      }
      else if ( 500 < randomNum && randomNum <= 750 )
      {
         return "cherries";
      }
      else if ( 750 < randomNum && randomNum <= 875 )
      {
         return "(space)";
      }
      else
      {
         return "7";
      }
   }
   
   //Returns a triple string object to simulate casino slot machine
   static TripleString pull()
   {
      //Create object and then store 3 random strings in it
      TripleString tempString = new TripleString();
      tempString.setString1( randString() );
      tempString.setString2( randString() );
      tempString.setString3( randString() );
      return tempString;
   }
   
   //Takes in a TripleString object and determines the payout based on the input
   static int getPayMultiplier ( TripleString thePull )
   {
      if ( thePull.getString1() == "cherries" )
      {
         if ( thePull.getString2() == "cherries" )
         {
            if ( thePull.getString3() == "cherries" )
            {
               return 30;
            }
            else return 15;
         }
         else return 5;   
      }
      else if ( thePull.getString1() == "BAR" && thePull.getString2() == "BAR"
            && thePull.getString3() == "BAR" )
      {
         return 50;
      }
      else if ( thePull.getString1() == "7" && thePull.getString2() == "7" && thePull.getString3() == "7" )
      {
         return 100;
      }
      else return 0;
   }
   
   //Displays the pull and winnings 
   static void display ( TripleString thePull, int winnings )
   {
      //checks to see if lost and display accordingly
      if ( winnings == 0 )
      {
         System.out.println( "whirrrrrr .... and your pull is ... ");
         System.out.println( thePull.toString() );
         System.out.println( "Sorry, you lose." );
         System.out.println("");
      }
      //Win and displays accordingly
      else
      {
         System.out.println( "whirrrrrr .... and your pull is ... ");
         System.out.println( thePull.toString() );
         System.out.printf( "Congratulations, you win: %d\n", winnings);
         System.out.println("");
      }
   }
   
}

class TripleString
{
   String string1;
   String string2;
   String string3;
   public static final int MAX_LEN = 20;
   public static final int MAX_PULLS = 40;
   static int pullWinnings[] = new int[MAX_PULLS];
   static int numPulls = 0;
   
   //Constructor for TripleString class
   public TripleString()
   {
      string1 = "";
      string2 = "";
      string3 = "";
   }
   
   //Returns true if the string is valid, returns false if not valid
   boolean validString( String str )
   {
      if ( str.length() <= MAX_LEN && str != null && !str.isEmpty() )
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   //Setter function for string1
   public boolean setString1 ( String str )
   {
      if ( validString ( str ))
      {
         string1 = str;
         return true;
      }
      else
      {
         return false;
      }
   }
   
   //Setter function for string2
   public boolean setString2 ( String str )
   {
      if ( validString ( str ))
      {
         string2 = str;
         return true;
      }
      else
      {
         return false;
      }
   }
   
   //Setter function for string3
   public boolean setString3 ( String str )
   {
      if ( validString ( str ))
      {
         string3 = str;
         return true;
      }
      else
      {
         return false;
      }
   }
   
   //Getter function for string1
   public String getString1()
   {
      return string1;
   }
   
   //Getter function for string2
   public String getString2()
   {
      return string2;
   }
   
   //Getter function for string3
   public String getString3()
   {
      return string3;
   }
   
   //Converts a TripleString object to string
   public String toString()
   {
      return getString1() + " " + getString2() + " " + getString3();
   }
   
   //Saves the winnings from the pull to the array
   public boolean saveWinnings( int winnings )
   {
      if ( numPulls < MAX_PULLS )
      {
         pullWinnings[numPulls] = winnings;
         numPulls++;
         return true;
      }
      else
      {
         return false;
      }
   }
   
   //Displays all the winnings in the array
   public String displayWinnings()
   {
      //String builder to store all parts of the string needed to be returned.
      StringBuilder builder = new StringBuilder();
      builder.append( "Thanks for playing at the Casino!\n" );
      builder.append( "Your individual winnings were:\n" );
      
      //Temp array to store winnings
      int temp[] = new int[numPulls];
      for ( int i = 0; i < numPulls; i++ )
      {
         temp[i] = pullWinnings[i];
      }
      
      //Loop to add all winnings to a single line
      for ( int value : temp )
      {
         builder.append(value + " ");
      }
      
      //Loops to get the sum of all winnings
      int totalWinnings = 0;
      for ( int i = 0; i < numPulls; i++ )
      {
         totalWinnings += pullWinnings[i];
      }
      //Convert to string and return string object
      builder.append("\n" + "Congratulations, you win: $" + totalWinnings);
      String winningsString = builder.toString();
      return winningsString;
   }
}

/* -------------------- Sample Run ---------------------------* 
How much would you like to bet (1 - 100); or 0 to quit?
-1
How much would you like to bet (1 - 100); or 0 to quit?
1000
How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
BAR BAR (space)
Sorry, you lose.

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
cherries BAR 7
Congratulations, you win: 250

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
(space) BAR BAR
Sorry, you lose.

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
BAR BAR BAR
Congratulations, you win: 2500

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
BAR cherries (space)
Sorry, you lose.

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
cherries BAR BAR
Congratulations, you win: 250

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
BAR cherries BAR
Sorry, you lose.

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
cherries BAR BAR
Congratulations, you win: 250

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
cherries (space) BAR
Congratulations, you win: 250

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
BAR cherries BAR
Sorry, you lose.

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
BAR 7 BAR
Sorry, you lose.

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
cherries BAR cherries
Congratulations, you win: 250

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
cherries 7 cherries
Congratulations, you win: 250

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
BAR BAR BAR
Congratulations, you win: 2500

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
BAR 7 cherries
Sorry, you lose.

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
BAR BAR (space)
Sorry, you lose.

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
BAR BAR BAR
Congratulations, you win: 2500

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
BAR 7 7
Sorry, you lose.

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
7 7 BAR
Sorry, you lose.

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
cherries BAR cherries
Congratulations, you win: 250

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
(space) cherries (space)
Sorry, you lose.

How much would you like to bet (1 - 100); or 0 to quit?
50
whirrrrrr .... and your pull is ... 
cherries BAR BAR
Congratulations, you win: 250

How much would you like to bet (1 - 100); or 0 to quit?
0
Thanks for playing at the Casino!
Your individual winnings were:
0 250 0 2500 0 250 0 250 250 0 0 250 250 2500 0 0 2500 0 0 250 0 250 
Congratulations, you win: $9500
-------------------------------------------------------- */