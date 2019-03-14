package com.AntonChernikov.g144;

/**
 * Class describing heap sort
 * */
public class HeapSort implements Sorter {
    public void sort(int[] numbers) {
        int leftBranchIndex = 0;
        int rightBranchIndex = 0;

        int length = numbers.length;
        int numOfBranches = countNumOfBranches(length);

        while (length > 1) {
            while (numOfBranches > 1) {
                for (int i = (int)Math.pow(2, (numOfBranches - 1) - 1) - 1; i < (int)Math.pow(2, numOfBranches - 1) - 1; i++) {
                    leftBranchIndex = 2 * i + 1;
                    rightBranchIndex = 2 * i + 2;
                    if (rightBranchIndex == length) {
                        if (numbers[i] < numbers[leftBranchIndex]) {
                            numbers[i] = returnFirst(numbers[leftBranchIndex], numbers[leftBranchIndex] = numbers[i]);
                        }
                    }
                    else {
                        if ((leftBranchIndex < length) && (rightBranchIndex < length)) {
                            if (numbers[leftBranchIndex] < numbers[rightBranchIndex]) {
                                numbers[leftBranchIndex] = returnFirst(numbers[rightBranchIndex], numbers[rightBranchIndex] = numbers[leftBranchIndex]);
                            }
                            if (numbers[i] < numbers[leftBranchIndex]) {
                                numbers[i] = returnFirst(numbers[leftBranchIndex], numbers[leftBranchIndex] = numbers[i]);
                            }
                        }
                    }
                }
                numOfBranches--;
            }
            numbers[0] = returnFirst(numbers[length - 1], numbers[length - 1] = numbers[0]);
            length--;
            numOfBranches = countNumOfBranches(length);
        }
    }

    private int returnFirst(int first, int second) {
        return first;
    }

    private int countNumOfBranches(int amount) {
        int numOfBranches = 0;
        for (int i = 0; amount > 0; i++)
        {
            amount -= (int)Math.pow(2, i);
            numOfBranches++;
        }
        return numOfBranches;
    }
}
