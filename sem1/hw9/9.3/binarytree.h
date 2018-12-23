#pragma once

struct Node;
struct BinaryTree;

BinaryTree *createBinaryTree();
void deleteBinaryTree(BinaryTree *tree);

void addElement(Node *&node, char symbol);
void fillFile(BinaryTree *tree, const char *nameOfInputFile, const char *nameOfOutputFile);
void fillTree(BinaryTree *tree, const char *nameOfInputFile);

const int maxLength = 256;
