#include <iostream>
#include <fstream>
#include "binarytree.h"
#include "string.h"
using namespace std;

struct Node
{
    char symbol;
    String *code;
    Node *leftChild;
    Node *rightChild;
};

struct BinaryTree
{
    Node *root;
};

BinaryTree *createBinaryTree(char symbol)
{
    BinaryTree *tree = new BinaryTree {nullptr};
    tree->root = new Node {symbol, nullptr, nullptr, nullptr};
    return tree;
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
    if (node->code)
    {
        deleteString(node->code);
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

char symbolOf(BinaryTree *tree)
{
    return tree->root->symbol;
}

void merge(BinaryTree *firstTree, BinaryTree *secondTree)
{
    Node *newRoot = new Node {'\0', nullptr, firstTree->root, secondTree->root};
    firstTree->root = newRoot;
    delete secondTree;
}

bool isLeaf(Node *node)
{
    return !(node->leftChild || node->rightChild);
}

void addCodes(Node *&node, String *&code)
{
    if (node->leftChild)
    {
        String *newCode = clone(code);
        char *zero = new char[maxLength] {};
        zero[0] = '0';
        String *zeroString = createString(zero);
        concatenate(newCode, zeroString);
        deleteString(zeroString);
        delete[] zero;
        addCodes(node->leftChild, newCode);
    }
    if (node->rightChild)
    {
        String *newCode = clone(code);
        char *one = new char[maxLength] {};
        one[0] = '1';
        String *oneString = createString(one);
        concatenate(newCode, oneString);
        deleteString(oneString);
        delete[] one;
        addCodes(node->rightChild, newCode);
    }
    if (isLeaf(node))
    {
        node->code = code;
    }
    else
    {
        deleteString(code);
    }
}

void addCodes(BinaryTree *tree)
{
    char *emptyString = new char[maxLength] {};
    String *code = createString(emptyString);
    addCodes(tree->root, code);
    delete[] emptyString;
}

void getAroundDirect(Node *&node, ofstream &file)
{
    if (node->symbol == '\0')
    {
        file << "(NODE ";
    }
    else
    {
        file << "(" << node->symbol << " ";
    }
    if (node->leftChild)
    {
        getAroundDirect(node->leftChild, file);
    }
    else
    {
        file << "null";
    }
    if (node->rightChild)
    {
        file << " ";
        getAroundDirect(node->rightChild, file);
        file << ")";
    }
    else
    {
        file << " null)";
    }
}

void findNode(Node *&select, Node *&node, char symbol)
{
    if (node->leftChild)
    {
        findNode(select, node->leftChild, symbol);
    }
    if (node->rightChild)
    {
        findNode(select, node->rightChild, symbol);
    }
    if (node->symbol == symbol)
    {
        select = node;
    }
}

void fillFileWithTree(BinaryTree *tree, const char *nameOfInputFile, const char *nameOfOutputFile)
{
    ofstream outputFile(nameOfOutputFile);
    if (tree->root)
    {
        getAroundDirect(tree->root, outputFile);
    }
    outputFile << '\n';
    ifstream inputFile(nameOfInputFile);
    char symbol = '\0';
    inputFile.get(symbol);
    while (!inputFile.eof())
    {
        Node *select = nullptr;
        findNode(select, tree->root, symbol);
        char *code = convertToChar(select->code);
        outputFile << code;
        delete[] code;
        inputFile.get(symbol);
    }
    inputFile.close();
    outputFile.close();
}
