#include <iostream>
using namespace std;

int main()
{
    FILE *file = fopen("file.txt", "r");
    int emptyStrings = 0;
    char symbol = ' ';
    while (!feof(file))
    {
        fscanf(file, "%c", &symbol);
        bool isEmpty = true;
        while (symbol != '\n' && !feof(file))
        {
            if (symbol != '\t' && symbol != ' ')
            {
                isEmpty = false;
            }
            fscanf(file, "%c", &symbol);
        }
        if (!isEmpty)
        {
            emptyStrings++;
        }
    }
    cout << "Number of non-empty strings = " << emptyStrings << endl;
    fclose(file);
}
