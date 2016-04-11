package figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FigureFrame extends JFrame {
    FigureCanvas canvas = new FigureCanvas();

    public FigureFrame() throws HeadlessException {
        super("Figure");

        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton start = new JButton("start");
        JButton stop = new JButton("stop");
        JButton  pauseButton = new JButton("pause");
        JButton continueButton =new JButton("continue");


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
//
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addActionPerformed(e);
            }

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

        controlPanel.add(addButton);
        controlPanel.add(start);
        controlPanel.add(stop);
        controlPanel.add(pauseButton);
        controlPanel.add(continueButton);

        add(controlPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);

        setSize(450, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addActionPerformed (ActionEvent e){

    }

    private void startActionPerformed(ActionEvent e){

    }

    private void pauseActionPerformed (ActionEvent e){

    }

    private void stopActionPerformed (ActionEvent e){

    }


    private void continueActionPerformed (ActionEvent e){

    }

    public static void main(String[] args) {
        new FigureFrame();
    }


}
