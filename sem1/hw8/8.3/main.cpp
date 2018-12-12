#include <iostream>
#include <fstream>
#include "hashtable.h"
using namespace std;

void nullWord(char *word);

const int maxLength = 256;

int main()
{
    const char *nameOfFile = "file.txt";
    ifstream file(nameOfFile);
    HashTable *hashTable = createHashTable(2);
    char *word = new char[maxLength] {};
    while (!file.eof())
    {
        nullWord(word);
        char symbol = '\0';
        while (!isalpha(symbol) && !file.eof())
        {
            file.get(symbol);
        }
        if (file.eof())
        {
            break;
        }
        for (int i = 0; isalpha(symbol) && !file.eof(); i++)
        {
            word[i] = symbol;
            file.get(symbol);
        }
        addRecord(hashTable, word);
    }
    delete[] word;
    displayWordsAndAmounts(hashTable);
    cout << endl;
    cout << "Load factor = " << loadFactor(hashTable) << endl;
    displaySamplesAndCells(hashTable);
    deleteHashTable(hashTable);
    file.close();
}

void nullWord(char *word)
{
    for (int i = 0; word[i] != '\0'; i++)
    {
        word[i] = '\0';
    }
}
