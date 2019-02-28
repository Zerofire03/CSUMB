/*
 * Christopher Holmes
 * ID: 002928626
 * Final Coding
 * 2/25/19
 * Abstract: Define insertBalanced, insertBalancedAux, findLevel, findLevelAux
 */

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
  bool search(const int & item) const; 
  void insert(const int & item);
  void insertBalanced(int array[], int start, int end);
  void graph(ostream & out);
  void preOrder(ostream & out);
  int findLevel(int item);
  
 private:
  /***** Node class *****/
  class BinNode 
  {
   public:
    int data;
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
  
  //Private functions
  BinNode* insertBalancedAux(int array[], int start, int end);
  void graphAux(ostream & out, int indent, BST::BinNode* subtreeRoot);
  void preOrderAux(ostream & out, BST::BinNode* subtreeRoot);
  BinNode* newNode(int data);
  int findLevelAux(BinNode* subtreeRoot, int item, int level);

  /***** Data Members *****/
  BinNode * myRoot; 

}; // end of class declaration

#endif
