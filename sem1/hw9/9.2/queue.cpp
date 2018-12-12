#include <iostream>
#include <fstream>
#include "queue.h"
#include "binarytree.h"
using namespace std;

struct QueueElement
{
    int priority;
    BinaryTree *tree;
    QueueElement *next;
};

struct Queue
{
    QueueElement *first;
};

Queue *createQueue()
{
    return new Queue {nullptr};
}

void deleteQueue(Queue *queue)
{
    while (queue->first)
    {
        QueueElement *deletedElement = queue->first;
        deleteBinaryTree(queue->first->tree);
        queue->first = queue->first->next;
        delete deletedElement;
    }
    delete queue;
}

void putOnPlace(QueueElement *&element)
{
    if (element->next && element->priority > element->next->priority)
    {
        QueueElement *current = element->next;
        element->next = current->next;
        current->next = element;
        element = current;
        putOnPlace(element->next);
    }
}

bool exists(Queue *queue, char symbol)
{
    QueueElement *current = queue->first;
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

void incrementPriority(QueueElement *&element, char symbol)
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

void addElement(Queue *queue, char symbol)
{
    if (exists(queue, symbol))
    {
        incrementPriority(queue->first, symbol);
    }
    else
    {
        BinaryTree *tree = createBinaryTree(symbol);
        QueueElement *newElement = new QueueElement {1, tree, queue->first};
        queue->first = newElement;
        putOnPlace(queue->first);
    }
}

void fillQueue(Queue *queue, const char *nameOfInputFile)
{
    ifstream file(nameOfInputFile);
    char symbol = '\0';
    file.get(symbol);
    while (!file.eof())
    {
        addElement(queue, symbol);
        file.get(symbol);
    }
    file.close();
}

void displayQueue(Queue *queue)
{
    QueueElement *current = queue->first;
    while (current)
    {
        cout << symbolOf(current->tree) << " -> " << current->priority << endl;
        current = current->next;
    }
}

void convertToTree(Queue *queue)
{
    while (queue->first->next)
    {
        queue->first->priority = queue->first->priority + queue->first->next->priority;
        merge(queue->first->tree, queue->first->next->tree);
        QueueElement *current = queue->first->next;
        queue->first->next = current->next;
        delete current;
        putOnPlace(queue->first);
    }
}

void addCodes(Queue *queue)
{
    addCodes(queue->first->tree);
}

void fillFile(Queue *queue, const char *nameOfInputFile, const char *nameOfOutputFile)
{
    fillFileWithTree(queue->first->tree, nameOfInputFile, nameOfOutputFile);
}
