/*
 * Christopher Holmes
 * ID: 002928626
 * Module 5 - Program Assignment
 * 2/11/19
 * Abstract: Implement the selection sort algorithm to sort an array passed in
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

//Implementation of selection sort
void selectionSort(int unsorted[], int arraySize)
{
	//Min number in cycle
	int min;
	//For loop to cycle through entire array
	for(int i=0; i < arraySize-1; i++)
	{
		min = i;
		//For loop to cycle through i+1 to end of array
		for(int j=i+1; j < arraySize; j++)
		{
			//If current element in loop is less than current minimum, chan minmum
			if(unsorted[j] < unsorted[min])
			{
				min = j;
			}
		}
		//Swap current with smallest
		swap(&unsorted[min], &unsorted[i]);
	}
}

//Print array function
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
	cout << "Input Array: " << endl;
	printArray(array, arraySize);
	selectionSort(array, arraySize);
	cout << "Sorted Array: " << endl;
	printArray(array, arraySize);
}
