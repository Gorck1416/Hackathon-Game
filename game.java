import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	public static final Toolkit tk = Toolkit.getDefaultToolkit();
	public static final int WIDTH = ((int) tk.getScreenSize().getWidth()), HEIGHT = ((int) tk.getScreenSize().getHeight());
  public static final int leftSide = game.WIDTH/4 + game.WIDTH/32, rightSide = leftSide + (game.WIDTH/2-game.WIDTH/12);
  public static boolean flag = false, hit = false;
  public static int health = 3;
  private static Font fnt = new Font("Monospaced", 1, 50);
	
	private Thread thread;
	public boolean running = false;
  private update upd;
  private HUD hud;

  private BufferedImage img = null, hrt3 = null, hrt2 = null, hrt1 = null;
  public static int score = 0;
  
  
  public enum STATE {
    Game,
    End
  };
  
  public static STATE gState = STATE.Game;
  public static Random r = new Random();
  
	
	public game() {

		JFrame frame  = new JFrame("Log of War");
		
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
    hud = new HUD(score);
    
    if(gState == STATE.Game){
      upd.addObj(new player(WIDTH/2,HEIGHT - HEIGHT/4,Name.Player, upd, this));
      upd.addObj(new wood(r.nextInt((rightSide - leftSide) + 1) + leftSide, 0, Name.wood, this));
    }
    
    
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
    if(gState == STATE.Game){
      if(hud != null) hud.tick();
    }
  }
  
  public void render() {
    
    try{
      img = ImageIO.read(new File("path.png"));
      hrt3 = ImageIO.read(new File("3 hearts.png"));
      hrt2 = ImageIO.read(new File("2 hearts.png"));
      hrt1 = ImageIO.read(new File("1 heart.png"));
    } catch (IOException e){
      e.printStackTrace();
    }
    
    BufferStrategy bs = this.getBufferStrategy();
    if(bs == null){
      this.createBufferStrategy(3);
      return;
    }
    
    Graphics g = bs.getDrawGraphics();
    g.drawImage(img, 0, 0, WIDTH, HEIGHT, this);
    g.drawImage(hrt3, WIDTH - 300, HEIGHT/32, 300,300,this);
    upd.render(g);
    
    if(gState == STATE.Game){
      hud.render(g);
      if(health == 2) g.drawImage(hrt2, WIDTH - 300, HEIGHT/32, 300,300,this);
      else if (health == 1)g.drawImage(hrt1, WIDTH - 300, HEIGHT/32, 300,300,this);
    } else if(gState == STATE.End) {
        for(int i = 0; i < upd.obj.size(); i++){
          upd.removeObj(upd.obj.get(i));
        }
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        g.setColor(Color.white);
        g.setFont(fnt);
        g.drawString("GAME OVER!", WIDTH/2-WIDTH/8, HEIGHT/2);
        g.drawString("SCORE: " + (hud.getScore())/10, WIDTH/2-WIDTH/8, HEIGHT/2 + HEIGHT/8);
    }
    
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

