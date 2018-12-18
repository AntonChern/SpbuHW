#pragma once

struct Graph;

Graph *createGraph();
void deleteGraph(Graph *graph);

void fillGraph(Graph *graph, const char *nameOfFile);
void distributeCities(Graph *graph);
void displayCities(Graph *graph);
