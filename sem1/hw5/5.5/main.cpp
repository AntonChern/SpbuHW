#include <iostream>
#include "calculator.h"
using namespace std;

const int maxLength = 256;

int main()
{
    cout << "Enter arithmetic expression" << endl;
    char *expression = new char[maxLength] {};
    cin >> expression;

    char *outputLine = new char[maxLength] {};
    postfixNotation(expression, outputLine);
    cout << "Value = " << calculate(outputLine);
    delete[] expression;
    delete[] outputLine;
}
