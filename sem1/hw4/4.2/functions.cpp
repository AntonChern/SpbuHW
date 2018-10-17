#include <iostream>
#include "functions.h"
using namespace std;

void copyFiles(FILE *fromFile, FILE *toFile)
{
    fseek(fromFile, 0, SEEK_SET);
    char symbol = ' ';
    fscanf(fromFile, "%c", &symbol);
    while (!feof(fromFile))
    {
        fprintf(toFile, "%c", symbol);
        fscanf(fromFile, "%c", &symbol);
    }
    fclose(fromFile);
    fclose(toFile);
}

void commandOne(FILE *pilotFile)
{
    cout << "Enter record: '*<name>,<number>;'" << endl;
    string record = " ";
    cin >> record;
    pilotFile = fopen("pilotFile.txt", "a");
    int lengthRecord = record.length();
    for (int i = 0; i < lengthRecord; i++)
    {
        fprintf(pilotFile, "%c", record[i]);
    }
    fprintf(pilotFile, "%c", '\n');
    fclose(pilotFile);
    cout << "Command completed" << endl << endl;
}

void commandTwo(FILE *pilotFile)
{
    cout << "Enter name: '<name>'" << endl;
    string name = " ";
    cin >> name;
    int lengthName = name.length();
    char symbol = ' ';
    pilotFile = fopen("file.txt", "r");
    fscanf(pilotFile, "%c", &symbol);
    bool isEqually = true;
    bool isExist = false;
    while (!feof(pilotFile))
    {
        if (symbol == name[0])
        {
            isEqually = true;
            for (int i = 1; i < lengthName; i++)
            {
                fscanf(pilotFile, "%c", &symbol);
                if (name[i] != symbol)
                {
                    isEqually = false;
                    break;
                }
            }
            fscanf(pilotFile, "%c", &symbol);
            if ((isEqually) && (symbol == ','))
            {
                displayNumber(pilotFile, symbol);
                isExist = true;
            }
        }
        fscanf(pilotFile, "%c", &symbol);
    }
    fclose(pilotFile);
    if (!isExist)
    {
        cout << "No records found" << endl;
    }
    cout << "Command completed" << endl << endl;
}

void commandThree(FILE *pilotFile)
{
    cout << "Enter number: '<number>'" << endl;
    string number = " ";
    cin >> number;
    int lengthNumber = number.length();
    char symbol = ' ';
    pilotFile = fopen("file.txt", "r");
    fscanf(pilotFile, "%c", &symbol);
    bool isEqually = true;
    bool isExist = false;
    while (!feof(pilotFile))
    {
        if (symbol == number[0])
        {
            isEqually = true;
            for (int i = 1; i < lengthNumber; i++)
            {
                fscanf(pilotFile, "%c", &symbol);
                if (number[i] != symbol)
                {
                    isEqually = false;
                    break;
                }
            }
            fscanf(pilotFile, "%c", &symbol);
            if ((isEqually) && (symbol == ';'))
            {
                setName(pilotFile, symbol);
                displayName(pilotFile, symbol);
                isExist = true;
            }
            setEndNumber(pilotFile, symbol);
        }
        fscanf(pilotFile, "%c", &symbol);
    }
    fclose(pilotFile);
    if (!isExist)
    {
        cout << "No records found" << endl;
    }
    cout << "Command completed" << endl << endl;
}

void commandFour(FILE *pilotFile, FILE *file)
{
    pilotFile = fopen("pilotFile.txt", "r");
    file = fopen("file.txt", "w");
    copyFiles(pilotFile, file);
    cout << "Command completed" << endl << endl;
}

void displayNumber(FILE *pilotFile, char symbol)
{
    fscanf(pilotFile, "%c", &symbol);
    while (symbol != ';')
    {
        cout << symbol;
        fscanf(pilotFile, "%c", &symbol);
    }
    cout << endl;
}

void displayName(FILE *pilotFile, char symbol)
{
    fscanf(pilotFile, "%c", &symbol);
    while (symbol != ',')
    {
        cout << symbol;
        fscanf(pilotFile, "%c", &symbol);
    }
    cout << endl;
}

void setName(FILE *pilotFile, char symbol)
{
    fseek(pilotFile, -2, SEEK_CUR);
    fscanf(pilotFile, "%c", &symbol);
    while (symbol != '*')
    {
        fseek(pilotFile, -2, SEEK_CUR);
        fscanf(pilotFile, "%c", &symbol);
    }
}

void setEndNumber(FILE *pilotFile, char symbol)
{
    fscanf(pilotFile, "%c", &symbol);
    while (symbol != ';')
    {
        fscanf(pilotFile, "%c", &symbol);
    }
}
