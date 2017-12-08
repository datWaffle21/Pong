package pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private Game game;
	private Player player;
	
	public static boolean[] keyDown = new boolean[2];
	public static boolean[] keyDown2 = new boolean[2];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		
		keyDown[0] = false;
		keyDown[1] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				// Code for player one
				
				if(key == KeyEvent.VK_W) {
					tempObject.setVelY(-10);
					keyDown[0] = true;
				}
				if(key == KeyEvent.VK_S) {
					tempObject.setVelY(10);
					keyDown[1] = true;
				}
			}
			if(tempObject.getId() == ID.Player2) {
				//Code for player two
				
				if(key == KeyEvent.VK_UP) {
					tempObject.setVelY(-10);
					keyDown2[0] = true;
				}
				if(key == KeyEvent.VK_DOWN) {
					tempObject.setVelY(10);
					keyDown2[1] = true;
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				// Code for player one
				
				if(key == KeyEvent.VK_W) {
					keyDown[0] = false;
				}
				if(key == KeyEvent.VK_S) {
					keyDown[1] = false;
				}
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
			}
			if(tempObject.getId() == ID.Player2) {
				//Code for player two
				
				if(key == KeyEvent.VK_UP) {
					keyDown2[0] = false;
				}
				if(key == KeyEvent.VK_DOWN) {
					keyDown2[1] = false;
				}
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
			}
			
		}
	}
	
}
