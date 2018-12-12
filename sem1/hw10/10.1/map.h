#pragma once

struct Point;
struct Map;

Map *createMap(const char *nameOfFile);
void deleteMap(Map *map);

Point *findPoint(Map *map, int length, int width);
void fillMap(Map *map, const char *nameOfFile);
void functionAStar(Map *map, Point *start, Point *end);
void displayMap(Map *map, Point *start, Point *end);
