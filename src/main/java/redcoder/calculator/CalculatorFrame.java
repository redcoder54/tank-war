package redcoder.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame {

    public CalculatorFrame() {
        super("计算器");
        setBounds(200, 200, 400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init(getContentPane());

        setVisible(true);
    }

    private void init(Container pane) {
        pane.setLayout(new GridBagLayout());
        int ipadx = 0;
        int ipady = 0;
        Insets insets = new Insets(1, 1, 1, 1);

        JPanel outputPanel = new JPanel();
        pane.add(outputPanel, new GridBagConstraints(0, 0, 1, 1, 1, 0.2,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), ipadx, ipady));
        outputPanel.setLayout(new GridBagLayout());

        JTextArea exprTextArea = new JTextArea();
        exprTextArea.setFont(new Font(null, Font.BOLD, 25));
        outputPanel.add(exprTextArea, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), ipadx, ipady));


        JPanel keyboardPanel = new JPanel();
        pane.add(keyboardPanel, new GridBagConstraints(0, 1, 1, 1, 1, 0.8,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));
        keyboardPanel.setLayout(new GridBagLayout());

        MyActionListener actionListener = new MyActionListener(exprTextArea);
        JButton btn_7 = new JButton("7");
        btn_7.addActionListener(actionListener);
        keyboardPanel.add(btn_7, new GridBagConstraints(0, 1, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_8 = new JButton("8");
        btn_8.addActionListener(actionListener);
        keyboardPanel.add(btn_8, new GridBagConstraints(1, 1, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_9 = new JButton("9");
        btn_9.addActionListener(actionListener);
        keyboardPanel.add(btn_9, new GridBagConstraints(2, 1, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_plus = new JButton("+");
        btn_plus.addActionListener(actionListener);
        keyboardPanel.add(btn_plus, new GridBagConstraints(3, 1, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_4 = new JButton("4");
        btn_4.addActionListener(actionListener);
        keyboardPanel.add(btn_4, new GridBagConstraints(0, 2, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_5 = new JButton("5");
        btn_5.addActionListener(actionListener);
        keyboardPanel.add(btn_5, new GridBagConstraints(1, 2, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_6 = new JButton("6");
        btn_6.addActionListener(actionListener);
        keyboardPanel.add(btn_6, new GridBagConstraints(2, 2, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_minus = new JButton("-");
        btn_minus.addActionListener(actionListener);
        keyboardPanel.add(btn_minus, new GridBagConstraints(3, 2, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_1 = new JButton("1");
        btn_1.addActionListener(actionListener);
        keyboardPanel.add(btn_1, new GridBagConstraints(0, 3, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_2 = new JButton("2");
        btn_2.addActionListener(actionListener);
        keyboardPanel.add(btn_2, new GridBagConstraints(1, 3, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_3 = new JButton("3");
        btn_3.addActionListener(actionListener);
        keyboardPanel.add(btn_3, new GridBagConstraints(2, 3, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_multiply = new JButton("*");
        btn_multiply.addActionListener(actionListener);
        keyboardPanel.add(btn_multiply, new GridBagConstraints(3, 3, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_0 = new JButton("0");
        btn_0.addActionListener(actionListener);
        keyboardPanel.add(btn_0, new GridBagConstraints(0, 4, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_clear = new JButton("C");
        btn_clear.addActionListener(actionListener);
        keyboardPanel.add(btn_clear, new GridBagConstraints(1, 4, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_eq = new JButton("=");
        btn_eq.addActionListener(actionListener);
        keyboardPanel.add(btn_eq, new GridBagConstraints(2, 4, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));

        JButton btn_divide = new JButton("/");
        btn_divide.addActionListener(actionListener);
        keyboardPanel.add(btn_divide, new GridBagConstraints(3, 4, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady));
    }

    private static class MyActionListener implements ActionListener {

        private JTextArea textArea;

        public MyActionListener(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            String text = btn.getText();
            if ("C".equals(text)) {
                textArea.setText("");
            } else if ("=".equals(text)) {
                long result = Helper.calculate(textArea.getText());
                String newText = textArea.getText() + "=\n" + result;
                textArea.setText(newText);
            } else {
                String newText = textArea.getText() + text;
                textArea.setText(newText);
            }
        }
    }
}
