package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import java.util.Random;

public class Enemy extends AbstractMovable{
  private Random random = new Random();
  private IMoveStrategy iMoveStrategy;
  /**
   * Crée une nouvelle instance de AbstractMovable.
   *
   * @param game      Le jeu dans lequel l'objet évolue.
   * @param xPosition La position en x initiale de l'objet.
   * @param yPosition La position en y initiale de l'objet.
   * @param sprite    L'instance de {@link Sprite} représentant l'objet.
   */
  public Enemy(BombermanGame game, double xPosition,
      double yPosition, Sprite sprite) {
    super(game, xPosition, yPosition, sprite);
  }

  @Override
  public int getX() {
    return (int) xPosition.get();
  }
  @Override
  public int getY() {
    return (int) yPosition.get();
  }

  public void setIMoveStrategy(IMoveStrategy iMoveStrategy) {
    this.iMoveStrategy = iMoveStrategy;
  }

  public IMoveStrategy getIMoveStrategy() {
    return iMoveStrategy;
  }

  @Override
  public void collidedWith(IMovable other) {
    if (other instanceof Bomb) {
      other.explode();
    }
    else if (other instanceof Enemy) {

    }
  }

  @Override
  public void explode() {
    game.enemyIsDead(this);
  }

  @Override
  public void hitEnemy() {
    //TODO : implement
  }

  @Override
  public boolean move(long delta) {
    if (iMoveStrategy != null) {
      iMoveStrategy.move(this);
      return super.move(delta);
    }
    return false;
  }




}
