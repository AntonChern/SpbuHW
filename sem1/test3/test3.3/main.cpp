#include <iostream>
#include "graph.h"
using namespace std;

int main()
{
    const char *nameOfFile = "file.txt";
    Graph *graph = createGraph(nameOfFile);
    fillGraph(graph, nameOfFile);
    findVertexes(graph);
    cout << "Result:" << endl;
    displayVertexes(graph);
    deleteGraph(graph);
}
