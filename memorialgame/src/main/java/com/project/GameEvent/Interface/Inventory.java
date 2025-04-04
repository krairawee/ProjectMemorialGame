package com.project.GameEvent.Interface;


import java.util.ArrayList;
import java.util.List;



import com.almasb.fxgl.dsl.FXGL;
import com.project.GameWorld.Item;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Inventory implements UI {
    private List<Item> inventory;
    private Text nameLabel;
    private Text detailText;
    private ImageView imageView;
    private TextArea textArea;

    private SplitPane splitPane;
    private VBox detailPanel;
    private boolean isClick = false;

    public Inventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    @Override
    public void set() {
     
        ListView<Button> listView = new ListView<>();
        listView.setPrefWidth(FXGL.getAppWidth() * 0.25); 
        listView.setPrefHeight(FXGL.getAppHeight()); 

       
        detailPanel = new VBox(10);
        
       
        textArea = new TextArea();
        textArea.setWrapText(true); 
        textArea.setEditable(false); 
        textArea.setPrefWidth(FXGL.getAppWidth() * 0.75);  
        textArea.setPrefHeight(FXGL.getAppHeight() * 0.50); 

       
       

        
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
            
        }); 
            listView.getItems().add(itemButton);
        }




        splitPane = new SplitPane();
        splitPane.getItems().addAll(listView, detailPanel); 

       
        splitPane.setDividerPositions(0.25); 

       
        splitPane.setPrefWidth(FXGL.getAppWidth());
        splitPane.setPrefHeight(FXGL.getAppHeight());

    
    }

    @Override 
    public void show() {
        
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
