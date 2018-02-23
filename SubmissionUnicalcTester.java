package hw8;


import org.junit.*;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class SubmissionUnicalcTester{
    private Unicalc calc1, calc2;
    private Unicalc calc;
    private String input;
    private List<String> emp = new ArrayList<String>();
    private AST oneVal, twoVal, threeVal, kmVal;
    private Quantity one, two, three, km;
    static int points = 0;
    public SubmissionUnicalcTester() {
        super();
    }
    
    /**
     * Standard Test Fixture.
     */
    @Before
    public void setUp() throws Exception {
        calc = new Unicalc(); 
        calc1 = new Unicalc();
        calc2 = new Unicalc();
        km = new Quantity(1.0, Arrays.asList("km"), emp);
        one = new Quantity(1.0, emp, emp);
        two = new Quantity(2.0, emp, emp);
        three = new Quantity(3.0, emp, emp);
        oneVal = new Value(one);
        twoVal = new Value(two);
        threeVal = new Value(three);
        kmVal = new Value(km);
    }
    @Test
    public void testS_def() {
        System.out.println("********testing testS_def.....");
        calc1.tokenize("def smoot 2");
        AST tree = calc1.S();
        assertEquals(tree, new Define("smoot", twoVal));        

    }
    @Test
    public void testS_L() {
        System.out.println("********testing testS_L.....");

        calc1.tokenize("1 + 2");
        calc2.tokenize("1 + 2");
        AST tree = calc1.S();
        assertEquals(tree, calc2.L());
    }
    @Test
    public void testL_normalize() {
        System.out.println("********testing testL_normalize.....");
        calc1.tokenize("# 1 km");
        AST tree = calc1.L();
        assertEquals(tree, new Normalize(new Product(oneVal, kmVal)));
    }
    @Test
    public void testL_E() {
        System.out.println("********testing testL_E.....");
        calc1.tokenize("1 + 2");
        calc2.tokenize("1 + 2");
        AST tree = calc1.L();
        assertEquals(tree, calc2.E());
    }
	@Test
    public void testE_plus() {
        System.out.println("********testing testE_plus.....");
        calc1.tokenize("1 + 2");
        AST tree = calc1.E();
        assertEquals(tree, new Sum(oneVal, twoVal));
    }
    @Test
    public void testE_minus() {
        System.out.println("********testing testE_minus.....");
        calc1.tokenize("1 - 2");
        AST tree = calc1.E();
        assertEquals(tree, new Difference(oneVal, twoVal));
    }
   
    @Test
    public void testP_muliply() {
        System.out.println("********testing testP_muliply.....");
        calc1.tokenize("2 * 3");
        AST tree = calc1.P();
        assertEquals(tree, new Product(twoVal, threeVal));
    }
    @Test
    public void testP_divide() {
        System.out.println("********testing testP_divide.....");
        calc1.tokenize("2 / 3");
        AST tree = calc1.P();
        assertEquals(tree, new Quotient(twoVal, threeVal));
    }
    @Test
    public void testK_negate() {
        System.out.println("********testing testK_negate.....");
        calc1.tokenize("-2");
        AST tree = calc1.K();
        assertEquals(tree, new Negation(twoVal));
    }
 @Test
    public void testK_Q() {
        System.out.println("********testing testK_Q.....");
        calc1.tokenize("km");
        calc2.tokenize("km");
        AST tree = calc1.K();
        assertEquals(tree, calc2.Q());
    }
    @Test
    public void testQ_R() {
        System.out.println("********testing testQ_R.....");
        calc1.tokenize("km");
        calc2.tokenize("km");
        AST tree = calc1.Q();
        assertEquals(tree, calc2.R());
    }  
}