package fr.univartois.butinfo.r304.bomberman.model.map.bricks;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class CrackedBrick implements IBrick {

    Sprite sprite;

    public CrackedBrick() {
        SpriteStore store = new SpriteStore();
        sprite = store.getSprite("cracked-bricks");
    }

    public Sprite getSprite() {
        return sprite;
    }

    public IBrick nextState() {
        return new EmptyBrick();
    }

}
