import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class object {
  
  protected int x, y;
  protected Name name;
  protected int velX, velY;
  
  public object(int x, int y, Name name){
    this.x = x;
    this.y = y;
    this.name = name;
  }
  
  public abstract void tick();
  public abstract void render(Graphics g);
  public abstract Rectangle getBounds();
  
  public void setX(int x){
    this.x = x;
  }
  
  public void setY(int y){
    this.y = y;
  }
  
  public int getX(){
    return x;
  }
  
  public int getY(){
    return y;
  }
  
  public void setName(Name name){
    this.name = name;
  }
  
  public Name getName(){
    return name;
  }
  
  public void setVelX(int velX){
    this.velX = velX;
  }
  
  public void setVelY(int velY){
    this.velY = velY;
  }
  
  public int getVelX(){
    return velX;
  }
  
  public int getVelY(){
    return velY;
  }
  
}