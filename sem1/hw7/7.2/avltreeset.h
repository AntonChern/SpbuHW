#pragma once

struct AVLset;

AVLset *createSet();
void deleteSet(AVLset *set);

struct BinaryTree;
struct Node;

BinaryTree *createBinaryTree();
void deleteBinaryTree(BinaryTree *tree);

int height(Node *node);
int balanceFactor(Node *node);
void updateHeight(Node *node);
Node *rotateLeft(Node *&root);
Node *rotateRight(Node *&root);
Node* balance(Node *&node);

void addElement(AVLset *set, int value);
void deleteElement(AVLset *set, int value);
bool exists(AVLset *set, int value);
void displayIncrease(AVLset *set);
void displayDescend(AVLset *set);
void displayDirect(AVLset *set);
