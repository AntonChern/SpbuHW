#include <iostream>
#include "graph.h"
using namespace std;

const char *nameOfFile = "file.txt";

int main()
{
    Graph *graph = createGraph();
    fillGraph(graph, nameOfFile);
    distributeCities(graph);
    displayCities(graph);
    deleteGraph(graph);
}
