/*
 * Christopher Holmes
 * ID: 002928626
 * Module 6 - Program Assignment
 * 2/17/19
 * Abstract: Implemented inOrder, preOrder, nodeCount, searchAux, inOrderAux, preOrderAux, and nodeCountAux
 */

#include <iostream>
#include <iomanip>

using namespace std;

#include "BST.h"

//--- Definition of constructor
BST::BST()
: myRoot(0)
{}

bool BST::empty() const
{ return myRoot == 0; }

//Public function that has access to private data to do recursion
bool BST::search(int & item)
{
	return searchAux(item, myRoot);
}

//Aux function that actually does recursive search
bool BST::searchAux(int & item, BinNode* subtreeRoot)
{
	//Empty search tree, return false
	if(subtreeRoot == 0)
	{
		return false;
	}
	//Item found, return true
	else if(subtreeRoot->data == item)
	{
		return true;
	}
	//Current node less than search term
	else if(subtreeRoot->data < item)
	{
		return searchAux(item, subtreeRoot->right);
	}
	//Current node greater than search term
	return searchAux(item, subtreeRoot->left);
}


void BST::insert(const int & item)
{
   BinNode * locptr = myRoot;   // search pointer
   BinNode * parent = 0;        // pointer to parent of current node
   bool found = false;     // indicates if item already in BST
   while (!found && locptr != 0)
   {
      parent = locptr;
      if (item < locptr->data)       // descend left
         locptr = locptr->left;
      else if (locptr->data < item)  // descend right
         locptr = locptr->right;
      else                           // item found
         found = true;
   }
   if (!found)
   {                                 // construct node containing item
      locptr = new BinNode(item);  
      if (parent == 0)               // empty tree
         myRoot = locptr;
      else if (item < parent->data )  // insert to left of parent
         parent->left = locptr;
      else                           // insert to right of parent
         parent->right = locptr;
   }
   else
      cout << "Item already in the tree\n";
}

//Public function that has access to private data to do recursion
void BST::inOrder(ostream & out)
{
	inOrderAux(out, myRoot);
}

//Aux function that actually does inOrder display
void BST::inOrderAux(ostream & out, BST::BinNode* subtreeRoot)
{
	if (subtreeRoot != 0)
	{
		inOrderAux(out, subtreeRoot->left); //Left operation
		out << subtreeRoot->data << " "; //Root operation
		inOrderAux(out, subtreeRoot->right); //Right operation
	}
}

//Public function that has access to private data to do recursion
void BST::preOrder(ostream & out)
{
	preOrderAux(out, myRoot);
}

//Aux function that actually does preOrder display
void BST::preOrderAux(ostream & out, BST::BinNode* subtreeRoot)
{
	if (subtreeRoot != 0)
	{
		out << subtreeRoot->data << " "; //Root operation
		inOrderAux(out, subtreeRoot->left); //Left operation
		inOrderAux(out, subtreeRoot->right); //Right operation
	}
}

//Public function that has access to private data to do recursion
int BST::nodeCount()
{
	return nodeCountAux(myRoot)-1;
}

//Aux function that actually does nodeCount
int BST::nodeCountAux(BST::BinNode* subtreeRoot)
{
	//Reached bottom of tree
	if(subtreeRoot == 0)
	{
		return 1;
	}
	int total = 0;
	//If node has two leafs
	if(subtreeRoot->left && subtreeRoot->right)
	{
		total++;
	}
	//Recursive call that adds all left and right trees
	total += (nodeCountAux(subtreeRoot->left) + nodeCountAux(subtreeRoot->right));
	return total;
}