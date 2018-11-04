#include <iostream>
#include "stack.h"
using namespace std;

Stack * createStack()
{
    return new Stack {nullptr};
}

void push(Stack *stack, char symbol)
{
    StackElement *newElement = new StackElement {symbol, stack->first};
    stack->first = newElement;
}

char pop(Stack *stack)
{
    if (!isEmpty(stack))
    {
        int result = stack->first->symbol;
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
