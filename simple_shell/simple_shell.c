/*

Date: May 27,2020
Author: Joshua Cadegan-Syms B00528943

Program: This program demonstrates a simple shell program able to take multiple arguments per line as 
well as pipe. To exit the program, type exit into the command line

Compile: In order to compile and run  this program, enter: gcc -o A2 A2.c then ./A2

*/


#include <stdio.h>
#include<stdlib.h>
#include <sys/wait.h>
#include<fcntl.h>
#include<sys/stat.h> //for file status description
#include <string.h>
#include <sys/types.h>
#include<dirent.h> //for readdir--ls command
#include <unistd.h>
#include<signal.h> // for exit

# define MAXINPUT 100

void input_parcing(char *line, char **argv);

int main(){
    
    int pipe_fds[2];
    pid_t parent_pid_t;
    pid_t child_1_pid_t;
    pid_t child_2_pid_t;
    
    
    int input_array_position; 
    int output_array_position;
    
    char input_command[MAXINPUT];
    char *input_pointer[MAXINPUT];
    
    while (1){

        printf("$ ");
        fgets(input_command,MAXINPUT, stdin);//Takes in input from terminal
        input_parcing(input_command,input_pointer);
        
        if(strstr(input_command,"exit")||strstr(input_command,"Exit")||
        strstr(input_command,"Quit")|| strstr(input_command,"quit"))break; //if exit, Exit, quir, or Quit is inputted then program terminates
        else system(input_command);
      
        if(input_pointer[1]!=NULL && strcmp(input_pointer[1],"|")==0){

            int pipefd[2];
            char str[MAXINPUT];
            int fp;

            input_array_position = dup(STDIN_FILENO);
            output_array_position = dup(STDOUT_FILENO);

            if (pipe(pipefd) < 0){
              perror("Error!");
                return -1;
            }

            parent_pid_t=fork();

            if(parent_pid_t==0){

                close(pipe_fds[0]);
                strcpy(str,"./");
      
                dup2(pipe_fds[1],STDOUT_FILENO);
                strcat(str,input_pointer[0]);       // Utilizes output as input for the pipe. 

                if(execvp(str,&input_pointer[0])==-1){
                    perror("invalid");
            }
        exit(1);
        }

        else{

            wait(NULL);
            child_1_pid_t=fork();
        
            if(child_1_pid_t==0){
              
            close(pipefd[1]);
            dup2(pipefd[0],STDIN_FILENO);
        
            strcpy(str,"./");
            strcat(str,input_pointer[2]);

            if( execvp(str, & input_pointer[2])==-1) {
                perror("Error!");
                exit(1);
            }

          exit(1);
        }

      else if(input_pointer[4]!=NULL){
        wait(NULL);
        
        child_2_pid_t=fork();
        
        if(child_2_pid_t==0 ){
          close(pipefd[1]);

          dup2(pipefd[0],STDIN_FILENO);/* Make the read end of the pipe stdin. */
          dup2(pipefd[1],STDOUT_FILENO);
          
          strcpy(str,"./");/* Run another program. */
          strcat(str,input_pointer[4]);
          
          if(execvp(str, & input_pointer[4])==-1){
            perror("Error!");
          }
        }
      }
      exit(1);
      }     
    }
  }  
return 0;
}
void input_parcing(char *line, char **argv){
  while (*line != '\0') {    
    while (*line == ' ' || *line == '\t' || *line == '\n'){
        *line++ = '\0'; 
    }
    *argv++ = line;
    
    while (*line != '\0' && *line != '\t' && *line != '\n'){
        line++;
    }
  }
*argv = NULL;//mark the end of argument   
}



