#include <iostream>
using namespace std;

int fibonacciRecursive(int);
int fibonacciIterative(int);

int main()
{
    cout << "Enter Fibonacci number\n";
    int number = 0;
    cin >> number;

    cout << "The " << number << "th Fibonacci number is\n";
    cout << fibonacciRecursive(number) << "\n";
    cout << fibonacciIterative(number);
}

int fibonacciRecursive(int number)
{
    if (number > 2)
    {
        return fibonacciRecursive(number - 1) + fibonacciRecursive(number - 2);
    }
    return 1;
}

int fibonacciIterative(int number)
{
    if (number > 2)
    {
        int fibonacciNumber = 0;
        int last = 1;
        int penult = 1;
        for (int i = 2; i < number; i++)
        {
            fibonacciNumber = penult + last;
            penult = last;
            last = fibonacciNumber;
        }
        return fibonacciNumber;
    }
    return 1;
}
