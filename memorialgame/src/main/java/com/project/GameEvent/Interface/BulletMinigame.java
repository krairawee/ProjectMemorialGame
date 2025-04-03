package com.project.GameEvent.Interface;

import java.util.ArrayList;
import java.util.Arrays;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.ui.ProgressBar;
import com.project.App;
import com.project.GameEvent.StoryEventHandler;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BulletMinigame extends Choice implements MinigameUI{
    private ProgressBar healthBar;
    private double health = 200.00;
    private Texture background;
    private ArrayList<String> catalogue;
    private Node node;

    public BulletMinigame(int sequence,int numberChoice){
        super("Bullet",sequence,numberChoice);
        catalogue = getData(sequence,numberChoice);
    }
    
    public void set(){
            // set health bar
            background = FXGL.getAssetLoader().loadTexture("UI/Background.png",600,500);
            healthBar = new ProgressBar(true);
            healthBar.setWidth(200);
            healthBar.setHeight(10);
            healthBar.setStyle("-fx-accent: red;");
            healthBar.setEffect(new DropShadow());
            healthBar.setFill(Color.RED);
            healthBar.setMaxValue(health);
            healthBar.setCurrentValue(health);
    
            //set Main Interface
            VBox menu = new VBox(20);
            menu.setTranslateX(0);  
            menu.setTranslateY(0);
            menu.setAlignment(Pos.CENTER);
    
            Text title = new Text("Find and arguement");
            title.setFill(Color.BLACK);
            title.setFont(Font.font(24));
            title.setStyle(null);

            Text label1 = new Text(catalogue.get(0));
            label1.setFill(Color.BLACK);
            label1.setMouseTransparent(true);
            Text label2 = new Text(catalogue.get(1));
            label2.setFill(Color.BLACK);
            label2.setMouseTransparent(true);
            Text label3 = new Text(catalogue.get(2));
            label3.setFill(Color.BLACK);
            label3.setMouseTransparent(true);
            Text label4 = new Text(catalogue.get(3));
            label4.setFill(Color.BLACK);
            label4.setMouseTransparent(true);

            Button option1 = new Button();
            option1.setPrefSize(400, 50);
            option1.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
            option1.setGraphic(FXGL.getAssetLoader().loadTexture("UI/Label.png",400,50));
            Button option2 = new Button();
            option2.setPrefSize(400, 50);
            option2.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
            option2.setGraphic(FXGL.getAssetLoader().loadTexture("UI/Label.png",400,50));
            Button option3 = new Button();
            option3.setPrefSize(400, 50);
            option3.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
            option3.setGraphic(FXGL.getAssetLoader().loadTexture("UI/Label.png",400,50));
            Button option4 = new Button();
            option4.setPrefSize(400, 50);
            option4.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
            option4.setGraphic(FXGL.getAssetLoader().loadTexture("UI/Label.png",400,50));
            
            ArrayList<Button> optionList = new ArrayList<>(Arrays.asList(option1,option2,option3,option4));
            StackPane pane1 = new StackPane(option1,label1);
            StackPane pane2 = new StackPane(option2,label2);
            StackPane pane3 = new StackPane(option3,label3);
            StackPane pane4 = new StackPane(option4,label4);
            
    
            menu.getChildren().addAll(healthBar,title, pane1, pane2, pane3,pane4);

            HBox hBox = new HBox(20);
            hBox.getChildren().addAll(menu);
            hBox.setAlignment(Pos.CENTER);
    
            StackPane menuPane = new StackPane(background, hBox);
            menuPane.setTranslateX(FXGL.getAppWidth() / 2 - background.getWidth() / 2);
            menuPane.setTranslateY(FXGL.getAppHeight() / 2 - background.getHeight() / 2);
            
            //set handler Button
            for(int i = 0;i<optionList.size();i++){
                if(i == Integer.parseInt(catalogue.get(4))-1){
                    optionList.get(i).setOnAction(e -> {
                        FXGL.getGameScene().removeUINode(menuPane);  
                        FXGL.set("TrialDialoguePhase", FXGL.geti("TrialDialoguePhase")+1);
                        FXGL.set("MinigamePhase", FXGL.geti("MinigamePhase")+1);
                        FXGL.set("StoryPhase", FXGL.geti("StoryPhase")+1);
                        FXGL.set("StatusGame", false);
                        App.Save();
                        StoryEventHandler.phase.set(FXGL.geti("StoryPhase"));
                        
                    });
                }
                else{
                    optionList.get(i).setOnAction(e -> {
                        healthBar.setCurrentValue(healthBar.getCurrentValue()-50);
                        if(healthBar.getCurrentValue() == 0){
                            FXGL.showMessage("False Answer", () -> {
                            FXGL.getGameScene().removeUINode(menuPane);
                            StoryEventHandler.setSameNumber();
                            });
                        }
                    });
                }
            }
    
            this.node = menuPane;
    }
    @Override
    public void show(){
        FXGL.getGameScene().addUINode(node);
    }

    @Override
    public ArrayList<String> getData(){
        return getData(sequence,numberChoice);
    }
}
