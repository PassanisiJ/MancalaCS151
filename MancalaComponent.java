package mancala;

import java.awt.BorderLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * View and Controller for the Mancala board game.
 * ActionListener and MouseListener contributes in Controller part,
 * called by undo button and mouse input respectively.
 * ChangeListener is invoked when any changes of Model are detected.
 * It changes number of stones in each pit or remaining undo counts
 * and updates the change on the screen
 */
@SuppressWarnings("serial")
public class MancalaComponent extends JFrame implements ChangeListener, MouseListener, ActionListener
{
	private static final int WIDTH = 795;
	private static final int HEIGHT = 450;
	private MancalaBoard board;
	private MancalaModel model;
	private JLabel undoLabel;
	private JLabel playerLabel;
	private Pit[] pits;
	private Player player = Player.Player1;
	
	public MancalaComponent(DesignLayout[] designs) throws IOException
	{
		setTitle("Mancala");
		setSize(WIDTH, HEIGHT);
		
		// get inputs from the user
		MancalaDialog selectScreen = new MancalaDialog(this, designs);
		selectScreen.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		selectScreen.display();
		
		//start a game screen
		start(selectScreen.getStoneNum(), selectScreen.getDesign());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setResizable(false);
		setVisible(true);
	}
	
	/**
	 * generates model with input number of stones and sets up a design with input design
	 * @param stones
	 * @param design
	 */
	public void start(int stones, DesignLayout design)
	{
		model = new MancalaModel(stones);
		board = new MancalaBoard(stones, design);
		model.addListener(this);
		board.addMouseListener(this);	
		pits = model.getPits();
				
		setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		
		playerLabel = new JLabel(player + "'s Turn");
		northPanel.add(playerLabel);
		northPanel.add(Box.createHorizontalStrut(400));
		
		JButton undoButton = new JButton("UNDO");
		undoButton.addActionListener(this);
		northPanel.add(undoButton);
		
		undoLabel = new JLabel("P1 : 3,  P2 : 3 left");
		northPanel.add(undoLabel);
		
		add(northPanel, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
	}
	
	@Override
	public void stateChanged(ChangeEvent e)
	{
		//stores number of stones into the design
		board.setData(pits, player);
		board.removeAll();
		board.repaint();
		
		//indicate when the game is over
		int gameOver = model.checkGameOver();
		if (gameOver == 3)
		{
			playerLabel.setText("DRAW");
			JOptionPane.showMessageDialog(this, "Game ended as a draw");
		}
		else if (gameOver == 1 || gameOver ==2)
		{
			playerLabel.setText("WINNER: " + "Player" + gameOver);
			JOptionPane.showMessageDialog(this, "Player" + gameOver + " is a winner!");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{			
			player = model.getPreviousPlayer();
			
			//undo
			model.undo(player);
			
			//refresh labels
			playerLabel.setText(player + "'s Turn");
			undoLabel.setText("P1 : " + model.getUndoCount(Player.Player1) + ", P2 : " + model.getUndoCount(Player.Player2) + " left");
			
			//get updated pits from the model
			pits = model.getPits();

			//update changes
			board.setData(pits, player);

		}
		catch (NoMoreUndosException e1)
		{
			JOptionPane.showMessageDialog(this, "You don't have any undo left");
			if (player == Player.Player1)
				player = Player.Player2;
			else
				player = Player.Player1;
			
		}
		catch (NoMoveToUndoException e1)
		{
			JOptionPane.showMessageDialog(this, "You can't undo now");
		}
		finally
		{
			//repaint after message pops up
			board.removeAll();
			board.repaint();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		ArrayList<Shape> shape = new ArrayList<>();
		shape = board.getShape();
		for (int i = 0; i < shape.size(); i++)
		{
			//checks which index of pit has pressed
			if (shape.get(i).contains(e.getPoint()))
			{
				try
				{
					if (model.move(i,player) == 1)
						player = Player.Player1;
					else
						player = Player.Player2;
					playerLabel.setText(player + "'s Turn");
					undoLabel.setText("P1 : " + model.getUndoCount(Player.Player1) + ", P2 : " + model.getUndoCount(Player.Player2) + " left");
				}
				catch (IllegalMoveException e1)
				{
					JOptionPane.showMessageDialog(this, "Invalid Move", "Warning", JOptionPane.INFORMATION_MESSAGE);
				} catch (EmptyPitException e2)
				{
					JOptionPane.showMessageDialog(this, "Select a pit with at lease 1 stone", "Warning", JOptionPane.INFORMATION_MESSAGE);
				}
				finally
				{
					//repaint after message pops up
					board.removeAll();
					board.repaint();
				}
			}
		}				
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}


}
