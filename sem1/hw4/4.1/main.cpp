#include <iostream>
#include "list.h"
using namespace std;

int main()
{
    cout << "Enter amount of warriors" << endl;
    int amount = 0;
    cin >> amount;
    List *list = createList();
    for (int i = 1; i <= amount; i++)
    {
        addElement(list, i);
    }

    cout << "Enter dead warrior number" << endl;
    int step = 0;
    cin >> step;

    int select = 1;
    while (size(list) > 1)
    {
        select += step - 1;
        if (select > size(list))
        {
            while (select > size(list))
            {
                select -= size(list);
            }
        }
        deleteElement(list, select);
    }

    cout << "With this version will survive warrior " << valueElement(list, 1);
    deleteList(list);
}
