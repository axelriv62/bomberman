package fr.univartois.butinfo.r304.bomberman.model.movables;

import java.util.ArrayList;
import java.util.List;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    public Player(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
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

    /////////////////BOMBES////////////////////
        // Liste des bombes possédées par le joueur
    private List<Bomb> bombs = FXCollections.observableArrayList();

    // Méthode pour accéder à la liste des bombes
    public List<Bomb> getBombs() {
        return bombs;
    }

    // Méthode pour ajouter une bombe
    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    // Méthode pour retirer une bombe
    public void removeBomb(Bomb bomb) {
        bombs.remove(bomb);
    }
    // Vérifier si le joueur peut poser une bombe (si la liste n'est pas vide)
    public boolean canDropBomb() {
        return !bombs.isEmpty();
    }

    // Méthode pour déposer une bombe
    public Bomb dropBomb() {
        if (canDropBomb()) {
            Bomb bomb = bombs.remove(0); // Retirer la première bombe de la liste
            bomb.replaceCell(getX(), getY()); // Placer la bombe à la position du joueur
            return bomb;
        }
        // Si pas de bombe disponible
        else{
            return null;
        }
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
