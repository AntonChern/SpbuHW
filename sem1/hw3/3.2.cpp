#include <iostream>
using namespace std;

int main()
{
    cout << "Enter string length" << endl;
    int length = 0;
    cin >> length;

    cout << "Enter first string" << endl;
    char* str1 = new char[length] {};
    int count1[26] = {};
    for (int i = 0; i < length; i++)
    {
        cin >> str1[i];
        count1[str1[i] - 97]++;
    }

    cout << "Enter second string" << endl;
    char* str2 = new char[length] {};
    int count2[26] = {};
    for (int i = 0; i < length; i++)
    {
        cin >> str2[i];
        count2[str2[i] - 97]++;
    }

    bool isEqually = true;
    for (int i = 0; i < 26; i++)
    {
        if (count1[i] != count2[i])
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

    delete str1;
    delete str2;
}
