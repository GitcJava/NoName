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
    private boolean isMove;

    public FigureHanoi(int x, int y, int width, int height, Canvas canvas) {
        this(x, y, width, height, canvas, Color.blue);
    }

    public FigureHanoi(int x, int y, int width, int height, Canvas canvas, Color color) {
        super(x, y, width, height, color);
        this.canvas = canvas;
    }

    public  void discMove(Peg start, Peg end) {
        this.start = start;
        this.end = end;
        isMove =true;
        discMove();

    }

    private void  discMove( ){
        while (isMove){
            nextUp();


        }

    }

    private  void nextUp(){
        while (isMove){
            if(getY()+getHeight()<=start.getY()){
                return;
            }
               move(0,-1);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                canvas.update(canvas.getGraphics());


        }
    }


    private void nextDown(){
        while (isMove){
            if (this.getY()+this.getHeight()!=end.getY()+end.getHeight()-1||end.getEndDisc().getY()-1!=getY()+getHeight()){
                move(0,1);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                canvas.update(canvas.getGraphics());
            }
           return;
        }
    }



}
