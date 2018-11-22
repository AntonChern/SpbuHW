#include <iostream>
#include "string.h"
using namespace std;

const int maxLength = 256;

int main()
{
    cout << "0 - escape" << endl;
    cout << "1 - clone string" << endl;
    cout << "2 - concatenate string" << endl;
    cout << "3 - compare strings" << endl;
    cout << "4 - count length of string" << endl;
    cout << "5 - check string for emptiness" << endl;
    cout << "6 - select a substring" << endl;
    cout << "7 - convert to char *" << endl;
    cout << "8 - display string" << endl << endl;

    cout << "Enter symbols: ";
    char symbols[maxLength] = {};
    cin >> symbols;
    String *string = createString(symbols);
    cout << "String created" << endl << endl;

    int command = -1;

    while (command != 0)
    {
        cout << "Enter command - ";
        cin >> command;
        switch (command)
        {
            case 1:
            {
                String *clonedString = clone(string);
                cout << "String cloned - ";
                displayString(clonedString);
                cout << endl << endl;
                deleteString(clonedString);
                break;
            }
            case 2:
            {
                cout << "Enter symbols: ";
                char symbols[maxLength] = {};
                cin >> symbols;
                String *argument = createString(symbols);
                concatenate(string, argument);
                cout << "String concatenated - ";
                displayString(string);
                cout << endl << endl;
                deleteString(argument);
                break;
            }
            case 3:
            {
                cout << "Enter symbols: ";
                char symbols[maxLength] = {};
                cin >> symbols;
                String *newString = createString(symbols);
                if (isEqual(string, newString))
                {
                    cout << "Strings are equal";
                }
                else
                {
                    cout << "Strings aren't equal";
                }
                cout << endl << endl;
                deleteString(newString);
                break;
            }
            case 4:
            {
                cout << "Length of string = " << lengthString(string) << endl << endl;
                break;
            }
            case 5:
            {
                if (isEmpty(string))
                {
                    cout << "String is empty";
                }
                else
                {
                    cout << "String isn't empty";
                }
                cout << endl << endl;
                break;
            }
            case 6:
            {
                cout << "Enter first and last indexes: ";
                int firstIndex = 0;
                int lastIndex = 0;
                cin >> firstIndex;
                cin >> lastIndex;
                String *substring = selectSubstring(string, firstIndex, lastIndex);
                cout << "The substring is selected - ";
                displayString(substring);
                cout << endl << endl;
                deleteString(substring);
                break;
            }
            case 7:
            {
                char *symbols = {};
                symbols = convertToChar(string);
                cout << "String converted to char * - ";
                cout << symbols << endl << endl;
                delete[] symbols;
                break;
            }
            case 8:
            {
                displayString(string);
                cout << endl << endl;
                break;
            }
        }
    }
    deleteString(string);
}
