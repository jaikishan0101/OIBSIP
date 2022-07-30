import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Atm_inteface {
	
	DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	static Connection con = null;
	
    static Connection getCon() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "root", "root");
        if (con == null) {
            System.out.println("Connection failed...........");
        }
        return con;
    }
	
    static boolean validate(String user, int password) throws Exception {
        PreparedStatement ps = getCon().prepareStatement("select * from atm_user_data where user_name = ? and password =?");
        ps.setString(1, user);
        ps.setInt(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return true;
        }
        return false;
    }
    
    int get_ac_no(String user, int password) throws Exception 
    {
    	int account_no = 0;
    	 PreparedStatement ps = getCon().prepareStatement("select * from atm_user_data where user_name = ? and password =?");
         ps.setString(1, user);
         ps.setInt(2, password);
         ResultSet rs = ps.executeQuery();

         if (rs.next()) {
        	 account_no = rs.getInt("account_no");
         }
         con.close();
		return account_no;
		
    }
    
    int get_balence(int account_no) throws  Exception
    {
    	int balance=0;
    	PreparedStatement ps = getCon().prepareStatement("select * from atm_user_data where account_no = ? ");
		ps.setInt(1, account_no);
		ResultSet rs = ps.executeQuery();
		
        while(rs.next())
        {
        	balance=rs.getInt("balance");
        }
        con.close();
		return balance;
    }
    
    void set_balence(int account_no,int balance) throws Exception
    {
    	PreparedStatement ps = getCon().prepareStatement("update atm_user_data set balance = ? where account_no = ?");
		ps.setInt(1, balance);
		ps.setInt(2,account_no);
        ps.executeUpdate();
        con.close();
    }
    
    void insert_into_transaction_history(int account_no,int amount ,String transectiontype) throws  Exception{
    	PreparedStatement ps = getCon().prepareStatement("insert into transection values(?,?,?,?)");
    	ps.setInt(1, account_no);
		ps.setInt(2,amount);
		ps.setString(3,transectiontype);
		ps.setString(4,dtf.format(now).toString());
        ps.executeUpdate();
        con.close();
    }
    
    void print_transaction_history(int account_no) throws Exception
    {
    	PreparedStatement ps = getCon().prepareStatement("select * from transection where accoun_no = ? ");
		ps.setInt(1, account_no);
		ResultSet rs = ps.executeQuery();
		int i = 1;
		System.out.println("------------------------------------------------------");
        while(rs.next())
        {
        	
        	System.out.println("|  "+i+"  |  "+rs.getInt("amount")+"  |  "+rs.getString("transectiontype")+"  |  "+rs.getString("transection_time")+"  |");
        	System.out.println("------------------------------------------------------");
        	i++;
        }
        con.close();
    }
    
	
	void withdraw(int amount,int account_no) throws  Exception
	{
		int balance=get_balence(account_no);
        
		if(amount < 0 )
		{
			System.out.println("Please enter valid amount...");
		}
		else if(balance>=amount)
		{
			System.out.println("Please wait...\nYour request in process...");
			System.out.println("Collect your cash...");
			balance = balance - amount;
			set_balence(account_no,balance);
			insert_into_transaction_history(account_no, amount, "Withdrawed");
	        
			
		}
		else  {
			System.out.println("Unsufficient balance");
		}
	}
	
	void deposit(int amount,int account_no) throws  Exception
	{
		
		int balance=get_balence(account_no);
		balance = balance + amount;
		set_balence(account_no,balance);
		insert_into_transaction_history(account_no, amount, "Deposited ");
		System.out.println("Amount: "+amount+" Deposited in  A/C NO.: "+account_no);
        
	}
	
	void transfer(int amount,int account_no,int to_account_no) throws  Exception
	{
		int balance=get_balence(account_no);
		if(balance>=amount)
		{
			balance = balance - amount;
			set_balence(account_no,balance);
			insert_into_transaction_history(account_no, amount, "Transfered");
			
			int balance1= get_balence(to_account_no);
			
	        balance1  = balance1+amount;
	        
	        set_balence(to_account_no,balance1);
	        insert_into_transaction_history(to_account_no, amount, "By A/C    ");
	        System.out.println("Amount: "+amount+" Transfered from A/C NO.: "+account_no+"to A/C NO.: "+to_account_no);
			
		}
		else {
			System.out.println("Unsufficient amount ...");
		}
	}

	public static void main(String[] args) throws Exception {
		Atm_inteface atm = new Atm_inteface();
		Scanner scan = new Scanner(System.in);
		System.out.println("\n**********WELCOME TO UCO BANK ATM SYSTEM**********");
		
        boolean flag1 =true;
        while(flag1)
        {
        	System.out.println("\n1.Login \n2.Exit");
            System.out.print("Enter Your Choice 1 or 2: ");
        	int ch1 = scan.nextInt();
            switch(ch1)
            {
            case 1:
            	System.out.println("\n\t\t logIn");
            	System.out.println("*********************************************\n");
        		System.out.println("Enter UserName");
        		String user = scan.next();
        		System.out.println("Enter Password");
        		int password = scan.nextInt();
                if(validate(user,password))
                {
                	boolean flag2 = true;
                    while (flag2)
                    {
                    	int amount;
                    	int account_no = atm.get_ac_no(user, password);
                        System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Transaction History \n5.Exit");
                        System.out.println("\nEnter Your Choice: ");
                        int ch2 = scan.nextInt();
                        switch(ch2)
                        {
                            case 1:
                            	System.out.println("\t\tWITHDRAW");
                            	System.out.println("*********************************************\n");
                            	System.out.println("Enter Amount you want to Withdraw: ");
                            	amount =scan.nextInt();
                                atm.withdraw(amount,account_no);
                                break;
                            case 2:
                            	System.out.println("\t\tDEPOSITE");
                            	System.out.println("*********************************************\n");
                            	System.out.println("Enter Amount you want to Deposite: ");
                            	amount =scan.nextInt();
                            	atm.deposit(amount,account_no);
                            	
                                break;
                            case 3:
                            	System.out.println("\t\tTRANSFER");
                            	System.out.println("*********************************************\n");
                            	System.out.println("Enter Amount you want to Transfer: ");
                            	amount =scan.nextInt();
                            	System.out.println("Enter Account No. where you want to Transfer Amount: ");
                            	int to_account_no =scan.nextInt();
                            	atm.transfer(amount,account_no, to_account_no);
                            	
                                break;
                            case 4:
                            	System.out.println("\t\tTRANSECTION HISTORY");
                            	System.out.println("*********************************************\n");
                            	atm.print_transaction_history(account_no);
                                break;
                            case 5:
                            	flag2 = false;
                                break;
                            default:
                            	System.out.println("Enter correct choice");
                            	break;
                        }
                        System.out.println("\n***************THANK YOU FOR USING US***************");  
                    }
                }
                else {
                	System.out.println("please enter correct username and password...");
                }
                break;
            case 2:
            	flag1 = false;
                break;
            default:
            	System.out.println("Enter correct choice");
            	break;
                
            }
        }
		scan.close();
	}
	

}
