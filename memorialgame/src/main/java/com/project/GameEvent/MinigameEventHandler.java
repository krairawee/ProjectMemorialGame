package com.project.GameEvent;



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
    
            // ข้อความ Title
            Text title = new Text("Find and arguement");
            title.setFill(Color.WHITE);
            title.setFont(Font.font(24));
    
            // ปุ่มตัวเลือก
            Button option1 = new Button("1. Fight the monster!");
            Button option2 = new Button("2. Try to negotiate.");
            Button option3 = new Button("3. Run away.");
    
            // การกระทำเมื่อกดปุ่ม
            
    
            menu.getChildren().addAll(title, option1, option2, option3);

            HBox hBox = new HBox(20);
            hBox.getChildren().addAll(menu);
            hBox.setAlignment(Pos.CENTER);
    
            StackPane menuPane = new StackPane(background, hBox);
            menuPane.setTranslateX(FXGL.getAppWidth() / 2.0 - 200);  // กึ่งกลางตามแกน X
            menuPane.setTranslateY(FXGL.getAppHeight() / 2.0 - 150); // กึ่งกลางตามแกน Y

            option1.setOnAction(e -> {
                FXGL.showMessage("Confirm Answer");
                FXGL.getGameScene().removeUINode(menuPane);  
            });
    
            option2.setOnAction(e -> {
                FXGL.showMessage("Confirm Answer");
                FXGL.getGameScene().removeUINode(menuPane);
                
            });

            option3.setOnAction(e -> {
                FXGL.showMessage("Confirm Answer");
                FXGL.getGameScene().removeUINode(menuPane);
            });
    
            FXGL.getGameScene().addUINode(menuPane);
        });
    }
    
}
