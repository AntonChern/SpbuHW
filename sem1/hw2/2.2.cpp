#include <iostream>
using namespace std;

void displayAddends(int, int n[]);

int main()
{
    cout << "Enter N\n";
    int number = 0;
    cin >> number;

    int *addend = new int[number] {};

    addend[number - 1] = number;

    int select = 2;
    int sum = 0;
    int sumFirstToPenult = 0;
    cout << "This number is represented as a sum of natural addends as:" << endl;
    while (select <= number)
    {
        sum = 0;
        for (int i = number - select; i < number; i++)
        {
            sum += addend[i];
        }
        if ((addend[number - select] + 1) * select <= sum)
        {
            addend[number - select]++;
            for (int i = select - 1; i > 1; i--)
            {
                addend[number - i] = addend[number - select];
            }

            sumFirstToPenult = 0;
            for (int i = 0; i < number - 1; i++)
            {
                sumFirstToPenult += addend[i];
            }

            addend[number - 1] = number - sumFirstToPenult;
            select = 2;
            displayAddends(number, addend);
        }
        else
        {
            select++;
        }
    }
    delete[] addend;
}

void displayAddends(int number, int addend[])
{
    for (int i = 0; i < number - 1; i++)
    {
        if (addend[i] != 0)
        {
            cout << addend[i] << "+";
        }
    }
    cout << addend[number - 1] << endl;
}

