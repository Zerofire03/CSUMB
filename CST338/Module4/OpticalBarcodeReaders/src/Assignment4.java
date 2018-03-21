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
      
      BarcodeImage defaultConstructor = new BarcodeImage();
      System.out.println("Default Constructor");
      defaultConstructor.displayToConsole();
      System.out.println("\nImage In");
      BarcodeImage imageIn = new BarcodeImage(sImageIn_3);
      imageIn.displayToConsole();
      System.out.println("End Image");
      //System.out.println("DataMatrix");
      //DataMatrix test = new DataMatrix("This");
      //test.generateImageFromText();

   }

}
