/*
 * Christopher Holmes
 * ID: 002928626
 * Module 5 - Program Assignment
 * 2/11/19
 * Abstract: Implement selectionSort to run in O(nk) and use O(nk) function to calculate median
 */

#include <iostream>
using namespace std;

//Simple swap function to swap two integers in an array
void swap(int *ap, int *bp)
{
	int temp = *ap;
	*ap = *bp;
	*bp = temp;
}

//Implementation of selection sort to run O(nk)
void selectionSort(int unsorted[], int arraySize, int k)
{
	//Minimum value in cycle
	int min;

	//Cycle through entire array once up till k
	for(int i=0; i < k; i++)
	{
		min = i;
		//Cycle through i+1 to end of array
		for(int j=i+1; j < arraySize; j++)
		{
			if(unsorted[j] < unsorted[min])
			{
				min = j;
			}
		}
		//Swap of smallest integer to sort array
		swap(&unsorted[min], &unsorted[i]);
	}
}

//Function to print the entire array that takes in an array and the size of the array
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
	//Declare variables and arrays for use in program
	int array[] = {4, 6, 8, 15, 20, 22, 10, 3, 9, 2};
	int arraySize = sizeof(array)/sizeof(array[0]);
	int middle = arraySize/2.0;
	//int k;

	//cout << "Enter k: " << endl;
	//cin >> k;

	//Display the array that was input
	cout << "Input Array: ";
	printArray(array, arraySize);

	//Read in a K value from user
	//cout << "k: " << k << endl;
	//selectionSort(array, arraySize, k);
	//printArray(array, k);

	//Call the selection sort with the middle number to make it O(nk) and display median
	selectionSort(array, arraySize, middle+1);
	if(arraySize%2) //Odd array size
	{
		cout << "Median: " << array[middle] << endl;
	}
	else //Even array size
	{
		cout << "Median: " << (array[middle]+array[middle-1])/2.0 << endl;
	}

}
