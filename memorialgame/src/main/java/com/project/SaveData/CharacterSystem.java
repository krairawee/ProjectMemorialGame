package com.project.SaveData;

import java.util.ArrayList;
import java.util.Arrays;


public class CharacterSystem {
    public static CharacterData shuiji = new CharacterData("shuiji", 0, 0, -1);
    public static CharacterData maki = new CharacterData("maki", 0, 0, -1);
    public static CharacterData kaito = new CharacterData("kaito", 0, 0, -1);
    public static CharacterData himiko = new CharacterData("himiko", 0, 0, -1);
    public static CharacterData kokichi = new CharacterData("kokichi", 0, 0, -1);
    public static CharacterData tsumugi = new CharacterData("tsumugi", 0, 0, -1);
    public static CharacterData gonta = new CharacterData("gonta", 0, 0, -1);
    public static ArrayList<CharacterData> listCharacter = new ArrayList<>(Arrays.asList(shuiji, maki, kaito, himiko, kokichi, tsumugi, gonta));

    public static void setData(String name,CharacterData charData){
        for (int i = 0; i < listCharacter.size(); i++) {
            if (listCharacter.get(i).getName().equals(name)) {  
                listCharacter.set(i, charData);  
                return;  
            }
        }
    }
    public static CharacterData getData(String name){
        for(CharacterData data:listCharacter){
            if(data.getName() == name){
                if(data.getPhaseCutsence()!=-1){
                    return data;                                
                }
                else{
                    return null;
                }
            }
        }
        return null;
    }
    
}
