package figure;

import figures.Figure;

import java.awt.*;

/**
 * Created by Meruzhan on 18.04.2016.
 */
public class Border extends Figure {


    public Border(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Border(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public int getTop(){
        return getY();
    }

    public int getBotton(){
        return getY()+getHeight();
    }

    public int getLeft(){
        return getX();
    }

    public int getRight(){
        return getX()+getWidth();
    }


    @Override
    public void setHeight(int height) {
        super.setHeight(height);
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public boolean isBelong(int x, int y) {
        return x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight();
    }
}
