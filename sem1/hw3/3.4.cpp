#include <iostream>
#include <time.h>
using namespace std;

void generateNumber(int n[], int);
void fillAttempt(int n[], int, int);

int main()
{
    int numOfDigits = 4;
    int *guessNum = new int[numOfDigits] {};
    generateNumber(guessNum, numOfDigits);

    int *attemptNum = new int[numOfDigits] {};

    cout << "======Bulls and cows======" << endl;
    int bulls = 0;
    int cows = 0;
    int numOfAttempts = 0;
    int attempt = 0;
    bool isWin = false;
    while (!isWin)
    {
        cout << "Guess" << endl;
        cin >> attempt;
        fillAttempt(attemptNum, attempt, numOfDigits);

        bulls = 0;
        cows = 0;

        for (int i = 0; i < numOfDigits; i++)
        {
            if (attemptNum[i] == guessNum[i])
            {
                bulls++;
            }
        }

        for (int i = 0; i < numOfDigits; i++)
        {
            for (int j = 0; j < numOfDigits; j++)
            {
                if ((attemptNum[i] == guessNum[j]) && (i != j))
                {
                    cows++;
                }
            }
        }

        if (bulls == numOfDigits)
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

void generateNumber(int guessNum[], int numOfDigits)
{
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
}

void fillAttempt(int attemptNum[], int attempt, int numOfDigits)
{
    for (int i = numOfDigits - 1; i >= 0; i--)
    {
        attemptNum[i] = attempt % 10;
        attempt /= 10;
    }
}
