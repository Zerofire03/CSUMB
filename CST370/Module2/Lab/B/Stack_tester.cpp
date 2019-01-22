/*
 * Christopher Holmes
 * ID: 002928626
 * Module 2 - Lab Part A
 * 1/13/19
 * Abstract: Read in a number, convert to binary, stored to stack,
 * printed out in binary in correct order
 */

/*---------------------------------------------------------------------
               Driver program to test the Stack class.
  ----------------------------------------------------------------------*/

#include <iostream>
#include <bitset>
using namespace std;

#include "Stack.h"

int main()
{
   Stack s;

   cout << "Enter a number: ";
   int userNumber;
   cin >> userNumber; //Read in number from user

   cout << "Decimal: " << userNumber << endl; //Display user number as decimal

   string binary = bitset<16>(userNumber).to_string(); //convert the decimal to binary

   for (int i = binary.length()-1; i >= 0; i--)
   {
	   s.push(binary[i]);
   }

   cout << "Binary: ";
   s.display(cout);


}
