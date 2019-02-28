/*
 * Christopher Holmes
 * ID: 002928626
 * Final Coding
 * 2/25/19
 * Abstract: Main program implementing balanced tree, find level of nodes
 */

#include <iostream>
using namespace std;

#include "BST.h"

int main()
{
	//Arrays of inputs
	int Input1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	int Input2[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
	int Input3[] = {1};

	//Size of array
	int arraySize1 = sizeof(Input1)/sizeof(Input1[0]);
	int arraySize2 = sizeof(Input2)/sizeof(Input2[0]);
	int arraySize3 = sizeof(Input3)/sizeof(Input3[0]);

	//Construct binary search tree
	BST intBSTBalanced1;
	BST intBSTBalanced2;
	BST intBSTBalanced3;

	//Insert array into balanced tree
	intBSTBalanced1.insertBalanced(Input1, 0, arraySize1-1);
	intBSTBalanced2.insertBalanced(Input2, 0, arraySize2-1);
	intBSTBalanced3.insertBalanced(Input3, 0, arraySize3-1);

	//Print out level of each node in tree
	cout << "***Input1***" << endl;
	for(int i=0; i<arraySize1; i++)
	{
		cout << "Node " << Input1[i] << ": " << intBSTBalanced1.findLevel(Input1[i]) << endl;
	}

	cout << endl << "***Input2***" << endl;
	for(int i=0; i<arraySize2; i++)
	{
		cout << "Node " << Input2[i] << ": " << intBSTBalanced2.findLevel(Input2[i]) << endl;
	}

	cout << endl << "***Input3***" << endl;
	for(int i=0; i<arraySize3; i++)
	{
		cout << "Node " << Input3[i] << ": " << intBSTBalanced3.findLevel(Input3[i]) << endl;
	}
}
