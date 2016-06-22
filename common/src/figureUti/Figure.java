package figureUti;

import java.awt.*;

public abstract class Figure implements Runnable {

	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;

	/**
	 * Speed of this Figure by x coordinate
	 */
	private int dX;
	/**
	 * Speed of this Figure by y coordinate
	 */
	private int dY;

	private Thread t;
	protected FigureCanvas canvas;

	private boolean isRunning;

	public Figure(int x, int y, int width, int height, FigureCanvas canvas) {
		this(x, y, width, height, canvas, Color.BLUE);
	}

	public Figure(int x, int y, int width, int height, FigureCanvas canvas, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.canvas = canvas;
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void move(int dX, int dY) {
		this.x += dX;
		this.y += dY;
	}

	public abstract void draw(Graphics g);

	public abstract boolean isBelong(int x, int y);

	@Override
	public void run() {
		while (isRunning) {
			try {
				move(dX, dY);
				Thread.sleep(50);
				canvas.repaint();
				checkBorder();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void checkBorder() {
		if (getY() + getHeight() > canvas.getHeight()) {
			dY = dY <0?dY: -dY;

		}
		if (getX() + getWidth() > canvas.getWidth()) {
			dX = dX < 0 ?  dX : -dX;

		}
		if (getX() <= 0) {
			dX = dX < 0 ?-dX : dX;

		}
		if (getY() <= 0) {
			dY = dY <0?-dY: dY;

		}
	}


	public void start() {
		t = new Thread(this);
		dX = 3;
		dY = 3;
		isRunning = true;
		t.start();

	}


}

