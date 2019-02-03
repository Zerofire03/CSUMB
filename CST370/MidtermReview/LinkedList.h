/*
 * Christopher Holmes
 * ID: 002928626
 * Module 1 - Lab
 * 1/8/19
 * Abstract: Defined function calls for maxItem and isAscendingOrder functions
 */

//----- LinkedList.h -----
#ifndef LINKEDLIST
#define LINKEDLIST

#include <iostream>
using namespace std;

typedef int ElementType;

class LinkedList
{ 
public:
	ElementType count(int searchFor); //Linked List Review 1
	ElementType getNth(int index); //Linked List Review 2
	ElementType pop(); //Linked List Review 3
	void append(LinkedList b); //Linked List Review 4
	LinkedList();   // constructor
	~LinkedList();  // destructor
	LinkedList(const LinkedList & original); //copy constructor
	void insert(ElementType item, int pos);
	void erase(ElementType item);
	ElementType maxItem();
	bool isAscendingOrder();
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
