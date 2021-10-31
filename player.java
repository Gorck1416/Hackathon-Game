import java.awt.Graphics;
import java.awt.Color;

public class player extends object{
  
  private int leftSide = game.WIDTH/4 + game.WIDTH/32, rightSide = leftSide + (game.WIDTH/2-game.WIDTH/12);
  
  public player(int x, int y, Name name){
    super(x, y, name);
    
    
  }
  
  public void tick(){
    x += velX;
    
    x = game.limit(x, leftSide, rightSide);
  }
  
  public void render(Graphics g){
    g.setColor(Color.cyan);
    g.fillOval(x,y, 32, 32);
  }
}

