/*
 * Christopher Holmes
 * ID: 002928626
 * Final Coding
 * 2/25/19
 * Abstract:
 */

#include <iostream>
using namespace std;

#include "BST.h"

int main()
{
	//Arrays of inputs
	int Input3[] = {1, 2, 3, 4, 5, 6};
	//int Input3[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	//int Input2[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
	//int Input3[] = {1};

	int arraySize = sizeof(Input3)/sizeof(Input3[0]);

	//Construct binary search tree
	BST intBSTBalanced;

	intBSTBalanced.insertBalanced(Input3, 0, arraySize-1);

	int num;
	cout << "Enter item to search for: " << endl;
	cin >> num;
	cout << "The number " << num << " is found on level " <<intBSTBalanced.findLevel(num) << endl;

	/*
	BST intBSTUnbalanced;
	for(int i=0; i<arraySize; i++)
	{
		intBSTUnbalanced.insert(Input3[i]);
	}

	cout << "Printing Unbalanced preOrder: ";
	intBSTUnbalanced.preOrder(cout);

	cout << endl << "Printing Balanced preOrder: ";
	intBSTBalanced.preOrder(cout);

	cout << "\nGraph Balanced" << endl;
	intBSTBalanced.graph(cout);
	*/

}
