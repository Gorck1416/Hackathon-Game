import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class player extends object{
  
  private update upd;
  private game Game;
  
  private BufferedImage img = null;
    
  public player(int x, int y, Name name, update upd, game Game){
    super(x, y, name);
    this.upd = upd;
    this.Game = Game;

  }
  
  public void tick(){
    x += velX;
    
    x = game.limit(x, game.leftSide, game.rightSide);
    
    collision();
  }
  
  public Rectangle getBounds(){
    return new Rectangle(x,y,64,96);
  }
  
  private void collision(){
    for(int i = 0; i < upd.obj.size(); i++){
      object tempObj = upd.obj.get(i);
      
      if(tempObj.getName() == Name.wood) {
          if(getBounds().intersects(tempObj.getBounds())) {
            game.hit = true;
            game.health--;
          }
          if(game.health == 0) game.gState = game.STATE.End;
      }
    }
  }
  
  public void render(Graphics g){
    
    
    try{
      img = ImageIO.read(new File("person right.png"));
    } catch (IOException e){
      e.printStackTrace();
    }
    
    
    g.drawImage(img, x-30, y-20, 128, 128, Game);

   }
    
 }

