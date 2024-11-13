package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Bomb extends AbstractMovable {

    /**
     * Moment de la création de la
     * bombe.
     */
    private long creationTime;

    /**
     * Durée d'affichage de la bombe (en millisecondes).
     */
    private static final long DISPLAY_DURATION = 2000; // 2 seconds

    private BooleanProperty consumed = new SimpleBooleanProperty(false);
    private DoubleProperty xProperty = new SimpleDoubleProperty();
    private DoubleProperty yProperty = new SimpleDoubleProperty();
    private SimpleObjectProperty<Sprite> spriteProperty = new SimpleObjectProperty<>();

    /**
     * Crée une nouvelle instance de Bombe.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public Bomb(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        this.creationTime = stockPosition();
        this.xProperty.set(xPosition);
        this.yProperty.set(yPosition);
        this.spriteProperty.set(sprite);
    }

    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof Explosion) {
            other.explode();
        }
    }

    public long stockPosition() {
        return System.currentTimeMillis();
    }

    @Override
    public void explode() {
        Explosion explosion = new Explosion(game, getX(), getY(), game.getSpriteStore().getSprite("explosion"));
        game.addMovable(explosion);
        game.removeMovable(this);
        explosion.replaceAdjacentCells(getX(), getY());
    }


    @Override
    public void hitEnemy() {

    }

    @Override
    public boolean move(long timeDelta) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - creationTime > DISPLAY_DURATION) {
            explode();
            return false;
        }
        return true;
    }

    public void reset() {
        this.creationTime = System.currentTimeMillis();
        this.consumed.set(false);
    }

}