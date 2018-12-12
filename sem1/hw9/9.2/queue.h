#pragma once

struct ListElement;
struct List;

List *createList();
void deleteList(List *list);

void addElement(List *list, char symbol);
void fillList(List *list, const char *nameOfInputFile);
void displayList(List *list);
void convertToTree(List *list);
void addCodes(List *list);
void fillFile(List *list, const char *nameOfInputFile, const char *nameOfOutputFile);
