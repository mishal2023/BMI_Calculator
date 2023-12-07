package p;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class BMICalculator extends JFrame {

    private JTextField heightEntry, weightEntry;
    private JLabel valueLabel;

    public BMICalculator() {
        setTitle("BMI Calculator");
        setSize(400, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color darkBackground = new Color(44, 62, 80);
        Color lightForeground = new Color(236, 240, 241);
        Color accentColor = new Color(52, 152, 219);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(darkBackground);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel heightLabel = new JLabel("Height");
        JLabel weightLabel = new JLabel("Weight");
        heightLabel.setForeground(lightForeground);
        weightLabel.setForeground(lightForeground);

        heightEntry = new JTextField(10);
        weightEntry = new JTextField(10);

        heightEntry.setText("Meter");
        heightEntry.setForeground(lightForeground);
        weightEntry.setText("Kilogram");
        weightEntry.setForeground(lightForeground);

        heightEntry.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (heightEntry.getText().equals("Meter")) {
                    heightEntry.setText("");
                    heightEntry.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (heightEntry.getText().isEmpty()) {
                    heightEntry.setText("Meter");
                    heightEntry.setForeground(lightForeground);
                }
            }
        });

        weightEntry.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (weightEntry.getText().equals("Kilogram")) {
                    weightEntry.setText("");
                    weightEntry.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (weightEntry.getText().isEmpty()) {
                    weightEntry.setText("Kilogram");
                    weightEntry.setForeground(lightForeground);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(heightLabel, gbc);

        gbc.gridx = 1;
        panel.add(heightEntry, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(weightLabel, gbc);

        gbc.gridx = 1;
        panel.add(weightEntry, gbc);

        JButton calculateButton = new JButton("Calculate BMI");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bmiCalculation();
            }
        });
        calculateButton.setBackground(accentColor);
        calculateButton.setForeground(lightForeground);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(calculateButton, gbc);

        valueLabel = new JLabel("BMI");
        valueLabel.setFont(new Font("Helvetica", Font.PLAIN, 15));
        valueLabel.setForeground(lightForeground);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(valueLabel, gbc);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setOpaque(true);
        panel.setBackground(darkBackground);

        add(panel);
    }

    private void bmiCalculation() {
        try {
            double height = Double.parseDouble(heightEntry.getText());
            double weight = Double.parseDouble(weightEntry.getText());
            double result = weight / Math.pow(height, 2);
            valueLabel.setText("BMI: " + String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            valueLabel.setText("Invalid input.\nPlease enter values for height and weight.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BMICalculator().setVisible(true);
            }
        });
    }
}
