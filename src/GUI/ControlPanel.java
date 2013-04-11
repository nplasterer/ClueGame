package GUI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueBoard.Board;
import clueBoard.Card;
import clueBoard.ClueGame;
import clueBoard.ComputerPlayer;
import clueBoard.Player;
import clueBoard.Solution;

public class ControlPanel {

	JLabel l1, l2, l3, l4, guessValue;
	JTextField tf1, tf2, tf3, tf4;
	JButton b1, b2;
	JFrame jf;
	Random roll;
	int rollnum =0;
	private ClueGame games;
	private ComputerPlayer comp;
	private MakeAccusationPanel accPanel;

	public ControlPanel(ClueGame game){
		this.games = game;
		this.comp = null;
		jf = new JFrame();
		jf.setSize(800, 300);

		roll = new Random();

		l1 = new JLabel("Whose turn?");
		l2 = new JLabel("Roll");
		l3 = new JLabel("Guess");
		l4 = new JLabel("Response");

		tf1 = new JTextField(20);
		tf2 = new JTextField(2);
		tf3 = new JTextField(20);
		tf4 = new JTextField(20);

		b1 = new JButton("Next Player");
		b1.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e)
		{
			if(!games.isGameOver()) {
				tf1.setText(games.getCurrentPlayer().getName());
				rollnum = roll.nextInt(6) + 1;
				if(!games.getBoard().isHumanTurn()) {
					if(games.getCurrentPlayer() == games.getHuman()){
						games.humanTurn(rollnum);
					}
					else{
						games.computerTurn(rollnum);
						games.repaint();
						setGuess();
					}


					tf2.setText("" + rollnum);
				}
			}
		}
		});

		b2 = new JButton("Make An Accusation");	
		b2.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e)
		{
			if(!games.isGameOver()) {
				if(games.getBoard().isHumanTurn() && !games.getHuman().isMoved()) {
					accPanel = new MakeAccusationPanel(games);
					accPanel.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"You cannot make an accusation at this time.", "ERROR!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}});
	}

	public Container setup(Player player){
		//add correct name of the player correct roll and correct guess etc..
		tf1.setText(player.getName());
		tf1.setEditable(false);

		tf2.setText("" + rollnum);
		tf2.setEditable(false);

		tf3.setText("" + guessValue);
		tf3.setEditable(false);

		tf4.setText("Response");
		tf4.setEditable(false);

		JPanel topleft = new JPanel();
		topleft.setLayout(new FlowLayout());
		topleft.add(l1);
		topleft.add(tf1);

		JPanel die = new JPanel();
		die.setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		die.add(l2);
		die.add(tf2);

		JPanel guess = new JPanel();
		guess.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		//guess.add(l3);
		guess.add(tf3);

		JPanel response = new JPanel();
		//response.setLayout(new FlowLayout());
		response.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		//response.add(l4);
		response.add(tf4);

		Container pane = jf.getContentPane();
		GridLayout grid = new GridLayout(2, 3);
		pane.setLayout(grid);
		pane.add(topleft);
		pane.add(b1);
		pane.add(b2);
		pane.add(die);
		pane.add(guess);
		pane.add(response);

		return pane;

	}

	public void setGuess() {
		Solution guess = games.getActiveSuggestion();
		Card disprove = games.getDisproveCard();
		if(guess == null)
			tf3.setText("");
		else
			tf3.setText(guess.getPerson() + " - " + guess.getRoom() + " - " + guess.getWeapon());

		if(disprove == null)
			tf4.setText("No new clue");
		else
			tf4.setText(disprove.toString());
	}

}
