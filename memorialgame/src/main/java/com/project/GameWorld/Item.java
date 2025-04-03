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
    private File imageFile; // ฟิลด์สำหรับเก็บไฟล์รูปภาพ

    // Constructor
    public Item(String name, File detailFile, File imageFile, ItemType type) {
        this.name = name;
        this.detailFile = detailFile;
        this.imageFile = imageFile;  // กำหนดรูปภาพให้กับ Item
        this.type = type;
        this.detail = loadDetailFromFile(detailFile);
    }

    // ✅ ฟังก์ชันที่ใช้ในการดึงไฟล์รูปภาพ
    public File getImageFile() {
        return imageFile;  // คืนค่าไฟล์รูปภาพ
    }

    // ✅ โหลดรายละเอียดจากไฟล์
    private String loadDetailFromFile(File file) {
        StringBuilder details = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                details.append(scanner.nextLine()).append("\n"); // อ่านทุกบรรทัด
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return "ไม่สามารถโหลดรายละเอียดไอเทมได้"; // ค่าเริ่มต้นถ้าอ่านไม่ได้
        }
        return details.toString().trim(); // ลบ \n ท้ายสุด
    }

    // ✅ Getter
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
