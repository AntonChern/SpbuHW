#include <iostream>
#include "string.h"
using namespace std;

struct String
{
    char *symbols;
    int capacity;
};

void displayString(String *string)
{
    cout << string->symbols;
}

void increaseCapacity(String *string, int length)
{
    if (string->capacity < length)
    {
        while (string->capacity < length)
        {
            string->capacity += 10;
        }
        if (string->symbols)
        {
            char *deletedSymbols = string->symbols;
            string->symbols = new char[string->capacity] {};
            int length = lengthString(string);
            for (int i = 0; i < length; i++)
            {
                string->symbols[i] = deletedSymbols[i];
            }
            delete[] deletedSymbols;
        }
    }
}

String *createString(char *symbols)
{
    String *string = new String {};
    string->capacity = 10;
    int length = 0;
    for (int i = 0; symbols[i] != '\0'; i++)
    {
        length++;
    }
    increaseCapacity(string, length);
    string->symbols = new char[string->capacity] {};
    for (int i = 0; symbols[i] != '\0'; i++)
    {
        string->symbols[i] = symbols[i];
    }
    return string;
}

void deleteString(String *string)
{
    delete[] string->symbols;
    delete string;
}

String *clone(String *string)
{
    String *newString = new String {};
    newString->capacity = string->capacity;
    newString->symbols = new char[newString->capacity] {};
    int length = lengthString(string);
    for (int i = 0; i < length; i++)
    {
        newString->symbols[i] = string->symbols[i];
    }
    return newString;
}

void concatenate(String *string, String *argument)
{
    int oldLength = lengthString(string);
    int newLength = lengthString(string) + lengthString(argument);
    increaseCapacity(string, newLength);
    for (int i = oldLength; i < newLength; i++)
    {
        string->symbols[i] = argument->symbols[i - oldLength];
    }
}

bool isEqual(String *first, String *second)
{
    if (lengthString(first) != lengthString(second))
    {
        return false;
    }
    int length = lengthString(first);
    for (int i = 0; i < length; i++)
    {
        if (first->symbols[i] != second->symbols[i])
        {
            return false;
        }
    }
    return true;
}

int lengthString(String *string)
{
    int result = 0;
    for (int i = 0; string->symbols[i] != '\0'; i++)
    {
        result++;
    }
    return result;
}

bool isEmpty(String *string)
{
    return !string->symbols[0];
}

String *selectSubstring(String *string, int firstIndex, int lastIndex)
{
    String *substring = new String {};
    substring->symbols = new char[string->capacity] {};
    for (int i = firstIndex; i <= lastIndex; i++)
    {
        substring->symbols[i - firstIndex] = string->symbols[i];
    }
    return substring;
}

char *convertToChar(String *string)
{
    char *result = new char[string->capacity] {};
    int length = lengthString(string);
    for (int i = 0; i < length; i++)
    {
        result[i] = string->symbols[i];
    }
    return result;
}
