package figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Canvas extends JPanel implements Runnable {
    private static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
    private static final Cursor LEFT_TOP_CORNER = new Cursor(Cursor.NE_RESIZE_CURSOR);
    private static final Cursor VERTICAL = new Cursor(Cursor.S_RESIZE_CURSOR);
    private static final Cursor RIGHT_TOP_CORNER = new Cursor(Cursor.SW_RESIZE_CURSOR);
    private static final Cursor HORIZONTAL = new Cursor(Cursor.E_RESIZE_CURSOR);
    private static final Cursor LEFT_NETHER_CORNER = new Cursor(Cursor.NW_RESIZE_CURSOR);
    private static final Cursor RIGHT_NETHER_CORNER = new Cursor(Cursor.SE_RESIZE_CURSOR);
    private ArrayList<Peg> peges = new ArrayList<>();
    private int mX;
    private int mY;
    private Peg peg1;
    private Peg peg2;
    private Peg peg3;
    private int discNumber;
    private Thread t;
    private boolean isSelected;
    private boolean left;
    private boolean right;
    private boolean top;
    private boolean botton;
    private boolean isMove = false;


    private Border border = new Border(10, 10, 1000, 600, Color.black);

    public Canvas() {

        peg1 = new Peg(border.getX() + (((border.getWidth() / 3) / 5) + (((((border.getWidth() / 3) / 5) * 3) / 5) * 2)),
                (border.getY() + border.getHeight()) - ((((border.getWidth() / 3) / 5) * 3) + ((((border.getWidth() / 3) / 5) * 3) / 5) + 50),
                ((((border.getWidth() / 3) / 5) * 3) / 5) / 2,
                (((border.getWidth() / 3) / 5) * 3),
                this, Color.green, "PEG 1");

        peg2 = new Peg(border.getX() + (border.getWidth() / 3) + (((border.getWidth() / 3) / 5) + (((((border.getWidth() / 3) / 5) * 3) / 5) * 2)),
                (border.getY() + border.getHeight()) - ((((border.getWidth() / 3) / 5) * 3) + ((((border.getWidth() / 3) / 5) * 3) / 5) + 50),
                ((((border.getWidth() / 3) / 5) * 3) / 5) / 2,
                (((border.getWidth() / 3) / 5) * 3),
                this, Color.green, "PEG 2");

        peg3 = new Peg(border.getX() + ((border.getWidth() / 3) * 2) + (((border.getWidth() / 3) / 5) + (((((border.getWidth() / 3) / 5) * 3) / 5) * 2)),
                (border.getY() + border.getHeight()) - ((((border.getWidth() / 3) / 5) * 3) + ((((border.getWidth() / 3) / 5) * 3) / 5) + 50),
                ((((border.getWidth() / 3) / 5) * 3) / 5) / 2,
                (((border.getWidth() / 3) / 5) * 3),
                this, Color.green, "PEG 3");

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
                transformBorder(e);

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseMovedPerformed(e);
            }
        });

    }

    private void mouseMovedPerformed(MouseEvent e) {
        if (border.getTop() + 3 >= e.getY() && border.getTop() - 3 <= e.getY() && border.getLeft() + 5 < e.getX() && border.getRight() - 5 > e.getX()) {
            setCursor(VERTICAL);
            top = true;
            left = false;
            right = false;
            botton = false;
        } else if (border.getBotton() + 3 >= e.getY() && border.getBotton() - 3 <= e.getY() && border.getLeft() + 5 < e.getX() && border.getRight() - 5 > e.getX()) {
            setCursor(VERTICAL);
            top = false;
            left = false;
            right = false;
            botton = true;

        } else if (border.getLeft() + 3 >= e.getX() && border.getLeft() - 3 <= e.getX() && border.getTop() + 5 < e.getY() && border.getBotton() - 5 > e.getY()) {
            setCursor(HORIZONTAL);
            top = false;
            left = true;
            right = false;
            botton = false;

        } else if (border.getRight() + 3 >= e.getX() && border.getRight() - 3 <= e.getX() && border.getTop() + 5 < e.getY() && border.getBotton() - 5 > e.getY()) {
            setCursor(HORIZONTAL);
            top = false;
            left = false;
            right = true;
            botton = false;
        } else if (border.getTop() + 5 >= e.getY() && border.getTop() - 5 <= e.getY() && border.getLeft() + 5 >= e.getX() && border.getLeft() - 5 <= e.getX()) {
            setCursor(LEFT_NETHER_CORNER);
            top = true;
            left = true;
            right = false;
            botton = false;

        } else if (border.getLeft() + 3 >= e.getX() && border.getLeft() - 3 <= e.getX() && border.getBotton() + 3 >= e.getY() && border.getBotton() - 3 <= e.getY()) {
            setCursor(LEFT_TOP_CORNER);
            top = false;
            left = true;
            right = false;
            botton = true;
        } else if (border.getTop() + 5 >= e.getY() && border.getTop() - 5 <= e.getY() && border.getRight() + 3 >= e.getX() && border.getRight() - 3 <= e.getX()) {
            setCursor(RIGHT_TOP_CORNER);
            top = true;
            left = false;
            right = true;
            botton = false;
        } else if (border.getBotton() + 3 >= e.getY() && border.getBotton() - 3 <= e.getY() && border.getRight() + 3 >= e.getX() && border.getRight() - 3 <= e.getX()) {
            setCursor(RIGHT_NETHER_CORNER);
            top = false;
            left = false;
            right = true;
            botton = true;
        } else {
            setCursor(DEFAULT_CURSOR);
            top = false;
            left = false;
            right = false;
            botton = false;

        }


    }

    private void responsPeg() {
        peg1.setX(((border.getWidth() / 3) / 5) + (((((border.getWidth() / 3) / 5) * 3) / 5) * 2));
        peg1.setY((border.getY() + border.getHeight()) - ((((border.getWidth() / 3) / 5) * 3) + ((((border.getWidth() / 3) / 5) * 3) / 5) + 50));
        peg1.setWidth(((((border.getWidth() / 3) / 5) * 3) / 5) / 2);
        peg1.setHeight((((border.getWidth() / 3) / 5) * 3));

        peg2.setX(border.getX() + (border.getWidth() / 3) + (((border.getWidth() / 3) / 5) + (((((border.getWidth() / 3) / 5) * 3) / 5) * 2)));
        peg2.setY((border.getY() + border.getHeight()) - ((((border.getWidth() / 3) / 5) * 3) + ((((border.getWidth() / 3) / 5) * 3) / 5) + 50));
        peg2.setWidth(((((border.getWidth() / 3) / 5) * 3) / 5) / 2);
        peg2.setHeight((((border.getWidth() / 3) / 5) * 3));

        peg3.setX(border.getX() + ((border.getWidth() / 3) * 2) + (((border.getWidth() / 3) / 5) + (((((border.getWidth() / 3) / 5) * 3) / 5) * 2)));
        peg3.setY((border.getY() + border.getHeight()) - ((((border.getWidth() / 3) / 5) * 3) + ((((border.getWidth() / 3) / 5) * 3) / 5) + 50));
        peg3.setWidth(((((border.getWidth() / 3) / 5) * 3) / 5) / 2);
        peg3.setHeight((((border.getWidth() / 3) / 5) * 3));
    }

    public void add(int discNumber) {
        killThread();
        cleardisc();
        int diskHeigth;
        int currentWidth;
        if ((peg1.getHeight() / 2) / discNumber > 10) {
            currentWidth = 10;
        } else {
            currentWidth = (peg1.getHeight() / 2) / discNumber;
        }
        if (((peg1.getHeight() - discNumber) / discNumber) < 10) {
            diskHeigth = peg1.getHeight() / discNumber;
        } else {
            diskHeigth = 10;
        }
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

//    private void transformDisc() {
//        int length = figures.size();
//        int diskHeigth;
//        int currentWidth;
//        if ((peg1.getHeight() / 2) / length > 10) {
//            currentWidth = 10;
//        } else {
//            currentWidth = (peg1.getHeight() / 2) / length;
//        }
//        if (((peg1.getHeight() - length) / length) < 10) {
//            diskHeigth = peg1.getHeight() / length;
//        } else {
//            diskHeigth = 10;
//        }
//        for (int i = 0; i < length; i++) {
//
//            figures.get(i).setNewKordinat(
//                    peg1.getX() - (peg1.getHeight() / 2) + (i * currentWidth) + (peg1.getWidth() / 2),
//                    (peg1.getY() + peg1.getHeight()) - (i * diskHeigth + i),
//                    peg1.getHeight() - ((i * currentWidth) * 2),
//                    diskHeigth);
//        }
//    }

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

    private void transformBorder(MouseEvent e) {
        if (top && left) {
            transformTopLeftCorner(e);
        } else if (top && right) {
            transformTopRightCorner(e);
        } else if (botton && left) {
            transformBottonLeftCorner(e);
        } else if (botton && right) {
            transformBottonRightCorner(e);
        } else if (top) {
            transformTop(e);
        } else if (left) {
            transformLeft(e);

        } else if (right) {
            transformRight(e);
        } else if (botton) {
            transformBotton(e);
        }


    }

    private void transformTopLeftCorner(MouseEvent e) {
        transformTop(e);
        transformLeft(e);
    }

    private void transformTopRightCorner(MouseEvent e) {
        transformTop(e);
        transformRight(e);
    }

    private void transformBottonLeftCorner(MouseEvent e) {
        transformBotton(e);
        transformLeft(e);
    }

    private void transformBottonRightCorner(MouseEvent e) {
        transformBotton(e);
        transformRight(e);
    }

    private void transformLeft(MouseEvent e) {

        border.setWidth(border.getWidth() + (border.getLeft() - e.getX()));
        border.setX(e.getX());
        responsPeg();
        update(getGraphics());
    }

    private void transformRight(MouseEvent e) {
        border.setWidth(border.getWidth() + (e.getX() - border.getRight()));
        responsPeg();
        update(getGraphics());
    }

    private void transformTop(MouseEvent e) {

        border.setHeight(border.getHeight() + (border.getY() - e.getY()));
        border.setY(e.getY());
        responsPeg();
        update(getGraphics());
    }

    private void transformBotton(MouseEvent e) {
        border.setHeight(border.getHeight() + (e.getY() - border.getBotton()));
        responsPeg();
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