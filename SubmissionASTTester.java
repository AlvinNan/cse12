package hw8;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * Title: class Quantity Tester Description: JUnit test class for AST class
 *
 * @author Christine Alvarado
 * @version 1.0 20-May-2014
 * Emma: added more test case...
 */
public class SubmissionASTTester {
    private Map<String, Quantity> db = new HashMap<String, Quantity>();
    private List<String> emp = new ArrayList<String>();
    private AST fiveKphValue, twoKmValue, tenKmValue;
    private Quantity fiveKph, twoKm, tenKm;
  
  private Map<String,Quantity> database = QuantityDB.getDB();
  private static List<String> empty = Collections.<String>emptyList();

  //Quantity variables
  private Quantity decameter;
  private Quantity fiveminutes;
  private Quantity unitless;
  private Quantity halfHour;
  private Quantity uLess;

  //AST variables
  private AST defineAST, defineAST2, normalizeAST, fivemeterAST, negationAST, differenceAST,
              sumAST, powerAST, quotientAST, productAST, uLessAST;
  
  //Value AST variables
  private AST decameterAST;
  private AST fiveminutesAST;
  private AST unitlessAST;
  private AST halfHourAST;
  
    static int points = 0;
    public SubmissionASTTester() {
        super();
    }
    
    /**
     * Standard Test Fixture.
     */
    @Before
    public void setUp() throws Exception {
        db.put("km", new Quantity(1000, Arrays.asList("meter"), emp));
        fiveKph = new Quantity(5.0, Arrays.asList("km"), Arrays.asList("hour"));
        twoKm = new Quantity(2.0, Arrays.asList("km"), emp);
        tenKm = new Quantity(10.0, Arrays.asList("km"), emp);
        fiveKphValue = new Value(fiveKph);
        twoKmValue = new Value(twoKm);
        tenKmValue = new Value(tenKm);
          //Initializing quantities
    unitless = new Quantity();                
    decameter = new Quantity(10.0, Arrays.asList("m"), empty);                
    fiveminutes = new Quantity(5.0, Arrays.asList("min"), empty);        
    halfHour = new Quantity(30.0, Arrays.asList("min"), empty);
    uLess = new Quantity(6.999, empty, empty);
    //Initializing AST values
    unitlessAST = new Value(unitless);
    fiveminutesAST = new Value(fiveminutes);
    decameterAST = new Value(decameter);
    halfHourAST = new Value(halfHour);
    
    //Initializing ASTs
    
    productAST = new Product(decameterAST, fiveminutesAST);
    fivemeterAST = new Value(new Quantity(5.0, Arrays.asList("m"), 
                         Collections.<String>emptyList()));
    uLessAST = new Value(uLess);
    quotientAST = new Quotient(halfHourAST, fiveminutesAST);
    sumAST = new Sum(halfHourAST, fiveminutesAST);
    differenceAST = new Difference(halfHourAST, fiveminutesAST);
    powerAST = new Power(decameterAST, 2);
    negationAST = new Negation(halfHourAST);
    normalizeAST = new  Normalize(halfHourAST);
    defineAST = new Define("var1", fiveminutesAST); 
    defineAST2 = new Define("var2", uLessAST);
    }
    
    /* Test Product Equals */
    @Test
    public void testProdEval() {
        System.out.println("********testing testProdEval.....");
        Quantity expected = new Quantity(10.0, Arrays.asList("km", "km"),
                                         Arrays.asList("hour"));
        Product target = new Product(fiveKphValue, twoKmValue);
        assertEquals(expected, target.eval(db));
    }
 
    @Test
    public void testQuotientEval() {
        System.out.println("********testing testQuotientEval.....");
        Quantity expected = new Quantity(2.5, emp, Arrays.asList("hour"));
        Quotient target = new Quotient(fiveKphValue, twoKmValue);
        assertEquals(expected, target.eval(db));
    }
 
    @Test
    public void testPowerEval() {
        System.out.println("********testing testPowerEval.....");
        Quantity expected = new Quantity(25, Arrays.asList("km", "km"),
                                         Arrays.asList("hour","hour"));
        Power target = new Power(fiveKphValue, 2);
        assertEquals(expected, target.eval(db));
    }
 
    @Test
    public void testSumEval() {
        System.out.println("********testing testSumEval.....");
        Quantity expected = new Quantity(12, Arrays.asList("km"), emp);
        Sum target = new Sum(twoKmValue, tenKmValue);
        assertEquals(expected, target.eval(db));
    }
 
    @Test
    public void testDifferenceEval() {
        System.out.println("********testing testDifferenceEval.....");
        Quantity expected = new Quantity(-8, Arrays.asList("km"), emp);
        Difference target = new Difference(twoKmValue, tenKmValue);
        assertEquals(expected, target.eval(db));
    }
 
    @Test
    public void testNegationEval() {
        System.out.println("********testing testNegationEval.....");
        Quantity expected = new Quantity(-2, Arrays.asList("km"), emp);
        Negation target = new Negation(twoKmValue);
        assertEquals(expected, target.eval(db));        
    }
}
