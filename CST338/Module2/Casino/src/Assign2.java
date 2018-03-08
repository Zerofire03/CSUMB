import java.util.*;
import java.lang.Math;

public class Assign2
{
   static Scanner sc = new Scanner(System.in);
   public static void main( String[] args )
   {
      TripleString result = new TripleString();
      int bet;
      do
      {
      bet = getBet();
      if ( bet != 0 )
      {
         result = pull();
         int multiplier = getPayMultiplier( result );
         int winnings = multiplier * bet;
         display ( result, winnings );
         if ( !(result.saveWinnings( winnings ) ))
         {
            System.out.println( "Max number of pulls exceeded. Game over." );
            System.out.println( result.displayWinnings() );
            System.out.printf( "Your total winnings were: $%d", result.getTotalWinnings() );
            System.exit(0);
         }
      }
      }while( bet != 0 );
      System.out.println( "Thanks for playing at the Casino!" );
      System.out.println( "Your individual winnings were:" );
      System.out.println( result.displayWinnings() );
      System.out.printf( "Your total winnings were: $%d", result.getTotalWinnings() );
      sc.close();
      
   }
   
   static int getBet()
   {
      int bet;
      do
      {
         System.out.println("How much would you like to bet (1 - 100); or 0 to quit?");
         bet = sc.nextInt();
      }while( bet < 0 | bet > 100);
      return bet;
   }
   
   static String randString()
   {
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
   
   static TripleString pull()
   {
      TripleString tempString;
      tempString = new TripleString();
      tempString.setString1( randString() );
      tempString.setString2( randString() );
      tempString.setString3( randString() );
      return tempString;
   }
   
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
   
   static void display ( TripleString thePull, int winnings )
   {
      if ( winnings == 0 )
      {
         System.out.println( "whirrrrrr .... and your pull is ... ");
         System.out.println( thePull.toString() );
         System.out.println( "Sorry, you lose." );
      }
      else
      {
         System.out.println( "whirrrrrr .... and your pull is ... ");
         System.out.println( thePull.toString() );
         System.out.printf( "Congratulations, you win: %d\n", winnings);
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
      StringBuilder builder = new StringBuilder();
      
      int temp[] = new int[numPulls];
      for ( int i = 0; i < numPulls; i++ )
      {
         temp[i] = pullWinnings[i];
      }
      
      for ( int value : temp )
      {
         builder.append(value + " ");
      }
      
      String winningsString = builder.toString();
      return winningsString;
   }
   
   //Get total winnings
   public int getTotalWinnings()
   {
      int totalWinnings = 0;
      for ( int i = 0; i < numPulls; i++ )
      {
         totalWinnings += pullWinnings[i];
      }
      return totalWinnings;
   }
}