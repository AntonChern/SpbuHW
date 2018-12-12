#include <iostream>
#include <fstream>
#include "graph.h"
using namespace std;

struct Graph
{
    int ribs;
    int vertexes;
    bool *select;
    int **graph;
};

int readNumber(ifstream &file, char &symbol)
{
    int result = 0;
    int sign = 1;
    if (symbol == '-')
    {
        sign = -sign;
        file.get(symbol);
    }
    while (symbol != ' ' && symbol != '\n' && !file.eof())
    {
        result = result * 10 + (symbol - '0');
        file.get(symbol);
    }
    file.get(symbol);
    return result * sign;
}

Graph *createGraph(const char *nameOfFile)
{
    Graph *graph = new Graph {};
    ifstream file(nameOfFile);
    char symbol = '\0';
    file.get(symbol);
    graph->ribs = readNumber(file, symbol);
    graph->vertexes = readNumber(file, symbol);
    graph->select = new bool[graph->vertexes] {};
    graph->graph = new int *[graph->vertexes] {};
    for (int i = 0; i < graph->vertexes; i++)
    {
        graph->graph[i] = new int[graph->ribs] {};
    }
    file.close();
    return graph;
}

void fillGraph(Graph *graph, const char *nameOfFile)
{
    ifstream file(nameOfFile);
    char symbol = '\0';
    file.get(symbol);
    while (symbol != '\n')
    {
        file.get(symbol);
    }
    file.get(symbol);
    for (int i = 0; i < graph->vertexes; i++)
    {
        for (int j = 0; j < graph->ribs; j++)
        {
            graph->graph[i][j] = readNumber(file, symbol);
        }
    }
    file.close();
}

void findVertex(Graph *graph, int indexOfVertex, bool *visited)
{
    visited[indexOfVertex] = true;
    for (int i = 0; i < graph->ribs; i++)
    {
        if (graph->graph[indexOfVertex][i] == -1)
        {
            for (int j = 0; j < graph->vertexes; j++)
            {
                if (graph->graph[j][i] == 1 && !visited[j])
                {
                    findVertex(graph, j, visited);
                }
            }
        }
    }
}

void nullVisited(bool *visited, int length)
{
    for (int i = 0; i < length; i++)
    {
        visited[i] = false;
    }
}

bool allVisited(bool *visited, int length)
{
    for (int i = 0; i < length; i++)
    {
        if (!visited[i])
        {
            return false;
        }
    }
    return true;
}

void findVertexes(Graph *graph)
{
    bool *visited = new bool[graph->vertexes] {};
    for (int i = 0; i < graph->vertexes; i++)
    {
        nullVisited(visited, graph->vertexes);
        findVertex(graph, i, visited);
        if (allVisited(visited, graph->vertexes))
        {
            graph->select[i] = true;
        }
    }
    delete[] visited;
}

void displayVertexes(Graph *graph)
{
    bool exists = false;
    for (int i = 0; i < graph->vertexes; i++)
    {
        if (graph->select[i])
        {
            exists = true;
            cout << i << endl;
        }
    }
    if (!exists)
    {
        cout << "Not found";
    }
}

void deleteGraph(Graph *graph)
{
    for (int i = 0; i < graph->vertexes; i++)
    {
        delete[] graph->graph[i];
    }
    delete[] graph->graph;
    delete[] graph->select;
    delete graph;
}
