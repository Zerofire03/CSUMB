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
      
      BarcodeImage bc = new BarcodeImage(sImageIn);
      DataMatrix dm = new DataMatrix(bc);
     
      
      // First secret message
      dm.translateImageToText();
      dm.displayTextToConsole();
      //dm.displayRawImage();
      dm.displayImageToConsole();
      
      // second secret message
      bc = new BarcodeImage(sImageIn_2);
      dm.scan(bc);
      dm.translateImageToText();
      dm.displayTextToConsole();
      //dm.displayRawImage();
      dm.displayImageToConsole();
      
      // create your own message
      dm.readText("What a great resume builder this is!");
      dm.generateImageFromText();
      dm.displayTextToConsole();
      //dm.displayRawImage();
      dm.displayImageToConsole();
   }

}
