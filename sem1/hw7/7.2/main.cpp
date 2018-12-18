#include <iostream>
#include "avlset.h"
using namespace std;

int main()
{
    cout << "0 - escape" << endl;
    cout << "1 - add element" << endl;
    cout << "2 - delete element" << endl;
    cout << "3 - find element" << endl;
    cout << "4 - print in increasing order" << endl;
    cout << "5 - print in descending order" << endl;
    cout << "6 - print by direct bypass" << endl << endl;
    int command = -1;

    AVLset *set = createSet();

    do
    {
        cout << "Enter command" << endl;
        cin >> command;
        switch (command)
        {
            case 1:
            {
                cout << "Enter value" << endl;
                int value = 0;
                cin >> value;
                addElement(set, value);
                cout << "Value added" << endl << endl;
                break;
            }
            case 2:
            {
                cout << "Enter value" << endl;
                int value = 0;
                cin >> value;
                deleteElement(set, value);
                cout << "Value deleted" << endl << endl;
                break;
            }
            case 3:
            {
                cout << "Enter value" << endl;
                int value = 0;
                cin >> value;
                if (exists(set, value))
                {
                    cout << "Value is contained" << endl << endl;
                }
                else
                {
                    cout << "No values found" << endl << endl;
                }
                break;
            }
            case 4:
            {
                displayIncrease(set);
                cout << endl << endl;
                break;
            }
            case 5:
            {
                displayDescend(set);
                cout << endl << endl;
                break;
            }
            case 6:
            {
                displayDirect(set);
                cout << endl << endl;
                break;
            }
        }
    }
    while (command);
    deleteSet(set);
}
