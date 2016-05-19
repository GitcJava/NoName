package figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Frame extends JFrame {
    Canvas canvas = new Canvas();
    JTextField textField;
    private int discNumber;
    public Frame() throws HeadlessException {
        super("Hanoi Tower");

        JPanel controlPanel = new JPanel();
         textField = new JTextField(15);
        JButton loading = new JButton("Loading");
        JButton start = new JButton("Start");


        loading.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addActionPerformed(e);
            }

        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startActionPerformed(e);
            }

        });

        controlPanel.add(textField);
        controlPanel.add(loading);
        controlPanel.add(start);

        add(controlPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private int getDiscNumber(){

        try {
            discNumber = Integer.parseInt(textField.getText());

            if(discNumber <1){
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(this,"Enter Number\n Number > 0","Warrning",JOptionPane.ERROR_MESSAGE);
            discNumber=0;
        }
        return discNumber;
    }

    private void addActionPerformed (ActionEvent e) {
        if(getDiscNumber()<1)return;
        canvas.add(getDiscNumber());
    }

    private void startActionPerformed(ActionEvent e){
        if(getDiscNumber()<1)return;
        canvas.start(getDiscNumber());
    }


    public static void main(String[] args) {
        new Frame();
    }


}
