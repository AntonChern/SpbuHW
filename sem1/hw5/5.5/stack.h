#pragma once

struct StackElement
{
    int value;
    StackElement *next;
};

struct Stack
{
    StackElement *first;
};

Stack * createStack();
void deleteStack(Stack *);
void push(Stack *, int);
int pop(Stack *);
bool isEmpty(Stack *);
