#include <iostream>
using namespace std;

int main()
{
    FILE *file = fopen("file.txt", "r");
    char symbol = ' ';
    char current = ' ';
    fscanf(file, "%c", &current);
    while (!feof(file))
    {
        symbol = current;
        fscanf(file, "%c", &current);
        while ((current == symbol) && (!feof(file)))
        {
            fscanf(file, "%c", &current);
        }
        cout << symbol;
    }
    fclose(file);
}
