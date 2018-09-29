#include <iostream>
using namespace std;


int main()
{
    cout << "Enter number" << endl;
    int number = 0;
    cin >> number;

    int reserveNumber = number;
    int numOfDigits = 0;
    while (reserveNumber > 0)
    {
        numOfDigits++;
        reserveNumber /= 10;
    }

    int *digit = new int[numOfDigits] {};

    reserveNumber = number;
    for (int i = 0; i < numOfDigits; i++)
    {
        digit[i] = reserveNumber % 10;
        reserveNumber /= 10;
    }

    for (int i = 0; i < numOfDigits; i++)
    {
        for (int j = 0; j < numOfDigits - 1; j++)
        {
            if (digit[j] > digit[j + 1])
            {
                swap(digit[j], digit[j + 1]);
            }
        }
    }

    int numOfMinPos = 0;
    for (int i = 0; i < numOfDigits; i++)
    {
        if (digit[i] != 0)
        {
            numOfMinPos = i;
            break;
        }
    }

    swap(digit[0], digit[numOfMinPos]);

    cout << "Minimum number made up of these digits = ";
    for (int i = 0; i < numOfDigits; i++)
    {
        cout << digit[i];
    }

    delete digit;
}
