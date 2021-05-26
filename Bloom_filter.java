import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

class Bloom_filter extends JFrame implements ActionListener{
	
	Font fhead, comfont;
	JLabel head, luser, lbit, lnames;
	JTextField tuser;
	JTextArea lsnames;
	JTable tbit;
	JScrollPane tp, tp2;
	JButton search, insert;
	String []thead = {"Index", "Value"};
	String [][]bitarray;
	
	public Bloom_filter(){
		
		fhead = new Font("Times New Roman", Font.BOLD, 30);
		comfont = new Font("Arial", Font.BOLD, 18);
		
		head = new JLabel("Bloom Filter");
		head.setFont(fhead);
		head.setBounds(150, 20, 200, 25);
		
		luser = new JLabel("Enter Username:");
		luser.setFont(comfont);
		luser.setBounds(25, 75, 150, 15);
		
		tuser = new JTextField();
		tuser.setFont(comfont);
		tuser.setBounds(25, 110, 215, 25);
		
		search = new JButton("Search");
		search.setBounds(25, 155, 105, 25);
		
		insert = new JButton("Insert");
		insert.setBounds(135, 155, 105, 25);
		
		lnames = new JLabel("Usernames:");
		lnames.setFont(comfont);
		lnames.setBounds(25, 210, 215, 25);
		
		lsnames = new JTextArea();
		tp2 = new JScrollPane(lsnames);
		tp2.setBounds(25, 255, 215, 175);
		
		lbit = new JLabel("Bit Array:");
		lbit.setFont(comfont);
		lbit.setBounds(260, 75, 150, 15);
		
		bitarray = new String[100][2];
		
		for(int i=0; i<100; i++){
			bitarray[i][0] = Integer.toString(i);
			bitarray[i][1] = "0";
		}
		
		tbit = new JTable(bitarray, thead);
		tp = new JScrollPane(tbit);
		tp.setBounds(260, 110, 200, 320);
		
		add(head);
		add(luser);
		add(tuser);
		add(search);
		search.addActionListener(this);
		add(insert);
		insert.addActionListener(this);
		add(lnames);
		add(tp2);
		add(lbit);
		add(tp);
		
		setSize(500,500);
		setLayout(null);
		setTitle("Bloom Filter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public int hash1(String name){
		int hash = 0;
		for(int i=0; i<name.length(); i++){
			hash = (hash + (int)name.charAt(i)) % 100;
		}
		return hash;
	}
	
	public int hash2(String name){
		int hash = 3, hk;
		for(int i=0; i<name.length(); i++){
			hk = 23 - ((int)name.charAt(i) % 23);
			hash = (hash + hk) % 100;
		}
		return hash;
	}
	
	public int hash3(String name){
		int hash = 23, hk;
		for(int i=0; i<name.length(); i++){
			hk = 71 * ((int)name.charAt(i) % 71);
			hash = (hash + hk) % 100;
		}
		return hash;
	}
	
	int hash4(String name){
		int hash = 3;
		int p = 7;
		for (int i = 0; i < name.length(); i++) {
			hash += hash * 7 + name.charAt(i) * Math.pow(p, i);
			hash = hash % 100;
		}
		return hash;
	}
	
	public boolean lookup(String name){
		int one = hash1(name);
		int two = hash2(name);
		int three = hash3(name);
		int four = hash4(name);
		
		int v1 = Integer.parseInt((String)tbit.getValueAt(one, 1));
		int v2 = Integer.parseInt((String)tbit.getValueAt(two, 1));
		int v3 = Integer.parseInt((String)tbit.getValueAt(three, 1));
		int v4 = Integer.parseInt((String)tbit.getValueAt(four, 1));
		
		if(v1!=0 && v2!=0 && v3!=0 && v4!=0){
			return true;
		} else {
			return false;
		}
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == search){
			String name = tuser.getText();
			if(name.isEmpty()){
				JOptionPane.showMessageDialog(this,"Please Enter Username!");
			} else {
				if(lookup(name)){
					JOptionPane.showMessageDialog(this,"Username Present!");
				} else {
					JOptionPane.showMessageDialog(this,"Username Absent!");
				}
				tuser.setText("");
			}
		}
		
		if(e.getSource() == insert){
			String name = tuser.getText();
			
			if(name.isEmpty()){
				JOptionPane.showMessageDialog(this,"Please Enter Username!");
			} else {
				if(lookup(name)){
					JOptionPane.showMessageDialog(this,"Username Already Present!");
				} else {
					int one = hash1(name);
					int two = hash2(name);
					int three = hash3(name);
					int four = hash4(name);
					
					tbit.setValueAt("1", one, 1);
					tbit.setValueAt("1", two, 1);
					tbit.setValueAt("1", three, 1);
					tbit.setValueAt("1", four, 1);
					
					JOptionPane.showMessageDialog(this,"Username Added!");
					
					String unames = lsnames.getText();
					unames = unames + name + "\n";
					lsnames.setText(unames);
				}
				tuser.setText("");
			}
		}
	}
	
	public static void main(String []args){
		new Bloom_filter();
	}
	
}