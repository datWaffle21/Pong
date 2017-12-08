package Pong;
import java.awt.Font;
import java.awt.Graphics;

import Pong.Game.STATE;

public class HUD {
	
	public static int score1 = 0;
	public static int score2 = 0;
	
	

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		if(Game.gameState == STATE.Game) {
			Font font = new Font("arial", 1, 50);
			
			g.setFont(font);
			g.drawString("SCORE", Game.WIDTH / 2 - 92, 50);
			g.drawString(score1 + "     ||     " + score2, 286, 100);
		}
		
	}
}