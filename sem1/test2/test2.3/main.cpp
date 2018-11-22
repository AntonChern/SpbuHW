#include <iostream>
#include "list.h"
using namespace std;

int main()
{
    List *list = createList();
    cout << "Enter n & m: ";
    int n = 0;
    int m = 0;
    cin >> n;
    cin >> m;
    const char *nameOfFile = "file.txt";
    fullListFromFile(list, nameOfFile);
    displayList(list);
    sortListDanger(list);
    displayList(list);
    cout << "Shot people:" << endl;
    for (int i = 0; i < n; i++)
    {
        displayFirst(list);
        deleteElement(list);
    }
    cout << endl;
    sortListABC(list);
    cout << "People sent to Siberia:" << endl;
    for (int i = 0; i < m; i++)
    {
        displayFirst(list);
        deleteElement(list);
    }
    cout << endl;
    cout << "Unscathed people:" << endl;
    displayList(list);
    deleteList(list);
}

