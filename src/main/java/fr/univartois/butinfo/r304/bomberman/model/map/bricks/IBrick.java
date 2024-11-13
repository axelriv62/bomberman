package fr.univartois.butinfo.r304.bomberman.model.map.bricks;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public interface IBrick {

    IBrick nextState();

    Sprite getSprite();
}
