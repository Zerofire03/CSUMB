#include <iostream>
#include "LinkedList.h"
using namespace std;

int main()
{
	LinkedList intList;
	LinkedList intList2;

	intList.insert(100, 0);
	intList.insert(200, 1);
	intList.insert(300, 2);
	intList.insert(400, 3);
	intList.insert(500, 4);
	intList.insert(500, 5);
	intList.insert(500, 6);

	intList2.insert(100,0);
	intList2.insert(200,1);

	cout << "intList: ";
	intList.display(cout);
	cout << endl << "intList2: ";
	intList2.display(cout);
	cout << endl;
	//cout << "Count of 500: " << intList.count(500) << endl;
	//cout << "Get Nth 3: " << intList.getNth(3) << endl;
	//cout << "Pop(): " << intList.pop() << endl;

	intList.append(intList2);
	cout << "Appended List: ";
	intList.display(cout);
}
