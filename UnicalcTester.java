/** This is the tester for class Unicalc.
 * @author Nanxiong Wang
 * A92038237
 * cs12foj
 */

package hw8;
import static org.junit.Assert.assertEquals;

import org.junit.*;
public class UnicalcTester {
	Unicalc unicalc;
	
	@Before
	public void setUp() {
		unicalc = new Unicalc();
	}
	//Test S
	@Test
	public void testS() {
		unicalc.tokenize("def wow 66m");
		AST ast = unicalc.S();
		assertEquals("checking S","Define(wow,Product(Value(66.0),Value(1.0 m)))",ast.toString());
	}
	//Test L
	@Test
	public void testL() {
		unicalc.tokenize("# 60Hz*30s");
		AST ast = unicalc.L();
		assertEquals("checking L","Normalize(Product(Product(Value(60.0),Value(1.0 Hz)),Product("
				+ "Value(30.0),Value(1.0 s))))",ast.toString());
	}
	//Test E
	@Test
	public void testE() {
		unicalc.tokenize("5m+3m");
		AST ast = unicalc.E();
		assertEquals("checking E","Sum(Product(Value(5.0),Value(1.0 m)),Product(Value(3.0),Value("
				+ "1.0 m)))",ast.toString());
	}
	//Test P
	@Test
	public void testP() {
		unicalc.tokenize("60Hz*30s");
		AST ast = unicalc.P();
		assertEquals("checking P","Product(Product(Value(60.0),Value(1.0 Hz)),Product(" 
				+ "Value(30.0),Value(1.0 s)))",ast.toString());
	}
	//Test K
	@Test
	public void testK() {
		unicalc.tokenize("-(60m+10m)");
		AST ast = unicalc.K();
		assertEquals("checking K","Negation(Sum(Product(Value(60.0),Value(1.0 m)),Product(Value("
				+ "10.0),Value(1.0 m))))",ast.toString());
	}
	//Test Q
	@Test
	public void testQ() {
		unicalc.tokenize("2 3");
		AST ast = unicalc.Q();
		assertEquals("checking Q","Product(Value(2.0),Value(3.0))",ast.toString());
	}
	//Test R
	@Test
	public void testR() {
		unicalc.tokenize("(3in)^2");
		AST ast = unicalc.R();
		assertEquals("checking R","Power(Product(Value(3.0),Value(1.0 in)),2)",ast.toString());
	}
	
	
}
