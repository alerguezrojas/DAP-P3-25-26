package divideyvenceras.sum;

import divideyvenceras.Problem;

public class SumProblem implements Problem {
    public int[] data;
    public int start, end;

    public SumProblem(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }
}
