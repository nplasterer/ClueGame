package GUI;
import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class WeaponPanel extends JPanel {
	JComboBox weapon;
	JCheckBox  lp, cs, kn, re, ro, wr;

	public WeaponPanel() {
		//create check boxes
		lp = new JCheckBox("Lead Pipe");
		cs = new JCheckBox("Candlestick");
		kn = new JCheckBox("Knife");
		re = new JCheckBox("Revolver");
		ro = new JCheckBox("Rope");
		wr = new JCheckBox("Wrench");
		
		//create drop down list
		weapon = new JComboBox();
		weapon.addItem("Unsure");
		weapon.addItem("Lead Pipe");
		weapon.addItem("Candlestick");
		weapon.addItem("Knife");
		weapon.addItem("Revolver");
		weapon.addItem("Rope");
		weapon.addItem("Wrench");
		
	}
	
	public JPanel displayCheckBoxes(){
		JPanel boxes = new JPanel();
		boxes.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		boxes.add(lp);
		boxes.add(cs);
		boxes.add(kn);
		boxes.add(re);
		boxes.add(ro);
		boxes.add(wr);
		return boxes;
		
	}
	
	public JPanel displayDropDowns(){
		JPanel drop = new JPanel();
		drop.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
		drop.add(weapon);
		return drop;
		
	}
	
}
