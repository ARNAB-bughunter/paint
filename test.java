import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
class test{
public static void main(String [ ] args){
make obj=new make();         
  }
}
class make implements MouseMotionListener,ActionListener,MouseListener{
int x1,y1,x2,y2;	
int posx1,posy1,posx2,posy2;
JFrame  frame=new JFrame();
Container c=frame.getContentPane();
Canvas canvas;
JPanel panel=new JPanel(); 
String s1[]={"Black","Blue","Cyan","Gray","LightGray","Green","Magenta","Orange","Pink","Red","Yellow"};
String s2[]={"2","2.5","3","3.5","4","4.5","5","5.5","6","6.5","7","7.5","8","8.5","9","9.5","10"};
JComboBox pen_col=new JComboBox(s1); 
JComboBox pen_size=new JComboBox(s2); 
JLabel label1=new JLabel("Pen Color",JLabel.CENTER);
JLabel label2=new JLabel("Pen size",JLabel.CENTER);
JLabel label3=new JLabel("Erasre",JLabel.CENTER);
JLabel label4=new JLabel("Reactangel",JLabel.CENTER);
JLabel label5=new JLabel("Circle",JLabel.CENTER);
JToggleButton erase=new JToggleButton("OFF");
JToggleButton reactangel=new JToggleButton("OFF");
JToggleButton circle=new JToggleButton("OFF");
JButton clearbt=new JButton("Clear-All");
JButton save=new JButton("Save");
Graphics2D g2;
make(){
c.setLayout(null);
c.setBackground(Color.red);
frame.setVisible(true);
frame.setResizable(false);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setBounds(10,10,805,1005);
pen_col.setBounds(0,871,80,20);
pen_size.setBounds(80,871,80,20);
erase.setBounds(160,871,80,20);
reactangel.setBounds(240,871,80,20);
circle.setBounds(320,871,80,20);
clearbt.setBounds(400,871,90,20);
save.setBounds(490,871,80,20);
canvas=new  Canvas(){public void paint(Graphics g){    }   };
panel.setBounds(10,10,765,945);
panel.setBackground(Color.black);
panel.setLayout(null);
canvas.setBounds(0,0,765,870);
panel.add(canvas);
panel.add(pen_col);
panel.add(pen_size);
label1.setBounds(0,891,80,20);label1.setForeground(Color.green);
label2.setBounds(80,891,80,20);label2.setForeground(Color.green);
label3.setBounds(160,891,80,20);label3.setForeground(Color.green);
label4.setBounds(240,891,80,20);label4.setForeground(Color.green);
label5.setBounds(320,891,80,20);label5.setForeground(Color.green);
panel.add(erase);
panel.add(reactangel);
panel.add(circle);
panel.add(clearbt);
panel.add(save);
panel.add(label1);
panel.add(label2);
panel.add(label3);
panel.add(label4);
panel.add(label5);
canvas.addMouseMotionListener(this);
canvas.addMouseListener(this);
erase.addActionListener(this);
reactangel.addActionListener(this);
circle.addActionListener(this);
clearbt.addActionListener(this);
//save.addActionListener(this);
canvas.setBackground(Color.white);
c.add(panel);
         }   
        
public void mouseMoved(MouseEvent ev){
x2=ev.getX();
y2=ev.getY();
}
public void mouseDragged(MouseEvent ev){
Graphics g=canvas.getGraphics();	
g2=(Graphics2D)g;
g2.setStroke(new BasicStroke(set_width()));
g2.setColor(set_color());
x1=ev.getX();
y1=ev.getY();
if(erase.isSelected())
       erase();
else{	
canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
g2.drawLine(x1,y1,x2,y2);
}
x2=x1;
y2=y1; 
    }
public void actionPerformed(ActionEvent ac){
if(ac.getSource()==erase && erase.isSelected()){
erase.setText("ON");
reactangel.setEnabled(false);reactangel.setText("OFF");
circle.setEnabled(false);circle.setText("OFF");
}
else{
erase.setText("OFF");	
circle.setEnabled(true);reactangel.setEnabled(true);
}
if(ac.getSource()==reactangel && reactangel.isSelected())
reactangel.setText("ON");
else
reactangel.setText("OFF");	
if(ac.getSource()==circle && circle.isSelected())
circle.setText("ON");
else
circle.setText("OFF");
if(ac.getSource()==clearbt){
g2.setColor(Color.white);
g2.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
  }
if(ac.getSource()==save){
BufferedImage image=new BufferedImage(canvas.getWidth(),canvas.getHeight(),BufferedImage.TYPE_INT_RGB);
Graphics2D g3=(Graphics2D)image.getGraphics();
canvas.paint(g3);
try{
ImageIO.write(image,"png",new File("C:/Users/Arnab Naha/Desktop/gAmE/paint/output.png"));
  }catch(Exception e){}
    }   
}    
public Color set_color(){
String s1=pen_col.getSelectedItem().toString();
if(s1=="Black") return Color.black;
if(s1=="Blue") return Color.blue;
if(s1=="Cyan") return Color.cyan;
if(s1=="Gray") return Color.gray;
if(s1=="LightGray") return Color.lightGray;
if(s1=="Green") return Color.green;
if(s1=="Magenta") return Color.magenta;
if(s1=="Orange") return Color.orange;
if(s1=="Pink") return Color.pink;
if(s1=="Red") return Color.red;
if(s1=="Yellow") return Color.yellow;
return Color.black;
  }
public float set_width(){
String s2=pen_size.getSelectedItem().toString();
float f=Float.parseFloat(s2);
return f;
  }
public void erase(){	 
canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));	
g2.setColor(Color.white);
g2.fillRect(x1-3,y1-3,6,6);
  }  
public void mouseExited(MouseEvent e){}
public void mouseEntered(MouseEvent e){}
public void mouseClicked(MouseEvent e){}
public void mousePressed(MouseEvent e){
posx1=e.getX();
posy1=e.getY();
}   
public void mouseReleased(MouseEvent e){
posx2=e.getX();
posy2=e.getY();
 if(reactangel.isSelected())
			reactangel();
 if(circle.isSelected())
        circle(); 
   }  
public void reactangel(){
canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));	
int width,height,pos1,pos2;	
width=(posx1>posx2)?(posx1-posx2):(posx2-posx1);
height=(posy1>posy2)?(posy1-posy2):(posy2-posy1);
pos1=(posx1<posx2)?posx1:posx2;
pos2=(posy1<posy2)?posy1:posy2;    
g2.fillRect(pos1,pos2,width,height);
g2.drawLine(posx1,posy1,posx2,posy1);
g2.drawLine(posx2,posy1,posx2,posy2);
g2.drawLine(posx2,posy2,posx1,posy2);
g2.drawLine(posx1,posy2,posx1,posy1);
   }
public void circle(){
canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));   
int width,height,pos1,pos2; 
width=(posx1>posx2)?(posx1-posx2):(posx2-posx1);
height=(posy1>posy2)?(posy1-posy2):(posy2-posy1);
pos1=(posx1<posx2)?posx1:posx2;
pos2=(posy1<posy2)?posy1:posy2; 
g2.fillOval(pos1,pos2,width,height);
g2.drawLine(posx1,posy1,posx2,posy1);
g2.drawLine(posx2,posy1,posx2,posy2);
g2.drawLine(posx2,posy2,posx1,posy2);
g2.drawLine(posx1,posy2,posx1,posy1);
 }    
}