/*
 * Christopher Holmes
 * ID: 002928626
 * Module 6 - Lab
 * 2/13/19
 * Abstract: Implement linear, iterativeBinary, and recursiveBinary search algorithms
 */

#include <iostream>
using namespace std;

bool linearSearch(int arr[], int searchTerm, int sizeOf)
{
	//Loop through each element of the array, returning true if term is found
	for(int i=0; i<sizeOf; i++)
	{
		if(arr[i]==searchTerm)
		{
			return true;
		}
	}
	return false;
}

bool iterBinarySearch(int arr[], int left, int right, int searchTerm)
{
	while(left <= right)
	{
		int middle = left + (right - left) / 2;

		//Check if middle equals search term
		if(arr[middle] == searchTerm)
		{
			return true;
		}

		//Check if searchTerm is greater, ignore left if it is
		if(arr[middle] < searchTerm)
		{
			left = middle + 1;
		}

		//Check if searchTerm is smaller, ignore right if it is
		else
		{
			right = middle - 1;
		}
	}

	//Term not found
	return false;
}

bool recursiveBinarySearch(int arr[], int left, int right, int searchTerm)
{
	if(right >= left)
	{
		int middle = left + (right - left) /2;

		//Check if middle equals search term
		if(arr[middle] == searchTerm)
		{
			return true;
		}

		//If element less than middle, has to be in left subset
		if(arr[middle] > searchTerm)
		{
			return recursiveBinarySearch(arr, left, middle - left, searchTerm);
		}

		//Element has to be in right subset if not in left subset
		else
		{
			return recursiveBinarySearch(arr, middle + left, right, searchTerm);
		}
	}

	//Search term not found
	return false;
}

int main()
{
	//Declare variables used by program
	int sorted[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	int input;
	int sizeOf = sizeof(sorted)/sizeof(sorted[0]);

	cout << "Enter search term: ";
	cin >> input;

	cout << endl << "***Searching using linearSearch***" << endl;
	//Call linearSearch
	(linearSearch(sorted, input, sizeOf) == 0)	? cout << "Search term " << input << " is not in array." << endl
												: cout << "Search term " << input << " is in the array." << endl;

	cout << endl << "***Searching using iterBinarySearch***" << endl;
	//Call iterBinarySearch
	(iterBinarySearch(sorted, 0, sizeOf - 1, input) == 0)	? cout << "Search term " << input << " is not in array." << endl
															: cout << "Search term " << input << " is in the array." << endl;

	cout << endl << "***Searching using recursiveBinarySearch***" << endl;
	//Call iterBinarySearch
	(recursiveBinarySearch(sorted, 0, sizeOf - 1, input) == 0)	? cout << "Search term " << input << " is not in array." << endl
																: cout << "Search term " << input << " is in the array." << endl;


}
