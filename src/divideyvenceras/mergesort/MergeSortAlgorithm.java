package divideyvenceras.mergesort;

import divideyvenceras.*;

import java.util.Arrays;

public class MergeSortAlgorithm extends DivConqTemplate {

    @Override
    protected boolean isSimple(Problem p) {
        MergeProblem mp = (MergeProblem) p;
        return (mp.end - mp.start) <= 1;
    }

    @Override
    protected Solution simplySolve(Problem p) {
        MergeProblem mp = (MergeProblem) p;
        int[] single = Arrays.copyOfRange(mp.data, mp.start, mp.end);
        return new MergeSolution(single);
    }

    @Override
    protected Problem[] decompose(Problem p) {
        MergeProblem mp = (MergeProblem) p;
        int mid = (mp.start + mp.end) / 2;
        return new Problem[]{
                new MergeProblem(mp.data, mp.start, mid),
                new MergeProblem(mp.data, mid, mp.end)
        };
    }

    @Override
    protected Solution combine(Problem p, Solution[] ss) {
        int[] left = ((MergeSolution) ss[0]).getSorted();
        int[] right = ((MergeSolution) ss[1]).getSorted();
        int[] merged = merge(left, right);
        return new MergeSolution(merged);
    }

    /** Combina dos subarrays ordenados */
    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
        return result;
    }
}
