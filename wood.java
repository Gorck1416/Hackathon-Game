import java.awt.Graphics;
import java.awt.Color;

public class wood extends object {

  public wood(int x, int y, Name name){
    super(x,y,name);
    
    velY = 5;
  }

  public tick() {
    y += velY();
    
    if(y == game.HEIGHT) y = 0;
  }

}