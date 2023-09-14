/*
 * Yvan Pierre Jr.
 * Dr, Steinberg
 * COP3503 Fall 2023
 * Programming Assignment 1
 */
import java.io.*;
import java.util.*;

public class Game {
    private int[][] chessboard; //2D chessboard 
    private char[] chessmover; //array to hold text file 
    private int boardSize; // boardsize given.

    public Game(int boardSize, String filename) {
        this.boardSize = boardSize;
        this.chessboard = new int[boardSize][boardSize];
        this.chessmover = new char[15]; //text file has 15 char max
        this.readMoves(filename);
    }

    public void readMoves(String filename) { // should read the files 
        try {
            Scanner file = new Scanner(new File(filename));

            for (int i = 0; i < 15 && file.hasNextLine(); i++) {
                String line = file.nextLine();
                if (!line.isEmpty()) {
                    chessmover[i] = line.charAt(0);
                }
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // private void EvenMovements(int row, int column){
    //     if (((row+1)%2==0) && ((column+1)%2==0)){
    //         row++;
    //         column++;
    //     }
    //     else if (((row+1)%2==0)){
    //         row++;
    //     }
    //     else if ((column+1%2==0)){
    //         column++;
    //     }}

    // private void OddMovements(int row, int column){
    //     if (((row+1)%2!=0) && ((column+1)%2!=0)){
    //         row++;
    //         column++;
    //     }
    //     else if (((row+1)%2!=0)){
    //         row++;
    //     }
    //     else if ((column+1%2!=0)){
    //         column++;
    //     }}
////////////////////////////////////////////////////////////////////////////////////
//all above is confirmed done and good for working // why does helper methods not work?

    public int play(int winplayer){
        int row = 0;
        int column = 0;
        int current_player;
        current_player = 1;
        while (row != 7 || column !=7){
            //System.out.printf("%d %d \n",row, column);
            
            if (winplayer == 1){ // WinPlayer 1 Condition 
                // System.out.printf("%d %d \n",row, column);
                if (row == 7){ //if reach bound, do opposites
                column++;
                }
                else if(column == 7){
                row++;
                }
                // if row is odd, preferred position
                else if (((row+1)%2!=0) && ((column+1)%2!=0)){
                    row++;
                    column++;
                }
                else if ((row+1)%2!=0){
                    row++;
                }
                else if ((column+1)%2!=0){
                    column++;
                }
                //marks those spaces as P1
                chessboard[row][column] = 1;
                if (row == 7 && column == 7){
                    //winning condition
                    return current_player;
                }
                // methods werent working so copied them in, 
                //sorry for improper initialization location
                int index = 0;
                char currentChar = chessmover[index]; //array handler
                // Player 2 logic for WinningPlayer 1 still
                if (row == 7){ //if hit bound do opposite 
                    column++;
                }
                if (column == 7){
                    row++;
                }
                if (row < boardSize - 1 && currentChar == 'b') { //down 
                    row++;
                } else if (column < boardSize - 1 && currentChar == 'r') { //right
                    column++;
                } else if (row < boardSize - 1 && column < boardSize - 1 && currentChar == 'd') { //diagonal
                    row++;
                    column++;
                }
                // System.out.printf("%d %d \n",row, column);
                chessboard[row][column] = 2;
                if (row == 7 && column == 7){ //Winning Condition for if Player 2 wins, should not happen.
                    current_player = 2;
                    return current_player;
                }
                // System.out.println(row != 7 && column !=7);
            }
            else if(winplayer == 2){ //When Player 2 should win
                if (winplayer == 2 && row== 0 && column == 0){
                    row=1; // setting up first move how we want it!
                    column=0;
                }
                if (((row+1)%2!=0) && ((column+1)%2!=0)){ //this is Player 2 logic, with odd bias
                    row++;
                    column++;
                }
                else if (((row+1)%2!=0)){
                    row++;
                }
                else if ((column+1%2!=0)){
                    column++;
                }
                chessboard[row][column] = 2;
                if (row==7 && column == 7){ // Return Player 2 as winner if it reaches here first.
                    current_player = 2;
                    return current_player;
                }

                if (((row+1)%2==0) && ((column+1)%2==0)){ //Player 1 logic, with even bias.
                    row++;
                    column++;
                }
                else if (((row+1)%2==0)){
                    row++;
                }
                else if ((column+1%2==0)){
                    column++;
                }
                chessboard[row][column] = 1;
                if(row == 7 && column == 7){
                    return current_player; // Will return as Player 1 winning if wrong.
                }
                
            }
        }
        return -1; //Note to self. After While loop, it request a return statement still even if returning statements.
    }
}