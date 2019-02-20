/*
 * Christopher Holmes
 * ID: 002928626
 * Module 6 - Homework
 * 2/13/19
 * Abstract:
 */

#include <iostream>
using namespace std;

/* Function to print an array */
void printArray(int arr[], int size)
{
    int i;
    for (i=0; i < size; i++)
        printf("%d ", arr[i]);
    printf("\n");
}

void swap(int arr[], int right, int left)
{
	int temp = arr[left];
	arr[left] = arr[right];
	arr[right] = temp;
}

int medianOfThree(int arr[], int left, int right)
{
	int mid = (left + right)/2;
	if (arr[right-1] < arr[left])
	    swap(arr, left, right-1);
	if (arr[mid] < arr[left])
		swap(arr, mid, left);
	if (arr[right-1] < arr[mid])
		swap(arr, right-1, mid);

	printArray(arr, 10);
	return mid;
}

int split(int arr[], int first, int last)
{
	int mid = (first + last)/2;
	int left = first;
	int	right = last-1;

	if (arr[right] < arr[left])
		swap(arr, left, right);
	if (arr[mid] < arr[left])
		swap(arr, mid, left);
	if (arr[right] < arr[mid])
		swap(arr, right, mid);

	int pivot = arr[mid];



	while(left < right)
	{
		while(pivot < arr[right])
		{
			right--;
		}
		while(left < right && (arr[left] < pivot || arr[left] == pivot))
		{
			left++;
		}
		if (left < right)
		{
			swap(arr[left], arr[right]);
		}
	}

	//int pos = right;
	//arr[first] = arr[pos];
	//arr[pos] = pivot;
	return pivot;
}





int main()
{
	int array[] = {45, 20, 50, 30, 80, 10, 60, 70, 40, 90};
	int arraySize = sizeof(array)/sizeof(array[0]);
	printArray(array, arraySize);
	int pos = split(array, 0, arraySize);
	printArray(array, arraySize);
	//cout << array[0] << " " << array[arraySize-1] << endl;
	//int median = medianOfThree(array, 0, arraySize);
	//printArray(array, arraySize);

}
