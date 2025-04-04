package com.project.GameEvent.Interface;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import com.project.App;
import com.project.GameEvent.StoryEventHandler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Random;

public class PanicTalkAction {

    private int score = 0; // ตัวแปรเก็บคะแนน
    private Text scoreText; // UI แสดงคะแนน
    private GridPane grid; // ตารางปุ่ม
    private Random random = new Random(); // สำหรับสุ่มปุ่ม
    private Button activeTile; // ปุ่มที่ต้องกด
    private StackPane root; // UI หลัก

    public PanicTalkAction() {
        Texture background = FXGL.getAssetLoader().loadTexture("MainMenu_Assets/Detective_Cat.jpg");
        background.setFitWidth(FXGL.getAppWidth());
        background.setFitHeight(FXGL.getAppHeight());

       
        scoreText = new Text("Score: " + score);
        scoreText.setFont(Font.font(32));
        scoreText.setFill(Color.BLACK);

       
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

      
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                Button tile = new Button();
                tile.setPrefSize(120, 150);
                tile.setStyle("-fx-background-color: gray; -fx-border-color: white; -fx-border-width: 2;");
                tile.setOnAction(event -> onTileClicked(tile));
                grid.add(tile, col, row);
            }
        }

     
        StackPane.setAlignment(scoreText, Pos.TOP_RIGHT);
        StackPane.setMargin(scoreText, new Insets(10, 20, 0, 0));

  
        root = new StackPane();
        root.setPrefSize(FXGL.getAppWidth(), FXGL.getAppHeight());
        root.getChildren().addAll(background, scoreText, grid);

        FXGL.getGameScene().getContentRoot().getChildren().add(root);

       
        startTileAnimation();
    }


    private void startTileAnimation() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> showNewTileToClick())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    
    private void showNewTileToClick() {
        for (Node node : grid.getChildren()) {
            if (node instanceof Button button) {
                button.setStyle("-fx-background-color: gray; -fx-border-color: white;");
            }
        }

        int randomCol = random.nextInt(4);
        int randomRow = random.nextInt(5);

        for (Node node : grid.getChildren()) {
            if (GridPane.getColumnIndex(node) == randomCol && GridPane.getRowIndex(node) == randomRow) {
                if (node instanceof Button button) {
                    activeTile = button;
                    activeTile.setStyle("-fx-background-color: green; -fx-border-color: white;");
                    break;
                }
            }
        }
    }

 
    private void onTileClicked(Button clickedTile) {
        if (clickedTile == activeTile) {
            score++;
            scoreText.setText("Score: " + score);
            clickedTile.setStyle("-fx-background-color: gray; -fx-border-color: white;");

          
            if (score >= 20) {
                removeUI(); 
                 
                FXGL.set("TrialDialoguePhase", FXGL.geti("TrialDialoguePhase")+1);
                FXGL.set("MinigamePhase", FXGL.geti("MinigamePhase")+1);
                FXGL.set("StoryPhase", FXGL.geti("StoryPhase")+1);
                FXGL.set("StatusGame", false);
                System.out.println(FXGL.geti("StoryPhase"));
                App.Save();
                StoryEventHandler.phase.set(FXGL.geti("StoryPhase"));
                FXGL.getGameController().exit();
                
            }
        }
    }
    private void removeUI() {
        FXGL.runOnce(() -> {
            FXGL.getGameScene().getContentRoot().getChildren().remove(root);
        }, Duration.seconds(0.5));
    }
}
