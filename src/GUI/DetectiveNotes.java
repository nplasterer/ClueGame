package GUI;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class DetectiveNotes {
	
	public DetectiveNotes() {

	}
	
	public void Setup(){
		JFrame jf = new JFrame();
		WeaponPanel weapon = new WeaponPanel();
		PeoplePanel person = new PeoplePanel();
		LocationPanel room = new LocationPanel();
		jf.setTitle("Detective Notes");
		jf.setSize(450, 600);
		
		Container pane = jf.getContentPane();
		GridLayout grid = new GridLayout(3, 2);
		pane.setLayout(grid);
		pane.add(weapon.displayCheckBoxes());
		pane.add(weapon.displayDropDowns());
		pane.add(person.displayCheckBoxes());
		pane.add(person.displayDropDowns());
		pane.add(room.displayCheckBoxes());
		pane.add(room.displayDropDowns());
		
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}
