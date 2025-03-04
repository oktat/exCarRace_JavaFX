package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MainController {    

    private Timeline timeline = new Timeline();
    private Vehicle vehicle1;
    private Vehicle vehicle2;
    private int TARGET = 800;

    @FXML
    private Pane panel;

    @FXML
    private ListView<String> list;

    @FXML
    void initialize() {
        vehicle1 = new Vehicle("Piros", Color.RED, 150);
        vehicle2 = new Vehicle("Kék", Color.BLUE, 190);
        panel.getChildren().add(vehicle1);
        panel.getChildren().add(vehicle2);

        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.03), e -> {
        
            if ((vehicle1.getX() + vehicle1.getWidth()) > TARGET) {
                addToList(vehicle1.name);
            }
            if (vehicle1.getX() < 810) {
                vehicle1.move();            
            }
            if ((vehicle2.getX() + vehicle2.getWidth()) > TARGET) {
                addToList(vehicle2.name);
            }            
            if (vehicle2.getX() < 810) {
                vehicle2.move();
            }

            if(vehicle1.getX() >= 810 && vehicle2.getX() >= 810) {
                timeline.stop();
            }
        });
        timeline.getKeyFrames().add(keyFrame);       
    }

    @FXML
    void onClickStartButton(ActionEvent event) {
        timeline.play(); 
    }

    @FXML
    void onClickResetButton(ActionEvent event) {
        vehicle1.moveToStart();
        vehicle2.moveToStart();
        list.getItems().clear();
    }

    @FXML
    void onClickStopButton(ActionEvent event) {
        timeline.stop();
    }
    
    private void addToList(String name) {
        if(list.getItems().contains(name)) {
            return;
        }
        list.getItems().add(name);
    }

}
