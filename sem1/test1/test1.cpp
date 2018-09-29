#include <iostream>
using namespace std;

// difficulty log(n)

void qSort(int, int, int n[]);
void display(int, int n[]);

int main()
{
    cout << "Enter amount of Vasya's reports" << endl;
    int lengthV = 0;
    cin >> lengthV;
    int *stackV = new int[lengthV] {};
    cout << "Enter complexity of Vasya's items in descending order" << endl;
    for (int i = 0; i < lengthV; i++)
    {
        cin >> stackV[i];
    }

    cout << "Enter amount of Petya's reports" << endl;
    int lengthP = 0;
    cin >> lengthP;
    int *stackP = new int[lengthP] {};
    cout << "Enter complexity of Petya's items in descending order" << endl;
    for (int i = 0; i < lengthP; i++)
    {
        cin >> stackP[i];
    }

    int *stackJoint = new int[lengthV + lengthP] {};

    for (int i = 0; i < lengthV; i++)
    {
        stackJoint[i] = stackV[i];
    }
    for (int i = lengthV; i < lengthV + lengthP; i++)
    {
        stackJoint[i] = stackP[i - lengthV];
    }

    qSort(0, lengthV + lengthP - 1, stackJoint);
    cout << "Result - ";
    display(lengthV + lengthP, stackJoint);

    delete[] stackJoint;
    delete[] stackV;
    delete[] stackP;
}

void qSort(int first, int last, int num[])
{
    if (first < last)
    {
        int length = last - first + 1;
        int select = first + length / 2;
        bool isSorted = false;
        while (!isSorted)
        {
            isSorted = true;
            for (int i = 0; i < select - first; i++)
            {
                if (num[first + i] <= num[select])
                {
                    swap(num[first + i], num[select]);
                    select = first + i;
                    isSorted = false;
                    break;
                }
            }
            for (int i = 0; i < last - select; i++)
            {
                if (num[last - i] > num[select])
                {
                    swap(num[last - i], num[select]);
                    select = last - i;
                    isSorted = false;
                    break;
                }
            }
        }
        qSort(first, select - 1, num);
        qSort(select + 1, last, num);
    }
}

void display(int length, int num[])
{
    for (int i = 0; i < length; i++)
    {
        cout << num[i] << " ";
    }
    cout << endl;
}
