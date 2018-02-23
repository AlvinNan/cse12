/** This is PA5 for cs12 FA17 with prof Alvarado.
 * This file reports 20 running time (along with the number of 
 * elements being removed) of removing elements from one of 
 * the three data structures with 10 being sorted removal
 * and 10 random removal for a total of 60 running time reported.
 * @author Nanxiong Wang, Yanqi Han
 * cs12foj, cs12fmm
 */

package hw5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class Complexity {
  
  
  /**
   * Remove all the elements from a linkedlist.
   * @param size
   * @param linkedlist
   */
  private void removeAll(int size, LinkedList<Integer> linkedlist){
    //Add elements to the linkedlist
    int startNum = 0;
    for(int i = 0; i < size; i++)
    {
      linkedlist.add(startNum);
      startNum++;
    }
    //start removing and recording running time
    long prevTime = System.nanoTime();
    for(int i = 0; i < linkedlist.size();i++) {
      linkedlist.remove();
    }
    long afterTime = System.nanoTime();
    //print out the running time
    System.out.print("Running time for LinkedList(sorted): ");
    System.out.print((afterTime-prevTime)/size);
    System.out.println(" ns (" + size + " elements)");
      
  }
  
  /**
   * Remove all the elements from the arraylist.
   * @param size
   * @param arraylist
   */
  private void removeAll(int size, ArrayList<Integer> arraylist){
    //Add elements to the arraylist
    int startNum = 0;
    for(int i = 0; i < size; i++)
    {
      arraylist.add(startNum);
      startNum++;
    }
    //start removing and recording running time
    long prevTime = System.nanoTime();
    for(int i = 0; i < arraylist.size(); i++) {
      arraylist.remove(0);
    }
    long afterTime = System.nanoTime();
    //print out the running time
    System.out.print("Running time for ArrayList(sorted): ");
    System.out.print((afterTime-prevTime)/size);
    System.out.println(" ns (" + size + " elements)");
  }
  
  /**
   * Remove all the elements from the hashset.
   * @param size
   * @param hashset
   */
  private void removeAll(int size, HashSet<Integer> hashset){
    //Initialize a linkedlist to record the order of elements
    //added to the hashset
    ArrayList<Integer> hashrecord = new ArrayList<Integer>();
    int Num = 0;
    //Add elements to the hashset
    for(int i = 0; i < size; i++)
    {
      hashset.add(Num);
      hashrecord.add(Num);
      Num++;
    }
    //start removing and recording running time
    long prevTime = System.nanoTime();
    for(int i = 0; i < size ; i++) {
      hashset.remove(hashrecord.get(i));
    }
    long afterTime = System.nanoTime();
    //print out the running time
    System.out.print("Running time for HashSet(sorted): ");
    System.out.print((afterTime-prevTime)/size);
    System.out.println(" ns (" + size + " elements)");
  }  
  
  /**
   * Remove all the shuffled elements from the linkedlist.
   * @param size
   * @param linkedlist
   */
  private void removeAllShuffle(int size, LinkedList<Integer> linkedlist)
  {
    int startNum = 0;
    //Add elements to the linkedlist
    for(int i = 0; i < size; i++)
    {
      linkedlist.add(startNum);
      startNum++;
    }
    //Add elements to the shufflelist
    ArrayList<Integer> shufflelist = new ArrayList<Integer>();
    for(int i = 0; i < size; i++) {
      shufflelist.add((Integer) linkedlist.get(i));
    }
    //shuffle the list
    Collections.shuffle(shufflelist);
    //start removing and recording running time
    long prevTime = System.nanoTime();
    for(int i = 0; i < size; i++)
    {
      linkedlist.remove(shufflelist.get(i));
    }
    long afterTime = System.nanoTime();
    //print out the running time
    System.out.print("Running time for LinkedList(random): ");
    System.out.print((afterTime-prevTime)/size);
    System.out.println(" ns (" + size + " elements)"); 
  }
  
  /**
   * Remove all the shuffled elements from the arraylist.
   * @param size
   * @param arraylist
   */
  private void removeAllShuffle(int size, ArrayList<Integer> arraylist)
  {
    //Add elements to the arraylist
    int startNum = 0;
    for(int i = 0; i < size; i++)
    {
      arraylist.add(startNum);
      startNum++;
    }
    //shuffle arraylist
    Collections.shuffle(arraylist);
    //start removing and recording running time
    long prevTime = System.nanoTime();
    for(int i = 0; i < size; i++)
    {
      arraylist.remove(0);
    }
    long afterTime = System.nanoTime();
    //print out the running time
    System.out.print("Running time for ArrayList(random): ");
    System.out.print((afterTime-prevTime)/size);
    System.out.println(" ns (" + size + " elements)"); 
  }
  
  
  /**
   * Remove all the shuffled elements from the hashset.
   * @param size
   * @param hashset
   */
  private void removeAllShuffle(int size, HashSet<Integer> hashset)
  {
    //Add elements to the hashset
    int Num = 0;
    for(int i = 0; i < size; i++)
    {
      //int randNum = rand.nextInt(numRange);
      hashset.add(Num);
      Num++;
    }
    ArrayList<Integer> shufflelist = new ArrayList<Integer>(hashset);   
    Collections.shuffle(shufflelist);
    long prevTime = System.nanoTime();
    for(int i = 0; i < size; i++)
    {
      hashset.remove(shufflelist.get(i));
    }
    long afterTime = System.nanoTime();
    System.out.print("Running time for HashSet(random): ");
    System.out.print((afterTime-prevTime)/size);
    System.out.println(" ns (" + size + " elements)"); 
  }
   
  //main method to call removeAll
	public static void main(String[] args) {
		Complexity complex = new Complexity();
		LinkedList<Integer> linkedlist = new LinkedList<Integer>();
		HashSet<Integer> hashset = new HashSet<Integer>();
	  ArrayList<Integer> arraylist = new ArrayList<Integer>();
		int firstSize = 1000;
	  
	  for(int i = 1; i < 11; i++) {
	    complex.removeAll(firstSize*i,linkedlist);
	  }
	  
	  for(int i = 1; i < 11; i++) {
      complex.removeAllShuffle(firstSize*i,linkedlist);
    }
	  
	  for(int i = 1; i < 11; i++) {
      complex.removeAll(firstSize*i,arraylist);
    }
	  
	  for(int i = 1; i < 11; i++) {
      complex.removeAllShuffle(firstSize*i,arraylist);
    }
	  
	  for(int i = 1; i < 11; i++) {
      complex.removeAll(firstSize*i,hashset);
    }
	  
	  for(int i = 1; i < 11; i++) {
      complex.removeAllShuffle(firstSize*i,hashset);
    }
	}
}
