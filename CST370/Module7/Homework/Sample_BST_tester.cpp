/*----- treetester.cpp -----------------------------------------------------
                Program for testing BST.
 ------------------------------------------------------------------------*/
#include <iostream>
using namespace std;

#include "BST.h"

int main()
{
   // Testing Constructor and empty()
   BST intBST;            // test the class constructor
   cout << "Constructing empty BST\n";
   cout << "BST " << (intBST.empty() ? "is" : "is not") << " empty\n";

   // Testing insert
   cout << "\nNow insert a bunch of integers into the BST."
           "\nTry items not in the BST and some that are in it:\n";
   char number;
   for (;;)
   {
      cout << "Item to insert (z to stop): ";
      cin >> number;
      if (number == 'z') break;
      intBST.insert(number);
   }
   cout << "BST " << (intBST.empty() ? "is" : "is not") << " empty\n";

   // Testing inOrder()
      cout << "\n\nNow testing the inOrder() operation." << endl;
      intBST.inOrder(cout);

   // Testing preOrder()
      cout << "\n\nNow testing the preOrder() operation." << endl;
      intBST.preOrder(cout);

   // Testing preOrder()
      cout << "\n\nNow testing the postOrder() operation." << endl;
      intBST.postOrder(cout);

      /*

   // Testing nodeCount()
      cout << "\n\nNow testing the nodeCount() operation." << endl;
      cout << "Node count: " << intBST.nodeCount() << endl;


   // Testing nodeOrder()
      cout << "\n\nNow testing the graph() operation." << endl;
      intBST.graph(cout);

   // Testing search()
   cout << "\n\nNow testing the search() operation."
           "\nTry both items in the BST and some not in it:\n";
   for (;;)
   {
      cout << "Item to find (-999 to stop): ";
      cin >> number;
      if (number == -999) break;
      cout << (intBST.search(number) ? "Found" : "Not found") << endl;
   }
*/
}
