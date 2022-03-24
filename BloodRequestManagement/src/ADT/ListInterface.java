/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

public interface ListInterface<T> {

    /**
     * Task: Adds a new entry to the end of the list. Entries currently in the
     * list are unaffected. The list's size will be increased by 1.
     *
     * @param newEntry the object to be added as a new entry
     * @return true if the addition is successful, or false if the list is full
     */
    public boolean add(T newEntry);

    /**
     * Task: Adds a new entry at a specified position within the list. Entries
     * originally at and above the specified position are at the next higher
     * position within the list. The list's size is increased by 1.
     *
     * @param newPosition an integer that specifies the desired position of the
     * new entry
     * @param newEntry the object to be added as a new entry
     * @return true if the addition is successful, or false if either the list
     * is full, newPosition < 1, or
     *          newPosition > getNumberOfEntries()+1
     */
    public boolean add(int newPosition, T newEntry);

    /**
     * Task: Replaces the entry at a given position in the list.
     *
     * @param givenPosition an integer that indicates the position of the entry
     * to be replaced
     * @param newEntry the object that will replace the entry at the position
     * givenPosition
     * @return true if the replacement occurs, or false if the list is empty
     */
    public T replace(int givenPosition, T newEntry);

    
    public T getRecord(int givenPosition);
    
    /**
     * Task: Removes the entry at a given position from the list. Entries
     * originally at positions higher than the given position are at the next
     * lower position within the list, and the list's size is decreased by 1.
     *
     * @param givenPosition an integer that indicates the position of the entry
     * to be removed
     * @return true if the value found in the list, false if the list is empty,
     * givenPosition < 1, or
     *          givenPosition > getNumberOfEntries()
     */
    public boolean remove(int givenPosition);

    /**
     * @param a indicates the position of the value in the list Task: Sort the
     * list in ascending order
     */
    public void sort(List a);

    /**
     * Task: Sees whether the list is empty.
     *
     * @return true if the list is empty, or false if not
     */
    public boolean isEmpty();

    /**
     *
     * @return array value capacity.
     */
    public int arraySize();

    /**
     * Task: Sees whether the list is full.
     *
     * @return true if the list is full, or false if not
     */
    public boolean isFull();

    

}

