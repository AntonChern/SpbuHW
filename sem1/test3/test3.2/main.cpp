#include <iostream>
#include <fstream>
using namespace std;

int readNumber(ifstream &file, char &symbol, char barrier);
void fillMatrix(int **matrix, ifstream &file, int height, int length, char &symbol);

const char *nameOfFile = "file.txt";

int main()
{
    ifstream file(nameOfFile);
    char symbol = '\0';
    file.get(symbol);
    int height = readNumber(file, symbol, ' ');
    int length = readNumber(file, symbol, '\n');
    int **matrix = new int *[height] {};
    for (int i = 0; i < height; i++)
    {
        matrix[i] = new int[length] {};
    }
    fillMatrix(matrix, file, height, length, symbol);
    file.close();
    int count = 0;
    for (int i = 0; i < height; i++)
    {
        int min = 0x7FFFFFFF;
        for (int j = 0; j < length; j++)
        {
            if (matrix[i][j] < min)
            {
                min = matrix[i][j];
            }
        }
        for (int j = 0; j < length; j++)
        {
            if (matrix[i][j] == min)
            {
                int max = 0;
                for (int k = 0; k < height; k++)
                {
                    if (matrix[k][j] > max)
                    {
                        max = matrix[k][j];
                    }
                }
                for (int k = 0; k < height; k++)
                {
                    if (k == i && matrix[k][j] == max)
                    {
                        count++;
                        cout << count << ". " << k << "," << j << " - " << min << endl;
                    }
                }
            }
        }
    }
    for (int i = 0; i < height; i++)
    {
        delete[] matrix[i];
    }
    delete[] matrix;
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

void fillMatrix(int **matrix, ifstream &file, int height, int length, char &symbol)
{
    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < length - 1; j++)
        {
            matrix[i][j] = readNumber(file, symbol, ' ');
        }
        matrix[i][length - 1] = readNumber(file, symbol, '\n');
    }
}
