package com.project.GameEvent;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.event.EventBus;
import com.project.GameEvent.Interface.BulletMinigame;
import com.project.GameEvent.Interface.UI;
import com.project.GameEvent.Interface.PictureScene;



public class MinigameEventHandler {
    public static EventBus eventBus;
    private static File inputFile = new File("MinigameData.txt");
    public static boolean isEventPicture = false;
    
    public static void setHandler(){
        
        eventBus = new EventBus();
        //choice bullet
        eventBus.addEventHandler(MinigameEvent.CHOICE_BULLET, event->{
           UI minigame = new BulletMinigame(FXGL.geti("MinigamePhase"), 4);
           minigame.set();
           minigame.show();
        });
        eventBus.addEventHandler(MinigameEvent.CHOICE_NETURAL, event->{
            UI minigame = new BulletMinigame(FXGL.geti("MinigamePhase"), 4);
            minigame.set();
            minigame.show();
         });
        eventBus.addEventHandler(MinigameEvent.SHOW_PICTURE, event->{
            UI minigame = new PictureScene("UI/MiuPicture.png","Cause of Death");
            minigame.set();
            minigame.show();
   
         });
         eventBus.addEventHandler(MinigameEvent.PANICTALK_ACTION, event->{
            UI minigame = new BulletMinigame(FXGL.geti("MinigamePhase"), 4);
            minigame.set();
            minigame.show();
         });

    }
    public static ArrayList<String> getData(int n, int choice) {
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

    

