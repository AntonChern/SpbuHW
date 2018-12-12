#include <iostream>
using namespace std;

void rabinKarp(char *string, char *substring);
int hashChar(char *string);
int len(char *string);

const int argument = 2;
const int maxLength = 256;
const int module = 997;

int main()
{
    cout << "Enter string: ";
    char *string = new char[maxLength] {};
    gets(string);

    cout << "Enter substring: ";
    char *substring = new char[maxLength] {};
    gets(substring);

    cout << endl << "Entry indexes:" << endl;
    rabinKarp(string, substring);

    delete[] string;
    delete[] substring;
}

int hashChar(char *string)
{
    int result = 0;
    int lengthOfString = len(string);
    for (int i = 0; i < lengthOfString; i++)
    {
        result = (result * argument + string[i]) % module;
    }
    return result;
}

int len(char *string)
{
    int result = 0;
    for (int i = 0; string[i] != '\0'; i++)
    {
        result++;
    }
    return result;
}

void rabinKarp(char *string, char *substring)
{
    int lengthOfString = len(string);
    int lengthOfSubstring = len(substring);
    int hashSubstring = hashChar(substring);
    int odd = 1;
    for (int i = 0; i < lengthOfSubstring - 1; i++)
    {
        odd *= argument;
    }
    char *sample = new char[maxLength] {};
    for (int i = 0; i < lengthOfSubstring; i++)
    {
        sample[i] = string[i];
    }
    int hashSample = hashChar(sample);
    bool exists = false;
    for (int i = 0; i < lengthOfString - lengthOfSubstring + 1; i++)
    {
        if (i != 0)
        {
            hashSample = (((hashSample - string[i - 1] * odd) % module + module) * argument + string[i + lengthOfSubstring - 1]) % module;
        }
        if (hashSample == hashSubstring)
        {
            exists = true;
            cout << i << endl;
        }
    }
    if (!exists)
    {
        cout << "Not found";
    }
    delete[] sample;
}
