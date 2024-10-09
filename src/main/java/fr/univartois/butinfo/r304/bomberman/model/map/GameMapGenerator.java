package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

/**
 * La classe {@link GameMapGenerator} gère la génération de la carte du jeu
 */
public class GameMapGenerator {

    /**
     * Constructeur privé pour empêcher l'instanciation de la classe.
     * Lance une AssertionError si une tentative d'instanciation est faite.
     */
    private GameMapGenerator() {
        throw new AssertionError("No GameMapGenerator instances for you!");
    }

    /**
     * Génère une carte de jeu avec la largeur et la hauteur spécifiées.
     *
     * @param width La largeur de la carte en nombre de cellules.
     * @param height La hauteur de la carte en nombre de cellules.
     * @return La carte du jeu ayant été généré.
     */
    public static GameMap generateMap(int width, int height) {
        SpriteStore spriteStore = new SpriteStore();
        GameMap map = new GameMap(height, width);

        Sprite wallSprite = spriteStore.getSprite("wall");
        Sprite lawnSprite = spriteStore.getSprite("lawn");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    // Contours de murs
                    map.setAt(i, j, new Cell(new Wall(wallSprite)));
                } else if ((i % 3 == 0 || i % 3 == 2) && (j % 3 == 0 || j % 3 == 2)) {
                    // Blocs de murs de 2x2 cellules
                    map.setAt(i, j, new Cell(new Wall(wallSprite)));
                } else {
                    // Cellules de pelouse
                    map.setAt(i, j, new Cell(lawnSprite));
                }
            }
        }

        return map;
    }
    
}
