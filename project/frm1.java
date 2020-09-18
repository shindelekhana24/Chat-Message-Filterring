import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class frm1 extends JFrame 
{
	JLabel l1,l2,l3,l4,l5;
	JTextField t1;
	JPasswordField p1;
	JButton b1,b2,b3;
	ResultSet rs;
	public frm1()
	{
		Container d=getContentPane();
		d.setLayout(null);
		try	
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:mydsn","","");
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=st.executeQuery("select * from logintab");
		}
		catch(Exception ee)
		{
		System.out.println(ee);
		}		
		addWindowListener(new WindowAdapter(){
		public void windowActivated(WindowEvent tt){
		try	
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:mydsn","","");
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=st.executeQuery("select * from logintab");
		}
		catch(Exception ee)
		{
		System.out.println(ee);
		}	
		}});
		l1=new JLabel("Chat Message Filtering",JLabel.CENTER);
		l2=new JLabel("User ID");
		l3=new JLabel("Password");
		l4=new JLabel("Create New Account?");
		l5=new JLabel(new ImageIcon("p1.jpg"));

		t1=new JTextField(20);
		p1=new JPasswordField(20);
		b1=new JButton("Login");
		b2=new JButton("New User");
		b3=new JButton("Reset");

		b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent tt){
		frm2 p=new frm2();
		}});
		b3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent tt){
		t1.setText("");
		p1.setText("");
		}});
		b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent tt){
		try
		{
		if(t1.getText().trim().equals("admin") && p1.getText().trim().equals("admin123"))
		{
		new server();
		dispose();
		return;
		}
	
		boolean flag=false;
		rs.first();
		do
		{
		if(rs.getString("username").equals(t1.getText()) && rs.getString("password").equals(p1.getText()) && rs.getString("status").equals("notlogin"))
		{
		rs.updateString("status","login");
		rs.updateRow();
		flag=true;
		break;
		}
		}while(rs.next());
		if(flag==true)
		{
		JOptionPane.showMessageDialog(null,"Login Successfull","MyProj",2);
		//frm4 p=new frm4(t1.getText());
		dispose();
		MFrm4 p=new MFrm4(t1.getText());
		}
		else
		{
		JOptionPane.showMessageDialog(null,"Login Failed or User Logged In Already","MyProj",2);
		t1.setText("");p1.setText("");
		}
		}catch(Exception ee){}
		}});


		p1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent tt){
		try
		{
		boolean flag=false;
		rs.first();
		do
		{
		if(rs.getString("username").equals(t1.getText()) && rs.getString("password").equals(p1.getText()) && rs.getString("status").equals("notlogin"))
		{
		rs.updateString("status","login");
		rs.updateRow();
		flag=true;
		break;
		}
		}while(rs.next());
		if(flag==true)
		{
		JOptionPane.showMessageDialog(null,"Login Successfull","MyProj",2);
		//frm4 p=new frm4(t1.getText());
		dispose();
		MFrm4 p=new MFrm4(t1.getText());
		}
		else
		{
		JOptionPane.showMessageDialog(null,"Login Failed or User Logged In Already","MyProj",2);
		t1.setText("");p1.setText("");
		}
		}catch(Exception ee){}
		}});



		Font f=new Font("calibri",Font.BOLD,30);

		l1.setFont(f);

		f=new Font("calibri",Font.BOLD,24);
		l2.setFont(f);
		l3.setFont(f);
		t1.setFont(f);
		p1.setFont(f);
		b1.setFont(f);
		b2.setFont(f);
		b3.setFont(f);
		

		l1.setForeground(Color.red);
		l1.setBounds(50,10,500,30);

		l2.setBounds(50,100,200,30);
		t1.setBounds(250,100,200,30);
		
		l3.setBounds(50,150,200,30);
		p1.setBounds(250,150,200,30);

		l4.setBounds(250,200,200,30);
		l4.setForeground(Color.blue);
		l5.setBounds(0,0,600,450);
		b1.setBounds(100,300,125,30);
		b2.setBounds(190,300,125,30);
		b3.setBounds(275,300,125,30);
		l4.addMouseListener(new MouseAdapter(){
		public void mousePressed(MouseEvent tt){
		new frm2();
		}});
		
		d.add(l1);
		d.add(l2);
		d.add(l3);
		d.add(t1);
		d.add(p1);
		d.add(l4);
		d.add(b1);
		//d.add(b2);
		d.add(b3);
		d.add(l5);
		
		addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent tt){
		dispose();
		}});
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,450);
		setLocation(380,150);
		setVisible(true);
	}
	public static void main(String args[])
	{
		frm1 p=new frm1();
	}
}
