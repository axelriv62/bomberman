/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2022-2024 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.model.map.bricks.FullBrick;
import fr.univartois.butinfo.r304.bomberman.model.map.bricks.IBrick;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

/**
 * La classe {@link Wall} représente un mur de briques sur la carte du jeu.
 * Il s'agit d'un élément qui ne peut pas être traversé, mais qui peut être détruit par
 * une explosion.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class Wall {

    private IBrick brick;

    /**
     * Le sprite représentant ce mur sur la carte.
     */
    private Sprite sprite;

    boolean isDestructible;

    /**
     * Crée une nouvelle instance de Wall.
     *
     * @param sprite Le sprite représentant le mur sur la carte.
     */
    public Wall(Sprite sprite, boolean isDestructible) {
        this.sprite = sprite;
        this.isDestructible = isDestructible;
        this.brick = new FullBrick();
    }

    /**
     * Donne le sprite représentant ce mur sur la carte.
     *
     * @return Le sprite représentant ce mur sur la carte.
     */
    public Sprite getSprite() {
        return sprite;
    }

    public void handle() {
        brick = brick.nextState();
        if (brick == null) {
            SpriteStore store = new SpriteStore();
            sprite = store.getSprite("lawn");
        } else {
            sprite = brick.getSprite();
        }
    }

    public IBrick getBrick() {
        return brick;
    }

    public boolean isDestructible() {
        return isDestructible;
    }

}
