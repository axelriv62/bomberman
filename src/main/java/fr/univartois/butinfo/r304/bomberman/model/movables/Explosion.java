package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * La classe Explosion représente une explosion dans le jeu Bomberman.
 * Elle est affichée pendant une durée limitée et peut interagir avec d'autres objets du jeu.
 */
public class Explosion extends AbstractMovable {

    /**
     * Moment de la création de l'explosion.
     */
    private long creationTime;

    /**
     * Durée d'affichage de l'explosion (en millisecondes).
     */
    private static final long DISPLAY_DURATION = 2000; // 2 seconds

    private BooleanProperty consumed = new SimpleBooleanProperty(false);
    private DoubleProperty xProperty = new SimpleDoubleProperty();
    private DoubleProperty yProperty = new SimpleDoubleProperty();
    private SimpleObjectProperty<Sprite> spriteProperty = new SimpleObjectProperty<>();

    /**
     * Crée une nouvelle instance de Explosion.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    protected Explosion(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        this.xProperty.set(xPosition);
        this.yProperty.set(yPosition);
        this.spriteProperty.set(sprite);
        this.creationTime = System.currentTimeMillis();
    }

    @Override
    public int getWidth() {
        return getSprite().getWidth();
    }

    @Override
    public int getHeight() {
        return getSprite().getHeight();
    }

    @Override
    public void setX(int xPosition) {
        this.xProperty.set(xPosition);
    }

    @Override
    public int getX() {
        return (int) this.xProperty.get();
    }

    @Override
    public DoubleProperty getXProperty() {
        return this.xProperty;
    }

    @Override
    public void setY(int yPosition) {
        this.yProperty.set(yPosition);
    }

    @Override
    public int getY() {
        return (int) this.yProperty.get();
    }

    @Override
    public DoubleProperty getYProperty() {
        return this.yProperty;
    }

    @Override
    public void consume() {
        this.consumed.set(true);
    }

    @Override
    public boolean isConsumed() {
        return this.consumed.get();
    }

    @Override
    public BooleanProperty isConsumedProperty() {
        return this.consumed;
    }

    @Override
    public void setHorizontalSpeed(double speed) {
        // Pas de mouvement horizontal pour une explosion
    }

    @Override
    public double getHorizontalSpeed() {
        return 0;
    }

    @Override
    public void setVerticalSpeed(double speed) {
        // Pas de mouvement vertical pour une explosion
    }

    @Override
    public double getVerticalSpeed() {
        return 0;
    }

    @Override
    public void setSprite(Sprite sprite) {
        this.spriteProperty.set(sprite);
    }

    @Override
    public Sprite getSprite() {
        return this.spriteProperty.get();
    }

    @Override
    public SimpleObjectProperty<Sprite> getSpriteProperty() {
        return this.spriteProperty;
    }

    @Override
    public boolean move(long timeDelta) {
        if (System.currentTimeMillis() - creationTime > DISPLAY_DURATION) {
            game.removeMovable(this);
            return false;
        }
        return true;
    }

    @Override
    public boolean isCollidingWith(IMovable other) {
        // Implémentez la logique de détection de collision
        return false;
    }

    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof Explosion || isEnemy(other)) {
            // Ne rien faire
        } else {
            //TODO OBJET QUELCONQUE
            other.explode();
        }
    }

    private boolean isEnemy(IMovable other) {
        return other instanceof Enemy;
    }

    @Override
    public void explode() {
        //TODO
        // Implémentez la logique d'explosion
        int x = getX();
        int y = getY();
        replaceAdjacentCells(x, y);
    }

    @Override
    public void hitEnemy() {
        //TODO
        // Implémentez la logique de collision avec un ennemi
    }

    void replaceAdjacentCells(int x, int y) {
        int i = 1;
        int j = 1;

        int[][] directions = {
                {0, 0}, {-i, 0}, {i, 0}, {0, -j}, {0, j}
        };

        for (int[] dir : directions) {
            replaceCell(x + dir[0], y + dir[1]);
        }
    }

    private void replaceCell(int x, int y) {
        Cell currentCell = game.getCellAt(x, y);
        if (currentCell != null && isWall(x, y) && currentCell.getWall().isDestructible()) {
            Wall wall = currentCell.getWall();
            wall.handle(); // passer à l'état suivant de la brique
            currentCell.replaceBy(new Cell(wall.getSprite()));
            if (wall.getBrick() == null) {
                currentCell.getWallProperty().set(null);
            } else {
                currentCell.getWallProperty().set(wall);
            }
        }
    }


    private boolean isWall(int x, int y) {
        Cell cell = game.getCellAt(x, y);
        return cell != null && cell.getWall() != null;
    }

}