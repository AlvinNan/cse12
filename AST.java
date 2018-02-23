// AST.java
// Classes for representing Unicalc Abstract Syntax Tree.
// Original author: Chris Stone

// Extended by: Christine Alvarado, Nanxiong Wang
// A92038237 cs12foj

package hw8;

import java.util.*;

/** The interface for an Abstract Syntax Tree for Unicalc */
interface AST {
    /** The string representation of this AST
     * @return The String representation of this AST */
    public String   toString();
    
    /** Evaluate this AST
     * @param env The environment in which to do the evaluation (symbol map)
     * @return The value of the AST
     * */
    public Quantity eval(Map<String,Quantity> env);
    
    /** Equals
     * @param An object to compare to
     * @return true if o represents the same kind of AST with the same structure
     *         false otherwise.
     * */
    public boolean equals( Object o );
}

// The variables and methods should be self-evident
// and you do not need to add header comments to the
// classes and methods below unless you want to or you
// are doing something cryptic.

class Product implements AST
{
    private AST left;
    private AST right;
    
    public Product(AST l, AST r)
    {
        this.left = l;
        this.right = r;
    }
    
    public Quantity eval(Map<String,Quantity> env)
    {
 
        return left.eval(env).mul(right.eval(env));
    }
    
    public String toString()
    {
        return ("Product(" + left + "," + right + ")");
    }
    
    public boolean equals( Object o ) {
        if ( o instanceof Product ) {
            Product tree = (Product)o;
            return tree.left.equals( left ) && tree.right.equals( right );
        }
        else return false;
    }
}

class Quotient implements AST
{
    private AST left;
    private AST right;
    
    public Quotient(AST l, AST r)
    {
        this.left = l;
        this.right = r;
    }
    
    public Quantity eval(Map<String,Quantity> env)
    {
        return left.eval(env).div(right.eval(env)); 
    }
    
    public String toString()
    {
        return ("Quotient(" + left + "," + right + ")");
    }
    
    public boolean equals( Object o ) {
        if ( o instanceof Quotient ) {
            Quotient tree = (Quotient)o;
            return tree.left.equals( left ) && tree.right.equals( right );
        }
        else return false;
    }
}

class Power implements AST
{
    private AST child;
    private int exponent;
    
    public Power(AST ast, int expt)
    {
        this.child = ast;
        this.exponent = expt;
    }
    
    public Quantity eval(Map<String,Quantity> env)
    {
        return child.eval(env).pow(exponent);
    }
    
    public String toString()
    {
        return ("Power(" + child + "," + exponent + ")");
    }
    
    public boolean equals( Object o ) {
        if ( o instanceof Power ) {
            Power tree = (Power)o;
            return tree.child.equals( this.child ) && tree.exponent == this.exponent;
        }
        else return false;
    }
}

class Sum implements AST
{
    private AST left;
    private AST right;
    
    public Sum(AST l, AST r)
    {
        this.left = l;
        this.right = r;
    }
    
    public Quantity eval(Map<String,Quantity> env)
    {
        return left.eval(env).add(right.eval(env));
    }
    
    public String toString()
    {
        return ("Sum(" + left + "," + right + ")");
    }
    
    public boolean equals( Object o ) {
        if ( o instanceof Sum ) {
            Sum tree = (Sum)o;
            return tree.left.equals( left ) && tree.right.equals( right );
        }
        else return false;
    }
}

class Difference implements AST
{
    private AST left;
    private AST right;
    
    public Difference(AST l, AST r)
    {
        this.left = l;
        this.right = r;
    }
    
    public Quantity eval(Map<String,Quantity> env)
    {
        return left.eval(env).sub(right.eval(env));
    }
    
    public String toString()
    {
        return ("Difference(" + left + "," + right + ")");
    }
    
    public boolean equals( Object o ) {
        if ( o instanceof Difference ) {
            Difference tree = (Difference)o;
            return tree.left.equals( left ) && tree.right.equals( right );
        }
        else return false;
    }
    
}


class Negation implements AST
{
    private AST child;
    
    public Negation(AST ast)
    {
        this.child = ast;
    }
    
    public Quantity eval(Map<String,Quantity> env)
    {
        return child.eval(env).negate();
    }
    
    public String toString()
    {
        return ("Negation(" + child + ")");
    }
    
    public boolean equals( Object o ) {
        if ( o instanceof Negation ) {
            Negation tree = (Negation)o;
            return tree.child.equals( this.child );
        }
        else return false;
    }
}
class Value implements AST
{
    private Quantity quant;
    
    public Value(Quantity q)
    {
        this.quant = q;
    }
    
    public Quantity eval(Map<String,Quantity> env)
    {	
        return this.quant; 
    }
    
    public String toString()
    {
        return ("Value(" + quant + ")");
    }
    
    public boolean equals( Object o ) {
        if ( o instanceof Value ) {
            Value val = (Value)o;
            return val.quant.equals( this.quant );
        }
        else return false;
    }
}

class Normalize implements AST
{
    private AST child;
    
    public Normalize(AST ast)
    {
        this.child = ast;
    }
    
    public Quantity eval(Map<String,Quantity> env)
    {
        return child.eval(env).normalize(env);
    }
    
    public String toString()
    {
        return ("Normalize(" + child + ")");
    }
    
    public boolean equals( Object o ) {
        if ( o instanceof Normalize ) {
            Normalize tree = (Normalize)o;
            return tree.child.equals( this.child );
        }
        else return false;
    }
    
}

class Define implements AST
{
    private String unitName;
    private AST defn;
    
    public Define(String name, AST ast)
    {
        this.unitName = name;
        this.defn = ast;
    }
    
    public Quantity eval(Map<String,Quantity> env)
    {
    		Quantity qt = defn.eval(env);
    		env.put(unitName, qt);
        return qt; 
    }
    
    public String toString()
    {
        return ("Define(" + unitName + "," + defn + ")");
    }  
    
    public boolean equals( Object o ) {
        if ( o instanceof Define ) {
            Define tree = (Define)o;
            return tree.unitName.equals( this.unitName ) 
            && tree.defn.equals( this.defn );
        }
        else return false;
    }
}
