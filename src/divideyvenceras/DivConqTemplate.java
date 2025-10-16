package divideyvenceras;

public abstract class DivConqTemplate {

    public final Solution solve(Problem p) {
        Problem[] subProblems;

        if (isSimple(p)) {
            return simplySolve(p);
        } else {
            subProblems = decompose(p);
        }

        Solution[] subSolutions = new Solution[subProblems.length];
        for (int i = 0; i < subProblems.length; i++) {
            subSolutions[i] = solve(subProblems[i]);
        }

        return combine(p, subSolutions);
    }

    protected abstract boolean isSimple(Problem p);
    protected abstract Solution simplySolve(Problem p);
    protected abstract Problem[] decompose(Problem p);
    protected abstract Solution combine(Problem p, Solution[] ss);
}
