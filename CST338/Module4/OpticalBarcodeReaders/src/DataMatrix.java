import java.util.Scanner;

/**
 * 
 */

/**
 * @author cholmes
 *
 */
public class DataMatrix implements BarcodeIO
{
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';  
   private BarcodeImage image;
   private String text;
   private int actualWidth;
   private int actualHeight;
   
   //Default constructor
   public DataMatrix()
   {
      image = new BarcodeImage();
      actualWidth = 0;
      actualHeight = 0;
      text = "";
   }
   
   //Constructor that takes in a barcode image
   
   //Constructor that takes in a string
   public DataMatrix(String text)
   {
      image = new BarcodeImage();
      actualWidth = 0;
      actualHeight = 0;
      readText(text);
   }
   
   //accepts a text string to be eventually encoded in an image
   public boolean readText( String text )
   {
      this.text = text;
      return true;
   }
   
   //accepts some image, represented as a BarcodeImage object to be described below, and stores a copy of this image.
   public boolean scan( BarcodeImage bc )
   {
      image = bc.clone();
      return true;
   }
   
   //Reads binary data in column and returns the char
   private char readCharFromCol(int col)
   {
      StringBuilder binaryNums = new StringBuilder();
      for( int i = 1; i < 8; i++ )
      {
         binaryNums.append(image.getPixel(i, col));
      }
      String binary = binaryNums.toString();
      int parseInt = Integer.parseInt(binary, 2);
      return (char)parseInt;
   }
   
   //looks at the internal text stored in the implementing class and produces a companion BarcodeImage
   public boolean generateImageFromText()
   {
      char[] stringCharArray = text.toCharArray();
      for( char ch : stringCharArray )
      {
         System.out.println(Integer.toBinaryString(ch));
      }
      return true;
   }
   
   //looks at the image and converts it to text
   public boolean translateImageToText()
   {
      StringBuilder word = new StringBuilder();
      for( int i = 1; i < 34; i++ )
      {
         word.append(readCharFromCol(i));
      }
      String finalWord = word.toString();
      System.out.println(finalWord);
      return true;
   }
   
   //Moves image to lower left of 2d array
   private void moveImageToLowerLeft()
   {
      int closeLimitationLinePos;
      int horizontileLimitationLinePos;
      for( int i = 0; i < image.MAX_HEIGHT; i++ )
      {
         StringBuilder rowData = new StringBuilder();
         StringBuilder colData = new StringBuilder();
         for( int j = 0; j < image.MAX_WIDTH; j++ )
         {
            if(image.getPixel(i, j) == true)
            {
               rowData.append("*");
            }
            else
            {
               rowData.append(" ");
            }
         }
         if(checkClosedLimiationLine(rowData.toString()))
         {
            closeLimitationLinePos = i;
         }
         else if(checkHorizontileOpenBorderLine(rowData.toString()))
         {
            horizontileLimitationLinePos = i;
         }
      }
      
   }
   
   //Function to see if string is closed limitation line
   private boolean checkClosedLimiationLine(String row)
   {
      char firstChar = row.charAt(0);
      for( int i=1; i < row.length(); i++ )
      {
         char temp = row.charAt(i);
         if( firstChar != temp )
         {
            return false;
         }
      }
      return true;
   }
   
   //Function to see if string is horizontile open border line
   private boolean checkHorizontileOpenBorderLine(String row)
   {
      for( int i=0; i < row.length(); i++ )
      {
         if( i%2 == 0 )
         {
            if( row.charAt(i) != ' ' )
            {
               return false;
            }
         }
         else
         {
            if( row.charAt(i) != '*' )
            {
               return false;
            }
         }
      }
      return true;
   }
   
   //Moves image down by offset
   private void shiftImageDown(int offset)
   {
      for( int loop = 0; loop < offset; loop++ )
      {
         for( int i = image.MAX_HEIGHT-1; i > 0; i-- )
         {
            for( int j = 0; j < image.MAX_WIDTH-1; j++ )
            {
               image.setPixel(i+1, j, image.getPixel(i, j));
            }
            
         }
      }
      
   }
   
   //Moves image to left by offset
   private void shiftImageLeft(int offset)
   {
      for( int loop = 0; loop < offset; loop++ )
      {
         for( int i = 0; i < image.MAX_HEIGHT-1; i++ )
         {
            for( int j = 0; j < image.MAX_WIDTH-1; j++ )
            {
               image.setPixel(i, j, image.getPixel(i, j+1));
            }
            
         }
      }
   }
}
