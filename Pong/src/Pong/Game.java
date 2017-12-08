package Pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

// Make a bot version of this game and select difficulty
// can make enemy match y pos of the ball and set y to whatever the y pos of the ball is and each diffiuty makes the delay go up or down depending of the difficulty

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -7539960646630426242L;
	
	public static final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;
	
	private Handler handler;
	private Thread thread;
	private HUD hud;
	private Menu menu;
	
	Random r;
	
	private boolean running = false;
	
	public enum STATE {
		Game,
		Pause,
		Count3,
		Count2,
		Count1,
		Menu;
	}
	
	public static STATE gameState = STATE.Menu;

	public Game() {
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		
		
		new Window(WIDTH, HEIGHT, "Pong", this);
		
		r = new Random();
		
		if(gameState == STATE.Game) {
			this.addKeyListener(new KeyInput(handler));
			handler.addObject(new Player(45, HEIGHT / 2 - 50, ID.Player, handler));
			handler.addObject(new Player(720, HEIGHT / 2 - 50, ID.Player2, handler));
			handler.addObject(new Ball(WIDTH / 2 - 16, HEIGHT / 2 - 16, ID.Ball, handler));
		} else {
			
		}
		
		
		this.addMouseListener(menu);
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;		
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		handler.tick();
		hud.tick();
		menu.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		handler.render(g);
		
		hud.render(g);
		
		menu.render(g);
		
		g.dispose();
		bs.show();		
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >=  max) {
			return var= max;
		} else if(var <= min) {
			return var = min;
		} else {
			return var;
		}
	}
	
	public static int countTimer1 = 100;
	public static int countTimer2 = 100;
	public static int countTimer3 = 100;
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
				countTimer1--;
			}
			if(running) {
				render();
			frames++;
			}
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}

	public static void main(String[] args) {
		new Game();
	}
}