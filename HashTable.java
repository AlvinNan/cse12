
package hw4;

import java.util.LinkedList;

public class HashTable implements IHashTable {
	
	//You will need a HashTable of LinkedLists. 
	
	private int nelems;  //Number of element stored in the hash table
	private int expand;  //Number of times that the table has been expanded
	private int collision;  //Number of collisions since last expansion
	private int longestChain;
	private double loadFactor;
	private String statsFileName;     //FilePath for the file to write statistics upon every rehash
	private boolean printStats = false;   //Boolean to decide whether to write statistics to file or not after rehashing
	private int tableSize;
	private LinkedList<String>[] hashtable;
	
	//You are allowed to add more :)
	private int getHash(String value)
	{
	  int hash = 0;
	  for(int i = 0; i < value.length(); i++)
	    hash = (hash*31 + value.charAt(i)) % tableSize;
          return hash;
        } 
	  	
	/**
	 * Constructor for hash table
	 * @param Initial size of the hash table
	 */
	public HashTable(int size) {
	  hashtable = new LinkedList[size];
		tableSize = size;
		//Initialize
	}
	
	/**
	 * Constructor for hash table
	 * @param Initial size of the hash table
	 * @param File path to write statistics
	 */
	public HashTable(int size, String fileName){
	  hashtable = new LinkedList[size];
		tableSize = size;
		printStats = true;
		statsFileName = fileName;	
		//Set printStats to true and statsFileName to fileName
	}

	@Override
	public boolean insert(String value) {
		if(value == null) throw new NullPointerException();
		
		if(this.contains(value) == true) return false;
		
		else {
		  if(hashtable[getHash(value)] == null) {
		    hashtable[this.getHash(value)] = new LinkedList<String>();
		    hashtable[this.getHash(value)].add(value);
		    nelems++;
		  }
		  //insert collision
		  else{
		    hashtable[this.getHash(value)].add(value);
		    collision++;
		    nelems++;
		  }

                  if(this.checkLoadFactor) this.rehash();
		  return true;
		}
	}

	@Override
	public boolean delete(String value) {
	  if(value == null) throw new NullPointerException();
	  
	  if(this.contains(value) == false) return false;
	  
	  else {
	    hashtable[this.getHash(value)].remove(value);
	    nelems--;
	    return true;
	  }
	}

	@Override
	public boolean contains(String value) {
	  if(value == null) throw new NullPointerException();
		
	  if(hashtable[getHash(value)] == null) return false;
	  
	  else {
	    for(int i = 0; i < hashtable[getHash(value)].size(); i++)
	      if(value.equals(hashtable[getHash(value)].get(i))) return true;          	        

	    return false;
	  }  
	}

	@Override
	public void printTable() {
	  for(int i = 0; i < tableSize; i++){
            System.out.println(i + ": " + Arrays.toString(hashtable[i].toArray()));
	}
	
	@Override
	public int getSize() {
          return nelems;
	}
	
        private boolean checkLoadFactor(){
          loadFactor = nelems/tableSize;
          
          if(loadFactor < 2/3) return false;
          else return true;
        }

        private LinkedList<String>[] rehash(){
          if( printStats == true){
            longestChain = longestchain();
            FileWriter writer = new FileWriter(statsFileName,true);
            writer.write(expand+"resizes, loaded factor  "+loadFactor+collision+"collisions, "+longestC            hain+"longest Chain")
            writer.close();
          }
          
          LinkedList<String>[] oldTable = hashtable;
          hashtable = new LinkedList[tableSize*2];
          for(int i = 0; i < tableSize; i++){
            if(oldTable[i] != null){
              for(int j = 0; j < oldTable[i].size(); j++){
                hashtable.insert(oldTable[i].get(j));
              }
	    }
          }
         
          expand++;
          collision = 0;
          longestChain = 0;          
          return hashtable;
        }
         
        private int longestchain(){
          int chain = 0;
          int longest = 0;
          for(int i = 0; i < tableSize; i++){
            int size = hashtable[i].size();
            if(size > longest){
              longest = size;
              int chain = 1;
              }
            else if(size == longest){
              chain++;
            }
           }  
        
         return chain;
        }
	//TODO - Helper methods can make your code more efficient and look neater.
	//We expect AT LEAST the follwoing helper methods in your code
	//rehash() to expand and rehash the items into the table when load factor goes over the threshold.
	//printStatistics() to print the statistics after each expansion. This method will be called from insert/rehash only if printStats=true

}
