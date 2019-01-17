import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandle implements KeyListener{

	private boolean[] Keys = new boolean[8];

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			Keys[0] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			Keys[1] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			Keys[2] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			Keys[3] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			Keys[4] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Keys[5] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Keys[6] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Keys[7] = true;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			Keys[0] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			Keys[1] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			Keys[2] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			Keys[3] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			Keys[4] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Keys[5] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Keys[6] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Keys[7] = false;
		}
		
	}

	public void keyTyped(KeyEvent e) {
		
		
	}
	
	public boolean[] getKeys() {
		return Keys;
	}
	
	
	

}
