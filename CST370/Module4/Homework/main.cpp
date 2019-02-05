#include <iostream>
using namespace std;

unsigned f(int n)
{
	if(n<0)
		return f(-n);
	if(n<10)
		return n;
	return f(n/10);
}

int recurs(int n)
{
	if(n==1)
	{
		return 0;
	}
	else
	{
		return recurs(n-1) + 5;
	}
}

int main()
{
	cout << f(0) << endl;
	cout << f(1) << endl;
	cout << f(5) << endl;
	cout << f(197) << endl;
	cout << f(59345) << endl;
}
