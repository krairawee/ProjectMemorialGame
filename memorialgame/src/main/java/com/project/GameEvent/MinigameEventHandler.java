package com.project.GameEvent;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.event.EventBus;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MinigameEventHandler {
    public static EventBus eventBus;
    private static File inputFile = new File("MinigameData.txt");
    
    public static void setHandler(){
        eventBus = new EventBus();
        eventBus.addEventHandler(MinigameEvent.CHOICE, event->{
            // สร้าง Rectangle สี่เหลี่ยมเพื่อเป็นฉากหลัง
            Rectangle background = new Rectangle(400, 300, Color.rgb(0, 0, 0, 0.8));
            background.setArcWidth(20);  // มุมโค้งมน
            background.setArcHeight(20);
    
            // สร้าง VBox สำหรับเก็บข้อความและปุ่ม
            VBox menu = new VBox(20);
            menu.setTranslateX(0);  // ไม่ต้องปรับตำแหน่ง เพราะจะจัดกึ่งกลางใน StackPane อัตโนมัติ
            menu.setTranslateY(0);
            menu.setAlignment(Pos.CENTER);
    
            // ข้อความ Title
            Text title = new Text("Find and arguement");
            title.setFill(Color.WHITE);
            title.setFont(Font.font(24));
    
            // ปุ่มตัวเลือก
            ArrayList<String> catalogue = getData(FXGL.geti("MinigamePhase"),4);
            Button option1 = new Button(catalogue.get(0));
            Button option2 = new Button(catalogue.get(1));
            Button option3 = new Button(catalogue.get(2));
            Button option4 = new Button(catalogue.get(3));
            // การกระทำเมื่อกดปุ่ม
            ArrayList<Button> optionList = new ArrayList<>(Arrays.asList(option1,option2,option3,option4));
            
    
            menu.getChildren().addAll(title, option1, option2, option3,option4);

            HBox hBox = new HBox(20);
            hBox.getChildren().addAll(menu);
            hBox.setAlignment(Pos.CENTER);
    
            StackPane menuPane = new StackPane(background, hBox);
            menuPane.setTranslateX(FXGL.getAppWidth() / 2.0 - 200);  // กึ่งกลางตามแกน X
            menuPane.setTranslateY(FXGL.getAppHeight() / 2.0 - 150); // กึ่งกลางตามแกน Y
            for(int i = 0;i<optionList.size();i++){
                if(i == Integer.parseInt(catalogue.get(4))){
                    optionList.get(i).setOnAction(e -> {
                        FXGL.showMessage("Correct Answer");
                        FXGL.getGameScene().removeUINode(menuPane);  
                    });
                }
                else{
                    optionList.get(i).setOnAction(e -> {
                        FXGL.showMessage("False Answer");
                    });
                }
            }
    
            FXGL.getGameScene().addUINode(menuPane);
        });
    }
    public static ArrayList<String> getData(int n,int choice){
        try(Scanner scanner = new Scanner(inputFile)){
            for(int i = 0;i<n-1;i++){
                for(int j = 0;j<choice+1;j++){
                    if(scanner.hasNextLine()){
                        scanner.nextLine();
                    }
                }
            }
            ArrayList<String> allChoice = new ArrayList<>();
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                if(data.startsWith("#")){
                    break;
                }else{
                    allChoice.add(data);
                }
            }
            return allChoice;
        }
        catch(IOException e){
                System.err.println(e.getMessage());
            }
            return null;
        }
    }

    

