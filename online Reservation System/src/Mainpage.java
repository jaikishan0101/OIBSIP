import javax.swing.*;

public class Mainpage {
	static JFrame mainframe;
	static JButton bt1_reservation;
	static JButton bt2_cancelation;
	static JButton bt3_logout;
	Mainpage(){
	
		mainframe = new JFrame("HOME");
		mainframe.setSize(800, 500);
		
		bt1_reservation = new JButton("Train Reservation");
		bt1_reservation.setBounds(250, 70, 200, 30);
	    bt1_reservation.addActionListener(new action());
		
		bt2_cancelation = new JButton("Cancel Reservation");
		bt2_cancelation.setBounds(250, 110, 200, 30);
	    bt2_cancelation.addActionListener(new action());
		
		bt3_logout = new JButton(" LogOut");
		bt3_logout.setBounds(5, 395, 100, 30);
	    bt3_logout.addActionListener(new action());
		
		mainframe.add(bt1_reservation);
		mainframe.add(bt2_cancelation);
		mainframe.add(bt3_logout);
		
		mainframe.setLayout(null);
		mainframe.setVisible(true);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
