#pragma once

struct QueueElement;
struct Queue;

Queue *createQueue();
void deleteQueue(Queue *queue);

void addElement(Queue *queue, char symbol);
void fillQueue(Queue *queue, const char *nameOfInputFile);
void displayQueue(Queue *queue);
void convertToTree(Queue *queue);
void addCodes(Queue *queue);
void fillFile(Queue *queue, const char *nameOfInputFile, const char *nameOfOutputFile);
