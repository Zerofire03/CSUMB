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

   /*
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
