#include <iostream>
using namespace std;

int main()
{
    int guessNum[4] = {3, 4, 2, 1};
    int attemptNum[4] = {};

    int bulls = 0;
    int cows = 0;

    cout << "======Bulls and cows======" << endl;

    int attempt = 0;
    bool isWon = false;
    while (!isWon)
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
            isWon = true;
        }
        else
        {
            cout << "Bulls = " << bulls << endl;
            cout << "Cows  = " << cows << endl;
            cout << endl;
        }
    }

    cout << "You won!" << endl;
}
