#include <iostream>
#include "stack.h"
using namespace std;

Stack * createStack()
{
    return new Stack {nullptr};
}

void push(Stack *stack, int value)
{
    StackElement *newElement = new StackElement {value, stack->first};
    stack->first = newElement;
}

int pop(Stack *stack)
{
    if (!isEmpty(stack))
    {
        int result = stack->first->value;
        StackElement *newElement = stack->first->next;
        delete stack->first;
        stack->first = newElement;
        return result;
    }
    return -1;
}

bool isEmpty(Stack *stack)
{
    return stack->first == nullptr;
}

void deleteStack(Stack *stack)
{
    StackElement *current = stack->first;
    while (current)
    {
        StackElement *newElement = current->next;
        delete current;
        current = newElement;
    }
    delete current;
    delete stack;
}
