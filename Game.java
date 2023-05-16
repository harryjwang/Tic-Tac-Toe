// Harry Wang

// Analysis
/* 
 * In this program, we need to have 9 individual TicTacToe games that are connected via one large
 * TicTacToe board. Each time a player puts down their mark on a small TicTacToe board, the next player's
 * move can only be in the small TicTacToe game board that corresponds to that section of the large
 * TicTacToe board. This goes on until a small TicTacToe game is either won by a player (3 in a row) or is
 * completely filled and with no winner. Once a small TTT board is filled, the next player can choose the
 * board they want to put their mark in, instead of being forced to put it based on the opponent's last input.
 * However, if a small TTT game is won but not filled, a player can still be forced to put their mark in, but
 * it won't change the result of that board. The game ends when the big TTT board has three in a row or is filled
 * and with no winner. The overall board is printed out each time a valid input is chosen. If an invalid input is
 * chosen, then the player is prompted to go again until they choose a valid input.
 */


// Design
/*
 * We have two big classes; one main driver that implements all the methods and holds the variables, and a TicTacToe
 * class that holds the methods. We will use the system to prompt users for input. We'll use the TicTacToe class to 
 * create a 2D array of chars that will be used to hold the contents of the small boards, as well as an extra board 
 * that will represent the larger TTT game board. We'll prompt the user for the game Mode and then the code will react
 * accordingly, either allowing both, one, or none of the players to input coordinates and possibly board numbers. Using
 * a while loop that runs until the large board has a winner or is full, we will also use for loops to access different rows
 * and columns. After * checking for valid inputs, we can use methods to set the designated player's symbol into the smaller 
 * TTT game board and then display the entire board. After each successful input, we'll check if the given small board is full, 
 * in which case we'll ask the next player to choose a boardNumber to move to, rather than moving according to the last move's 
 * position. Otherwise, depending on where the last player moved, the boardNumber for the next iteration will be changed.
 */




package twoClasses;

import java.io.*;
import java.util.*;


class Game {

	   public static void main(String[] args) throws IOException {

		   Scanner input = new Scanner(System.in);

	       //variables
		   int boardNumber = 0;
	       int row = 0; 
	       int column = 0;
	       int playerNum = 1;
	       int gameMode;
	       char symbol = ' ';
           boolean isFull = false;

           //formatting and design
           System.out.println("Ultimate TIC-TAC-TOE");
   			System.out.println("--------------------");
           System.out.println("Printing all the boards: ");
	       
           //Create TTT array and boards
	       TicTacToe boards[] = new TicTacToe[10];
	       //display empty board
	       for(int i = 0; i < 9; i++){
	           boards[i] = new TicTacToe();
	           boards[i].display(i);
	       }
	       boards[9] = new TicTacToe(); //this last board (9) is to keep track of overall wins aka large TTT game

	       //execute min once; choose the game mode
	       do {
	    	   System.out.println("Choose: 1 = AI vs AI, 2 = Player vs AI, 3 = Player vs Player");
	    	   gameMode = input.nextInt();
	       }while(gameMode < 1 || gameMode > 3);
	       
	       
	       //User choice to select any board to play
	       System.out.println("Select a Board to play");
	       boardNumber = input.nextInt();

	       while(true) {

	           //switch Players alternatively
	           if(playerNum == 1){
	               System.out.println("Player 1");
	               symbol = 'X';
	               playerNum = 2;
	           } 
	           else {
	               System.out.println("Player 2 move: ");
	               symbol = 'O';
	               playerNum = 1;
	           }

	           while(true){
	        	   //1 = AI vs AI | 2 = Player vs AI | 3 = Player vs Player
	               
	        	   if(symbol == 'X') {	//Player 1's turn
	            	   if(gameMode == 1) {
	            		   row = (int)(Math.random() * 3);
		            	   column = (int)(Math.random() * 3);
	            	   }else if(gameMode == 2) {
	            		   row = input.nextInt();
			               column = input.nextInt();
	            	   }else if(gameMode == 3) {
	            		   row = input.nextInt();
			               column = input.nextInt();
	            	   }

	               } else if(symbol == 'O') { //Player 2's turn
	            	   if(gameMode == 1) {
	            		   row = (int)(Math.random() * 3);
		            	   column = (int)(Math.random() * 3);
	            	   }else if(gameMode == 2) {
	            		   row = (int)(Math.random() * 3);
		            	   column = (int)(Math.random() * 3);
	            	   }else if(gameMode == 3) {
	            		   row = input.nextInt();
			               column = input.nextInt();
	            	   }
	               }
	               

	               //checks that coordinates make sense aka are valid
	               if(row >= 3 || column >= 3){
	                   System.out.println("Invalid Inputs, coordinates cannot be greater than 2");
	                   System.out.println("Enter Again");
	               } else if(row < 0 || column < 0 ){
	                   System.out.println("Invalid Inputs, coordinates cannot be negative");
	                   System.out.println("Enter Again");
	                
	               } 
	               // check that space in small board is not full
	               else {		
	            	   if(boards[boardNumber].isAvailable(row, column) == true) {
	            		   if(boards[boardNumber].setChar(symbol, row, column)){
	            			   break;
	            		   }
	                   }
	               }
	           }
	           
	           //display all boards 
	           for(int i = 0; i < 9; i++){

		           boards[i].display(i);
		       }

	           //Check for Win Results after every move
	           char res = boards[boardNumber].checkResult();
	           //if a singular TTT board is won, then set its symbol in board 10
	           if(res == 'X'){

	               System.out.println("Player1 takes BOARD#" + boardNumber);
	               boards[9].setChar('X', boardNumber / 3, boardNumber % 3);
//	               boards[9].display(9);
	           }
	           else if(res == 'O'){

	               System.out.println("Player2 takes BOARD#" + boardNumber);
	               boards[9].setChar('O', boardNumber / 3, boardNumber % 3);
//	               boards[9].display(9);
	           }
	           
	           
	           
	           //check for overall win (large board)
	           char overallWin = boards[9].checkResult();

	           //different displays based on winner
	           if(overallWin == 'X'){

	               System.out.println("Player 1 wins");
	               System.out.println("--------------------");	               
	               boards[9].display(9);
	               break;
	           }
	           else if(overallWin == 'O'){

	               System.out.println("Player 2 wins");
	               System.out.println("--------------------");
	               boards[9].display(9);
	               break;
	           }
	           
	           
	           //check if board is full
	           //go through the board and find a '-'; if no dashes, it's full
	           boards[boardNumber].checkFull();
	           
	           //if small board is full, keep asking for valid board that is not full to choose
	           if(boards[boardNumber].checkFull()) {
	        	   while(boards[boardNumber].checkFull()) {
	        		   System.out.println("Board is full! You can choose what board you want to add to");
	        		   
	        		   if(symbol == 'X') {	//Player 1's choice
		            	   if(gameMode == 1) {
		            		   boardNumber = (int)(Math.random() * 9);
		            	   }else if(gameMode == 2) {
		            		   boardNumber = input.nextInt();
		            	   }else if(gameMode == 3) {
		            		   boardNumber = input.nextInt();
		            	   }
		            	   
		               } else if(symbol == 'O') { //Player 2's choice
		            	   if(gameMode == 1) {
		            		   boardNumber = (int)(Math.random() * 9);
		            	   }else if(gameMode == 2) {
		            		   boardNumber = (int)(Math.random() * 9);
		            	   }else if(gameMode == 3) {
		            		   boardNumber = input.nextInt();
		            	   }
		               }
	        	   }
	           } else { //boardNumber based on placement of last symbol
		           boardNumber = (row * 3) + column;
	           }
	           
	           //design and formatting
	           System.out.println("Next BOARD#: " + boardNumber);
	           System.out.println("---------------------------------------");
	           
	         
	       }
	   }
	}
