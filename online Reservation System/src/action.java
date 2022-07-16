import java.awt.event.*;
import javax.swing.JOptionPane;
public class action implements ActionListener, KeyListener {
	
    public void actionPerformed(ActionEvent e) {
    	logdb db = new logdb();
    	if (e.getSource() == LogIn.blogin) {
            try {
                if (logdb.validate(LogIn.tusername.getText().toString(), LogIn.tpassword.getText().toString())) {
                	
                    new Mainpage();
                } else {
                    // Main.ms.setText("Enter correct details");
                    JOptionPane.showMessageDialog(null, new LogIn(), "Enter correct userId & Password", 0);
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    	
    	if (e.getSource() == Mainpage.bt1_reservation) {
    		
    		new Service();
    	}
    	
        if (e.getSource() == Mainpage.bt2_cancelation) {
        	
        	new cancelation();
    	}
        
        if (e.getSource() == Mainpage.bt3_logout) {
        	
        	new LogIn();
        }
        
        if (e.getSource() == Service.sconferm) {
            try {
            	String res = db.setData(Integer.parseInt(Service.trainno.getText()), Service.trainname.getText(), Service.sStation.getText(),Service.dStation.getText(),Service.classtypr.getText(),Service.date.getText()) == 1 ? "Successfully Reserved" : "reservation Failed ";
            	int pnrNo = db.getpnr();
            	JOptionPane.showMessageDialog(null, new Service(), res+"   PNR"+pnrNo, 0);            
            } catch (Exception e1) {

                e1.printStackTrace();
            }
        }
        
        if (e.getSource() == Service.home) {
        	
        	new Mainpage();
        }
        
        if (e.getSource() == Service.reset) {
        	Service.trainno.setText("");
        	Service.trainname.setText("");
        	Service.sStation.setText("");
        	Service.dStation.setText("");
        	Service.classtypr.setText("");
        	Service.date.setText("YYYY-MM-DD");
        }
        
        if (e.getSource() == LogIn.reset) {
        	LogIn.tusername.setText("");
        	LogIn.tpassword.setText("");
        }
        
        if (e.getSource() == cancelation.conferm) {
            try {
                String res = db.cancel_reservation(cancelation.pnr.getText()) == 1 ? "Reservation is canceled:" : "Cancellation Failed ";
                cancelation.l2.setText(res);
                cancelation.pnr.setText("");
            } catch (Exception e1) {

                e1.printStackTrace();
            }  
        }
        if (e.getSource() == cancelation.home) {
        	
        	new Mainpage();
        }
     }
    
    public void keyReleased(KeyEvent e)
    {
    	logdb db = new logdb();
    	int trainno = Integer.parseInt(Service.trainno.getText());
    	String rs="";
		try {
			rs = db.gettrainname(trainno);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	Service.trainname.setText(rs);
    }
    
	public void keyTyped(KeyEvent e)
    {
    	
    }
	
    public void keyPressed(KeyEvent e)
    {
    	
    }
}
