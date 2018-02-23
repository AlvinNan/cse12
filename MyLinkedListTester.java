package hw2;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;


/**
 *  Title: class MyLinkedListTester
 *  Description: JUnit test class for MyLinkedList class
 *  @author Philip Papadopoulos, Christine Alvarado
 *  @version 3.0 05-April-2015
 * */

/*
 * You should modify the information above to add your names and CSE12 accounts.
 * 
 * We have provided 7 tests to help you get familiar. 
 *
 * As a part of the Milestone submission, you have to add 10 new tests.
 * These tests will ONLY be graded against the correct implementation of MyLinkedList.
 * However, you will also be given feedback on how your tests behave on the 
 * buggy implementation of MyLinkedList. 
 *
 * After the Milestone submission, you will be able to view each other's tests which you
 * can use to add or improve your tests for the final submission. At the final submission,
 * if you are using someone else's tests, we expect you to give the required attribution.
 * We also expect you to write a README file that explains in greater detail why you used
 * the test. 
 * 
 * For the Final Submission, your tests will be graded against both the correct and
 * buggy implementation of MyLinkedList. At the time of final submission, you will be
 * given feedback on how your tests behave against few but not all, buggy implementations.
 *
 * Finally, when your tester is complete, you will rename it MyLinkedListTester.java 
 * (renaming LinkedList to MyLinkedList everywhere in the file) so that you can
 * use it to test your MyLinkedList and MyListIterator classes.
 */
public class MyLinkedListTester
{
  private MyLinkedList<Integer> empty ;
  private MyLinkedList<Integer> one ;
  private MyLinkedList<Integer> several ;
  private MyLinkedList<String>  slist ;
  static final int DIM = 5;
  static final int FIBMAX = 30;
  
  /**
   * Standard Test Fixture. An empty list, a list with one entry (0) and 
   * a list with several entries (0,1,2)
   */ 
  @Before
  public void setUp()
  {
    empty = new MyLinkedList<Integer>();
    one = new MyLinkedList<Integer>();
    one.add(0,new Integer(0));
    several = new MyLinkedList<Integer>() ;
    // List: 1,2,3,...,Dim
    for (int i = DIM; i > 0; i--)
      several.add(0,new Integer(i));
    
    // List: "First","Last"
    slist = new MyLinkedList<String>();
    slist.add(0,"First");
    slist.add(1,"Last");
  }
  /** Test if heads of the lists are correct */
  @Test
  public void testGetHead()
  {
    assertEquals("Check 0",new Integer(0),one.get(0)) ;
    assertEquals("Check 0",new Integer(1),several.get(0)) ;
  }
  
  /** Test if size of lists are correct */
  @Test
  public void testListSize()
  {
    assertEquals("Check Empty Size",0,empty.size()) ;
    assertEquals("Check One Size",1,one.size()) ;
    assertEquals("Check Several Size",DIM,several.size()) ;
  }
  
  /** Test setting a specific entry */
  @Test
  public void testSet()
  {
    slist.set(1,"Final");
    assertEquals("Setting specific value", "Final",slist.get(1));
  }
  
  /** Test isEmpty */
  @Test
  public void testEmpty()
  {
    assertTrue("empty is empty",empty.isEmpty()) ;
    assertTrue("one is not empty",!one.isEmpty()) ;
    assertTrue("several is not empty",!several.isEmpty()) ;
  }

  /** Test out of bounds exception on get */
  @Test
  public void testGetException()
  {
    try 
    {
      empty.get(0);
      // This is how you can test when an exception is supposed 
      // to be thrown
      fail("Should have generated an exception");  
    }
    catch(IndexOutOfBoundsException e)
    {
      //  normal
    }
  }

  
  /** Test iterator on empty list and several list */
  @Test
  public void itestIterator()
  {
    int counter = 0 ;
    ListIterator<Integer> iter;
    for (iter = empty.listIterator() ; iter.hasNext(); )
    {
      fail("Iterating empty list and found element") ;
    }
    counter = 0 ;
    for (iter = several.listIterator() ; iter.hasNext(); iter.next())
      counter++;
    assertEquals("Iterator several count", counter, DIM);
  }
  
  
  /** test Iterator Fibonacci.
    * This is a more holistic test for the iterator.  You should add
    * several unit tests that do more targeted testing of the individual
    * iterator methods.  */
  @Test
  public void testIteratorFibonacci()
  {
    
    MyLinkedList<Integer> fib  = new MyLinkedList<Integer>();
    ListIterator<Integer> iter;
    // List: 0 1 1 2 3 5 8 13 ... 
    // Build the list with integers 1 .. FIBMAX
    int t, p = 0, q = 1;
    fib.add(0,p);
    fib.add(1,q);
    for (int k = 2; k <= FIBMAX; k++)
    {
      t = p+q;
      fib.add(k,t);
      p = q; q = t; 
    }
    // Now iterate through the list to near the middle, read the
    // previous two entries and verify the sum.
    iter = fib.listIterator();
    int sum = 0;
    for (int j = 1; j < FIBMAX/2; j++)
      sum = iter.next();
    iter.previous();
    assertEquals(iter.previous() + iter.previous(),sum);
    // Go forward with the list iterator
    assertEquals(iter.next() + iter.next(),sum);
  }
  
 
  /** Test remove on several list */
  @Test
  public void testRemove()
  {
    several.remove(0);
    assertEquals("Check 0", new Integer(2), several.get(0));
  }


  /** Test if tails of the lists are correct */
  @Test
  public void testGetTail()
  {
    assertEquals("Check 0", new Integer(0), one.get(0));
    assertEquals("Check 4", new Integer(5), several.get(4));
    assertEquals("Check 1", "Last", slist.get(1));
  }


  /** Test out of bounds exception on remove */
  @Test
  public void testRemoveException()
  {
    try
    {
      empty.remove(0);
      fail("Should have generated an exception");
    }
    catch(IndexOutOfBoundsException e)
    {
    //  normal
    }
  }


  /** Test add on one list */
  @Test
  public void testAdd()
  {
    one.add(new Integer(1));
    assertEquals("Check 1", new Integer(1), one.get(1));
  }


  /** Test out of bounds exception on add */
  @Test
  public void testAddException()
  {
    try
    {
      one.add(-1, new Integer(1));
      fail("Should have generated an exception");
    }
    catch(IndexOutOfBoundsException e)
    {
    //  normal
    }
  }


  /** Test out of bounds exception on set */
  @Test
  public void testSetException()
  {
    try
    {
      one.set(1, new Integer(1));
      fail("Should have generated an exception");
    }
    catch(IndexOutOfBoundsException e)
    {
    //  normal
    }
  }


  /** Test next on several list */
  @Test
  public void testNext()
  {
    ListIterator<Integer> severalIterator;
    severalIterator = several.listIterator();
    assertEquals("Check 0", several.get(0), severalIterator.next());
  }


  /** Test hasNext on several list */
  @Test
  public void testHasNext()
  {
    ListIterator<Integer> severalIterator;
    severalIterator = several.listIterator();
    assertEquals("Check 0 HasNext", true, severalIterator.hasNext());
  }


  /** Test previous on several list */
  @Test
  public void testPrevious()
  {
    ListIterator<Integer> severalIterator;
    severalIterator = several.listIterator();
    severalIterator.next();
    assertEquals("Check 0", several.get(0), severalIterator.previous());
  }

  
  /** Test hasPrevious on several list */
  @Test
  public void testHasPrevious()
  {
    ListIterator<Integer> severalIterator;
    severalIterator = several.listIterator();
    assertEquals("Check 0 HasPrevious", false, severalIterator.hasPrevious());
  } 



}
