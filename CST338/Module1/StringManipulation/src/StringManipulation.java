//Christopher Holmes
//CST 338
//Assignment 1
//2-23-18

import java.text.DecimalFormat;
import java.util.Scanner;
public class StringManipulation
{

   public static void main(String[] args)
   {
      //Part 1
      //Create scanner and prompt for first and last name storing to variables
      Scanner sc = new Scanner(System.in);
      System.out.println("Please enter first name:");
      String firstName = sc.next();
      System.out.println("Please enter last name:");
      String lastName = sc.next();

      //Concatenate first and last name storing into fullName
      String fullName = firstName + " " + lastName;
      
      //Print out the various formats of the names with the length of the string
      System.out.println(fullName);
      System.out.print("Name length: ");
      System.out.println(fullName.length());
      System.out.println(fullName.toUpperCase());
      System.out.println(fullName.toLowerCase());
      
      //Part 2
      final int MIN_HOURS = 12;
      final int MAX_HOURS = 20;
      //Prompt user to input how many hours were worked between the static variable range
      System.out.println("Enter many hours you spent working on this class this week between " + MIN_HOURS
            + " to " + MAX_HOURS + ":");
      double hoursWorked = sc.nextDouble();
      
      //Define pattern used by DecimalFormat to round to single decimal point.
      //Format the hours worked that was previously read in and then output.
      String pattern = "##.#";
      DecimalFormat myFormat = new DecimalFormat(pattern);
      String formatted = myFormat.format(hoursWorked);
      System.out.println("Hours worked rounded: " + formatted);
      
      sc.close();
   }

}

/********************Output*********************
 * 
Please enter first name:
Chris
Please enter last name:
Holmes
Chris Holmes
Name length: 12
CHRIS HOLMES
chris holmes
Enter many hours you spent working on this class this week between 12 to 20:
12.367
Hours worked rounded: 12.4

Please enter first name:
Christopher
Please enter last name:
Holmes
Christopher Holmes
Name length: 18
CHRISTOPHER HOLMES
christopher holmes
Enter many hours you spent working on this class this week between 12 to 20:
19.745
Hours worked rounded: 19.7
 * 
 * *********************************************/