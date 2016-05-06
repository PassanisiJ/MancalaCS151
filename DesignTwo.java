package mancala;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Red Design
 */
public class DesignTwo extends DesignLayout
{
	@Override
	public void redraw(Graphics g, int[] pits, MancalaBoard board)
	{
		super.setColor(Color.WHITE,new GradientPaint(100, 0, new Color(102, 0, 0), 600, 600, new Color(200, 0, 0)),
				 new Color(170, 70, 70), Color.WHITE);
		super.setShapes(makeShapes());
		
		try
		{
			stone = ImageIO.read(new File("marble2.png"));
		}
		catch (Exception e)
		{ 	stone = null;	}
		
		super.redraw(g, pits, board);	
	}

	@Override
	public String getName()
	{
		return "RED Mancala";
	}
	
	/**
	 * Stores the Shapes of pits and mancalas into the proper coordinate through an ArrayList regarding indexes.
	 * Second design(Red)
	 * @return ArrayList<Shape>
	 */
	public ArrayList<Shape> makeShapes()
	{
		ArrayList<Shape> s = new ArrayList<>();
		
		int widthP = 70;
		int heightP = 90;
		int widthM = 70;
		int heightM = 300;
		int width = 15;
		int height = 50;
		int widthCorner = 60;
		int heightCornerM = 40;
		int heightCornerP = 70;
		s.add(new RoundRectangle2D.Double(widthCorner+widthM+width, heightCornerP+heightP+height, widthP, heightP, 20, 20));//0
		s.add(new RoundRectangle2D.Double(widthCorner+widthM+width*2+widthP, heightCornerP+heightP+height, widthP, heightP, 20, 20));//1
		s.add(new RoundRectangle2D.Double(widthCorner+widthM+width*3+widthP*2, heightCornerP+heightP+height, widthP, heightP, 20, 20));//2
		s.add(new RoundRectangle2D.Double(widthCorner+widthM+width*4+widthP*3, heightCornerP+heightP+height, widthP, heightP, 20, 20));//3
		s.add(new RoundRectangle2D.Double(widthCorner+widthM+width*5+widthP*4, heightCornerP+heightP+height, widthP, heightP, 20, 20));//4
		s.add(new RoundRectangle2D.Double(widthCorner+widthM+width*6+widthP*5, heightCornerP+heightP+height, widthP, heightP, 20, 20));//5
		s.add(new RoundRectangle2D.Double(widthCorner+widthM+width*7+widthP*6, heightCornerM, widthM, heightM, 80, 80));//6		
		s.add(new RoundRectangle2D.Double(widthCorner+widthM+width*6+widthP*5, heightCornerP, widthP, heightP, 20, 20));//7
		s.add(new RoundRectangle2D.Double(widthCorner+widthM+width*5+widthP*4, heightCornerP, widthP, heightP, 20, 20));//8
		s.add(new RoundRectangle2D.Double(widthCorner+widthM+width*4+widthP*3, heightCornerP, widthP, heightP, 20, 20));//9
		s.add(new RoundRectangle2D.Double(widthCorner+widthM+width*3+widthP*2, heightCornerP, widthP, heightP, 20, 20));//10
		s.add(new RoundRectangle2D.Double(widthCorner+widthM+width*2+widthP, heightCornerP, widthP, heightP, 20, 20));//11
		s.add(new RoundRectangle2D.Double(widthCorner+widthM+width, heightCornerP, widthP, heightP, 20, 20));//12
		s.add(new RoundRectangle2D.Double(widthCorner, heightCornerM, widthM, heightM, 80, 80));//13
		
		return s;
	}
}
