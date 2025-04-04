package com.project.GameWorld;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Item {
    public static ArrayList<Item> items = new ArrayList<>();
    private String name;
    private File detailFile;
    private String detail;
    private ItemType type;
    private File imageFile;

    // Constructor
    public Item(String name, File detailFile, File imageFile, ItemType type) {
        this.name = name;
        this.detailFile = detailFile;
        this.imageFile = imageFile;  
        this.type = type;
        this.detail = loadDetailFromFile(detailFile);
    
    }

    
    public File getImageFile() {
        return imageFile;  
    }

    
    private String loadDetailFromFile(File file) {
        StringBuilder details = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                details.append(scanner.nextLine()).append("\n"); 
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return "ไม่สามารถโหลดรายละเอียดไอเทมได้"; 
        }
        return details.toString().trim(); 
    }

    
    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public ItemType getType() {
        return type;
    }
}
