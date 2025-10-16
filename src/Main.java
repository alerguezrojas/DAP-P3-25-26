package divideyvenceras;

import divideyvenceras.sum.*;
import divideyvenceras.max.*;

public class Main {
    public static void main(String[] args) {
        int[] datos = {3, 8, 2, 5, 7, 1, 9, 4};

        // ---- Suma ----
        SumProblem sp = new SumProblem(datos, 0, datos.length);
        SumAlgorithm sumAlg = new SumAlgorithm();
        SumSolution sumSol = (SumSolution) sumAlg.solve(sp);
        System.out.println("Suma total = " + sumSol.getValue());

        // ---- Máximo ----
        MaxProblem mp = new MaxProblem(datos, 0, datos.length);
        MaxAlgorithm maxAlg = new MaxAlgorithm();
        MaxSolution maxSol = (MaxSolution) maxAlg.solve(mp);
        System.out.println("Máximo = " + maxSol.getMax());
    }
}
