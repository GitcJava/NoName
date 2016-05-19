package figure;

import figures.Figure;

import java.awt.*;

/**
 * Created by Meruzhan on 13.04.2016.
 */
public abstract class FigureHanoi extends Figure {

    private Canvas canvas;
    private Peg start;
    private Peg end;
    private int dX = 0;
    private int dY = 0;
    private MoveMode movingMode = MoveMode.DONE;

    public FigureHanoi(int x, int y, int width, int height, Canvas canvas) {
        this(x, y, width, height, canvas, Color.blue);


    }

    public FigureHanoi(int x, int y, int width, int height, Canvas canvas, Color color) {
        super(x, y, width, height, color);
        this.canvas = canvas;
    }

    public void discMove(Peg start, Peg end) {
        this.start = start;
        this.end = end;
        discMove();

    }

    public void discMove() {
        movingMode = MoveMode.UP;
        while (canvas.isMove()) {
            switch (movingMode) {
                case UP:
                    if (getY() + getHeight() <= start.getY() + 2) {
                        movingMode = MoveMode.TO_NEXT_PEG;
                    } else {
                        dX = 0;
                        dY = -1;
                    }
                    break;
                case TO_NEXT_PEG:
                    if (getX() + (getWidth() / 2) == end.getX() + (end.getWidth() / 2) && getY() + getHeight() == end.getY()) {
                        movingMode = MoveMode.DOWN;
                        end.getDisksArray().add(start.getDisksArray().remove(start.getDisksArray().size() - 1));
                    } else {
                        ensureDirection();
                    }
                    break;
                case DOWN:
                    if (isEndDisc() || getY() + getHeight() == end.getY() + end.getHeight() - 1) {
                        movingMode = MoveMode.DONE;
                    } else {
                        dX = 0;
                        dY = 1;
                    }
                    break;
            }
            if (!canvas.isMove() || movingMode == MoveMode.DONE) {
                return;
            }
            move(dX, dY);
            canvas.repaint();
            try {
                Thread.sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }

    private void ensureDirection() {
        dX = getX() + (getWidth() / 2) > end.getX() + (end.getWidth() / 2) ? -1 : getX() + (getWidth() / 2) < end.getX() + (end.getWidth() / 2) ? 1 : 0;
        dY = getY() + getHeight() > end.getY() ? -1 : getY() + getHeight() < end.getY() ? 1 : 0;

    }

    private int sign(int x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
    }

    private boolean isEndDisc() {
        if (end.getDisksArray().size() < 2) {
            return false;
        }
        return end.getDisksArray().get(end.getDisksArray().size() - 2).getY() - 1 == getY() + getHeight();
    }

    enum MoveMode {DONE, UP, TO_NEXT_PEG, DOWN}


}
