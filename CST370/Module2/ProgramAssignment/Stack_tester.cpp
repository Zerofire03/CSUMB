/*
 * Christopher Holmes
 * ID: 002928626
 * Module 2 - Programming Assignment
 * 1/21/19
 * Abstract: Implement a sort of a stack using another temporary stack
 */

/*---------------------------------------------------------------------
               Driver program to test the Stack class.
  ----------------------------------------------------------------------*/

#include <iostream>
using namespace std;

#include "Stack.h"

int main()
{
   Stack mainStack;
   Stack tempStack;

   //Store values in stack. Only leave one section uncommented at a time

   //Sample input 1
   //1, 5, 3, -3, 4, 8, 10, -5
   /*
   mainStack.push(1);
   mainStack.push(5);
   mainStack.push(3);
   mainStack.push(-3);
   mainStack.push(4);
   mainStack.push(8);
   mainStack.push(10);
   mainStack.push(-5);
   */

   //Sample input 2
   // 1, 5, -4, 6, 2
   /*
   mainStack.push(1);
   mainStack.push(5);
   mainStack.push(-4);
   mainStack.push(6);
   mainStack.push(2);
   */

   //Sample input 3
   // -1, -4, -4, 6, 6, 9
   mainStack.push(-1);
   mainStack.push(-4);
   mainStack.push(-4);
   mainStack.push(6);
   mainStack.push(6);
   mainStack.push(9);

   while(!mainStack.empty())
   {
	   //Grab the top element
	   int tempInt = mainStack.top();
	   mainStack.pop();

	   //Temp stack is not empty, top of main stack is greater than temp
	   while (!tempStack.empty() && tempStack.top() > tempInt)
	   {
		   //Pop temporary stack and push int to main stack
		   mainStack.push(tempStack.top());
		   tempStack.pop();
	   }

	   //Push tempInt in tempStack
	   tempStack.push(tempInt);
   }

   cout << "Sample Input:" << endl;
   tempStack.display(cout);
}
