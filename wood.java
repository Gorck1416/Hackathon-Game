import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class wood extends object {
  
  
  private game Game;
  private BufferedImage img = null;

  public wood(int x, int y, Name name, game Game){
    super(x,y,name);
    this.Game = Game;
    
    velY = 20;
  }

  public Rectangle getBounds() {
    return new Rectangle (x,y,135,120);
  }
  
  public void tick() {
   
    if(game.score > 10 )velY *= (game.score/10);
    y += velY ;
    
    int k = game.reset(y, game.HEIGHT);
    if(game.flag) {
      game.flag = false;
      y = 0;
      x = game.r.nextInt((game.rightSide - game.leftSide) + 1) + game.leftSide;
    }
    if(game.hit) {
      game.hit = false;
      y = 0;
      x = game.r.nextInt((game.rightSide - game.leftSide) + 1) + game.leftSide;
    }
    
    
    
   
  }
  
  public void render(Graphics g){
    
    
    try{
      img = ImageIO.read(new File("log.png"));
    } catch (IOException e){
      e.printStackTrace();
    }
    
     g.drawImage(img, x-30, y, 200, 200, Game);
  
  }

}