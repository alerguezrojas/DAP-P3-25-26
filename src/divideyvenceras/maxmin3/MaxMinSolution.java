package divideyvenceras.maxmin3;

import divideyvenceras.Solution;

public class MaxMinSolution implements Solution {
    private int max;
    private int min;

    public MaxMinSolution(int max, int min) {
        this.max = max;
        this.min = min;
    }

    public int getMax() { return max; }
    public int getMin() { return min; }

    @Override
    public String toString() {
        return "(max=" + max + ", min=" + min + ")";
    }
}
