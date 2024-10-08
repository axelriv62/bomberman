# *Bomberman* en JavaFX

## Description

Ce projet fournit une implantation de base du jeu *Bomberman* en *JavaFX*.
Pour pouvoir développer votre propre implantation de ce projet, vous devez
en créer une **divergence** en cliquant sur le bouton `Fork` en haut à droite
de cette page.

Lorsque ce sera fait, vous pourrez inviter les membres de votre groupe en tant
que *Developer* pour vous permettre de travailler ensemble sur ce projet.

## Consignes

Vous pouvez retrouver ci-dessous les liens vers les sujets de TP vous guidant
dans le développement de votre projet.

- [Lancement du projet](https://gitlab.univ-artois.fr/enseignements-rwa/modules/but-2/r3-04/tp/-/tree/main/TP03)

## Fonctionnement des Commit, merge request et branches

Utilisation d'un vocabulaire simple selon l'action réalisée pour les commit.

Exemple: 
        
> Ajout de la méthode XXX
> Correction d'un bug dans la classe XXX
> Suppression de la méthode XXX


Les merge request restent aussi simple la branche de base est envoyée vers la branche DEV.

Exemple: 
> Explosion -> DEV

Le nom des branches correspond à la fonctionnalité réalisée.

## Diagramme de classes

```plantuml
hide empty members

class Bomberman {
    - {static} GAME_WIDTH: int
    - {static} GAME_HEIGHT: int
    - {static} NB_ENEMIES: int

    + start(stage: Stage): void
    + {static} main(args: String[]): void
}
Bomberman --> BombermanController : << charge >>
Bomberman --> BombermanGame : << crée >>

class BombermanGame {
    + {static} RANDOM: Random
    + {static} DEFAULT_SPEED: int
    + {static} DEFAULT_BOMBS: int
    - width: int
    - height: int
    - spriteStore: ISpriteStore
    - gameMap: GameMap
    - player: IMovable
    - nbEnemies: int
    - remainingEnemies: int
    - movableObjects: List<IMovable>
    - animation: BombermanAnimation
    - controller: IBombermanController

    + BombermanGame(gameWidth: int, gameHeight: int, spriteStore: ISpriteStore, nbEnemies: int)
    + setController(controller: IBombermanController): void
    + getWidth(): int
    + getHeight(): int
    + prepare(): void
    - createMap(): GameMap
    + start(): void
    - createMovables(): void
    - initStatistics(): void
    - spawnMovable(movable: IMovable): void
    + moveUp(): void
    + moveRight(): void
    + moveDown(): void
    + moveLeft(): void
    + stopMoving(): void
    + dropBomb(): void
    + dropBomb(bomb: IMovable): void
    - getCellOf(movable: IMovable): Cell
    + getCellAt(x: int, y: int): Cell
    + addMovable(object: IMovable): void
    + removeMovable(object: IMovable): void
    - clearAllMovables(): void
    + enemyIsDead(enemy: IMovable): void
    + playerIsDead(): void
    - gameOver(message: String): void
}
BombermanGame o-- "1" ISpriteStore
BombermanGame *-- "1" GameMap
BombermanGame *-- "*" IMovable
BombermanGame *-- "1" BombermanAnimation
BombermanGame o-- "1" IBombermanController

class BombermanAnimation {
    - movableObjects: List<IMovable>
    - previousTimestamp: long

    + BombermanAnimation(movableObjects: List<IMovable>)
    + start(): void
    + handle(now: long): void
    - moveObjects(delta: long): void
    - checkCollisions(): void
}
BombermanAnimation o-- "*" IMovable

interface IBombermanController{
    + {abstract} setGame(game: BombermanGame): void
    + {abstract} prepare(map: GameMap): void
    + {abstract} bindScore(scoreProperty: IntegerExpression): void
    + {abstract} bindBombs(bombsProperty: IntegerExpression): void
    + {abstract} bindLife(lifeProperty: IntegerExpression): void
    + {abstract} addMovable(movable: IMovable): void
    + {abstract} gameOver(endMessage: String): void
    + {abstract} reset(): void
}

class BombermanController implements IBombermanController {
    - game: BombermanGame
    - stage: Stage
    - backgroundPane: GridPane
    - movingPane: Pane
    - score: Label
    - bombs: Label
    - life: Label
    - message: Label
    - started: boolean

    + setStage(stage: Stage): void
    + setGame(game: BombermanGame): void
    + prepare(map: GameMap): void
    - createBackground(map: GameMap): void
    - addKeyListeners(): void
    + bindScore(scoreProperty: IntegerExpression): void
    + bindBombs(bombsProperty: IntegerExpression): void
    + bindLife(lifeProperty: IntegerExpression): void
    + addMovable(movable: IMovable): void
    + gameOver(endMessage: String): void
    + reset(): void
}
BombermanController o-- "1" BombermanGame

interface IMovable {
    + {abstract} getWidth(): int
    + {abstract} getHeight(): int
    + {abstract} setX(xPosition: int): void
    + {abstract} getX(): int
    + {abstract} getXProperty(): DoubleProperty
    + {abstract} setY(yPosition: int): void
    + {abstract} getY(): int
    + {abstract} getYProperty(): DoubleProperty
    + {abstract} consume(): void
    + {abstract} isConsumed(): boolean
    + {abstract} isConsumedProperty(): BooleanProperty
    + {abstract} setHorizontalSpeed(speed: double): void
    + {abstract} getHorizontalSpeed(): double
    + {abstract} setVerticalSpeed(speed: double): void
    + {abstract} getVerticalSpeed(): double
    + {abstract} setSprite(sprite: Sprite): void
    + {abstract} getSprite(): Sprite
    + {abstract} getSpriteProperty(): ObjectProperty<Sprite>
    + {abstract} move(timeDelta: long): boolean
    + {abstract} isCollidingWith(other: IMovable): boolean
    + {abstract} collidedWith(other: IMovable): void
    + {abstract} explode(): void
    + {abstract} hitEnemy(): void
    + {abstract} self(): IMovable
}

abstract class AbstractMovable implements IMovable {
    - {static} MARGIN: int
    # game: BombermanGame
    # xPosition: DoubleProperty
    # yPosition: DoubleProperty
    # consumed: BooleanProperty
    # horizontalSpeed: double
    # verticalSpeed: double
    # sprite: ObjectProperty<Sprite>

    # AbstractMovable(game: BombermanGame, xPosition: double, yPosition: double, sprite: Sprite)
    + getWidth(): int
    + getHeight(): int
    + setX(xPosition: int): void
    + getX(): int
    + getXProperty(): DoubleProperty
    + setY(yPosition: int): void
    + getY(): int
    + getYProperty(): DoubleProperty
    + consume(): void
    + isConsumed(): boolean
    + isConsumedProperty(): BooleanProperty
    + setHorizontalSpeed(speed: double): void
    + getHorizontalSpeed(): double
    + setVerticalSpeed(speed: double): void
    + getVerticalSpeed(): double
    + setSprite(sprite: Sprite): void
    + getSprite(): Sprite
    + getSpriteProperty(): ObjectProperty<Sprite>
    + move(timeDelta: long): boolean
    - isOnWall(x: int, y: int): boolean
    + isCollidingWith(other: IMovable): boolean
    + collidedWith(other: IMovable): void
    + explode(): void
    + hitEnemy(): void
    + self(): IMovable
    + hashCode(): int
    + equals(obj: Object): boolean
}
AbstractMovable *-- "1" BombermanGame
AbstractMovable o-- "1" Sprite

class GameMap {
    - height: int
    - width: int
    - cells: Cell[][]

    + GameMap(height: int, width: int)
    - init(): void
    + getHeight(): int
    + getWidth(): int
    + isOnMap(row: int, column: int): boolean
    + getAt(row: int, column: int): Cell
    + setAt(row: int, column: int, cell: Cell): void
    + getEmptyCells(): List<Cell>
}
GameMap *-- "*" Cell

class Cell {
    - row: int
    - column: int
    - spriteProperty: ObjectProperty<Sprite>
    - wallProperty: ObjectProperty<Wall>

    + Cell(row: int, column: int)
    + Cell(sprite: Sprite)
    # Cell(wall: Wall)
    + getRow(): int
    + getColumn(): int
    + getWidth(): int
    + getHeight(): int
    + isEmpty(): boolean
    + getSprite(): Sprite
    + getSpriteProperty(): ObjectProperty<Sprite>
    + getWall(): Wall
    + getWallProperty(): ObjectProperty<Wall>
    + replaceBy(cell: Cell): void
}
Cell o-- "1" Sprite
Cell *-- "0..1" Wall

class Wall {
    - sprite: Sprite

    + Wall(sprite: Sprite)
    + getSprite(): Sprite
}

interface ISpriteStore {
    + {abstract} getSprite(identifier: String): Sprite
    + getSpriteSize(): int
}
ISpriteStore --> Sprite : << crée >>

class SpriteStore implements ISpriteStore {
    - spriteCache: Map<String, Sprite>
    + getSprite(identifier: String): Sprite
    - loadImage(name: String): Image
}

class Sprite {
    - image: Image

    + Sprite(image: Image)
    + getWidth(): int
    + getHeight(): int
    + getImage(): Image
    + draw(graphics: GraphicsContext, x: int, y: int): void
}
```

## Tâches réalisées

### TP n°3

| Fonctionnalité                             | Terminée ? | Auteur(s)                                     |
| ------------------------------------------ | ---------- | --------------------------------------------- |
| Représentation des ennemis                 |            |                                               |
| Intégration des ennemis dans la partie     |            |                                               |
| Représentation du joueur                   |            |                                               |
| Intégration du joueur dans la partie       |            |                                               |
| Représentation des bombes et explosion     |            |                                               |
| Intégration des bombes dans la partie      |            |                                               |
| Création de la carte du jeu                |            |                                               |



### TP n°4

| Fonctionnalité                              | Patron de conception utilisé | Terminée ? | Auteur(s)       |
| ------------------------------------------- | ---------------------------- | ---------- | --------------- |
| Variantes de déplacement des ennemis        |                              |            |                 |
| Gestion des points de vie (ennemis, joueur) |                              |            |                 |
| Invulnérabilité du joueur                   |                              |            |                 |
| Solidité des murs                           |                              |            |                 |
| Variantes de génération pour la carte       |                              |            |                 |
| Différents types de bombes                  |                              |            |                 |




