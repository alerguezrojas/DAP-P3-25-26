package divideyvenceras.ui;

import divideyvenceras.*;
import divideyvenceras.sum.*;

public class SumAdapter implements AlgorithmAdapter {
    private final SumAlgorithm alg = new SumAlgorithm();

    @Override public String name() { return "Array Sum"; }

    @Override public Problem makeProblem(int[] data) {
        return new SumProblem(data, 0, data.length);
    }

    @Override public Solution solve(Problem p) {
        return alg.solve(p);
    }

    @Override public String render(Solution s) {
        return "Total sum = " + ((SumSolution) s).getValue();
    }
}
