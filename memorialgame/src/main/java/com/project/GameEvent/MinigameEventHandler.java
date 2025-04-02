package com.project.GameEvent;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.event.EventBus;
import com.almasb.fxgl.time.Timer;
import com.almasb.fxgl.ui.ProgressBar;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MinigameEventHandler {
    public static EventBus eventBus;
    private static File inputFile = new File("MinigameData.txt");
    
    public static void setHandler(){
        eventBus = new EventBus();
        //choice bullet
        eventBus.addEventHandler(MinigameEvent.CHOICE_BULLET, event->{
            double health = 200.00;
            var background = FXGL.getAssetLoader().loadTexture("UI/Background.png",600,500);
           

            ProgressBar healthBar = new ProgressBar(true);
            healthBar.setWidth(200);
            healthBar.setHeight(10);
            healthBar.setStyle("-fx-accent: red;");
            healthBar.setEffect(new DropShadow());
            healthBar.setFill(Color.RED);
            healthBar.setMaxValue(health);
            healthBar.setCurrentValue(health);
    
            
            VBox menu = new VBox(20);
            menu.setTranslateX(0);  
            menu.setTranslateY(0);
            menu.setAlignment(Pos.CENTER);
    
            
            Text title = new Text("Find and arguement");
            title.setFill(Color.WHITE);
            title.setFont(Font.font(24));
            title.setStyle(null);

            
    
            
            ArrayList<String> catalogue = getData(FXGL.geti("MinigamePhase"),4);
            Text label1 = new Text(catalogue.get(0));
            label1.setFill(Color.WHITE);
            label1.setMouseTransparent(true);
            Text label2 = new Text(catalogue.get(1));
            label2.setFill(Color.WHITE);
            label2.setMouseTransparent(true);
            Text label3 = new Text(catalogue.get(2));
            label3.setFill(Color.WHITE);
            label3.setMouseTransparent(true);
            Text label4 = new Text(catalogue.get(3));
            label4.setFill(Color.WHITE);
            label4.setMouseTransparent(true);
            Button option1 = new Button();
            option1.setPrefSize(400, 50);
            option1.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
            option1.setGraphic(FXGL.getAssetLoader().loadTexture("UI/Bullet.png",400,50));
            Button option2 = new Button();
            option2.setPrefSize(400, 50);
            option2.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
            option2.setGraphic(FXGL.getAssetLoader().loadTexture("UI/Bullet.png",400,50));
            Button option3 = new Button();
            option3.setPrefSize(400, 50);
            option3.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
            option3.setGraphic(FXGL.getAssetLoader().loadTexture("UI/Bullet.png",400,50));
            Button option4 = new Button();
            option4.setPrefSize(400, 50);
            option4.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
            option4.setGraphic(FXGL.getAssetLoader().loadTexture("UI/Bullet.png",400,50));
            
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
            
            for(int i = 0;i<optionList.size();i++){
                if(i == Integer.parseInt(catalogue.get(4))-1){
                    optionList.get(i).setOnAction(e -> {
                        FXGL.getGameScene().removeUINode(menuPane);  
                        FXGL.set("TrialDialoguePhase", FXGL.geti("TrialDialoguePhase")+1);
                        FXGL.set("MinigamePhase", FXGL.geti("MinigamePhase")+1);
                        StoryEventHandler.phase.set(StoryEventHandler.getPhase()+1);
                    });
                }
                else{
                    optionList.get(i).setOnAction(e -> {
                        FXGL.showMessage("False Answer");
                        healthBar.setCurrentValue(healthBar.getCurrentValue()-50);
                        if(healthBar.getCurrentValue() == 0){
                            FXGL.getGameScene().removeUINode(menuPane);
                        }
                    });
                }
            }
    
            FXGL.getGameScene().addUINode(menuPane);
        });

        //Choice netural
        eventBus.addEventHandler(MinigameEvent.CHOICE_NETURAL, event->{
            double health = 200.00;
            var background = FXGL.getAssetLoader().loadTexture("UI/Background.png",600,500);
           

            ProgressBar healthBar = new ProgressBar(true);
            healthBar.setWidth(200);
            healthBar.setHeight(10);
            healthBar.setStyle("-fx-accent: red;");
            healthBar.setEffect(new DropShadow());
            healthBar.setFill(Color.RED);
            healthBar.setMaxValue(health);
            healthBar.setCurrentValue(health);
    
            
            VBox menu = new VBox(20);
            menu.setTranslateX(0);  
            menu.setTranslateY(0);
            menu.setAlignment(Pos.CENTER);
    
            
            Text title = new Text("Find Best Answer");
            title.setFill(Color.WHITE);
            title.setFont(Font.font(24));
            title.setStyle(null);

            
    
            
            ArrayList<String> catalogue = getData(FXGL.geti("MinigamePhase"),4);
            Text label1 = new Text(catalogue.get(0));
            label1.setFill(Color.WHITE);
            label1.setMouseTransparent(true);
            Text label2 = new Text(catalogue.get(1));
            label2.setFill(Color.WHITE);
            label2.setMouseTransparent(true);
            Text label3 = new Text(catalogue.get(2));
            label3.setFill(Color.WHITE);
            label3.setMouseTransparent(true);
            Text label4 = new Text(catalogue.get(3));
            label4.setFill(Color.WHITE);
            label4.setMouseTransparent(true);
            Button option1 = new Button();
            option1.setPrefSize(400, 50);
            option1.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
            option1.setGraphic(FXGL.getAssetLoader().loadTexture("UI/Bullet.png",400,50));
            Button option2 = new Button();
            option2.setPrefSize(400, 50);
            option2.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
            option2.setGraphic(FXGL.getAssetLoader().loadTexture("UI/Bullet.png",400,50));
            Button option3 = new Button();
            option3.setPrefSize(400, 50);
            option3.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
            option3.setGraphic(FXGL.getAssetLoader().loadTexture("UI/Bullet.png",400,50));
            Button option4 = new Button();
            option4.setPrefSize(400, 50);
            option4.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
            option4.setGraphic(FXGL.getAssetLoader().loadTexture("UI/Bullet.png",400,50));
            
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
            
            for(int i = 0;i<optionList.size();i++){
                if(i == Integer.parseInt(catalogue.get(4))-1){
                    optionList.get(i).setOnAction(e -> {
                        FXGL.getGameScene().removeUINode(menuPane);  
                        FXGL.set("TrialDialoguePhase", FXGL.geti("TrialDialoguePhase")+1);
                        FXGL.set("MinigamePhase", FXGL.geti("MinigamePhase")+1);
                        StoryEventHandler.phase.set(StoryEventHandler.getPhase()+1);
                    });
                }
                else{
                    optionList.get(i).setOnAction(e -> {
                        FXGL.showMessage("False Answer");
                        healthBar.setCurrentValue(healthBar.getCurrentValue()-50);
                        if(healthBar.getCurrentValue() == 0){
                            FXGL.getGameScene().removeUINode(menuPane);
                        }
                    });
                }
            }
    
            FXGL.getGameScene().addUINode(menuPane);
        });
    }
       public static ArrayList<String> getData(int n, int choice) {
    try (Scanner scanner = new Scanner(inputFile)) {
        // ข้ามไปยังคำถามที่ n
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < 6; j++) {  // ข้ามไป 5 บรรทัดในแต่ละคำถาม
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        }

        ArrayList<String> allChoice = new ArrayList<>();
        
        // อ่าน 5 บรรทัดแรกของคำถาม
        for (int i = 0; i < 5; i++) {
            if (scanner.hasNextLine()) {
                allChoice.add(scanner.nextLine());
            }
        }

        // ตรวจสอบว่ามีข้อมูลเพิ่มเติมหลังจากนั้นหรือไม่
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if (data.startsWith("#")) {  // หยุดเมื่อเจอ # เพื่อแบ่งกลุ่มคำถาม
                break;
            } else {
                allChoice.add(data);
            }
        }
        return allChoice;
    } catch (IOException e) {
        System.err.println(e.getMessage());
    }
    return null;
}
    }

    

