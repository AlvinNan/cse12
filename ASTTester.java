/** This is the tester for class AST.
 * @author Nanxiong Wang
 * A92038237
 * cs12foj
 */

package hw8;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;

public class ASTTester {
	Map<String,Quantity> db;
	AST left;
	AST right;
	Quantity q1;
	Quantity q2;
	@Before
	public void setUp() {
		q1 = new Quantity(1.0, Arrays.asList("m"), new ArrayList<String>());
		q2 = new Quantity(2.0, new ArrayList<String>(),Arrays.asList("s"));
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
	//Test product
	@Test
	public void testProduct() {
		left = new Value(q1);
		right = new Value(q2);
		AST product = new Product(left, right);
		assertEquals("checking product","2.0 m s^-1",product.eval(db).toString());
	}
	//Test quotient
	@Test
	public void testQuotient() {
		left = new Value(q1);
		right = new Value(q2);
		AST quotient = new Quotient(left, right);
		assertEquals("checking quotient","0.5 m s",quotient.eval(db).toString());
	}
	//Test sum
	@Test
	public void testSum() {
		Quantity q3 = new Quantity(1.0, Arrays.asList("m"), new ArrayList<String>());
		left = new Value(q1);
		right = new Value(q3);
		AST sum = new Sum(left, right);
		assertEquals("checking sum","2.0 m",sum.eval(db).toString());
	}
	//Test difference
	@Test
	public void testDifference() {
		Quantity q3 = new Quantity(2.0, Arrays.asList("m"), new ArrayList<String>());
		left = new Value(q3);
		right = new Value(q1);
		AST difference = new Difference(left, right);
		assertEquals("checking difference","1.0 m",difference.eval(db).toString());
	}
	//Test power
	@Test
	public void testPower() {
		int exp = 2;
		left = new Value(q1);
		AST power = new Power(left, exp);
		assertEquals("checking power","1.0 m^2",power.eval(db).toString());
	}
	//Test negation
	@Test
	public void testNegation() {
		left = new Value(q1);
		AST negation = new Negation(left);
		assertEquals("checking negation","-1.0 m",negation.eval(db).toString());
	}
	//Test normalize
	@Test
	public void testNormalize() {
		q1 = new Quantity(2.0, Arrays.asList("km"), new ArrayList<String>());
		left = new Value(q1);
		AST normalize = new Normalize(left);
		assertEquals("checking normalize","2000.0 meter",normalize.eval(db).toString());
	}
	//Test define
	@Test
	public void testDefine() {
		q1 = new Quantity(2.0, Arrays.asList("in"), new ArrayList<String>());
		String name = "smoot";
		left = new Value(q1);
		AST define = new Define(name,left);
		assertEquals("checking define","2.0 in",define.eval(db).toString());
	}
	//Test value
	@Test
	public void testValue() {
		left = new Value(q1);
		assertEquals("checking value","1.0 m",left.eval(db).toString());
	}
}
