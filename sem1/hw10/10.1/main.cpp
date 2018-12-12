#include <iostream>
#include "map.h"
using namespace std;

Point *assignPoint(Map *map);

int main()
{
    const char *nameOfFile = "file.txt";
    Map *map = createMap(nameOfFile);
    fillMap(map, nameOfFile);
    cout << "Enter coordinates of start point <x y>: ";
    Point *start = assignPoint(map);
    cout << "Enter coordinates of end point <x y>:   ";
    Point *end = assignPoint(map);
    cout << endl;
    if (!start || !end)
    {
        cout << "Incorrect data";
    }
    else
    {
        functionAStar(map, start, end);
        displayMap(map, start, end);
    }
    deleteMap(map);
}

Point *assignPoint(Map *map)
{
    int length = 0;
    cin >> length;
    int width = 0;
    cin >> width;
    return findPoint(map, length, width);
}
