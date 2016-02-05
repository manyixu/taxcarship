package com.derun.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Photo_Flow {
	public static void main(String[] args) throws Exception{
		//源文件，必须存在，路径可选  
        File sf = new File("D:/aa.jpg");    
        //目的文件，因为要向其中写入，指定文件可以不存在，由程序生成  
        File df = new File("D:/ab.jpg");  
        new ReadWriteGra(sf,df);  
        new UseGra(df); 
	}
}
// 写入
class ReadWriteGra {  
    FileInputStream in = null;  
    FileOutputStream out = null;  
    public ReadWriteGra(File sourceFile,File destFile) throws Exception{  
        byte[] buf = new byte[1024];  
        int len = 0;  
        in = new FileInputStream(sourceFile);  
        out = new FileOutputStream(destFile,true);  
        while( (len = in.read(buf)) != -1 ){  
            out.write(buf,0,len);  
        }  
        out.close();  
    }  
}  
// 读出
class UseGra extends JFrame {  
	private static final long serialVersionUID = 1L;

	public UseGra(File picFile) throws Exception{  
  
        this.setVisible(true);  
        this.setResizable(false);  
        this.setLayout(null);  
        this.setBounds(600, 200, 400, 370);  
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
        JPanel p1 = (JPanel)this.getContentPane();  
        p1.setOpaque(false);  
        p1.setLayout(null);  
        InputStream is = new FileInputStream(picFile);  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        int b = 0;  
        while((b = is.read()) != -1){  
            baos.write(b);  
        }  
        ImageIcon image = new ImageIcon(baos.toByteArray());  
        JLabel background = new JLabel(image);  
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));  
        background.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());  
        JButton bt = new JButton("Test_Button");  
        p1.add(bt);  
        bt.setBounds(10,10,150,25);  
        validate();  
    }  
} 



//URL url=new URL("http://img0.ph.126.net/iFg5_fsrbTsdvvgJdXfAGQ==/6597701985842527750.jpg");
//HttpURLConnection conn= (HttpURLConnection)url.openConnection();
//InputStream is=conn.getInputStream();
//ImageData id=new ImageData(is);
//Image img=new Image(display,id);
//shell.setBackgroundImage(img);