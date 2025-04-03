package com.project.GameEvent.Interface;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;

public class ScenceMenuFactory extends SceneFactory {
        @Override
        public FXGLMenu newMainMenu() {
            return new MainMenu(MenuType.MAIN_MENU);
        }

        @Override
        public FXGLMenu newGameMenu() {
            return new MainMenu(MenuType.GAME_MENU);
        }
}
