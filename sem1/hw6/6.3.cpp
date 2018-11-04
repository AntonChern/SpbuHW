#include <iostream>
using namespace std;

int numOfDigits(int number);
int absolute(int number);

int main()
{
    cout << "Enter the largest degree: ";
    int maxDegree = 0;
    cin >> maxDegree;

    cout << "Enter coefficients: ";
    int *coef = new int[maxDegree + 1] {};
    for (int i = 0; i <= maxDegree; i++)
    {
        cin >> coef[i];
    }

    bool existsMaxDeg = false;
    for (int i = 0; i <= maxDegree; i++)
    {
        int degree = maxDegree - i;
        if (coef[i] != 0)
        {
            if ((coef[i] < 0) || (existsMaxDeg))
            {
                cout << "  ";
            }
            if (absolute(coef[i]) > 1)
            {
                int digits = numOfDigits(coef[i]);
                for (int j = 0; j < digits; j++)
                {
                    cout << " ";
                }
            }
            cout << " ";
            if (degree > 1)
            {
                cout << degree;
            }
            existsMaxDeg = true;
        }
    }
    cout << endl;

    existsMaxDeg = false;
    for (int i = 0; i <= maxDegree; i++)
    {
        int degree = maxDegree - i;
        if (coef[i] != 0)
        {
            if (coef[i] < 0)
            {
                cout << "- ";
            }
            else
            {
                if (existsMaxDeg)
                {
                    cout << "+ ";
                }
            }
            int absCoef = absolute(coef[i]);
            if ((absCoef > 1) || (degree == 0))
            {
                cout << absCoef;
            }
            if (degree > 0)
            {
                cout << "x";
            }
            int digits = numOfDigits(degree);
            for (int j = 0; j < digits; j++)
            {
                cout << " ";
            }
            existsMaxDeg = true;
        }
    }
    delete[] coef;
}

int numOfDigits(int number)
{
    int result = 0;
    int absNumber = absolute(number);
    while (absNumber > 0)
    {
        result++;
        absNumber /= 10;
    }
    return result;
}

int absolute(int number)
{
    if (number < 0)
    {
        int reverse = 0xFFFFFFFF;
        return (number ^ reverse) + 1;
    }
    return number;
}
