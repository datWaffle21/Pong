package Pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import Pong.Game.STATE;

public class Menu extends MouseAdapter {

	private Random r = new Random();
	private Game game; 
	private HUD hud;
	private Handler handler;
	
	private int count = 100;
	private int count2 = 33;
	private int times = 3;

	private boolean start = false;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		
		handler.addObject(new Player( 25, 100, ID.Player2, handler));
		handler.addObject(new Player( Game.WIDTH - 58, Game.HEIGHT - 200, ID.Player2, handler));
		handler.addObject(new Ball(Game.WIDTH / 2 - 16, Game.HEIGHT / 2 - 16, ID.Ball, handler));
		
	}
	
	public void tick() {
		if(start) {
			count--;
			if(count <= 0) {
				count2 --;
			}
			if(count < 100) {
				times--;
				
				Game.gameState = STATE.Count3;
			}
			if(count <66) {
				times--;
				
				Game.gameState = STATE.Count2;
			}
			if(count < 33) {
				times--;
				
				Game.gameState = STATE.Count1;
			}
			
			
			if(Game.gameState == STATE.Count1 && count2 == 0) {
				
				Game.gameState = STATE.Game;
				
				handler.removeAll();
				
				handler.addObject(new Player(45, Game.HEIGHT / 2 - 50, ID.Player, handler));
				handler.addObject(new Player(720, Game.HEIGHT / 2 - 50, ID.Player2, handler));
				handler.addObject(new Ball(Game.WIDTH / 2 - 16, Game.HEIGHT / 2 - 16, ID.Ball, handler));
				
				start = false;
				}	
			anime();
		}
	}
	
	public void render(Graphics g) {
		if(Game.gameState == STATE.Menu) {
			Font font = new Font("arial", 1, 40);
			Font font2 = new Font("arial", 1, 144);
			Font font3 = new Font("arial", 1, 25);
			
			g.setColor(Color.white);
			g.drawRect(250, 425, 300, 50);
			
			g.setFont(font);
			g.drawString("Welcome to", 278, 100);
			g.drawString("Play", 358, 463);
			g.setFont(font2);
			g.drawString("Pong!", 200, 290);
			
			g.drawRect(686, 503, 100, 50);
			g.setFont(font3);
			g.drawString("QUIT", 708, 538);
			
			
			
			
		}
		if(Game.gameState == STATE.Count3) {
			Font font = new Font("arial", 1, 40);
			Font font2 = new Font("arial", 1, 144);
			Font font3 = new Font("arial", 1, 25);
			
			g.setColor(Color.white);
			g.setFont(font2);
			g.drawString("3", 350, 300);
		}
		if(Game.gameState == STATE.Count2) {
			Font font = new Font("arial", 1, 40);
			Font font2 = new Font("arial", 1, 144);
			Font font3 = new Font("arial", 1, 25);
			
			g.setColor(Color.white);
			g.setFont(font2);
			g.drawString("2", 350, 300);
		}
		if(Game.gameState == STATE.Count1) {
			Font font = new Font("arial", 1, 40);
			Font font2 = new Font("arial", 1, 144);
			Font font3 = new Font("arial", 1, 25);
			
			g.setColor(Color.white);
			g.setFont(font2);
			g.drawString("1", 350, 300);
		}
		
		
	}
	
	private void anime() {
		try {
			
			Robot robot = new Robot();
			
		robot.keyPress(KeyEvent.VK_W);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//System.out.println("X: " + mx + " Y: " + my);
		
		if(Game.gameState == STATE.Menu) {
			
			// Play Button
			if(mouseOver(mx, my, 250, 425, 300, 50)) {
				handler.removeAll();
				start = true;				
			}
			
			// Quit Button
			if(mouseOver(mx, my, 686, 503, 100, 50)) {
				System.exit(1);
			}
 		}
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
}