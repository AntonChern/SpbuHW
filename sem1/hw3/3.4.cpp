#include <iostream>
#include <time.h>
using namespace std;

int main()
{
    int numOfDigits = 4;
    int *guessNum = new int[numOfDigits] {};

    srand(time(NULL));
    bool isEqually = true;
    for (int i = 0; i < numOfDigits; i++)
    {
        while (isEqually)
        {
            guessNum[i] = rand() % 10;
            isEqually = false;
            for (int j = 0; j < i; j++)
            {
                if (guessNum[i] == guessNum[j])
                {
                    isEqually = true;
                }
            }
        }
        isEqually = true;
    }

    int *attemptNum = new int[numOfDigits] {};

    int bulls = 0;
    int cows = 0;

    cout << "======Bulls and cows======" << endl;

    int numOfAttempts = 0;
    int attempt = 0;
    bool isWin = false;
    while (!isWin)
    {
        cout << "Guess" << endl;
        cin >> attempt;

        for (int i = 3; i >= 0; i--)
        {
            attemptNum[i] = attempt % 10;
            attempt /= 10;
        }

        bulls = 0;
        cows = 0;

        for (int i = 0; i < 4; i++)
        {
            if (attemptNum[i] == guessNum[i])
            {
                bulls++;
            }
        }

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if ((attemptNum[i] == guessNum[j]) && (i != j))
                {
                    cows++;
                }
            }
        }

        if (bulls == 4)
        {
            isWin = true;
        }
        else
        {
            cout << "Bulls = " << bulls << endl;
            cout << "Cows  = " << cows << endl;
            cout << endl;
        }
        numOfAttempts++;
    }

    cout << "You win!" << endl;
    cout << "Attempts: " << numOfAttempts << endl;
    delete[] guessNum;
    delete[] attemptNum;
}
