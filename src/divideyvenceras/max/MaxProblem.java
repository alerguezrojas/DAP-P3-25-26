package divideyvenceras.max;

import divideyvenceras.Problem;

public class MaxProblem implements Problem {
    public int[] data;
    public int start, end;

    public MaxProblem(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }
}
