package com.AntonChernikov.g144;

/**
 * Class describing unique list
 * */
public class UniqueList<T> extends List<T>{

    /**
     * @inheritDoc
     * */
    @Override
    public void add(T value) throws ElementExistsException {
        if (exists(value)) {
            throw new ElementExistsException();
        }
        else {
            super.add(value);
        }
    }

    /**
     * @inheritDoc
     * */
    @Override
    public void remove(T value) throws NoElementException {
        if (!exists(value)) {
            throw new NoElementException();
        }
        else {
            super.remove(value);
        }
    }
}

class ElementExistsException extends Exception {
}

class NoElementException extends Exception {
}







