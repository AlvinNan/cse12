package hw4;
import org.junit.*;
import static org.junit.Assert.*;
public class HashtableTester {
	private HashTable testHashTable1;
	@Before
	public void setUp()
	{
		testHashTable1 = new HashTable(1);
	}
	@Test
	public void testInsert()
	{
		assertEquals("checking insert",true,testHashTable1.insert("abc"));
		assertEquals("Checking contains after insert",true,testHashTable1.contains("abc"));
	}
	@Test
	public void testDelete()
	{
    testHashTable1.insert("abc");
		assertEquals("Checking delete",true,testHashTable1.delete("abc"));
		assertEquals("Checking contains after delete",false,testHashTable1.contains("abc"));
	}
	@Test
	public void testGetSize()
	{
		testHashTable1.insert("abc");
		testHashTable1.insert("pqr");
		testHashTable1.insert("xyz");
		assertEquals("Checking getSize",new Integer(3),new Integer(testHashTable1.getSize()));
	}
	
	@Test
	public void testGetSize2()
	{
	  testHashTable1.insert("abc");
	  assertEquals("Checking getSize",new Integer(1),new Integer(testHashTable1.getSize()));
	  testHashTable1.delete("abc");
	  assertEquals("Checking getSize",new Integer(0),new Integer(testHashTable1.getSize()));
	}
	
	@Test
	public void testInsertFalse()
	{
	  assertEquals("checking insert",true,testHashTable1.insert("abc"));
	  assertEquals("checking insert",false,testHashTable1.insert("abc"));
	}
	
	@Test
	public void testDeleteFalse()
	{
    assertEquals("Checking delete",false,testHashTable1.delete("abc"));
	}
	
	@Test
  public void testContains()
  {       
    testHashTable1.insert("abc");
    assertEquals("Checking contains after insert",true,testHashTable1.contains("abc"));                
    testHashTable1.delete("abc");
	  assertEquals("Checking contains after delete",false,testHashTable1.contains("abc")); 
	}
        
	@Test
  public void testInsertException()
  {
    try
    {
      testHashTable1.insert(null);
      fail("Should have generated an exception");
    }
    catch(NullPointerException e)
    {
    }
  }
      
	@Test
  public void testDeleteException()
  {
    try
    {
      testHashTable1.delete(null); 
      fail("Should have generated an exception");
    }
    catch(NullPointerException e)
    {
    }
  }
       
	@Test
  public void testContainsException()
  {
    try
    {
      testHashTable1.contains(null);
      fail("Should have generated an exception");
    }
    catch(NullPointerException e)
    {
    }
  }
}

