package figure;

import figures.Figure;

import java.awt.*;

/**
 * Created by Meruzhan on 13.04.2016.
 */
public abstract class FigureHanoi extends Figure implements Runnable {

    private FigureCanvas canvas;

    public FigureHanoi(int x, int y, int width, int height, FigureCanvas canvas) {
        this(x, y, width, height, canvas, Color.blue);
    }

    public FigureHanoi(int x, int y, int width, int height, FigureCanvas canvas, Color color) {
        super(x, y, width, height, color);
        this.canvas = canvas;
    }

    @Override
    public void run(){

    }

}
