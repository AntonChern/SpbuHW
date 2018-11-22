#include <iostream>
#include "hashtable.h"
#include "list.h"
using namespace std;

const int capacity = 29;

struct HashTable
{
    int size;
    int capacity;
    List **buckets;
};

HashTable *createHashTable()
{
    HashTable *hashTable = new HashTable {0, capacity, nullptr};
    hashTable->buckets = new List*[capacity] {};
    for (int i = 0; i < capacity; i++)
    {
        hashTable->buckets[i] = createList();
    }
    return hashTable;
}

int hashInt(int number)
{
    int result = number;
    result += ~(result << 16);
    result ^=  (result >>  5);
    result +=  (result <<  3);
    result ^=  (result >> 13);
    result += ~(result <<  9);
    result ^=  (result >> 17);
    return result % capacity;
}

void addRecord(HashTable *hashTable, int number, int value)
{
    int key = hashInt(number);
    addElement(hashTable->buckets[key], number, value);
    hashTable->size++;
}

int findValue(HashTable *hashTable, int number)
{
    int key = hashInt(number);
    return findElement(hashTable->buckets[key], number);
}

void deleteHashTable(HashTable *hashTable)
{
    for (int i = 0; i < capacity; i++)
    {
        deleteList(hashTable->buckets[i]);
    }
    delete[] hashTable->buckets;
    delete hashTable;
}
