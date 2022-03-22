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
public interface ArrayStackInterface<T> {

    /**
     * Task: remove all the data and elements in the stack become empty stack
     */
    public void clear();//used

    /**
     * Task: to check whether the stack contain any data and elements or not
     * 
     * @return true if the stack is empty, else false
     */
    public boolean isEmpty();//used

    /**
     * Task: retrieve the elements and data according to the index position
     * 
     * @param index
     * @return the elements in the stack at index position
     */
    public T peek(int index);//used

    /**
     * Task: retrieve the elements from the stack and remove it from the 
     * stack according to the index position
     * 
     * @param index
     * @return the elements and data from stack
     */
    public T pop(int index);//used

    /**
     * Task: add or insert elements (newEntry) into the top of the stack
     * 
     * @param newEntry
     */
    public void push(T newEntry);//used

    /**
     * Task: to get the current number of elements in the stack
     * 
     * @return the current size of the stack
     */
    public int getNumOfEntry();//used

    /**
     * Task: retrieve all the elements in stack and convert into string
     * 
     * @return all the elements of stack in string type
     */
    public String toString2();//used
}
