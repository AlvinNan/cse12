/** This is the Quantity class.
 * @author Nanxiong Wang
 * A92038237
 * cs12foj
 */
package hw8;

import java.util.*;
import java.text.DecimalFormat;

public class Quantity {
	private double value;
	private HashMap<String,Integer> map;
	//no argument constructor
	public Quantity(){
		value = 1.0;
		map = new HashMap<String, Integer>();
	}
	//one argument constructor
	public Quantity(Quantity q){
		this.value = q.value;
		map = new HashMap<String, Integer>();
		if(!q.map.isEmpty()) {		
			this.map.putAll(q.map);
		}	
	}
	//three argument constructor
	public Quantity(double value,List<String> numerator,List<String> denominator)
			throws IllegalArgumentException{
		if(numerator == null || denominator == null) throw new IllegalArgumentException();
		this.value = value;
		this.map = new HashMap<String, Integer>();
		//Add strings to numerator
		for(int i = 0; i < numerator.size(); i++) {
			if(!this.map.containsKey(numerator.get(i))) {
				this.map.put(numerator.get(i), 1);
			}
			else {
				int expo = this.map.get(numerator.get(i)) + 1;
				this.map.put(numerator.get(i), expo);
			}
		}
		//Add strings to denominator
		for(int i = 0; i < denominator.size(); i++) {
			if(!this.map.containsKey(denominator.get(i))) {
				this.map.put(denominator.get(i), -1);
			}
			else {
				int expo = this.map.get(denominator.get(i)) - 1;
				this.map.put(denominator.get(i), expo);
			}
		}		
	}
	/**
	 * Quantity method mul that does multiplication.
	 * @param q multiplier
	 * @return Quantity product
	 * @throws IllegalArgumentException
	 */
	public Quantity mul(Quantity q) throws IllegalArgumentException{
		if(q == null) throw new IllegalArgumentException();
		//Make a copy of this Quantity to store the result
		Quantity newResult = new Quantity(this);
		newResult.value = this.value*q.value;
		//If Quantity q has unit(s)
		if(!q.map.isEmpty()) {
			for(String unit : this.map.keySet()) {
				//Compute the new exponent for each unit
				if(q.map.containsKey(unit)) {
					int newExp = this.map.get(unit) + q.map.get(unit);
					if(newExp == 0) {
						newResult.map.remove(unit);
					}
					else{
						newResult.map.put(unit, newExp);
					}
				}		    
			}
			for(String unit : q.map.keySet()) {
				//Add the unique unit in Quantity q to newResult
				if(!this.map.containsKey(unit)) {
					newResult.map.put(unit, q.map.get(unit));
				}
			}
		}
		return newResult;
	}
	/**
	 * Quantity method div that does division.
	 * @param q divisor
	 * @return Quantity quotient
	 * @throws IllegalArgumentException
	 */
	public Quantity div(Quantity q) throws IllegalArgumentException {
		if(q == null || q.value == 0.0) throw new IllegalArgumentException();
		//Make a copy of this Quantity to store the result
		Quantity newResult = new Quantity(this);
		newResult.value = this.value/q.value;
		
		//If Quantity q has unit(s)
		if(!q.map.isEmpty()) {
			for(String unit : this.map.keySet()) {
				//Compute the new exponent for each unit
				if(q.map.containsKey(unit)) {
					int newExp = this.map.get(unit) - q.map.get(unit);
					if(newExp == 0) {
						newResult.map.remove(unit);
					}
					else{
						newResult.map.put(unit, newExp);
					}
				}
			}
			//Add the unique unit in Quantity q to newResult
			for(String unit : q.map.keySet()) {
				if(!this.map.containsKey(unit)) {
					newResult.map.put(unit, -q.map.get(unit));
				}
			}
		}
		return newResult;
	}
	/**
	 * Quantity method pow that does power.
	 * @param power exponent
	 * @return Quantity
	 */
	public Quantity pow(int power) {
		//Make a copy of this Quantity to store the result
		Quantity newResult = new Quantity(this);
		newResult.value = Math.pow(this.value, power);
		//If this Quantity has unit(s)
		//Update the new exponent of the unit
		int newExp = 0;
		if(!this.map.isEmpty()) {
			for(String unit : this.map.keySet()) {
				//If unit is a numerator
				if(this.map.get(unit) > 0) {
					if(this.map.get(unit) == 1) {
						newExp = power;					
					}
					else {
						newExp =  (int) Math.pow(this.map.get(unit), power);				      	
					}
				}
				//If unit is a denominator
				if(this.map.get(unit) < 0) {
					if(this.map.get(unit) == -1) {
						newExp = -power;					
					}
					else {
						newExp =  (int) ((int) Math.pow(this.map.get(unit), power) * 
								Math.pow(-1,power+1));	
					}
				}
				newResult.map.put(unit, newExp);
				//If exponent is zero, remove unit
				if(newResult.map.get(unit) == 0) {
					newResult.map.remove(unit);
				}
			}
		}
		return newResult;
	}
	/**
	 * Quantity method add that does addition.
	 * @param q Quantity to be added
	 * @return Quantity sum
	 * @throws IllegalArgumentException
	 */
	public Quantity add(Quantity q) throws IllegalArgumentException {
		if(q == null) throw new IllegalArgumentException();
		//Check if Quantity q has the same unit(s)
		//as this Quantity
		if(!this.map.equals(q.map)) throw new IllegalArgumentException();
		//Make a copy of this Quantity to store the result
		Quantity newResult = new Quantity(this);
		newResult.value = this.value + q.value;
		
		return newResult;
	}
	/**
	 * Quantity method sub that does subtraction.
	 * @param q Quantity to be subtracted
	 * @return Quantity difference
	 * @throws IllegalArgumentException
	 */
	public Quantity sub(Quantity q) throws IllegalArgumentException {
		if(q == null) throw new IllegalArgumentException();
		//Check if Quantity q has the same unit(s)
		//as this Quantity
		if(!this.map.equals(q.map)) throw new IllegalArgumentException();
		//Make a copy of this Quantity to store the result
		Quantity newResult = new Quantity(this);
		newResult.value = this.value - q.value;
		
		return newResult;
	}
    /**
     * Quantity method negation that does negation.
     * @return negated Quantity
     */
	public Quantity negate() {
		//Make a copy of this Quantity to store the result
		Quantity newResult = new Quantity(this);
		newResult.value = -this.value;
		return newResult;
	}
	//Quantity method toString 
	public String toString() {
		double valueOfTheQuantity = this.value;
		Map<String,Integer> mapOfTheQuantity = this.map;
		 
		// Ensure we get the units in order
		TreeSet<String> orderedUnits =  
		      new TreeSet<String>(mapOfTheQuantity.keySet());
		    
		StringBuffer unitsString = new StringBuffer();
		    
		for (String key : orderedUnits) {
			int expt = mapOfTheQuantity.get(key);
		    unitsString.append(" " + key);
		    if (expt != 1)
		    		unitsString.append("^" + expt);
		  }
		    
		// Used to convert doubles to a string with a 
		//   fixed maximum number of decimal places.
		DecimalFormat df = new DecimalFormat("0.0####");
		// Put it all together and return.
		return df.format(valueOfTheQuantity) + unitsString.toString();
	}
    //Quantity method equals that compares two quantities.
	public boolean equals(Object o) {
		if(o instanceof Quantity) {
			Quantity q = (Quantity) o;
			if(q.toString().equals(this.toString()) &&
				this.map.equals(q.map)	) return true;
			else return false;
		}
		return false;
	}
	//Quantity method hashCode that return a hashcode of quantity
	public int hashCode() {		
		return this.toString().hashCode();
	}
	/**
	 * Quantity method normalizedUnit that normalizes a unit.
	 * @param unit unit to be normalized
	 * @param db database
	 * @return normalized Quantity
	 */
	public static Quantity normalizedUnit(String unit, Map<String,Quantity> db) {
		Quantity q = new Quantity(1.0, Arrays.asList(unit), new ArrayList<String>());
		if(!db.containsKey(unit)) {	
			return q;
		}
		else {
			q.value = db.get(unit).value;
			q.map = (db.get(unit)).map;
			return q.normalize(db);
		}	
	}
	/**
	 * Quantity method normalize that normalize a Quantity.
	 * @param db database
	 * @return normalized Quantity
	 */
	public Quantity normalize(Map<String,Quantity> db) {
		//Make a copy of this Quantity to store the result
		Quantity normalizedQ = new Quantity(this);
		LinkedList<String> denominator = new LinkedList<String>();
		LinkedList<String> numerator = new LinkedList<String>();
		//Record strings in numerator and denominator
		for(String unit : normalizedQ.map.keySet()) {
			int num_units = normalizedQ.map.get(unit);
			while(num_units < 0) {
				denominator.add(unit);
				num_units ++;
			}		
			while(num_units > 0){
				numerator.add(unit);
				num_units --;
			}
		}
		//Recursively call normalizedUnit
		Quantity result = new Quantity();
		result.value = this.value;
		for(String unit : numerator) {
			result = result.mul(normalizedUnit(unit,db));
		}	
		for(String unit : denominator) {
			result = result.div(normalizedUnit(unit,db));
		}
		return result;
	}
}
