#include <iostream>
using namespace std;

int main()
{
    int amount = 10;
    int* arr = new int[amount] {2, 4, 9, 3, 7, 5, 8, 1, 4, 6};

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

    for (int i = 0; i < amount; i++)
    {
        cout << arr[i] << " ";
    }
    cout << endl;

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

    delete arr;
}
