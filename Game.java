package jjr;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Game extends Applet implements KeyListener{ 
	//NOTE: Currently using AWT cause that's what I've used before.
	//I know it's outdated as hell. The amount of graphics we
	//actually need is minimal.. If you know Swing or JavaFX 
	//and want to convert the code into one of these, be my guest.

	Song s;
	
	public void init() {
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
		s.play();
	}
	
	public void paint(Graphics g)
	{
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//doesn't matter, isn't used
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//doesn't matter, isn't used
	}

}
