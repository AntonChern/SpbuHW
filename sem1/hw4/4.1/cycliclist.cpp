#include <iostream>
#include "cycliclist.h"
using namespace std;

CyclicList *createCyclicList()
{
    return new CyclicList {nullptr};
}

void deleteCyclicList(CyclicList *cyclicList)
{
    CyclicListElement *current = cyclicList->first;
    while (current->next != cyclicList->first)
    {
        CyclicListElement *nextElement = current->next;
        delete current;
        current = nextElement;
    }
    delete current;
    delete cyclicList;
}

int size(CyclicList *cyclicList)
{
    CyclicListElement *current = cyclicList->first;
    int length = 1;
    while (current->next != cyclicList->first)
    {
        length++;
        current = current->next;
    }
    return length;
}

int valueElement(CyclicList *cyclicList, int index)
{
    CyclicListElement *current = cyclicList->first;
    for (int i = 1; i < index; i++)
    {
        current = current->next;
    }
    return current->value;
}

void addElement(CyclicList *cyclicList, int value)
{
    CyclicListElement *current = cyclicList->first;
    if (cyclicList->first == nullptr)
    {
        cyclicList->first = new CyclicListElement {value, cyclicList->first};
        cyclicList->first->next = cyclicList->first;
    }
    else
    {
        while (current->next != cyclicList->first)
        {
            current = current->next;
        }
        CyclicListElement *pilotElement = new CyclicListElement {value, current->next};
        current->next = pilotElement;
    }
}

void deleteElement(CyclicList *cyclicList, int index)
{
    CyclicListElement *current = cyclicList->first;
    if (index < 1)
    {
        return;
    }
    if (index == 1)
    {
        while (current->next != cyclicList->first)
        {
            current = current->next;
        }
        cyclicList->first = current->next->next;
    }
    else
    {
        for (int i = 2; i < index; i++)
        {
            current = current->next;
        }
    }
    CyclicListElement *pilotElement = current->next->next;
    delete current->next;
    current->next = pilotElement;
}
