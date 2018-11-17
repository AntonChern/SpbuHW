#include <fstream>
#pragma once

struct BinaryTree;
struct Node;

BinaryTree *createBinaryTree();
void deleteBinaryTree(BinaryTree *tree);

void fillTree(BinaryTree *tree, const char nameOfFile[]);
void displayInfixNotation(BinaryTree *tree);
void calculate(BinaryTree *tree);
void addElement(Node *&node, char *value);

const int maxLength = 256;
