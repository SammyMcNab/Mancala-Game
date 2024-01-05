package ui;

import mancala.MancalaGame;
import mancala.Player;

import java.util.Scanner;

import mancala.InvalidMoveException;

public class TextUI{   
    private static MancalaGame game;

    private static Player player1;

    private static Player player2;

    public static void main(String[] args){

        Scanner scnr = new Scanner(System.in);

        game = new MancalaGame();
        
        System.out.print("Player 1 name: ");
        player1 = new Player(scnr.next());

        System.out.print("Player 2 name: ");
        player2 = new Player(scnr.next());
        
        game.setPlayers(player1, player2);

        int playAgain = 1;
        
        boolean valid = false;

        while(playAgain == 1) {
            game.startNewGame();

            game.setCurrentPlayer(player1);

            while(!game.isGameOver()){
                
                System.out.print(game.toString());
                //get move from the player
                System.out.print(game.getCurrentPlayer().toString()+"'s turn now\nEnter your pit num: ");
                while(!valid){
                    try {

                        game.move(scnr.nextInt());

                        valid = true;

                    } catch (InvalidMoveException e) {

                        //System.out.print("Please enter a valid move: ");
                        System.out.print(e.getMessage());
                    }
                }

                valid = false;

                try { //make sure that the other side can make a move before changing current player
                    if (game.getCurrentPlayer() == player2 && game.getBoard().isSideEmpty(3) != true){
                        if(!game.getBoard().freeTurn()) {

                            game.setCurrentPlayer(player1);
                        } else {
                            game.getBoard().setFreeturn(false);

                        }             
                    } else if(game.getCurrentPlayer() == player1 && game.getBoard().isSideEmpty(10) != true) {

                        if(!game.getBoard().freeTurn()){

                            game.setCurrentPlayer(player2);

                        } else {

                            game.getBoard().setFreeturn(false);
                        }
                    } 
                } catch (Exception e) {
                    //Exceptions are not possible as p1 and p2 are already checked
                }
            }
            try {
                System.out.print(game.toString());

                System.out.println(game.getWinner().toString() + " is the winner!\n");

                System.out.print("Another round? (1 - Yes, 0 - No): ");
            } catch (Exception e) {
                //never throws exception
            }

            playAgain = scnr.nextInt();

        }  
        scnr.close();
    }
}