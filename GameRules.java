package com.example.projetojogogalo;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class GameRules {
     String currentPlayer;
     int Xwins;
     int Owins;
     int roundCounter;


     public GameRules(String startingPlayer){
         this.currentPlayer = startingPlayer;
         this.Owins = 0;
         this.Xwins = 0;
         this.roundCounter = 0;
     }

     public void updateWins (){
         if (this.currentPlayer.equals("x"))
             this.Xwins += 1;
         else if (this.currentPlayer.equals("o")) {
             this.Owins += 1;
         } else
             System.out.println("SOMETHING WENT WRONG!");
     }

    public boolean checkDraw(){
        this.roundCounter++;
        return this.roundCounter == 9;
    }

     public boolean checkWon(GridPane board){
         //if ((getButtonFromBoard(board, 0, 0).getIdentity()).equals((getButtonFromBoard(board, 0, 1).getIdentity()))){
         //    System.out.println("0.0 é igual a 0.1 e 0.2 WOW");
        // }
         return (checkAllLineWin(board) || checkAllColWin(board) || checkLeftDiagonalWin(board) || checkRightDiagonalWin(board));
     }

     public boolean checkRightDiagonalWin (GridPane board){
         boolean piecesEqual = false;

         for (int i = 0; i < 2; i++){
             if (getButtonFromBoard(board, i, i).getIdentity().equals("empty") || !(getButtonFromBoard(board, i, i).getIdentity().equals((getButtonFromBoard(board, i + 1, i + 1).getIdentity())))) {
                 piecesEqual = false;
                 break;
             }
             piecesEqual = true;
         }

         return piecesEqual;
     }

    public boolean checkLeftDiagonalWin (GridPane board){
        boolean piecesEqual = false;

        for (int i = 0, j = 2; i < 2 && j >= 0; j--, i++){
                if (getButtonFromBoard(board, i, j).getIdentity().equals("empty") || !(getButtonFromBoard(board, i, j).getIdentity().equals((getButtonFromBoard(board, i + 1, j - 1).getIdentity())))) {
                    piecesEqual = false;
                    break;
                }
                piecesEqual = true;
        }
        return piecesEqual;
    }

     public boolean checkAllColWin (GridPane board){
         boolean piecesEqual = false;

         for (int i = 0; i < 3; i++){
             for (int j = 0; j < 2; j++){
                 if (getButtonFromBoard(board, j, i).getIdentity().equals("empty") || !(getButtonFromBoard(board, j, i).getIdentity().equals((getButtonFromBoard(board, j + 1, i).getIdentity())))) {
                     piecesEqual = false;
                     break;
                 }
                 piecesEqual = true;
             }
             if (piecesEqual){
                 return true;
             }
         }
         return false;
     }

     public boolean checkAllLineWin (GridPane board){
         boolean piecesEqual = false;

         for (int i = 0; i < 3; i++){
             for (int j = 0; j < 2; j++){
                 if (getButtonFromBoard(board, i, j).getIdentity().equals("empty") || !(getButtonFromBoard(board, i, j).getIdentity().equals((getButtonFromBoard(board, i, j + 1).getIdentity())))) {
                     piecesEqual = false;
                     break;
                 }
                 piecesEqual = true;
             }
             if (piecesEqual){
                 return true;
             }
         }
         return false;
     }

     public GameButton getButtonFromBoard (GridPane board, int row, int col){
         for (Node node : board.getChildren()){
             if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                 return (GameButton) node;
             }
         }
         return null;
     }

     public void changeCurrentPlayer(){
         if (this.currentPlayer.equals("x"))
             this.currentPlayer = "o";
         else
             this.currentPlayer = "x";
     }

     public void reset (String startingPlayer){
         setCurrentPlayer(startingPlayer);
         setRoundCounter(0);
     }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String player){
         this.currentPlayer = player;
    }

    public void setRoundCounter(int counter){
         this.roundCounter = counter;
    }

    public int getOwins() {
        return Owins;
    }

    public int getXwins() {
        return Xwins;
    }
}

