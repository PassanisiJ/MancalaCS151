package mancala;

import java.awt.Graphics;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JComponent;

public class MancalaBoard extends JComponent
{
	private DesignLayout design;
	private int[] pits = {4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};
	
	public MancalaBoard(DesignLayout design)
	{
		this.design = design;
	}
	
	public void paintComponent(Graphics g)
	{
		design.redraw(g, pits, this);
	}
	
	public void setStones(int[] pitStone)
	{
		pits = pitStone;
	}
	
	public ArrayList<Shape> getShape()
	{
		return design.getShape();
	}
}
