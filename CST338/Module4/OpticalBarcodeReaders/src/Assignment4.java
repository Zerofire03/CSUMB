/**
 * 
 */

/**
 * @author cholmes
 *
 */
public class Assignment4
{

   /**
    * @param args
    */
   public static void main(String[] args)
   {
      //width:42
      //height:9
      String[] sImageIn =
         {
            "                                               ",
            "                                               ",
            "                                               ",
            "     * * * * * * * * * * * * * * * * * * * * * ",
            "     *                                       * ",
            "     ****** **** ****** ******* ** *** *****   ",
            "     *     *    ****************************** ",
            "     * **    * *        **  *    * * *   *     ",
            "     *   *    *  *****    *   * *   *  **  *** ",
            "     *  **     * *** **   **  *    **  ***  *  ",
            "     ***  * **   **  *   ****    *  *  ** * ** ",
            "     *****  ***  *  * *   ** ** **  *   * *    ",
            "     ***************************************** ",  
            "                                               ",
            "                                               ",
            "                                               "

         };      
      
      String[] sImageIn_2 =
         {
               "                                          ",
               "                                          ",
               "* * * * * * * * * * * * * * * * * * *     ",
               "*                                    *    ",
               "**** *** **   ***** ****   *********      ",
               "* ************ ************ **********    ",
               "** *      *    *  * * *         * *       ",
               "***   *  *           * **    *      **    ",
               "* ** * *  *   * * * **  *   ***   ***     ",
               "* *           **    *****  *   **   **    ",
               "****  *  * *  * **  ** *   ** *  * *      ",
               "**************************************    ",
               "                                          ",
               "                                          ",
               "                                          ",
               "                                          "

         };
      
      String[] sImageIn_3 =
         {
               "* * * * * * * * * * * * * * * * * *", 
               "*                                 *", 
               "***** ** * **** ****** ** **** **  ", 
               "* **************      *************", 
               "**  *  *        *  *   *        *  ", 
               "* **  *     **    * *   * ****   **", 
               "**         ****   * ** ** ***   ** ", 
               "*   *  *   ***  *       *  ***   **", 
               "*  ** ** * ***  ***  *  *  *** *   ", 
               "***********************************"
         };
      
      /**
      //BarcodeImage defaultConstructor = new BarcodeImage();
      //System.out.println("Default Constructor");
      //defaultConstructor.displayToConsole();
      System.out.println("Image In");
      BarcodeImage imageIn = new BarcodeImage(sImageIn_2);
      imageIn.displayToConsole();
      System.out.println("\nEnd Image");
      System.out.println("\nDataMatrix");
      DataMatrix test = new DataMatrix(imageIn);
      test.displayRawImage();
      System.out.println("\nEnd DataMatrix");
      System.out.printf("Signal Height: %d Signal Width: %d\n", test.getActualHeight(), test.getActualWidth());
      test.translateImageToText();
      **/
      
      BarcodeImage bc = new BarcodeImage(sImageIn);
      DataMatrix dm = new DataMatrix(bc);
     
      // First secret message
      System.out.printf("Signal Height: %d Signal Width: %d\n", dm.getActualHeight(), dm.getActualWidth());
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayRawImage();
      //dm.displayImageToConsole();
      
      // second secret message
      bc = new BarcodeImage(sImageIn_2);
      dm.scan(bc);
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayRawImage();
      //dm.displayImageToConsole();
      
      // create your own message
      dm.readText("What a great resume builder this is!");
      dm.generateImageFromText();
      dm.displayTextToConsole();
      dm.displayRawImage();
      //dm.displayImageToConsole();
   }

}
