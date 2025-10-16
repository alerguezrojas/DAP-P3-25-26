package divideyvenceras.ui;

import divideyvenceras.*;

public interface AlgorithmAdapter {
    String name();
    Problem makeProblem(int[] data);
    Solution solve(Problem p);
    String render(Solution s);

    default DivConqTemplate getAlgorithm() {
        return null;
    }
}
