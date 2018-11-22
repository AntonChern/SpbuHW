#include <iostream>
#include <fstream>
#include "graph.h"
using namespace std;

struct Graph
{
    int amountOfCities;
    int **roads;
    int **capitals;
};

Graph *createGraph()
{
    return new Graph {};
}

int readNumber(ifstream &file, char &symbol, char barrier)
{
    int result = 0;
    while (symbol != barrier && !file.eof())
    {
        result = result * 10 + (symbol - '0');
        file.get(symbol);
    }
    file.get(symbol);
    return result;
}

void fillGraph(Graph *graph, const char *nameOfFile)
{
    ifstream file(nameOfFile);
    char symbol = ' ';
    file.get(symbol);
    int amountOfCities = readNumber(file, symbol, ' ');
    graph->amountOfCities = amountOfCities;
    graph->roads = new int *[amountOfCities] {};
    for (int i = 0; i < amountOfCities; i++)
    {
        graph->roads[i] = new int[amountOfCities] {};
    }
    graph->capitals = new int *[amountOfCities] {};
    int amountOfRoads = readNumber(file, symbol, '\n');
    for (int i = 0; i < amountOfRoads; i++)
    {
        int firstCity = readNumber(file, symbol, ' ');
        int secondCity = readNumber(file, symbol, ' ');
        int length = readNumber(file, symbol, '\n');
        graph->roads[firstCity - 1][secondCity - 1] = length;
        graph->roads[secondCity - 1][firstCity - 1] = length;
    }
    int amountOfCapitals = readNumber(file, symbol, '\n');
    for (int i = 0; i < amountOfCapitals; i++)
    {
        int capital = readNumber(file, symbol, ' ');
        graph->capitals[capital - 1] = new int[amountOfCities - 1] {};
    }
    file.close();
}

bool allVisited(Graph *graph, bool *visited)
{
    for (int i = 0; i < graph->amountOfCities; i++)
    {
        if (!visited[i])
        {
            return false;
        }
    }
    return true;
}

void distributeCities(Graph *graph)
{
    bool *visited = new bool[graph->amountOfCities] {};
    for (int i = 0; i < graph->amountOfCities; i++)
    {
        if (graph->capitals[i])
        {
            visited[i] = true;
        }
    }
    while (!allVisited(graph, visited))
    {
        for (int i = 0; i < graph->amountOfCities; i++)
        {
            if (graph->capitals[i])
            {
                int minLength = 0x7FFFFFF;
                int nearestCity = 0;
                for (int j = 0; j < graph->amountOfCities; j++)
                {
                    if (graph->roads[i][j] != 0 && !visited[j] && graph->roads[i][j] < minLength)
                    {
                        minLength = graph->roads[i][j];
                        nearestCity = j + 1;
                    }
                }
                for (int j = 0; j < graph->amountOfCities - 1 && graph->capitals[i][j] != 0; j++)
                {
                    int select = graph->capitals[i][j] - 1;
                    for (int k = 0; k < graph->amountOfCities; k++)
                    {
                        if (graph->roads[select][k] != 0 && !visited[k] && graph->roads[select][k] < minLength)
                        {
                            minLength = graph->roads[select][k];
                            nearestCity = k + 1;
                        }
                    }
                }
                for (int j = 0; j < graph->amountOfCities - 1; j++)
                {
                    if (graph->capitals[i][j] == 0)
                    {
                        graph->capitals[i][j] = nearestCity;
                        break;
                    }
                }
                if (nearestCity != 0)
                {
                    visited[nearestCity - 1] = true;
                }
            }
        }
    }
    delete[] visited;
}

void displayCities(Graph *graph)
{
    int indexOfState = 0;
    for (int i = 0; i < graph->amountOfCities; i++)
    {
        if (graph->capitals[i])
        {
            cout << "======" << ++indexOfState << "======" << endl;
            cout << "Capital => " << i + 1 << endl;
            if (graph->capitals[i][0] != 0)
            {
                cout << "Cities:" << endl;
            }
            for (int j = 0; j < graph->amountOfCities - 1 && graph->capitals[i][j] != 0; j++)
            {
                cout << graph->capitals[i][j] << endl;
            }
            cout << "=============" << endl;
            cout << endl;
        }
    }
}

void deleteGraph(Graph *graph)
{
    for (int i = 0; i < graph->amountOfCities; i++)
    {
        delete[] graph->roads[i];
    }
    delete[] graph->roads;
    for (int i = 0; i < graph->amountOfCities; i++)
    {
        if (graph->capitals[i])
        {
            delete[] graph->capitals[i];
        }
    }
    delete[] graph->capitals;
    delete graph;
}
