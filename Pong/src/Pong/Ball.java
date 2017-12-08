package Pong;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends GameObject {
	
	private Handler handler;
	Random r = new Random();;

	public Ball(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		int var = r.nextInt(2);
		if(var == 1) {
			velX = 5;
		} else if(var == 2 || var == 0) {
			velX = -5;
		}
		int var2 = r.nextInt(2);
		if(var2 == 1) {
			velY = 10;
		} else if(var2 == 2 || var2 == 0) {
			velY = -10;
		}
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 55) velY *= -1;
		
		y = Game.clamp(y, 0, Game.HEIGHT - 16);
		
		if(x < 0) {
			HUD.score2 ++;
			handler.removeObject(this);
			handler.addObject(new Ball(Game.WIDTH / 2 - 16, Game.HEIGHT / 2 - 16, ID.Ball, handler));
		}
		if(x > Game.WIDTH) {
			HUD.score1++;
			handler.removeObject(this);
			handler.addObject(new Ball(Game.WIDTH / 2 - 16, Game.HEIGHT / 2 - 16, ID.Ball, handler));			
		}
		
		
		collison();
		
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, 32, 32);
		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	public void collison() {
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player || tempObject.getId() == ID.Player2) {
				if(getBounds().intersects(tempObject.getBounds())) {
					// Collision code
					velX *= -1;
				}
			
			}	
		}
	
	}
}