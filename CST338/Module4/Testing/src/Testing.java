/**
 * 
 */

/**
 * @author cholmes
 *
 */
public class Testing
{

   /**
    * @param args
    */
   public static void main(String[] args)
   {
      //char[][] binaryStorage = new char[8][];
      char test = 'T';
      String testString = "Congrats"; //text in datamatrix
      char[] testStringChar = testString.toCharArray(); //line 187 
      char[][] binaryStorage = new char[8][testStringChar.length];
      String[] binaryStringStorage = new String[testStringChar.length]; 
      
      //Convert each character in the string to binary data storing the resulting string in a string array
      for( int i = 0; i < testStringChar.length; i++ )
      {
         System.out.println(Integer.toBinaryString(testStringChar[i]));
         binaryStringStorage[i] = Integer.toBinaryString(testStringChar[i]);
      }
      
      //Split individual binary string into chars for storage into array
      for( int i = 0; i < binaryStringStorage.length; i++ )
      {
         char[] strCharArray = binaryStringStorage[i].toCharArray();
         if ( strCharArray.length < 8 )
         {
            int charArrayLength = strCharArray.length;
            int difference = 8 - charArrayLength;
            char[] temp = new char[8];
            for ( int k = 0; k < 8; k++ )
            {
               if( k < difference )
               {
                  temp[k] = '0';
               }
               else
               {
                  temp[k] = strCharArray[k - difference];
               }
            }
            strCharArray = new char[8];
            strCharArray = temp;
         }
         
         for( int j = 0; j < strCharArray.length; j++ )
         {
            binaryStorage[j][i] = strCharArray[j];
         }
      }
      
      //Reassemble column to binary string
      StringBuilder reassembleStringBuilder = new StringBuilder();
      
      
      for( int i = 0; i < testStringChar.length; i++ )
      {
         StringBuilder binaryDataStringBuilder = new StringBuilder();
         for( int j = 0; j < 8; j++ )
         {
            binaryDataStringBuilder.append(binaryStorage[j][i]);
         }
         String binaryString = binaryDataStringBuilder.toString();
         char temp = (char)Integer.parseInt(binaryString,2);
         reassembleStringBuilder.append(temp);
         
      }
      System.out.printf("Binary string turned back into a char string: %s\n", reassembleStringBuilder.toString());
      
      
   }

}
