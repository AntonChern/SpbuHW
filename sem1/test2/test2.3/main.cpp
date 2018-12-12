#include <iostream>
using namespace std;

void reset(bool *included);

const int maxLength = 256;
const int symbolsABC = 26;

int main()
{
    cout << "Enter sentence: ";
    char *sentence = new char[maxLength] {};
    gets(sentence);
    bool *included = new bool[symbolsABC] {};
    int amountOfWords = 0;

    int maxLengthOfWord = 0;
    int lengthOfWord = 0;

    int maxDifferentLetters = 0;
    int differentLetters = 0;

    for (int i = 0; i < maxLength; i++)
    {
        if (!isalpha(sentence[i]) || sentence[i] == '\0')
        {
            amountOfWords++;
            for (int j = 0; j < symbolsABC; j++)
            {
                if (included[j])
                {
                    differentLetters++;
                }
            }
            if (lengthOfWord > maxLengthOfWord)
            {
                maxLengthOfWord = lengthOfWord;
                maxDifferentLetters = differentLetters;
            }

            if (sentence[i] == '\0')
            {
                break;
            }
            while (!isalpha(sentence[i]))
            {
                i++;
            }
            i--;

            lengthOfWord = 0;
            differentLetters = 0;
            reset(included);
        }
        else
        {
            lengthOfWord++;
            included[sentence[i] - 'a'] = true;
        }
    }
    cout << "Amount of words = " << amountOfWords << endl;
    cout << "Number of different letters in the longest word = " << maxDifferentLetters;
    delete[] sentence;
    delete[] included;
}

void reset(bool *included)
{
    for (int i = 0; i < symbolsABC; i++)
    {
        included[i] = false;
    }
}
