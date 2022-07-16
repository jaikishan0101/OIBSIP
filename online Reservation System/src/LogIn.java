import javax.swing.*;
public class LogIn {

	static JFrame flogin;
	static JTextField tusername;
	static JPasswordField tpassword;
	static JLabel luser,lpass,limg;
	static JButton blogin,reset;
	LogIn()
	{
		flogin = new JFrame("User LogIn....");
		flogin.setSize(800, 500);
	
		luser = new JLabel("Enter Username: ");
	    luser.setBounds(100, 55, 350, 30);
		tusername = new JTextField(10);
		tusername.setBounds(250,55,350, 30);
		
		lpass = new JLabel("Enter Password: ");
		lpass.setBounds(100, 100, 350, 30);
		tpassword = new JPasswordField();
		tpassword.setBounds(250,100,350, 30);
		
		blogin = new JButton("LogIn");
		blogin.setBounds(250, 145, 150, 30);
		blogin.addActionListener(new action());
		
		reset = new JButton("Reset");
		reset.setBounds(450, 145, 150, 30);
		reset.addActionListener(new action());
		
		flogin.add(luser);flogin.add(tusername);
		flogin.add(lpass);flogin.add(tpassword);
		flogin.add(blogin);flogin.add(reset);
		
		flogin.setLayout(null);
		flogin.setVisible(true);
		flogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}
	public static void main(String args[]) 
	{
		new LogIn();
	}
}
