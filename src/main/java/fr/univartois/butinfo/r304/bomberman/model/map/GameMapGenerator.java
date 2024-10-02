package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;
import javafx.scene.image.Image;

public class GameMapGenerator {

    private GameMapGenerator() {
        throw new AssertionError("No GameMapGenerator instances for you!");
    }
    
    public static GameMap generateMap(int width, int height) {
        GameMap map = new GameMap(height, width);
        SpriteStore spriteStore = new SpriteStore();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Sprite wallSprite = spriteStore.getSprite("lawn");
                map.setAt(i, j, new Cell(wallSprite));
            }
        }

        return map;
    }
    
}
