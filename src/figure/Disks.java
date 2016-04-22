package figure;

import java.awt.*;

public class Disks extends FigureHanoi {

    public Disks(int x, int y, int width, int height, Canvas canvas) {
        this(x, y, width, height, canvas, Color.green);
    }

    public Disks(int x, int y, int width, int height, Canvas canvas, Color color) {
        super(x, y, width, height, canvas, color);
    }

    @Override
    public void draw(Graphics g) {
       g.setColor(getColor());
        g.fillRoundRect(getX(),getY(),getWidth(),getHeight(),getHeight()/2,getHeight()/2);
    }

    @Override
    public boolean isBelong(int x, int y) {

        return x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight();
    }


}
