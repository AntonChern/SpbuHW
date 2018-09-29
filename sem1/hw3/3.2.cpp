#include <iostream>
using namespace std;

void qsort(int, int, char num[]);
void declareString(char num[], int);

int main()
{
    cout << "Enter string length" << endl;
    int length = 0;
    cin >> length;

    cout << "Enter first string" << endl;
    char* str1 = new char[length] {};
    declareString(str1, length);

    cout << "Enter second string" << endl;
    char* str2 = new char[length] {};
    declareString(str2, length);

    bool isEqually = true;
    for (int i = 0; i < length; i++)
    {
        if (str1[i] != str2[i])
        {
            isEqually = false;
        }
    }

    if (isEqually)
    {
        cout << "The second string can be composed from the first";
    }
    else
    {
        cout << "The second string cannot be composed from the first";
    }

    delete[] str1;
    delete[] str2;
}

void qsort(int first, int last, char num[])
{
    if (first < last)
    {
        int length = last - first + 1;
        int select = first + length / 2;
        bool isSorted = false;
        while (!isSorted)
        {
            isSorted = true;
            for (int i = 0; i < select - first; i++)
            {
                if (num[first + i] > num[select])
                {
                    swap(num[first + i], num[select]);
                    select = first + i;
                    isSorted = false;
                    break;
                }
            }
            for (int i = 0; i < last - select; i++)
            {
                if (num[last - i] <= num[select])
                {
                    swap(num[last - i], num[select]);
                    select = last - i;
                    isSorted = false;
                    break;
                }
            }
        }
        qsort(first, select - 1, num);
        qsort(select + 1, last, num);
    }
}

void declareString(char str[], int length)
{
    for (int i = 0; i < length; i++)
    {
        cin >> str[i];
    }
    qsort(0, length - 1, str);
}
