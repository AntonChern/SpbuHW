#include <iostream>
using namespace std;

int const offsetExponent = 1023;
int const bitsOfMantissa = 52;
unsigned long long int const maskNullExponent = 0x3FF0000000000000;

int main()
{
    cout << "Enter a number: ";
    double original = 0;
    cin >> original;
    cout << "Result: ";
    unsigned long long int *number = (unsigned long long int *)&original;

    unsigned long long int mask = 0x800FFFFFFFFFFFFF;
    unsigned long long int nullMantissa = ((*number & mask) + maskNullExponent);
    double *mantissa = (double *)&nullMantissa;
    cout << *mantissa << "*2^";

    mask = 0x7FF0000000000000;
    unsigned long long int transformedExponent = ((*number & mask) >> bitsOfMantissa) - offsetExponent;
    long long int *exponent = (long long int *)&transformedExponent;
    cout << *exponent;
}
