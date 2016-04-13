package figure;

import java.awt.*;

/**
 * Created by Meruzhan on 07.04.2016.
 */
public class Peg extends FigureHanoi {

    public Peg(int x, int y, int width, int height, FigureCanvas canvas) {
        this(x, y, width, height, canvas,Color.green);
    }

    public Peg(int x, int y, int width, int height, FigureCanvas canvas, Color color) {
        super(x, y, width, height, canvas, color);
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public boolean isBelong(int x, int y) {
        return false;
    }
}
