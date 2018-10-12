#include <iostream>
#include "list.h"
using namespace std;


List *createList()
{
    return new List {nullptr};
}

void deleteList(List *list)
{
    ListElement *current = list->first;
    while (current->next != list->first)
    {
        ListElement *nextElement = current->next;
        delete current;
        current = nextElement;
    }
    delete current;
    delete list;
}

int size(List *list)
{
    ListElement *current = list->first;
    int length = 1;
    while (current->next != list->first)
    {
        length++;
        current = current->next;
    }
    return length;
}

int valueElement(List *list, int index)
{
    ListElement *current = list->first;
    for (int i = 1; i < index; i++)
    {
        current = current->next;
    }
    return current->value;
}

void addElement(List *list, int value)
{
    ListElement *current = list->first;
    if (list->first == nullptr)
    {
        list->first = new ListElement {value, list->first};
        list->first->next = list->first;
    }
    else
    {
        while (current->next != list->first)
        {
            current = current->next;
        }
        ListElement *pilotElement = new ListElement {value, current->next};
        current->next = pilotElement;
    }
}

void deleteElement(List *list, int index)
{
    ListElement *current = list->first;
    if (index < 1)
    {
        return;
    }
    if (index == 1)
    {
        while (current->next != list->first)
        {
            current = current->next;
        }
        list->first = current->next->next;
    }
    else
    {
        for (int i = 2; i < index; i++)
        {
            current = current->next;
        }
    }
    ListElement *pilotElement = current->next->next;
    delete current->next;
    current->next = pilotElement;
}
