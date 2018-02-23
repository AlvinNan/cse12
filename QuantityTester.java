/** This is the tester for class Quantity.
 * @author Nanxiong Wang
 * A92038237
 * cs12foj
 */

package hw8;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class QuantityTester {
	private Quantity Quantity1;
	private Quantity Quantity2;
	private Quantity Quantity3;
	private Map<String,Quantity> db;
	@Before
	public void setUp() {
		Quantity1 =  new Quantity();	
		db = new HashMap<String,Quantity>();
		List<String> emp = new ArrayList<String>();  	            
		db.put("km", new Quantity(1000, Arrays.asList("meter"), emp));
		db.put("day", new Quantity(24, Arrays.asList("hour"), emp));
		db.put("hour", new Quantity(60, Arrays.asList("minute"), emp));
		db.put("minute", new Quantity(60, Arrays.asList("second"), emp));
		db.put("hertz", new Quantity(1, emp, Arrays.asList("second")));
		db.put("kph", new Quantity(1, Arrays.asList("km"),                  
		                           Arrays.asList("hour")));
	}
	//Test no-argument constructor
	@Test
	public void testQuantity1() {
		Quantity temp = new Quantity();
		assertEquals("checking Quantity1",true,Quantity1.equals(temp));
	}
	//Test single argument constructor
	@Test
	public void testQuantity2() {
		Quantity2 = new Quantity(Quantity1);
		assertEquals("checking Quantity2",true,Quantity2.equals(Quantity1));
	}
	//Test 3-argument constructor
	@Test
	public void testQuantity3() {
		Quantity3 = new Quantity(9.8, Arrays.asList("m"), Arrays.asList("s"));
		assertEquals("checking Quantity3","9.8 m s^-1",Quantity3.toString());
	}
	@Test
	public void Quantity3Eception() {
		try
		{
			new Quantity(1, null, Arrays.asList("s", "s"));
			fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e)
		{
		}
	}
	//Test mul
	@Test
	public void testMul() {
		Quantity newQuantity;
		newQuantity = Quantity1.mul(Quantity1);
		assertEquals("checking mul","1.0",newQuantity.toString());
	}
	//Test mul exception
	@Test
	public void testMulException() {
		try
		{
			Quantity newQuantity;
			newQuantity = Quantity1.mul(null);
			fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e)
		{
		}
	}
	//Test div
	@Test
	public void testDiv() {
		Quantity newQuantity;
		newQuantity = Quantity1.div(Quantity1);
		assertEquals("checking div","1.0",newQuantity.toString());
	}
	//Test div exception1
	@Test
	public void testDivException1() {
		try
		{
			Quantity newQuantity;
			newQuantity = Quantity1.div(null);
			fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e)
		{
		}
	}
	//Test div exception2
	@Test
	public void testDivException2() {
		try
		{
			Quantity1 = Quantity1.sub(Quantity1);
			Quantity1.div(Quantity1);
			fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e)
		{	
		}
	}
	//Test pow
	@Test
	public void testPow() {
		Quantity newQuantity;
	    newQuantity = new Quantity(5, Arrays.asList( "km"),
                   Arrays.asList("hour"));
	    Quantity testQuantity = new Quantity(25, Arrays.asList("km","km"),
                Arrays.asList("hour","hour"));
		assertEquals("checking pow",testQuantity,newQuantity.pow(2));
	}
	//Test add
	@Test
	public void testAdd() {
		Quantity newQuantity;
		newQuantity = Quantity1.add(Quantity1);
		assertEquals("checking add","2.0",newQuantity.toString());
	}
	//Test add exception
	@Test
	public void testAddException() {
		try
		{
		Quantity q1 = new Quantity(1,Arrays.asList("m"),new ArrayList<String>());
		Quantity q2 = new Quantity(1,Arrays.asList("kg"),new ArrayList<String>());
		q1.add(q2);
		fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e)
		{
		}
	}
	//Test sub
	@Test
	public void testSub() {
		Quantity q1 = new Quantity(2,Arrays.asList("m"),new ArrayList<String>());
		Quantity q2 = new Quantity(1,Arrays.asList("m"),new ArrayList<String>());
		Quantity newQuantity;
		newQuantity = q1.sub(q2);
		assertEquals("checking sub","1.0 m",newQuantity.toString());
	}
	//Test sub exception1
	@Test
	public void testSubException1() {
		try
		{
			Quantity q1 = new Quantity(1,Arrays.asList("m"),new ArrayList<String>());
			Quantity q2 = new Quantity(1,Arrays.asList("kg"),new ArrayList<String>());
			q1.sub(q2);
			fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e)
		{	
		}
	}
	//Test sub exception2
	@Test
	public void testSubException2() {
		try
		{
			Quantity1.sub(null);
			fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e)
		{	
		}
	}
	//Test negate
	@Test
	public void testNegate() {
		Quantity3 = new Quantity(9.8, Arrays.asList("m"), Arrays.asList("s"));
		Quantity newQuantity;
		newQuantity = Quantity3.negate();
		assertEquals("cheking negate","-9.8 m s^-1",newQuantity.toString());
	}
    //Test normalizedUnit
	@Test
	public void testNormalizedUnit() {
		assertEquals("checking normalizedUnit","1000.0 meter",Quantity.normalizedUnit("km", db).toString());
		assertEquals("checking normalizedUnit","1.0 smoot",Quantity.normalizedUnit("smoot", db).toString());
	}
	//Test normalize
	@Test
	public void testNormalize() {		
		Quantity q1 = new Quantity(30.0,Arrays.asList("km","km"),Arrays.asList("hour"));
		assertEquals("checking normalize","8333.33333 meter^2 second^-1",q1.normalize(db).toString());	
	}
	//Test equals
	@Test
	public void testEquals() {
		Quantity q1 = new Quantity(0.111112,Arrays.asList("inch"),new ArrayList<String>());
		Quantity q2 = new Quantity(0.111113,Arrays.asList("inch"),new ArrayList<String>());
		Quantity q3 = new Quantity(0.111118,Arrays.asList("inch"),new ArrayList<String>());
		Quantity q4 = new Quantity(0.111112,Arrays.asList("meter"),new ArrayList<String>());
		assertEquals("checking equals",true,q1.equals(q2));
		assertEquals("checking not equals",false,q1.equals(q3));
		assertEquals("checking not equals",false,q1.equals(q4));
	}
	//Test hashCode
	@Test
	public void testHashCode() {
		Quantity q1 = new Quantity(1,Arrays.asList("inch"),new ArrayList<String>());
		Quantity q2 = new Quantity(1,Arrays.asList("inch"),new ArrayList<String>());
		int hash1 = q1.toString().hashCode();
		int hash2 = q2.toString().hashCode();
		assertEquals("checking HashCode",hash1,hash2);
	}
	
	
}
