#pragma once

struct ElementPhoneBook;
struct PhoneBook;

PhoneBook *createPhoneBook();
void addRecord(PhoneBook *phoneBook, char *name, char *number, bool saved);
void findName(PhoneBook *phoneBook, char *number);
void findNumber(PhoneBook *phoneBook, char *name);
void copyFromFile(PhoneBook *phoneBook, char nameFile[]);
void copyIntoFile(PhoneBook *phoneBook, char nameFile[]);
void deletePhoneBook(PhoneBook *phoneBook);

void insertSymbol(char *line, char symbol);
int length(char *line);
bool isEqual(char *firstLine, char *secondLine);

int const maxLength = 256;
