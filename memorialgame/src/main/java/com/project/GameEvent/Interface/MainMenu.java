package com.project.GameEvent.Interface;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

public class MainMenu extends FXGLMenu {

    public MainMenu(MenuType type) {
        super(type);

      
        Label gameTitle = new Label("Memorial Game");
        gameTitle.setFont(Font.font(64));
        gameTitle.setTextFill(Color.BLUE);
        gameTitle.setStyle("-fx-background-color: #ff6347;");
       

        Button playButton = new Button("Play Game");
        playButton.setFont(Font.font(32));
        playButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        playButton.setOnAction(event -> {
          
            FXGL.getGameController().startNewGame();
        });

      
        Button exitButton = new Button("Exit Game");
        exitButton.setFont(Font.font(32));
        exitButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        exitButton.setOnAction(event -> {
           
            FXGL.getGameController().exit();
        });

     
        VBox menuLayout = new VBox(80);
        menuLayout.setAlignment(Pos.CENTER);  
        menuLayout.getChildren().addAll(gameTitle, playButton, exitButton);

        Texture backgroundImageView = FXGL.getAssetLoader().loadTexture("MainMenu_Assets/Detective_Cat.jpg");  // ใส่ชื่อไฟล์ PNG ของคุณในโฟลเดอร์ assets
        

     
        backgroundImageView.setFitWidth(getAppWidth());
        backgroundImageView.setFitHeight(getAppHeight());
        backgroundImageView.setPreserveRatio(true);

        
        StackPane root = new StackPane();
        root.getChildren().add(backgroundImageView);  
        root.getChildren().add(menuLayout);           
        root.setPrefSize(getAppWidth(), getAppHeight());

     
        getContentRoot().getChildren().add(root);
    }
}
