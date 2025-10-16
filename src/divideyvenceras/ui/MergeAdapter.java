package divideyvenceras.ui;

import divideyvenceras.*;
import divideyvenceras.mergesort.*;
import java.util.Arrays;

public class MergeAdapter implements AlgorithmAdapter {
    private final MergeSortAlgorithm alg = new MergeSortAlgorithm();

    @Override public String name() { return "Merge Sort"; }

    @Override public Problem makeProblem(int[] data) {
        return new MergeProblem(data, 0, data.length);
    }

    @Override public Solution solve(Problem p) {
        return alg.solve(p);
    }

    @Override public String render(Solution s) {
        int[] sorted = ((MergeSolution) s).getSorted();
        return "Sorted array = " + Arrays.toString(sorted);
    }

    @Override
    public DivConqTemplate getAlgorithm() {
        return alg;
    }
}
