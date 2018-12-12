#include <iostream>
#include <fstream>
#include "list.h"
#include "binarytree.h"
using namespace std;

struct ListElement
{
    int priority;
    BinaryTree *tree;
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
    while (list->first)
    {
        ListElement *deletedElement = list->first;
        deleteBinaryTree(list->first->tree);
        list->first = list->first->next;
        delete deletedElement;
    }
    delete list;
}

void putOnPlace(ListElement *&element)
{
    if (element->next && element->priority > element->next->priority)
    {
        ListElement *current = element->next;
        element->next = current->next;
        current->next = element;
        element = current;
        putOnPlace(element->next);
    }
}

bool exists(List *list, char symbol)
{
    ListElement *current = list->first;
    while (current)
    {
        if (symbolOf(current->tree) == symbol)
        {
            return true;
        }
        current = current->next;
    }
    return false;
}

void incrementPriority(ListElement *&element, char symbol)
{
    if (symbolOf(element->tree) == symbol)
    {
        element->priority++;
        putOnPlace(element);
        return;
    }
    if (element->next)
    {
        incrementPriority(element->next, symbol);
    }
}

void addElement(List *list, char symbol)
{
    if (exists(list, symbol))
    {
        incrementPriority(list->first, symbol);
    }
    else
    {
        BinaryTree *tree = createBinaryTree(symbol);
        ListElement *newElement = new ListElement {1, tree, list->first};
        list->first = newElement;
        putOnPlace(list->first);
    }
}

void fillList(List *list, const char *nameOfInputFile)
{
    ifstream file(nameOfInputFile);
    char symbol = '\0';
    file.get(symbol);
    while (!file.eof())
    {
        addElement(list, symbol);
        file.get(symbol);
    }
    file.close();
}

void displayList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << symbolOf(current->tree) << " -> " << current->priority << endl;
        current = current->next;
    }
}

void convertToTree(List *list)
{
    while (list->first->next)
    {
        list->first->priority = list->first->priority + list->first->next->priority;
        merge(list->first->tree, list->first->next->tree);
        ListElement *current = list->first->next;
        list->first->next = current->next;
        delete current;
        putOnPlace(list->first);
    }
}

void addCodes(List *list)
{
    addCodes(list->first->tree);
}

void fillFile(List *list, const char *nameOfInputFile, const char *nameOfOutputFile)
{
    fillFileWithTree(list->first->tree, nameOfInputFile, nameOfOutputFile);
}
