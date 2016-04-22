package figure;

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
    private int disksize;
    private Thread t;
    private boolean isSelected;

    private Border border = new Border(10, 10, 1000, 600, Color.black);

    public Canvas() {


        peg1 = new Peg(border.getX() + (((border.getWidth() / 3) / 5) + (((((border.getWidth() / 3) / 5) * 3) / 5) * 2)),
                (border.getY() + border.getHeight()) - ((((border.getWidth() / 3) / 5) * 3) + ((((border.getWidth() / 3) / 5) * 3) / 5) + 50),
                ((((border.getWidth() / 3) / 5) * 3) / 5) / 2,
                (((border.getWidth() / 3) / 5) * 3),
                this, Color.green);

        peg2 = new Peg(border.getX() + (border.getWidth() / 3) + (((border.getWidth() / 3) / 5) + (((((border.getWidth() / 3) / 5) * 3) / 5) * 2)),
                (border.getY() + border.getHeight()) - ((((border.getWidth() / 3) / 5) * 3) + ((((border.getWidth() / 3) / 5) * 3) / 5) + 50),
                ((((border.getWidth() / 3) / 5) * 3) / 5) / 2,
                (((border.getWidth() / 3) / 5) * 3),
                this, Color.green);

        peg3 = new Peg(border.getX() + ((border.getWidth() / 3) * 2) + (((border.getWidth() / 3) / 5) + (((((border.getWidth() / 3) / 5) * 3) / 5) * 2)),
                (border.getY() + border.getHeight()) - ((((border.getWidth() / 3) / 5) * 3) + ((((border.getWidth() / 3) / 5) * 3) / 5) + 50),
                ((((border.getWidth() / 3) / 5) * 3) / 5) / 2,
                (((border.getWidth() / 3) / 5) * 3),
                this, Color.green);

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

    public void add(int value) {
        peg1.getDisksArray().clear();
        int diskHeigth;
        int currentWidth;
        if((peg1.getHeight() / 2) / value>10){
            currentWidth = 10;
        }else {
            currentWidth = (peg1.getHeight() / 2) / value;
        }
        if (((peg1.getHeight() - value) / value) < 10) {
            diskHeigth = peg1.getHeight() / value;
        } else {
            diskHeigth = 10;
        }
        for (int i = 1; i <= value; i++) {
            peg1.addArray(new Disks(
                    peg1.getX() - (peg1.getHeight() / 2) + (i * currentWidth) + (peg1.getWidth() / 2),
                    (peg1.getY() + peg1.getHeight()) - (i * diskHeigth + i),
                    peg1.getHeight() - ((i * currentWidth) * 2),
                    diskHeigth, this, Color.blue)
            );
        }
        update(getGraphics());
    }


    public Peg getSelected() {
        if (isSelected) {
            int size = peges.size();
            return size > 0 ? (Peg) peges.get(size - 1) : null;
        }
        return null;
    }

    public void start(int value) {
        this.disksize = value;
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
        if (n == 0) {
            start.getEndDisc().discMove(start, end);

        } else {
            movedisk(n - 1, start, end, second);
            start.getEndDisc().discMove(start, end);
        }
            movedisk(n - 1, second, start, end);
    }


    @Override
    public void run() {
        movedisk(disksize - 1, peg1, peg2, peg3);
    }

    public void pause() {


    }

    public void continueRun() {

    }

    public void stop() {

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
