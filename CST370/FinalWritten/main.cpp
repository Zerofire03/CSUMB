#include <iostream>
#include <climits>
using namespace std;

// A BST node
struct node {
    int data;
    struct node *left, *right;
};

// Function to create a new binary tree node having given key
Node* newNode(int key)
{
    Node* node = new Node;
    node->data = key;
    node->left = node->right = nullptr;

    return node;
}


int count = 0;

void print(struct node *root, int k)
{
    if (root != NULL && count <= k)
    {
        print(root->right, k);
        count++;
        if (count == k)
          printf("%d ", root->data);
       print(root->left, k);
    }
}



int main()
{
	Node* root = nullptr;
	int keys[] = {15, 10, 20, 8, 12, 16, 25};

}
