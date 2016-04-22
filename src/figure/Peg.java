package figure;

import figures.Figure;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Meruzhan on 07.04.2016.
 */
public class Peg extends FigureHanoi {

    private ArrayList<FigureHanoi> disks = new ArrayList<>();
    public Peg(int x, int y, int width, int height, Canvas canvas) {
        this(x, y, width, height, canvas,Color.green);
    }

    public Peg(int x, int y, int width, int height, Canvas canvas, Color color) {
        super(x, y, width, height, canvas, color);
    }


    public ArrayList<FigureHanoi> getDisksArray(){
        return disks;
    }
    public void addArray( Disks disk){
        disks.add(disk);
    }

    public FigureHanoi getEndDisc(){
        return disks.get(disks.size()-1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());

        g.fillRect(getX(),getY(),getWidth(),getHeight());

        g.fillRect((getX()-(getHeight()/2)+(getWidth()/2)),
                getY()+getHeight(),
                getHeight(),
                getWidth());

    }

    @Override
    public boolean isBelong(int x, int y) {
        return (x>=getX() && x<=getX()+getWidth() && y>=getY() && y<=getHeight()+getY())||
                ( x>=(getX()-(getHeight()/2)+(getWidth()/2)) && x <= (getX()-(getHeight()/2)+(getWidth()/2)) +  getHeight() &&
                y >= getY()+getHeight() && y<= (getY()+getHeight())+ getWidth());
    }
}
