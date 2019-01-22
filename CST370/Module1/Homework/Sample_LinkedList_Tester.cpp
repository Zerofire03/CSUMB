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


   // Test insert()
   intList.insert(9, 0);
   intList.insert(17, 1);
   intList.insert(22, 2);
   intList.insert(26, 3);
   intList.insert(34, 4);

   cout << "Before insertnew" << endl;
   intList.display(cout);

   intList.insertnew(10,0);

   cout << endl << "After insertnew(10,0)" << endl;
   intList.display(cout);

   intList.insertnew(20,1);

   cout << endl << "After insertnew(20,1)" << endl;
   intList.display(cout);
   



}
