package divideyvenceras;

/**
 * Decorador que mide el rendimiento de un algoritmo Divide & Conquer.
 * Métricas:
 *  - solveCalls: nº de nodos (aprox. una por llamada a isSimple).
 *  - leafCount: nº de casos base (simplySolve).
 *  - workMs: tiempo interno de trabajo (decompose + simplySolve + combine).
 *
 * Nota: El tiempo total "de pared" se mide fuera (en la UI) con System.nanoTime().
 */
public class MeasuredAlgorithm extends DivConqTemplate {
    private final DivConqTemplate inner;

    private int solveCalls = 0;
    private int leafCount  = 0;
    private long workNanos = 0L;

    public MeasuredAlgorithm(DivConqTemplate inner) {
        this.inner = inner;
    }

    // --- Métricas públicas
    public int getSolveCalls() { return solveCalls; }
    public int getLeafCount()  { return leafCount;  }
    public double getWorkMs()  { return workNanos / 1_000_000.0; }
    public DivConqTemplate getInner() { return inner; }

    @Override
    protected boolean isSimple(Problem p) {
        // isSimple se evalúa por nodo; lo usamos para contar llamadas a solve()
        solveCalls++;
        return inner.isSimple(p);
    }

    @Override
    protected Solution simplySolve(Problem p) {
        long t0 = System.nanoTime();
        Solution s = inner.simplySolve(p);
        long t1 = System.nanoTime();
        workNanos += (t1 - t0);
        leafCount++;
        return s;
    }

    @Override
    protected Problem[] decompose(Problem p) {
        long t0 = System.nanoTime();
        Problem[] subs = inner.decompose(p);
        long t1 = System.nanoTime();
        workNanos += (t1 - t0);
        return subs;
    }

    @Override
    protected Solution combine(Problem p, Solution[] ss) {
        long t0 = System.nanoTime();
        Solution s = inner.combine(p, ss);
        long t1 = System.nanoTime();
        workNanos += (t1 - t0);
        return s;
    }
}
