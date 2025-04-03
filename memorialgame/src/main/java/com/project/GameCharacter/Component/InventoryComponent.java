package com.project.GameCharacter.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.inventory.Inventory;
import com.project.GameWorld.Item;
import com.project.GameWorld.ItemType;

public class InventoryComponent extends Component {
    private ArrayList<Item> inventory = new ArrayList<>();
    public InventoryComponent(){
      
        inventory.add(new Item("MiuCorpse", new File("ItemData/MiuCorpse.txt"), new File("ItemData/MiuCorpseImage.png"), ItemType.MIUCORPSE));
        
    }
    public ArrayList<Item> getInventory(){
        return this.inventory;
    }
}
