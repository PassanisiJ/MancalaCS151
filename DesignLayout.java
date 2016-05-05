package mancala;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;


public abstract class DesignLayout
{
	private Color borderColor;
	private Color pitColorLight;
	private Color pitColorDark;
	private ArrayList<Shape> shapes = new ArrayList<>();
	
	public void redraw(Graphics g, int[] pits, MancalaBoard board)
	{
		Graphics2D g2 = (Graphics2D) g;
	    RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(45, 10, 695, 350, 30, 30);
	    
	    g2.setColor(borderColor);
	    g2.fill(roundedRectangle);	    
	    g2.draw(roundedRectangle);
	    
	    

	    g2.setColor(pitColorDark);
	    for (Shape s : shapes)
	    {		    	
	    	g2.draw(s);	    	
	    	g2.fill(s);
	    	
	    }
	    	
	    
	    
	    
		
		
	}
	
	public abstract String getName();
	
	
	public void setColor(Color bColor, Color pColorLight, Color pColorDark)
	{
		borderColor = bColor;
		pitColorLight = pColorLight;
		pitColorDark = pColorDark;
	}
	
	public void setShapes(ArrayList<Shape> pitShapes)
	{
		shapes = pitShapes;
	}
	
	public ArrayList<Shape> getShape()
	{
		return shapes;
	}

}
