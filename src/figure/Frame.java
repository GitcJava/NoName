package figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Frame extends JFrame {
    Canvas canvas = new Canvas();
    JTextField textField;
    private int value;
    public Frame() throws HeadlessException {
        super("Hanoi Tower");

        JPanel controlPanel = new JPanel();
         textField = new JTextField(15);
        JButton addButton = new JButton("Add");
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton  pauseButton = new JButton("Pause");
        JButton continueButton =new JButton("Continue");

        addButton.addActionListener(new ActionListener() {
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

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopActionPerformed(e);
            }
///
        });

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                continueActionPerformed(e);
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                pauseActionPerformed(e);
            }
        });

        controlPanel.add(textField);
        controlPanel.add(addButton);
        controlPanel.add(start);
        controlPanel.add(stop);
        controlPanel.add(pauseButton);
        controlPanel.add(continueButton);

        add(controlPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);


        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private int getValue(){

        try {
            value = Integer.parseInt(textField.getText());
            if(value<1){
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(this,"Enter Number\n Number > 0","Warrning",JOptionPane.ERROR_MESSAGE);
            value = -1;
        }
        return value;
    }

    private void addActionPerformed (ActionEvent e) {
        canvas.add(getValue());
    }

    private void startActionPerformed(ActionEvent e){
        canvas.start(getValue());
    }

    private void pauseActionPerformed (ActionEvent e){

    }

    private void stopActionPerformed (ActionEvent e){

    }


    private void continueActionPerformed (ActionEvent e){

    }

    public static void main(String[] args) {
        new Frame();
    }


}
