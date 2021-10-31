import java.util.LinkedList;
import java.awt.Graphics;

public class update{
    LinkedList<object> obj = new LinkedList<object>();
    
    public void tick(){
      for(int i = 0; i < obj.size(); i++){
        object tempObj = obj.get(i);
        
        tempObj.tick();
      
      }
     }
     
     public void render(Graphics g){
      for(int i = 0; i < obj.size(); i++){
        object tempObj = obj.get(i);
        
        tempObj.render(g);
      
      }
     }
     
     public void addObj(object ob){
      this.obj.add(ob);
     }
     
     public void removeObj(object ob){
      this.obj.remove(ob);
     }
}