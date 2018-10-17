#include <iostream>
#include "cycliclist.h"
using namespace std;

int main()
{
    cout << "Enter amount of warriors" << endl;
    int amount = 0;
    cin >> amount;
    CyclicList *cyclicList = createCyclicList();
    for (int i = 1; i <= amount; i++)
    {
        addElement(cyclicList, i);
    }

    cout << "Enter dead warrior number" << endl;
    int step = 0;
    cin >> step;

    int select = 1;
    while (amount > 1)
    {
        select += step - 1;
        if (select > amount)
        {
            while (select > amount)
            {
                select -= amount;
            }
        }
        deleteElement(cyclicList, select);
        amount--;
    }

    cout << "With this version will survive warrior " << valueElement(cyclicList, 1);
    deleteCyclicList(cyclicList);
}
