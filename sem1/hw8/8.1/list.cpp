#include <iostream>
#include "list.h"
using namespace std;

struct ListElement
{
    int number;
    int value;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList()
{
    return new List {nullptr};
}

void addElement(List *list, int number, int value)
{
    ListElement *newElement = new ListElement {number, value, list->first};
    list->first = newElement;
}

int findElement(List *list, int number)
{
    ListElement *current = list->first;
    while (current)
    {
        if (current->number == number)
        {
            return current->value;
        }
        current = current->next;
    }
    return -1;
}

void deleteList(List *list)
{
    while (list->first)
    {
        ListElement *current = list->first;
        list->first = current->next;
        delete current;
    }
    delete list;
}
