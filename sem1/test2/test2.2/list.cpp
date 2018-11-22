#include <iostream>
#include "list.h"
using namespace std;

struct ListElement
{
    int value;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

void swap(ListElement *first, ListElement *second)
{
    int pilot = first->value;
    first->value = second->value;
    second->value = pilot;
}

void sort(ListElement *element)
{
    if (element->next)
    {
        if (element->value > element->next->value)
        {
            swap(element->value, element->next->value);
        }
        sort(element->next);
        if (element->value > element->next->value)
        {
            swap(element->value, element->next->value);
        }
        sort(element->next);
    }
}

void sortList(List *list)
{
    sort(list->first);
}


List *createList()
{
    return new List {nullptr};
}

void addElement(ListElement *&element, int value)
{
    if (!element)
    {
        element = new ListElement {value, nullptr};
    }
    else
    {
        addElement(element->next, value);
    }
}

void addElement(List *list, int value)
{
    addElement(list->first, value);
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
        cout << current->value << " ";
        current = current->next;
    }
    cout << endl;
}
