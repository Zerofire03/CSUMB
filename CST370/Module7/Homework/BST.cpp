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


bool BST::search(char & item)
{
	return searchAux(item, myRoot);
	/*
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
   */
}

bool BST::searchAux(char & item, BinNode* subtreeRoot)
{
	//Empty search tree
	if(subtreeRoot == 0)
	{
		return false;
	}
	else if(subtreeRoot->data == item)
	{
		return true;
	}
	else if(subtreeRoot->data < item)
	{
		return searchAux(item, subtreeRoot->right);
	}
	return searchAux(item, subtreeRoot->left);
}


void BST::insert(const char & item)
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

void BST::inOrder(ostream & out)
{
	inOrderAux(out, myRoot);
}

void BST::inOrderAux(ostream & out, BST::BinNode* subtreeRoot)
{
	if (subtreeRoot != 0)
	{
		inOrderAux(out, subtreeRoot->left); //Left operation
		out << subtreeRoot->data << " "; //Root operation
		inOrderAux(out, subtreeRoot->right); //Right operation
	}
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
		inOrderAux(out, subtreeRoot->left); //Left operation
		inOrderAux(out, subtreeRoot->right); //Right operation
	}
}

void BST::postOrder(ostream & out)
{
	postOrderAux(out, myRoot);
}

void BST::postOrderAux(ostream & out, BST::BinNode* subtreeRoot)
{
	if (subtreeRoot != 0)
	{
		postOrderAux(out, subtreeRoot->left); //Left operation
		postOrderAux(out, subtreeRoot->right); //Right operation
		out << subtreeRoot->data << " "; //Root operation
	}
}

int BST::nodeCount()
{
	return nodeCountAux(myRoot)-1;
}

int BST::nodeCountAux(BST::BinNode* subtreeRoot)
{
	if(subtreeRoot == 0)
	{
		return 1;
	}
	int total = 0;
	if(subtreeRoot->left && subtreeRoot->right)
	{
		total++;
	}
	total += (nodeCountAux(subtreeRoot->left) + nodeCountAux(subtreeRoot->right));
	return total;
}

void BST::remove(char & item)
{
	/*
	bool found;
	BinNode* x, parent;
	search2(item, found, x, parent);
	if(!found)
	{
		cout << "Item not in the BST\n";
		return;
	}
	if(x->left != 0 && x->right != 0)
	{
		BinNode* xSucc = x->right;
		parent = x;
		while(xSucc->left != 0)
		{
			parent = xSucc;
			xSucc = xSucc->left;
		}

		x->data = xSucc->data;
		x = xSucc;
	}

	BinNode* subtree = x->left;
	if(subtree == 0)
	{
		subtree = x->right;
	}
	if(parent == 0)
	{
		myRoot = subtree;
	}
	else if(parent->left == x)
	{
		parent->left = subtree;
	}
	else
	{
		parent->right = subtree
	}
	delete x;
*/
}

void BST::search2(char & item, bool & found, BinNode* & locptr, BinNode* & parent)
{
	/*
	locptr = myRoot;
	parent = 0;
	found = false;
	while(!found && locptr != 0)
	{
		if(item < locptr->data)
		{
			parent = locptr;
			locptr = locptr->left;
		}
		else if(locptr->data < item)
		{
			parent = locptr;
			locptr = locptr->right
		}
		else
		{
			found = true;
		}
	}
	*/
}

void BST::graph(ostream & out)
{
	graphAux(out, 0, myRoot);
}

void BST::graphAux(ostream & out, int indent, BST::BinNode* subtreeRoot)
{
	if(subtreeRoot != 0)
	{
		graphAux(out, indent + 8, subtreeRoot->right);
		out << setw(indent) << " " << subtreeRoot->data << endl;
		graphAux(out, indent + 8, subtreeRoot->left);
	}
}
