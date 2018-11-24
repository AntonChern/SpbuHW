#include <iostream>
#include "phonebook.h"
using namespace std;

void addRecord(PhoneBook *phoneBook);
void findNumberByName(PhoneBook *phoneBook);
void findNameByNumber(PhoneBook *phoneBook);
void save(PhoneBook *phoneBook, char nameFile[]);

int main()
{
    char nameFile[maxLength] = {"file.txt"};
    PhoneBook *phoneBook = createPhoneBook();
    copyFromFile(phoneBook, nameFile);

    cout << "0 - escape" << endl;
    cout << "1 - add record" << endl;
    cout << "2 - find number by name" << endl;
    cout << "3 - find name by number" << endl;
    cout << "4 - save" << endl << endl;
    int command = - 1;
    while (command != 0)
    {
        cout << "Enter command" << endl;
        cin >> command;
        switch (command)
        {
            case 1:
            {
                addRecord(phoneBook);
                break;
            }
            case 2:
            {
                findNumberByName(phoneBook);
                break;
            }
            case 3:
            {
                findNameByNumber(phoneBook);
                break;
            }
            case 4:
            {
                save(phoneBook, nameFile);
                break;
            }
        }
    }
    deletePhoneBook(phoneBook);
}

void addRecord(PhoneBook *phoneBook)
{
    cout << "Enter name: ";
    char *name = new char[maxLength] {};
    cin >> name;
    cout << "Enter number: ";
    char *number = new char[maxLength] {};
    cin >> number;
    addRecord(phoneBook, name, number, false);
    cout << "Record added" << endl << endl;
}

void findNumberByName(PhoneBook *phoneBook)
{
    cout << "Enter name: ";
    char *name = new char[maxLength] {};
    cin >> name;
    cout << "Its number: ";
    findNumber(phoneBook, name);
    cout << endl << endl;
    delete[] name;
}

void findNameByNumber(PhoneBook *phoneBook)
{
    cout << "Enter number: ";
    char *number = new char[maxLength] {};
    cin >> number;
    cout << "Its name: ";
    findName(phoneBook, number);
    cout << endl << endl;
    delete[] number;
}

void save(PhoneBook *phoneBook, char nameFile[])
{
    copyIntoFile(phoneBook, nameFile);
    cout << "Data saved" << endl << endl;
}
