#include <iostream>
#include <fstream>
#include <ctype.h>
using namespace std;

void nullExists(bool exists[]);
int value(int current);

int main()
{
    int numOfLetters = 26;
    bool exists[numOfLetters] = {};
    nullExists(exists);
    char nameOfFile[] = {"file.txt"};
    ifstream file(nameOfFile);
    char current = ' ';
    file.get(current);
    while (!file.eof())
    {
        if (isalpha(current))
        {
            if (!exists[value(current)])
            {
                exists[value(current)] = true;
                cout << current;
            }
            file.get(current);
        }
        else
        {
            nullExists(exists);
            while ((!isalpha(current)) && (!file.eof()))
            {
                file.get(current);
            }
            cout << ' ';
        }
    }
    file.close();
}

void nullExists(bool exists[])
{
    for (int i = 'a'; i <= 'z'; i++)
    {
        exists[value(i)] = false;
    }
}

int value(int current)
{
    return current - 'a';
}
