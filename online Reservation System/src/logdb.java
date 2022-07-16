import java.sql.*;

public class logdb {

	static Connection con = null;
	
    static Connection getCon() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ors", "root", "root");
        if (con == null) {
            System.out.println("Connection failed...........");
        }
        return con;
    }
    
    static boolean validate(String user, String password) throws Exception {
        PreparedStatement ps = getCon().prepareStatement("select * from userdata where username = ? and userpassword =?");
        ps.setString(1, user);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return true;
        }
        return false;
    }
    
    int setData(int trainno, String trainname, String sstation, String dstation, String classtype,String date) throws Exception {
        PreparedStatement ps = getCon().prepareStatement("insert into pessengerdata values(?,?,?,?,?,?,?)");
        PreparedStatement ps1 = getCon().prepareStatement("select * from pnr");
        ResultSet rs = ps1.executeQuery();
        int pnrno = 0,pnrc;
        while(rs.next())
        {
        	 pnrno=rs.getInt("pnrno");
        }
        pnrc=pnrno;
        pnrc=pnrc+1;
        rs.next();
        ps.setString(1, "PNR"+pnrc);
        ps.setInt(2, trainno);
        ps.setString(3, trainname);
        ps.setString(4, sstation);
        ps.setString(5, dstation);
        ps.setString(6, classtype);
        ps.setString(7, date);
        int res = ps.executeUpdate();
        PreparedStatement ps2 = getCon().prepareStatement("update pnr set pnrno = ? where pnrno = ?");
        ps2.setInt(1, pnrc);
        ps2.setInt(2, pnrno);
        ps2.executeUpdate();
        con.close();
        
        return res;
    }
    
    int cancel_reservation(String pnrno) throws Exception
    {
    	PreparedStatement ps3 = getCon().prepareStatement("delete from pessengerdata where pnr_no = ?");
        ps3.setString(1, pnrno);
        int res = ps3.executeUpdate();
        con.close();
        return res;
    }
    
    String gettrainname(int trainno) throws Exception
    {
    	PreparedStatement ps1 = getCon().prepareStatement("select * from traindata where train_no = ?");
    	ps1.setInt(1,trainno);
        ResultSet rs = ps1.executeQuery();
        String trainname="";
        while(rs.next())
        {
        	trainname =rs.getString("train_name");
        }
		return trainname;	
    }
    
    int getpnr() throws  Exception
    {
    	PreparedStatement ps1 = getCon().prepareStatement("select * from pnr");
        ResultSet rs = ps1.executeQuery();
        int pnrno = 0;
        while(rs.next())
        {
        	 pnrno=rs.getInt("pnrno");
        }
		return pnrno;	
    }
}

