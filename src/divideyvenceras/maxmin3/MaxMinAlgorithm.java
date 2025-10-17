package divideyvenceras.maxmin3;

import divideyvenceras.*;

public class MaxMinAlgorithm extends DivConqTemplate {

    @Override
    protected boolean isSimple(Problem p) {
        MaxMinProblem prob = (MaxMinProblem) p;
        return prob.size() <= 1;
    }

    @Override
    protected Solution simplySolve(Problem p) {
        MaxMinProblem prob = (MaxMinProblem) p;
        int value = prob.getData()[prob.getStart()];
        return new MaxMinSolution(value, value);
    }

    @Override
    protected Problem[] decompose(Problem p) {
        MaxMinProblem prob = (MaxMinProblem) p;
        int[] data = prob.getData();
        int start = prob.getStart();
        int end = prob.getEnd();
        int size = prob.size();

        // Dividimos el vector en tres partes lo más equilibradas posible
        int tercio = size / 3;
        int resto = size % 3;

        int mid1 = start + tercio;
        int mid2 = mid1 + tercio + (resto > 0 ? 1 : 0);

        return new Problem[] {
                new MaxMinProblem(data, start, mid1),
                new MaxMinProblem(data, mid1, mid2),
                new MaxMinProblem(data, mid2, end)
        };
    }

    @Override
    protected Solution combine(Problem p, Solution[] ss) {
        int globalMax = Integer.MIN_VALUE;
        int globalMin = Integer.MAX_VALUE;

        for (Solution s : ss) {
            MaxMinSolution sol = (MaxMinSolution) s;
            if (sol.getMax() > globalMax) globalMax = sol.getMax();
            if (sol.getMin() < globalMin) globalMin = sol.getMin();
        }

        return new MaxMinSolution(globalMax, globalMin);
    }
}
