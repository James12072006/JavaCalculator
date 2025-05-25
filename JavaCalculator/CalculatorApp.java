import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp {
    private JFrame frame;
    private JTextField textField;
    private double num1, num2, result;
    private String operator;

    public CalculatorApp() {
        frame = new JFrame("Calculator");
        textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C"
        };

        for (String label : buttons) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            panel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();

                    if (cmd.charAt(0) >= '0' && cmd.charAt(0) <= '9' || cmd.equals(".")) {
                        textField.setText(textField.getText() + cmd);
                    } else if (cmd.equals("C")) {
                        textField.setText("");
                        num1 = num2 = result = 0;
                        operator = "";
                    } else if (cmd.equals("=")) {
                        num2 = Double.parseDouble(textField.getText());
                        result = CalculatorLogic.calculate(num1, num2, operator);
                        textField.setText("" + result);
                    } else {
                        operator = cmd;
                        num1 = Double.parseDouble(textField.getText());
                        textField.setText("");
                    }
                }
            });
        }

        frame.setLayout(new BorderLayout());
        frame.add(textField, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorApp());
    }
}

class CalculatorLogic {
    public static double calculate(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> b != 0 ? a / b : Double.NaN;
            default -> 0;
        };
    }
}
