package divideyvenceras.mergesort;

import divideyvenceras.Solution;

public class MergeSolution implements Solution {
    private int[] sorted;

    public MergeSolution(int[] sorted) {
        this.sorted = sorted;
    }

    public int[] getSorted() {
        return sorted;
    }
}
