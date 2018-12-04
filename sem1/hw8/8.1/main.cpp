#include <iostream>
#include "hashtable.h"
using namespace std;

void printPassedList(HashTable *hashTable, int students);
void printSendDownList(HashTable *hashTable, int students);

int main()
{
    HashTable *hashTable = createHashTable();

    cout << "Enter number of students - ";
    int students = 0;
    cin >> students;

    cout << "Enter student number and number allowed to write off:" << endl;
    for (int i = 0; i < students; i++)
    {
        int number = 0;
        int givenWrite = 0;
        cin >> number;
        cin >> givenWrite;
        addRecord(hashTable, number, givenWrite);
    }
    cout << endl;

    printPassedList(hashTable, students);
    printSendDownList(hashTable, students);

    deleteHashTable(hashTable);
}

bool existsPassedStudent(HashTable *hashTable, int students)
{
    for (int i = 1; i <= students; i++)
    {
        int value = findValue(hashTable, i);
        if (value != -1)
        {
            return true;
        }
    }
    return false;
}

bool existsSendDownStudent(HashTable *hashTable, int students)
{
    for (int i = 1; i <= students; i++)
    {
        int value = findValue(hashTable, i);
        if (value == -1)
        {
            return true;
        }
    }
    return false;
}

void printPassedList(HashTable *hashTable, int students)
{
    if (existsPassedStudent(hashTable, students))
    {
        cout << "List of passed students:" << endl;
        for (int i = 1; i <= students; i++)
        {
            int value = findValue(hashTable, i);
            if (value != -1)
            {
                while (value != findValue(hashTable, value))
                {
                    value = findValue(hashTable, value);
                }
                cout << i << " => " << value << endl;
            }
        }
    }
    return;
}

void printSendDownList(HashTable *hashTable, int students)
{
    if (existsSendDownStudent(hashTable, students))
    {
        cout << "List of sended down students:" << endl;
        for (int i = 1; i <= students; i++)
        {
            int value = findValue(hashTable, i);
            if (value == -1)
            {
                cout << i << endl;
            }
        }
    }
    return;
}
