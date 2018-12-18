#pragma once

struct String;

String *createString(char *symbols);
void deleteString(String *string);
String *clone(String *string);

void concatenate(String *string, String *argument);
bool isEqual(String *first, String *second);
int lengthString(String *string);
bool isEmpty(String *string);
String *selectSubstring(String *string, int firstIndex, int lastIndex);
char *convertToChar(String *string);

void displayString(String *string);
