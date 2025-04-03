package com.project.GameEvent.Interface;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.project.GameWorld.Item;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PanicTalk implements UI {
    private List<Item> inventory;
    private Text nameLabel;
    private Text detailText;
    private ImageView imageView;
    private TextArea textArea;

    private SplitPane splitPane;
    private VBox detailPanel;
    private boolean isClick = false;

    public PanicTalk(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    @Override
    public void set() {
        // 🔹 สร้าง Panel ซ้าย (Inventory) โดยใช้ ListView
        ListView<Button> listView = new ListView<>();
        listView.setPrefWidth(FXGL.getAppWidth() * 0.25); // ใช้ 25% ของความกว้างหน้าจอ
        listView.setPrefHeight(FXGL.getAppHeight()); // ใช้ความสูงเต็มหน้าจอ

        // 🔹 สร้าง Panel ขวา (รายละเอียดไอเทม)
        detailPanel = new VBox(10);
        
        // สร้าง TextArea สำหรับแสดงเนื้อหาจากไฟล์ .txt
        textArea = new TextArea();
        textArea.setWrapText(true);  // ทำให้ข้อความแสดงในบรรทัดใหม่เมื่อเกินขนาด
        textArea.setEditable(false); // ไม่ให้ผู้ใช้แก้ไขข้อความ
        textArea.setPrefWidth(FXGL.getAppWidth() * 0.75);  // ความกว้าง 75% ของหน้าจอ
        textArea.setPrefHeight(FXGL.getAppHeight() * 0.50); // ความสูง 50% ของหน้าจอ

        // อ่านไฟล์ .txt และแสดงใน TextArea
       

        
        detailPanel.getChildren().add(textArea);

        nameLabel = new Text("");

        imageView = new ImageView();
    
        imageView.setFitWidth(FXGL.getAppWidth() * 0.75);
        imageView.setFitHeight(FXGL.getAppHeight() * 0.50);

        
        VBox imageAndDetails = new VBox(10);
        imageAndDetails.getChildren().addAll(nameLabel, imageView);  

        
        detailPanel.getChildren().add(imageAndDetails);

        
        for (Item item : inventory) {
            Button itemButton = new Button(item.getName());
        
            itemButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: transparent;");
            itemButton.setPrefWidth(FXGL.getAppWidth() * 0.20);

            
            itemButton.setOnAction(e -> {
                nameLabel.setText(item.getName());
                textArea.setText(item.getDetail());
                textArea.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");

            imageView.setImage(new Image(item.getImageFile().toURI().toString()));
            
        }); // เมื่อคลิกที่ปุ่ม
            listView.getItems().add(itemButton); // เพิ่มปุ่มใน ListView
        }

    // เพิ่มปุ่ม "ปิด" ลงใน detailPanel


        // 🔹 สร้าง SplitPane สำหรับแบ่งซ้ายและขวา
        splitPane = new SplitPane();
        splitPane.getItems().addAll(listView, detailPanel); // เพิ่ม ListView และ VBox ที่แสดงรายละเอียด

        // 🔹 ตั้งค่า SplitPane ให้เหมาะสมกับขนาดหน้าจอ
        splitPane.setDividerPositions(0.25); // กำหนดตำแหน่งของ divider (แบ่ง 25% ซ้าย, 75% ขวา)

        // 🔹 ทำให้ SplitPane ครอบคลุมเต็มหน้าจอ
        splitPane.setPrefWidth(FXGL.getAppWidth());
        splitPane.setPrefHeight(FXGL.getAppHeight());

        // 🔹 เพิ่ม SplitPane ลงในหน้าจอ
    
        

    
  
        

    }

    @Override 
    public void show() {
          // 🔹 เพิ่ม SplitPane ลงในหน้าจอ
          if(isClick == false){
            FXGL.getGameScene().getContentRoot().getChildren().add(splitPane);
            isClick = true;
          }
          else{
            FXGL.getGameScene().getContentRoot().getChildren().remove(splitPane);
            isClick = false;
          }

          
            
          
    }

}
