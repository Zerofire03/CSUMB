#include <iostream>
#include <fstream>
#include <string>
using namespace std;

#include "Queue.h"
#include "Stack.h"

int main()
{
	Stack firstStack;
	Stack secondStack;

	firstStack.push(1);
	firstStack.push(2);
	firstStack.push(3);
	firstStack.push(4);
	firstStack.push(5);
	firstStack.display(cout);

	secondStack.push(6);
	secondStack.push(7);
	secondStack.push(8);
	secondStack.push(9);
	secondStack.push(10);

	int temp = firstStack.top() * 4;
	firstStack.pop();
	secondStack.push(temp);
	firstStack.pop();

	firstStack.display(cout);

	/*
	int values [5] = {50, 10, 5, 20, 30};
	Queue q;

	for (int i = 0; i < 5; i++)
	{
		q.enqueue( values[i] );
	}

	q.display(cout);

	int n=0;


	for(int i=0; i<2; i++)
	{
		n += q.front();
		q.dequeue();
	}

	cout << n;
	*/
}
