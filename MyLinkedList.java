/*
THIS CODE WAS OUR OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS OR COPIED FROM ONLINE RESOURCES.
Laura Neff
 */

import java.util.*;

public class MyLinkedList
{
  /*******************************************************/
  class Node
  {
      private long data;
      private Node next;
      public Node(long data, Node next)
      {
          this.data = data;
          this.next = next;
      }
      public String toString(){ return "" + this.data; }

   }
   /********************************************************/

   private Node head;
   private int size;

   public MyLinkedList() {
      head = null;
      size = 0;
   }

   public int getSize(){ return size; }

   // Returns true if the list is empty
   public boolean isEmpty() {
     return head == null;
   }

   // Inserts a new node at the beginning of this list
   public void addFirst(long item) {
     head = new Node(item, head);
     size++;
   }

   // Inserts a new node to the end of this list
   public void addLast(long item) {
     if( isEmpty() )
        addFirst(item);
     else
     {
       Node current = head;
       while( current.next != null ) current = current.next;
       // Now current is pointing to the last element!
       current.next = new Node(item, null);
       size++;
     }
   }

   // Returns the first element (data) in the list
   public long getFirst() {
     if( isEmpty() ) throw new NoSuchElementException();
     return head.data;
   }

   // Returns the last element (data) in the list
   public long getLast() {
     if(head == null) throw new NoSuchElementException();
     Node tmp = head;
     while(tmp.next != null) tmp = tmp.next;
     return tmp.data;
   }

   // Returns a reference to the Node at the given position,
   // assuming that node indexes start at zero
   public Node get(int pos) {
     if (head == null) throw new IndexOutOfBoundsException();
     Node tmp = head;
     for (int k = 0; k < pos; k++){
       tmp = tmp.next;
       if( tmp == null ) throw new IndexOutOfBoundsException();
     }
     return tmp;
   }

   public long[] toArray() {
     if(head == null) throw new IndexOutOfBoundsException();
     long[] result = new long[getSize()];
     int i = 0;
     for(Node tmp = head; tmp != null; tmp = tmp.next){
       result[i] = tmp.data;
       i++;
     }
     return result;
   }

   // Removes and returns the first element (data) in the list.
   public long removeFirst() {
     long temp = getFirst();
     head = head.next;
     size--;
     return temp;
   }

   // Removes the first occurrence of the specified element in this list.
   public void remove(long key) {
     if(head == null)
        throw new RuntimeException("cannot delete");

     if( head.data == key ) {
        head = head.next;
        size--;
        return;
     }

     Node cur  = head;
     Node prev = null;
     while(cur != null && cur.data != key )
     {
        prev = cur;
        cur = cur.next;
     }
     if(cur == null) throw new RuntimeException("cannot delete");
     prev.next = cur.next;
     size--;
   }

   // Returns a string representation
   public String toString() {
      String output = "";
      if(head == null) throw new NoSuchElementException();
      Node tmp = head;
      while(tmp != null) {
        output += tmp + " -> ";
        tmp = tmp.next;
      }
      output += "[NULL]";
      return output;
   }


   //-------------------------------------------------------------------------
   //----- Use MergeSort algorithm to sort the nodes in this linked list -----
   //-------------------------------------------------------------------------
   // TODO: Find the middle element of the list that begins at the passed node

   // MergeSort starting point
   public void mergeSort() {
     if(head == null) throw new RuntimeException("Cannot sort empty list.");
     head = sort(head);
   }

   // TODO: Sort this linked list using merge sort (recursively)
   public Node sort(Node node) {

       if(node.next == null){
           return node;
       }

       Node mid = getMiddle(node);
       Node afterMid = mid.next;

       mid.next = null;

       Node lo = sort(node);

       Node hi = sort(afterMid);

       Node list = merge(lo,hi);

       return list;

    }

    public Node getMiddle(Node node) {

        if(node == null){
            return node;
        }

        Node slow = node;
        Node fast = node;

        while(fast.next != null) {
            Node afterFast = fast.next;
            if (afterFast.next != null) {
                slow = slow.next;
                fast = afterFast.next;
            } else {
                break;
            }
        }

        return slow;

    }

   // TODO: Merge two sorted lists (can be non-recursive)
   public Node merge(Node left, Node right) {
    // ... Add your code here ... //

       Node finished = null;

       if(left == null){
           return right;
       } if (right == null) {
           return left;
       }

       if(right.data >= left.data){
           finished = left;
           finished.next = merge(left.next, right);
       } else {
           finished = right;
           finished.next = merge(left, right.next);
       }

       return finished;



   }

   public static void main(String[] args) {
      // Test your merge sort implementation here!
      MyLinkedList list = new MyLinkedList();
      list.addLast(3);
      list.addLast(2);
      list.addLast(8);
      list.addLast(10);
      list.addLast(5);
      System.out.println("Before list.mergeSort()...");
      System.out.println(list);
      list.mergeSort();
      System.out.println("\nAfter list.mergeSort()...");
      System.out.println(list);
   }

} // End of MyLinkedList class
