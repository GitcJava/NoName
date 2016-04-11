package figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class FigureCanvas extends JPanel {
	private ArrayList<Figure> figures = new ArrayList<>();

	private int mX;
	private int mY;

	private boolean isSelected;

	public FigureCanvas() {


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

	public void add(Figure figure) {

	}


	public Figure getSelected() {
		if (isSelected) {
			int size = figures.size();
			return size > 0 ? (Figure)figures.get(size - 1) : null;
		}
		return null;
	}

	public void start() {

	}

	private void mouseDraggedPerformed(MouseEvent e) {
		if (isSelected) {
			getSelected().move(e.getX() - mX, e.getY() - mY);
			repaint();
		}
		mX = e.getX();
		mY = e.getY();
	}

	private void mousePressedPerformed(MouseEvent e) {
		mX = e.getX();
		mY = e.getY();
		select(mX, mY);
		update(getGraphics());

	}

	private void select(int x, int y) {
		int size = figures.size();
		isSelected = false;
		for (int i = size - 1; i >= 0; i--) {
			Figure f = (Figure)figures.get(i);
			if (f.isBelong(x, y)) {
				figures.remove(f);
				figures.add(f);
				isSelected = true;
				return;
			}
		}

	}

	public void pause(){


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

	}

}
