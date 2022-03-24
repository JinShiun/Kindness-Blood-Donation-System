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
public interface PriorityQueueInterface<T> {

    /**
     * Task: add newEntry into the priority queue according to their priority,
     * add in-between will require elements behind to shift back one place
     *
     * @param newEntry object to be added as a newEntry
     * @return true if the newEntry is added successfully into the priority
     * queue, else return false
     */
    public boolean enqueue(T newEntry);

    /**
     * Task: retrieve and delete the first element in the queue, element at the
     * back will shift one place forward
     * 
     * @return the first element in the queue
     */
    public T dequeue();

    /**
     * Task: to get the current number of elements in the queue, if the 
     * elements removed from queue number of elements - 1
     *
     * @return the current number of entry in the queue
     */
    public int getNumEntry();

    /**
     * Task: get the total entry added into the queue before. If elements
     * removed, total number of entry remain unchanged. Elements added
     * successfully, total number of entry + 1
     *
     * @return the total number of entry added into the queue before
     */
    public int getTotalEntry();

    /**
     * Task: get the elements from the queue according to the givenPosition
     *
     * @param givenPosition index which specific the position of elements in
     * queue
     * @return the elements or data from the queue
     */
    public T getEntry(int givenPosition);

    /**
     * Task: check whether the queue is empty or not
     *
     * @return true if the queue is empty, else false
     */
    public boolean isEmpty();

    /**
     * Task: replace the data and element at position index in the queue with
     * newEntry
     *
     * @param index to specific the position of elements in the queue
     * @param newEntry the object to replace the specific position of elements
     * in the queue
     */
    public void replace(int index, T newEntry);

    /**
     * Task: remove the data and elements at position index
     *
     * @param index to specific the position of elements in the queue
     */
    public void remove(int index);

    /**
     * Task: remove and empty all the data in the queue, loop all the  elements
     * in the queue and assign value to null
     */
    public void clear();
}
