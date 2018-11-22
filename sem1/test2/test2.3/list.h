#pragma once

struct ListElement;
struct List;

List *createList();
void deleteList(List *list);

void sortListDanger(List *list);
void sortListABC(List *list);
void addElement(List *list, char *surname, int loyalty);
void displayFirst(List *list);
void displayList(List *list);
void deleteElement(List *list);
void fullListFromFile(List *list, const char *nameOfFile);
