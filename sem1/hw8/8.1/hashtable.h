#pragma once

struct HashTable;

HashTable *createHashTable();
void deleteHashTable(HashTable *hashTable);

int hashInt(int number);
void addRecord(HashTable *hashTable, int number, int value);
int findValue(HashTable *hashTable, int number);
