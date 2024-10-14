package fr.univartois.butinfo.r304.bomberman.model.movables;

import java.util.Random;

public class CurrentMoveStrategy implements IMoveStrategy {
  private Random random = new Random();

  @Override
  public void move(Enemy enemy) {
    enemy.setHorizontalSpeed((random.nextDouble() * 2 - 1) * 100);
    enemy.setVerticalSpeed((random.nextDouble() * 2 - 1) * 100);
  }
}