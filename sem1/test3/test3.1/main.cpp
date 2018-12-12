#include <iostream>
#include "list.h"
using namespace std;

int main()
{
    List *list = createList();
    cout << "Enter numbers:" << endl;
    int number = 0;
    cin >> number;
    for (int i = 0; number != 0; i++)
    {
        addElement(list, number);
        cin >> number;
    }
    cout << endl;
    displayList(list);
    deleteList(list);
}

