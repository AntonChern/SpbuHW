#include <iostream>
using namespace std;

void fillArray(int *arr[], int);
void displayArray(int *arr[], int);

int main()
{
    cout << "Enter odd n" << endl;
    int amount = 0;
    cin >> amount;

    int **arr = new int*[amount] {};
    for (int i = 0; i < amount; i++)
    {
        arr[i] = new int[amount] {};
    }

    fillArray(arr, amount);
    cout << "Array:" << endl;
    displayArray(arr, amount);
    cout << "Output:" << endl;

    int count = 0;
    int block = 1;
    int step = 1;
    int column = amount / 2;
    int line = amount / 2;

    cout << arr[column][line] << " ";
    count++;
    while (count < amount * amount)
    {
        for (int i = 0; (i < block) && (count < amount * amount); i++)
        {
            line += step;
            cout << arr[column][line] << " ";
            count++;
        }
        for (int i = 0; (i < block) && (count < amount * amount); i++)
        {
            column += step;
            cout << arr[column][line] << " ";
            count++;
        }
        block++;
        step = -step;
    }

    for (int i = 0; i < amount; i++)
    {
        delete[] arr[i];
    }
    delete[] arr;
}

void fillArray(int *arr[], int amount)
{
    int select = 1;
    for (int i = 0; i < amount; i++)
    {
        for (int j = 0; j < amount; j++)
        {
            arr[i][j] = select;
            select++;
        }
    }
}

void displayArray(int *arr[], int amount)
{
    for (int i = 0; i < amount; i++)
    {
        for (int j = 0; j < amount; j++)
        {
            cout << arr[i][j] << " ";
        }
        cout << endl;
    }
}
