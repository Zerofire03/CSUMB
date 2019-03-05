#include <iostream>
using namespace std;

int main()
{
	int array [] = {1, 2, 3, 4, 5, 6, 8};

	int arraySize = sizeof(array)/sizeof(array[0]);

	for(int i=0; i<arraySize-1; i++)
	{
		if(!(array[i+1] == array[i] + 1))
		{
			cout << (array[i] + 1) << endl;
		}
	}
}
