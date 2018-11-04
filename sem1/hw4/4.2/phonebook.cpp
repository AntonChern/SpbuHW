#include <iostream>
#include <fstream>
#include "phonebook.h"
using namespace std;

struct ElementPhoneBook
{
    char *name;
    char *number;
    bool saved;
    ElementPhoneBook *next;
};

struct PhoneBook
{
    ElementPhoneBook *first;
};

PhoneBook *createPhoneBook()
{
    return new PhoneBook {nullptr};
}

void addRecord(PhoneBook *phoneBook, char *name, char *number, bool saved)
{
    ElementPhoneBook *newElement = new ElementPhoneBook {name, number, saved, phoneBook->first};
    phoneBook->first = newElement;
}

void findName(PhoneBook *phoneBook, char *number)
{
    ElementPhoneBook *current = phoneBook->first;
    bool exists = false;
    while (current)
    {
        if (isEqual(current->number, number))
        {
            cout << current->name << ' ';
            exists = true;
        }
        current = current->next;
    }
    if (!exists)
    {
        cout << "No records found";
    }
}

void findNumber(PhoneBook *phoneBook, char *name)
{
    ElementPhoneBook *current = phoneBook->first;
    bool exists = false;
    while (current)
    {
        if (isEqual(current->name, name))
        {
            cout << current->number << ' ';
            exists = true;
        }
        current = current->next;
    }
    if (!exists)
    {
        cout << "No records found";
    }
}

void copyFromFile(PhoneBook *phoneBook, char nameFile[])
{
    ifstream file(nameFile);
    char symbol = ' ';
    file.get(symbol);
    while (!file.eof())
    {
        char *name = new char[maxLength] {};
        char *number = new char[maxLength] {};
        while (symbol != ',')
        {
            insertSymbol(name, symbol);
            file.get(symbol);
        }
        file.get(symbol);
        while ((symbol != ';') && (!file.eof()))
        {
            insertSymbol(number, symbol);
            file.get(symbol);
        }
        file.get(symbol);
        addRecord(phoneBook, name, number, true);
    }
    file.close();
}

void copyIntoFile(PhoneBook *phoneBook, char nameFile[])
{
    ofstream file(nameFile, ios::app);
    ElementPhoneBook *current = phoneBook->first;
    while (current)
    {
        if (!current->saved)
        {
            file << current->name << ',' << current->number << ';';
        }
        current->saved = true;
        current = current->next;
    }
    file.close();
}

void deletePhoneBook(PhoneBook *phoneBook)
{
    while (phoneBook->first)
    {
        ElementPhoneBook *current = phoneBook->first;
        phoneBook->first = current->next;
        delete[] current->name;
        delete[] current->number;
        delete current;
    }
    delete phoneBook;
}

void insertSymbol(char *line, char symbol)
{
    int select = 0;
    while (line[select] != '\0')
    {
        select++;
    }
    line[select] = symbol;
}

int length(char *line)
{
    int length = 0;
    while (line[length] != '\0')
    {
        length++;
    }
    return length;
}

bool isEqual(char *firstLine, char *secondLine)
{
    int firstLength = length(firstLine);
    int secondLength = length(secondLine);
    if (firstLength != secondLength)
    {
        return false;
    }
    for (int i = 0; i < firstLength; i++)
    {
        if (firstLine[i] != secondLine[i])
        {
            return false;
        }
    }
    return true;
}
