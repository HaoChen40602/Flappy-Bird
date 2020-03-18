import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Rectangle;
import javax.swing.JFrame;
/**
 * the class for drawing things on the screen while the double game is going.
 *
 * @author Hao Chen
 * @version 7/24/2019
 */
public class GameStateDouble
{
    private Player player1;
    private Player player2;
    /**
     * store the point players get.
     */
    public static int point;
    private ArrayList<Pipe> pipes;
    private Game game;
    private int addPoint;
    private int sdfs = 0;
    private long lastTime = System.nanoTime();
    private long now;
    private long timer = 0;
    private long la;
    /**
     * initialize the variables.
     * @param game the game object the player is in.
     */
    public GameStateDouble(Game game, long a){
        this.game = game;
        la = a;
        if (LauncherReal.levelS.equals("easy")){
            point = -6;
            addPoint = 2;
        }else if(LauncherReal.levelS.equals("normal")){
            point = -12;
            addPoint = 4;
        }else if(LauncherReal.levelS.equals("hard")){
            point = -18;
            addPoint = 6;
        }else if(LauncherReal.levelS.equals("crazy")){
            point = -24;
            addPoint = 8;
        }else{
            point = -30;
            addPoint = 10;
        }
        player1 = new Player(game, 200, 100, 32, 29);
        player2 = new Player(game, 100, 100, 32, 29);
        pipes = new ArrayList<Pipe>();
    }
    
    
    /**
     * change the position of the player and the pipes
     */
    public int tick(){
        if ((System.nanoTime() - la) / 10.0 <= 300000000){return (int) (3 - ((System.nanoTime() - la) / 1000000000));}
        now = System.nanoTime();
        timer += now - lastTime;
        lastTime = now;
        player1.tick();
        player2.tick();
        if (timer/10 >= -300000000 / Pipe.speed){
            pipes.add(new Pipe());
            point += addPoint;
            timer = 0;
        }
        for(int i = pipes.size() - 1; i >= 0; i--){
            if (pipes.get(i).spaceX < -200){
                pipes.remove(i);
            }
        }
        for (Pipe pipe:pipes){
            pipe.tick();
        }
        return -1;
    }
    
    /**
     * draw the player, background and pipes.
     * @param g the graphics object you wnat to draw on
     */
    public void render(Graphics g, int i){
        g.drawImage(Assets.background, -sdfs % 700, 0, null);
        g.drawImage(Assets.background, -sdfs % 700 + 700, 0, null);
	sdfs++;
	player1.render(g);
        player2.render(g);
        for (Pipe pipe:pipes){
            pipe.render(g);
        }
        if (point < 0){
            g.drawString(0 + " points", 340, 20);
        }else{
            g.drawString(point + " points", 340, 20);
        }
        if (i != -1){
            g.drawString("" + i, 340, 200);
        }
    }
    /**
     * check if the player crash into the pipes
     * @return boolean -if the player dies
     */
    public boolean inter(){
        ArrayList<Rectangle> rectArray = new ArrayList<Rectangle>();
        for (Pipe pipe:pipes){
            rectArray.add(pipe.getTopRect());
            rectArray.add(pipe.getBottomRect());
        }
        for(Rectangle rect : rectArray){
            if (player2.getMe().intersects(rect)){
                return true;
            }
        }
        for(Rectangle rect : rectArray){
            if (player1.getMe().intersects(rect)){
                
                return true;
            }
        }
        if (player1.y <= 0 || player1.y >= game.height - player1.height){
            
                return true;
        }
        if (player2.y <= 0 || player2.y >= game.height - player2.height){
            
                return true;
        }
        return false;
    }
}
