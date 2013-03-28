package GUI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlPanel {

	JLabel l1, l2;
	JTextField tf1, tf2;
	JButton b1, b2;
	
	public ControlPanel(){
		JFrame jf = new JFrame();
		jf.setTitle("Clue Game");
		jf.setSize(800, 300);

		JLabel l1 = new JLabel("Whose turn?");
		JLabel l2 = new JLabel("Roll");
		JLabel l3 = new JLabel("Guess");
		JLabel l4 = new JLabel("Response");

		JTextField tf1 = new JTextField(20);
		JTextField tf2 = new JTextField(2);
		JTextField tf3 = new JTextField(20);
		JTextField tf4 = new JTextField(20);

		JButton b1 = new JButton("Next Player");
		JButton b2 = new JButton("Make An Accusation");

		tf1.setText("Name");
		tf1.setEditable(false);
		
		tf2.setText("5");
		tf2.setEditable(false);
		
		tf3.setText("Guess");
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
		guess.add(l3);
		guess.add(tf3);

		JPanel response = new JPanel();
		response.setLayout(new FlowLayout());
		response.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		response.add(l4);
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

		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	public static void main(String[] args) {

		// TODO, add your application code
		new ControlPanel();
	}
}
