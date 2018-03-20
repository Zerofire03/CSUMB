/**
 * 
 */

/**
 * @author cholmes
 *
 */
public class BarcodeImage implements Cloneable
{
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65;
   private boolean[][] image_data;
   
   //Default constructor no params
   public BarcodeImage()
   {
      image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
      for ( int i=0; i < MAX_HEIGHT; i++ )
      {
         for ( int j=0; j < MAX_WIDTH; j++ )
         {
            image_data[i][j] = false;
         }
      }
   }
   
   //Constructor with string array passed in
   public BarcodeImage(String[] str_data)
   {
      image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
      int dataLength = str_data.length - 1;
      for ( int i=MAX_HEIGHT-1; i >= 0; i-- )
      {
         for ( int j=0; j < MAX_WIDTH; j++ )
         {
            if( dataLength >= 0 )
            {
               char[] dataCharArray = str_data[dataLength].toCharArray();
               if( j < dataCharArray.length )
               {
                  if( dataCharArray[j] == '*' )
                  {
                     image_data[i][j] = true;
                  }
                  else if ( dataCharArray[j] == ' ' )
                  {
                     image_data[i][j] = false;
                  }
               
               }
               else
               {
                  image_data[i][j] = false;
               }
            }
            else
            {
               image_data[i][j] = false;
            }
         }
         dataLength--;
      }            
   }
   
   //Accessor for pixel
   boolean getPixel(int row, int col)
   {
      if(image_data[row][col])
      {
         return image_data[row][col];
      }
      else
         return false;
   }
   
   //Mutator for pixel
   boolean setPixel(int row, int col, boolean value)
   {
      if(row < MAX_WIDTH && 0 <= row && col < MAX_HEIGHT && 0 <= col)
      {
         image_data[row][col] = value;
         return true;
      }
      else
      {
         return false;
      }
   }
   
   //Checks the incoming data for every conceivable size or null error
   /**
   private boolean checkSize(String[] data)
   {
      if(data == null || data.length >= MAX_HEIGHT)
      {
         return false;
      }
      else
      {
         for( int i = 0; i < data.length; i++ )
         {
            if( data[i].length() > MAX_WIDTH )
            {
               return false;
            }
            else
            {
               return true;
            }
         }
      }
   }
   **/
   
   //Displays the data to the console
   public void displayToConsole()
   {
      for( int i = 0; i < MAX_HEIGHT; i++ )
      {
         StringBuilder data = new StringBuilder();
         for( int j = 0; j < MAX_WIDTH; j++ )
         {
            if(image_data[i][j] == true)
            {
               data.append("*");
               //System.out.println("displayToConsole true");
            }
            else if(image_data[i][j] == false)
            {
               data.append(" ");
               //System.out.println("displayToConsole false");
            }
         }
         System.out.println(data);
      }
   }
   
   //Method that overrides the method of that name in Cloneable interface
   public BarcodeImage clone()
   {
      try
      {
         BarcodeImage copy = (BarcodeImage)super.clone();
         copy.image_data = (boolean[][])image_data.clone();
         return copy;
      }
      catch (CloneNotSupportedException e)
      {
         return null;
      }
   }
   
}
