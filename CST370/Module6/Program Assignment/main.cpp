/*
 * Christopher Holmes
 * ID: 002928626
 * Module 6 - Program Assignment
 * 2/17/19
 * Abstract: Implement coolSort
 */

#include <iostream>
#include <vector>
using namespace std;

void insertionSort(int arr[], int n)
{
	//Variable deceleration
   int i, key, j;
   //For loop to cycle through entire array
   for (i = 1; i < n; i++)
   {
       key = arr[i];
       j = i-1;

       /* Move elements of arr[0..i-1], that are
          greater than key, to one position ahead
          of their current position */
       while (j >= 0 && arr[j] > key)
       {
           arr[j+1] = arr[j];
           j = j-1;
       }
       arr[j+1] = key;
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

void coolSort(int arr[], int numberSequence[], int arraySizeA, int arraySizeH)
{
	//For loop to loop through each value in the H array
	for(int k=0; k<arraySizeH; k++)
	{
		//Declare vector to store all sub arrays into for easier manipulation
		vector<int> tempArrayStorage;
		//For loop to cycle through each the entire original array
		for(int i=0; i<numberSequence[k]; i++)
		{
			//Vector to store H size array into
			vector<int> tempVector;
			for(int j=i; j<arraySizeA; j+=numberSequence[k])
			{
				//Store each element into the tempVector based on the H value
				tempVector.push_back(arr[j]);
			}
			//Sort the H size array
			insertionSort(&tempVector[0], tempVector.size());
			//Insert the subarray that's sorted into tempArrayStorage
			tempArrayStorage.insert(tempArrayStorage.end(), tempVector.begin(), tempVector.end());
		}
		//Copy the data back into the original array that we started with
		copy(tempArrayStorage.begin(), tempArrayStorage.end(), arr);
	}
}

int main()
{
	//Test case 1
	/*
	int a[] = {62, 83, 18, 53, 07, 17, 95, 86, 47, 69, 25, 28};
	int h[] = {5, 3, 1};
	int arraySizeA = sizeof(a)/sizeof(a[0]);
	int arraySizeH = sizeof(h)/sizeof(h[0]);
	cout << "Test Case 1: ";
	coolSort(a, h, arraySizeA, arraySizeH);
	printArray(a, arraySizeA);
	*/


	//Test case 2
	/*
	int a[] = {2, 5, 6, 4, 10, 9, 8, 1, 10, 5};
	int h[] = {5, 3, 1};
	int arraySizeA = sizeof(a)/sizeof(a[0]);
	int arraySizeH = sizeof(h)/sizeof(h[0]);
	cout << "Test Case 2: ";
	coolSort(a, h, arraySizeA, arraySizeH);
	printArray(a, arraySizeA);
	*/

	//Test case 3

	int a[] = {2, 5, 9, 4, 10, 7, 8, 1, 11, 5};
	int h[] = {5, 2, 1};
	int arraySizeA = sizeof(a)/sizeof(a[0]);
	int arraySizeH = sizeof(h)/sizeof(h[0]);
	cout << "Test Case 3: ";
	coolSort(a, h, arraySizeA, arraySizeH);
	printArray(a, arraySizeA);




}
