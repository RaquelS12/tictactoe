package com.example.projetojogogalo;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.Optional;

public class Game extends StackPane {
    GridPane gridPane;
    Label labelXWins;
    Label labelOWins;
    Label labelCurrentPlayer;
    String getCurrentPlayerToLabel;
    HBox hBoxLabelsWins;
    HBox hBoxLabelCurrentPlayer;
    String getXwinsToLabel;
    String getOwinsToLabel;
    Alert alertWin;
    Alert alertDraw;
    String startingPlayer;
    GameRules rules; //start player X

    public Game() {
        this.startingPlayer = "x";

        this.rules = new GameRules(this.startingPlayer);

        gridPane = new GridPane();
        this.getOwinsToLabel = "O wins: " + this.rules.getOwins();
        this.getXwinsToLabel = "X wins: " + this.rules.getXwins();
        hBoxLabelsWins = new HBox();
        labelXWins = new Label(this.getXwinsToLabel);
        labelOWins = new Label(this.getOwinsToLabel);

        hBoxLabelCurrentPlayer = new HBox();
        this.getCurrentPlayerToLabel = "Current player: " + this.rules.getCurrentPlayer();
        labelCurrentPlayer = new Label(this.getCurrentPlayerToLabel);

        this.hBoxLabelsWins.getChildren().addAll(this.labelXWins, this.labelOWins);
        this.hBoxLabelsWins.setSpacing(50);
        this.hBoxLabelsWins.setPadding(new Insets(30, 0, 0, 0));

        this.hBoxLabelCurrentPlayer.getChildren().add(this.labelCurrentPlayer);
        this.hBoxLabelCurrentPlayer.setPadding((new Insets(80,0,0,0)));

        createBoard();

        this.setMinSize(800,800);
        this.getChildren().addAll(this.hBoxLabelsWins, this.hBoxLabelCurrentPlayer, this.gridPane);
        this.gridPane.setAlignment(Pos.CENTER);
        this.hBoxLabelsWins.setAlignment(Pos.TOP_CENTER);
        this.hBoxLabelCurrentPlayer.setAlignment(Pos.TOP_CENTER);
    }

    public void createBoard (){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                GameButton myButton = new GameButton(j,i);
                this.gridPane.add(myButton, i, j);
                myButton.setOnAction(event -> {
                   // myButton.setFocusTraversable(false);
                    play(myButton);
                });
            }
        }
    }

    public void alertWinContinueOrExit(){
        Alert alertWin = new Alert(Alert.AlertType.CONFIRMATION);
        alertWin.setHeaderText("WINNER!");
        alertWin.setContentText("The player " + rules.getCurrentPlayer() + " is the WINNER. Play again?");

        Optional<ButtonType> result = alertWin.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        if (button == ButtonType.OK) {
            System.out.println("I CLICK OK");
            reset();
        } else {
            System.out.println("I CLICK CANCEL");
            Platform.exit();
            System.exit(0);
        }
    }

    public void alertDrawContinueOrExit(){
        Alert alertDraw = new Alert(Alert.AlertType.CONFIRMATION);
        alertDraw.setHeaderText("DRAW!");
        alertDraw.setContentText("It was a DRAW. Play again?");

        Optional<ButtonType> result = alertDraw.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);

        if (button == ButtonType.OK) {
            System.out.println("I CLICK OK");
            reset();
        } else {
            System.out.println("I CLICK CANCEL");
            Platform.exit();
            System.exit(0);
        }
    }

    public void reset(){
        rules.reset(this.startingPlayer);

        for (Node node : this.gridPane.getChildren()){
            GameButton button = (GameButton) node;
            button.setIdentity("empty");
            button.setDisable(false);
            button.updateImg();
        }
    }

    public void play(GameButton myButton) {
        //myButton.setText(rules.getCurrentPlayer());
        myButton.setDisable(true);
        myButton.setIdentity(rules.getCurrentPlayer());
        myButton.updateImg();


        if (rules.checkDraw())
            alertDrawContinueOrExit();
        else if (rules.checkWon(this.gridPane)){
            rules.updateWins();
            alertWinContinueOrExit();
        }else
            rules.changeCurrentPlayer();

        this.getCurrentPlayerToLabel = "Current player: " + this.rules.getCurrentPlayer();
        this.labelCurrentPlayer.setText(this.getCurrentPlayerToLabel);

        this.getOwinsToLabel = "O wins: " + this.rules.getOwins();
        this.getXwinsToLabel = "X wins: " + this.rules.getXwins();
        this.labelXWins.setText(this.getXwinsToLabel);
        this.labelOWins.setText(this.getOwinsToLabel);
    }
}
