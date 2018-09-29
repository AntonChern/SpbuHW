#include <iostream>
#include <time.h>
using namespace std;

int pow(int, int);
int countNumOfBranches(int);
void displayArray(int, int n[]);

int main()
{
    cout << "Enter amount of numbers" << endl;
    int amount = 0;
    cin >> amount;
    int *num = new int[amount] {};

    srand(time(NULL));
    for (int i = 0; i < amount; i++)
    {
        num[i] = rand() % 10;
    }

    cout << "Before - ";
    displayArray(amount, num);

    int length = amount;
    int numOfBranches = countNumOfBranches(length);

    int leftBranchIndex = 0;
    int rightBranchIndex = 0;
    while (length > 1)
    {
        while (numOfBranches > 1)
        {
            for (int i = pow(2, (numOfBranches - 1) - 1) - 1; i < pow(2, numOfBranches - 1) - 1; i++)
            {
                leftBranchIndex = 2 * i + 1;
                rightBranchIndex = 2 * i + 2;
                if (rightBranchIndex == length)
                {
                    if (num[i] < num[leftBranchIndex])
                    {
                        swap(num[i], num[leftBranchIndex]);
                    }
                }
                else
                {
                    if ((leftBranchIndex < length) && (rightBranchIndex < length))
                    {
                        if (num[leftBranchIndex] < num[rightBranchIndex])
                        {
                            swap(num[leftBranchIndex], num[rightBranchIndex]);
                        }
                        if (num[i] < num[leftBranchIndex])
                        {
                            swap(num[i], num[leftBranchIndex]);
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

    delete[] num;
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
