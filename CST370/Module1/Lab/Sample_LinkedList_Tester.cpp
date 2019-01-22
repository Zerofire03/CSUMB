/*
 * Christopher Holmes
 * ID: 002928626
 * Module 1 - Lab
 * 1/8/19
 * Abstract: Added function calls to maxItem and isAscendingOrder to lists to demonstrate their working
 */

#include <iostream>
using namespace std;

#include "LinkedList.h"

int main()
{
   // Test the class constructor
   LinkedList intList;
   cout << "Constructing intList\n";
   cout << "Max item empty list: " << intList.maxItem() << endl; //Empty list maxItem call
   bool ascendingOrder = intList.isAscendingOrder(); //Empty list isAscendingOrder call
   cout << "Ascending order empty list: ";
   printf("%s", ascendingOrder ? "true" : "false");
   cout << endl << endl;

   // Test insert()
   intList.insert(100, 0);
   cout << "Max item single item: " << intList.maxItem() << endl; //Single item list maxItem call
   ascendingOrder = intList.isAscendingOrder(); //Single item isAscendingOrder call
   cout << "Ascending order single item list: ";
   printf("%s", ascendingOrder ? "true" : "false");
   cout << endl;
   intList.display(cout);
   cout << endl << endl;


   intList.insert(200, 0);
   cout << "Max item two item: " << intList.maxItem() << endl; //Max item 2 items
   intList.display(cout);
   cout << endl << endl;
   
   intList.insert(300, 1);
   cout << "Max item three item: " << intList.maxItem() << endl; //Max item 3 items
   intList.display(cout);
   cout << endl << endl;
   
   intList.insert(400, 1);
   cout << "Max item four item: " << intList.maxItem() << endl; //Max items 4 items
   ascendingOrder = intList.isAscendingOrder(); //isAscendingOrder 4 items
   cout << "Ascending order four item list: ";
   printf("%s", ascendingOrder ? "true" : "false");
   cout << endl;
   intList.display(cout);
   cout << endl << endl;
   

   intList.insert(500, 3);
   cout << "Max item five item: " << intList.maxItem() << endl; //Max items 5 items
   ascendingOrder = intList.isAscendingOrder(); //isAscending order 5 items
   cout << "Ascending order five item list: ";
   printf("%s", ascendingOrder ? "true" : "false");
   cout << endl;
   intList.display(cout);
   cout << endl << endl;


   // Test destructor
   {
      LinkedList anotherList;
      for (int i = 0; i < 10; i++)
      {
	anotherList.insert(100*i, i);
      }
      cout << "\nThis is another list\n";
      cout << "Max item another list: " << anotherList.maxItem() << endl; //maxItem another list
      ascendingOrder = anotherList.isAscendingOrder(); //isAscendingOrder another list
      cout << "Ascending order another list: ";
      printf("%s", ascendingOrder ? "true" : "false");
      cout << endl;
      anotherList.display(cout);
   }

   // Test erase
   intList.erase(1);
   intList.erase(1);
   cout << "\n\nTwo items are erased from the first list\n";
   intList.display(cout);
   cout << endl;


}
