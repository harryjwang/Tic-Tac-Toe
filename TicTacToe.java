package twoClasses;

import java.io.*;
import java.util.*;

class TicTacToe {

   //create board array of 3x3 chars
   char board[][] = new char[3][3];

   //constructor
   TicTacToe(){
       for(int i = 0; i < 3; i++){

           //Initialize the Board with '-' to mark empty spaces
           Arrays.fill(board[i],'-');
       }
   }

   //method to check whether 3 characters in row are same or not
   boolean checkRow(char row[]){

       if((row[0] == row[1]) && (row[1]== row[2]) && (row[2] != '-') ) {
           return true;
       }
       return false;
   }

   //method to check if spot in small board is taken yet
   boolean isAvailable(int p1, int p2) {
	   if(board[p1][p2] == 'X' || board[p1][p2] == 'O') {
		   System.out.println("Spot is taken up, please try again");
		   return false;
	   }
	   return true;
   }
   
   
   //set a character on board at a specific Location
   boolean setChar(char c, int p1, int p2){
           board[p1][p2] = c;
           return true;
   }
  
   //method to check if small board is completely filled
   boolean checkFull() {
	   for(int row = 0; row < 3; row++) {
    	   for(int col = 0; col < 3; col++) {
    		   if(board[row][col] == '-') {
    			   return false;
    		   }
    	   }
       }
	   return true;
   }

   //method to display The entire board
   void display(int boardNum){
       System.out.print("BOARD#" + boardNum);
       System.out.println();
       
       for(int row = 0; row < 3; row++) {
           for(int col = 0; col < 3; col++) {
               System.out.print(board[row][col] + "\t");
           }
           System.out.println();
       }
   }

   //after every successful input, check for if there's any winning combinations
   char checkResult(){

       char myrow[] = new char[3];

       //check row
       for(int row = 0; row < 3; row++){
           if(checkRow(board[row])){
               return board[row][0];
           }
       }

       //check column
       for(int row = 0; row < 3; row++){
           for(int col = 0; col < 3; col++){
               myrow[col] = board[col][row];
           }
           if(checkRow(myrow)){
               return board[0][row];
           }
       }

       //check diagonals
       myrow[0] = board[0][0];
       myrow[1] = board[1][1];
       myrow[2] = board[2][2];

       if(checkRow(myrow)){
           return board[0][0];
       }

       myrow[0] = board[0][2];
       myrow[1] = board[1][1];
       myrow[2] = board[2][0];

       if(checkRow(myrow)){
           return board[1][1];
       }
       return ' ';
   }
}
