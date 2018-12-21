#pragma once

struct ListElement;
struct List;

List *createList();
void deleteList(List *list);

void addElement(List *list, int length, int width);
void removeElement(List *list, int length, int width);
bool exists(List *list, int length, int width);
bool isEmpty(List *list);
int lengthOfList(List *list);
int lengthOf(List *list, int index);
int widthOf(List *list, int index);
