package com.example.projetojogogalo;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameButton extends Button {
    int row;
    int col;
    String identity;
    static Image imgEmpty = new Image("/imageEmpty.png");
    static Image imgX = new Image("/imageX.png");
    static Image imgO = new Image("/imageO.png");
    ImageView imageView;
    int size;

    public GameButton(int row, int col){
        this.size = 100;
        //this.setPrefSize(100, 100);
        this.setMaxWidth(size);
        this.setMaxHeight(size);
        this.row = row;
        this.col = col;
        this.identity = "empty";
        this.imageView = new ImageView(imgEmpty);
        sizeFixImage();
        this.setGraphic(imageView);
    }

    public void setIdentity(String id) {
        this.identity = id;
    }

    public void updateImg () {
        if (this.identity.equals("x")) {
            this.imageView.setImage(imgX);
            sizeFixImage();
        } else if (this.identity.equals("o")) {
            this.imageView.setImage(imgO);
            sizeFixImage();
        } else {
            this.imageView.setImage(imgEmpty);
            sizeFixImage();
        }
    }

    public void sizeFixImage (){
        this.setContentDisplay(ContentDisplay.CENTER);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
    }

    public String getIdentity() { return identity;}

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
