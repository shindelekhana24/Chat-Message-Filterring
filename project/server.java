import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class server extends JFrame
{
JButton b1,b2;
public server()
{
Container d=getContentPane();
b1=new JButton("Display User");
b2=new JButton("Add Abuse Words");
d.setLayout(null);
b1.setBounds(100,100,400,40);
b2.setBounds(100,200,400,40);
d.add(b1);
d.add(b2);
b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
new frm4();
}});


b2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
new frm3();
}});

addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent tt){
dispose();
}});
setSize(600,400);
setVisible(true);
}
}
