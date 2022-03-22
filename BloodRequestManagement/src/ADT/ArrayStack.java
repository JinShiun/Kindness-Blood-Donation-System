/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author Lee Jin Shiun
 */
public class ArrayStack<T> implements ArrayStackInterface<T> {

    private T[] array;
    private int topIndex; // index of top entry
    private static final int DEFAULT_CAPACITY = 50;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int initialCapacity) {
        array = (T[]) new Object[initialCapacity];
        topIndex = -1;
    }

    //add
    @Override
    public void push(T newEntry) {
        topIndex++;

        if (topIndex < array.length) {
            array[topIndex] = newEntry;
        }
    }

    @Override
    public T peek(int index) {
        T result = null;

        if (!isEmpty()) {
            result = array[index];
        }
        return result;
    }

    @Override
    public T pop(int index) {
        T entry = null;

        if (!isEmpty()) {
            entry = array[index];
            do {
                array[index] = array[index + 1];
            } while (index + 1 <= topIndex);
            topIndex--;
        } // end if

        return entry;
    }

    @Override
    public int getNumOfEntry() {
        return topIndex;
    }

    @Override
    public String toString2() {
        String result = "";
        int scan = topIndex;

        while (scan >= 0) {
            if (array[scan] != null) {
                result += array[scan].toString() + "\n";
            }
            scan--;
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            topIndex = -1;
        }
    }

}
