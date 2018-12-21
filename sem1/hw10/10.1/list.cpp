#include <iostream>
#include "list.h"
using namespace std;

struct ListElement
{
    int length;
    int width;
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

void deleteList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        ListElement *newElement = current->next;
        delete current;
        current = newElement;
    }
    delete list;
}

void addElement(List *list, int length, int width)
{
    ListElement *newElement = new ListElement {length, width, list->first};
    list->first = newElement;
}

void removeElement(ListElement *&element, int length, int width)
{
    if (element->length == length && element->width == width)
    {
        ListElement *current = element;
        element = current->next;
        delete current;
    }
    else
    {
        removeElement(element->next, length, width);
    }
}

void removeElement(List *list, int length, int width)
{
    removeElement(list->first, length, width);
}

bool exists(List *list, int length, int width)
{
    ListElement *current = list->first;
    while (current)
    {
        if (current->length == length && current->width == width)
        {
            return true;
        }
        current = current->next;
    }
    return false;
}

bool isEmpty(List *list)
{
    return !list->first;
}

int lengthOfList(List *list)
{
    int result = 0;
    ListElement *current = list->first;
    while (current)
    {
        result++;
        current = current->next;
    }
    return result;
}

int lengthOf(List *list, int index)
{
    ListElement *current = list->first;
    for (int i = 0; i < index; i++)
    {
        current = current->next;
    }
    return current->length;
}

int widthOf(List *list, int index)
{
    ListElement *current = list->first;
    for (int i = 0; i < index; i++)
    {
        current = current->next;
    }
    return current->width;
}
