package divideyvenceras.ui;

import divideyvenceras.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class App extends JFrame {
    private final JComboBox<AlgorithmAdapter> algoBox;
    private final JTextField inputField;
    private final JTextArea outputArea;
    private final JButton runBtn;

    public App() {
        super("DAP-P3-25-26 – Divide & Conquer Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(640, 360);
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
        runBtn = new JButton("Run");

        // --- Layout
        JPanel top = new JPanel(new GridLayout(2, 1, 8, 8));
        JPanel row1 = new JPanel(new BorderLayout(8, 8));
        row1.add(new JLabel("Algorithm:"), BorderLayout.WEST);
        row1.add(algoBox, BorderLayout.CENTER);

        JPanel row2 = new JPanel(new BorderLayout(8, 8));
        row2.add(new JLabel("Array (comma separated):"), BorderLayout.WEST);
        row2.add(inputField, BorderLayout.CENTER);
        row2.add(runBtn, BorderLayout.EAST);

        top.add(row1);
        top.add(row2);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // --- Actions
        runBtn.addActionListener(e -> runAlgorithm());
    }

    private void runAlgorithm() {
        AlgorithmAdapter adapter = (AlgorithmAdapter) algoBox.getSelectedItem();
        if (adapter == null) return;

        try {
            int[] data = parseArray(inputField.getText());
            Problem p = adapter.makeProblem(data);

            long t0 = System.nanoTime();
            Solution s = adapter.solve(p);
            long t1 = System.nanoTime();

            StringBuilder sb = new StringBuilder();
            sb.append("Input: ").append(Arrays.toString(data)).append("\n");
            sb.append("Algorithm: ").append(adapter.name()).append("\n");
            sb.append(adapter.render(s)).append("\n");
            sb.append(String.format("Elapsed: %.3f ms", (t1 - t0) / 1_000_000.0));

            outputArea.setText(sb.toString());
        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
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
