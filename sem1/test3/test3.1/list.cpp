#include <iostream>
#include "list.h"
using namespace std;

struct ListElement
{
    int number;
    int amount;
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

void putOnPlace(ListElement *&element, int number)
{
    if (element->next && element->number > element->next->number)
    {
        ListElement *current = element->next;
        element->next = element->next->next;
        current->next = element;
        element = current;
        putOnPlace(element->next, number);
    }
}

void addElement(List *list, int number)
{
    ListElement *current = list->first;
    while (current)
    {
        if (current->number == number)
        {
            current->amount++;
            return;
        }
        current = current->next;
    }
    ListElement *newElement = new ListElement {number, 1, list->first};
    list->first = newElement;
    putOnPlace(list->first, number);
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

void displayList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->number << "=>" << current->amount << " ";
        current = current->next;
    }
}

