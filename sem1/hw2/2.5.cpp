#include <iostream>
using namespace std;

int pow(int, int);
int countNumOfBranches(int);
void displayArray(int, int n[]);

int main()
{
    int amount = 10;
    int *num = new int[amount] {1, 6, 4, 3, 0, 2, 5, 8, 7, 9};

    cout << "Before - ";
    displayArray(amount, num);

    int length = amount;
    int numOfBranches = countNumOfBranches(length);

    while (length > 1)
    {
        while (numOfBranches > 1)
        {
            for (int i = pow(2, (numOfBranches - 1) - 1) - 1; i < pow(2, numOfBranches - 1) - 1; i++)
            {
                if (2 * i + 2 == length)
                {
                    if (num[i] < num[2 * i + 1])
                    {
                        swap(num[i], num[2 * i + 1]);
                    }
                }
                else
                {
                    if ((2 * i + 1 < length) && (2 * i + 2 < length))
                    {
                        if (num[2 * i + 1] < num[2 * i + 2])
                        {
                            swap(num[2 * i + 1], num[2 * i + 2]);
                        }
                        if (num[i] < num[2 * i + 1])
                        {
                            swap(num[i], num[2 * i + 1]);
                        }
                    }
                }
            }
            numOfBranches--;
        }
        swap(num[0], num[length - 1]);
        length--;
        numOfBranches = countNumOfBranches(length);
    }

    cout << "After  - ";
    displayArray(amount, num);

    delete num;
}

int pow(int baseNum, int exponent)
{
    int composition = 1;
    for (int i = 1; i <= exponent; i++)
    {
        composition *= baseNum;
    }
    return composition;
}

int countNumOfBranches(int amount)
{
    int length = amount;
    int numOfBranches = 0;
    for (int i = 0; length > 0; i++)
    {
        length -= pow(2, i);
        numOfBranches++;
    }
    return numOfBranches;
}

void displayArray(int amount, int num[])
{
    for (int i = 0; i < amount; i++)
    {
        cout << num[i] << " ";
    }
    cout << endl;
}
