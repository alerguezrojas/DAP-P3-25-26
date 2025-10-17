package divideyvenceras.maxmin3;

import divideyvenceras.Problem;

public class MaxMinProblem implements Problem {
    private int[] data;
    private int start;
    private int end;

    public MaxMinProblem(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    public int[] getData() { return data; }
    public int getStart() { return start; }
    public int getEnd() { return end; }

    public int size() { return end - start; }
}
