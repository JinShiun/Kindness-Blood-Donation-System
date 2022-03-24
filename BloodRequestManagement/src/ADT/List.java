/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import java.io.Serializable;
/**
 *
 * @author teren
 */
public class List<T extends Comparable> implements ListInterface<T>, Serializable {

    private T[] array;
    private int numberOfEntries;
    private static int DEFAULT_CAPACITY = 100;

    public List() {
        this(DEFAULT_CAPACITY);
    }

    public List(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Comparable[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) {
        boolean check = true;
        //check whether the array is full
        if (isFull()) {
            check = false;
        } else {
            //if it is empty, then the newEntry will be added into the list.
            array[numberOfEntries++] = newEntry;
        }

        return check;
    }

    @Override
    public boolean add(int newEntryPosition, T newEntry) {
        boolean validation = false;
        //check whether the array is full
        if (!isFull()) {
            if ((0 < newEntryPosition) && (newEntryPosition <= numberOfEntries)) {
                newEntryPosition -= 1;
                makeSpace(newEntryPosition);
                array[newEntryPosition] = newEntry;
                numberOfEntries++;
                validation = true;
            }
        }
        return validation;
    }

    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < numberOfEntries; ++i) {
            output += i + 1 + ". " + array[i] + "\n";

        }

        return output;
    }

    @Override
    public T getRecord(int positionEntry) {
        T result = null;

        if (!(positionEntry <= numberOfEntries) && (positionEntry > 0)) {
            System.out.println("There is no such record..");
        } else {
            result = array[positionEntry - 1];
        }

        return result;
    }

    @Override
    public T replace(int givenEntryPosition, T newEntry) {
        T entry;

        if ((givenEntryPosition <= numberOfEntries) && (givenEntryPosition > 0)) {
            array[givenEntryPosition - 1] = newEntry;
            entry = newEntry;
        } else {
            entry = array[givenEntryPosition - 1];
        }
        return entry;
    }

    @Override
    public boolean remove(int givenEntryPosition) {
        boolean check = true;
            if ((givenEntryPosition > 0) && (givenEntryPosition <= numberOfEntries)) {

                deleteGap(givenEntryPosition);

            } else {
                check = false;
            }

        return check;
    }

    @Override
    public void sort(List a) {
        for (int i = 0; i < array.length; i++) {
            T min = array[i];
            int minId = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] != null) {
                    if (array[j].compareTo(min) < 0) {
                        min = array[j];
                        minId = j;
                    }
                }
            }
            //swapping
            T temp = array[i];
            array[i] = min;
            array[minId] = temp;
        }
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == DEFAULT_CAPACITY;
    }

    @Override
    public int arraySize() {
        return numberOfEntries;
    }

    private void makeSpace(int newPosition) {
        for (int i = numberOfEntries - 1; i >= newPosition - 1; i--) {
            array[i + 1] = array[i];
        }
    }

    private void deleteGap(int givenPosition) {
        for (int i = givenPosition - 1; i < numberOfEntries - 1; i++) {
            array[i] = array[i + 1];
        }
        numberOfEntries--;
    }

}

