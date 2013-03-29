import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class PeoplePanel extends JPanel{
	JComboBox person;
	JCheckBox  pp, mw, cm, ms, mg, mp;
	
	public PeoplePanel() {
		//create check boxes
		pp = new JCheckBox("Professor Plum");
		mw = new JCheckBox("Mrs. White");
		cm = new JCheckBox("Colonel Mustard");
		ms = new JCheckBox("Miss Scarlet");
		mg = new JCheckBox("Mr. Green");
		mp = new JCheckBox("Mrs. Peacock");
		
		//create drop down list
		person = new JComboBox();
		person.addItem("Unsure");
		person.addItem("Professor Plum");
		person.addItem("Mrs. White");
		person.addItem("Colonel Mustard");
		person.addItem("Miss Scarlet");
		person.addItem("Mr. Green");
		person.addItem("Mrs. Peacock");
	}
	
	public JPanel displayCheckBoxes(){
		JPanel boxes = new JPanel();
		boxes.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		boxes.add(pp);
		boxes.add(mw);
		boxes.add(cm);
		boxes.add(ms);
		boxes.add(mg);
		boxes.add(mp);
		return boxes;
		
	}
	
	public JPanel displayDropDowns(){
		JPanel drop = new JPanel();
		drop.setBorder(new TitledBorder(new EtchedBorder(), "Person Guess"));
		drop.add(person);
		return drop;
		
	}
}
