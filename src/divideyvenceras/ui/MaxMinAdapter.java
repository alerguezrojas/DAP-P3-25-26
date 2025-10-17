package divideyvenceras.ui;

import divideyvenceras.*;
import divideyvenceras.maxmin3.*;

public class MaxMinAdapter implements AlgorithmAdapter {
    private final MaxMinAlgorithm alg = new MaxMinAlgorithm();

    @Override
    public String name() { return "Max-Min (3-way)"; }

    @Override
    public Problem makeProblem(int[] data) {
        return new MaxMinProblem(data, 0, data.length);
    }

    @Override
    public Solution solve(Problem p) {
        return alg.solve(p);
    }

    @Override
    public String render(Solution s) {
        MaxMinSolution sol = (MaxMinSolution) s;
        return "Max = " + sol.getMax() + ", Min = " + sol.getMin();
    }

    @Override
    public DivConqTemplate getAlgorithm() {
        return alg;
    }
}
