#pragma once

struct StackElement
{
    char symbol;
    StackElement *next;
};

struct Stack
{
    StackElement *first;
};

Stack * createStack();
void deleteStack(Stack *stack);
void push(Stack *stack, char symbol);
int pop(Stack *stack);
bool isEmpty(Stack *stack);
