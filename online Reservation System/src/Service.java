import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class Service {
	
	static JFrame serviceframe;
	static JLabel l1,l2,l3,l4,l5,l6;
	static JTextField trainno,trainname,sStation,dStation,classtypr,date;
	static JButton sconferm,reset,home;
	
    Service(){
    	serviceframe = new JFrame("Train Reservation...");
    	serviceframe.setSize(800, 500);
    	
    	l1 = new JLabel("Enter Train No.: ");
	    l1.setBounds(100, 55, 250, 30);
	    trainno = new JTextField(10);
	    trainno.setBounds(250,55,250, 30);
	    trainno.addKeyListener( new action());

    	l2 = new JLabel(" Train Name: ");
	    l2.setBounds(100, 95, 250, 30);
	    trainname = new JTextField(10);
	    trainname.setBounds(250,95,250, 30);

    	l3 = new JLabel("Sourse Station: ");
	    l3.setBounds(100, 135, 250, 30);
	    sStation = new JTextField(10);
	    sStation.setBounds(250,135,250, 30);

    	l4 = new JLabel("Destination Station: ");
	    l4.setBounds(100, 175, 250, 30);
	    dStation = new JTextField(10);
	    dStation.setBounds(250,175,250, 30);

    	l5 = new JLabel("Class Type: ");
	    l5.setBounds(100, 215, 250, 30);
	    classtypr = new JTextField(10);
	    classtypr.setBounds(250,215,250, 30);
	    
	    l6 = new JLabel("Date Of Journey: ");
	    l6.setBounds(100, 255, 250, 30); 
	    date = new JTextField("YYYY-MM-DD");
	    date.setBounds(250, 255, 250, 30);
	    	    
	    sconferm = new JButton("Confirm");
	    sconferm.setBounds(250, 295, 100, 30);
	    sconferm.addActionListener(new action());
	    
	    reset = new JButton("Reset");
	    reset.setBounds(400, 295, 100, 30);
	    reset.addActionListener(new action());
	    
	    home = new JButton("Home");
	    home.setBounds(5, 400, 100, 30);
	    home.addActionListener(new action());
	    
	    serviceframe.add(l1);serviceframe.add(trainno);
	    serviceframe.add(l2);serviceframe.add(trainname);
	    serviceframe.add(l3);serviceframe.add(sStation);
	    serviceframe.add(l4);serviceframe.add(dStation);
	    serviceframe.add(l5);serviceframe.add(classtypr);
	    serviceframe.add(l6);serviceframe.add(date);
	    serviceframe.add(sconferm);serviceframe.add(reset);
	    serviceframe.add(home);
		
	    serviceframe.setLayout(null);
	    serviceframe.setVisible(true);
	    serviceframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
