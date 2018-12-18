#pragma once

struct AVLset;

AVLset *createSet();
void deleteSet(AVLset *set);

void addElement(AVLset *set, int value);
void deleteElement(AVLset *set, int value);
bool exists(AVLset *set, int value);
void displayIncrease(AVLset *set);
void displayDescend(AVLset *set);
void displayDirect(AVLset *set);
