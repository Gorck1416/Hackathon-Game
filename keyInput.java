import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class keyInput extends KeyAdapter {

  private update upd;
  private boolean lP = false, rP = false;
  
  public keyInput(update upd){
    this.upd = upd;
  }
  
  public void keyPressed (KeyEvent e) {
    int key = e.getKeyCode();
    
    for(int i = 0; i < upd.obj.size(); i++){
      object tempOb = upd.obj.get(i);
      
      
      if(tempOb.getName() == Name.Player){
        if(key == KeyEvent.VK_LEFT) {
          lP = true;
          tempOb.setVelX(-15);
          
        }
        if(key == KeyEvent.VK_RIGHT) {
          rP = true;
          tempOb.setVelX(15);
        }
      }
    }
  }
  
  public void keyReleased(KeyEvent e){
    int key = e.getKeyCode();
    
    for(int i = 0; i < upd.obj.size(); i++){
      object tempOb = upd.obj.get(i);
      
      
      if(tempOb.getName() == Name.Player){
        if(key == KeyEvent.VK_LEFT) {
          lP = false;
          if(rP) {
            tempOb.setVelX(15);
          } else tempOb.setVelX(0);
        }
        if(key == KeyEvent.VK_RIGHT) {
          rP = false;
          if(lP) {
            tempOb.setVelX(-15);
          } else tempOb.setVelX(0);
        }
      }
    }
    
  }
}