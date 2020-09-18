import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class frm4 extends JFrame
{
JTable tb;
JLabel l1;
public frm4()
{
l1=new JLabel("List Of All Active Users",JLabel.CENTER);
String hd[]={"Username","FirstName","SurName","Gender"};
Object dt[][]={
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""},
		{"","","",""}
		};
tb=new JTable(dt,hd);
JScrollPane jsp1=new JScrollPane(tb);
Container d=getContentPane();
d.add("North",l1);
d.add("Center",jsp1);

	try	
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:mydsn","","");
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery("select * from logintab");
		int r=0;
		while(rs.next())
		{
		tb.setValueAt(rs.getString("username")+"",r,0);
		tb.setValueAt(rs.getString("FirstName")+"",r,1);
		tb.setValueAt(rs.getString("SurName")+"",r,2);
		tb.setValueAt(rs.getString("Gender")+"",r,3);
		r++;
		}
		}	
		catch(Exception ee)
		{
		}
	
addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent tt){
dispose();
}});
setSize(600,400);
setVisible(true);
}
}
