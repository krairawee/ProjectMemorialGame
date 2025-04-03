package com.project.GameEvent.Interface;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.texture.Texture;
import com.project.App;
import com.project.GameEvent.StoryEventHandler;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PictureScene implements UI{
    private String name;
    private Texture view;
    public Node node;
    public PictureScene(String address,String name){
        this.name = name;
        view = FXGL.getAssetLoader().loadTexture("UI/MiuPicture.png");
    }

    @Override
    public void set() {
    Text namePicture = new Text(name);
    namePicture.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: black;"); // ตั้งค่าฟอนต์
    namePicture.setTranslateY(-view.getFitHeight() / 2 - 20); // ขยับขึ้นจากภาพ
    

    view.setFitHeight(FXGL.getAppHeight() / 1.5);
    view.setFitWidth(FXGL.getAppWidth() / 1.2);

    Texture background = FXGL.getAssetLoader().loadTexture("UI/Background.png", FXGL.getAppHeight(), FXGL.getAppWidth());
    Texture enter = FXGL.getAssetLoader().loadTexture("UI/ENTER.png", 100, 50);

    // ปุ่ม ENTER อยู่ล่างขวาของ Background
    enter.setTranslateX((background.getWidth() / 2) - enter.getWidth() - 20);
    enter.setTranslateY((background.getHeight() / 2) - enter.getHeight() - 30);

    // ใช้ VBox เพื่อวางชื่ออยู่เหนือรูปภาพ
    VBox content = new VBox(namePicture, view);
    content.setAlignment(Pos.CENTER); // จัดกลางแนวตั้ง
    content.setSpacing(10); // กำหนดระยะห่าง

    StackPane panel = new StackPane(background, content, enter);
    panel.setTranslateX((FXGL.getAppWidth() - background.getWidth()) / 2);
    panel.setTranslateY((FXGL.getAppHeight() - background.getHeight()) / 2);
    panel.setAlignment(Pos.CENTER);

    UserAction enterAction = new UserAction("EnterPressed") {
        @Override
        protected void onActionBegin() {
            FXGL.removeUINode(panel);
            FXGL.set("TrialDialoguePhase", FXGL.geti("TrialDialoguePhase")+1);
            FXGL.set("MinigamePhase", FXGL.geti("MinigamePhase")+1);
            FXGL.set("StoryPhase", FXGL.geti("StoryPhase")+1);
            FXGL.set("StatusGame", false);
            App.Save();
            StoryEventHandler.phase.set(FXGL.geti("StoryPhase"));
        }
    };
    FXGL.getInput().addAction(enterAction, KeyCode.ENTER);
    

    this.node = panel;
    }

    @Override
    public void show(){
        FXGL.addUINode(node);
    }

    public Texture getData(){
        return this.view;
    }
    
}
