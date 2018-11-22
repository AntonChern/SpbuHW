#include <iostream>
#include <fstream>
#include "list.h"
using namespace std;

const int maxLength = 256;

struct ListElement
{
    char *surname;
    int loyalty;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

int lengthSurname(ListElement *element)
{
    int result = 0;
    for (int i = 0; element->surname[i] != '\0'; i++)
    {
        result++;
    }
    return result;
}

bool isAbove(ListElement *first, ListElement *second)
{
    int minLength = (lengthSurname(first) < lengthSurname(second)) ? lengthSurname(first) : lengthSurname(second);
    for (int i = 0; i < minLength; i++)
    {
        if (first->surname[i] < second->surname[i])
        {
            return true;
        }
        if (first->surname[i] > second->surname[i])
        {
            return false;
        }
    }
    return false;
}

void assign(ListElement *first, ListElement *second)
{
    int length = lengthSurname(second);
    for (int i = 0; i < length; i++)
    {
        first[i] = second[i];
    }
}

void swapSurnames(ListElement *first, ListElement *second)
{
    ListElement *pilot = new ListElement {};
    pilot->surname = new char[maxLength] {};
    assign(pilot, first);
    assign(first, second);
    assign(second, pilot);
    delete pilot;
}

void sortDanger(ListElement *element)
{
    if (element->next)
    {
        if (element->loyalty < element->next->loyalty)
        {
            swapSurnames(element, element->next);
            swap(element->loyalty, element->next->loyalty);
        }
        sortDanger(element->next);
        if (element->loyalty < element->next->loyalty)
        {
            swapSurnames(element, element->next);
            swap(element->loyalty, element->next->loyalty);
        }
        sortDanger(element->next);
    }
}

void sortListDanger(List *list)
{
    sortDanger(list->first);
}

void sortABC(ListElement *element)
{
    if (element->next)
    {
        if (isAbove(element->next, element))
        {
            swapSurnames(element, element->next);
            swap(element->loyalty, element->next->loyalty);
        }
        sortABC(element->next);
        if (isAbove(element->next, element))
        {
            swapSurnames(element, element->next);
            swap(element->loyalty, element->next->loyalty);
        }
        sortABC(element->next);
    }
}

void sortListABC(List *list)
{
    sortABC(list->first);
}


List *createList()
{
    return new List {nullptr};
}

void addElement(ListElement *&element, char *surname, int loyalty)
{
    if (!element)
    {
        element = new ListElement {surname, loyalty, nullptr};
    }
    else
    {
        addElement(element->next, surname, loyalty);
    }
}

void addElement(List *list, char *surname, int loyalty)
{
    addElement(list->first, surname, loyalty);
}

void deleteList(List *list)
{
    while (list->first)
    {
        ListElement *current = list->first;
        list->first = current->next;
        delete[] current->surname;
        delete current;
    }
    delete list;
}

void displayFirst(List *list)
{
    cout << list->first->surname << endl;
}

void displayList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->surname << " " << current->loyalty << endl;
        current = current->next;
    }
    cout << endl;
}


void deleteElement(List *list)
{
    ListElement *current = list->first;
    list->first = current->next;
    delete[] current->surname;
    delete current;
}

void fullListFromFile(List *list, const char *nameOfFile)
{
    ifstream file(nameOfFile);
    char symbol = '\0';
    while (!file.eof())
    {
        char *surname = new char[maxLength] {};
        file.get(symbol);
        for (int i = 0; symbol != ' '; i++)
        {
            surname[i] = symbol;
            file.get(symbol);
        }
        file.get(symbol);
        int loyalty = 0;
        for (int i = 0; symbol != ';' && !file.eof(); i++)
        {
            loyalty = loyalty * 10 + (symbol - '0');
            file.get(symbol);
        }
        addElement(list, surname, loyalty);
    }
    file.close();
}
