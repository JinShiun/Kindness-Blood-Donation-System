/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author Chai Jia Hao
 * @author Gan Wei Zhe
 */
public interface SortedArrayListInterface<T> {

    /**
     * Task: add newEntry into the array list according to their order,
     * add in-between will require elements behind to shift back one place,
     * sort by alphabet
     *
     * @param newEntry object to be added as a newEntry
     * @return true if the newEntry is added successfully into the array list
     * , else return false
     */
    public boolean add(T newEntry);//used

    /**
     * Task: to get the current number of elements in the array list, if the 
     * elements removed from the list, the number of elements - 1
     *
     * @return the current number of entry in the array list
     */
    public int getNumberOfEntries();//used

    /**
     * Task: get the elements from the array list according to the index
     * position
     * 
     * @param index which specific the position of elements in array list
     * @return the elements or data from the array list
     */
    public T getEntry(int index);//used

    public void update(T newEntry, int index);

    public boolean remove(T anEntry);

    public boolean contains(T anEntry);

    public void clear();

    public boolean isEmpty();
}
