/*
 * Christopher Holmes
 * ID: 002928626
 * Final Coding
 * 2/25/19
 * Abstract: Implement insertBalanced, insertBalancedAux, findLevel, findLevelAux
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


bool BST::search(const int & item) const
{
   BinNode * locptr = myRoot;
   bool found = false;
   while (!found && locptr != 0)
   {
      if (item < locptr->data)       // descend left
        locptr = locptr->left;
      else if (locptr->data < item)  // descend right
        locptr = locptr->right;
      else                           // item found
        found = true;
   }
   return found;
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

void BST::insertBalanced(int array[], int start, int end)
{
	myRoot = insertBalancedAux(array, start, end);
}

BST::BinNode* BST::insertBalancedAux(int array[], int start, int end)
{

	//End of recurssion
	if(start > end)
	{
		return NULL;
	}

	//Find the middle and make root
	int mid = (start + end)/2;
	BinNode* root = newNode(array[mid]);

	//Recurse left
	root->left = insertBalancedAux(array, start, mid-1);

	//Recurse right
	root->right = insertBalancedAux(array, mid+1, end);


	return root;
}

void BST::preOrder(ostream & out)
{
	preOrderAux(out, myRoot);
}

void BST::preOrderAux(ostream & out, BST::BinNode* subtreeRoot)
{
	if (subtreeRoot != 0)
	{
		out << subtreeRoot->data << " "; //Root operation
		preOrderAux(out, subtreeRoot->left); //Left operation
		preOrderAux(out, subtreeRoot->right); //Right operation
	}
}

//Create new node to insert into tree
BST::BinNode* BST::newNode(int data)
{
	BinNode* node = new BinNode(data);
	node->left = NULL;
	node->right = NULL;

	return node;
}

//Public function to return level
int BST::findLevel(int item)
{
	return findLevelAux(myRoot, item, 0);
}

//Private function to recursively find level of item
int BST::findLevelAux(BinNode* subtreeRoot, int item, int level)
{
	//Base case
	if(subtreeRoot == 0)
	{
		return 0;
	}

	//Item found returning level
	if(subtreeRoot -> data == item)
	{
		return level;
	}

	//Recurssive call down left side of tree
	int nextLevel = findLevelAux(subtreeRoot->left, item, level+1);

	if(nextLevel != 0)
	{
		return nextLevel;
	}

	//Recursisve call down right side of tree
	nextLevel = findLevelAux(subtreeRoot->right, item, level+1);
	return nextLevel;
}
