#include <stdio.h>

int main()
{
    printf("Enter x please\n");

    int x;

    scanf("%d", &x);

    int a = x * x;

    printf("Value of formula - ");

    printf("%d", ((a + x)*(a + 1) + 1));
}
