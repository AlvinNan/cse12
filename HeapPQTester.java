package hw6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class HeapPQTester {

	private HeapPQ12<Integer> testHeapPQ12a;
	private HeapPQ12<Integer> testHeapPQ12b;
	
	
	@Before
	public void setUp() {
		testHeapPQ12a = new HeapPQ12<Integer>();
		int offerInt1 = 1;
		int offerInt2 = 2;
		int offerInt3 = 3;
		testHeapPQ12a.offer(offerInt1);
		testHeapPQ12a.offer(offerInt2);
		testHeapPQ12a.offer(offerInt3);
	}
	
	@Test
	public void testZeroConstructor(){
		assertEquals("checking zeroArgument",3,testHeapPQ12a.size());
		assertEquals("checking zeroArgument",new Integer(1),
				testHeapPQ12a.peek());
	}
	
	@Test
	public void testCopy() {
		HeapPQ12<Integer> testHeapPQ12c = 
				new HeapPQ12<Integer>(testHeapPQ12a);
	    testHeapPQ12c.poll();
	    assertEquals("checking copy",3,testHeapPQ12a.size());
	    
	}
	@Test
	public void testpoll() {
		int result = testHeapPQ12a.poll();
		assertEquals("checking poll",1,result);
		assertEquals("checking poll",2,testHeapPQ12a.size());
	}
	
	@Test
	public void testlength() {
		int offerInt4 = 4;
		int offerInt5 = 5;
		int offerInt6 = 6;
		testHeapPQ12a.offer(offerInt4);
		testHeapPQ12a.offer(offerInt5);
		testHeapPQ12a.offer(offerInt6);
		assertEquals("checking length",6,testHeapPQ12a.size());
		assertEquals("checking length",new Integer(1),testHeapPQ12a.peek());
	}
	
	@Test
	public void testofferexception() {
		 try
		    {
		      testHeapPQ12a.offer(null);
		      fail("Should have generated an exception");
		    }
		    catch(NullPointerException e)
		    {
		    }
	}
	
	
	
	
	@Test
	public void testoffer() {
		int offerInt = 0;
		testHeapPQ12a.offer(offerInt);
		int result = (int) testHeapPQ12a.peek();
		assertEquals("checking offer",0,result);
	}
	
	
	@Test
	public void testSize() {
		int result = testHeapPQ12a.size();
		assertEquals("checking size",3,result);
	}
	
	@Test
	public void testpeek() {
		int result = testHeapPQ12a.peek();
		assertEquals("checking peek",1,result);
		assertEquals("checking peek",3,testHeapPQ12a.size());
	}
	
	@Test
	public void testMaxHeap(){
		testHeapPQ12b = new HeapPQ12<Integer>(true);
		int offerInt1 = 1;
		int offerInt2 = 2;
		int offerInt3 = 3;
		testHeapPQ12b.offer(offerInt1);
		testHeapPQ12b.offer(offerInt2);
		testHeapPQ12b.offer(offerInt3);
		int result = testHeapPQ12b.peek();
		assertEquals("checking maxheap",3,result);
		
	}
	
	@Test
	public void testMinHeap(){
		testHeapPQ12b = new HeapPQ12<Integer>(false);
		int offerInt1 = 1;
		int offerInt2 = 2;
		int offerInt3 = 3;
		testHeapPQ12b.offer(offerInt1);
		testHeapPQ12b.offer(offerInt2);
		testHeapPQ12b.offer(offerInt3);
		int result = testHeapPQ12a.peek();
		assertEquals("checking minheap",1,result);
	}
	
	}





    
