package com.neptune;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Neptune on 3/18/2017.
 */
public class AlphaBeta {

    private HashMap<Integer, State> mapAlphaBeta = new HashMap<>();
    private Evaluation evaluation = new Evaluation();

    public State exec(State currentState, int depth) {
        mapAlphaBeta.clear();
        int value = exec(currentState, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
        return mapAlphaBeta.get(value);
    }

    private int exec(State currentState, int alpha, int beta, int depth) {
        if (depth == 0) {
            int heuristic = evaluation.computeHeuristic(currentState);
            mapAlphaBeta.put(heuristic, currentState);
            return heuristic;
        }
        ArrayList<State> successors = currentState.getSuccessors();
        int bestValue;
        if (currentState.getCurrentPlayer() == Mark.MAX) {
            bestValue = alpha;
            for (State successor : successors) {
                int childValue = exec(successor, bestValue, beta, depth - 1);
                if (childValue > bestValue) {
                    bestValue = childValue;
                }
                //bestValue = Math.max(bestValue, exec(successor, bestValue, beta, depth - 1));
                if (bestValue >= beta) {
                    break;
                }
            }
        } else {
            bestValue = beta;
            for (State successor : successors) {
                int childValue = exec(successor, alpha, bestValue, depth - 1);
                if (childValue < bestValue) {
                    bestValue = childValue;
                }
                //bestValue = Math.min(bestValue, exec(successor, alpha, bestValue, depth - 1));
                if (bestValue <= alpha) {
                    break;
                }
            }
        }
        return bestValue;
    }
}
