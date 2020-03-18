import javafx.application.*;
import javafx.embed.swing.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javafx.embed.swing.JFXPanel;
import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * this is a class that create the leader board window.
 *
 * @author Hao Chen
 * @version 7/24/2019
 */
public class Test {
        private JFrame frame;
        private Canvas canvas;
        private String title;
        private int width, height;
        /**
         * create the Jframe object.
         */
         private static void initAndShowGUI() {
             // This method is invoked on Swing thread
             JFrame frame = new JFrame("FX");
             final JFXPanel fxPanel = new JFXPanel();
             frame.add(fxPanel);
             frame.setSize(700, 392);
             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             frame.setResizable(false);
             frame.setLocationRelativeTo(null);
             frame.setVisible(true);

             Platform.runLater(new Runnable() {
                 @Override
                 public void run() {
                     initFX(fxPanel);
                 }
             });
         }
         /**
          * initialize the JFXPanel object and draw thing on it.
          * @param fxPanel the JFXPanel you want to draw things on
          * 
          */
         private static void initFX(JFXPanel fxPanel) {
             // This method is invoked on JavaFX thread
             Group root = new Group();
             Scene scene = new Scene(root, 300, 300, Color.GRAY);
             Text t = new Text(250, 50, "Game Over!");
             t.setFont(new Font(50));
             Text t2;
             if (LauncherReal.twoP){
                t2 = new Text(170, 130, "your score is " + GameStateDouble.point + " points!");
             }else{
                t2 = new Text(170, 130, "your score is " + GameState.point + " points!");
             }
             t2.setFont(new Font(30));
             
             ArrayList<String> stringList;
             if (LauncherReal.twoP){
                 stringList = FileIO.writeInFile(LauncherReal.userName, GameStateDouble.point);
             }else{
                 stringList = FileIO.writeInFile(LauncherReal.userName, GameState.point);
             }
             int[] intArray = new int[stringList.size() / 2];
             int a = 0;
             for (int i = 1; i < stringList.size(); i += 2){
                 String str = stringList.get(i);
                 intArray[a] = Integer.parseInt(str);
                 a++;
             }
             
             Arrays.sort(intArray);
             int currentPlace = -1;
             for (int i = intArray.length - 1; i >= 0; i--){
                 if (intArray[i] == Integer.parseInt(stringList.get(stringList.size() - 1))){
                     currentPlace = intArray.length - i;
                     break;
                 }
             }
             String[] topThree = new String[3];
             
             for(int i = stringList.size() - 1; i >= 0; i -= 2){
                 if(stringList.get(i).equals("" + intArray[intArray.length - 1]) && topThree[0] == null){
                     topThree[0] = stringList.get(i - 1);
                     continue;
                 }
                 if(stringList.get(i).equals("" + intArray[intArray.length - 2]) && topThree[1] == null){
                     topThree[1] = stringList.get(i - 1);
                     continue;
                 }
                 if(stringList.get(i).equals("" + intArray[intArray.length - 3]) && topThree[2] == null){
                     topThree[2] = stringList.get(i - 1);
                     continue;
                 }
             }
             
             Text tTitle = new Text(230, 180, "Leaderboard");
             tTitle.setFill(Color.GOLD);
             tTitle.setFont(new Font(40));
             
             
             Text tFirst = new Text(260, 220, "1st " + topThree[0] + " " + intArray[intArray.length - 1]);
             tFirst.setFill(Color.GOLD);
             tFirst.setFont(new Font(30));
             
             Text tSecond = new Text(260, 260, "2nd " + topThree[1] + " " + intArray[intArray.length - 2]);
             tSecond.setFill(Color.SILVER);
             tSecond.setFont(new Font(30));
             
             Text tThird = new Text(260, 300, "3rd " + topThree[2] + " " + intArray[intArray.length - 3]);
             tThird.setFill(Color.FUCHSIA);
             tThird.setFont(new Font(30));
             String str = "";
             if(currentPlace % 10 == 1){
                 str = "You are in " + currentPlace + "st"+ " place";
             }else if (currentPlace % 10 == 2){
                 str = "You are in " + currentPlace + "nd"+ " place";
             }else if (currentPlace % 10 == 3){
                 str = "You are in " + currentPlace + "rd"+ " place";
             }else{
                 str = "You are in " + currentPlace + "th"+ " place";
             }
             
             Text place = new Text(220, 340, str);           
             place.setFont(new Font(20));
             
             root.getChildren().add(t);
             root.getChildren().add(t2);
             root.getChildren().add(tTitle);
             root.getChildren().add(tFirst);
             root.getChildren().add(tSecond);
             root.getChildren().add(tThird);
             root.getChildren().add(place);
             fxPanel.setScene(scene);
             
         }
         /**
          * call the methods above.
          */
         public static void main(String[] args) {
             SwingUtilities.invokeLater(new Runnable() {
                 @Override
                 public void run() {
                     initAndShowGUI();
                 }
             });
             
         }
     }
