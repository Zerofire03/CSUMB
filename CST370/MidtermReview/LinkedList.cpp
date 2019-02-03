/*
 * Christopher Holmes
 * ID: 002928626
 * Module 1 - Lab
 * 1/8/19
 * Abstract: Implemented maxItem and isAscendingOrder functions
 */

#include <iostream>
using namespace std;

#include "LinkedList.h"

//Linked Lists Review 1
ElementType LinkedList::count(int searchFor)
{
	Node * ptr = first;
	int num = 0;
	while (ptr != 0)
	{
		if(ptr->data == searchFor)
		{
			num++;
		}
		ptr = ptr->next;
	}
	return num;
}

ElementType LinkedList::getNth(int index) //Linked List Review 2
{
	Node * ptr = first;
	if(index < 0 || index > mySize -1)
	{
		cout << "Index out of range. ";
		return NULL;
	}
	else
	{
		for(int i=0; i<=index; i++)
		{
			if(i==index)
			{
				return ptr->data;
			}
			ptr = ptr->next;
		}
	}
}

ElementType LinkedList::pop() //Linked List Review 3
{
	Node * firstNode = first;
	if(mySize == 0)
	{
		cout << "List is empty." << endl;
		return NULL;
	}
	else if(mySize == 1)
	{
		ElementType temp = firstNode->data;
		this -> erase(0);
		return temp;
	}
	else
	{
		Node * secondNode = firstNode->next;
		ElementType temp = firstNode->data;
		this -> erase(0);
		first = secondNode;
		return temp;
	}
}

void LinkedList::append(LinkedList b) //Linked List Review 4
{
	cout << "Beginning append" << endl;
	Node * ptrAList = first;
	Node * ptrBList = b.first;
	cout << "ptrBList: " << ptrBList << endl;
	while(ptrAList != NULL)
	{
		ptrAList = ptrAList->next;
	}
	cout << "End of first while loop" << endl;
	ptrAList->next = ptrBList;

	while(ptrBList != NULL)
	{
		cout << "Beginning of second while loop" << endl;
		mySize++;
		ptrBList = ptrBList->next;
	}
	cout << "End of append" << endl;
	return;
}

//-- Default constructor
LinkedList::LinkedList()
{ 
	mySize = 0;
	first = NULL;
}

//-- Definition of the copy constructor
LinkedList::LinkedList(const LinkedList & origList)
{
	mySize = origList.mySize;
	first = NULL;
	
	if (mySize == 0) 
		return;

	Node * origPtr, * lastPtr;
	first = new Node(origList.first->data); // copy first node
	lastPtr = first;
	origPtr = origList.first->next;
	while (origPtr != NULL)
	{
		lastPtr->next = new Node(origPtr->data);
		origPtr = origPtr->next;
		lastPtr = lastPtr->next;
	}
}


//-- Definition of the destructor
LinkedList::~LinkedList()
{
	Node * prev = first;
	Node * ptr;

	while (prev != NULL)
	{
		ptr = prev->next;
		delete prev;
		prev = ptr;
	}
}


//-- Definition of insert()
void LinkedList::insert(ElementType dataVal, int index)
{
	if (index < 0 || index > mySize)
	{
		cerr << "Illegal location to insert -- " << index << endl;
		return;
	}

	mySize++;
	Node * newPtr = new Node(dataVal);
	Node * predPtr = first;
	if (index == 0)
	{
		newPtr->next = first;
		first = newPtr;
	}
	else
	{
		for(int i = 1; i < index; i++)
			predPtr = predPtr->next;
		newPtr->next = predPtr->next;
		predPtr->next = newPtr;
	}
}


//-- Definition of erase()
void LinkedList::erase(int index)
{
	if (index < 0 || index >= mySize)
	{
		cerr << "Illegal location to delete -- " << index << endl;
		return;
	}

	mySize--;
	Node * ptr;
	Node * predPtr = first;
	if (index == 0)
	{
		ptr = first;
		first = ptr->next;
		delete ptr;
	}
	else
	{
		for(int i = 1; i < index; i++)
			predPtr = predPtr->next;
		ptr = predPtr->next;
		predPtr->next = ptr->next;
		delete ptr;
	}
}



//-- Definition of display()
void LinkedList::display(ostream & out) const
{
	Node * ptr = first;
	while (ptr != 0)
	{
		out << ptr->data << " ";
		ptr = ptr->next;
	}
}

//-- Definition of maxItem()
ElementType LinkedList::maxItem()
{
	if(mySize == 0) //Check to see if list is empty and return accordingly
	{
		cout << "Empty List ";
		return -1;
	}
	else if(mySize == 1) //Return if list size is 1
	{
		return first->data;
	}
	else //Cycle through the list looking at each node to determine if it is larger than the one currently stored in maxItem
	{
		Node * ptr = first;
		ElementType maxItem = first->data;
		ptr = ptr->next;
		while (ptr != 0)
		{
			if(ptr->data > maxItem)
			{
				maxItem = ptr->data;
			}
			ptr = ptr->next;
		}
		return maxItem;
	}
}

//-- Definition of isAscendingOrder()
bool LinkedList::isAscendingOrder()
{
	if(mySize == 0 || mySize == 1) //List size 0 and 1 return true
	{
		return true;
	}
	else //Any other list size cycle through list and check order comparing nodes
	{
		Node * currentPtr = first;
		Node * nextPtr = currentPtr->next;
		while(currentPtr->next != 0)
		{
			ElementType curData = currentPtr->data;
			ElementType nextData = nextPtr->data;
			if(curData > nextData) //if current node is larger than next node not in order
			{

				return false;
			}
			currentPtr = currentPtr->next;
			nextPtr = currentPtr->next;
		}
		return true;
	}
}


