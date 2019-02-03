/*
 * Christopher Holmes
 * ID: 002928626
 * Midterm - Coding
 * 2/3/19
 * Abstract: Write driver code for implementing getNthqueue
 */

#include <iostream>
using namespace std;

#include "Queue.h"

int main()
{
	Queue q1;
	for ( int i = 10; i <= 50; i = i + 10)
	{
		q1.enqueue(i);
	}
	cout << "Queue: " << endl;
	q1.display(cout);

	cout << "Enter value N: ";
	int input;
	cin >> input;


	cout << "Value at " << input << " location: " << q1.getNthQueue(input) << endl;
	cout << "Queue after getNthElement: " << endl;
	q1.display(cout);
}
