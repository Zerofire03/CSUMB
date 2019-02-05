#include <iostream>
using namespace std;

void swap(int *ap, int *bp)
{
	int temp = *ap;
	*ap = *bp;
	*bp = temp;
}

void selectionSort(int unsorted[], int arraySize, int k)
{
	int min;
	//int arraySize = sizeof(unsorted)/sizeof(unsorted[0]);
	for(int i=0; i < k; i++)
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
	int middle = arraySize/2.0;

	//cout << "Middle: " << middle << endl;
	//cout << "arraySize % 2: " << arraySize%2 << endl;
	if(arraySize%2) //Odd array size
	{
		selectionSort(array, arraySize, middle+1);
		//cout << "Partial Sort: " << endl;
		//printArray(array, arraySize);
		cout << "Median: " << array[middle] << endl;
	}
	else //Even array size
	{
		selectionSort(array, arraySize, middle+1);
		//cout << "Partial Sort: " << endl;
		//printArray(array, arraySize);
		//cout << "array[middle]: " << array[middle] << " array[middle-1]: " << array[middle-1] << endl;
		cout << "Median: " << (array[middle]+array[middle-1])/2.0 << endl;
	}
	//selectionSort(array, arraySize, arraySize);
	//cout << "Sorted Array: " << endl;
	//printArray(array, arraySize);
}
