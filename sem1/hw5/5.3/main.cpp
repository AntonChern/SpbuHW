#include <iostream>
#include "stack.h"
using namespace std;

void postfixNotation(string &, string &, Stack *);
int priority(int);

int main()
{
    cout << "Enter infix notation: ";
    string expression = "";
    cin >> expression;

    Stack *stack = createStack();
    string outputLine = "";
    postfixNotation(expression, outputLine, stack);
    cout << "Postfix notation: " << outputLine;
    deleteStack(stack);
}

void postfixNotation(string &expression, string &outputLine, Stack *stack)
{
    int lengthExpression = expression.length();
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
                outputLine += expression[i];
                break;
            }
            case '(':
            {
                push(stack, expression[i]);
                break;
            }
            case ')':
            {
                int element = pop(stack);
                while (element != '(')
                {
                    outputLine += element;
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
                    int element = pop(stack);
                    while (priority(element) >= priority(expression[i]))
                    {
                        outputLine += element;
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
        outputLine += pop(stack);
    }
}

int priority(int element)
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
