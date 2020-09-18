import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class frm2 extends JFrame implements ActionListener
{
	JLabel lblFnm,lblSnm,lblUnm,lblPwd,lblGen,lblRe,lblReg,l1,l2,l3,l4,l5,l6;
	JTextField txtFnm,txtSnm,txtUnm;
	JPasswordField txtPwd,txtPwdRe;
	JRadioButton rbM,rbF;
	JCheckBox chAgree;
	JButton btnSubmit;
	ResultSet rs;

	public frm2()
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
		
		lblFnm=new JLabel("First Name");
		txtFnm=new JTextField(20);
		l1=new JLabel("*");

		lblSnm=new JLabel("Surname");
		txtSnm=new JTextField(20);
		l2=new JLabel("*");

		lblUnm=new JLabel("User ID");
		txtUnm=new JTextField(20);
		l3=new JLabel("*");
	
		lblPwd=new JLabel("Password");
		txtPwd=new JPasswordField(20);
		l4=new JLabel("*");

		lblRe=new JLabel("Re-Password");
		txtPwdRe=new JPasswordField(20);
		l5=new JLabel("*");
		txtPwd.addFocusListener(new FocusAdapter(){
		public void focusLost(FocusEvent tt){
		String xx=txtPwd.getText();
		int d=0,c=0;
		if(xx.trim().length()<8 || xx.trim().length()>20)
		{
		JOptionPane.showMessageDialog(null,"Invalid Password","MyProj",2);
		return;
		}
		for(int i=0;i<xx.length();i++)
		{
		if(Character.isLetter(xx.charAt(i))==true) c++;
		if(Character.isDigit(xx.charAt(i))==true) d++;
		}
		if(d==0 || c==0)
		{
		JOptionPane.showMessageDialog(null,"Invalid Password","MyProj",2);
		return;
		}
		}});
		lblGen=new JLabel("Gender");
		rbM=new JRadioButton("Male",true);				
		rbF=new JRadioButton("Female",false);
		l6=new JLabel("*");
		ButtonGroup bg=new ButtonGroup();
		bg.add(rbM);
		bg.add(rbF);
	
		lblReg=new JLabel("Registration Form");
		
		l6=new JLabel(new ImageIcon("praje.jpg"));
		chAgree=new JCheckBox("I Agree the above field information is correct.");
		btnSubmit=new JButton("Submit");	
	
		Font f1=new Font("calibri",Font.BOLD,55);		
		lblReg.setFont(f1);
		lblReg.setForeground(Color.RED);

		
		Font f2=new Font("calibri",Font.BOLD,12);		
		l1.setFont(f2);
		l2.setFont(f2);
		l3.setFont(f2);
		l4.setFont(f2);
		l5.setFont(f2);
		
		
		l1.setForeground(Color.RED);
		l2.setForeground(Color.RED);	
		l3.setForeground(Color.RED);	
		l4.setForeground(Color.RED);	
		l5.setForeground(Color.RED);	
		

		Font f=new Font("calibri",Font.BOLD,25);		
		lblFnm.setFont(f);
		lblSnm.setFont(f);
		lblUnm.setFont(f);
		lblPwd.setFont(f);
		lblGen.setFont(f);
		lblRe.setFont(f);
		//lblReg.setFont(f);
		btnSubmit.setFont(f);
		chAgree.setFont(f);
		rbM.setFont(f);
		rbF.setFont(f);

lblFnm.setForeground(Color.WHITE);
lblSnm.setForeground(Color.WHITE);
lblUnm.setForeground(Color.WHITE);
lblPwd.setForeground(Color.WHITE);
lblGen.setForeground(Color.WHITE);
lblRe.setForeground(Color.WHITE);
		add(lblReg);
		
		add(lblFnm);
		add(txtFnm);
		add(l1);
		
		add(lblSnm);
		add(txtSnm);
		add(l2);
	
		add(lblUnm);
		add(txtUnm);
		add(l3);

		add(lblPwd);
		add(txtPwd);
		add(l4);		

		add(lblRe);
		add(txtPwdRe);
		add(l5);

		add(lblGen);
		add(rbM);
		add(rbF);
		
		
		add(chAgree);
		add(btnSubmit);
		add(l6);

		lblReg.setBounds(400,20,500,50);
		lblFnm.setBounds(400,100,150,30);		
		txtFnm.setBounds(600,100,150,30);
		l1.setBounds(760,100,150,30);

		lblSnm.setBounds(400,160,150,30);
		txtSnm.setBounds(600,160,150,30);
		l2.setBounds(760,160,150,30);

		lblUnm.setBounds(400,230,150,30);
		txtUnm.setBounds(600,230,150,30);
		l3.setBounds(760,230,150,30);

		lblPwd.setBounds(400,290,150,30);
		txtPwd.setBounds(600,290,150,30);
		l4.setBounds(760,290,150,30);

		lblRe.setBounds(400,350,170,30);
		txtPwdRe.setBounds(600,350,150,30);
		l5.setBounds(760,350,150,30);

		lblGen.setBounds(400,410,150,30);
		rbM.setBounds(600,410,120,30);
		rbF.setBounds(750,410,120,30);
		
	
		chAgree.setBounds(400,500,600,30);		
		btnSubmit.setBounds(600,600,150,30);	
		btnSubmit.addActionListener(this);
		l6.setBounds(0,0,1500,1000);
		
	addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent tt){
		dispose();
		}});
	
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500,1000);
		setVisible(true);
txtFnm.addKeyListener(new KeyAdapter(){
public void keyPressed(KeyEvent tt){
l1.setVisible(false);
l2.setVisible(false);
l3.setVisible(false);
l4.setVisible(false);
l5.setVisible(false);
}});

txtSnm.addKeyListener(new KeyAdapter(){
public void keyPressed(KeyEvent tt){
l1.setVisible(false);
l2.setVisible(false);
l3.setVisible(false);
l4.setVisible(false);
l5.setVisible(false);
}});

txtUnm.addKeyListener(new KeyAdapter(){
public void keyPressed(KeyEvent tt){
l1.setVisible(false);
l2.setVisible(false);
l3.setVisible(false);
l4.setVisible(false);
l5.setVisible(false);
}});

txtPwd.addKeyListener(new KeyAdapter(){
public void keyPressed(KeyEvent tt){
l1.setVisible(false);
l2.setVisible(false);
l3.setVisible(false);
l4.setVisible(false);
l5.setVisible(false);
}});

txtPwdRe.addKeyListener(new KeyAdapter(){
public void keyPressed(KeyEvent tt){
l1.setVisible(false);
l2.setVisible(false);
l3.setVisible(false);
l4.setVisible(false);
l5.setVisible(false);
}});

l1.setVisible(false);
l2.setVisible(false);
l3.setVisible(false);
l4.setVisible(false);
l5.setVisible(false);
		
	}		
	public void actionPerformed(ActionEvent tt)
	{
	if(chAgree.isSelected()==false)
	return;	
	if(txtUnm.getText().length()==0)	l3.setVisible(true);
	if(txtPwd.getText().length()==0)	l4.setVisible(true);
	if(txtFnm.getText().length()==0)	l1.setVisible(true);
	if(txtSnm.getText().length()==0)	l2.setVisible(true);
	if(txtPwdRe.getText().length()==0)	l5.setVisible(true);
		

if(txtUnm.getText().length()==0 || txtSnm.getText().length()==0 || txtPwd.getText().length()==0 || txtPwdRe.getText().length()==0 || txtFnm.getText().length()==0)
	return;	
	try
		{
			boolean flag=false;
			try
			{
				rs.first();
				do
				{
					if(rs.getString("username").equalsIgnoreCase(txtUnm.getText()))
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
				txtUnm.setText("");
				return;
			}
			else
			{
			if(txtPwd.getText().trim().equals(txtPwdRe.getText().trim()) && txtPwd.getText().trim().length()>=8)
			{
	
			rs.moveToInsertRow();
			rs.updateString("username",txtUnm.getText().trim());
			rs.updateString("password",txtPwd.getText().trim());
			rs.updateString("FirstName",txtFnm.getText().trim());
			rs.updateString("SurName",txtSnm.getText().trim());
			if(rbM.isSelected()==true)
			rs.updateString("Gender","Male");
			else
			rs.updateString("Gender","Female");
			rs.updateString("status","notlogin");
			
			rs.insertRow();
			JOptionPane.showMessageDialog(null,"User Added","MyProj",2);
						
			txtUnm.setText("");
			txtPwd.setText("");
			txtFnm.setText("");
			txtSnm.setText("");
			txtPwdRe.setText("");
			chAgree.setSelected(false);		
			}
			else
			JOptionPane.showMessageDialog(null,"Password and ReEnter Password Not Equal or Length Short","MyProj",2);
			}
		}catch(Exception ttt){}
		
	}

	public static void main(String args[])
	{
		frm2 p=new frm2();
	}
}
