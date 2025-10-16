package divideyvenceras;

import java.util.Arrays;

/**
 * Decorador que traza las llamadas recursivas del método plantilla
 * mostrando un árbol visual con símbolos ├──, └── y │.
 */
public class TracingAlgorithm extends DivConqTemplate {
    private final DivConqTemplate inner;
    private final StringBuilder trace;
    private int depth = 0;

    public TracingAlgorithm(DivConqTemplate inner, StringBuilder trace) {
        this.inner = inner;
        this.trace = trace;
    }

    /** Genera la indentación con ramas verticales (│) */
    private String indent() {
        return "│   ".repeat(Math.max(0, depth - 1));
    }

    @Override
    protected boolean isSimple(Problem p) {
        // Cabecera de la llamada
        trace.append(indent());
        if (depth > 0) trace.append("├── ");
        else trace.append("└── ");
        trace.append(inner.getClass().getSimpleName())
                .append("(")
                .append(previewProblem(p))
                .append(")\n");
        return inner.isSimple(p);
    }

    @Override
    protected Solution simplySolve(Problem p) {
        depth++;
        trace.append(indent()).append("└── simplySolve()\n");
        depth--;
        return inner.simplySolve(p);
    }

    @Override
    protected Problem[] decompose(Problem p) {
        depth++;
        Problem[] subs = inner.decompose(p);
        for (int i = 0; i < subs.length; i++) {
            trace.append(indent());
            if (i == subs.length - 1)
                trace.append("└── ");
            else
                trace.append("├── ");
            trace.append("Subproblem: ")
                    .append(previewProblem(subs[i]))
                    .append("\n");
        }
        depth--;
        return subs;
    }

    @Override
    protected Solution combine(Problem p, Solution[] ss) {
        depth++;
        trace.append(indent()).append("└── combine()\n");
        Solution out = inner.combine(p, ss);
        depth--;
        trace.append("\n");
        return inner.combine(p, ss);
    }

    /** Devuelve una vista simplificada del problema mostrando el subarray */
    private String previewProblem(Problem p) {
        try {
            var field = p.getClass().getDeclaredField("data");
            field.setAccessible(true);
            int[] data = (int[]) field.get(p);

            var start = p.getClass().getDeclaredField("start");
            start.setAccessible(true);
            int s = (int) start.get(p);

            var end = p.getClass().getDeclaredField("end");
            end.setAccessible(true);
            int e = (int) end.get(p);

            int[] sub = Arrays.copyOfRange(data, s, e);
            return Arrays.toString(sub);
        } catch (Exception e) {
            return p.getClass().getSimpleName();
        }
    }
}
