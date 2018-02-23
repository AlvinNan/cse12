package hw8;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;
/**
 * hw8 grader.
 * Emma:
 */
public class SubmissionQuantityTester {

  //Quantity objects to be used in tests.
  private Quantity millihertz;
  private Quantity day;
  private Quantity decameter;
  private Quantity threehours;
  private Quantity kilograv;
  private Quantity voidQuantity;
  private Quantity dayCopy;
  private Quantity decidavis;
  private Quantity zeroValue;
  private Quantity unitless;
  private Quantity karina;
  private Quantity speed;
  static int points = 0;

  private static Map<String,Quantity> db;
  private static List<String> emp = Collections.<String>emptyList();

  //Initializing instance variables to run tests.
  @Before
    public void setUp()
    {
      //THIS IS THE DATABASE WE WILL SET UP. DATABASE VALUES ARE BASED ON PRIMITIVE UNITS
      db = new HashMap<String,Quantity>();
      db.put("kilohertz", new Quantity(1000.0, Arrays.asList("hertz"), Collections.<String>emptyList()));
      db.put("minute", new Quantity(60.0, Arrays.asList("second"), Collections.<String>emptyList()));
      db.put("hour", new Quantity(60.0, Arrays.asList("minute"), Collections.<String>emptyList()));
      db.put("kilometer", new Quantity(1000.0, Arrays.asList("meter"), Collections.<String>emptyList()));
      db.put("davis", new Quantity(999.0, Arrays.asList("meter", "meter", "meter", "meter", "second"), Arrays.asList("meter")));
      db.put("gravity", new Quantity(9.8, Arrays.asList("meter"), Arrays.asList("second", "second")));
      db.put("decameter", new Quantity(10.0, Arrays.asList("meter"), Collections.<String>emptyList()));
      db.put("kph", new Quantity(1.0, Arrays.asList("kilometer"), Arrays.asList("hour")));
      db.put("day", new Quantity(24.0, Arrays.asList("hour"), emp));

      //THESE ARE VALUES THAT ARE DERIVED FROM DATABASE VALUES, we will use these quantities in our tester. 
      millihertz = new Quantity(0.001, Arrays.asList("hertz"), Collections.<String>emptyList());        
      day = new Quantity(24.0, Arrays.asList("hour"), Collections.<String>emptyList());
      decameter = new Quantity(10.0, Arrays.asList("meter"), Collections.<String>emptyList());
      threehours = new Quantity(3.0, Arrays.asList("hour"), Collections.<String>emptyList());
      kilograv = new Quantity(1000.0, Arrays.asList("gravity"), Collections.<String>emptyList());        
      decidavis = new Quantity(0.1, Arrays.asList("davis"), Collections.<String>emptyList());
      dayCopy = new Quantity(day); //uses copy constructor to deep copy secondCopy
      zeroValue = new Quantity(0.0, Collections.<String>emptyList(), Collections.<String>emptyList());
      unitless = new Quantity(1.0, Collections.<String>emptyList(), Collections.<String>emptyList());
      karina = new Quantity(12.0, Arrays.asList("hour"), Arrays.asList("meter", "meter"));
      voidQuantity = null;
      speed = new Quantity(60.0, Arrays.asList("kilometer"), Arrays.asList("hour"));

    }

  //CONSTRUCTOR TESTS
  @Test
    public void testNoArgConstructor()
    {
      System.out.println("********testing testConstructor.....");
      Quantity defaultQuantity = new Quantity();
      Quantity defaultClone = new Quantity(1.0, Collections.<String>emptyList(), Collections.<String>emptyList());
      assertTrue(defaultQuantity.equals(defaultClone));
    }

  /** Test #2 testDeepCopyConstructor
   * Makes a deep copy of the Quantity object
   */
  @Test
    public void testDeepCopyConstructor()
    {
        System.out.println("********testing testCopy.....");
      Quantity copied = new Quantity(decameter);
      assertTrue(copied.equals(decameter));        //its a deep copy
      assertFalse(copied==decameter);        //but it does not occupy same memory address
    }

  /** Test #3 testThreeArgConstructor
   * Checks that it creates a Quantity object according to parameters
   */
  @Test
    public void testThreeArgConstructor()
    {
        System.out.println("********testing testConstructorwithArg.....");
      Quantity threehertzsquared = new Quantity(3.0, Arrays.asList("hertz", "hertz"), emp);
      Quantity threehertzsquaredcomp = new Quantity(3.0, Arrays.asList("hertz", "hertz"), emp);
      assertEquals(threehertzsquared, threehertzsquaredcomp);
    }



  //METHOD TESTS

  /** Test #5 testMulException
   * Pass in null quantity, should except.
   */
  @Test
    public void testMulException()
    {
        System.out.println("********testing testMulException.....");
      try {
        threehours.mul(null);
        fail("Should have thrown an IllegalArgumentException");
      }
      catch (IllegalArgumentException e) {}
    }

  /** Test #6 testMul
   * multiplies two quantity objects, expected units
   */
  @Test
    public void testMul()
    {
        System.out.println("********testing testMul.....");
      //Test with different units
      Quantity product = millihertz.mul(threehours);
      Quantity testProd = new Quantity(0.003, Arrays.asList("hertz", "hour"), emp);
      assertTrue(product.equals(testProd));

      //Test with unit-less quantity
      Quantity zeroProduct = decameter.mul(unitless);
      Quantity testZero = new Quantity(10.0, Arrays.asList("meter"), emp);
      assertTrue(product.equals(testProd));

      //Test with same unit
      Quantity sameUnit = decameter.mul(decameter);
      Quantity testSameUnit = new Quantity(100.0, Arrays.asList("meter", "meter"), emp);

    }


  /** Test #8 testDivException
   * Pass in null quantity, should except.
   */
  @Test
    public void testDivException()
    {
        System.out.println("********testing testDivException.....");
      try {
        threehours.div(null);
        fail("Should have thrown an IllegalArgumentException");
      }
      catch (IllegalArgumentException e) {}
    }
  
  /** Test #8.5 testDivException2
   * Pass in a 0 quantity, should except
   */
  @Test
    public void testDivException2()
    {
        System.out.println("********testing testDiv.....");
      try{
        threehours.div(new Quantity(0.0, emp, emp));
        fail("Should have thrown an IllegalArgumentException");
      }
      catch(IllegalArgumentException e) {}
    }

  /** Test #9 testDiv
   * Divides two quantity objects, expected units
   */
  @Test
    public void testDiv()
    {
        System.out.println("********testing testDiv.....");
        System.out.println("decameter " + decameter);
      Quantity result = decameter.div(decameter);
      Quantity testResult = new Quantity(1.0, emp, emp); //Should now be unitless
      assertEquals(testResult, result);
      System.out.println("decameter " + decameter);
      

      //Dividing against no units
      Quantity constantProduct = decameter.div(unitless);
      Quantity testConstant = new Quantity(10.0, Arrays.asList("meter"), emp);
      assertEquals(testConstant, constantProduct);

      Quantity differentUnit = decameter.div(threehours);
      Quantity testDiffUnit = new Quantity((double)10/3, 
          Arrays.asList("meter"), Arrays.asList("hour"));
      assertEquals(testDiffUnit, differentUnit);

      Quantity div2 = karina.div(decameter);
      Quantity testDiv2 = new Quantity(1.2, Arrays.asList("hour"), 
          Arrays.asList("meter", "meter", "meter"));

      Quantity div3 = karina.div(threehours);
      Quantity testDiv3 = new Quantity(4.0, emp, Arrays.asList("meter", "meter"));
      assertEquals(div3, testDiv3);

      //Dividing a quantity by itself
      Quantity div4 = karina.div(karina);
      assertEquals(div4, unitless);
    }


  /** Test #11 testPow
   * Make sure that testPow returns correct values.
   */
  @Test
    public void testPow()
    {
        System.out.println("********testing testPow.....");
      //Raising to a positive integer
      Quantity squared = threehours.pow(2);
      Quantity testSquared = new Quantity(9.0, 
                             Arrays.asList("hour", "hour"), emp);
      assertEquals(testSquared, squared);

      Quantity cubed = day.pow(3);
      Quantity testCubed = new Quantity(13824.0, 
                           Arrays.asList("hour", "hour", "hour"), emp);
      assertEquals(cubed, testCubed);

      //Raising to a negative integer
      Quantity inverse = decameter.pow(-1);
      Quantity testInverse = new Quantity(0.1, emp, Arrays.asList("meter"));
      assertEquals(inverse, testInverse);

      //Raising to 0
      Quantity zeroProduct = millihertz.pow(0);
      Quantity testZero = new Quantity(1.0, emp, emp);
      assertEquals(testZero, zeroProduct); 
    }

  /** Test #12 testAddNullException
   * pass in a null value to argument, should throw IllegalArgEx
   */
  @Test
    public void testAddNullException()
    {
        System.out.println("********testing testAddNullException.....");
      try {
        threehours.add(null);
        fail("Added null. Should have thrown IllegalArgumentException");
      }
      catch (IllegalArgumentException e) {}
    }

  /** Test #14 testAdd 
   * adding returns consistent, appropriate units
   */
  @Test
    public void testAdd()
    {
        System.out.println("********testing testAdd.....");
      //Adding with same unit. should retain unit
      Quantity sum1 = day.add(dayCopy);
      Quantity testSum1 = new Quantity(48.0, Arrays.asList("hour"), emp);
      assertEquals(sum1, testSum1);

      //Adding unit-less quantities
      Quantity sum2 = unitless.add(unitless);
      Quantity testSum2 = new Quantity(2.0, emp, emp);
      assertEquals(sum2, testSum2);

      //Adding with multiple units
      Quantity sum3 = karina.add(karina);
      Quantity testSum3 = new Quantity(24.0, Arrays.asList("hour"), Arrays.asList("meter", "meter"));   

    }
}


// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:sw=4
