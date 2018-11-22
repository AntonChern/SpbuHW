#include <iostream>
#include "binarytree.h"
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

void addElement(BSTset *set, int value)
{
    addElement(set->tree, value);
}

void deleteElement(BSTset *set, int value)
{
    deleteElement(set->tree, value);
}

bool exists(BSTset *set, int value)
{
    return exists(set->tree, value);
}

void displayIncrease(BSTset *set)
{
    displayIncrease(set->tree);
}

void displayDescend(BSTset *set)
{
    displayDescend(set->tree);
}

void displayDirect(BSTset *set)
{
    displayDirect(set->tree);
}
