#include <iostream>
#include "functions.h"
using namespace std;

int main()
{
    FILE *pilotFile = fopen("pilotFile.txt", "w");
    FILE *file = fopen("file.txt", "r");
    copyFiles(file, pilotFile);

    cout << "0 - escape" << endl;
    cout << "1 - add record" << endl;
    cout << "2 - find number by name" << endl;
    cout << "3 - find name by number" << endl;
    cout << "4 - save" << endl;
    cout << endl;
    int command = 5;
    while (command != 0)
    {
        command = 0;
        cout << "Enter command" << endl;
        cin >> command;
        if (command == 1)
        {
            commandOne(pilotFile);
        }
        if (command == 2)
        {
            commandTwo(pilotFile);
        }
        if (command == 3)
        {
            commandThree(pilotFile);
        }
        if (command == 4)
        {
            commandFour(pilotFile, file);
        }
    }
}
