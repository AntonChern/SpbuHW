#include <iostream>
using namespace std;

int main()
{
    cout << "Enter n" << endl;
    int n = 0;
    cin >> n;

    int firstNumerator = 0;
    int firstDenominator = 1;

    int secondNumerator = 1;
    int secondDenominator = n;

    int thirdNumerator = secondNumerator;
    int thirdDenominator = secondDenominator;
    int coef = 0;

    cout << "All simple fractions between 0 and 1, whose denominators are at most " << n << ":" << endl;
    while ((thirdNumerator != 1) || (thirdDenominator != 1))
    {
        cout << thirdNumerator << "/" << thirdDenominator << " ";
        coef = (n + firstDenominator) / secondDenominator;

        thirdNumerator = coef * secondNumerator - firstNumerator;
        thirdDenominator = coef * secondDenominator - firstDenominator;

        firstNumerator = secondNumerator;
        firstDenominator = secondDenominator;

        secondNumerator = thirdNumerator;
        secondDenominator = thirdDenominator;
    }
}
