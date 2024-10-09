package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player extends AbstractMovable{

    /**
     * Stocker le score.
     */
    private IntegerProperty score = new SimpleIntegerProperty(0);

    /**
     * Point de vie.
     */
    private IntegerProperty life = new SimpleIntegerProperty(3);

    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    protected Player(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    public IntegerProperty getScore() {
        return score;
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public IntegerProperty getLife() {
        return life;
    }

    public IntegerProperty lifeProperty() {
        return life;
    }

    public void setLife(int life) {
        this.life.set(life);
    }


    /**
     * Ajouter 1 au score
     */
    public void addScore() {
        score.set(score.get() + 1);
    }

    /**
     * Retirer 1 point de vie
     */
    public void removeLife() {
        life.set(life.get() - 1);
    }

    @Override
    public void collidedWith(IMovable other) {
        removeLife();
    }

    @Override
    public void explode() {
        addScore();
    }

    @Override
    public void hitEnemy() {
        /**
         * Ne rien faire
         */
    }
}
