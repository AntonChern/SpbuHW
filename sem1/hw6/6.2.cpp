#include <iostream>
using namespace std;

void convertToBinary(int number);

int const sizeInt = 32;

int main()
{
    cout << "Enter first number" << endl;
    int first = 0;
    cin >> first;

    cout << "Enter second number" << endl;
    int second = 0;
    cin >> second;

    cout << "First:  ";
    convertToBinary(first);
    cout << endl;

    cout << "Second: ";
    convertToBinary(second);
    cout << endl;

    cout << "Sum:    ";
    convertToBinary(first + second);
    cout << ", decimal: " << first + second <<endl;
}

void convertToBinary(int number)
{
    int bit = 0b1;
    int binNumber[sizeInt] = {};
    for (int i = sizeInt - 1; i >= 0; i--)
    {
        binNumber[i] = ((number & bit) ? 1 : 0);
        bit <<= 1;
    }
    bool isValid = false;
    for (int i = 0; i < sizeInt; i++)
    {
        if ((binNumber[i] != 0) || (isValid))
        {
            cout << binNumber[i];
            isValid = true;
        }
    }
}
