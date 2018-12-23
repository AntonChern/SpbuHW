#pragma once

struct Node;
struct BinaryTree;

BinaryTree *createBinaryTree(char symbol);
void deleteBinaryTree(BinaryTree *tree);

char symbolOf(BinaryTree *tree);
void merge(BinaryTree *firstTree, BinaryTree *secondTree);
void addCodes(BinaryTree *tree);
void fillFileWithTree(BinaryTree *tree, const char *nameOfInputFile, const char *nameOfOutputFile);

const int maxLength = 256;
