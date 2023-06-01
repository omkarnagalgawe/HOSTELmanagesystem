import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.lang.*;
public class Student extends JFrame implements ActionListener
{
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l,l9,l10,ld;
    JLabel an,as,ast,pin;
    JTextField t2,t3,t4,t6,t7;
    JTextField a1,a2,a3,a4;
    TextArea ta1;
    JButton b1,b2,b3;
    Choice ch,ag,ch1,ch2;
    String
            mon[]={"Jan","Feb","Mar","Apr","May","Jun","July","Aug","Sep","Oct"," Nov","Dec"};
    Connection con;
    Statement st;
    public Student()
    {
        super("Student Info");
        setup();
        addWindowListener(new WindowEventHandler());
        setVisible(true);
    }
    public void setup()
    {
        setLayout(null);
        l1=new JLabel("Student Registration Form");
        l1.setFont(new Font("Monotype Corsiva",Font.ITALIC,25));
        l1.setBounds(280,50,200,25);
        l1.setForeground(Color.red);
        setFont(new Font("Courier",Font.BOLD,15));
        l2=new JLabel("Student ID");
        l2.setBounds(70,120,100,25);
        t2=new JTextField();
        t2.setBounds(240,120,180,25);
        l3=new JLabel("Student Name");
        l3.setBounds(70,160,120,25);
        t3=new JTextField();
        t3.setBounds(240,160,200,25);
        l4=new JLabel("Father's Name");
        l4.setBounds(70,200,140,25);
        t4=new JTextField();
        t4.setBounds(240,200,200,25);
        l5=new JLabel("Age");
        l5.setBounds(70,240,160,25);
        l6=new JLabel("Address");
        l6.setBounds(70,280,160,25);
        an=new JLabel("House No");
        an.setBounds(240,300,160,25);
        a1=new JTextField();
        a1.setBounds(300,300,100,25);
        as=new JLabel("City");
        as.setBounds(420,300,160,25);
        a2=new JTextField();
        a2.setBounds(470,300,100,25);
        ast=new JLabel("State");
        ast.setBounds(240,350,160,25);
        ch2=new Choice();
        ch2.setBounds(300,350,200,25);
        ch2.add("");
        ch2.add("Maharastra");
        ch2.add("TamilNadu");
        ch2.add("Karnataka");
        pin=new JLabel("Pincode");
        pin.setBounds(420,350,160,25);
        a3=new JTextField();
        a3.setBounds(470,350,100,25);
        l7=new JLabel("Mobile");
        l7.setBounds(70,400,160,25);
        l8=new JLabel("Gender");
        l8.setBounds(500,240,200,25);
        t6=new JTextField();
        t6.setBounds(240,400,200,25);
        b1=new JButton("Save");
        b1.setBounds(200,450,100,25);
        b2=new JButton("Cancel");
        b2.setBounds(340,450,100,25);
        b3=new JButton("Clear");
        b3.setBounds(480,450,100,25);
        l=new JLabel("*");
        l.setVisible(false);
        l10=new JLabel("* Invalid Values");
        l10.setBounds(20,30,200,25);
        l10.setVisible(false);
        ld=new JLabel("Date");
        ld.setBounds(500,120,100,25);
        t7=new JTextField();
        t7.setBounds(550,150,120,25);
        ch=new Choice();
        ch.add("Male");
        ch.add("Female");
        ch.setBounds(550,260,100,25);
        ag=new Choice();
        ag.add("");
        for(int i=0;i<=100;i++)
            ag.add(""+i);
        ag.setBounds(240,240,70,25);
        l9=new JLabel("Hostel Name");
        l9.setBounds(500,180,100,25);
        ch1=new Choice();
        ch1.add("");
        ch1.add("Pcet Boy's Hostel");
        ch1.add("Pcet Girl's Hostel");
        ch1.setBounds(550,210,150,25);
        Calendar cal=Calendar.getInstance();
        String dat=cal.get(Calendar.YEAR)+"-"+mon[cal.get(Calendar.MONTH)]
                +"-"+cal.get(Calendar.DATE);
        t7.setText(""+dat);
        t7.setEditable(false);
        add(l);
        add(l1);add(l2);add(l3);add(l4);add(l5);add(l6);add(t2);
        add(t4);add(t3);add(l7);add(t6);add(an);add(a1);add(as);
        add(a2);add(ast);add(a3);add(pin);add(ch2);
        add(b1);add(b2);add(l8);add(ch);add(ag);add(l10);add(ch1);
        add(l9);add(t7);add(b3);add(ld);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        setBackground(Color.cyan);
        setSize(800,600);
    }
    public void actionPerformed(ActionEvent ae)
    {
        String sst=ae.getActionCommand();
        if(sst.equals("Save"))
        {
            String id=t2.getText();
            String nam=t3.getText();
            String fname=t4.getText();
            String age=ag.getSelectedItem();
            String hname=ch1.getSelectedItem();
            String gender=ch.getSelectedItem();
            String mob=t6.getText();
            //Double a=Double.parseDouble(id);
            String da=t7.getText();
            String no=a1.getText();
            String str=a2.getText();
            String pin=a3.getText();
            String sta=ch2.getSelectedItem();
            if(id.length()==0){
                JOptionPane.showMessageDialog(null,"invlaid ID");}
            else if(nam.length()==0){
                JOptionPane.showMessageDialog(null,"invlaid Student name number");}
            else if(fname.length()==0){
                JOptionPane.showMessageDialog(null,"invlaid Father name");}
            else if(age.length()==0){
                JOptionPane.showMessageDialog(null,"invlaid age");}
            else if(hname.length()==0){
                JOptionPane.showMessageDialog(null,"invlaid hostel name");}
            else if(gender.length()==0){
                JOptionPane.showMessageDialog(null,"invlaid gender");}
            else if(no.length()==0){
                JOptionPane.showMessageDialog(null,"invlaid Door number");}
            else if(str.length()==0){
                JOptionPane.showMessageDialog(null,"invlaid city");}
            else if(sta.length()==0){
                JOptionPane.showMessageDialog(null,"invlaid State");}
            else if(pin.length()==0){
                JOptionPane.showMessageDialog(null,"invlaid pincode");}
            else if(pin.length()!=6){
                JOptionPane.showMessageDialog(null,"pincode havaing 6 numbers");}
            else if(mob.length()!=10){
                JOptionPane.showMessageDialog(null,"invlaid Mobile number");}

            else
            {
                int n=0;
                if(n==0)
                {
                    try
                    {
                        int an=Integer.parseInt(id);
                        n=1;
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,"Enter numbers only in ID");
                    }
                }
                if(n==1)
                {
                    try
                    {
                        Long an=Long.parseLong(mob);
                        n=2;
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,"Enter numbers only in Mobile field");
                    }

                }
                if(n==2)
                {
                    int len=nam.length();
                    char s[]=nam.toCharArray();
                    for(int i=0;i<len;i++)
                    {
                        if(Character.isDigit(s[i]))
                        {
                            JOptionPane.showMessageDialog(null,"Enter text only in name field");
                            i=len;
                            n=2;
                        }
                        else
                            n=3;
                    }
                }
                if(n==3)
                {
                    int le=fname.length();
                    char s[]=fname.toCharArray();
                    for(int i=0;i<le;i++)
                    {
                        if(Character.isDigit(s[i]))
                        {
                            JOptionPane.showMessageDialog(null,"Enter text only father name field");
                            i=le;
                            n=3;
                        }
                        else
                            n=4;
                    }
                }
                if(n==4)
                {
                    try
                    {
                        int an=Integer.parseInt(pin);
                        n=5;
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,"Enter numbers only pincode");
                    }
                }
                if(n==5)
                {
                    try
                    {
                      // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Hostel","root","1234");
                        st=con.createStatement();
                        System.out.println("om");
                      st.executeUpdate("INSERT INTO student VALUES(" + id+ ", '" + nam + "', '" + fname + "', " + age + ", '" + hname + "', '" + gender + "',  " + mob + " ,' " + da +" ')");


                       st.executeUpdate("insert into address values("+ id +",'"+ no +"','"+ str +"','"+ sta +"',"+ pin +")");

                        JOptionPane.showMessageDialog(null," Record insert sucessfully");
                        con.close();
                        st.close();
                        dispose();
                        new Hostel();
                    }
                    catch(Exception e)
                    {
                        System.out.println("SqlException Caught:"+e);
                        JOptionPane.showMessageDialog(null," sql Error is occured");
                    }

                }
            }
        }
        else if(sst.equals("Cancel"))
        {
            dispose();
            Student st=new Student();
        }
        else if(sst.equals("Clear"))
        {
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t6.setText("");
            a1.setText("");
            a2.setText("");
            a3.setText("");

        }
    }
    public static void main(String s[])
    {
        Student stt=new Student();
    }
    class WindowEventHandler extends WindowAdapter
    {
        public void windowClosing(WindowEvent we)
        {
            dispose();
            new Hostel();
        }
    }
}