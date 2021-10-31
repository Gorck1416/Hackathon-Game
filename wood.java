import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class wood extends object {

  public wood(int x, int y, Name name){
    super(x,y,name);
    
    velY = 1;
  }

  public Rectangle getBounds() {
    return new Rectangle (x,y,32,32);
  }
  
  public void tick() {
    y += velY;
    
    if(game.flag) x = game.r.nextInt((game.rightSide - game.leftSide) + 1) + game.leftSide;
    
    y = game.reset(y, game.HEIGHT);
  }
  
  public void render(Graphics g){
    
    Graphics2D g2d = (Graphics2D) g;
    
    g.setColor(Color.green);
    g2d.draw(getBounds());
    
    g.setColor(new Color(99, 76, 12));
    g.fillRect(x,y,32,32);
  
  }

}