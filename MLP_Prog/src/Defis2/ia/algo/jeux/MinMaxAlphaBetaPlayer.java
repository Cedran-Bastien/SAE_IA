package Defis2.ia.algo.jeux;

import Defis2.ia.framework.common.Action;
import Defis2.ia.framework.common.ActionValuePair;
import Defis2.ia.framework.jeux.Game;
import Defis2.ia.framework.jeux.GameState;
import Defis2.ia.framework.jeux.Player;

import java.util.Collections;
import java.util.List;

public class MinMaxAlphaBetaPlayer extends Player {
    /**
     * Represente un joueur
     *
     * @param g          l'instance du jeux
     * @param player_one si joueur 1
     */
    public MinMaxAlphaBetaPlayer(Game g, boolean player_one) {
        super(g, player_one);
    }

    @Override
    public Action getMove(GameState state) {
        long startTime = System.currentTimeMillis();
        ActionValuePair actionValuePair;
        if (player == PLAYER1) {
            actionValuePair = MaxValue(state, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 13);
        } else {
            actionValuePair = MinValue(state, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 13);
        }
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("La recherche a durée " + estimatedTime / 1000. + " sec.");
        return actionValuePair.getAction();
    }

    private ActionValuePair MaxValue(GameState state, double alpha, double beta, int depth) {
        //System.out.println("MaxValue, " + "Depth: " + depth + ", Alpha: " + alpha + ", Beta: " + beta);
        if (game.endOfGame(state)) {
            return new ActionValuePair(null, state.getGameValue());
        }
        if(depth == 0) {
            return CenterValue(state);
        }
        Action maxAction = null;
        double maxValue = Double.NEGATIVE_INFINITY;
        for (Action action : game.getActions(state)) {
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair valueAction = MinValue(nextState, alpha, beta, depth - 1);
            if (valueAction.getValue() > maxValue) {
                maxValue = valueAction.getValue();
                maxAction = action;
                alpha = Math.max(alpha, maxValue);
            }
            if (maxValue >= beta) {
                return new ActionValuePair(maxAction, maxValue);
            }
        }
        return new ActionValuePair(maxAction, maxValue);
    }

    private ActionValuePair MinValue(GameState state, double alpha, double beta, int depth) {
        //System.out.println("MinValue, " + "Depth: " + depth + ", Alpha: " + alpha + ", Beta: " + beta);
        if (game.endOfGame(state)) {
            return new ActionValuePair(null, state.getGameValue());
        }
        if(depth == 0) {
            return CenterValue(state);
        }
        double minValue = Double.POSITIVE_INFINITY;
        Action minAction = null;
        for (Action action : game.getActions(state)) {
            GameState nextState = (GameState) game.doAction(state, action);
            ActionValuePair valueAction = MaxValue(nextState, alpha, beta, depth - 1);
            if (valueAction.getValue() < minValue) {
                minValue = valueAction.getValue();
                minAction = action;
                beta = Math.min(beta, minValue);
            }
            if (minValue <= alpha) {
                return new ActionValuePair(minAction, minValue);
            }
        }
        return new ActionValuePair(minAction, minValue);
    }

    private ActionValuePair CenterValue(GameState state) {
        List<Action> actions = game.getActions(state);
        Collections.sort(actions, (o1, o2) -> {
            int a1 = Integer.parseInt(o1.getName());
            int a2 = Integer.parseInt(o2.getName());
            int center = 3;
            return Math.abs(a1 - center) - Math.abs(a2 - center);
        });
        //System.out.println(actions);
        Action nextAction = actions.get(0);
        GameState nextState = (GameState) game.doAction(state, nextAction);
        return new ActionValuePair(nextAction, nextState.getGameValue());
    }
}
