package model;

import java.util.ArrayList;

public class Engine {
    private ArrayList<State> solution;

    public Engine() {
	solution = new ArrayList<>();
    }

    public void backtrack(State state) {
	if (state.isFinal() && !solution.contains(state)) {
	    solution.add(state);
	    System.out.println(state + "\n");
	} else {
	    for (State nextState : state.nextStates()) {
		// solution.add(nextState);
		backtrack(nextState);
		// solution.remove(solution.indexOf(nextState));
	    }
	}
    }

    public void showSolution() {
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		State initialState = new State(i, j);
		// solution.add(initialState);
		backtrack(initialState);
		// /solution.remove(solution.indexOf(initialState));
	    }
	}
    }
}
