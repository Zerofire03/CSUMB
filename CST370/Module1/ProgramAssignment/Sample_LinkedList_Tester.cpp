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
	   cout << userString[pos] << " ";
	   pos++;
   } while(pos < userString.length());
   charList.display(cout);

   /*
   cout << "Constructing intList\n";

   // Test insert()
   intList.insert(100, 0);
   intList.display(cout);
   cout << endl;

   intList.insert(200, 0);
   intList.display(cout);
   cout << endl;
   
   intList.insert(300, 1);
   intList.display(cout);
   cout << endl;
   
   intList.insert(400, 1);
   intList.display(cout);
   cout << endl;
   
   intList.insert(500, 3);
   intList.display(cout);
   cout << endl;

   // Test destructor
   {
      LinkedList anotherList;
      for (int i = 0; i < 10; i++)
      {
	anotherList.insert(100*i, i);
      }
      cout << "\nThis is another list\n";
      anotherList.display(cout);
   }

   // Test erase
   intList.erase(1);
   intList.erase(1);
   cout << "\n\nTwo items are erased from the first list\n";
   intList.display(cout);
   cout << endl;
   */

}
