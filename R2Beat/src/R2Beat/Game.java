package r2beat;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Game extends Applet implements KeyListener{ 
	//NOTE: Currently using AWT cause that's what I've used before.
	//I know it's outdated as hell. The amount of graphics we
	//actually need is minimal.. If you know Swing or JavaFX 
	//and want to convert the code into one of these, be my guest.

	Song s;
	
	int left = 65;
	int down = 83;
	int up = 75;//87 or 75
	int right = 76;//68 or 76
	
	BufferedImage receptors = null;
	BufferedImage leftArrow = null;
	BufferedImage downArrow = null;
	BufferedImage upArrow = null;
	BufferedImage rightArrow = null;
	
	Graphics bufferGraphics;
	Image image;
	
	int life = 100;
	double score = 0.0;
	double maxScore;
	
	int perfs = 0;
	int greats = 0;
	int goods = 0;
	int bads = 0;
	int misses = 0;
	
	int combo = 0;
	int maxCombo = 0;
	
	int judgement = 9;
	
	boolean fail = false;
	
	String playerName = "Michael Marino";
	
	boolean songOver = false;
	
	DecimalFormat d = new DecimalFormat("#.##%");
	
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
		Font currentFont = bufferGraphics.getFont();
		Font newFont = currentFont.deriveFont(currentFont.getSize() * 2F);
		bufferGraphics.setFont(newFont);
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
		s = new Song(new File("Extratone Pirates.sm"), new File("Extratone Pirates.mp3"));
		s.play();
		maxScore = s.getNotes().getNotes().size() * 2.0;
	}
	
	public void paint(Graphics g)
	{
		bufferGraphics.setFont(bufferGraphics.getFont().deriveFont(bufferGraphics.getFont().getSize() * 2F));
		bufferGraphics.clearRect(0, 0, 750, 1200);
		if (songOver == false)
		{
			bufferGraphics.drawImage(receptors, 100, 150, this);
			int currentTime = (int)(System.currentTimeMillis() - s.getTrueTime());
			for (int i = 0; i < s.getNotes().getNotes().size(); i++)
			{
				Note n = s.getNotes().getNotes().get(i);
				if (n.getTime()- currentTime < -200)
				{
					life-=10;
					score-=2;
					misses+=1;
					combo=0;
					judgement = 4;
					s.getNotes().getNotes().remove(n);
				}
				else if (n.getTime() - currentTime > 1000)
					break;
				else
				{
					if(n.getTrack() == 1)
						bufferGraphics.drawImage(leftArrow, 100, (int) (150+(n.getTime() - currentTime)*1.8), this);
					else if(n.getTrack() == 2)
						bufferGraphics.drawImage(downArrow, 250, (int) (150+(n.getTime() - currentTime)*1.8), this);
					else if(n.getTrack() == 3)
						bufferGraphics.drawImage(upArrow, 400, (int) (150+(n.getTime() - currentTime)*1.8), this);
					else if(n.getTrack() == 4)
						bufferGraphics.drawImage(rightArrow, 550, (int) (150+(n.getTime() - currentTime)*1.8), this);
				}
			}
			if (judgement == 0)
			{
				bufferGraphics.drawString("Perfect!!", 300, 900);
			}
			else if (judgement == 1)
			{
				bufferGraphics.drawString("Great!", 300, 900);
			}
			else if (judgement == 2)
			{
				bufferGraphics.drawString("Good", 300, 900);
			}
			else if (judgement == 3)
			{
				bufferGraphics.drawString("Bad", 300, 900);
			}
			else if (judgement == 4)
			{
				bufferGraphics.drawString("Miss...", 300, 900);
			}
			bufferGraphics.setFont(bufferGraphics.getFont().deriveFont(bufferGraphics.getFont().getSize() * 0.5F));
			bufferGraphics.drawString("Combo: " + combo, 600, 1000);
			bufferGraphics.drawString("Life: " + life + "/200", 25, 25);
			bufferGraphics.drawString(d.format(score/maxScore), 25, 75);
			bufferGraphics.setFont(bufferGraphics.getFont().deriveFont(bufferGraphics.getFont().getSize() * 0.5F));
			bufferGraphics.drawString("Perfect: " + perfs, 25, 400);
			bufferGraphics.drawString("Great: " + greats, 25, 450);
			bufferGraphics.drawString("Good: " + goods, 25, 500);
			bufferGraphics.drawString("Bad: " + bads, 25, 550);
			bufferGraphics.drawString("Miss: " + misses, 25, 600);
			bufferGraphics.drawString(playerName, 25, 1000);
			bufferGraphics.setFont(bufferGraphics.getFont().deriveFont(bufferGraphics.getFont().getSize() * 2F));
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(image, 0, 0, this);
			frame(); //double buffer secondary method
		}
		else
		{
			bufferGraphics.drawString("Perfect: " + perfs, 100, 100);
			bufferGraphics.drawString("Great: " + greats, 100, 200);
			bufferGraphics.drawString("Good: " + goods, 100, 300);
			bufferGraphics.drawString("Bad: " + bads, 100, 400);
			bufferGraphics.drawString("Miss: " + misses, 100, 500);
			bufferGraphics.drawString("Max Combo: " + maxCombo, 100, 600);
			bufferGraphics.drawString("Score: " + d.format(score/maxScore), 100, 800);
			if (fail == true)
			{
				bufferGraphics.drawString("Grade: F", 100, 900);
			}
			else if (score == maxScore)
			{
				bufferGraphics.drawString("Grade: AAA", 100, 900);
			}
			else if (score >= maxScore*0.99)
			{
				bufferGraphics.drawString("Grade: AA", 100, 900);
			}
			else if (score >= maxScore*0.97)
			{
				bufferGraphics.drawString("Grade: A+", 100, 900);
			}
			else if (score >= maxScore*0.92)
			{
				bufferGraphics.drawString("Grade: A", 100, 900);
			}
			else if (score >= maxScore*0.90)
			{
				bufferGraphics.drawString("Grade: A-", 100, 900);
			}
			else if (score >= maxScore*0.88)
			{
				bufferGraphics.drawString("Grade: B+", 100, 900);
			}
			else if (score >= maxScore*0.82)
			{
				bufferGraphics.drawString("Grade: B", 100, 900);
			}
			else if (score >= maxScore*0.80)
			{
				bufferGraphics.drawString("Grade: B-", 100, 900);
			}
			else if (score >= maxScore*0.78)
			{
				bufferGraphics.drawString("Grade: C+", 100, 900);
			}
			else if (score >= maxScore*0.72)
			{
				bufferGraphics.drawString("Grade: C", 100, 900);
			}
			else if (score >= maxScore*0.70)
			{
				bufferGraphics.drawString("Grade: C-", 100, 900);
			}
			else if (score >= maxScore*0.68)
			{
				bufferGraphics.drawString("Grade: D+", 100, 900);
			}
			else if (score >= maxScore*0.62)
			{
				bufferGraphics.drawString("Grade: D", 100, 900);
			}
			else if (score >= maxScore*0.60)
			{
				bufferGraphics.drawString("Grade: D-", 100, 900);
			}
			else
			{
				bufferGraphics.drawString("Grade: E", 100, 900);
			}
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(image, 0, 0, this);
		}
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}
	
	public void frame()
	{
		while (System.currentTimeMillis() % 10 != 0) {}; //100fps
		if (life <= 0 || s.getNotes().getNotes().size() == 0)
		{
			songOver = true;
			if (life <= 0)
			{
				fail = true;
			}
			s.getSongThread().end();
		}
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
		for (Note n : s.getNotes().getNotes())
		{
			if (n.getTrack() == track)
			{
				int noteTime = n.getTime();
				if (Math.abs(noteTime - tapTime) < 50)
				{
					life+=2;
					if (life > 200)
						life = 200;
					score+=2;
					perfs+=1;
					combo+=1;
					judgement = 0;
					s.getNotes().getNotes().remove(n);
				}
				else if (Math.abs(noteTime - tapTime) < 100)
				{
					life+=1;
					if (life > 200)
						life = 200;
					score+=1;
					greats+=1;
					combo+=1;
					judgement = 1;
					s.getNotes().getNotes().remove(n);
				}
				else if (Math.abs(noteTime - tapTime) < 150)
				{
					goods+=1;
					combo=0;
					judgement = 2;
					s.getNotes().getNotes().remove(n);
				}
				else if (Math.abs(noteTime - tapTime) < 200)
				{
					life-=5;
					score-=1;
					bads+=1;
					combo=0;
					judgement = 3;
					s.getNotes().getNotes().remove(n);
				}
				if (combo > maxCombo)
				{
					maxCombo = combo;
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
