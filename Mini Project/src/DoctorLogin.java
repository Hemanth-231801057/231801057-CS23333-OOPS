

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DoctorLogin extends JFrame implements ActionListener{
    private JLabel l1,l2,l3;
    private JButton b1,b2;
    private JPasswordField p1;
    private JCheckBox c1;
    
    DoctorLogin(){
        
        l1=new JLabel("Password");
        l1.setFont(new Font("Times New Roman",Font.PLAIN,17));
        l1.setBounds(70,250,100,30);
        add(l1);
        
        p1=new JPasswordField();
        p1.setFont(new Font("Times New Roman",Font.PLAIN,15));
        p1.setBounds(150,250,150,30);
        add(p1);
        
        c1=new JCheckBox();
        c1.setBounds(150,280,20,30);
        c1.addActionListener(this);
        add(c1);
        
        l2=new JLabel("Show Password");
        l2.setBounds(170,280,100,30);
        add(l2);
        
        b1=new JButton("Login");
        b1.setFont(new Font("Times New Roman",Font.PLAIN,17));
        b1.setBounds(60,330,120,30);
        b1.addActionListener(this);
        add(b1);
        
        b2=new JButton("Back");
        b2.setFont(new Font("Times New Roman",Font.PLAIN,17));
        b2.setBounds(210,330,120,30);
        b2.addActionListener(this);
        add(b2);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("H:/mini project/oops/Clinical-Management-System/src/src/cms/img2.png"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        l3 = new JLabel(i3);
        l3.setBounds(90,10,200,200);
        add(l3);
        
        setSize(400,450);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setTitle("Doctor Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1){
            String password = p1.getText();
            connection c = new connection();
            c.createConnection();
            String str = "select * from credential where designation = 'Doctor' and password = '"+password+"'";
            try{
                ResultSet rs = c.s.executeQuery(str);
                if(rs.next()){
                    new Doctor().setVisible(true);
                    this.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Password.");
                }
                
                c.disconnectConnection();
                c = null;
            }catch(Exception e){   
                System.out.println(e);
            }
        }
        else if(ae.getSource()==b2){
            new Login().setVisible(true);
            this.setVisible(false);
        }
        if(ae.getSource()==c1){
            if(c1.isSelected()){
                p1.setEchoChar((char)0);
            }else{
                p1.setEchoChar('*');
            }
        }
    }
    public static void main(String[] args){
        new DoctorLogin();
    }
}