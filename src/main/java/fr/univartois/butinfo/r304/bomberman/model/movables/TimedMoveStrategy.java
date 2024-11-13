package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;

public class TimedMoveStrategy implements IMoveStrategy {
  private static final int MOVE_DURATION = 1500; // 2 seconds in milliseconds
  private long lastMoveTime;
  private Direction currentDirection;

  public TimedMoveStrategy() {
    this.currentDirection = Direction.LEFT;
    this.lastMoveTime = System.currentTimeMillis();
  }

  @Override
  public void move(Enemy enemy) {
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastMoveTime >= MOVE_DURATION) {
      switchDirection();
      lastMoveTime = currentTime;
    }

    switch (currentDirection) {
      case LEFT:
        enemy.setHorizontalSpeed(-50);
        enemy.setVerticalSpeed(0);
        break;
      case RIGHT:
        enemy.setHorizontalSpeed(50);
        enemy.setVerticalSpeed(0);
        break;
      case UP:
        enemy.setHorizontalSpeed(0);
        enemy.setVerticalSpeed(-50);
        break;
      case DOWN:
        enemy.setHorizontalSpeed(0);
        enemy.setVerticalSpeed(50);
        break;
    }
  }

  private void switchDirection() {
    switch (currentDirection) {
      case LEFT:
        currentDirection = Direction.RIGHT;
        break;
      case RIGHT:
        currentDirection = Direction.UP;
        break;
      case UP:
        currentDirection = Direction.DOWN;
        break;
      case DOWN:
        currentDirection = Direction.LEFT;
        break;
    }
  }

  private enum Direction {
    LEFT, RIGHT, UP, DOWN
  }
}