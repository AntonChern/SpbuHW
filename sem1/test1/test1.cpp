#include <iostream>
using namespace std;

int countSpases(char *str, int length);
void insertSpase(char *rStr, int rLength, int number);
void displayRStr(char *rStr, int rLength);

int main()
{
    cout << "Enter length of string" << endl;
    int length = 0;
    cin >> length;

    cout << "Enter string" << endl;
    char *str = new char[length] {};
    for (int i = 0; i <= length; i++)
    {
        char symbol = '\0';
        scanf("%c", &symbol);
        if (i > 0)
        {
            str[i - 1] = symbol;
        }
    }

    cout << "Enter length of required string" << endl;
    int rLength = 0;
    cin >> rLength;

    char *rStr = new char[rLength] {};
    for (int i = 0; i < length; i++)
    {
        rStr[i] = str[i];
    }

    int spases = countSpases(str, length);
    int reserveSpases = rLength - length;
    int barrier = length;

    while ((reserveSpases >= spases) && (spases != 0))
    {
        for (int i = 0; i < barrier; i++)
        {
            if (rStr[i] == ' ')
            {
                insertSpase(rStr, rLength, i);
                reserveSpases--;
                barrier++;
                int spasesBetwWords = 0;
                for (int j = i; rStr[j] == ' '; j++)
                {
                    spasesBetwWords++;
                }
                i += spasesBetwWords;
            }
        }
    }

    int counter = 1;
    for (int i = 0; (i < rLength) && (reserveSpases > 0); i++)
    {
        if ((rStr[i] == ' ') && (counter > 0))
        {
            insertSpase(rStr, rLength, i);
            reserveSpases--;
            counter *= -1;
        }
    }

    displayRStr(rStr, rLength);

    delete[] str;
    delete[] rStr;
}

int countSpases(char *str, int length)
{
    int result = 0;
    for (int i = 0; i < length; i++)
    {
        if (str[i] == ' ')
        {
            result++;
        }
    }
    return result;
}

void insertSpase(char *rStr, int rLength, int number)
{
    int spasesBetwWords = 0;
    for (int i = number; rStr[i] == ' '; i++)
    {
        spasesBetwWords++;
    }
    int current = rStr[number + spasesBetwWords];
    rStr[number + spasesBetwWords] = rStr[number];
    for (int i = rLength - 1; i >= number + spasesBetwWords; i--)
    {
        rStr[i] = rStr[i - 1];
    }
    rStr[number + spasesBetwWords + 1] = current;
}

void displayRStr(char *rStr, int rLength)
{
    for (int i = 0; i < rLength; i++)
    {
        cout << rStr[i];
    }
}

