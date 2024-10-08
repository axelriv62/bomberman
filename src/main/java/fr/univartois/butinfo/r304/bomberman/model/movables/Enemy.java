package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import java.util.Random;

public class Enemy extends AbstractMovable{
  private Random random = new Random();

  /**
   * Crée une nouvelle instance de AbstractMovable.
   *
   * @param game      Le jeu dans lequel l'objet évolue.
   * @param xPosition La position en x initiale de l'objet.
   * @param yPosition La position en y initiale de l'objet.
   * @param sprite    L'instance de {@link Sprite} représentant l'objet.
   */
  protected Enemy(BombermanGame game, double xPosition,
      double yPosition, Sprite sprite) {
    super(game, xPosition, yPosition, sprite);
  }

  public int getX() {
    return (int) xPosition.get();
  }

  public int getY() {
    return (int) yPosition.get();
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

  }

  @Override
  public boolean move(long delta) {

    setHorizontalSpeed((random.nextDouble() * 2 - 1) * 100);
    setVerticalSpeed((random.nextDouble() * 2 - 1) * 100);

    return super.move(delta);
  }




}
