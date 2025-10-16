package divideyvenceras.mergesort;

import divideyvenceras.Problem;

public class MergeProblem implements Problem {
    public int[] data;
    public int start, end;

    public MergeProblem(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }
}
