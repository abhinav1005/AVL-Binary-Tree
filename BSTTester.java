

import java.util.*;

//Abhinav Khanna 
// Testing class for testing the BinarySearchTree methods

import org.junit.Test; import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertArrayEquals;
import java.lang.Object;
import java.util.NoSuchElementException;

public class BSTTester {
  
  @Test public void testInsert(){ 
    
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>();
    
    // adding to an empty tree
    tree.insert(10, "A");
    
    // adding to the right side of the tree
    tree.insert(5, "B");
    
    // adding to the left side of the root
    tree.insert(2, "C");
    
    assertEquals("A", tree.search(10));
    assertEquals("B", tree.search(5));
    assertEquals("C", tree.search(2));
    
  }
  
  
  @Test public void testSearch(){ 
    
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>();
    
    // searching in an empty tree
    try{
      assertEquals("exception", tree.search(5));
    }catch(NoSuchElementException e){
      ;
    }
    tree.insert(10, "A");
    tree.insert(20, "D");
    tree.insert(3, "E");
   
    // normally searching a tree
    assertEquals("A", tree.search(10));
    assertEquals("E", tree.search(3));
    assertEquals("D", tree.search(20));
    
    
  }
  
  
  @Test public void testDelete(){ 
    
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>();
    
    // Trying to delete from an empty tree
    //assertNull(tree.delete(4));
    
    tree.insert(10, "A");
    tree.insert(5, "B");
    tree.insert(12, "C");

    assertEquals("A", tree.search(10));
    assertEquals("B", tree.search(5));
    assertEquals("C", tree.search(12));

    tree.delete(10);
    
    // normal deletion and testing using the search method
    // searching for a deleted key will return null
    assertEquals(null, tree.search(10));
    try{
    assertEquals(null ,tree.search(10));
    }catch(NoSuchElementException e){
      ;
    }
    
    BinarySearchTree<Integer, String> tree2 = new BinarySearchTree<Integer, String>();
    // delete when root is null
    try{
      tree2.delete(4);
    }catch(NoSuchElementException e){
      ;
    }  
    // delete when key is null
    int a;
    try{
      tree.delete(4);
    }catch(NoSuchElementException e){
      ;
    }

  }
  
  
  @Test public void testInorderRec(){ 
    
    BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
     bst.insert(2,2);
    bst.insert(1,1);
    bst.insert(4,4);
    bst.insert(5,5);
    bst.insert(9,9);
    bst.insert(3,3);
    bst.insert(7,7);
    bst.insert(10,10);
    bst.insert(12,12);
    bst.insert(11,11);
    bst.insert(20,20);
    bst.insert(10,10);
    bst.insert(1,1);
    bst.insert(15,15);
   
      
    ArrayList<Integer> list = new ArrayList<Integer>();
    
    list.add(1);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(7);
    list.add(9);
    list.add(10);
    list.add(10);
    list.add(11);
    list.add(12);
    list.add(15);
    list.add(20);
    
    
    
    assertEquals(list, bst.inorderRec());
    
    
    BinarySearchTree<Integer, Integer> tree2 = new BinarySearchTree<Integer, Integer>();
    tree2.insert(20, 20);
    tree2.insert(10, 10);
    tree2.insert(4, 4);
    tree2.insert(1, 1);
    tree2.insert(7, 7);
    
    ArrayList<Integer> list1 = new ArrayList<Integer>();
    list1.add(1);
    list1.add(4);
    list1.add(7);
    list1.add(10);
    list1.add(20);
    
    assertEquals(list1, tree2.inorderRec());
   
    
    BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();
    tree.insert(20,20);
    tree.insert(10,10);
    tree.insert(1,1);
    tree.insert(15,15);
    tree.insert(50,50);
    
    ArrayList<Integer> list2 = new ArrayList<Integer>();
    list2.add(1);
    list2.add(10);
    list2.add(15);
    list2.add(20);
    list2.add(50);
    assertEquals(list2 ,tree.inorderRec());
    
    
    BinarySearchTree<Integer, Integer> bst1 = new BinarySearchTree<Integer, Integer>();
    bst1.insert(2,2);
    bst1.insert(1,1);
    bst1.insert(4,4);
    bst1.insert(5,5);
    bst1.insert(9,9);
    bst1.insert(3,3);
    bst1.insert(7,7);
    bst1.insert(10,10);
    bst1.insert(12,12);
    bst1.insert(11,11);
    
    bst1.delete(4);
    
    ArrayList<Integer> list3 = new ArrayList<Integer>();
     list3.add(1);
     list3.add(2);
     list3.add(3);
     list3.add(5);
     list3.add(7);
     list3.add(9);
     list3.add(10);
     list3.add(11);
     list3.add(12);
    assertEquals(list3, bst1.inorderRec());
  }
  
  
  @Test public void testKthSmalles(){
    BinarySearchTree<Integer, Integer> bst1 = new BinarySearchTree<Integer, Integer>();
    
    // kthSmallest of an empty tree returns null
    assertEquals(null, bst1.kthSmallest(1));
   
    bst1.insert(2,2);
    bst1.insert(1,1);
    bst1.insert(4,4);
    bst1.insert(5,5);
    
    // when k<0 returns null
    assertEquals(null, bst1.kthSmallest(-1));
    // 2nd smallest
    assertEquals(new Integer(2), bst1.kthSmallest(2));
    
    // smallest
    assertEquals(new Integer(1), bst1.kthSmallest(1));
    
    
  }
  
}
