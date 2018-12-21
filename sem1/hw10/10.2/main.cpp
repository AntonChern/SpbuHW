#include <iostream>
#include <fstream>
using namespace std;

void rabinKarp(char *string, char *substring);
int hashChar(char *string);
int len(char *string);

const int argument = 2;
const int maxLength = 256;
const int module = 997;

int main()
{
    const char *nameOfFile = "file.txt";
    ifstream file(nameOfFile);
    char *string = new char[maxLength] {};
    file >> string;

    char *substring = new char[maxLength] {};
    file >> substring;
    file.close();

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
        result = ((result * argument) + string[i]) % module;
    }
    return result;
}

int len(char *string)
{
    int result = 0;
    for (int i = 0; string[i] != '\0' && string[i] != '\n'; i++)
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
    char *sample = new char[maxLength] {};
    int odd = 1;
    for (int i = 0; i < lengthOfSubstring - 1; i++)
    {
        odd *= argument;
    }
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
            hashSample = hashSample - string[i - 1] * odd;
            while (hashSample < 0)
            {
                hashSample += module;
            }
            hashSample = ((hashSample * argument) + string[lengthOfSubstring + i - 1]) % module;
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
