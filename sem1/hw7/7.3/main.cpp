#include <iostream>
#include <fstream>
#include "ast.h"
using namespace std;

int main()
{
    BinaryTree *tree = createBinaryTree();
    const char nameOfFile[maxLength] = {"file.txt"};
    fillTree(tree, nameOfFile);
    displayInfixNotation(tree);
    cout << " = ";
    calculate(tree);
    deleteBinaryTree(tree);
}
