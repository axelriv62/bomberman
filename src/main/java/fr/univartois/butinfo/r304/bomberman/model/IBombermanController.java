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

package fr.univartois.butinfo.r304.bomberman.model;

import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.movables.Bomb;
import javafx.beans.binding.IntegerExpression;
import javafx.collections.ObservableList;

/**
 * L'interface {@link IBombermanController} définit le contrat qui doit être respecté par
 * n'importe quel contrôleur du jeu du Bomberman.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface IBombermanController {

    /**
     * Associe à ce contrôleur la partie du jeu Bomberman en cours.
     *
     * @param game La partie en cours.
     */
    void setGame(BombermanGame game);

    /**
     * Prépare l'affichage du jeu avant que celui-ci ne démarre.
     *
     * @param map La carte du jeu à afficher.
     */
    void prepare(GameMap map);

    /**
     * Lie le score du joueur à son affichage dans la vue.
     *
     * @param scoreProperty La propriété stockant le score du joueur.
     */
    void bindScore(IntegerExpression scoreProperty);

    /**
     * Lie le nombre de bombes du joueur à son affichage dans la vue.
     *
     * @param list La propriété stockant le nombre de bombes.
     */
    void bindBombs(ObservableList<Bomb> list);

    /**
     * Lie la vie du joueur à son affichage dans la vue.
     *
     * @param lifeProperty La propriété stockant la vie du joueur.
     */
    void bindLife(IntegerExpression lifeProperty);

    /**
     * Ajoute un objet pouvant se déplacer dans le jeu, afin de pouvoir l'afficher.
     *
     * @param movable L'objet à aficher.
     */
    void addMovable(IMovable movable);

    /**
     * Affiche un message lorsque la partie est terminée.
     *
     * @param endMessage Le message à afficher.
     */
    void gameOver(String endMessage);

    /**
     * Réinitialise l'affichage, afin de préparer une nouvelle partie.
     */
    void reset();

}
