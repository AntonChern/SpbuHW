#include <iostream>
#include "stack.h"
using namespace std;

const int maxLength = 256;

int calculate(char *outputLine, Stack *stack);
int length(char *line);

int main()
{
    cout << "Enter postfix notation: ";
    char *expression = new char[maxLength] {};
    cin >> expression;

    Stack *stack = createStack();
    cout << "Value = " << calculate(expression, stack);
    deleteStack(stack);
    delete[] expression;
}

int calculate(char *outputLine, Stack *stack)
{
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

int length(char *line)
{
    int result = 0;
    for (int i = 0; line[i] != '\0'; i++)
    {
        result++;
    }
    return result;
}
