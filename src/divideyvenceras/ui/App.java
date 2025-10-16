package divideyvenceras.ui;

import divideyvenceras.*;
import divideyvenceras.sum.*;
import divideyvenceras.max.*;
import divideyvenceras.mergesort.*;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class App extends JFrame {
    private final JComboBox<AlgorithmAdapter> algoBox;
    private final JTextField inputField;
    private final JTextArea outputArea;
    private final JButton runBtn;
    private final JButton randomBtn;  // <--- nuevo botón

    public App() {
        super("DAP-P3-25-26 – Divide & Conquer Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 420);
        setLocationRelativeTo(null);

        // --- UI components
        algoBox = new JComboBox<>(new AlgorithmAdapter[]{
                new SumAdapter(),
                new MaxAdapter(),
                new MergeAdapter()
        });

        inputField = new JTextField("3, 8, 2, 5, 7, 1, 9, 4");
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        runBtn = new JButton("Run");
        randomBtn = new JButton("Random"); // <--- nuevo botón

        // --- Layout superior (selector + entrada + botones)
        JPanel top = new JPanel(new GridLayout(2, 1, 8, 8));

        JPanel row1 = new JPanel(new BorderLayout(8, 8));
        row1.add(new JLabel("Algorithm:"), BorderLayout.WEST);
        row1.add(algoBox, BorderLayout.CENTER);

        JPanel row2 = new JPanel(new BorderLayout(8, 8));
        row2.add(new JLabel("Array (comma separated):"), BorderLayout.WEST);
        row2.add(inputField, BorderLayout.CENTER);

        // Panel para agrupar Random + Run a la derecha
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        buttonsPanel.add(randomBtn); // <--- añadimos Random
        buttonsPanel.add(runBtn);
        row2.add(buttonsPanel, BorderLayout.EAST);

        top.add(row1);
        top.add(row2);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // --- Actions
        runBtn.addActionListener(e -> runAlgorithm());
        randomBtn.addActionListener(e -> generateRandomArray()); // <--- acción Random
    }

    private void runAlgorithm() {
        AlgorithmAdapter adapter = (AlgorithmAdapter) algoBox.getSelectedItem();
        if (adapter == null) return;

        try {
            int[] data = parseArray(inputField.getText());
            Problem p = adapter.makeProblem(data);

            // --- Preparar StringBuilder para el trace
            StringBuilder trace = new StringBuilder();

            // --- Obtener algoritmo base e envolverlo con el tracer
            DivConqTemplate baseAlg = adapter.getAlgorithm();
            DivConqTemplate tracedAlg = new TracingAlgorithm(baseAlg, trace);

            // --- Ejecutar algoritmo con medición de tiempo
            long t0 = System.nanoTime();
            Solution s = tracedAlg.solve(p);
            long t1 = System.nanoTime();

            // --- Mostrar resultados en el área de salida
            StringBuilder sb = new StringBuilder();
            sb.append("Input: ").append(Arrays.toString(data)).append("\n");
            sb.append("Algorithm: ").append(adapter.name()).append("\n");
            sb.append(adapter.render(s)).append("\n");
            sb.append(String.format("Elapsed: %.3f ms\n\n", (t1 - t0) / 1_000_000.0));
            sb.append("Recursion trace:\n");
            sb.append(trace);

            outputArea.setText(sb.toString());
        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
    }

    // --- NUEVO: genera un array aleatorio y lo vuelca en el inputField
    private void generateRandomArray() {
        Random rnd = new Random();
        int length = 8 + rnd.nextInt(5); // tamaño entre 8 y 12
        int[] arr = rnd.ints(length, 0, 100).toArray();

        // Convierte a "1, 5, 9, 2, 8"
        String text = Arrays.toString(arr)
                .replace("[", "")
                .replace("]", "");
        inputField.setText(text);

        // Feedback visual opcional
        outputArea.setText("Random array generated:\n" + Arrays.toString(arr));
    }

    private int[] parseArray(String text) {
        return Arrays.stream(text.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}
