/*---------------------------------------------------------------------
                  Driver program to test the Queue class.
  ----------------------------------------------------------------------*/

#include <iostream>
#include <string>
using namespace std;
	
#include "Queue.h"
#include "Stack.h"

int main()
{
	//Variables to store needed items
   Queue q1;
   Stack s1;
   string userInput;

   //Read in string from user
   cout << "Enter a string: " << endl;
   cin >> userInput;

   //Store user string into stack and queue
   for(int i=0; i < userInput.length(); i++)
   {
	   s1.push(userInput[i]);
	   q1.enqueue(userInput[i]);
   }

   //Loop through checking the top of stack and front of queue to see if equal
   while(!s1.empty())
   {
	   //If top and front are same, move on to next element
	   if(s1.top() == q1.front())
	   {
		   s1.pop();
		   q1.dequeue();
		   //When stack is empty, it's a palindrome
		   if(s1.empty())
		   {
			   cout << userInput << " is a palindrome" << endl;
		   }
	   }
	   //If top and front aren't the same, not a palindrome
	   else
	   {
		   cout << "Not a palindrome" << endl;
		   break;
	   }
   }
}
