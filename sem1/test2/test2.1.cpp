#include <iostream>
using namespace std;

int countFibonacciNum(int index);

int main()
{
    cout << "Enter index of Fibonacci number\n";
    int index = 0;
    cin >> index;

    cout << "The " << index << "th Fibonacci number is ";
    cout << countFibonacciNum(index);
}

int countFibonacciNum(int index)
{
    if (index > 2)
    {
        int fibonacciNumber = 0;
        int last = 1;
        int penult = 1;
        for (int i = 2; i < index; i++)
        {
            fibonacciNumber = penult + last;
            penult = last;
            last = fibonacciNumber;
        }
        return fibonacciNumber;
    }
    return 1;
}
