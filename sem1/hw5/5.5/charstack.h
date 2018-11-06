#pragma once

struct CharStackElement
{
    char symbol;
    CharStackElement *next;
};

struct CharStack
{
    CharStackElement *first;
};

CharStack * createCharStack();
void deleteCharStack(CharStack *stack);
void pushChar(CharStack *stack, char symbol);
char popChar(CharStack *stack);
bool isEmptyChar(CharStack *stack);
