#include <iostream>
#include "charstack.h"
using namespace std;

CharStack * createCharStack()
{
    return new CharStack {nullptr};
}

void pushChar(CharStack *stack, char symbol)
{
    CharStackElement *newElement = new CharStackElement {symbol, stack->first};
    stack->first = newElement;
}

bool isEmptyChar(CharStack *stack)
{
    return stack->first == nullptr;
}

char popChar(CharStack *stack)
{
    if (!isEmptyChar(stack))
    {
        int result = stack->first->symbol;
        CharStackElement *newElement = stack->first->next;
        delete stack->first;
        stack->first = newElement;
        return result;
    }
    return '\0';
}

void deleteCharStack(CharStack *stack)
{
    CharStackElement *current = stack->first;
    while (current)
    {
        CharStackElement *newElement = current->next;
        delete current;
        current = newElement;
    }
    delete current;
    delete stack;
}
