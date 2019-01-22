/*
 * Christopher Holmes
 * ID: 002928626
 * Module 2 - Lab Part A
 * 1/13/19
 * Abstract: Read in a string, stored to stack, printed out in reverse from stack
 */

/*---------------------------------------------------------------------
               Driver program to test the Stack class.
  ----------------------------------------------------------------------*/

#include <iostream>
using namespace std;

#include "Stack.h"

int main()
{
   Stack s;

   cout << "Enter a string: ";
   string userString;
   getline(cin, userString);

   cout << "You entered " << userString << endl;
   for (int i = 0; i < userString.length(); i++)
   {
      s.push(userString[i]);
   }

   cout << "Reverse is ";
   s.display(cout);


}
