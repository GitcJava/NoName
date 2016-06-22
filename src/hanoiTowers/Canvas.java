package hanoiTowers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Canvas extends JPanel implements Runnable {

    private ArrayList<Peg> peges = new ArrayList<>();
    private int mX;
    private int mY;
    private Peg peg1;
    private Peg peg2;
    private Peg peg3;
    //start coordinates peg
    private static final int X_PEG1 = 160;
    private static final int Y_PEG1 = 320;
    private static final int WIDTH_PEG1 = 20;
    private static final int HEIGTH_PEG1 = 200;

    private static final int X_PEG2 = 480;
    private static final int Y_PEG2 = 320;
    private static final int WIDTH_PEG2 = 20;
    private static final int HEIGTH_PEG2 = 200;

    private static final int X_PEG3 = 820;
    private static final int Y_PEG3 = 320;
    private static final int WIDTH_PEG3 = 20;
    private static final int HEIGTH_PEG3 = 200;

    private int discNumber;
    private Thread t;
    private boolean isSelected;
    private boolean isMove = false;


    private Border border = new Border(10, 10, 1000, 600, Color.black);

    public Canvas() {

        peg1 = new Peg(X_PEG1,Y_PEG1, WIDTH_PEG1,HEIGTH_PEG1,this, Color.green, "PEG 1");

        peg2 = new Peg(X_PEG2,Y_PEG2, WIDTH_PEG2,HEIGTH_PEG2,this, Color.green, "PEG 2");

        peg3 = new Peg(X_PEG3,Y_PEG3, WIDTH_PEG3,HEIGTH_PEG3,this, Color.green, "PEG 3");

        peges.add(peg1);
        peges.add(peg2);
        peges.add(peg3);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedPerformed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseDraggedPerformed(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

    }


    public void add(int discNumber) {
        killThread();
        cleardisc();
        int diskHeigth;
        int currentWidth;

        currentWidth = getCurrentcurrentWidth(discNumber);

        diskHeigth = currentHeight(discNumber);

        for (int i = 1; i <= discNumber; i++) {
            peg1.addArray(new Disks(
                    peg1.getX() - (peg1.getHeight() / 2) + (i * currentWidth) + (peg1.getWidth() / 2),
                    (peg1.getY() + peg1.getHeight()) - (i * diskHeigth + i),
                    peg1.getHeight() - ((i * currentWidth) * 2),
                    diskHeigth, this, Color.blue)
            );
        }
        update(getGraphics());
    }

    private  int currentHeight( int discNumber){
        if (((peg1.getHeight() - discNumber) / discNumber) < 10) {
            return peg1.getHeight() / discNumber;
        } else {
            return  10;
        }
    }

    private int getCurrentcurrentWidth(int discNumber){
        if ((peg1.getHeight() / 2) / discNumber > 10) {
            return  10;
        } else {
            return (peg1.getHeight() / 2) / discNumber;
        }
    }


    private synchronized void killThread() {
        isMove = false;
        if (t != null && t.getState() != Thread.State.TERMINATED) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isMove() {
        return isMove;
    }

    private void cleardisc() {
        peg1.getDisksArray().clear();
        peg2.getDisksArray().clear();
        peg3.getDisksArray().clear();

    }

    public Peg getSelected() {
        if (isSelected) {
            int size = peges.size();
            return size > 0 ? (Peg) peges.get(size - 1) : null;
        }
        return null;
    }

    public void start(int discNumber) {
        this.discNumber = discNumber;
        t = new Thread(this);
        t.start();
    }


    private void mouseDraggedPerformed(MouseEvent e) {
        if (isSelected) {
            int size = getSelected().getDisksArray().size();
            getSelected().move(e.getX() - mX, e.getY() - mY);
            for (int i = 0; i < size; i++) {
                getSelected().getDisksArray().get(i).move(e.getX() - mX, e.getY() - mY);
            }
            repaint();
        }
        mX = e.getX();
        mY = e.getY();
        update(getGraphics());
    }


    private void mousePressedPerformed(MouseEvent e) {
        mX = e.getX();
        mY = e.getY();
        select(mX, mY);
        update(getGraphics());
    }

    private void select(int x, int y) {
        int size = peges.size();
        isSelected = false;
        for (int i = size - 1; i >= 0; i--) {
            Peg f = peges.get(i);
            if (f.isBelong(x, y)) {
                peges.remove(f);
                peges.add(f);
                isSelected = true;
                return;
            }
        }
    }


    private void movedisk(int n, Peg start, Peg second, Peg end) {
        if (!isMove) {
            return;
        }
        if (n == 0) {
            start.getEndDisc().discMove(start, end);

        } else {
            movedisk(n - 1, start, end, second);
            start.getEndDisc().discMove(start, end);
            movedisk(n - 1, second, start, end);
        }

    }


    @Override
    public void run() {
        isMove = true;
        movedisk(discNumber - 1, peg1, peg2, peg3);
        synchronized (this) {
            this.notify();
        }
    }


    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        border.draw(gr);
        peg1.draw(gr);
        peg2.draw(gr);
        peg3.draw(gr);
        for (Peg fig : peges) {
            fig.draw(gr);
            for (FigureHanoi fi : fig.getDisksArray()) {
                fi.draw(gr);
            }
        }
    }


}