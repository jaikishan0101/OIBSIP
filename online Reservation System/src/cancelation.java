import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class cancelation {
	
	JFrame cf;
	static JLabel l1;
	static JLabel l2;
	static JTextField pnr;
	static JButton conferm,home;
	cancelation(){
		cf = new JFrame("Train cencellation...");
		cf.setSize(800, 500);
    	
    	l1 = new JLabel("Enter PNR No.: ");
	    l1.setBounds(100, 55, 250, 30);
	    pnr = new JTextField(10);
	    pnr.setBounds(250,55,250, 30);
	    
	    conferm = new JButton("Conferm");
	    conferm.setBounds(250, 95, 250, 30);
	    conferm.addActionListener(new action());
	    
	    l2 = new JLabel();
	    l2.setBounds(200,135,400,30);
	    
	    home = new JButton("Home");
	    home.setBounds(5, 400, 100, 30);
	    home.addActionListener(new action());
		
		cf.add(l1);
		cf.add(pnr);
		cf.add(conferm);
		cf.add(home);
		cf.add(l2);
		
		cf.setLayout(null);
		cf.setVisible(true);
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
