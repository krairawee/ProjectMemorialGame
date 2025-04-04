package com.project.GameEvent.Interface;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import com.project.App;
import com.project.GameEvent.StoryEventHandler;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PictureScene implements UI {
    private String name;
    private Texture view;
    public Node node;

    public PictureScene(String address, String name) {
        this.name = name;
        view = FXGL.getAssetLoader().loadTexture(address);
    }

    @Override
    public void set() {
        Text namePicture = new Text(name);
        namePicture.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: black;");
        namePicture.setTranslateY(-view.getFitHeight() / 2 - 20);

        view.setFitHeight(FXGL.getAppHeight() / 1.5);
        view.setFitWidth(FXGL.getAppWidth() / 1.2);

        Texture background = FXGL.getAssetLoader().loadTexture("UI/Background.png", FXGL.getAppHeight(), FXGL.getAppWidth());

        VBox content = new VBox(namePicture, view);
        content.setAlignment(Pos.CENTER);
        content.setSpacing(10);

        StackPane panel = new StackPane(background, content);
        panel.setTranslateX((FXGL.getAppWidth() - background.getWidth()) / 2);
        panel.setTranslateY((FXGL.getAppHeight() - background.getHeight()) / 2);
        panel.setAlignment(Pos.CENTER);

        panel.setOnMouseClicked(event -> {
            FXGL.removeUINode(node);
                     FXGL.set("TrialDialoguePhase", FXGL.geti("TrialDialoguePhase")+1);
                        FXGL.set("MinigamePhase", FXGL.geti("MinigamePhase")+1);
                        FXGL.set("StoryPhase", FXGL.geti("StoryPhase")+1);
                        FXGL.set("StatusGame", false);
                        System.out.println(FXGL.geti("StoryPhase"));
                        App.Save();
                        StoryEventHandler.phase.set(FXGL.geti("StoryPhase"));
        });

        this.node = panel;
    }

    @Override
    public void show() {
        FXGL.addUINode(node);
    }

    public Texture getData() {
        return this.view;
    }


  
}
