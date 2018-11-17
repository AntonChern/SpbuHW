#include <iostream>
#include <fstream>
#include "ast.h"
using namespace std;

struct BinaryTree
{
    Node *root;
};

struct Node
{
    char *value;
    Node *leftChild;
    Node *rightChild;
};

BinaryTree *createBinaryTree()
{
    return new BinaryTree {nullptr};
}

void deleteElement(Node *&node)
{
    if (node->leftChild)
    {
        deleteElement(node->leftChild);
    }
    if (node->rightChild)
    {
        deleteElement(node->rightChild);
    }
    delete[] node->value;
    delete node;
}

void deleteBinaryTree(BinaryTree *tree)
{
    if (tree->root)
    {
        deleteElement(tree->root);
    }
    delete tree;
}

void addElement(Node *&node, char *value)
{
    node = new Node {value, nullptr, nullptr};
}

void fillValue(char *value, ifstream &file, char symbol)
{
    if (symbol != '-')
    {
        value[0] = '0';
    }
    else
    {
        value[0] = '1';
        file.get(symbol);
    }
    for (int i = 1; (symbol != ' ') && (symbol != ')'); i++)
    {
        value[i] = symbol;
        file.get(symbol);
    }
}

void addOperation(Node *&node, char symbol)
{
    char *value = new char[maxLength] {};
    value[0] = symbol;
    addElement(node, value);
}

void addOperand(Node *&node, ifstream &file, char symbol)
{
    char *value = new char[maxLength] {};
    fillValue(value, file, symbol);
    addElement(node, value);
}

void fillTree(Node *&node, ifstream &file, char symbol)
{
    file.get(symbol);
    addOperation(node, symbol);
    for (int i = 0; i < 2; i++)
    {
        file.get(symbol);
        while (symbol == ' ')
        {
            file.get(symbol);
        }
        if (symbol == '(')
        {
            if (!node->leftChild)
            {
                fillTree(node->leftChild, file, symbol);
            }
            else
            {
                fillTree(node->rightChild, file, symbol);
            }
        }
        else
        {
            if (!node->leftChild)
            {
                addOperand(node->leftChild, file, symbol);
            }
            else
            {
                addOperand(node->rightChild, file, symbol);
            }
        }
    }
    file.get(symbol);
}

void fillTree(BinaryTree *tree, const char nameOfFile[])
{
    ifstream file(nameOfFile);
    char symbol = ' ';
    file.get(symbol);
    fillTree(tree->root, file, symbol);
    file.close();
}

bool isLeaf(Node *&node)
{
    if (!node->leftChild && !node->rightChild)
    {
        return true;
    }
    return false;
}

void displayOperand(char *value)
{
    if (value[0] == '1')
    {
        cout << "-";
    }
    for (int i = 1; value[i] != '\0'; i++)
    {
        cout << value[i];
    }
}

void displayInfixNotation(Node *&node)
{
    if (node->leftChild)
    {
        cout << "(";
        displayInfixNotation(node->leftChild);
        cout << " ";
    }
    if (isLeaf(node))
    {
        displayOperand(node->value);
    }
    else
    {
        cout << node->value;
    }
    if (node->rightChild)
    {
        cout << " ";
        displayInfixNotation(node->rightChild);
        cout << ")";
    }
}

void displayInfixNotation(BinaryTree *tree)
{
    displayInfixNotation(tree->root);
}

void deleteLeaf(Node *&node)
{
    Node *deletedElement = node;
    delete[] deletedElement->value;
    delete deletedElement;
    node = nullptr;
}

void count(Node *&node, int value)
{
    for (int i = 0; i < maxLength; i++)
    {
        node->value[i] = '\0';
    }
    if (value > 0)
    {
        node->value[0] = '0';
    }
    else
    {
        node->value[0] = '1';
        value = -value;
    }
    int digits = 0;
    for (int i = 1; value > 0; i++)
    {
        node->value[i] = value % 10 + '0';
        value /= 10;
        digits++;
    }
    for (int i = 1; i <= digits / 2; i++)
    {
        swap(node->value[i], node->value[digits - i + 1]);
    }
    deleteLeaf(node->leftChild);
    deleteLeaf(node->rightChild);
}

int convertToInt(char *value)
{
    int result = 0;
    for (int i = 1; value[i] != '\0'; i++)
    {
        result = result * 10 + (value[i] - '0');
    }
    return (value[0] == '0') ? result : -result;
}

void calculate(Node *&node)
{
    if (!isLeaf(node))
    {
        if (isLeaf(node->leftChild) && isLeaf(node->rightChild))
        {
            int first = convertToInt(node->leftChild->value);
            int second = convertToInt(node->rightChild->value);
            switch (node->value[0])
            {
                case '+':
                {
                    count(node, first + second);
                    break;
                }
                case '-':
                {
                    count(node, first - second);
                    break;
                }
                case '*':
                {
                    count(node, first * second);
                    break;
                }
                case '/':
                {
                    count(node, first / second);
                    break;
                }
            }
        }
        else
        {
            calculate(node->leftChild);
            calculate(node->rightChild);
            calculate(node);
        }
    }
}

void calculate(BinaryTree *tree)
{
    calculate(tree->root);
    displayOperand(tree->root->value);
}
