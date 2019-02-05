#include <iostream>
using namespace std;

void swap(int *ap, int *bp)
{
	int temp = *ap;
	*ap = *bp;
	*bp = temp;
}

void selectionSort(int unsorted[], int arraySize)
{
	int min;
	//int arraySize = sizeof(unsorted)/sizeof(unsorted[0]);
	for(int i=0; i < arraySize-1; i++)
	{
		min = i;
		for(int j=i+1; j < arraySize; j++)
		{
			if(unsorted[j] < unsorted[min])
			{
				min = j;
			}
		}
		swap(&unsorted[min], &unsorted[i]);
	}
}

void printArray(int array[], int arraySize)
{
	for(int i=0; i<arraySize; i++)
	{
		cout << array[i] << " ";
	}
	cout << endl;
}

int main()
{
	int array[] = {4, 6, 8, 15, 20, 22, 10, 3, 9, 2};
	int arraySize = sizeof(array)/sizeof(array[0]);
	selectionSort(array, arraySize);
	cout << "Sorted Array: " << endl;
	printArray(array, arraySize);
}
