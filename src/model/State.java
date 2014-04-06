/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Robert
 * 
 */
public class State {
    private int matrix[][];

    public State() {
	matrix = new int[8][8];
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		matrix[i][j] = 0;
	    }
	}
    }

    public State(State other) {
	this();
	for (int i = 0; i < 8; i++) {
	    matrix[i] = Arrays.copyOf(other.matrix[i], 8);
	}
    }

    public State(int line, int col) {
	this();
	matrix[line][col] = 1;
	occupyTerritory(line, col);
    }

    /**
     * @return the matrix
     */
    public int[][] getMatrix() {
	return matrix;
    }

    /**
     * @param matrix
     *            the matrix to set
     */
    public void setMatrix(int[][] matrix) {
	this.matrix = matrix;
    }

    public boolean isInside(int index) {
	return index < 8 && index >= 0;
    }

    private void fillFirstDiag(int line, int column) {
	int lineInd = 0, colInd = 0;

	if (line <= column) {
	    lineInd = line - column;
	} else {
	    colInd = column - line;
	}
	for (int i = lineInd, j = colInd; i < 8 && j < 8; i++, j++) {
	    if (isInside(i) && isInside(j) && matrix[i][j] == 0) {
		matrix[i][j] = 2;
	    }
	}
    }

    private void fillSecondDiag(int line, int column) {
	int lineInd = 0, colInd = 7;

	if (7 - line < column) {
	    lineInd = column - (7 - line);
	} else {
	    colInd = column + line;
	}
	for (int i = lineInd, j = colInd; i < 8 && j > -1; i++, j--) {
	    if (isInside(i) && isInside(j) && matrix[i][j] == 0) {
		matrix[i][j] = 2;
	    }
	}
    }

    private void occupyTerritory(int line, int column) {
	for (int i = 0; i < 8; i++) {
	    if (matrix[i][column] == 0) {
		matrix[i][column] = 2;
	    }
	}
	for (int i = 0; i < 8; i++) {
	    if (matrix[line][i] == 0) {
		matrix[line][i] = 2;
	    }
	}

	fillFirstDiag(line, column);
	fillSecondDiag(line, column);
    }

    public void addQueen(int line, int column) {
	if (matrix[line][column] == 0) {
	    matrix[line][column] = 1;
	    occupyTerritory(line, column);
	}
    }

    public ArrayList<State> nextStates() {
	ArrayList<State> nextStates = new ArrayList<>();
	boolean ret = false;
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		if (matrix[i][j] == 0) {
		    State newState = new State(this);
		    newState.addQueen(i, j);
		    nextStates.add(newState);
		    ret = true;
		}
	    }
	    if (ret) {
		return nextStates;
	    }
	}
	return nextStates;
    }

    public boolean isFinal() {
	int count = 0;
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		if (matrix[i][j] == 1) {
		    count++;
		}
	    }
	}

	return count == 8;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	String matrixView = "";
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		if (matrix[i][j] == 1) {
		    matrixView += " Q ";
		} else {
		    if ((i + j) % 2 == 0) {
			matrixView += "[ ]";
		    } else {
			matrixView += "[*]";
		    }
		}
	    }
	    matrixView += "\n";
	}
	return matrixView;
    }

    public void print() {
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		System.out.print(matrix[i][j] + " ");
	    }
	    System.out.println();
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Arrays.hashCode(matrix);
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj == null) return false;
	if (getClass() != obj.getClass()) return false;
	State other = (State) obj;
	if (!Arrays.deepEquals(matrix, other.matrix)) return false;
	return true;
    }

}
