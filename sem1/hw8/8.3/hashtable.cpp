#include <iostream>
#include "hashtable.h"
#include "string.h"
using namespace std;

const int maxLength = 256;

struct HashTableElement
{
    String *string;
    int amount;
    int samples;
};

struct HashTable
{
    int size;
    int capacity;
    HashTableElement **buckets;
};


void displayHashTable(HashTable *hashTable)
{
    cout << "Capacity = " << hashTable->capacity << endl;
    cout << "Size = " << hashTable->size << endl;
    for (int i = 0; i < hashTable->capacity; i++)
    {
        cout << i << ". ";
        if (!isEmpty(hashTable->buckets[i]->string))
        {
            displayString(hashTable->buckets[i]->string);
            cout << " " << hashTable->buckets[i]->amount;
            cout << " " << hashTable->buckets[i]->samples;
        }
        else
        {
            cout << " - ";
        }
        cout << endl;
    }
    cout << endl;
}


void increaseCapacity(HashTable *&hashTable)
{
    if (hashTable->size == hashTable->capacity)
    {
        HashTable *newHashTable = createHashTable(hashTable->capacity * 2);
        for (int i = 0; i < hashTable->capacity; i++)
        {
            if (!isEmpty(hashTable->buckets[i]->string))
            {
                char *symbols = convertToChar(hashTable->buckets[i]->string);
                char newSymbols[maxLength] = {};
                for (int j = 0; symbols[j] != '\0'; j++)
                {
                    newSymbols[j] = symbols[j];
                }
                for (int j = 0; j < hashTable->buckets[i]->amount; j++)
                {
                    addRecord(newHashTable, newSymbols);
                }
            }
        }
        HashTable *deletedHashTable = hashTable;
        hashTable = newHashTable;
        deleteHashTable(deletedHashTable);
    }
}

HashTable *createHashTable(int capacity)
{
    HashTable *hashTable = new HashTable {0, capacity, nullptr};
    hashTable->buckets = new HashTableElement*[hashTable->capacity] {nullptr};
    for (int i = 0; i < hashTable->capacity; i++)
    {
        hashTable->buckets[i] = new HashTableElement {nullptr, 0, 0};
        char emptySymbol[maxLength] = {};
        hashTable->buckets[i]->string = createString(emptySymbol);
    }
    return hashTable;
}

int hashInt(HashTable *hashTable, char symbols[])
{
    int result = 0;
    for (int i = 0; symbols[i] != '\0'; i++)
    {
        result = (result * 10 + symbols[i]) % hashTable->capacity;
    }
    return result;
}

void addRecord(HashTable *&hashTable, char symbols[])
{
    int key = hashInt(hashTable, symbols);
    int samples = 0;
    String *argument = createString(symbols);
    if (!isEmpty(hashTable->buckets[key]->string))
    {
        if (!isEqual(hashTable->buckets[key]->string, argument))
        {
            int step = 1;
            do
            {
                key = (key + step * step) % hashTable->capacity;
                step++;
                samples++;
            }
            while (!isEmpty(hashTable->buckets[key]->string));
        }
        else
        {
            hashTable->buckets[key]->amount++;
            deleteString(argument);
            samples++;
            hashTable->buckets[key]->samples = samples;
            increaseCapacity(hashTable);
            return;
        }
    }
    concatenate(hashTable->buckets[key]->string, argument);
    hashTable->buckets[key]->amount++;
    deleteString(argument);
    hashTable->size++;
    samples++;
    hashTable->buckets[key]->samples = samples;
    increaseCapacity(hashTable);
}

void deleteHashTable(HashTable *hashTable)
{
    for (int i = 0; i < hashTable->capacity; i++)
    {
        deleteString(hashTable->buckets[i]->string);
        delete hashTable->buckets[i];
    }
    delete[] hashTable->buckets;
    delete hashTable;
}

double loadFactor(HashTable *hashTable)
{
    return (double)hashTable->size / hashTable->capacity;
}

void displayWordsAndAmounts(HashTable *hashTable)
{
    cout << "Words and their amounts:" << endl;
    for (int i = 0; i < hashTable->capacity; i++)
    {
        if (!isEmpty(hashTable->buckets[i]->string))
        {
            displayString(hashTable->buckets[i]->string);
            cout << " => " << hashTable->buckets[i]->amount << endl;
        }
    }
}

void displaySamplesAndCells(HashTable *hashTable)
{
    String *maxSampleString = nullptr;
    int maxSample = 0;
    int sumSamples = 0;
    int numOfSamples = 0;
    int numOfEmptyCells = 0;
    int numOfWords = 0;
    for (int i = 0; i < hashTable->capacity; i++)
    {
        if (!isEmpty(hashTable->buckets[i]->string))
        {
            sumSamples += hashTable->buckets[i]->samples;
            if (hashTable->buckets[i]->samples > maxSample)
            {
                maxSample = hashTable->buckets[i]->samples;
                maxSampleString = hashTable->buckets[i]->string;
            }
            numOfSamples++;
            numOfWords += hashTable->buckets[i]->amount;
        }
        else
        {
            numOfEmptyCells++;
        }
    }

    cout << endl << "Maximum sample - " << maxSample << endl;
    cout << "Values with the maximum number of samples:" << endl;
    char *symbols = convertToChar(maxSampleString);
    char maxSymbols[maxLength] = {};
    for (int i = 0; symbols[i] != '\0'; i++)
    {
        maxSymbols[i] = symbols[i];
    }
    int maxKey = hashInt(hashTable, maxSymbols);
    for (int i = 0; i < maxSample - 1; i++)
    {
        maxKey = (maxKey + i * i) % hashTable->capacity;
        cout << i + 1 << ". ";
        displayString(hashTable->buckets[maxKey]->string);
        cout << endl;
    }

    cout << endl << "Average sample - " << (double)sumSamples / numOfSamples << endl;
    cout << "Number of empty cells - " << numOfEmptyCells << endl;
    cout << "Number of words - " << numOfWords << endl;
}
