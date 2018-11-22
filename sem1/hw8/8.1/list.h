#pragma once

struct ListElement;
struct List;

List *createList();
void deleteList(List *list);

void addElement(List *list, int number, int value);
int findElement(List *list, int number);
