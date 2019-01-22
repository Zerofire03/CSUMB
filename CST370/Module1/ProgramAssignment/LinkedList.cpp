/*
 * Christopher Holmes
 * ID: 002928626
 * Module 1 - Programming Assignment
 * 1/12/19
 * Abstract: Defined displaySubstrings function
 */

#include <iostream>
using namespace std;

#include "LinkedList.h"

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
//void LinkedList::erase(int index)
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

//-- Definition of displaySibstrings()
void LinkedList::displaySubstrings(ostream & out) const
{
	Node * ptr = first; //Begging of linked list
	Node * ptrA; //Pointer for beginning of substring
	int numStrings = 0; //Number of substrings found

	while(ptr != 0)
	{
		if(ptr->data == 'A') //If the data stored at that node is an A, start looking for a B
		{
			ptrA = ptr; //Store current location to pointer so we don't lose our place in list
			string output;
			while(ptrA != 0)
			{
				if(ptrA->data == 'B') //If the data stored at that node is a B, output the stored string
				{
					output += ptrA->data; //Store current node data to output string
					numStrings++; //increase number of strings found
					cout << "Substring " << numStrings << " " << output << endl; //output to console
					ptrA = ptrA->next; //Move to next node
				}
				else
				{
					output += ptrA->data; //Store current node data to output string
					ptrA = ptrA->next; //Move to next node
				}
			}
			ptr = ptr->next; //Move to next node
		}
		else //If not an a move to next node
		{
			ptr = ptr->next; //Move to next node
		}
	}
	cout << "Total " << numStrings << " substrings"; //Output total number substrings
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


