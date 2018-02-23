
// file:    Tokenizer.java
// purpose: Input Tokenizer
// author:  Zach Dodds, Christine Alvarado
// date: Sometime in 2008 or so

// comments:

package hw8;

import java.util.*;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/** class: Tokenizer
 * purpose: to provide an array of Strings (tokens) as desired
 * can use System.in or a filename to get its tokens
 */
public class Tokenizer
{
    
    
    /** method: nextTokens
     * inputs: none
     * outputs: an array of Strings - the next array of tokens from
     *          this Tokenizer's input stream...
     */
    public static List<String> tokenize( String line )
    {
        List<String> tokens = new LinkedList<String>();
        Pattern pat = Pattern.compile( "[a-zA-Z]+|[0-9]*\\.[0-9]+|[0-9]+|[\\+\\-\\^\\*/#\\(\\)]" );
        Matcher match = pat.matcher( line );
        while ( match.find() ) {
            tokens.add( line.substring( match.start(), match.end() ) );
        }
        if ( tokens.size() > 0 ) {
            return tokens;
        }
        else return null;
    }
    
    /** method: main
     * inputs: usual
     * outputs: none - just tests the Tokenizer...
     */
    public static void main(String[] args)
    {
        Tokenizer tokenizer = new Tokenizer();
        //tokenizer = new Tokenizer("testfile.txt");
        
        Scanner s = new Scanner( System.in );
        
        while (true)
        {
            String line = s.nextLine();
            List<String> tokens = Tokenizer.tokenize( line );
            if (tokens == null)
                break;
            
            System.out.println("The tokens are " + tokens );
            
            System.out.println();
            
        }
    }
    
}

