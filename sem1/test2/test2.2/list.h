#pragma once

struct ListElement;
struct List;

List *createList();
void deleteList(List *list);

void swap(ListElement *first, ListElement *second);
void sortList(List *list);
void addElement(List *list, int value);
void displayList(List *list);
