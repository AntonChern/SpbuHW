#include <iostream>
#include "intstack.h"
using namespace std;

IntStack * createIntStack()
{
    return new IntStack {nullptr};
}

void pushInt(IntStack *stack, int value)
{
    IntStackElement *newElement = new IntStackElement {value, stack->first};
    stack->first = newElement;
}

bool isEmptyInt(IntStack *stack)
{
    return stack->first == nullptr;
}

int popInt(IntStack *stack)
{
    if (!isEmptyInt(stack))
    {
        int result = stack->first->value;
        IntStackElement *newElement = stack->first->next;
        delete stack->first;
        stack->first = newElement;
        return result;
    }
    return -1;
}

void deleteIntStack(IntStack *stack)
{
    IntStackElement *current = stack->first;
    while (current)
    {
        IntStackElement *newElement = current->next;
        delete current;
        current = newElement;
    }
    delete current;
    delete stack;
}
