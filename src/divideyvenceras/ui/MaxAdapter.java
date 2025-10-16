package divideyvenceras.ui;

import divideyvenceras.*;
import divideyvenceras.max.*;

public class MaxAdapter implements AlgorithmAdapter {
    private final MaxAlgorithm alg = new MaxAlgorithm();

    @Override public String name() { return "Array Max"; }

    @Override public Problem makeProblem(int[] data) {
        return new MaxProblem(data, 0, data.length);
    }

    @Override public Solution solve(Problem p) {
        return alg.solve(p);
    }

    @Override public String render(Solution s) {
        return "Max value = " + ((MaxSolution) s).getMax();
    }

    @Override
    public DivConqTemplate getAlgorithm() {
        return alg;
    }
}
