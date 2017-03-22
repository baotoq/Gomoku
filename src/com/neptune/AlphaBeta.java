package com.neptune;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Neptune on 3/18/2017.
 */
public class AlphaBeta {
    private Evaluation evaluation;
    private HashMap<Integer, Move> trackingMove;

    public AlphaBeta() {
        evaluation = new Evaluation();
        trackingMove = new HashMap<>();
    }

    public Move exec(State currentState, int depth) {
        int value = exec(currentState, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
        return trackingMove.get(Rule.MAX_DEPTH);
    }

    private int exec(State currentState, int alpha, int beta, int depth) {
        if (depth == 0) {
            return evaluation.computeHeuristic(currentState);
        }
        //ArrayList<State> successors = currentState.getSuccessors();
        HashMap<Move, Integer> mapMoveSuccessors = currentState.getMoveSuccessors();
        int bestValue;
        if (currentState.getCurrentPlayer() == Mark.MAX) {
            bestValue = alpha;
            for (Map.Entry<Move, Integer> kv : mapMoveSuccessors.entrySet()) {
                Move move = kv.getKey();
                currentState.performMove(move.row, move.col);
                int childValue = exec(currentState, bestValue, beta, depth - 1);
                if (childValue > bestValue) {
                    bestValue = childValue;
                    trackingMove.put(depth, move);
                }
                currentState.undoLastMove();
                if (bestValue >= beta) {
                    break;
                }
            }
        } else {
            bestValue = beta;
            for (Map.Entry<Move, Integer> kv : mapMoveSuccessors.entrySet()) {
                Move move = kv.getKey();
                currentState.performMove(move.row, move.col);
                int childValue = exec(currentState, alpha, bestValue, depth - 1);
                if (childValue < bestValue) {
                    bestValue = childValue;
                    trackingMove.put(depth, move);
                }
                currentState.undoLastMove();
                if (bestValue <= alpha) {
                    break;
                }
            }
        }
        return bestValue;
    }
}
