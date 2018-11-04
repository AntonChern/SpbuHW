#include <iostream>
#include "stack.h"
using namespace std;

const int maxLength = 256;

void postfixNotation(char *expression, char *outputLine, Stack *stack);
int priority(char element);
int length(char *line);
void completeLine(char *line, char symbol);

int main()
{
    cout << "Enter infix notation: ";
    char *expression = new char[maxLength] {};
    cin >> expression;

    Stack *stack = createStack();
    char *outputLine = new char[maxLength] {};
    postfixNotation(expression, outputLine, stack);
    cout << "Postfix notation: " << outputLine;
    deleteStack(stack);
    delete[] expression;
    delete[] outputLine;
}

void postfixNotation(char *expression, char *outputLine, Stack *stack)
{
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
                push(stack, expression[i]);
                break;
            }
            case ')':
            {
                char element = pop(stack);
                while (element != '(')
                {
                    completeLine(outputLine, element);
                    element = pop(stack);
                }
                break;
            }
            case '+':
            case '-':
            case '*':
            case '/':
            {
                if (!isEmpty(stack))
                {
                    char element = pop(stack);
                    while (priority(element) >= priority(expression[i]))
                    {
                        completeLine(outputLine, element);
                        element = pop(stack);
                    }
                    push(stack, element);
                }
                push(stack, expression[i]);
                break;
            }
        }
    }
    while (!isEmpty(stack))
    {
        completeLine(outputLine, pop(stack));
    }
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
