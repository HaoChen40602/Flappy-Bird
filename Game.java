 
import javafx.embed.swing.JFXPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.*;
import java.util.ArrayList;
import java.awt.Font;
/**
 * the class for the game loop
 *
 * @author Hao Chen
 * @version 7/24/2019
 */
public class Game implements Runnable {
    
    private Display display;
    /**
     * store the size of the window
     */
    public int width, height;
    /**
     * store the title of the window
     */
    public String title;
    private boolean running = false;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    private GameState gameState;
    private GameStateDouble gameStateDouble;
    private KeyManager keyManager;
    private int i;
    /**
     * initialize the variables
     * @param title the title of the game
     * @param width the width of the game window
     * @param height the height of the game window
     */
    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }
    /**
     * initialize the variables
     */
    private void init(long a){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();
        // state
        if (LauncherReal.twoP){
            gameStateDouble = new GameStateDouble(this, a);
        }else{
            gameState = new GameState(this, a);
        }
    }
    /**
     * change the position of the players and pipes and background
     */
    private void tick(){
        keyManager.tick();
        if (LauncherReal.twoP){
            i = gameStateDouble.tick();
        }else{
            i = gameState.tick();
        }
    }
    /**
     * draw the players and pipes and background to the window.
     */
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.setFont(new Font("", Font.PLAIN, 30));
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here!
        
        if (LauncherReal.twoP){
            gameStateDouble.render(g, i);
        }else{
            gameState.render(g, i);
        }
        //End Drawing!
        bs.show();
        g.dispose();
    }
    /**
     * the game loop
     */
    public void run(){
        
        int fps = 60;
        double timePerTick = 1000000000.0 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long la = System.nanoTime();
        init(la);
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            if (delta >= 1){
                if (LauncherReal.twoP){
                    if (gameStateDouble.inter()){
                        display.getFrame().setVisible(false);
                        if(GameStateDouble.point < 0){
                             GameStateDouble.point = 0;
                        }
                        AudioPlayWave audioPlayWave = new AudioPlayWave("boom.wav");
                        audioPlayWave.start();
                        Test.main(null);
                        break;
                    }
                }else{
                    if (gameState.inter()){
                        display.getFrame().setVisible(false);
                        if(GameState.point < 0){
                            GameState.point = 0;
                        }
                        AudioPlayWave audioPlayWave = new AudioPlayWave("boom.wav");
                        audioPlayWave.start();
                        Test.main(null);
                        break;
                    }
                }
                tick();
                render();
                delta --;
                
            } 
        }
        
        stop();
        
    }
    /**
     * return the keyManager
     * @return keyManager KeyManager object
     */
    public KeyManager getKeyManager(){
        return keyManager;
     }
    /**
     * set the game loop to running
     */
    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    /**
     * set the game loop to stop running
     */
    public synchronized void stop(){
        if(!running){
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}











