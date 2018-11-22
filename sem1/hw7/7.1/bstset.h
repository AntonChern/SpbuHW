#pragma once

struct BSTset;

BSTset *createSet();
void deleteSet(BSTset *set);

void addElement(BSTset *set, int value);
void deleteElement(BSTset *set, int value);
bool exists(BSTset *set, int value);
void displayIncrease(BSTset *set);
void displayDescend(BSTset *set);
void displayDirect(BSTset *set);
