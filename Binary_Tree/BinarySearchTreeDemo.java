
/*
Joshua Cadegan-Syms


This program creates a BinarySearchTree object, with various methods to add, remove,
clear and search a generic type tree. 

*/

import java.util.Random;//imported so that the program can generate random integers 
import java.util.Scanner;
import java.io.*;//imported so that the program can print to a txt file


public class BinarySearchTreeDemo {
	public static void main(String[] args)throws IOException {
		  
		  Scanner kb = new Scanner(System.in);
		  int randomInt;
		  Random r = new Random();

		  BinarySearchTree<Integer> binarySearchTree1 = new BinarySearchTree<>();
		  BinarySearchTree<Integer> binarySearchTree2 = new BinarySearchTree<>();


		  while(binarySearchTree1.size()!=10){/*will continue to loop until the tree is filled with 10 nodes. This will ensure that 
		                                      is filled dispite duplicates*/
		    
		    randomInt = r.nextInt(1000) + 1;//a random variable between 0-1000 is created 
		    binarySearchTree1.insert(randomInt);//variable is added to the binarySearchTree
		  }

		   binarySearchTree1.inorder(binarySearchTree1.getTree());//binarySearchTree1 is printed inorder
		    
		   System.out.println();
		   System.out.println("Maximum element: "+ binarySearchTree1.findMax());//largest element is the binarySearchTree1 is printed 
		   System.out.println("Manimum element: "+ binarySearchTree1.findMin());//smallest element is the binarySearchTree1 is printed
		    
		   System.out.print("Enter a key to search: ");//user enters a key that will be searched for in the  binarySearchTree 
		   int key= kb.nextInt();
		    
		   System.out.println(binarySearchTree1.recursiveSearch(key));
		    
		   //The next two lines of code create a new txt and will print the results of block 1 to a txt file
	      PrintStream block1Output = new PrintStream (new  FileOutputStream ("block1Output.txt"));
	      System.setOut(block1Output);
          
         /*The code below repeats the above code, however instead of the outpur being printed to 
         the console it is printed to a new txt file*/
	       
         System.out.print("In-order Traversal: "); 
         binarySearchTree1.inorder(binarySearchTree1.getTree());//binarySearchTree1 is printed inorder
		    
		   System.out.println();
         System.out.println();
		   System.out.println("Maximum element: "+ binarySearchTree1.findMax());//largest element is the binarySearchTree1 is printed 
		   System.out.println("Manimum element: "+ binarySearchTree1.findMin());//smallest element is the binarySearchTree1 is printed
		   System.out.println();
         System.out.println("Element Searched: " + key);
		    
         if(binarySearchTree1.recursiveSearch(key)!=null) {//will print if the number is found in the tree
		    	System.out.println("Element found in Tree");
		    }
		   else
		    	System.out.println("Element not found in Tree");//will print if the number is not found in the tree
         
         System.out.println();
		    
		   while(binarySearchTree2.size()!=100){//will continue to loop until 100 nodes are in the tree
		    
		      randomInt = r.nextInt(1000) + 1;//creates a random variable
		      binarySearchTree2.insert(randomInt);//variable is added to tree
		   }
         
         //The next two lines of code create a new txt and will print the results of block 2 to a txt file
		   PrintStream out = new PrintStream (new  FileOutputStream ("block2Output.txt"));
		   System.setOut(out);
		   
         System.out.print("Tree height: ");//tree height is printed
		   binarySearchTree2.getHeight(binarySearchTree2.getTree());

		   System.out.print("The Tree is Balanced: ");//prints true/false if the tree is balanced
		   binarySearchTree2.balanced(binarySearchTree2.getTree());
		      
		   int counter=1;
		   //binarySearchTree2.getTree().clear();
		      
		   while(counter!=51){/*will continue to loop until 50 outputs are printed to the txt file.
		                         The same implementation below as with above*/ 
		     
		     System.out.println(counter + ".");
		     binarySearchTree2 = new BinarySearchTree<>();//binarySearchTree2 is set to new 
		      
		     while(binarySearchTree2.size()!=100){    
		        randomInt = r.nextInt(1000) + 1;
		        binarySearchTree2.insert(randomInt);
		     }
		       
		      System.out.print("Tree height: ");
		      binarySearchTree2.getHeight(binarySearchTree2.getTree());

		      System.out.print("The Tree is Balanced: ");
		      binarySearchTree2.balanced(binarySearchTree2.getTree());
		      System.out.println();
		      
		      counter++;//counter is increased by 1
		    }
		  }
      }
