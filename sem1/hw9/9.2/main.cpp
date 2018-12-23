#include <iostream>
#include "queue.h"
using namespace std;

int main()
{
    const char *nameOfInputFile = "inputFile.txt";
    const char *nameOfOutputFile = "outputFile.txt";
    Queue *queue = createQueue();
    fillQueue(queue, nameOfInputFile);
    displayQueue(queue);
    convertToTree(queue);
    addCodes(queue);
    fillFile(queue, nameOfInputFile, nameOfOutputFile);
    deleteQueue(queue);
}
