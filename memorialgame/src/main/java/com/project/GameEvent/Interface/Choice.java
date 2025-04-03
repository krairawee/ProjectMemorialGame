package com.project.GameEvent.Interface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Choice {
    protected String name;
    protected int numberChoice;
    protected int sequence;
    private static final File inputFile = new File("MinigameData.txt");
    public Choice(String name,int sequence,int numberChoice){
        this.name = name;
        this.sequence = sequence;
        this.numberChoice = numberChoice;

    }

    
    public ArrayList<String> getData(int n, int choice) {
    try (Scanner scanner = new Scanner(inputFile)) {
        // ข้ามไปยังคำถามที่ n
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < choice+2; j++) {  // ข้ามไป 5 บรรทัดในแต่ละคำถาม
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        }

        ArrayList<String> allChoice = new ArrayList<>();
        
        // อ่าน 5 บรรทัดแรกของคำถาม
        for (int i = 0; i < choice+1; i++) {
            if (scanner.hasNextLine()) {
                allChoice.add(scanner.nextLine());
            }
        }

        // ตรวจสอบว่ามีข้อมูลเพิ่มเติมหลังจากนั้นหรือไม่
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if (data.startsWith("#")) {  // หยุดเมื่อเจอ # เพื่อแบ่งกลุ่มคำถาม
                break;
            } else {
                allChoice.add(data);
            }
        }
        return allChoice;
    } catch (IOException e) {
        System.err.println(e.getMessage());
    }
    return null;
}
}
