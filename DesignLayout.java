package mancala;

import java.awt.Graphics;

import other.Board;

public abstract class DesignLayout implements Design
{
	public abstract void redraw(Graphics g);
	
	public abstract String getName();



}
