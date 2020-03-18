 

import java.awt.Canvas;
import java.awt.Dimension;
import javafx.embed.swing.JFXPanel;
import javax.swing.JFrame;
/**
 * create a JFrame object.
 * 
 * @author Hao Chen
 * @version 7/24/2019
 */
public class Display {

	private JFrame frame;
	private Canvas canvas;
	private String title;
	private int width, height;
	/**
	 * initialize the window.
	 * @param title the title of the window
	 * @param width the width of the window
	 * @param height the height of the indow
	 */
	public Display(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		frame = new JFrame(title);
		
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		frame.add(canvas);
		frame.pack();
	}
	/**
	 * reuturn the canvas object
	 * @return canvas the canvas object
	 */
	public Canvas getCanvas(){
		return canvas;
	}
	/**
	 * reuturn the JFrame object
	 * @return frame the JFrame object
	 */
	public JFrame getFrame(){
		return frame;
	}
	
	
	
	
}
