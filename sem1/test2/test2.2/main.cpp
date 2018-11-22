#include <iostream>
#include "list.h"
using namespace std;

int main()
{
    List *list = createList();
    cout << "Enter number of elements: ";
    int amount = 0;
    cin >> amount;
    cout << "Enter values:" << endl;
    for (int i = 0; i < amount; i++)
    {
        cout << i + 1 << ". ";
        int value = 0;
        cin >> value;
        addElement(list, value);
    }
    sortList(list);
    displayList(list);
    deleteList(list);
}
