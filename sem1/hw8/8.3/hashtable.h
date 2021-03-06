#pragma once

struct HashTable;

HashTable *createHashTable(int capacity);
void deleteHashTable(HashTable *hashTable);
void displayHashTable(HashTable *hashTable);
void increaseCapacity(HashTable *&hashTable);

int hashInt(HashTable *hashTable, char *symbols);
void addRecord(HashTable *&hashTable, char *symbols);
double loadFactor(HashTable *hashTable);
bool exists(HashTable *hashTable, char *symbols);

void displayWordsAndAmounts(HashTable *hashTable);
void displaySamplesAndCells(HashTable *hashTable);
