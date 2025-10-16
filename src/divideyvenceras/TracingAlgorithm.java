package divideyvenceras;

import java.util.Arrays;

/**
 * Decorador que traza el árbol de recursión de un algoritmo de Divide y Vencerás.
 * Corrige la gestión de 'depth' para que las llamadas hijas queden indentadas
 * (incrementa en decompose y decrementa en combine).
 * Además, si el algoritmo envuelto es un Medidor, muestra el nombre del algoritmo real.
 */
public class TracingAlgorithm extends DivConqTemplate {
    private final DivConqTemplate inner;
    private final DivConqTemplate baseForName; // algoritmo real para mostrar
    private final StringBuilder trace;
    private int depth = 0;

    public TracingAlgorithm(DivConqTemplate inner, StringBuilder trace) {
        this.inner = inner;
        this.trace = trace;
        // si viene medido, obtenemos el algoritmo base para el nombre
        if (inner instanceof MeasuredAlgorithm m) {
            this.baseForName = m.getInner();
        } else {
            this.baseForName = inner;
        }
    }

    /** Indentación con ramas verticales */
    private String indent() {
        return "│   ".repeat(Math.max(0, depth));
    }

    private String algoName() {
        return baseForName.getClass().getSimpleName();
    }

    @Override
    protected boolean isSimple(Problem p) {
        // Cabecera en el nivel actual
        if (depth == 0) trace.append("└── ");
        else            trace.append(indent()).append("├── ");
        trace.append(algoName()).append("(").append(previewProblem(p)).append(")\n");
        return inner.isSimple(p);
    }

    @Override
    protected Solution simplySolve(Problem p) {
        // Caso base dentro del nivel actual (sin cambiar depth)
        trace.append(indent()).append("└── simplySolve()\n");
        return inner.simplySolve(p);
    }

    @Override
    protected Problem[] decompose(Problem p) {
        // Entramos a nivel de hijos
        trace.append(indent()).append("├── decompose()\n");
        Problem[] subs = inner.decompose(p);

        // Subimos depth para que las siguientes llamadas (a solve de hijos) se vean indentadas
        depth++;

        // (opcional) listar los subproblemas detectados:
        for (int i = 0; i < subs.length; i++) {
            trace.append(indent());
            if (i == subs.length - 1) trace.append("└── ");
            else                      trace.append("├── ");
            trace.append("Subproblem: ").append(previewProblem(subs[i])).append("\n");
        }
        return subs;
    }

    @Override
    protected Solution combine(Problem p, Solution[] ss) {
        // Se ejecuta tras resolver recursivamente los hijos → aún estamos un nivel más adentro
        trace.append(indent()).append("└── combine()\n");
        Solution out = inner.combine(p, ss);

        // Bajamos depth al cerrar el nivel del padre
        depth--;

        // Separación visual opcional
        //trace.append("\n");
        return out;
    }

    /** Vista amigable del subarray si existen campos data/start/end */
    private String previewProblem(Problem p) {
        try {
            var fData = p.getClass().getDeclaredField("data");
            fData.setAccessible(true);
            int[] data = (int[]) fData.get(p);

            var fStart = p.getClass().getDeclaredField("start");
            fStart.setAccessible(true);
            int s = (int) fStart.get(p);

            var fEnd = p.getClass().getDeclaredField("end");
            fEnd.setAccessible(true);
            int e = (int) fEnd.get(p);

            int[] sub = Arrays.copyOfRange(data, s, e);
            return Arrays.toString(sub);
        } catch (Exception e) {
            return p.getClass().getSimpleName();
        }
    }
}
