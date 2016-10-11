package jjr;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Game extends Applet implements KeyListener{ 
	//NOTE: Currently using AWT cause that's what I've used before.
	//I know it's outdated as hell. The amount of graphics we
	//actually need is minimal.. If you know Swing or JavaFX 
	//and want to convert the code into one of these, be my guest.

	Song s = new Song(new File("Quasar.sm"));
	
	int left = 37;
	int down = 40;
	int up = 38;
	int right = 39;
	
	BufferedImage receptors = null;
	BufferedImage leftArrow = null;
	BufferedImage downArrow = null;
	BufferedImage upArrow = null;
	BufferedImage rightArrow = null;
	
	Graphics bufferGraphics;
	Image image;
	
	public void init() {
		try {
		    receptors = ImageIO.read(new File("receptors.png"));
		    leftArrow = ImageIO.read(new File("leftarrow.png"));
		    downArrow = ImageIO.read(new File("downarrow.png"));
		    upArrow = ImageIO.read(new File("uparrow.png"));
		    rightArrow = ImageIO.read(new File("rightarrow.png"));
		} catch (IOException e) {
		}
		image = createImage(750,1200);
		bufferGraphics = image.getGraphics();
		s.play();
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
	}
	
	public void paint(Graphics g)
	{
		bufferGraphics.clearRect(0, 0, 750, 1200);
		bufferGraphics.drawImage(receptors, 100, 150, this);
		int currentTime = (int)(System.currentTimeMillis() - s.getTrueTime());
		for (int i = 0; i < s.getNotes().getNotes().size(); i++)
		{
			Note n = s.getNotes().getNotes().get(i);
			if ((n.getTime()-s.getSongStartTime()) - currentTime < -200)
			{
				System.out.println("Miss...");
				s.getNotes().getNotes().remove(n);
			}
			else if ((n.getTime()-s.getSongStartTime()) - currentTime > 1000)
				break;
			else
			{
				if(n.getTrack() == 1)
					bufferGraphics.drawImage(leftArrow, 100, 150+((n.getTime()-s.getSongStartTime()) - currentTime), this);
				else if(n.getTrack() == 2)
					bufferGraphics.drawImage(downArrow, 250, 150+((n.getTime()-s.getSongStartTime()) - currentTime), this);
				else if(n.getTrack() == 3)
					bufferGraphics.drawImage(upArrow, 400, 150+((n.getTime()-s.getSongStartTime()) - currentTime), this);
				else if(n.getTrack() == 4)
					bufferGraphics.drawImage(rightArrow, 550, 150+((n.getTime()-s.getSongStartTime()) - currentTime), this);
			}
		}
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, 0, 0, this);
		frame(); //double buffer secondary method
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}
	
	public void frame()
	{
		while (System.currentTimeMillis() % 10 != 0) {}; //100fps
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == left)
			processKeyPress(1);
		
		if(e.getKeyCode() == down)
			processKeyPress(2);
		
		if(e.getKeyCode() == up)
			processKeyPress(3);
		
		if(e.getKeyCode() == right)
			processKeyPress(4);
		
	}
	
	public void processKeyPress(int track)
	{
		int tapTime = (int) (System.currentTimeMillis() - s.getTrueTime());
		int judgement = -1;
		for (Note n : s.getNotes().getNotes())
		{
			if (n.getTrack() == track)
			{
				int noteTime = n.getTime() - s.getSongStartTime();
				System.out.println(Math.abs(noteTime - tapTime));
				if (Math.abs(noteTime - tapTime) < 50)
				{
					judgement = 0; //"Perfect"
					System.out.println("Perfect!!"); // for debugging
					s.getNotes().getNotes().remove(n);
				}
				else if (Math.abs(noteTime - tapTime) < 100)
				{
					judgement = 1; //"Great"
					System.out.println("Great!"); //for debugging
					s.getNotes().getNotes().remove(n);
				}
				else if (Math.abs(noteTime - tapTime) < 150)
				{
					judgement = 2; //"Good"
					System.out.println("Good"); //for debugging
					s.getNotes().getNotes().remove(n);
				}
				else if (Math.abs(noteTime - tapTime) < 200)
				{
					judgement = 3; //"Bad"
					System.out.println("Bad"); //for debugging
					s.getNotes().getNotes().remove(n);
				}
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//doesn't matter, isn't used
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		//doesn't matter, isn't used
	}

}
