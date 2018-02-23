
import java.util.*;

public class StackWorklist implements SearchWorklist {
  private LinkedList<Square> stack;
  
  public StackWorklist() {
    stack = new LinkedList<Square>();
  }
  
  /** Add a Square to the worklist, as appropriate 
  * @param The Square to add 
  */
  public void add(Square s) {
    stack.addFirst(s);    
  }
   
  /** Removes and returns the next Square to be explored 
  * @return The next Square to explore */
  public Square getNext() {
     return stack.removeFirst();
  }

  /** isEmpty
  * @return true if the worklist is empty, false otherwise
  */
  public boolean isEmpty() {
    return stack.isEmpty();
  }
  
  /** size of the worklist
  * @return The number of elements in the worklist
  */
  public int size() {
    return stack.size();
  }
}
