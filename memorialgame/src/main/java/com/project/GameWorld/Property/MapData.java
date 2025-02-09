package com.project.GameWorld.Property;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.tiled.TMXLevelLoader;

public class MapData {
    public static Level mapPreTrial = FXGL.getAssetLoader().loadLevel("PreTrialMap.tmx", new TMXLevelLoader());
    public static Level mapTrial;
    
}
