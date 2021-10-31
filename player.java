import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class player extends object{
  
  public player(int x, int y, Name name){
    super(x, y, name);

  }
  
  public void tick(){
    x += velX;
    
    x = game.limit(x, game.leftSide, game.rightSide);
  }
  
  public Rectangle getBounds(){
    return new Rectangle(x,y,32,32);
  }
  public void render(Graphics g){
    g.setColor(Color.cyan);
    g.fillRect(x,y, 32, 32);
  }
}

