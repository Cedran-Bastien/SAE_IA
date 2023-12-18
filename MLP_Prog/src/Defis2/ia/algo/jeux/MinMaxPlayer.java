package Defis2.ia.algo.jeux;

import Defis2.ia.framework.common.Action;
import Defis2.ia.framework.common.ActionValuePair;
import Defis2.ia.framework.jeux.Game;
import Defis2.ia.framework.jeux.GameState;
import Defis2.ia.framework.jeux.Player;

public class MinMaxPlayer extends Player {
    /**
     * Represente un joueur
     *
     * @param g          l'instance du jeux
     * @param player_one si joueur 1
     */
    public MinMaxPlayer(Game g, boolean player_one) {
        super(g, player_one);
    }

    @Override
    public Action getMove(GameState state) {
        ActionValuePair valueAction;
        if (player == PLAYER1) {
            valueAction = MaxValue(state);
        } else {
            valueAction = MinValue(state);
        }
        return valueAction.getAction();
    }

    private ActionValuePair MaxValue(GameState state) {
        if (game.endOfGame(state)) {
            return new ActionValuePair(null, state.getGameValue());
        }
        Action maxAction = null;
        double maxValue = Double.NEGATIVE_INFINITY;
        for (Action action : game.getActions(state)) {
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair valueAction = MinValue(nextState);
            if (valueAction.getValue() > maxValue) {
                maxValue = valueAction.getValue();
                maxAction = action;
            }
        }
        return new ActionValuePair(maxAction, maxValue);
    }

    private ActionValuePair MinValue(GameState state) {
        if (game.endOfGame(state)) {
            return new ActionValuePair(null, state.getGameValue());
        }
        double minValue = Double.POSITIVE_INFINITY;
        Action minAction = null;
        for (Action action : game.getActions(state)) {
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair valueAction = MaxValue(nextState);
            if (valueAction.getValue() < minValue) {
                minValue = valueAction.getValue();
                minAction = action;
            }
        }
        return new ActionValuePair(minAction,minValue);
    }
}
