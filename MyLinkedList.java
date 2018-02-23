/** Class MyLinkedList creates a doubly-linked list that has series of
 * nodes that point to a node before and a node after itself.
 * @author Nanxiong Wang, Yanqi Han
 * cs12foj, cs12fmm
 */


package hw2;
 

import java.util.*;

public class MyLinkedList<E> extends AbstractList<E> {
  
  private int nelems;
  private Node head;
  private Node tail;
  
  
  /** class Node implementation */
  protected class Node {
    E data;
    Node next;
    Node prev;
    
    /** Constructor to create singleton Node */
    public Node(E element)
    {
      this.data = element;
    }
    
    /** Constructor to create singleton link it between previous and next 
      *   @param element Element to add, can be null
      *   @param prevNode predecessor Node, can be null
      *   @param nextNode successor Node, can be null 
      */
    public Node(E element, Node prevNode, Node nextNode)
    {
      data = element;
      this.next = nextNode;
      this.prev = prevNode;
      if(nextNode != null ) {
    	 nextNode.prev = this;
      }

      if(prevNode != null){
         prevNode.next = this; 
      }
      
      
    }
    
    /** Remove this node from the list. Update previous and next nodes */
    public void remove()
    {
      Node prev = this.prev;
      Node next = this.next;
      prev.next = next;
      next.prev = prev;
      nelems--;
    }
    
    /** Set the previous node in the list
      *  @param p new previous node
      */
    public void setPrev(Node p)
    {
      if(p == null) return;
      this.prev = p;
      p.next = this;
    }
    

	
    /** Set the next node in the list
      *  @param n new next node
      */
    public void setNext(Node n)
    { 
      if(n == null) return;
      this.next = n;
      n.prev = this;
    }

    
    /** Set the element 
      *  @param e new element 
      */
    public void setElement(E e)
    {
      this.data = e;
    }
    
    /** Accessor to get the next Node in the list */
    public Node getNext()
    {
      return this.next;
    }
    
    /** Accessor to get the prev Node in the list */
    public Node getPrev()
    {      
      return this.prev;
    } 
    
    /** Accessor to get the Nodes Element */
    public E getElement()
    {
      return this.data; 
    } 
  }
  
  /** ListIterator implementation */ 
  protected class MyListIterator implements ListIterator<E> {
    
    private boolean forward;
    private boolean canRemove;
    private Node left,right; // Cursor sits between these two nodes
    private int idx; // Tracks current position. what next() would return 

    /**
     * Constructor to create a new myListIterator
     */
    public MyListIterator()
    {
      forward = false;
      canRemove = false;
      left = head;
      right = head.getNext();
      idx = 0;
		/* Add your implementation here */
    }

    @Override
    /**
     * Add a node to the list and update the previous and next node
     * @param e new data of the new node
     * @throws IllegalArgumentException
     */
    public void add(E e) throws  IllegalArgumentException
    {
	  if(e == null) throw new IllegalArgumentException();
	 
	  Node currNode = new Node(e);
	  right.setPrev(currNode);
	  currNode.setNext(right);
	  left.setNext(currNode);
	  currNode.setPrev(left);
	  forward = true;
	  canRemove = false;
	  nelems ++;
    }

    @Override
    /**
     * to check if the list have next node.
     * @return boolean, true it has next node,false if it doesn't
     */
    public boolean hasNext()
    {
      if(right == tail) return false;
      
      return true;
    }
    
    @Override
    /**
     * to check if the list has previous node.
     * @return boolean, true it has previous node,false if it doesn't
     */
    public boolean hasPrevious()
    {
      if(right.getPrev() == head) return false;

      else return true;
    }

    @Override
    /**
     * To move the iterator to the next position and return
     * the data in current node.
     * @return E the data in current node
     * @throws NoSuchElementException
     */
    public E next() throws NoSuchElementException
    {
      if(right == tail) throw new NoSuchElementException();
    	
      left = right;
      right = right.getNext();
      idx++;
      canRemove = true;
      forward = true;
      return left.getElement();
    }

    @Override
    /**
     * To return the current index
     * @return int number of current index
     */
    public int nextIndex()
    {
      if (this.hasNext() == false) return idx+1;
      
      else return idx;
    }

    @Override
    /**
     * Move the iterator the previous position and return 
     * the data in the left.
     * @return E data in the left node
     * @throws NoSuchElementException
     */
    public E previous() throws NoSuchElementException
    {
      if(right.getPrev() == head) throw new NoSuchElementException();
      
      right = left;
      left = right.getPrev();
      idx--;
      canRemove = true;
      forward = false;
      return right.getElement();
    }
    
    @Override
    /**
     * Get the index of previous position
     * @return int the number of the index
     */
    public int previousIndex()
    {
      if (this.hasPrevious() == false) return -1;      

      else return idx-1; 
    }

    @Override
    /**
     * To remove the node in the position based on the previous 
     * call
     * @throws IllegalStateException
     */
    public void remove() throws IllegalStateException
    {
	  if(canRemove == false) throw new IllegalStateException();
	  
	  else if(canRemove == true && forward == true)
	  {
        Node temp = left.getPrev();
        temp.setNext(right);
        right.setPrev(temp);
        left.setPrev(null);
        left.setNext(null);
        left = temp;
	  }
	  
	  else if(canRemove == true && forward == false)
	  {
        Node temp = right.getNext();
        temp.setPrev(left);
	    left.setNext(temp);
	    right.setNext(null);
	    right.setPrev(null);
	    right = temp;
	  }	
	  nelems--;
    }

    @Override
    /**
     * Set the element in current Node to e
     * @param E the data user want to put in
     * @throws IllegalStateException
     */
    public void set(E e) 
      throws IllegalArgumentException,IllegalStateException
    {
      if(e == null) throw new IllegalArgumentException();
		
      else if(e != null && canRemove == false) throw new IllegalStateException();
		
	  else if(e != null && canRemove == true)
	  {
	    if(forward = true) this.left.setElement(e);
            
	    else if(forward == false) this.right.setElement(e);
	  }	
    }
    
  }
  
  
  //  Implementation of the MyLinkedList Class
  
  
  /** Only 0-argument constructor is define */
  public MyLinkedList()
  {
    head = new Node(null);
    tail = new Node(null);
    head.setNext(tail);
    tail.setPrev(head);
    nelems = 0;
  }

  @Override
  /**
   * To get the size of myLinkedList
   * @return integer the size of myLinkedList
   */
  public int size()
  {
	
    return nelems; 
  }
  
  @Override
  /**
   * To get the element in the designated index position
   * @return the element in the node
   * @throws IndexOutOfBoundsException
   */
  public E get(int index) throws IndexOutOfBoundsException
  {
    if(index > this.size() || index < 0 || this.size() == 0 ) throw new IndexOutOfBoundsException();
    
    Node currNode = getNth(index);
	return currNode.getElement();
  }
  
  @Override
  /** Add an element to the list 
    * @param index  where in the list to add
    * @param data data to add
    * @throws IndexOutOfBoundsException
    * @throws IllegalArgumentException
    */ 
  public void add(int index, E data) 
  throws IndexOutOfBoundsException,IllegalArgumentException
  {
    if( data == null) throw new IllegalArgumentException();
	if(index > this.size() || index < 0) throw new IndexOutOfBoundsException();

	Node currNode = getNth(index);
       

	if(this.size() == 0)
	{
	  new Node(data, head, tail);		
	}
	
	else
	{
	  new Node(data, currNode.getPrev(), currNode);		
	}	
	nelems++;
  }

  /** Add an element to the end of the list 
    * @param data data to add
    * @throws IllegalArgumentException
    */ 
  public boolean add(E data) throws IllegalArgumentException
  {
    if (data == null) throw new IllegalArgumentException();

    new Node(data, tail.getPrev(), tail); 
    
    return true;  
  }
  
  /** Set the element at an index in the list 
    * @param index  where in the list to add
    * @param data data to add
    * @return element that was previously at this index.
    * @throws IndexOutOfBoundsException
    * @throws IllegalArgumentException
    * @throws IllegalStateExceptoin
    */ 
  public E set(int index, E data) 
    throws IndexOutOfBoundsException,IllegalArgumentException
  {
    if(data == null) throw new IllegalArgumentException();
    if(index >= this.size() || index < 0) throw new IndexOutOfBoundsException();
    
    Node currNode = head.getNext();
    
    while(index != 0)
    {
    	currNode = currNode.getNext();
    	index--;
    }
    
    E dataPrev = currNode.getElement();
    currNode.setElement(data);
  
	return dataPrev;
  }
  
  /** Remove the element at an index in the list 
    * @param index  where in the list to add
    * @return element the data found
    * @throws IndexOutOfBoundsException
    * @throws IllegalStateException
    */ 
  public E remove(int index) throws IndexOutOfBoundsException
  {
	if(index >= this.size() || index < 0) throw new IndexOutOfBoundsException();
	
	Node currNode = getNth(index); 
	currNode.remove();
	nelems--;	  
	
	return currNode.getElement();
  }
  
  /** Clear the linked list */
  public void clear()
  {
    this.head.setNext(tail);
    this.tail.setPrev(head);
    nelems = 0;
  }
  
  /** Determine if the list empty 
    * @return true if empty, false otherwise */
  public boolean isEmpty()
  {
	if(head.getNext() == tail) return true;  
    return false;
  }
  
  
  // Helper method to get the Node at the Nth index
  private Node getNth(int index) 
  {
    Node currNode = this.head;
	
    for(int i = 0; i <= index; i++)
    	currNode = currNode.getNext();
    
    return currNode;
  }
  
  public Iterator<E> iterator()
  {
  return new MyListIterator();
  }
  public ListIterator<E> listIterator()
  {
  return new MyListIterator();
  }
      
}

// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4

