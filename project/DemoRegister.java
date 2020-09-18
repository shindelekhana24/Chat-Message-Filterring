import java.awt.*;
import javax.swing.*;
 class DemoRegister extends JFrame
{
	JLabel lblFnm,lblSnm,lblUnm,lblPwd,lblGen,lblState;
	JTextField txtFnm,txtSnm,txtUnm,txtPwd;
	JComboBox lst;
	JRadioButton rbM,rbF;
	JCheckBox chAgree;
	JButton btnSubmit;
	
	public DemoRegister()
	{
		setLayout(null);
		lblFnm=new JLabel("First Name");
		txtFnm=new JTextField(20);

		lblSnm=new JLabel("Surname");
		txtSnm=new JTextField(20);

		lblUnm=new JLabel("User name");
		txtUnm=new JTextField(20);
	
		lblPwd=new JLabel("Password");
		txtPwd=new JTextField(20);
		
		lblGen=new JLabel("Gender");
		rbM=new JRadioButton("Male",true);				
		rbF=new JRadioButton("Female",true);

		lblState=new JLabel("State");
		lst=new JComboBox();
		lst.addItem("Maharashtra");
		lst.addItem("Gujrat");
		lst.addItem("Keral");
		lst.addItem("Madhya Pradesh");
		lst.addItem("Uttar Pradesh");
		

		chAgree=new JCheckBox("I Agree");

		btnSubmit=new JButton("Submit");

		add(lblFnm);
		add(txtFnm);

		add(lblSnm);
		add(txtSnm);
	
		add(lblUnm);
		add(txtUnm);

		add(lblPwd);
		add(txtPwd);

		add(lblGen);
		add(rbM);
		add(rbF);
		
		add(lblState);
		add(lst);
		
		add(chAgree);
		add(btnSubmit);
		
		lblFnm.setBounds(30,50,80,30);		
		txtFnm.setBounds(140,50,150,30);
		lblSnm.setBounds(30,100,80,30);
		txtSnm.setBounds(140,100,150,30);
		lblUnm.setBounds(30,150,80,30);
		txtUnm.setBounds(140,150,150,30);
		lblPwd.setBounds(30,200,80,30);
		txtPwd.setBounds(140,200,150,30);
		lblGen.setBounds(30,250,50,30);
		rbM.setBounds(140,250,80,30);
		rbF.setBounds(220,250,80,30);
		lblState.setBounds(30,300,50,30);
		lst.setBounds(140,300,100,30);
		chAgree.setBounds(30,400,90,30);		
		btnSubmit.setBounds(250,450,90,30);

	}
	public static void main(String args[])
	{	
		DemoRegister d= new DemoRegister();
		d.setVisible(true);
		d.setSize(600,600);
	}
}