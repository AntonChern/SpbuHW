#pragma once

struct ListElement
{
    int value;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void deleteList(List *list);

void addElement(List *list, int value);
void deleteElement(List *list, int value);
int valueElement(List *list, int index);
int size(List *list);
