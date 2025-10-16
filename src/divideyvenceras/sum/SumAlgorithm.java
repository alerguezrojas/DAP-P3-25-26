package divideyvenceras.sum;

import divideyvenceras.*;

public class SumAlgorithm extends DivConqTemplate {

    @Override
    protected boolean isSimple(Problem p) {
        SumProblem sp = (SumProblem) p;
        return (sp.end - sp.start) == 1;
    }

    @Override
    protected Solution simplySolve(Problem p) {
        SumProblem sp = (SumProblem) p;
        return new SumSolution(sp.data[sp.start]);
    }

    @Override
    protected Problem[] decompose(Problem p) {
        SumProblem sp = (SumProblem) p;
        int mid = (sp.start + sp.end) / 2;
        return new Problem[] {
                new SumProblem(sp.data, sp.start, mid),
                new SumProblem(sp.data, mid, sp.end)
        };
    }

    @Override
    protected Solution combine(Problem p, Solution[] ss) {
        int total = 0;
        for (Solution s : ss) {
            total += ((SumSolution) s).getValue();
        }
        return new SumSolution(total);
    }
}
