package mancala;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;


public abstract class DesignLayout
{
	private Color fontColor;
	private Color pitColor;
	private GradientPaint backColor;
	private Color stoneColor;
	protected MancalaBoard gameBoard; // remove?
	private ArrayList<Shape> shapes = new ArrayList<>();
	private ArrayList<JLabel> labels = new ArrayList<>();
	private ArrayList<Shape> stones = new ArrayList<>();
	
	public void redraw(Graphics g, int[] pits, MancalaBoard board)
	{
		
		Graphics2D g2 = (Graphics2D) g;
		
		//generate board
		gameBoard = board;
	    RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(45, 10, 695, 350, 30, 30);	    
	    g2.setPaint(backColor);
	    g2.fill(roundedRectangle);	    
	    g2.draw(roundedRectangle);
	    
	    //generate labels
	    placeLabels(pits);
	    for (JLabel l : labels)
	    {
	    	gameBoard.add(l);
	    }
	    
	    //generate pits and stones
	    
	    for (int i=0; i<shapes.size(); i++)
	    {
	    	Shape s = shapes.get(i);
	    	g2.setColor(pitColor);
	    	g2.draw(s);	    	
	    	g2.fill(s);

	    	stones = placeStones(s, i);
	    	for (Shape stone : stones)
	    	{
	    		g2.draw(stone);
	    		g2.setColor(stoneColor);
	    		g2.fill(stone);
	    	}
	    		
	    	
	    	
	    }
	    
	    
	   

	    
	    
		
		
	}
	
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
	public ArrayList<Shape> placeStones(Shape pit, int index)
	{
		ArrayList<Shape> s = new ArrayList<>();
		Random rand = new Random();
		double x, y;
		int width = 20;
		int height = 20;
		double centerX = pit.getBounds().getCenterX()-10;
		double centerY = pit.getBounds().getCenterY()-10;
		double minX = 0;
		double maxX = Math.abs(pit.getBounds().getX()+10 - centerX);
		double minY = 0;
		double maxY = Math.abs(pit.getBounds().getY()+10 - centerY);
		
		for (int j=0; j<Integer.parseInt(labels.get(index).getText()); j++)
		{
			x = rand.nextInt((int)((maxX - minX) + 1)) + minX;
			y = rand.nextInt((int)((maxY - minY) + 1)) + minY;
			Ellipse2D stone = new Ellipse2D.Double(centerX + x * Math.pow(-1, index), centerY + y * Math.pow(-1, index), width, height);
			s.add(stone);
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
