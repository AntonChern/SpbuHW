#include <iostream>
using namespace std;

void display(int, int n[]);

int main()
{
    cout << "Enter amount of number" << endl;
    int amount = 0;
    cin >> amount;
    int *arr = new int[amount] {};
    cout << "Enter item values" << endl;
    for (int i = 0; i < amount; i++)
    {
        cin >> arr[i];
    }

    cout << "Before - ";
    display(amount, arr);

    int current = 0;
    int selIndex = 0;
    for (int i = 2; i < amount; i += 2)
    {
        current = arr[i];
        for (selIndex = i - 2; selIndex >= 0 && arr[selIndex] > current; selIndex -= 2)
        {
            arr[selIndex + 2] = arr[selIndex];
        }
        arr[selIndex + 2] = current;
    }

    cout << "After  - ";
    display(amount, arr);
    delete[] arr;
}

void display(int length, int num[])
{
    for (int i = 0; i < length; i++)
    {
        cout << num[i] << " ";
    }
    cout << endl;
}
