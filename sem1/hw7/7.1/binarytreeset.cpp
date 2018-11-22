#include <iostream>
#include "binarytreeset.h"
using namespace std;

struct BSTset
{
    BinaryTree *tree;
};

BSTset *createSet()
{
    BinaryTree *tree = createBinaryTree();
    return new BSTset {tree};
}

void deleteSet(BSTset *set)
{
    deleteBinaryTree(set->tree);
    delete set;
}


struct BinaryTree
{
    Node *root;
};

struct Node
{
    int value;
    Node *leftChild;
    Node *rightChild;
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

void addElement(Node *&node, int value)
{
    if (!node)
    {
        node = new Node {value, nullptr, nullptr};
    }
    else
    {
        if (value != node->value)
        {
            if (value > node->value)
            {
                addElement(node->rightChild, value);
            }
            else
            {
                addElement(node->leftChild, value);
            }
        }
    }
}

void addElement(BSTset *set, int value)
{
    addElement(set->tree->root, value);
}

Node *specifyLeftChild(Node *&node)
{
    if (!node->leftChild)
    {
        Node *current = node;
        node = nullptr;
        return current;
    }
    else
    {
        specifyLeftChild(node->leftChild);
    }
}

void deleteElement(Node *&node, int value)
{
    if (node)
    {
        if (value == node->value)
        {
            Node *deletedElement = node;
            if ((node->leftChild) && (node->rightChild))
            {
                node = specifyLeftChild(node->rightChild);
                node->leftChild = deletedElement->leftChild;
                node->rightChild = deletedElement->rightChild;
            }
            else
            {
                if (!((node->leftChild) || (node->rightChild)))
                {
                    node = nullptr;
                }
                else
                {
                    if (node->leftChild)
                    {
                        node = deletedElement->leftChild;
                    }
                    else
                    {
                        node = deletedElement->rightChild;
                    }
                }
            }
            delete deletedElement;
        }
        else
        {
            if (value > node->value)
            {
                deleteElement(node->rightChild, value);
            }
            else
            {
                deleteElement(node->leftChild, value);
            }
        }
    }
}

void deleteElement(BSTset *set, int value)
{
    deleteElement(set->tree->root, value);
}

bool exists(BSTset *set, int value)
{
    Node *foundElement = set->tree->root;
    while (foundElement)
    {
        if (value == foundElement->value)
        {
            return true;
        }
        else
        {
            if (value > foundElement->value)
            {
                foundElement = foundElement->rightChild;
            }
            else
            {
                foundElement = foundElement->leftChild;
            }
        }
    }
    return false;
}

void displayIncrease(Node *&node)
{
    if (node->leftChild)
    {
        displayIncrease(node->leftChild);
    }
    cout << node->value << " ";
    if (node->rightChild)
    {
        displayIncrease(node->rightChild);
    }
}

void displayIncrease(BSTset *set)
{
    if (set->tree->root)
    {
        displayIncrease(set->tree->root);
    }
}

void displayDescend(Node *&node)
{
    if (node->rightChild)
    {
        displayDescend(node->rightChild);
    }
    cout << node->value << " ";
    if (node->leftChild)
    {
        displayDescend(node->leftChild);
    }
}

void displayDescend(BSTset *set)
{
    if (set->tree->root)
    {
        displayDescend(set->tree->root);
    }
}

void displayDirect(Node *&node)
{
    cout << "(" << node->value << " ";
    if (node->leftChild)
    {
        displayDirect(node->leftChild);
    }
    else
    {
        cout << "null";
    }
    if (node->rightChild)
    {
        cout << " ";
        displayDirect(node->rightChild);
        cout << ")";
    }
    else
    {
        cout << " null)";
    }
}

void displayDirect(BSTset *set)
{
    if (set->tree->root)
    {
        displayDirect(set->tree->root);
    }
}
