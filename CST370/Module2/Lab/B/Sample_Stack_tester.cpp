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


   /*
   for (int i = 0; i < userString.length(); i++)
   {
      s.push(userString[i]);
   }

   cout << "Reverse is ";
   s.display(cout);


   cout << "Stack contents:\n";
   s.display(cout);
   cout << "Stack empty? " << s.empty() << endl;

   cout << "Top value: " << s.top() << endl;

   while (!s.empty())
   {
     cout << "Popping " << s.top() << endl;
     s.pop();
   }
   cout << "Stack empty? " << s.empty() << endl;
   cout << "Top value: " << s.top() << endl;
   cout << "Trying to pop: " << endl;
   s.pop();
   */
}
