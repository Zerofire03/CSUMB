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
   public DataMatrix(BarcodeImage image)
   {
      scan(image);
      text = "";
   }
   
   //Constructor that takes in a string
   public DataMatrix(String text)
   {
      image = new BarcodeImage();
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
      if( bc.clone() != null )
      {
         image = bc.clone();
         cleanImage();
         actualWidth = computeSignalWidth();
         actualHeight = computeSignalHeight();
         return true;
      }
      return false;
   }
   
   //Accessor function for actualWidth
   public int getActualWidth()
   {
      return actualWidth;
   }
   
   //Accessor function for actualHeight
   public int getActualHeight()
   {
      return actualHeight;
   }
   
   //Computers the actual width of the signal
   private int computeSignalWidth()
   {
      int signalWidth = 0;
      for( int x = 0; x < BarcodeImage.MAX_WIDTH; x++ )
      {
         if( !image.getPixel(BarcodeImage.MAX_HEIGHT-1,x) )
         {
            signalWidth = x - 1;
            break;
         }
      }
      return signalWidth;
   }
   
   //Computers the actual height of the signal
   private int computeSignalHeight()
   {
      int signalHeight = 0;
      for( int y = BarcodeImage.MAX_HEIGHT - 1; y >= 0; y-- )
      {
         if( !image.getPixel(y,0) )
         {
            signalHeight = y - 1;
            break;
         }
      }
      return signalHeight;
   }
   
   //Moves the image signal to the lower left of the 2d array
   private void cleanImage()
   {
      moveImageToLowerLeft();
      
   }
   
   //Reads binary data in column and returns the char
   private char readCharFromCol(int col)
   {
      StringBuilder binaryDataStringBuilder = new StringBuilder();
      
      for( int i = 0; i < BarcodeImage.MAX_HEIGHT; i++ )
      {
         if( image.getPixel(i, col) )
         {
            binaryDataStringBuilder.append('1');
         }
         else
         {
            binaryDataStringBuilder.append('0');
         }         
      }
      return (char)Integer.parseInt(binaryDataStringBuilder.toString(),2);
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
      StringBuilder imageTextStringBuilder = new StringBuilder();
      for( int i = 0; i < BarcodeImage.MAX_WIDTH; i++ )
      {
         imageTextStringBuilder.append(readCharFromCol(i));
      }
      return true;
   }
   
   //Moves image to lower left of 2d array
   private void moveImageToLowerLeft()
   {
      int lowerLeftX = 0;
      int lowerLeftY = 0;
      
      //Find the lower left corner of the image
      for( int x = 0; x < BarcodeImage.MAX_WIDTH; x++ )
      {
         for( int y = BarcodeImage.MAX_HEIGHT - 1; y >= 0; y-- )
         {
            if( image.getPixel(y, x) )
            {
               lowerLeftX = x;
               lowerLeftY = y;
               break;
            }
         }         
      }
      
      //Call shift functions to move image to lower left by the offset found
      shiftImageDown(lowerLeftY);
      shiftImageLeft(lowerLeftX);
      
   }
   
   //Moves image down by offset
   private void shiftImageDown(int offset)
   {
      for( int loop = 0; loop < offset; loop++ )
      {
         for( int i = BarcodeImage.MAX_HEIGHT-2; i > 0; i-- )
         {
            for( int j = 0; j < BarcodeImage.MAX_WIDTH-1; j++ )
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
         for( int i = 0; i < BarcodeImage.MAX_HEIGHT-1; i++ )
         {
            for( int j = 0; j < BarcodeImage.MAX_WIDTH-1; j++ )
            {
               image.setPixel(i, j, image.getPixel(i, j+1));
            }
            
         }
      }
   }
   
   //Displays the raw image in the DataMatrix
   public void displayRawImage()
   {
      image.displayToConsole();
   }
}
