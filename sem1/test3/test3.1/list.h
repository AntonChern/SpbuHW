#pragma once

struct ListElement;
struct List;

List *createList();
void deleteList(List *list);

void addElement(List *list, int number);
void displayList(List *list);
