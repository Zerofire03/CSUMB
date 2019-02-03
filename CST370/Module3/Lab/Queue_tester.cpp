/*
 * Christopher Holmes
 * ID: 002928626
 * Module 3 - Lab
 * 1/28/19
 * Abstract: Read in text from a file, parse out the footnotes, display text
 * without footnotes, display footnotes grouped together at the end
 */

/*---------------------------------------------------------------------
                  Driver program to test the Queue class.
  ----------------------------------------------------------------------*/

#include <iostream>
#include <fstream>
#include <string>
using namespace std;
	
#include "Queue.h"

int main()
{
   Queue q1;
   //string line;
   string output;

   //Create stream and open sample.txt file
   ifstream input("sample.txt");

   //Loop through each line in the file
   for(string line; getline(input, line);)
   {
	   //Loop through each char in the line to see if there are any delimiters
	   int startStr = 0;
	   for(int i=0; i<line.length(); i++)
	   {
		   //Check to see if char is opening of footnote
		   if(line[i] == '{')
		   {
			   //First substring of displayed text
			   if(startStr==0)
			   {
				   cout << line.substr(startStr, i);
			   }
			   //All other substrings of displayed text
			   else
			   {
				   cout << line.substr(startStr+2, i-startStr-2);
			   }
			   //Loop to find the end delimiter of footnote
			   for(int j=i; j<line.length(); j++)
			   {
				   //Check if char is end delimiter
				   if(line[j] == '}')
				   {
					   //Enqueue substring of data, change startStr, and break out of loop
					   q1.enqueue(line.substr(i+1, j-i-1));
					   startStr = j;
					   break;
				   }
			   }
		   }
		   //If char is not opening delimiter
		   else
		   {
			   //check to see if at end of line before outputing
			   if(startStr==0 && i==line.length()-1)
			   {
				   cout << line << endl;
			   }
		   }
	   }
   }
   //Display footnotes
   cout << endl << "Footnotes: ";
   q1.display(cout);
}
