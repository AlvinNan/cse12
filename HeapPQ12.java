

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
/** HeapPQ12 class that implements an unbounded array-backed heap structure and is
 * an extension of the Java Collections AbstractQueue class 
 * <p>
 * The elements of the heap are ordered according to their natural 
 * ordering,  HeapPQ12 does not permit null elements. 
 * The top of this heap is the minimal or maximal element (called min/max)  
 * with respect to the specified natural ordering.  
 * If multiple elements are tied for min/max value, the top is one of 
 * those elements -- ties are broken arbitrarily. 
 * The queue retrieval operations poll and peek 
 * access the element at the top of the heap.
 * <p>
 * A HeapPQ12 is unbounded, but has an internal capacity governing the size of 
 * an array used to store the elements on the queue. It is always at least as 
 * large as the queue size. As elements are added to a HeapPQ12, its capacity 
 * grows automatically. The details of the growth policy are not specified.
 * <p>
 * This class and its iterator implements the optional methods of the 
 * Iterator interface (including remove()). The Iterator provided in method 
 * iterator() is not guaranteed to traverse the elements of the HeapPQ12 in 
 * any particular order. 
 * <p>
 * Note that this implementation is not synchronized. Multiple threads 
 * should not access a HeapPQ12 instance concurrently if any of the 
 * threads modifies the HeapPQ12. 
 */
public class HeapPQ12<E extends Comparable <? super E>> extends 
    AbstractQueue<E> 
{   
    /* -- Define any private member variables here -- */
    /* In particular, you will need an ArrayList<E> to hold the elements
     * in the heap.  You will also want many more variables */
    private ArrayList<E> myHeap;
    private int length;
    private boolean max;
    
    /** O-argument constructor. Creates an empty HeapPQ12 with unspecified
     *  initial capacity, and is a min-heap 
     */ 
    public HeapPQ12()
    {
      length = 5;
      myHeap = new ArrayList<E>();   
      myHeap.add(null);
    }

    /** 
     * Constructor to build a min or max heap
     * @param isMaxHeap   if true, this is a max-heap, else a min-heap 
     */ 
    public HeapPQ12(boolean isMaxHeap)
    {
      length = 5;
      myHeap = new ArrayList<E>();
      myHeap.add(null);
      max = isMaxHeap; 
    }

    /** 
     * Constructor to build a with specified initial capacity 
     *  min or max heap
     * @param capacity      initial capacity of the heap.   
     * @param isMaxHeap     if true, this is a max-heap, else a min-heap 
     */ 
    public HeapPQ12(int capacity, boolean isMaxHeap)
    {
      length = capacity;
      myHeap = new ArrayList<E>();
      myHeap.add(null);
      max = isMaxHeap;
    }
    
    /** Copy constructor. Creates HeapPQ12 with a deep copy of the argument
     * @param toCopy      the heap that should be copied
     */
    public HeapPQ12 (HeapPQ12<E> toCopy)
    {
      ArrayList<E> oldHeap = toCopy.myHeap;
      this.myHeap = new ArrayList<E>();
      this.myHeap.add(null);
      for (int i = 1; i <= length; i++){
        myHeap.set(i, oldHeap.get(i));
      }
    }

    /* The following are defined "stub" methods that provide degenerate
     * implementations of methods defined as abstract in parent classes.
     * These need to be coded properly for HeapPQ12 to function correctly
     */

    /** Size of the heap
     * @return the number of elements stored in the heap
    */
    public int size()
    {
      return myHeap.size()-1; 
    }

    /** 
     * @return an Iterator for the heap 
    */
    public Iterator<E> iterator()
    {
      HeapPQ12Iterator heapIterator = new HeapPQ12Iterator();
      return heapIterator;
    }

    /** peek - see AbstractQueue for details 
     * 
     * @return Element at top of heap. Do not remove 
    */
    public E peek()
    {
      if(myHeap.isEmpty()) return null;
      
      return myHeap.get(1);
    }
    /** poll - see AbstractQueue for details 
     * @return Element at top of heap. And remove it from the heap. 
     *  return <tt>null</tt> if the heap is empty
    */
    public E poll()
    {
      if(myHeap.isEmpty()) return null;
      
      E root = myHeap.get(1);
      
      myHeap.set(1, myHeap.get(size()));
      myHeap.remove(size());
      trickleDown(1);
      
      return root;
    }
    /** offer -- see AbstractQueue for details
     * insert an element in the heap
     * @return true
     * @throws NullPointerException 
     *  if the specified element is null    
     */
    public boolean offer (E e) throws NullPointerException
    {
      if(e == null) throw new NullPointerException();
      
      if(this.size() == length) {
        ArrayList<E> oldHeap = myHeap;
        myHeap = new ArrayList<E>();
        myHeap.add(null);
        for (int i = 1; i <= length; i++){
          myHeap.set(i, oldHeap.get(i));
        }
        length = length*2;
      }
      myHeap.add(e);
      bubbleUp(this.size());    
 
      return true;
    }

    /* ------ Private Helper Methods ----
     *  DEFINE YOUR HELPER METHODS HERE
     */
    
    private void swap(int cindex, int pindex) {
      E child = myHeap.get(cindex);
      E parent = myHeap.get(pindex);
      myHeap.set(cindex, parent);
      myHeap.set(pindex, child);
    }
    
    private void bubbleUp(int index) {
      E child = myHeap.get(index);
      E parent = myHeap.get(index/2);
      if(index == 1) return;
      
      if(max) {
        if(child.compareTo(parent) >= 0) {
          swap(index, index/2);
          bubbleUp(index/2);
        }
        else return;
      }
      
      if(!max) {
        if(child.compareTo(parent) <= 0) {
          swap(index, index/2);
          bubbleUp(index/2);
        }
        else return;
      }
    }
    
    private void trickleDown(int index) {
      if (index*2 > this.size()) 
      return;
      
      E parent = myHeap.get(index);
      E left = myHeap.get(index*2);
      E right = null;
      if(index*2 < this.size()) {        
        right = myHeap.get(index*2+1);
      }
      E child = left;
      int childIndex = index*2;
          
      if(max) {
        if(right != null && left.compareTo(right) < 0) 
          child = right;
          childIndex = index*2 + 1;
        if(child.compareTo(parent) > 0) {
          swap(childIndex,index);
          trickleDown(childIndex);
        }
        else return;
      }
      
      if(!max) {
        if(right != null && left.compareTo(right) > 0) 
          child = right;
          childIndex = index*2 + 1;
        if(child.compareTo(parent) < 0) {
          swap(childIndex,index);
          trickleDown(childIndex);
        }
        else return;
      }
    }
    /** Inner Class for an Iterator **/
    /* stub routines */

    private class HeapPQ12Iterator implements Iterator<E>
    {
      private E next;
      private int idx;
      private boolean canRemove;
      
        private HeapPQ12Iterator()
        {
          next = myHeap.get(1);
          idx = 1;
          canRemove = false;
        }

        /* hasNext() to implement the Iterator<E> interface */
        public boolean hasNext()
        {
          if (next == myHeap.get(size()+1)) return false;
          
          return true;
        }

        /* next() to implement the Iterator<E> interface */
        public E next() throws NoSuchElementException
        {
          if(next == myHeap.get(size()+1)) throw new NoSuchElementException();
          idx++;
          canRemove = true;
          E prev = next;
          next = myHeap.get(idx);
          return prev; 
            
        }
        /* remove() to implement the Iterator<E> interface */
        public void remove() throws IllegalStateException
        {
          if(canRemove == false) throw new IllegalStateException();
          
          myHeap.set(idx-1, myHeap.get(size()));
          myHeap.set(size(), null);
          trickleDown(idx-1);
        }   
    }
} 
