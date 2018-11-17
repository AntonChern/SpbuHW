#pragma once

struct BinaryTree;
struct Node;

BinaryTree *createBinaryTree();
void deleteBinaryTree(BinaryTree *tree);

void addElement(BinaryTree *tree, int value);
void deleteElement(BinaryTree *tree, int value);
bool exists(BinaryTree *tree, int value);
void displayIncrease(BinaryTree *tree);
void displayDescend(BinaryTree *tree);
void displayDirect(BinaryTree *tree);
