/*Joshua Cadegan-Syms B00528943 This program creates an object of type Process with four
variables, ID, timeReqd, priority, and timeArrival
*/

public class Process implements Comparable<Process>{/*in order to add a process object to the heap, which 
                                                     extends Comparable, we make it so that Process implements Comparable */ 	

   private int	id;//instance variables
   private int	timeReqd;
   private int priority;
   private int	timeArrival;

   public Process(){}//empty constructor
   public Process(int id,int timeReqd, int priority, int	timeArrival ){//constructor
      
      this.id= id;
      this.timeReqd=timeReqd;
      this.priority=priority;
      this.timeArrival=timeArrival;
   }
   
   public int getId(){return id;}//get methods
   public int getTimeReqd(){return timeReqd;}
   public int getPriority(){return priority;}
   public int getTimeArrival(){return timeArrival;}
   
   public void setId(int id){this.id = id; }//Setter methods 
   public void setTimeReqd(int timeReqd){this.timeReqd = timeReqd; }
   public void setpriority(int priority){this.priority = priority; }
   public void setTimeArrival(int timeArrival){this.timeArrival = timeArrival; }
   
   public int compareTo(Process process){//compareTo method that overrides compareTo in Comparable
   
      return Integer.compare(getPriority(),process.getPriority());
   
   }
   
   public String toString(){//toString method
      
      return  "(" + getTimeReqd() + " " + getPriority() + " " + getTimeArrival()+ ")";
   
   
   
   }
   
   
   }