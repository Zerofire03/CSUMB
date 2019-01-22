/*
 * Christopher Holmes
 * ID: 002928626
 * Module 1 - Programming Assignment
 * 1/12/19
 * Abstract: Read in a string from user to variable, store string in linked list,
 * search string for all substrings starting with a and ending with b and display
 */

#include <iostream>
#include <string>
using namespace std;

#include "LinkedList.h"

int main()
{

   LinkedList charList;
   cout << "Enter a string: "; //Prompt user for string of characters

   string userString;
   getline(cin, userString); //Store user provided string of characters to string
   int pos = 0;
   do
   {
	   charList.insert(userString[pos], pos); //Store each element of string to LinkedList
	   pos++;
   } while(pos < userString.length());
   charList.displaySubstrings(cout); //Call to substring function to display all combinations

}
