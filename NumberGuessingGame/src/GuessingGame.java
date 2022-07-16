import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuessingGame implements ActionListener {
	JFrame f1;
	JLabel l1;
	static JLabel l2;
	static JButton b1;
	static int score;
	GuessingGame()
	{
		f1 = new JFrame("Number Guessing Game");
		f1.setSize(800, 500);
		
		l1 = new JLabel("Number Guessing Game....");
		l1.setBounds(200, 130, 400, 30);	
		l1.setFont(new Font("Serif",Font.ITALIC,35));
		l1.setForeground(Color.BLUE);
		
		b1 = new JButton("START");
		b1.setBounds(310, 190, 150, 30);
		b1.addActionListener(this);
		
		l2 = new JLabel();
		l2.setBounds(280, 250, 400, 30);	
		l2.setFont(new Font("Serif",Font.ITALIC,35));
		l2.setForeground(Color.RED);
		
		
		f1.add(l1);
		f1.add(b1);
		f1.add(l2);
		
		f1.setLayout(null);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
		   int computerNumber=(int)(Math.random()*100+1);
		   score=50;
	       int userAnswer=0;
	       System.out.println("The Correct Guess Would Be :"+computerNumber);
	       int count=0;
	       int countLimit=4;
	       JOptionPane.showMessageDialog(null , "You only have 5 chances");
	       
	       while(userAnswer != computerNumber)
	       {
	           if(count<=countLimit)
	           {
	           String response=JOptionPane.showInputDialog(null, " Enter a Number between 1 and 100","Guessing Game",3);
	           userAnswer=Integer.parseInt(response);
	           count++;
	           JOptionPane.showMessageDialog(null, " "+determiningGuess(userAnswer,computerNumber, count));
	           }
	           else {
	        	   break;
	           }
	       }
	       
	     
	}
	
	public static String determiningGuess(int userAnswer, int computerNumber, int count)
    {
		if(count==5)
	       {  
	    	   l2.setText("Your Score: "+0);
	    	   b1.setText("Start again");
	       }
        if(userAnswer <=0 || userAnswer>100)
        {
        	score-=10;
            return " Your guess is invalid";
        }else if(userAnswer == computerNumber)
        {
        	l2.setText("Your Score: "+score);
        	b1.setText("Start again");
            return" CORRECT!! \n Total Guesses :"+count;
        }
        else if(userAnswer> computerNumber)
        {
        	score-=10;
            return " Your guess is TOO HIGH, try again.\n Try Number:"+count;
        }
        else if(userAnswer< computerNumber)
        {
        	score-=10;
            return " Your guess is TOO LOW, try again. \n Try Number :"+count;
        }
        else 
        {
        	score-=10;
            return " Your guess is INCORRECT \n Try Again :"; 
        }
    }    

	public static void main(String[] args) {
		new GuessingGame();
	}

}
