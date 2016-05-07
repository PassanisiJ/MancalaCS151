package mancala;

import java.awt.Graphics;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JComponent;
@SuppressWarnings("serial")
public class MancalaBoard extends JComponent
{
	private DesignLayout design;
	private int[] pits;
	
	public MancalaBoard(int stones, DesignLayout design)
	{
		//sets up the user input design
		this.design = design;
		
		//generate initial number of stones give by user input
		pits = new int[14];
		for (int i=0; i<14; i++)
		{
			if (i == 6 || i == 13)
				pits[i] = 0;
			else
				pits[i] = stones;
		}
	}
	
	public void paintComponent(Graphics g)
	{
		design.redraw(g, pits, this);
	}
	
	/**
	 * returns ArrayList of pit shapes so that the View can get the information of Shapes
	 * to indicate whether the user clicks on the pit or not
	 * @return ArrayList<Shape>
	 */
	public ArrayList<Shape> getShape()
	{
		return design.getShape();
	}
	
	/**
	 * gets the array of Pit from the model and convert it to the array of int
	 * which stores number of stones for each pit index
	 * @param stones : the array of Pit
	 */
	public void setData(Pit[] stones, Player player)
	{
		pits = new int[14];
		//System.out.println("\nnew pit = ");
		for (int i=0; i<stones.length; i++)
		{
			pits[i] = stones[i].getStones();
			//System.out.print(pits[i] + " ");
		}
		design.placeLabels(pits);
		
	}
	
}
