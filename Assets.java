import java.awt.image. BufferedImage;
/**
 * this is a class that load all of the image and can be used anywhere in the project
 *
 * @author Hao Chen
 * @version 7/24/2019
 */
public class Assets
{
    /**
     * store the pictures.
     */
    public static BufferedImage player1, player2, background, pipe;
    /**
     * load all of the images needed.
     */
    public static void init(){
        
        player1 = ImageLoader.loadImage("/bird1.png");
        player2 = ImageLoader.loadImage("/bird2.png");
        background= ImageLoader.loadImage("/flappy.png");
        pipe = ImageLoader.loadImage("/column.png");
        
    }
}
