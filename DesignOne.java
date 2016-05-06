package mancala;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class DesignOne extends DesignLayout
{

	@Override
	public void redraw(Graphics g, int[] pits, MancalaBoard board)
	{
		gameBoard = board;
		super.setColor(Color.WHITE, new GradientPaint(20, 10, new Color(0, 0, 51), 200, 500, new Color(0, 0, 153)),
				new Color(23, 76, 126), Color.WHITE);
		super.setShapes(makeShapes());
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,200,200));
		gameBoard.add(panel);
		super.redraw(g, pits, board);
		
	}

	@Override
	public String getName()
	{
		return "BLUE Mancala";
	}
	
	/**
	 * Stores the Shapes of pits and mancalas into the proper coordinate through an ArrayList regarding indexes.
	 * First design(blue)
	 * @return ArrayList<Shape>
	 */
	public ArrayList<Shape> makeShapes()
	{
		ArrayList<Shape> s = new ArrayList<>();
		
		int widthP = 70;
		int heightP = 100;
		int widthM = 70;
		int heightM = 300;
		int width = 15;
		int height = 40;
		int widthCorner = 60;
		int heightCornerM = 40;
		int heightCornerP = 65;

		s.add(new Ellipse2D.Double(widthCorner+widthM+width, heightCornerP+heightP+height, widthP, heightP));//0
		s.add(new Ellipse2D.Double(widthCorner+widthM+width*2+widthP, heightCornerP+heightP+height, widthP, heightP));//1
		s.add(new Ellipse2D.Double(widthCorner+widthM+width*3+widthP*2, heightCornerP+heightP+height, widthP, heightP));//2
		s.add(new Ellipse2D.Double(widthCorner+widthM+width*4+widthP*3, heightCornerP+heightP+height, widthP, heightP));//3
		s.add(new Ellipse2D.Double(widthCorner+widthM+width*5+widthP*4, heightCornerP+heightP+height, widthP, heightP));//4
		s.add(new Ellipse2D.Double(widthCorner+widthM+width*6+widthP*5, heightCornerP+heightP+height, widthP, heightP));//5
		s.add(new RoundRectangle2D.Double(widthCorner+widthM+width*7+widthP*6, heightCornerM, widthM, heightM, 80, 80));//6		
		s.add(new Ellipse2D.Double(widthCorner+widthM+width*6+widthP*5, heightCornerP, widthP, heightP));//7
		s.add(new Ellipse2D.Double(widthCorner+widthM+width*5+widthP*4, heightCornerP, widthP, heightP));//8
		s.add(new Ellipse2D.Double(widthCorner+widthM+width*4+widthP*3, heightCornerP, widthP, heightP));//9
		s.add(new Ellipse2D.Double(widthCorner+widthM+width*3+widthP*2, heightCornerP, widthP, heightP));//10
		s.add(new Ellipse2D.Double(widthCorner+widthM+width*2+widthP, heightCornerP, widthP, heightP));//11
		s.add(new Ellipse2D.Double(widthCorner+widthM+width, heightCornerP, widthP, heightP));//12
		s.add(new RoundRectangle2D.Double(widthCorner, heightCornerM, widthM, heightM, 80, 80));//13
		
		return s;
	}	
}
