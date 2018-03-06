import java.util.*;
import java.lang.Math;

public class Assign2
{
   
}

class TripleString
{
   String string1;
   String string2;
   String string3;
   public static final int MAX_LEN = 20;
   public static final int MAX_PULLS = 40;
   static int pullWinnings[] = new int[MAX_PULLS];
   static int numPulls;
   
   public TripleString()
   {
      string1 = "";
      string2 = "";
      string3 = "";
   }
   
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
   
   public String toString()
   {
      return string1 + " " + string2 + " " + string3;
   }
}