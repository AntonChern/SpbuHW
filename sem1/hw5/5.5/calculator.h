#pragma once

void postfixNotation(char *expression, char *outputLine);
int calculate(char *outputLine);
int priority(char element);
void completeLine(char *line, char symbol);
int length(char *line);
