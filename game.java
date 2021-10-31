import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	public static final Toolkit tk = Toolkit.getDefaultToolkit();
	public static final int WIDTH = ((int) tk.getScreenSize().getWidth()), HEIGHT = ((int) tk.getScreenSize().getHeight());
  public static final int leftSide = game.WIDTH/4 + game.WIDTH/32, rightSide = leftSide + (game.WIDTH/2-game.WIDTH/12);
  public static boolean flag = false, hit = false;
  public static int health = 3;
	
	private Thread thread;
	public boolean running = false;
  private update upd;
  
  public static Random r = new Random();
  
	
	public game() {

		JFrame frame  = new JFrame("Death of a Bike");
		
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.setVisible(true);
		this.start();
    
    
    
    upd = new update();
    this.addKeyListener(new keyInput(upd));
    upd.addObj(new player(WIDTH/2,HEIGHT - HEIGHT/8,Name.Player, upd, this));
    upd.addObj(new wood(r.nextInt((rightSide - leftSide) + 1) + leftSide, 0, Name.wood));
    
    
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
    
	}
	
	public void run() {
		while(running) {
      this.requestFocus();
			tick();
      render();
		}
	}
  
  public void tick(){
    if(upd != null) upd.tick();
  }
  
  public void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if(bs == null){
      this.createBufferStrategy(3);
      return;
    }
    
    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.blue);
    g.fillRect(0,0,WIDTH, HEIGHT);
    g.setColor(Color.green);
    g.fillRect(WIDTH/4,0,WIDTH/2, HEIGHT);
    g.setColor(new Color(186, 148, 9));
    g.fillRect(WIDTH/4 + WIDTH/32,0,WIDTH/2-WIDTH/16, HEIGHT);
    upd.render(g);
    
    g.dispose();
    bs.show();
  }
  
  public static int limit(int x, int min, int max){
    if( x >= max) return max;
    else if (x <= min) return min;
    else return x;
  }
  
  public static int reset(int y, int max){
    if(y > max) {
      flag = true;
      return 0;
    }
    else {
      flag = false;
      return y;
    }
  }

  public static void main(String[] args) {
     new game();
  }
}

