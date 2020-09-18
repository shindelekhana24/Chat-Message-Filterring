import java.lang.reflect.*;
class demo1
{
public static void main(String[] args)
{
try
{
Class c=Class.forName(args[0]);
Method m[]=c.getDeclaredMethods();
for(int i=0;i<m.length;i++)
{
System.out.println(m[i].toString());
}
}catch(Exception ee){}
}
}