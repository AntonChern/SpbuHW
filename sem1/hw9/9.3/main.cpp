#include <iostream>
#include "binarytree.h"
using namespace std;

int main()
{
    const char *nameOfInputFile = "inputFile.txt";
    const char *nameOfOutputFile = "outputFile.txt";
    BinaryTree *tree = createBinaryTree();
    fillTree(tree, nameOfInputFile);
    fillFile(tree, nameOfInputFile, nameOfOutputFile);
    deleteBinaryTree(tree);
}
