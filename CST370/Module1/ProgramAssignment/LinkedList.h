/*
 * Christopher Holmes
 * ID: 002928626
 * Module 1 - Programming Assignment
 * 1/12/19
 * Abstract: Define displaySubstrings function
 */

//----- LinkedList.h -----
#ifndef LINKEDLIST
#define LINKEDLIST

#include <iostream>
using namespace std;

typedef char ElementType;

class LinkedList
{ 
public:
	LinkedList();   // constructor
	~LinkedList();  // destructor
	LinkedList(const LinkedList & original); //copy constructor
	void insert(ElementType item, int pos);
	void erase(int item);
	void displaySubstrings(ostream & out) const;
	void display(ostream & out) const;
	/*--------------------------------------------------------------------
	Display the contensts of this LinkedList.
	Precondition: ostream out is open
	Postcondition: Elements of this LinkedList have been output to out.
	--------------------------------------------------------------------*/

private:
	class Node
	{
	public:
		ElementType data;
		Node * next;
		//------ Node OPERATIONS
		//-- Default constrctor: initializes next member to
		Node()
			: next(NULL)
		{ }
		//-- Explicit-value constructor: initializes data member to dataValue
		//-- and next member to 0
		Node(ElementType dataValue)
			: data(dataValue), next(NULL)
		{ }
	};
	Node * first;
	int mySize;
};

#endif
