package com.project.GameEvent.Interface;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

public class MainMenu extends FXGLMenu {

    public MainMenu(MenuType type) {
        super(type);

        // สร้างข้อความชื่อเกม
        Label gameTitle = new Label("Memorial Game");
        gameTitle.setFont(Font.font(64));
        gameTitle.setTextFill(Color.BLUE);
        gameTitle.setStyle("-fx-background-color: #ff6347;");
       

        // สร้างปุ่ม "Play"
        Button playButton = new Button("Play Game");
        playButton.setFont(Font.font(32));
        playButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        playButton.setOnAction(event -> {
            // เริ่มเกมเมื่อกดปุ่ม "Play"
            FXGL.getGameController().startNewGame();
        });

        // สร้างปุ่ม "Exit"
        Button exitButton = new Button("Exit Game");
        exitButton.setFont(Font.font(32));
        exitButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        exitButton.setOnAction(event -> {
            // ออกจากเกมเมื่อกดปุ่ม "Exit"
            FXGL.getGameController().exit();
        });

        // ใช้ VBox เพื่อจัดเรียงปุ่มในแนวตั้ง
        VBox menuLayout = new VBox(80);
        menuLayout.setAlignment(Pos.CENTER);  // จัดให้อยู่ตรงกลาง
        menuLayout.getChildren().addAll(gameTitle, playButton, exitButton);

        Texture backgroundImageView = FXGL.getAssetLoader().loadTexture("MainMenu_Assets/Detective_Cat.jpg");  // ใส่ชื่อไฟล์ PNG ของคุณในโฟลเดอร์ assets
        

        // ทำให้ภาพมีขนาดเต็มหน้าจอ
        backgroundImageView.setFitWidth(getAppWidth());
        backgroundImageView.setFitHeight(getAppHeight());
        backgroundImageView.setPreserveRatio(true);

        // ใช้ StackPane เพื่อจัดตำแหน่งให้ปุ่มและข้อความตรงกลาง
        StackPane root = new StackPane();
        root.getChildren().add(backgroundImageView);  // ใส่ภาพพื้นหลังไว้ใน StackPane
        root.getChildren().add(menuLayout);           // ใส่เมนูลงใน StackPane
        root.setPrefSize(getAppWidth(), getAppHeight());

        // เพิ่ม StackPane ลงใน content root ของเมนู
        getContentRoot().getChildren().add(root);
    }
}
