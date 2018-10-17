#include <iostream>
#include "stack.h"
using namespace std;

int calculate(string &, Stack *);

int main()
{
    cout << "Enter postfix notation: ";
    string expression = "";
    cin >> expression;

    Stack *stack = createStack();
    cout << "Value = " << calculate(expression, stack);
    deleteStack(stack);
}

int calculate(string &outputLine, Stack *stack)
{
    int lengthOutputLine = outputLine.length();
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
                push(stack, outputLine[i] - '0');
                break;
            }
            case '+':
            {
                int second = pop(stack);
                push(stack, pop(stack) + second);
                break;
            }
            case '-':
            {
                int second = pop(stack);
                push(stack, pop(stack) - second);
                break;
            }
            case '*':
            {
                int second = pop(stack);
                push(stack, pop(stack) * second);
                break;
            }
            case '/':
            {
                int second = pop(stack);
                push(stack, pop(stack) / second);
                break;
            }
        }
    }
    return pop(stack);
}
