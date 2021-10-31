import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class HUD {

  private int score;
  private Font fnt = new Font("Monospaced", 1, 50);

  public HUD (int score){
    this.score = score;
  }

  public void tick(){
    score++ ;
  }
  
  public void render(Graphics g){
    g.setFont(fnt);
    g.setColor(Color.white);
    g.drawString("Score:" , (game.WIDTH/8 - game.WIDTH/16) - 20, game.HEIGHT/4 - game.HEIGHT/8);
    g.drawString(""+score / 10, (game.WIDTH/8 - game.WIDTH/16) + 30, (game.HEIGHT/4 - game.HEIGHT/8) + 50);
  }
  
  public void Score(int score) {
    this.score = score;
  }
  
  public int getScore(){
    return score;
  }
  
}