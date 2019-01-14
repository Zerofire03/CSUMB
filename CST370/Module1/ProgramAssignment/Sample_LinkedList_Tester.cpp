/*
 * Christopher Holmes
 * Module 1 - Programming Assignment
 * 1/12/19
 */
#include <iostream>
#include <string>
using namespace std;

#include "LinkedList.h"

int main()
{
   // Test the class constructor
   LinkedList charList;
   cout << "Enter a string: ";

   string userString;
   getline(cin, userString);
   int pos = 0;
   do
   {
	   charList.insert(userString[pos], pos);
	   pos++;
   } while(pos < userString.length());
   charList.displaySubstrings(cout);

}
