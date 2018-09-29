#include <iostream>
using namespace std;

void sort(int arr[], int);

int main()
{
    cout << "Enter amount of numbers" << endl;
    int amount = 0;
    cin >> amount;
    int* arr = new int[amount] {};

    cout << "Enter values of numbers" << endl;
    for (int i = 0; i < amount; i++)
    {
        cin >> arr[i];
    }

    sort(arr, amount);

    int maxRepeat = 0;
    for (int i = amount - 1; i > 0; i--)
    {
        if (arr[i] == arr[i - 1])
        {
            maxRepeat = arr[i];
            break;
        }
    }

    if (maxRepeat != 0)
    {
        cout << "Maximum repeating element is " << maxRepeat << endl;
    }
    else
    {
        cout << "There are no maximum repeating elements" << endl;
    }

    delete[] arr;
}

void sort(int arr[], int amount)
{
    for (int i = amount - 1; i > 1; i--)
    {
        for (int j = 0; j < i; j++)
        {
            if (arr[j] > arr[j + 1])
            {
                swap(arr[j], arr[j + 1]);
            }
        }
    }
}
