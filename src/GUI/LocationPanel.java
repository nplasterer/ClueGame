import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class LocationPanel extends JPanel{
	JComboBox room;
	JCheckBox  bb, ll, kk, ss, hh, cc, rr, dd, oo;
	
	public LocationPanel() {
		//create check boxes
		bb = new JCheckBox("Ballroom");
		ll = new JCheckBox("Library");
		kk = new JCheckBox("Kitchen");
		ss = new JCheckBox("Study");
		hh = new JCheckBox("Hall");
		cc = new JCheckBox("Conservatory");
		rr = new JCheckBox("Billard Room");
		dd = new JCheckBox("Dining Room");
		oo = new JCheckBox("Lounge");
		
		//create drop down list
		room = new JComboBox();
		room.addItem("Unsure");
		room.addItem("Ballroom");
		room.addItem("Library");
		room.addItem("Kitchen");
		room.addItem("Study");
		room.addItem("Hall");
		room.addItem("Conservatory");
		room.addItem("Billard Room");
		room.addItem("Dining Room");
		room.addItem("Lounge");
	}
	
	public JPanel displayCheckBoxes(){
		JPanel boxes = new JPanel();
		boxes.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		boxes.add(bb);
		boxes.add(ll);
		boxes.add(kk);
		boxes.add(ss);
		boxes.add(hh);
		boxes.add(cc);
		boxes.add(rr);
		boxes.add(dd);
		boxes.add(oo);
		return boxes;
		
	}
	
	public JPanel displayDropDowns(){
		JPanel drop = new JPanel();
		drop.setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
		drop.add(room);
		return drop;
		
	}
}
