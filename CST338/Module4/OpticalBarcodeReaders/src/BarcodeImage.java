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

   /// accessors and mutators
   public boolean getPixel(int row, int col)
   {
      // test for error conditions - out of index
      if (checkRowCols(row, col))
	 return false;

      // [height][width]
      return image_data[row][col];
   }
   public boolean setPixel(int row, int col, boolean value)
   {
      if (!checkRowCols(row, col))
         return false;

      // set the pixel value
      image_data[row][col] = value;

      return true;
   }

   /// Constuctors
   public BarcodeImage()
   {
      image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];

      // initialize array to false
      for (int height = 0; height < image_data.length; height++)
      {
         for (int width = 0; width < image_data[height].length; width++)
         {
            image_data[height][width] = false;
         }
      }
   }

   // take a 1D array with length and populate the image
   public BarcodeImage(String[] str_data)
   {
      image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];

      // test for invalid array input
      if (!checkSize(str_data))
      {
         return;
      }

      // loop through the array elements
      // left columns is a solid marker and throw away
      // bottom line is a solid marker and throw away
      // right is on/off and throw away
      // top is on/off and throw away
      // loop through starting at the bottom-left and work up
      // NOTE: Loop is not testing format of marker lines
      //      effectively we are evaluating index 1 through length - 1
      int imageIndex = 0;
      for (int x = str_data.length-1; x >= 0; x--)
      {
         // evaluate the string within the array element
         // left-most element is throw away
         // right element is either a "*" or " " based on odd/even line and should be skipped
         for (int y = 0; y < str_data[x].length(); y++)
         {
            image_data[imageIndex][y] = str_data[x].charAt(y) == '*' ? true : false;
         }

         imageIndex++;
      }
   }

   // validate array is within specs
   private boolean checkSize(String[] data)
   {
      if (data.length > MAX_HEIGHT || data[0].length() > MAX_WIDTH)
      {
         // error - should throw an exception here
         System.out.println("BarCodeImage Constructor: The provided array is out of bounds.");
         return false;
      }

      return true;
   }

   // validate the row and col params
   private boolean checkRowCols(int rows, int cols)
   {
      if (rows > MAX_HEIGHT || cols > MAX_WIDTH)
         return false;

      return true;
   }

   // print the contents of the array to console for testing
   public void displayToConsole()
   {
      // loop through top to bottom - max height
      for (int x = MAX_HEIGHT-1; x >= 0; x--)
      {
         // print the return char
         if (x < MAX_HEIGHT)
         {
            System.out.print("\n");
         }

         // loop through left->right - 0 index
         for (int y = 0; y < MAX_WIDTH; y++)
         {
            if (image_data[x][y] == true)
            {
               System.out.print("*");
            }
            else
            {
               System.out.print(" ");
            }
         }
      }
   }
   
   @Override
   public BarcodeImage clone() throws CloneNotSupportedException
   {
      BarcodeImage cloneImg = new BarcodeImage();
      cloneImg.image_data = image_data.clone();
      return cloneImg;
   }
}
