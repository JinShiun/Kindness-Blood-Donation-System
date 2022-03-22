/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author Lim Jun Hua
 */
public class Queue<T> implements QueueInterface<T> {

    private T[] array; //
    private int frontIndex;
    private int backIndex;
    private int count; //tracking the number of queue
    //private int
    private static final int DEFAULT_CAPACITY = 5;

    public Queue() {
        this(DEFAULT_CAPACITY);
    }

    public Queue(int initialCapacity) {
        array = (T[]) new Object[initialCapacity];
        backIndex = -1;
    }

    @Override
    public void enqueue(T newEntry) {
        if (!isArrayFull()) {
            backIndex++;
            array[backIndex] = newEntry;
            count++;
        } else {
            doubleArray();
            backIndex++;
            array[backIndex] = newEntry;
            count++;
        }
    }
    
    public void replace(int index, T newEntry){
        array[index] = newEntry;
        
    }



    public int getTotalEntry(){
        return count;
    }
    
    public int getNumEntry() {
        return backIndex + 1;
    }

    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= count)) {
            result = array[givenPosition - 1];
        }

        return result;
    }

    public T getFront() {
        T front = null;
        if (!isEmpty()) {
            front = array[frontIndex];
        }
        return front;
    }

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

    public boolean isEmpty() {
        boolean check =false;
        if(count == 0){
            check = true;
        }
        return check;
    }

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

        array = (T[]) new Object[2 * oldArray.length];
        for (int x = 0; x < oldArray.length; x++) {
            array[x] = oldArray[x];
        }
    }

    private boolean isArrayFull() {
        return backIndex == array.length - 1;
    }

    public void remove(int num) {
        int arrayNum = num - 1;
        array[arrayNum] = null; //1 = null
        for(int i = arrayNum; i < array.length - 1; ++i){
            array[i] = array[i + 1]; // 2 value move  1 ,3 move to 2
        }
        array[backIndex] = null; //last index remove
        backIndex--;//last index position remove
        count--;
    }
}
