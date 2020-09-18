import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class frm3 extends JFrame implements ActionListener
{
	JLabel l1,l2;
	JTextField t1;
	JButton b1,b2,b3,b4;
	ResultSet rs;
	public frm3()
	{
		Container d=getContentPane();
		d.setLayout(null);
		try	
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:mydsn","","");
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=st.executeQuery("select * from grammertab");
		}	
		catch(Exception ee)
		{
		}
		
		l1=new JLabel("Dictionary Addition",JLabel.CENTER);
		l2=new JLabel("Words");
		t1=new JTextField(30);
		b1=new JButton("Submit");
		b2=new JButton("Search");
		b3=new JButton("Update");
		b4=new JButton("Remove");

		Font f=new Font("Courier",Font.BOLD,24);
		l1.setFont(f);

		f=new Font("Courier",Font.BOLD,16);
		l2.setFont(f);
		t1.setFont(f);
		b1.setFont(f);
		b2.setFont(f);
		b3.setFont(f);
		b4.setFont(f);

		l1.setBounds(0,10,500,30);
		l2.setBounds(50,100,200,25);
		t1.setBounds(250,100,200,25);
		b1.setBounds(25,200,100,25);
		b2.setBounds(135,200,100,25);
		b3.setBounds(245,200,100,25);
		b4.setBounds(355,200,100,25);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

		d.add(l1);
		d.add(l2);
		d.add(t1);
		d.add(b1);
		d.add(b2);
		d.add(b3);
		d.add(b4);

	addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent tt){
		dispose();
		}});
	
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,300);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent tt)
	{
	if(tt.getSource()==b1)
	{
		try
		{
			boolean flag=false;
			try
			{
				rs.first();
				do
				{
					if(rs.getString("awords").equalsIgnoreCase(t1.getText()))
					{
						flag=true;
						break;
					}
				}
				while(rs.next());
			}
			catch(Exception ee){}
			if(flag==true)
			{
				JOptionPane.showMessageDialog(null,"Already Exist","MyProj",2);
				t1.setText("");
				return;
			}
			else
			rs.moveToInsertRow();
			rs.updateString("awords",t1.getText().trim());
			rs.insertRow();
			JOptionPane.showMessageDialog(null,"Word Added In Dictionary","MyProj",2);
		}
		catch(Exception ttt){}
	}
	else
	if(tt.getSource()==b2)
	{
	try{
	String xx=JOptionPane.showInputDialog("Enter Abuse Word");
			boolean flag=false;

				rs.first();
				do
				{
					if(rs.getString("awords").equalsIgnoreCase(xx))
					{
						t1.setText(xx);
						flag=true;
						break;
					}
				}
				while(rs.next());
			if(flag==false)
			{
				JOptionPane.showMessageDialog(null,"Abusive Word Not Exist","MyProj",2);
				t1.setText("");
				return;
			}
		
	}catch(Exception ee){}
	}
	else
	if(tt.getSource()==b3)
	{
	try{
			rs.updateString("awords",t1.getText().trim());
			rs.updateRow();
			JOptionPane.showMessageDialog(null,"Word Updated In Dictionary","MyProj",2);

	}catch(Exception ee){}

	}
	else
	if(tt.getSource()==b4)
	{
	try{
			rs.deleteRow();
			t1.setText("");
			JOptionPane.showMessageDialog(null,"Word Deleted From Dictionary","MyProj",2);



	}catch(Exception ee){}

	}
	}
	public static void main(String args[])
	{
		frm3 p=new frm3();
	}
}
