 import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

/**
 * start the game and play the music
 *
 * @author Hao Chen
 * @version 7/24/2019
 */
public class Music{
        /**
         * store the BGM 
         */
        public static AudioPlayWave audioPlayWave = new AudioPlayWave("BGM.wav");
        /**
         * start the game and play the music
         */
	public static void main(String[] args){
	    
            audioPlayWave.start();
            Game game = new Game("Have you ever experienced despair?", 700, 392);
	    game.start();
	}
	
	
	
}
