package divideyvenceras.max;

import divideyvenceras.DivConqTemplate;

public class MaxAlgorithm  extends DivConqTemplate {

    @Override
    protected boolean isSimple(Problem p) {
        MaxProblem problem = (MaxProblem) p;
        return (mp.end - mp.start) == 1;
    }

    @Override
    protected Solution simplySolve(Problem p) {
        MaxProblem mp = (MaxProblem) p;
        return new MaxSolution(mp.data[mp.start]);
    }

    @Override
    protected Problem[] decompose(Problem p) {
        MaxProblem mp = (MaxProblem) p;
        int mid = (mp.start + mp.end) / 2;
        Problem[] subProblems = new Problem[2];
        subProblems[0] = new MaxProblem(mp.data, mp.start, mid);
        subProblems[1] = new MaxProblem(mp.data, mid, mp.end);
        return subProblems;
    }

    @Override
    protected Solution combine(Problem p, Solution[] ss) {
        int max1 = ((MaxSolution) ss[0]).getMax();
        int max2 = ((MaxSolution) ss[1]).getMax();
        return new MaxSolution(Math.max(max1, max2));
    }
}
