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
      
      //BarcodeImage defaultConstructor = new BarcodeImage();
      //System.out.println("Default Constructor");
      //defaultConstructor.displayToConsole();
      System.out.println("Image In");
      BarcodeImage imageIn = new BarcodeImage(sImageIn);
      imageIn.displayToConsole();
      System.out.println("\nEnd Image");
      System.out.println("\nDataMatrix");
      DataMatrix test = new DataMatrix(imageIn);
      test.displayRawImage();
      System.out.println("\nEnd DataMatrix");

   }

}
