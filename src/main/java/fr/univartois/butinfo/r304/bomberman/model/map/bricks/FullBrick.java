package fr.univartois.butinfo.r304.bomberman.model.map.bricks;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class FullBrick implements IBrick {

    Sprite sprite;

    public FullBrick() {
        SpriteStore store = new SpriteStore();
        sprite = store.getSprite("bricks");
    }

    public Sprite getSprite() {
        return sprite;
    }

    public IBrick nextState() {
        return new CrackedBrick();
    }

}
