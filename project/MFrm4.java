import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.*;
public class MFrm4 extends JFrame implements ListSelectionListener
{
	JTextField t1;
	JLabel l1,l2,l3,l4,l5;
	JButton b1,b2,b3,b4;
	ResultSet rs,rs1;
	JList li1;
Statement st;
String mynm="";
ArrayList<String> a1=new ArrayList<String>();
String xx="";
JLabel green[]=new JLabel[10];
	public MFrm4(String ab)
	{
	mynm=ab;
addWindowListener(new WindowAdapter(){
public void windowActivated(WindowEvent tt){
t1.requestFocus();
}});
setTitle(mynm);
		Container d=getContentPane();
		d.setLayout(null);	
	int heightme=150;
	for(int iii=0;iii<green.length;iii++)
	{
	green[iii]=new JLabel("*",JLabel.RIGHT);
	green[iii].setForeground(Color.GREEN);
	green[iii].setFont(new Font("Courier",Font.BOLD,32));
	green[iii].setBounds(950,heightme,45,30);
	heightme+=40;
	green[iii].setVisible(false);
	d.add(green[iii]);
	}
		l1=new JLabel(new ImageIcon("frnd.png"));
		l2=new JLabel(new ImageIcon("msg.png"));
		l3=new JLabel(new ImageIcon("lock.png"));
		l4=new JLabel("Friend List");
		l5=new JLabel(new ImageIcon("bg1.png"));
		t1=new JTextField(20);
    new TextPrompt("Search", t1);

t1.requestFocus();
		b1=new JButton(new ImageIcon("a1.jpg"));

		try	
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:mydsn","","");
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String query="select * from friendreq";
			rs1=st.executeQuery(query);
			while(rs1.next())
			{
			String field1=rs1.getString("username").trim();
			String field2=rs1.getString("friendname").trim();
			if((field1.equals(mynm) || field2.equals(mynm)) && rs1.getString("status").equalsIgnoreCase("Friend") && rs1.getString("confirm").equalsIgnoreCase("Yes"))
			{
			if(field1.equals(mynm))
			{
			if(a1.contains(field2)==false)
			a1.add(field2);
			}
			else
			{
			if(a1.contains(field1)==false)
			a1.add(field1);
			}
			}
			}
		rs1=st.executeQuery("select * from logintab where status='login'");
		int ii=0;
		for(String getmeuser:a1)
		{
		rs1.first();
		boolean flag=false;
		do
		{
		if(rs1.getString("username").equals(getmeuser))
		{
		flag=true;
		break;
		}
		}while(rs1.next());
		if(flag==true)
		{
		green[ii].setVisible(true);
		}	
		ii++;
		}	
		}catch(Exception ee)
		{
		}

		addWindowListener(new WindowAdapter(){
		public void windowActivated(WindowEvent tt){
		try{
	for(int iii=0;iii<green.length;iii++)
	{
	green[iii].setVisible(false);
	}
		rs1=st.executeQuery("select * from logintab where status='login'");
		int ii=0;
		for(String getmeuser:a1)
		{
		rs1.first();
		boolean flag=false;
		do
		{
		if(rs1.getString("username").equals(getmeuser))
		{
		flag=true;
		break;
		}
		}while(rs1.next());
		if(flag==true)
		{
		green[ii].setVisible(true);
		}	
		ii++;
		}	
		}catch(Exception ee)
		{
		}
		}});
		b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent tt){
		try
		{
String myuser="";
			rs=st.executeQuery("select * from logintab");

		rs.first();
		boolean flag=false;
		do
		{
		String pp=rs.getString("firstname")+" "+rs.getString("surname");
		if(pp.equals(t1.getText()))
		{
		myuser=rs.getString("username");
		flag=true;
		break;
		}
		}while(rs.next());
		//System.out.println(flag);		
		if(flag==false)
		{
		JOptionPane.showMessageDialog(null,"No  Record Found","MyProj",2);
		return;
		}
		else
		{
		String hh="select * from friendreq where username ='"+ mynm + "' or username='" + myuser + "'";	
		rs=st.executeQuery(hh);
		flag=false;
		while(rs.next())
		{
		String uuu=rs.getString("friendname");
		if((uuu.equals(myuser) || uuu.equals(mynm))&& rs.getString("status").equals("Friend"))
		{
		flag=true;
		break;
		}
		}
	
		mydialog1 pp=new mydialog1(mynm,myuser,flag,MFrm4.this,"MyDialog",true);
		pp.setSize(400,400);
		pp.setVisible(true);
		t1.setText("");
		}
		}catch(Exception ttt){System.out.println(ttt);}
	}});
		b2=new JButton("Friend Request");
		b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent tt){
		try
		{
		popup3 p=new popup3(mynm,MFrm4.this,"MyDialog",true);
		p.setSize(600,500);
		p.setVisible(true);


		}catch(Exception ee){}
		}});
		b3=new JButton("Messages");

		b3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent tt){
		popup2 pp=new popup2(mynm,MFrm4.this,"MyBox",true);
//JOptionPane.showMessageDialog(null,"Main","MyProj",2);

		//JOptionPane.showMessageDialog(null,xx,"MyProj",2);

		new popup1(xx,mynm,MFrm4.this,"MyProj",true);

		}});
		b4=new JButton("Logout");
		b4.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent tt){
		try
		{
		//System.out.println(mynm);
		mynm=mynm.trim();
		rs=st.executeQuery("select * from logintab");
		while(rs.next())
		{
//System.out.println("In");
			if(rs.getString("username").trim().equals(mynm))
			{
		//System.out.println("1");
			rs.updateString("status","notlogin");
		
			rs.updateRow();
		//System.out.println("12");

		break;
			}
		}
		}catch(Exception ee){System.out.println(ee);}

		dispose();
//System.exit(0);
		//new frm1();
		}});
		Font f=new Font("calibri",Font.BOLD,33);
		l4.setFont(f);


		f=new Font("calibri",Font.BOLD,24);
		t1.setFont(f);
		b2.setFont(f);
		b3.setFont(f);
		b4.setFont(f);
		    String[] items = new String[a1.size()];
             
            for(int i = 0; i <a1.size(); i++){
                items[i] = a1.get(i);
            }
li1=new JList(items);             
li1.addListSelectionListener(this);
		li1.setFont(f);
		t1.setBounds(50,30,800,30);
		b1.setBounds(850,30,46,30);

		l1.setBounds(50,120,50,40);
		b2.setBounds(100,120,220,40);
		
		l2.setBounds(50,180,50,40);
		b3.setBounds(100,180,220,40);

		l3.setBounds(50,240,50,40);
		b4.setBounds(100,240,220,40);

		l4.setBounds(1000,110,300,30);
		JScrollPane jsp11=new JScrollPane(li1);
		jsp11.setBounds(1000,150,300,500);
		l5.setBounds(0,0,1500,1000);
		d.add(t1);
		d.add(b1);

		d.add(l1);
		d.add(b2);
		d.add(jsp11);		
		d.add(l2);
		d.add(b3);

		d.add(l3);
		d.add(b4);
		
		d.add(l4);
		addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent tt){
				try
		{
		//System.out.println(mynm);
		mynm=mynm.trim();
		rs=st.executeQuery("select * from logintab");
		while(rs.next())
		{
//System.out.println("In");
			if(rs.getString("username").trim().equals(mynm))
			{
		//System.out.println("1");
			rs.updateString("status","notlogin");
		
			rs.updateRow();
		//System.out.println("12");

		break;
			}
		}
		}catch(Exception ee){System.out.println(ee);}

		dispose();
		}});
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		d.add(l5);
		setSize(1500,1000);
		setVisible(true);
	}
public void valueChanged(ListSelectionEvent tt)
{
Object b[]=li1.getSelectedValues();
 xx=(String) b[0];
new popup1(xx,mynm,this,"MyProj",true);
}

class TextPrompt extends JLabel implements FocusListener, DocumentListener {
  JTextComponent component;
  Document document;

  public TextPrompt(String text, JTextComponent component) {
    this.component = component;
    document = component.getDocument();

    setText(text);
    setFont(component.getFont());
    setBorder(new EmptyBorder(component.getInsets()));

    component.addFocusListener(this);
    document.addDocumentListener(this);

    component.add(this);
  }

  public void checkForPrompt() {
    if (document.getLength() == 0)
      setSize(component.getSize());
    else
      setSize(0, 0);
  }

  public void focusGained(FocusEvent e) {
    checkForPrompt();
  }

  public void focusLost(FocusEvent e) {
    setSize(0, 0);
  }


  public void insertUpdate(DocumentEvent e) {
    checkForPrompt();
  }

  public void removeUpdate(DocumentEvent e) {
    checkForPrompt();
  }

  public void changedUpdate(DocumentEvent e) {
  }

}


	public static void main(String args[])
	{
		MFrm4 p=new MFrm4("sds");
	}

class popup2 extends JDialog implements ListSelectionListener
{
	JList li2;
Statement st1;
ResultSet rs1;
ArrayList<String> a2=new ArrayList<String>();
String mynm="";
public popup2(String a,MFrm4 p,String d,boolean e)
{
super(p,d,e);
mynm=a;
setLayout(null);
//
		try	
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:mydsn","","");
			st1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String query="select * from messagetab1";
			rs1=st1.executeQuery(query);
			while(rs1.next())
			{
			String field1=rs1.getString("fromuser").trim();
			if(field1.equals(mynm) )
			{
			String field0=rs1.getString("ToUser");
			
			String field2=rs1.getString("MsgText");
			if(field2.length()<=50)
			field2=field2.substring(0,field2.length());
			else
			field2=field2.substring(0,50);
			field2=field2+"\t\t\t"+rs1.getString("MTime")+" "+rs1.getString("MDate");
			field0=field0+":"+field2;
			a2.add(field0);
			}
			}
			
			
		}	
		catch(Exception ee)
		{
		}

//
    String[] items = new String[a2.size()];
             
            for(int i = 0; i <a2.size(); i++){
                items[i] = a2.get(i);
            }
li2=new JList(items);             
li2.addListSelectionListener(this);
		li2.setFont(new Font("Courier",Font.BOLD,22));
JScrollPane jsp1=new JScrollPane(li2);
jsp1.setBounds(100,100,800,400);
add(jsp1);
setSize(1000,600);
setVisible(true);
}
public void valueChanged(ListSelectionEvent tt)
{
Object b[]=li2.getSelectedValues();
 xx=(String) b[0];
//JOptionPane.showMessageDialog(null,xx,"MyProj",2);
xx=xx.substring(0,xx.indexOf(':'));
//JOptionPane.showMessageDialog(null,xx,"MyProj",2);

dispose();
}

}
}
class mydialog1 extends JDialog
{
JLabel l1,l2;
JButton b1;
String a1="",b11="";
boolean c1=false;
ResultSet rs1;
JComboBox cb1;
public mydialog1(String a,String b,boolean c,MFrm4 p,String d,boolean e)
{
super(p,d,e);
this.a1=a;
this.b11=b;
this.c1=c;
cb1=new JComboBox();
		try	
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:mydsn","","");
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs1=st.executeQuery("select * from friendreq");
		}	
		catch(Exception ee)
		{
		}
cb1.addItem(b);

l2=new JLabel(new ImageIcon("search2.jpg"));
Font f=new Font("calibri",Font.BOLD,33);
		l2.setFont(f);
l2.setForeground(Color.GREEN);


l1=new JLabel("Friends Name ");
if(c1==true)
b1=new JButton("Remove Friend");
else
b1=new JButton("Add Friend");
setLayout(null);
l1.setForeground(Color.WHITE);
l2.setBounds(0,0,600,600);
l1.setBounds(20,50,150,30);cb1.setBounds(180,50,150,30);b1.setBounds(350,50,150,30);

b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
try{
if(c1==false)
{
rs1.moveToInsertRow();
rs1.updateString("username",a1);
rs1.updateString("friendname",b11);
rs1.updateString("status","Friend");
rs1.updateString("confirm","No");
rs1.insertRow();
		JOptionPane.showMessageDialog(null,"Added In Friend List","MyProj",2);
dispose();
}
else
{
rs1.first();
do
{
if(rs1.getString("username").equals(a1) && rs1.getString("friendname").equals(b11))
{
rs1.updateString("status","UnFriend");
rs1.updateString("confirm","No");
rs1.updateRow();
break;
}
}while(rs1.next());
}
}catch(Exception eeee){}
}});
add(l1);

add(cb1);
add(b1);
add(l2);
setSize(600,600);
setVisible(true);

}
}


class popup1 extends JDialog //implements ActionListener
{
JLabel l1,l2,l3;
JTextField t1;
JTextArea ta1;
JTextArea ta2;
JButton b1,b2;
String nm="";
String sendernm="";
ResultSet rs2,rs3;
Statement st;
String left="",right="";
JFileChooser fc=new JFileChooser();
javax.swing.Timer t;
String fullname="";
public  popup1(String x,String y,MFrm4 pp,String tit,boolean flag)
{
super(pp,tit,flag);
nm=x;
sendernm=y;
t=new javax.swing.Timer(2000,new dispme());
t.start();
	Container d=getContentPane();
	d.setLayout(null);

		try	
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:mydsn","","");
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String ss="select * from messagetab where FromUser='" + x + "' and ToUser='" +y + "'";
			rs2=st.executeQuery(ss);
			while(rs2.next())
			{
			left=left+rs2.getString("MDate")+"\n"+rs2.getString("MsgText")+"\n"+rs2.getString("MTime")+"\n";
			}
			rs2=st.executeQuery("select * from logintab");
			while(rs2.next())
			{
			if(rs2.getString("username").trim().equals(nm)){
			fullname=rs2.getString("FirstName")+" "+rs2.getString("SurName");
			break;
			}
			}			
			
			ss="select * from messagetab where FromUser='" + y + "' and ToUser='" +x + "'";
			rs2=st.executeQuery(ss);
			while(rs2.next())
			{
			right=right+rs2.getString("MDate")+"\n"+rs2.getString("MsgText")+"\n"+rs2.getString("MTime")+"\n";
			}

		}
		catch(Exception ee)
		{
		//System.out.println(ee);
		}	
l2=new JLabel(new ImageIcon(""));
l3=new JLabel(new ImageIcon("ms.jpg"));
	l1=new JLabel(fullname);
	t1=new JTextField(20);
	new TextPrompt("Type a message here...", t1);
t1.requestFocus();
addWindowListener(new WindowAdapter(){
public void windowActivated(WindowEvent tt){
t1.requestFocus();
}});

	ta1=new JTextArea(5,30);
	ta2=new JTextArea(5,30);
	b1=new JButton("Send");
	b2=new JButton("Image");

JScrollPane jsp1=new JScrollPane(ta1);
JScrollPane jsp2=new JScrollPane(ta2);
	
	l1.setBounds(30,30,340,25);
	jsp1.setBounds(30,75,160,200);
	jsp2.setBounds(210,75,160,200);
	t1.setBounds(30,300,280,30);
	b1.setBounds(310,300,70,30);
	b2.setBounds(380,300,70,30);
	l3.setBounds(0,0,500,400);
l2.setBounds(380,75,120,120);
ta1.setText(left);
ta2.setText(right);
left="";
right="";
ta1.setEditable(false);
ta2.setEditable(false);
	add(l1);
	add(l2);
	add(t1);
	add(jsp1);
	add(jsp2);
	add(b1);
	add(b2);
	add(l3);

b2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
try{
int kk=fc.showOpenDialog(popup1.this);
if(kk==JFileChooser.APPROVE_OPTION)
{
			rs3=st.executeQuery("select * from messagetab");

rs3.moveToInsertRow();
rs3.updateString("MsgText","Image");
rs3.updateString("FromUser",sendernm);
rs3.updateString("ToUser",nm);
java.util.GregorianCalendar gc=new java.util.GregorianCalendar();
String time=gc.get(java.util.Calendar.HOUR_OF_DAY)+":"+gc.get(java.util.Calendar.MINUTE)+":"+gc.get(java.util.Calendar.SECOND);
String dt=gc.get(java.util.Calendar.DAY_OF_MONTH)+":"+(gc.get(java.util.Calendar.MONTH)+1)+":"+gc.get(java.util.Calendar.YEAR);
rs3.updateString("ImageName",fc.getSelectedFile().getPath());
rs3.updateString("MTime",time);
rs3.updateString("MDate",dt);
rs3.insertRow();
l2.setIcon(new ImageIcon(fc.getSelectedFile().getPath()));
ta2.append(dt+"\n"+"Image"+"\n"+time);

JOptionPane.showMessageDialog(null,"Image Send","MyProj",3);
}
}catch(Exception ee){}
}
});

b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
try
{
			rs2=st.executeQuery("select * from grammertab");

boolean flag=true;			
String str=t1.getText().trim();
while(rs2.next())
{
String dog=rs2.getString("Awords");
if(str.toUpperCase().indexOf(dog.toUpperCase())>=0)
{
str=str.replace(dog," ");
}
}
t1.setText(str);

			rs3=st.executeQuery("select * from messagetab");
String time="";
String dt="";
if(flag==true)
{
rs3.moveToInsertRow();
rs3.updateString("MsgText",t1.getText());
rs3.updateString("FromUser",sendernm);
rs3.updateString("ToUser",nm);
java.util.GregorianCalendar gc=new java.util.GregorianCalendar();
time=gc.get(java.util.Calendar.HOUR_OF_DAY)+":"+gc.get(java.util.Calendar.MINUTE)+":"+gc.get(java.util.Calendar.SECOND);
dt=gc.get(java.util.Calendar.DAY_OF_MONTH)+":"+(gc.get(java.util.Calendar.MONTH)+1)+":"+gc.get(java.util.Calendar.YEAR);
rs3.updateString("ImageName","NA");
rs3.updateString("MTime",time);
rs3.updateString("MDate",dt);
rs3.insertRow();
ta2.append(dt+"\n"+t1.getText()+"\n"+time);


			rs3=st.executeQuery("select * from messagetab1");
			boolean pooja=false;
			try{
			while(rs3.next())
			{
			if(rs3.getString("fromuser").equals(sendernm) && rs3.getString("touser").equals(nm))
			{
			pooja=true;
	rs3.updateString("MsgText",t1.getText());
	//java.util.GregorianCalendar gc=new java.util.GregorianCalendar();
	 time=gc.get(java.util.Calendar.HOUR_OF_DAY)+":"+gc.get(java.util.Calendar.MINUTE)+":"+gc.get(java.util.Calendar.SECOND);
	 dt=gc.get(java.util.Calendar.DAY_OF_MONTH)+":"+(gc.get(java.util.Calendar.MONTH)+1)+":"+gc.get(java.util.Calendar.YEAR);
	rs3.updateString("ImageName","NA");
	rs3.updateString("MTime",time);
	rs3.updateString("MDate",dt);
	rs3.updateRow();
	break;
			}
			}			
			}catch(Exception eeeeeeeeeeee){}
if(pooja==false)
{
rs3.moveToInsertRow();
rs3.updateString("MsgText",t1.getText());
rs3.updateString("FromUser",sendernm);
rs3.updateString("ToUser",nm);
//java.util.GregorianCalendar gc=new java.util.GregorianCalendar();
 time=gc.get(java.util.Calendar.HOUR_OF_DAY)+":"+gc.get(java.util.Calendar.MINUTE)+":"+gc.get(java.util.Calendar.SECOND);
 dt=gc.get(java.util.Calendar.DAY_OF_MONTH)+":"+(gc.get(java.util.Calendar.MONTH)+1)+":"+gc.get(java.util.Calendar.YEAR);
rs3.updateString("ImageName","NA");
rs3.updateString("MTime",time);
rs3.updateString("MDate",dt);
rs3.insertRow();

}
JOptionPane.showMessageDialog(null,"Message Delivered Successfully...","MyProj",3);



}
else
{
JOptionPane.showMessageDialog(null,"Such Words are Not Allowed","MyProj",3);
t1.setText("");
}
t1.setText("");
}catch(Exception ee){System.out.println(ee);}
}});
	setSize(500,400);
	setLocation(450,200);
	setVisible(true);

}
class dispme implements ActionListener
{
public void actionPerformed(ActionEvent tt)
{
		try	
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:mydsn","","");
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String ss="select * from messagetab where FromUser='" + nm + "' and ToUser='" +sendernm + "'";
			rs2=st.executeQuery(ss);
			left="";
			right="";
			while(rs2.next())
			{
			left=left+rs2.getString("MDate")+"\n"+rs2.getString("MsgText")+"\n"+rs2.getString("MTime")+"\n";
			}
			

			ss="select * from messagetab where FromUser='" + sendernm + "' and ToUser='" +nm + "'";
			rs2=st.executeQuery(ss);
			while(rs2.next())
			{
			right=right+rs2.getString("MDate")+"\n"+rs2.getString("MsgText")+"\n"+rs2.getString("MTime")+"\n";
			}
ta1.setText("");
ta2.setText("");
ta1.setText(left);
ta2.setText(right);


		}
		catch(Exception ee)
		{
		System.out.println(ee);
		}	

}
}
}
class popup3 extends JDialog
{
JLabel l1,l2; 
JComboBox li1;
JRadioButton rb1,rb2;
JButton b1;
String name="";
Statement st;
ResultSet rs2;
public popup3(String nm,MFrm4 pp,String tit,boolean flag)
{
super(pp,tit,flag);
name=nm;
	Container c=getContentPane();
	c.setLayout(null);

	l1=new JLabel("Users");
	l2=new JLabel(new ImageIcon("bg.jpg"));
	li1=new JComboBox();
	rb1=new JRadioButton("Accept");
	rb2=new JRadioButton("Reject");
	ButtonGroup bg=new ButtonGroup();
	bg.add(rb1);
	bg.add(rb2);
	b1=new JButton("Submit");


		try	
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:mydsn","","");
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String ss="select * from friendreq where Friendname='" + name + "' and confirm='No'";
			rs2=st.executeQuery(ss);
			while(rs2.next())
			{
			li1.addItem(rs2.getString("username"));
			}
		}catch(Exception ee){}
	l1.setBounds(30,30,100,25);
	li1.setBounds(30,75,200,25);
	rb1.setBounds(250,75,100,25);
	rb2.setBounds(400,75,100,25);
	b1.setBounds(250,150,100,25);
	l2.setBounds(0,0,600,600);

	
	add(l1);
	add(li1);
	add(rb1);
	add(rb2);
	add(b1);
	b1.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent tt)
	{
	try
	{
			if(rb1.isSelected()==true){
			String ss="select * from friendreq where Friendname='" + name + "' and confirm='No'";
			rs2=st.executeQuery(ss);
			while(rs2.next())
			{
			if(rs2.getString("username").equals((String) li1.getSelectedItem()))
			{
			rs2.updateString("confirm","Yes");
			rs2.updateRow();
			break;
			}
			}					
}
	}catch(Exception ee){System.out.println(ee);}
	}});
add(l2);
}
}





