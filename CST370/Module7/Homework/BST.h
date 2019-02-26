#include <iostream>
using namespace std;  

#ifndef BINARY_SEARCH_TREE
#define BINARY_SEARCH_TREE

class BST
{
 public:
  /***** Function Members *****/
  BST();
  bool empty() const;
  bool search(char & item);
  void insert(const char & item);
  void inOrder(ostream & out);
  void preOrder(ostream & out);
  void postOrder(ostream & out);
  void remove(char & item);
  void graph(ostream & out);
  int nodeCount();
  
 private:
  /***** Node class *****/
  class BinNode 
  {
   public:
    char data;
    BinNode * left;
    BinNode * right;

    // BinNode constructors
    // Default -- data part is default int value; both links are null.
    BinNode()
    : left(0), right(0)
    {}

    // Explicit Value -- data part contains item; both links are null.
    BinNode(int item)
    : data(item), left(0), right(0)
    {}
 
  };// end of class BinNode declaration
  
  /***** Data Members *****/
  BinNode * myRoot; 

  /**** Private Member Functions *****/
  bool searchAux(char & item, BST::BinNode* subtreeRoot);
  void inOrderAux(ostream & out, BST::BinNode* subtreeRoot);
  void preOrderAux(ostream & out, BST::BinNode* subtreeRoot);
  void postOrderAux(ostream & out, BST::BinNode* subtreeRoot);
  int nodeCountAux(BST::BinNode* subtreeRoot);
  void search2(char & item, bool & found, BinNode* & locptr, BinNode* & parent);
  void graphAux(ostream & out, int indent, BST::BinNode* subtreeRoot);

}; // end of class declaration

#endif
