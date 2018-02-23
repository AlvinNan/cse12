
import java.util.*;

public class QueueWorklist implements SearchWorklist {
  private LinkedList<Square> queue;
  
  public QueueWorklist() {
    queue = new LinkedList<Square>();
  }        
  
  /** Add a Square to the worklist, as appropriate 
  * @param The Square to add 
  */
  public void add(Square s) {
     queue.addLast(s);    
  }
   
  /** Removes and returns the next Square to be explored 
  * @return The next Square to explore */
  public Square getNext() {
     return queue.removeFirst();
  }

  /** isEmpty
  * @return true if the worklist is empty, false otherwise
  */
  public boolean isEmpty() {
    return queue.isEmpty();
  }
   
  /** size of the worklist
  * @return The number of elements in the worklist
  */
  public int size() {
    return queue.size();
  }
  
}
