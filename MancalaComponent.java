package mancala;

import java.awt.BorderLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class MancalaComponent extends JFrame implements ChangeListener, MouseListener
{
	private static final int WIDTH = 795;
	private static final int HEIGHT = 450;
	private MancalaBoard board;
	private MancalaModel model;
	private JButton undoButton;
	
	public MancalaComponent(DesignLayout[] designs)
	{
		setTitle("Mancala");
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		
		// get inputs from the user
		MancalaDialog selectScreen = new MancalaDialog(this, designs);
		selectScreen.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		selectScreen.display();
		
		//start a game screen
		start(selectScreen.getStoneNum(), selectScreen.getDesign());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setResizable(false);
	}
	
	public void start(int stones, DesignLayout design)
	{
		model = new MancalaModel(stones);
		board = new MancalaBoard(design);
		model.attach(this);
		board.addMouseListener(this);
		
				
		setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		
		JLabel playerLabel = new JLabel("player 1/2's turn"); // instead of string, model.getPlayer() which returns String!!
		northPanel.add(playerLabel);
		northPanel.add(Box.createHorizontalStrut(400));
		
		undoButton = new JButton("UNDO: 3");
		undoButton.addActionListener(new
				ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						System.out.println("undo clicked");						
					}					
				});
		northPanel.add(undoButton);
		
		add(northPanel, BorderLayout.NORTH);
		
		
		add(board, BorderLayout.CENTER);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		ArrayList<Shape> shape = new ArrayList<>();
		shape = board.getShape();
		for (int i = 0; i < shape.size(); i++)
		{
			if (shape.get(i).contains(e.getPoint()))
			{
				System.out.println(i + "'th pit has clicked");
				/*try
				{
					model.move(i,model.getPlayer());
					undoButton.setText("UNDO: "+ game.getUndoCount());
				}
				catch (IllegalMoveException exp)
				{
					JOptionPane.showMessageDialog(this, exp.getMessage(), "Invalid Move", JOptionPane.WARNING_MESSAGE);
				}*/
			}
		}				
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}


}
