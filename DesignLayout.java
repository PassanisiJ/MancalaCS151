package mancala;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 * Parent of all design classes.
 * It gets the color of background, font, pit and stones from its children design classes.
 * It also gets the Shapes of pits from the design classes and draw them on the board.
 */
public abstract class DesignLayout
{
	private Color fontColor;
	private Color pitColor;
	private GradientPaint backColor;
	private Color stoneColor;
	protected Image stone;
	private ArrayList<Shape> shapes = new ArrayList<>();
	private ArrayList<JLabel> labels = new ArrayList<>();
	private ArrayList<Shape> stones = new ArrayList<>();
	
	public void redraw(Graphics g, int[] pits, MancalaBoard board)
	{
		
		Graphics2D g2 = (Graphics2D) g;
		
		//generate board
		RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(45, 10, 695, 350, 30, 30);	    
	    g2.setPaint(backColor);
	    g2.fill(roundedRectangle);	    
	    g2.draw(roundedRectangle);
	    
	    //generate labels
	    placeLabels(pits);
	    for (JLabel l : labels)
	    {
	    	board.add(l);
	    }
	    
	    //generate pits and stones	    
	    for (int i=0; i<shapes.size(); i++)
	    {
	    	Shape s = shapes.get(i);
	    	g2.setColor(pitColor);
	    	g2.draw(s);	    	
	    	g2.fill(s);

	    	g2.setStroke(new BasicStroke(4.0f));
	    	stones = placeStones(s, i, g2, board);
	    }
	    
	    
	   

	    
	    
		
		
	}
	
	/**
	 * returns name of the design
	 */
	public abstract String getName();
	
	/**
	 * set Color of font, pit, and background from a certain design
	 * @param fColor : font color
	 * @param pColor : pit color
	 * @param bColor : background color
	 * @param sColor : stone color
	 */
	public void setColor(Color fColor, GradientPaint bColor, Color pColor, Color sColor)
	{
		fontColor = fColor;
		backColor = bColor;
		pitColor = pColor;
		stoneColor = sColor;
	}
	
	public void setImage(Image marble)
	{
		stone = marble;
	}
	
	/**
	 * generate ArrayList of pit Shapes from a certain design
	 * @param pitShapes : ArrayList of pits
	 */
	public void setShapes(ArrayList<Shape> pitShapes)
	{
		shapes = pitShapes;
	}
	
	/**
	 * returns pit Shapes so that the view can access to them
	 * @return ArrayList<Shape>
	 */
	public ArrayList<Shape> getShape()
	{
		return shapes;
	}
	
	/**
	 * place labels in each pits. These labels indicate number of stones in the pit
	 * @param pitLabels : ArrayList of JLabel
	 */
	public void setLabels(ArrayList<JLabel> pitLabels)
	{
		labels = pitLabels;
	}
	
	/**
	 * places stones in corresponding pit
	 * @param pit : Shape of the pit
	 * @param index : index of pit
	 * @return s : ArrayList of stone shapes
	 */
	public ArrayList<Shape> placeStones(Shape pit, int index, Graphics2D g2, MancalaBoard board)
	{
		ArrayList<Shape> s = new ArrayList<>();
		Random rand = new Random();
		int x, y;
		int width = 25;
		int height = 25;
		double centerX = pit.getBounds().getCenterX();
		double centerY = pit.getBounds().getCenterY();
		double distX = Math.abs(pit.getBounds().getX()+10 - centerX)-10;
		double minX = centerX - distX;
		double maxX = centerX + distX-10;
		double distY = Math.abs(pit.getBounds().getY()+10 - centerY)-10;
		double minY = centerY - distY;
		double maxY = centerY + distY-10;
		
		for (int j=0; j<Integer.parseInt(labels.get(index).getText()); j++)
		{
			x = (int) (rand.nextInt((int)((maxX - minX) + 1)) + minX);
			y = (int) (rand.nextInt((int)((maxY - minY) + 1)) + minY);
			g2.drawImage(stone, x, y, width, height, board);
		}

		return s;	
	}
	
	/**
	 * place labels of number of stones on or under each pit
	 * @param stones : array of integer which stores number of stones on each pit
	 */
	public void placeLabels(int[] stones)
	{
		labels = new ArrayList<>();
		
		for (int i=0; i<7; i++)
		{
			JLabel label = new JLabel(stones[i] + "");
			if (i == 6)
			{				
				label.setBounds((int) shapes.get(i).getBounds().getX()+30, (int) shapes.get(i).getBounds().getY()+285, 50, 50);				
			}
			else
			{		
				label.setBounds((int) shapes.get(i).getBounds().getX()+30, (int) shapes.get(i).getBounds().getY()+85, 50, 50);
			}
			label.setForeground(fontColor);
			labels.add(label);
			
		}
		for (int i=7; i<14; i++)
		{			
			JLabel label = new JLabel(stones[i] + "");
			if (i == 13)
			{				
				label.setBounds((int) shapes.get(i).getBounds().getX()+30, (int) shapes.get(i).getBounds().getY()+285, 50, 50);
			}
			else
			{
				label.setBounds((int) shapes.get(i).getBounds().getX()+30, (int) shapes.get(i).getBounds().getY()-35, 50, 50);
			}
			label.setForeground(fontColor);
			labels.add(label);
		}


	}

}
