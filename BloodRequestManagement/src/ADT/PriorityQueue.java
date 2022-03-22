/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author Chai Jia Hao
 */
public class PriorityQueue<T extends Comparable<T>> implements PriorityQueueInterface<T> {

    private T[] array;
    private int frontIndex;
    private int backIndex;
    private int count; //tracking the number of queue
    //private int
    private static final int DEFAULT_CAPACITY = 5;

    public PriorityQueue() {
        this(DEFAULT_CAPACITY);
    }

    public PriorityQueue(int initialCapacity) {
        array = (T[]) new Comparable[initialCapacity];
        backIndex = -1;
    }

    @Override
    public boolean enqueue(T newEntry) {
        int i = 0;
        while (i < backIndex + 1 && newEntry.compareTo(array[i]) >= 0) {
            i++;
        }
        if (!isArrayFull()) {//check whether the array is full or not
            makeRoom(i);
            array[i] = newEntry;
            backIndex++;
            count++;
        } else {
            doubleArray();
            makeRoom(i);
            array[i] = newEntry;
            backIndex++;
            count++;
        }

        return true;
    }

    private void makeRoom(int newPosition) {
        int newIndex = newPosition;
        int lastIndex = backIndex;

        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    @Override
    public void replace(int index, T newEntry) {
        array[index - 1] = newEntry;
    }

    @Override
    public int getTotalEntry() {
        return count;
    }

    @Override
    public int getNumEntry() {
        return backIndex + 1;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= count)) {
            result = array[givenPosition - 1];
        }

        return result;
    }

    @Override
    public T dequeue() {
        T front = null;
        if (!isEmpty()) {
            front = array[frontIndex];      // shift remaining array items forward one position      
            for (int i = frontIndex; i < backIndex; ++i) {
                array[i] = array[i + 1];
            }
            backIndex--;
        }
        return front;
    }

    @Override
    public boolean isEmpty() {
        return frontIndex > backIndex;
    }

    /**
     *
     */
    @Override
    public void clear() {
        if (!isEmpty()) { // deallocates only the used portion      
            for (int index = frontIndex; index <= backIndex; index++) {
                array[index] = null;
            }
            backIndex = -1;
        }
    }

    private void doubleArray() {
        T[] oldArray = array;
        int oldArr = oldArray.length;

        array = (T[]) new Comparable[2 * oldArr];
        for (int x = 0; x < oldArr; x++) {
            array[x] = oldArray[x];
        }
    }

    private boolean isArrayFull() {
        return backIndex == array.length - 1;
    }

    @Override
    public void remove(int index) {
        int arrayNum = index - 1;
        array[arrayNum] = null;
        for (int i = arrayNum; i < array.length - 1; ++i) {
            array[i] = array[i + 1];
        }
        array[backIndex] = null;
        backIndex--;
    }
}
