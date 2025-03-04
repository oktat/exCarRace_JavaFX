package com.example;

import java.util.ArrayList;

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
    private ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();

    private int TARGET = 800;

    @FXML
    private Pane panel;

    @FXML
    private ListView<String> list;

    @FXML
    void initialize() {
        vehicleList.add(new Vehicle("Piros", Color.RED, 150));
        vehicleList.add(new Vehicle("Kék", Color.BLUE, 190));
        vehicleList.add(new Vehicle("Zöld", Color.GREEN, 230));

        for(Vehicle vehicle : vehicleList) {
            panel.getChildren().add(vehicle);
        }

        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.03), e -> {

            boolean isStopped = true;
            for(Vehicle vehicle : vehicleList) {
                if ((vehicle.getX() + vehicle.getWidth()) > TARGET) {
                    addToList(vehicle.name);
                }
                if (vehicle.getX() < 810) {
                    vehicle.move();            
                }                
                if (vehicle.getX() < 810) {
                    isStopped = false;
                }    
            }
            if (isStopped) {
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
        for(Vehicle vehicle : vehicleList) {
            vehicle.moveToStart();
        }
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
