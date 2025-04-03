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
        inventory.add(new Item("Killing_Game_Simulator", new File("ItemData/Killing_Game_Simulator.txt"), new File("ItemData/Killing_Game_SimulatorImage.png"), ItemType.KILLING_GAME_SIMULATOR));
        inventory.add(new Item("Login_Logout_Record", new File("ItemData/Login_Logout_Record.txt"), new File("ItemData/Login_Logout_RecordImage.png"), ItemType.LOGIN_LOGOUT_RECORD));
        inventory.add(new Item("Map_of_the_Mansion", new File("ItemData/Map_of_the_Mansion.txt"), new File("ItemData/Map_of_the_MansionImage.png"), ItemType.MAP_OF_THE_MANSION));
        inventory.add(new Item("Map_of_the_World", new File("ItemData/Map_of_the_World.txt"), new File("ItemData/Map_of_the_WorldImage.png"), ItemType.MAP_OF_THE_WORLD));
        inventory.add(new Item("Toilet_Paper", new File("ItemData/Toilet_Paper.txt"), new File("ItemData/Toilet_PaperImage.png"), ItemType.TOILET_PAPER));
        inventory.add(new Item("Signboard_Used_as_a_Bridge", new File("ItemData/Signboard_Used_as_a_Bridge.txt"), new File("ItemData/Signboard_Used_as_a_BridgeImage.png"), ItemType.SIGNBOARD_USED_AS_A_BRIDGE));
        inventory.add(new Item("Miu_s_Avatar", new File("ItemData/Miu_s_Avatar.txt"), new File("ItemData/Miu_s_AvatarImage.png"), ItemType.MIU_S_AVATAR));
        inventory.add(new Item("Hammer", new File("ItemData/Hammer.txt"), new File("ItemData/HammerImage.png"), ItemType.HAMMER));
        inventory.add(new Item("Cell_Phone", new File("ItemData/Cell_Phone.txt"), new File("ItemData/Cell_PhoneImage.png"), ItemType.CELL_PHONE));
        inventory.add(new Item("Lattice_Near_the_Chapel", new File("ItemData/Lattice_Near_the_Chapel.txt"), new File("ItemData/Lattice_Near_the_ChapelImage.png"), ItemType.LATTICE_NEAR_THE_CHAPEL));
        inventory.add(new Item("Miu_and_Kokichi's_Meeting", new File("ItemData/Miu_and_Kokichi's_Meeting.txt"), new File("ItemData/Miu_and_Kokichi's_MeetingImage.png"), ItemType.MIU_AND_S_MEETING));
        inventory.add(new Item("Kokichi_In_the_Salon", new File("ItemData/Kokichi_In_the_Salon.txt"), new File("ItemData/Kokichi_In_the_SalonImage.png"), ItemType.KOKICHI_IN_THE_SALON));
        inventory.add(new Item("Wall_Added_by_Miu", new File("ItemData/Wall_Added_by_Miu.txt"), new File("ItemData/Wall_Added_by_MiuImage.png"), ItemType.WALL_ADDED_BY_MIU));
        inventory.add(new Item("Setting_on_Kokichi_s_Avatar", new File("ItemData/Setting_on_Kokichi_s_Avatar.txt"), new File("ItemData/Setting_on_Kokichi_s_AvatarImage.png"), ItemType.SETTING_ON_KOKICHI_S_AVATAR));
        inventory.add(new Item("Avatar_Error", new File("ItemData/Avatar_Error.txt"), new File("ItemData/Avatar_ErrorImage.png"), ItemType.AVATAR_ERROR));
        inventory.add(new Item("Device_Instructions", new File("ItemData/Device_Instructions.txt"), new File("ItemData/Device_InstructionsImage.png"), ItemType.DEVICE_INSTRUCTIONS));
        
        
    }
    public ArrayList<Item> getInventory(){
        return this.inventory;
    }
}
