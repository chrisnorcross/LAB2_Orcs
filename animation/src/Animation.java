//Lab2 Completed by Christopher Norcross, Eric Allen, Noah Awad

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation extends JPanel {

    final int frameCount = 10;
    int picNum = 0;
    BufferedImage[] pics0;
    BufferedImage[] pics1;
    BufferedImage[] pics2;
    BufferedImage[] pics3;
    int xloc = 0;
    int yloc = 0;
    final int xIncr = 8;
    final int yIncr = 2;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    public int direction=0;
    //Override this JPanel's paint method to cycle through picture array and draw images
    
    public void paint(Graphics g) {
    	if(direction==0) {
    		if(xloc>frameWidth-imgWidth && yloc<frameHeight-imgHeight) {
    			direction =1;
    			picNum = (picNum + 1) % frameCount;
    			g.drawImage(pics1[picNum], xloc, yloc, Color.gray, this);
    		}
    		else if(xloc<frameWidth-imgWidth&&yloc>frameHeight-imgHeight) {
    			direction=3;
    			picNum = (picNum + 1) % frameCount;
    			g.drawImage(pics3[picNum], xloc, yloc, Color.gray, this);
    		}
    		else {
    			picNum = (picNum + 1) % frameCount;
    			g.drawImage(pics0[picNum], xloc+=xIncr, yloc+=yIncr, Color.gray, this);
    		}
		}
    	else if(direction==1) {
    		if(yloc>frameHeight-imgHeight && xloc>0) {
    			direction =2;
    			picNum = (picNum + 1) % frameCount;
        		g.drawImage(pics2[picNum], xloc, yloc, Color.gray, this);
        		
    		}
    		else if(yloc<frameHeight-imgHeight && xloc<0) {
    			direction=0;
    			picNum = (picNum + 1) % frameCount;
        		g.drawImage(pics0[picNum], xloc, yloc, Color.gray, this);
    		}
    		else {
    			picNum = (picNum + 1) % frameCount;
    			g.drawImage(pics1[picNum], xloc-=xIncr, yloc+=yIncr, Color.gray, this);
    			}
    		}
    	else if(direction==2) {
    		if(xloc<0 && yloc>0) {
    			direction =3;
    			picNum = (picNum + 1) % frameCount;
        		g.drawImage(pics3[picNum], xloc, yloc, Color.gray, this);
    			}
    		else if(xloc>0 &&yloc<0) {
    			direction=1;
    			picNum = (picNum + 1) % frameCount;
        		g.drawImage(pics1[picNum], xloc, yloc, Color.gray, this);
    		}
    		else {
    			picNum = (picNum + 1) % frameCount;
    			g.drawImage(pics2[picNum], xloc-=xIncr, yloc-=yIncr, Color.gray, this);
    			}
    		}
    	else if(direction==3) {
    		if(yloc<0 && xloc<frameWidth-imgWidth) {
    			direction =0;
    			picNum = (picNum + 1) % frameCount;
        		g.drawImage(pics0[picNum], xloc+=xIncr, yloc-=yIncr, Color.gray, this);
    			}
    		else if(xloc>frameWidth-imgWidth&&yloc>0) {
    			direction =2;
    			picNum = (picNum + 1) % frameCount;
        		g.drawImage(pics2[picNum], xloc, yloc, Color.gray, this);
    		}
    		else {
    			picNum = (picNum + 1) % frameCount;
        		g.drawImage(pics3[picNum], xloc+=xIncr, yloc-=yIncr, Color.gray, this);
    		}
    	}
    }
				
    		
    		// TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
	//Be sure that animation picture direction matches what is happening on screen.
    	

    //Make frame, loop on repaint and wait
    public static void main(String[] args) {
    	JFrame frame = new JFrame();
    	frame.getContentPane().add(new Animation());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	for(int i = 0; i < 1000; i++){
    		frame.repaint();
    		try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }

    //Constructor: get image, segment and store in array
    public Animation(){
    	BufferedImage img = createImage(0);
    	pics0 = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++) {
    		pics0[i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	}
    	BufferedImage img1 = createImage(1);
    	pics1 = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++) {
    		pics1[i] = img1.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	}
    	BufferedImage img2 = createImage(2);
    	pics2 = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++) {
    		pics2[i] = img2.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	}
    	BufferedImage img3 = createImage(3);
    	pics3 = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++) {
    		pics3[i] = img3.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	}
    }
    
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded 
    
    //Read image from file and return
    private BufferedImage createImage(int n){
    	BufferedImage bufferedImage;
    	if(n==0) {
    		try {
    			bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southeast.png"));
    			return bufferedImage;
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		return null;
    	}
    	else if(n==1) {
    		try {
    			bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southwest.png"));
    			return bufferedImage;
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		return null;
    	}
    	else if(n==2) {
    		try {
    			bufferedImage = ImageIO.read(new File("images/orc/orc_forward_northwest.png"));
    			return bufferedImage;
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		return null;
    	}
    	else if(n==3) {
    		try {
    			bufferedImage = ImageIO.read(new File("images/orc/orc_forward_northeast.png"));
    			return bufferedImage;
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    		return null;
    }
}
