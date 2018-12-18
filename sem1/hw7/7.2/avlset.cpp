#include <iostream>
#include "binarytree.h"
#include "avlset.h"
using namespace std;

struct AVLset
{
    BinaryTree *tree;
};

AVLset *createSet()
{
    BinaryTree *tree = createBinaryTree();
    return new AVLset {tree};
}

void deleteSet(AVLset *set)
{
    deleteBinaryTree(set->tree);
    delete set;
}

void addElement(AVLset *set, int value)
{
    addElement(set->tree, value);
}

void deleteElement(AVLset *set, int value)
{
    deleteElement(set->tree, value);
}

bool exists(AVLset *set, int value)
{
    return exists(set->tree, value);
}

void displayIncrease(AVLset *set)
{
    displayIncrease(set->tree);
}

void displayDescend(AVLset *set)
{
    displayDescend(set->tree);
}

void displayDirect(AVLset *set)
{
    displayDirect(set->tree);
}
