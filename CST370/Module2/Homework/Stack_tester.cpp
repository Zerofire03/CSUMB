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
   Stack first;
   Stack second;

   for(int i=1; i<=5; i++)
	   first.push(2*i);

   /*
   first.push(44);
   first.push(33);
   first.push(22);
   while(!first.empty())
	   first.pop();
	   */

   first.display(cout);

   /*
   first.push(1);
   first.push(2);
   first.push(3);
   first.push(4);

   second.push(11);
   second.push(12);
   second.push(13);
   second.push(14);
   second.push(15);


   cout << "First: " << endl;
   first.display(cout);
   cout << endl << "Second: " << endl;
   second.display(cout);

   int temp = first.top();
   first.pop();
   temp = temp * 2;
   second.push(temp);

   temp = first.top();
   temp = temp * 2;
   second.push(temp);


   temp = second.top() * 3;
   second.pop();
   first.push(temp);

   temp = second.top() * 3;
   second.pop();
   first.push(temp);

   temp = second.top() * 3;
   second.pop();
   first.push(temp);


   cout << endl << "First top: " << first.top() << endl;
   cout << "Second top: " << second.top() << endl;

   cout << endl << "First: " << endl;
   first.display(cout);
   cout << endl << "Second: " << endl;
   second.display(cout);
   */

}
