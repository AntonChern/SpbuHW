#include <iostream>
#include <fstream>
#include "hashtable.h"
using namespace std;

const int maxLength = 256;

int main()
{
    const char *nameOfFile = "file.txt";
    ifstream file(nameOfFile);
    HashTable *hashTable = createHashTable(2);
    while (!file.eof())
    {
        char *word = new char[maxLength] {};
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
        delete[] word;
    }
    displayWordsAndAmounts(hashTable);
    cout << endl;
    cout << "Load factor = " << loadFactor(hashTable) << endl;
    displaySamplesAndCells(hashTable);
    deleteHashTable(hashTable);
    file.close();
}
