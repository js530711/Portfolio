/*
Joshua Cadegan-Syms


This program creates a BinarySearchTree object, with various methods to add, remove,
clear and search a generic type tree. 


*/
public class BinarySearchTree<T extends Comparable<T>>{
//you are using the compareTo method on objects of type T; hence T should extend Comparable<T>
	private BinaryTree<T> tree;
	private int size;
	
	public BinarySearchTree(){
		tree = new BinaryTree<T>();
		size=0;
	}
   
	public BinaryTree<T> getTree(){
		return tree;
	}
	
   public boolean isEmpty(){
		return tree.isEmpty();
	}
	public int size(){
		return size;
	}
   
	public BinaryTree<T> search(T key){
		BinaryTree<T> t = tree;
		
      if (isEmpty()) return null;
		
      while (t!=null){
			
         if (key.compareTo(t.getData())<0)
				t = t.getLeft();
			else if (key.compareTo(t.getData())>0)
				t = t.getRight();
			else // key is found
				return t;
		}
		return null;
	}
		
		
	 public void insert(T item){
		
      BinaryTree<T> newNode = new BinaryTree<T>(); //sets left, right, parent and data to null
		newNode.setData(item);

		if (size==0){tree = newNode; size++;return;}
		
		BinaryTree<T> t = tree;
		boolean done = false;
		while (!done){
			int c = item.compareTo(t.getData());
			
         if (c==0){return;}
			
         else if (c<0){ //need to go left
			
				if (t.getLeft()==null){	//place to insert found
				
					t.setLeft(newNode);
					newNode.setParent(t);
					size++;
					done = true;
				}
				else
					t = t.getLeft(); //otherwise go left one branch
			}
			else{ //c>0; need to go right
			
				if (t.getRight()==null){ //place to insert found
				
					t.setRight(newNode);
					newNode.setParent(t);
					size++;
					done=true;
				}
				else
					t = t.getRight();
			}
		}
	}
	
	public BinaryTree<T> findPredecessor(BinaryTree<T> node){
		
      if (node==null) return null;
		if (node.getLeft()==null) return null;
		BinaryTree<T> pred = node.getLeft();
		while (pred.getRight()!=null)
			pred = pred.getRight();
		return pred;
	}
	
	public void deleteHere(BinaryTree<T> deleteNode, BinaryTree<T> attach){
		
      if (deleteNode==null) return;
		BinaryTree<T> parent = deleteNode.getParent();
		
		if (parent == null) return;
		if (attach == null)
		{
			if (parent.getLeft()==deleteNode)
				parent.setLeft(null);
			else
				parent.setRight(null);
			return;
		}
		if (deleteNode==parent.getRight()){
			parent.detachRight();
			deleteNode.setParent(null);
			//attach.setParent(parent);
			attach.setParent(null);
			parent.attachRight(attach);
			attach.setParent(parent);
		}
		else{
			parent.detachLeft();
			deleteNode.setParent(null);
			
			//attach.setParent(parent);
			attach.setParent(null);
			parent.attachLeft(attach);
			attach.setParent(parent);
		}
		deleteNode.clear();
	}
	
	public void delete(T key){//deleates a node equal to the inputted key
	
		if (size==0){System.out.println("Can't delete. Empty tree"); return;}
		BinaryTree<T> deleteNode = search(key);
		if (deleteNode==null) {System.out.println("Key not found. Can't delete"); return;}
		
		BinaryTree<T> hold = null;
		if (deleteNode.getLeft()==null && deleteNode.getRight()==null){
			deleteHere(deleteNode, null);
		}
		else if (deleteNode.getLeft()==null){
			hold = deleteNode.getRight();
			deleteHere(deleteNode, hold);
		}
		else if (deleteNode.getRight()==null){
			hold = deleteNode.getLeft();
			deleteHere(deleteNode, hold);
		}
		else{
			hold = findPredecessor(deleteNode);
			deleteNode.setData(hold.getData());
			deleteNode=hold;
			deleteHere(deleteNode, deleteNode.getLeft());
		}
		size--;
	}
   

   public T findMax(){//returns the largest key in the binary search tree

      BinaryTree<T> t = tree;

      if (isEmpty()) 
         return null;

      while (t.getRight() !=null){
         t = t.getRight();
      }
      return t.getData();
   }

   public T findMin(){//returns the smallest key in the binary search tree
 
    BinaryTree<T> t = tree;

    if (isEmpty()) return null;

  while (t.getLeft() !=null){

      t = t.getLeft();
  }

  return t.getData();
 }
 
 public BinaryTree<T> recursiveSearch(T key){

  if (tree.isEmpty())//returns null if tree is empty
    return null;

  else
    return recursiveSearch(tree, key);//calls the method recursiveSearch

 }

  public BinaryTree<T> recursiveSearch(BinaryTree<T> t, T key){// recursive search method

    if(t==null){
        return null;
    }
    
    if(key.compareTo(t.getData()) == 0) { 
          return t;
    }

    else if (key.compareTo(t.getData())<0)//if the key is less, t.getleft is called 
      return recursiveSearch(t.getLeft(), key);

    else if (key.compareTo(t.getData())>0)//if the key is greater, t.getright is called
      return recursiveSearch(t.getRight(), key);
   
    else
      return null;//otherwise, null is returned
  }

  public static <T> void inorder(BinaryTree bst){//static method that print the tree in order
                
        if (bst!=null)
        {
            inorder(bst.getLeft());//prints left node using recursion
            System.out.print(bst.getData() + " ");//prints root
            inorder(bst.getRight());//prints right node using recursion
        }
    }

  public static void getHeight(BinaryTree bst){//static method that will print the height of the tree
   System.out.println(bst.getHeight());
  }
 
  public static void balanced(BinaryTree bst){//with print true/false if the tree is balanced
   System.out.println( bst.balanced());
 
  }
 }
	