#include <iostream>
#include "list.h"
using namespace std;

int main()
{
    const char *nameOfInputFile = "inputFile.txt";
    const char *nameOfOutputFile = "outputFile.txt";
    List *list = createList();
    fillList(list, nameOfInputFile);
    displayList(list);
    convertToTree(list);
    addCodes(list);
    fillFile(list, nameOfInputFile, nameOfOutputFile);
    deleteList(list);
}
