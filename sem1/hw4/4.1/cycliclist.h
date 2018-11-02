#pragma once

struct CyclicListElement
{
    int value;
    CyclicListElement *next;
};

struct CyclicList
{
    CyclicListElement *first;
};

CyclicList *createCyclicList();
void deleteCyclicList(CyclicList *cyclicList);

void addElement(CyclicList *cyclicList, int value);
void deleteElement(CyclicList *cyclicList, int value);
int valueElement(CyclicList *cyclicList, int index);
int size(CyclicList *cyclicList);
