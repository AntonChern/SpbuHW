#include <iostream>
using namespace std;

void convertToBinary(int number, int binNumber[]);
void convertToBinarySum(int binFirstNum[], int binSecondNum[], int binSum[]);
void displayBinNumber(int binNumber[]);
int convertToDecimal(int binNumber[]);

int const sizeInt = 32;

int main()
{
    cout << "Enter first number" << endl;
    int first = 0;
    int *binFirst = new int[sizeInt] {};
    cin >> first;

    cout << "Enter second number" << endl;
    int second = 0;
    int *binSecond = new int[sizeInt] {};
    cin >> second;

    int *binSum = new int[sizeInt] {};

    cout << "First:  ";
    convertToBinary(first, binFirst);
    displayBinNumber(binFirst);
    cout << endl;

    cout << "Second: ";
    convertToBinary(second, binSecond);
    displayBinNumber(binSecond);
    cout << endl;

    cout << "Sum:    ";
    convertToBinarySum(binFirst, binSecond, binSum);
    displayBinNumber(binSum);
    cout << ", decimal: " << convertToDecimal(binSum);

    delete[] binFirst;
    delete[] binSecond;
    delete[] binSum;
}

void convertToBinary(int number, int binNumber[])
{
    int bit = 0b1;
    for (int i = sizeInt - 1; i >= 0; i--)
    {
        binNumber[i] = ((number & bit) ? 1 : 0);
        bit <<= 1;
    }
}

void convertToBinarySum(int binFirstNum[], int binSecondNum[], int binSum[])
{
    int reserve = 0;
    for (int i = sizeInt - 1; i >= 0; i--)
    {
        int digitSum = binFirstNum[i] + binSecondNum[i] + reserve;
        if (digitSum > 1)
        {
            reserve = 1;
            digitSum -= 2;
        }
        else
        {
            reserve = 0;
        }
        binSum[i] = digitSum;
    }
}

void displayBinNumber(int binNumber[])
{
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

int convertToDecimal(int binNumber[])
{
    int result = 0;
    int counter = 1;
    for (int i = sizeInt - 1; i >= 0; i--)
    {
        result += counter * binNumber[i];
        counter *= 2;
    }
    return result;
}
