package GUI;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueBoard.Card;
import clueBoard.HumanPlayer;

public class MyCards extends JFrame {
	private JLabel name;
	private String weaponlabel, roomlabel, personlabel;
	private JTextField weapon, room, person;
	private HumanPlayer human;
	
	public MyCards() {
		
		human = new HumanPlayer();
		name = new JLabel("My Cards");
		weapon = new JTextField(10);
		room = new JTextField(10);
		person = new JTextField(10);
	}
	
	public Container setup(ArrayList<Card> cards){
		//Display correct player cards
		for(Card c: cards){
			if(c.getType() == Card.cardType.WEAPON)
				weaponlabel = (c.getCard());
			else if(c.getType() == Card.cardType.PERSON)
				personlabel = (c.getCard());
			else if (c.getType() == Card.cardType.ROOM)
				roomlabel = (c.getCard());
		}
		
		weapon.setText("" + weaponlabel);
		weapon.setEditable(false);
		
		room.setText("" + roomlabel);
		room.setEditable(false);
		
		person.setText("" + personlabel);
		person.setEditable(false);
		
		
		//Setup JPanels
		JPanel weapon2 = new JPanel();
		weapon2.setBorder(new TitledBorder(new EtchedBorder(), "Weapon"));
		weapon2.add(weapon);
		
		JPanel person2 = new JPanel();
		person2.setBorder(new TitledBorder(new EtchedBorder(), "Person"));
		person2.add(person);
		
		JPanel room2 = new JPanel();
		room2.setBorder(new TitledBorder(new EtchedBorder(), "Room"));
		room2.add(room);
		
		
		
		Container pane = getContentPane();
		GridLayout grid = new GridLayout(4, 1);
		pane.setLayout(grid);
		pane.add(name);
		pane.add(weapon2);
		pane.add(person2);
		pane.add(room2);
		
		return pane;
		
	}
	
	

}
