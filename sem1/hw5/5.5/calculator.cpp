#include <iostream>
#include "intstack.h"
#include "charstack.h"
#include "calculator.h"
using namespace std;

const int maxLength = 256;

void postfixNotation(char *expression, char *outputLine)
{
    CharStack *stack = createCharStack();
    int lengthExpression = length(expression);
    for (int i = 0; i < lengthExpression; i++)
    {
        switch (expression[i])
        {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            {
                completeLine(outputLine, expression[i]);
                break;
            }
            case '(':
            {
                pushChar(stack, expression[i]);
                break;
            }
            case ')':
            {
                char element = popChar(stack);
                while (element != '(')
                {
                    completeLine(outputLine, element);
                    element = popChar(stack);
                }
                break;
            }
            case '+':
            case '-':
            case '*':
            case '/':
            {
                if (!isEmptyChar(stack))
                {
                    char element = popChar(stack);
                    while (priority(element) >= priority(expression[i]))
                    {
                        completeLine(outputLine, element);
                        element = popChar(stack);
                    }
                    pushChar(stack, element);
                }
                pushChar(stack, expression[i]);
                break;
            }
        }
    }
    while (!isEmptyChar(stack))
    {
        completeLine(outputLine, popChar(stack));
    }
    deleteCharStack(stack);
}

int calculate(char *outputLine)
{
    IntStack *stack = createIntStack();
    int lengthOutputLine = length(outputLine);
    for (int i = 0; i < lengthOutputLine; i++)
    {
        switch (outputLine[i])
        {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            {
                pushInt(stack, outputLine[i] - '0');
                break;
            }
            case '+':
            {
                int second = popInt(stack);
                pushInt(stack, popInt(stack) + second);
                break;
            }
            case '-':
            {
                int second = popInt(stack);
                pushInt(stack, popInt(stack) - second);
                break;
            }
            case '*':
            {
                int second = popInt(stack);
                pushInt(stack, popInt(stack) * second);
                break;
            }
            case '/':
            {
                int second = popInt(stack);
                pushInt(stack, popInt(stack) / second);
                break;
            }
        }
    }
    int result = popInt(stack);
    deleteIntStack(stack);
    return result;
}

int priority(char element)
{
    switch (element)
    {
        case '*':
        case '/':
        {
            return 1;
            break;
        }
        case '+':
        case '-':
        {
            return 0;
            break;
        }
        case '(':
        {
            return -1;
            break;
        }
    }
    return -1;
}

int length(char *line)
{
    int result = 0;
    for (int i = 0; line[i] != '\0'; i++)
    {
        result++;
    }
    return result;
}

void completeLine(char *line, char symbol)
{
    for (int i = 0; i < maxLength; i++)
    {
        if (line[i] == '\0')
        {
            line[i] = symbol;
            return;
        }
    }
}
