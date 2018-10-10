#include <iostream>
using namespace std;

int main()
{
    cout << "Enter the base number" << endl;
    int baseNum;
    cin >> baseNum;

    cout << "Enter exponent" << endl;
    int exponent;
    cin >> exponent;

    int dispExp = exponent;
    int resExponent = 0;
    int degree = 0;
    int composition = 1;

    if (exponent < 0)
    {
        exponent = - exponent;
    }
    int i = 0;
    while (exponent != 0)
    {
        resExponent = 0;
        i = 1;
        while (exponent >= i * 2)
        {
            resExponent++;
            i *= 2;
        }
        exponent -= i;

        degree = baseNum;
        for (int i = 0; i < resExponent; i++)
        {
            degree *= degree;
        }
        composition *= degree;
    }
    cout << baseNum << "^(" << dispExp << ") = ";
    if (dispExp < 0)
    {
        cout << "1/";
    }
    cout << composition;
}
