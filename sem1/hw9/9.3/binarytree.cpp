#include <iostream>
#include <fstream>
#include "binarytree.h"
#include "string.h"
using namespace std;

struct Node
{
    char symbol;
    Node *leftChild;
    Node *rightChild;
};

struct BinaryTree
{
    Node *root;
};

BinaryTree *createBinaryTree()
{
    return new BinaryTree {nullptr};
}

void deleteElement(Node *&node, BinaryTree *tree)
{
    if (node->leftChild)
    {
        deleteElement(node->leftChild, tree);
    }
    if (node->rightChild)
    {
        deleteElement(node->rightChild, tree);
    }
    delete node;
}

void deleteBinaryTree(BinaryTree *tree)
{
    if (tree->root)
    {
        deleteElement(tree->root, tree);
    }
    delete tree;
}

bool isLeaf(Node *node)
{
    return !(node->leftChild || node->rightChild);
}

void findNode(Node *&node, Node *&select, String *code)
{
    char *way = convertToChar(code);
    int length = lengthString(code);
    String *newCode = selectSubstring(code, 1, length);
    if (way[0] == '0' && node->leftChild)
    {
        findNode(node->leftChild, select, newCode);
    }
    if (way[0] == '1' && node->rightChild)
    {
        findNode(node->rightChild, select, newCode);
    }
    deleteString(newCode);
    if (isLeaf(node))
    {
        select = node;
    }
    delete[] way;
}

void addElement(Node *&node, char symbol)
{
    node = new Node {symbol, nullptr, nullptr};
}

int countEnters(const char *nameOfInputFile)
{
    ifstream inputFile(nameOfInputFile);
    int result = 0;
    char symbol = '\0';
    inputFile.get(symbol);
    while (!inputFile.eof())
    {
        if (symbol == '\n')
        {
            result++;
        }
        inputFile.get(symbol);
    }
    inputFile.close();
    return result;
}

void fillFile(BinaryTree *tree, const char *nameOfInputFile, const char *nameOfOutputFile)
{
    int amountOfEnters = countEnters(nameOfInputFile);
    ifstream inputFile(nameOfInputFile);
    char symbol = '\0';
    for (int i = 0; i < amountOfEnters; i++)
    {
        while (symbol != '\n')
        {
            inputFile.get(symbol);
        }
        inputFile.get(symbol);
    }
    ofstream outputFile(nameOfOutputFile);
    while (!inputFile.eof())
    {
        bool added = false;
        char *emptyString = new char[maxLength] {};
        String *code = createString(emptyString);
        delete[] emptyString;
        while (!added)
        {
            char *codeArgument = new char[maxLength] {};
            codeArgument[0] = symbol;
            String *argument = createString(codeArgument);
            concatenate(code, argument);
            deleteString(argument);
            delete[] codeArgument;
            Node *select = nullptr;
            findNode(tree->root, select, code);
            if (select)
            {
                outputFile << select->symbol;
                added = true;
            }
            inputFile.get(symbol);
        }
        deleteString(code);
    }
    outputFile.close();
    inputFile.close();
}

void fillTree(Node *&node, ifstream &inputFile, char symbol)
{
    inputFile.get(symbol);
    if (symbol == '\n')
    {
        addElement(node, symbol);
        inputFile.get(symbol);
    }
    else
    {
        inputFile.get(symbol);
        if (symbol != ' ')
        {
            while (symbol != ' ')
            {
                inputFile.get(symbol);
            }
            addElement(node, '\0');
        }
        else
        {
            inputFile.unget();
            inputFile.unget();
            inputFile.get(symbol);
            addElement(node, symbol);
            inputFile.get(symbol);
        }
    }
    for (int i = 0; i < 2; i++)
    {
        inputFile.get(symbol);
        if (symbol == '(')
        {
            if (!node->leftChild)
            {
                fillTree(node->leftChild, inputFile, symbol);
            }
            else
            {
                fillTree(node->rightChild, inputFile, symbol);
            }
        }
        else
        {
            if (symbol == '\n')
            {
                if (!node->leftChild)
                {
                    addElement(node->leftChild, symbol);
                }
                else
                {
                    addElement(node->rightChild, symbol);
                }
            }
            else
            {
                inputFile.get(symbol);
                if (symbol != ' ')
                {
                    while (symbol != ' ' && symbol != ')')
                    {
                        inputFile.get(symbol);
                    }
                }
                else
                {
                    inputFile.unget();
                    inputFile.unget();
                    inputFile.get(symbol);
                    if (!node->leftChild)
                    {
                        addElement(node->leftChild, symbol);
                    }
                    else
                    {
                        addElement(node->rightChild, symbol);
                    }
                }
            }
        }
    }
    inputFile.get(symbol);
}

void fillTree(BinaryTree *tree, const char *nameOfInputFile)
{
    ifstream inputFile(nameOfInputFile);
    char symbol = '\0';
    inputFile.get(symbol);
    fillTree(tree->root, inputFile, symbol);
    inputFile.close();
}
