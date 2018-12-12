#include <iostream>
#include <fstream>
#include <cmath>
#include "map.h"
#include "list.h"
using namespace std;

struct Point
{
    int length;
    int width;
    int g;
    int f;
    Point *from;
};

struct Map
{
    int length;
    int width;
    Point ***point;
};

Point *findPoint(Map *map, int length, int width)
{
    return map->point[length][width];
}

int readNumber(ifstream &file, char &symbol)
{
    int result = 0;
    while (symbol != ' ' && symbol != '\n' && !file.eof())
    {
        result = result * 10 + (symbol - '0');
        file.get(symbol);
    }
    file.get(symbol);
    return result;
}

Map *createMap(const char *nameOfFile)
{
    Map *map = new Map {0, 0, nullptr};
    ifstream file(nameOfFile);
    char symbol = '\0';
    file.get(symbol);
    map->length = readNumber(file, symbol);
    map->width = readNumber(file, symbol);
    file.close();
    map->point = new Point **[map->length] {nullptr};
    for (int i = 0; i < map->length; i++)
    {
        map->point[i] = new Point *[map->width] {nullptr};
    }
    return map;
}

void fillMap(Map *map, const char *nameOfFile)
{
    ifstream file(nameOfFile);
    char symbol = '\0';
    while (symbol != '\n')
    {
        file.get(symbol);
    }
    file.get(symbol);
    for (int j = 0; j < map->width; j++)
    {
        for (int i = 0; i < map->length; i++)
        {
            if (readNumber(file, symbol) == 0)
            {
                map->point[i][j] = new Point {i, j, 0, 0, nullptr};
            }
        }
    }
    file.close();
}

int h(Point *first, Point *second)
{
    int lengthSquare = (first->length - second->length) * (first->length - second->length);
    int widthSquare = (first->width + second->width) * (first->width + second->width);
    return (int)sqrt(lengthSquare + widthSquare) + 0.5;
}

bool existsIndex(int index, int barrier)
{
    return index >= 0 && index < barrier;
}

Point *minF(Map *map, List *list)
{
    int min = 0x7FFFFFFF;
    Point *result = nullptr;
    int length = lengthOfList(list);
    for (int i = 0; i < length; i++)
    {
        int indexLength = lengthOf(list , i);
        int indexWidth = widthOf(list, i);
        if (map->point[indexLength][indexWidth]->f < min)
        {
            min = map->point[indexLength][indexWidth]->f;
            result = map->point[indexLength][indexWidth];
        }
    }
    return result;
}

void functionAStar(Map *map, Point *start, Point *end)
{
    List *open = createList();
    addElement(open, start->length, start->width);
    List *closed = createList();
    start->g = 0;
    start->f = start->g + h(start, end);
    while (!isEmpty(open))
    {
        Point *current = minF(map, open);
        if (current == end)
        {
            deleteList(open);
            deleteList(closed);
            return;
        }
        removeElement(open, current->length, current->width);
        addElement(closed, current->length, current->width);
        int stepWidth = 1;
        int stepLength = 0;
        for (int i = 0; i < 4; i++)
        {
            int indexWidth = current->width + stepWidth;
            int indexLength = current->length + stepLength;
            if (existsIndex(indexWidth, map->width) && existsIndex(indexLength, map->length) && map->point[indexLength][indexWidth] && !exists(closed, map->point[indexLength][indexWidth]->length, map->point[indexLength][indexWidth]->width))
            {
                Point *neighbour = map->point[indexLength][indexWidth];
                int tempG = current->g + 1;
                if (!exists(open, neighbour->length, neighbour->width) || tempG < neighbour->g)
                {
                    neighbour->from = current;
                    neighbour->g = tempG;
                    neighbour->f = neighbour->g + h(neighbour, end);
                }
                if (!exists(open, neighbour->length, neighbour->width))
                {
                    addElement(open, neighbour->length, neighbour->width);
                }
            }
            stepWidth = -stepWidth;
            stepLength = -stepLength;
            if (indexWidth < current->width)
            {
                stepWidth = 0;
                stepLength = 1;
            }
        }
    }
    deleteList(open);
    deleteList(closed);
}

void addPoint(List *way, Point *step)
{
    addElement(way, step->length, step->width);
    if (step->from)
    {
        addPoint(way, step->from);
    }
}

void displayFrame(Map *map)
{
    cout << "+";
    for (int i = 0; i < map->length; i++)
    {
        cout << "-";
    }
    cout << "+" << endl;
}

void displayMap(Map *map, Point *start, Point *end)
{
    List *way = createList();
    addPoint(way, end);
    displayFrame(map);
    for (int j = 0; j < map->width; j++)
    {
        cout << "|";
        for (int i = 0; i < map->length; i++)
        {
            if (map->point[i][j])
            {
                if (exists(way, map->point[i][j]->length, map->point[i][j]->width) || map->point[i][j] == start)
                {
                    cout << ".";
                }
                else
                {
                    cout << " ";
                }
            }
            else
            {
                cout << "#";
            }
        }
        cout << "|" << endl;
    }
    displayFrame(map);
    deleteList(way);
}

void deleteMap(Map *map)
{
    for (int i = 0; i < map->length; i++)
    {
        for (int j = 0; j < map->width; j++)
        {
            delete map->point[i][j];
        }
        delete[] map->point[i];
    }
    delete[] map->point;
    delete map;
}
