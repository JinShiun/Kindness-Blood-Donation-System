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
public interface QueueInterface<T> {

    public void enqueue(T newEntry);

    public int getNumEntry();
    
    public int getTotalEntry();

    public T getEntry(int givenPosition);

    public boolean isEmpty();
    
    public void replace(int index, T newEntry);

    public String toString();

    public boolean equals(Object obj);

    public int hashCode();

    public void remove(int num);

    
}
