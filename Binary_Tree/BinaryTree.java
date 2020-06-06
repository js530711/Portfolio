/*
Joshua Cadegan-Syms

 
 This program creates a binary tree, with methods that will print the 
 tree in-Order, Pre-Order, post-Order, level Order and will determine if
 the tree is balanced, and its height.  

*/
import java.util.*;
public class BinaryTree<T>{
   
    private T data;//instance variables
    private BinaryTree<T> parent;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree(){//constructor
        parent = left = right = null;
        data = null;
    }
    
    public void makeRoot(T data){//creates a root
        if (!isEmpty()){
            System.out.println("Can't make root. Already exists");
        }
        else
            this.data = data;//if no root exists, root is set to data
    }

    public void setData(T data){//setter method for data
        this.data = data;
    }

    public void setLeft(BinaryTree<T> tree){//setter for left node 
        left = tree;
    }
    
    public void setRight(BinaryTree<T> tree){//setter for right node
        right = tree;
    }
    
    public void setParent(BinaryTree<T> tree){//setter for parent
        parent = tree;
    }

    public T getData(){//getter method
        return data;
    }
    
    public BinaryTree<T> getParent(){//getter method for parent
        return parent;
    }
    
    public BinaryTree<T> getLeft(){//getter method for left node
        return left;
    }
    
    public BinaryTree<T> getRight(){//getter method for right node
        return right;
    }

    public void attachLeft(BinaryTree<T> tree){//attaches node to left
        if (tree==null) return;
        
        else if (left!=null || tree.getParent()!=null){//cant attach to the left node if one exists
            System.out.println("Can't attach");
            return;
        }
        else{
            tree.setParent(this);//if no node exists, the setParent is called and set left
            this.setLeft(tree);
        }
    }

    public void attachRight(BinaryTree<T> tree){//attaches node to right
        
        if (tree==null) return;
        
        else if (right!=null || tree.getParent()!=null){//cant attach to the right node if one exists
            System.out.println("Can't attach");
            return;
        }
        else{
            tree.setParent(this);//if no node exists, the setParent is called and set right
            this.setRight(tree);
        }
    }

    public BinaryTree<T> detachLeft(){//detaches left node
        
        if (this.isEmpty()) 
            return null;
        BinaryTree<T> retLeft = left;
        left = null;//left is set to null
        
        if (retLeft!=null) retLeft.setParent(null);
           return retLeft;
    }
    public BinaryTree<T> detachRight(){//detaches right node
        if (this.isEmpty()) 
            return null;
        BinaryTree<T> retRight = right;
        right =null;//right node set to null
        
        if (retRight!=null) retRight.setParent(null);
           return retRight;
    }
    public boolean isEmpty(){//returns a boolean if tree is empty
        if (data == null)
            return true;
        else
            return false;
    }
    public void clear(){//clears tree
        left = right = parent =null;
        data = null;
    }

    public BinaryTree<T> root(){
        if (parent == null)
            return this;
        else{
            BinaryTree<T> next = parent;
            while (next.getParent()!=null)
                next = next.getParent();
            return next;
        }
    }

    public static <T> void preorder(BinaryTree<T> node){//prints the tree preorder
        if (node!=null)
        {
            System.out.print(node.getData()+" ");//prints root
            preorder(node.getLeft());// prints left node using recursion
            preorder(node.getRight());//prints right node using recursion
        }
    }

    public static <T> void inorder(BinaryTree<T> node){
        if (node!=null)
        {
            inorder(node.getLeft());//prints left node using recursion
            System.out.print(node.getData() + " ");//prints root
            inorder(node.getRight());//prints right node using recursion
        }
    }

    public static <T> void postorder(BinaryTree<T> node){
        
        if (node!=null){
            postorder(node.getLeft());//prints left node using recursion
            postorder(node.getRight());//prints right node using recursion
            System.out.print(node.getData() + " ");//prints root
        }
    }

    
    public int size(){//Determines the total number of nodes in the tree
        if(left == null && right == null){//if there are no nodes attached to the root
          return 1;
        }
        else if (left != null && right == null){
          return 1+left.size();// uses recursion to count the number of nodes on the left side if there are no right nodes
        }
        else if(left == null && right != null){
          return 1+right.size();// uses recursion to count the number of nodes on the right side if there are no left nodes 
        }
        else{
          return 1+left.size()+right.size();//uses recursion to count the number of nodes if there exists right and left nodes
        }
    }
    
    public int getHeight(){//determines height of the tree
        if(left != null && right != null){//if there are both right and left nodes 
            return 1+ Math.max(left.getHeight(), right.getHeight());//using Math.max calls recurshionon both sides of the tree
        }
        else if(left != null){//if left doesnt equal null, uses recusion to calculated height
            return 1 + left.getHeight();
        }
        else if(right != null){//if right doesnt equal null, uses recusion to calculated height
            return 1 + right.getHeight();
        }
        else{
            return 0;
        }
    }

    //Prints out all objects of the tree by level
    public static<T> void levelOrder(BinaryTree<T> tree){
        
        ArrayList<BinaryTree<T>> queue = new ArrayList<BinaryTree<T>>();//creates a queue that will hold the elements in order
        
        if(tree != null){//
            queue.add(tree);//adds the tree to the queue
        }

        while(!queue.isEmpty()){//while queue doesnt equal empty
            
            BinaryTree<T> node = queue.remove(0);//sets t to and removes and prints the element at index 0 
            
            System.out.print(node.getData()+" ");//calls getData method and prints data for t
            
            if(node.getLeft()!= null){//calls the method getLeft adding it to the queue
                queue.add(node.getLeft());
            }
            
            if(node.getRight() != null){//calls the method getRight adding it to the queue
                queue.add(node.getRight());
            }
        }
    }
    
    public boolean balanced(){//determines if tree is balanced
        
        boolean balanced = false;
        if(left==null && right==null){
           balanced=true;
        }
        else if (left==null)
            balanced = false;
        else if (right==null)
            balanced = false;
        else if (left.getHeight() == right.getHeight()//left height can be no more than 1 higher than right
           ||left.getHeight()+1 == right.getHeight()
           || left.getHeight()-1 == right.getHeight()){
            
            balanced = true;//returns true 
        }
        return balanced;
    }       
}