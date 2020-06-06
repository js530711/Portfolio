/*Joshua Cadegan-Syms B00528943. This program builds an application that demonstrates 	 
how a	CPU uses a	heap data structure (priority	 queue) for	process scheduling. 
*/

import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

public class Heap_demo {
 public static void main(String[] args)throws IOException {
 
 Scanner keybord = new Scanner(System.in);
 Heap<Process> heap= new Heap<Process>();//a new heap of type process is created
 
 System.out.println("Enter the file name to read: ");//user inputs the name of a file for the program to read
 String filename = keybord.nextLine(); 

 File file = new File(filename);//a new file object is created
 StringTokenizer token;
 int timeUnit = 1;//this will track the time units 

 do{//this do/while loop will continue to loop while heap.empty is not empty
 
 Scanner inputFile = new Scanner(file);
 while (inputFile.hasNext()) {
    
    String line = inputFile.nextLine();
    token = new StringTokenizer(line, " ");
    
    int id = Integer.parseInt(token.nextToken());//the program sets each of the variables converting them to integers
    int timeReqd = Integer.parseInt(token.nextToken()); 
    int priority = Integer.parseInt(token.nextToken()); 
    int timeArrival = Integer.parseInt(token.nextToken()); 
    
    Process newProcess = new Process(id,timeReqd,priority,timeArrival);/*a new process object is created and the 
                                                                        required parameters are passed through*/
      if( timeArrival==timeUnit){//if timeArrival is equal to timeUnit, process is added to the heap
         heap.add(newProcess);
      }   
    }
 
    inputFile.close();//the file is closed
    
    System.out.println("Time Unit: " + timeUnit);
    System.out.println();
    System.out.print("Heap: ");
    heap.enumerate();//the heap is printed
    
    Process maxProcess=heap.deleteMax();/* finds the highest priority element in the heap, 
                                           deletes it and sets maxProcess to equal it*/ 
    
    System.out.println("CPU:" + maxProcess.toString());//the highest priority element is processed by the CPU
    maxProcess.setTimeReqd(maxProcess.getTimeReqd()-1);//TimeReqd is reduced by 1 
    
    
    if(maxProcess.getTimeReqd()>=1){/*if TimeReqd for highest propity is greater 
                                      or equal to 1 it is readded to the heap*/
      heap.add(maxProcess);
    }
    
    System.out.print("New Heap:");//A new heap is printed containing all of the elements that require more processing
    heap.enumerate();
    System.out.println();
    
    timeUnit++;//time unit is increated by 1
       
  }while(heap.isEmpty()==false);
 }
}
