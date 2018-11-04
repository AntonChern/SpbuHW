#pragma once

struct IntStackElement
{
    int value;
    IntStackElement *next;
};

struct IntStack
{
    IntStackElement *first;
};

IntStack * createIntStack();
void deleteIntStack(IntStack *stack);
void pushInt(IntStack *stack, int value);
int popInt(IntStack *stack);
bool isEmptyInt(IntStack *stack);
