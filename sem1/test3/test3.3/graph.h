#pragma once

struct Graph;

Graph *createGraph(const char *nameOfFile);
void deleteGraph(Graph *graph);

void fillGraph(Graph *graph, const char *nameOfFile);
void findVertexes(Graph *graph);
void displayVertexes(Graph *graph);

void displayGraph(Graph *graph);
