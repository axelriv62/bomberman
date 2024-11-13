package fr.univartois.butinfo.r304.bomberman.model.map.bricks;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class EmptyBrick implements IBrick {

    Sprite sprite;

    public EmptyBrick() {
        SpriteStore store = new SpriteStore();
        sprite = store.getSprite("empty-bricks");
    }

    public Sprite getSprite() {
        return sprite;
    }

    public IBrick nextState() {
        return null;
    }

}
