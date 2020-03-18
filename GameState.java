import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Rectangle;
import javax.swing.JFrame;
/**
 * the class for drawing things on the screen while the game is going.
 *
 * @author Hao Chen
 * @version 7/24/2019
 */
public class GameState
{
    private Player player1;
    /**
     * store the points the player gets
     */
    public static int point;
    private ArrayList<Pipe> pipes;
    private Game game;
    private int addPoint;
    private int sdfs = 0;
    private long lastTime;
    private long la;
    private long now;
    private long timer = 0;
    /**
     * initialize the variables.
     * @param game the game object the player is in.
     */
    public GameState(Game game, long a){
        this.game = game;
        lastTime = System.nanoTime();
        la = a;
        if (LauncherReal.levelS.equals("easy")){
            point = -3;
            addPoint = 1;
        }else if(LauncherReal.levelS.equals("normal")){
            point = -6;
            addPoint = 2;
        }else if(LauncherReal.levelS.equals("hard")){
            point = -9;
            addPoint = 3;
        }else if(LauncherReal.levelS.equals("crazy")){
            point = -12;
            addPoint = 4;
        }else{
            point = -15;
            addPoint = 5;
        }
        player1 = new Player(game, 200, 100, 32, 29);
        pipes = new ArrayList<Pipe>();
    }
    
    /**
     * change the position of the player and the pipes
     */
    public int tick(){
        if ((System.nanoTime() - la) / 10.0 <= 300000000){
            return (int) (3 - ((System.nanoTime() - la) / 1000000000));
        }
        now = System.nanoTime();
        timer += now - lastTime;
        lastTime = now;
        player1.tick();
        if (timer/10.0 >= -300000000.0 / Pipe.speed){
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
        for (Pipe pipe:pipes){
            pipe.render(g);
        }
        if (point < 0){
            g.drawString(0 + " points", 340, 70);
        }else{
            g.drawString(point + " points", 340, 70);
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
            if (player1.getMe().intersects(rect)){
                return true;
            }
        }
        if (player1.y <= 0 || player1.y >= game.height - player1.height){
                return true;
        }
        return false;
    }
}
