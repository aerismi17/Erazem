import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
public class AdventureGUI {

   public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException{
      Scanner readText = new Scanner (new File("Erazem.txt"));
      Scanner console = new Scanner (System.in);
      JOptionPane.showMessageDialog(null, "Welcome to Erazem");
      int choice = JOptionPane.showConfirmDialog(null, "Do you want to play?");
      int timesPlayed = 0;
      if (choice == JOptionPane.YES_OPTION){
         Options firstOption = buildStory(readText);
         playGame(console, firstOption);
      }   
      else{
         JOptionPane.showMessageDialog(null, "okay, then leave.");
      }
   }
   
   public static Options buildStory (Scanner readText){
      String firstTitle = readText.nextLine();
      String firstDescription = readText.nextLine();
      Options firstOption = getChild(readText, firstTitle, firstDescription, true);
      return firstOption;
   }
   
   public static void playGame(Scanner console, Options firstOption) throws IllegalArgumentException{
      boolean userIsAlive = true;
      boolean nowDead = false;
      boolean thirdOp= false;
      String firstTitle = " ";
      JScrollPane scrollPane = null;
      Object message = null;
      JTextArea textArea = null;
      String userChoice = " ";
      Options nextChoice = null;
      String firstDescription = " ";
      Options choice = null;
      String nextDescription = " ";
      boolean aOrB = false;
      while(aOrB != true){
         try{
            firstTitle = firstOption.getTitle();
            firstDescription= firstOption.getDescription();
            scrollPane= new JScrollPane(new JLabel(firstDescription));
            scrollPane.setPreferredSize(new Dimension(500,250));
            message = scrollPane;         
            textArea= new JTextArea(firstDescription);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setMargin(new Insets(5,5,5,5));
            scrollPane.getViewport().setView(textArea);
            message= scrollPane;
            userChoice = JOptionPane.showInputDialog(null,message);               
            choice = firstOption.getAction(userChoice);
            nextDescription = choice.getDescription();
            aOrB = true;
         }
         catch(NullPointerException a){
            JOptionPane.showMessageDialog(null,"Please type a or b");
            aOrB = false;
         }      
      }
      aOrB = false;
      while(userIsAlive){
         while(aOrB != true){
            try{
               scrollPane= new JScrollPane(new JLabel(nextDescription));
               scrollPane.setPreferredSize(new Dimension(500,250));
               message = scrollPane;         
               textArea= new JTextArea(nextDescription);
               textArea.setLineWrap(true);
               textArea.setWrapStyleWord(true);
               textArea.setMargin(new Insets(5,5,5,5));
               scrollPane.getViewport().setView(textArea);
               message= scrollPane;
               userChoice = JOptionPane.showInputDialog(null,message);
               nextChoice= choice.getAction(userChoice);
               if(choice.getAction("a") == null){
                  break; 
               }    
               nextDescription= nextChoice.getDescription();
               aOrB = true;    
            }
            catch(NullPointerException a){
               JOptionPane.showMessageDialog(null,"Please type a or b or c (if there's a c option).");
               aOrB = false;
            }   
         }
         if(choice.getAction("a") == null){
            break;     
         }
         choice = nextChoice;
         aOrB = false;   
      }
   }
    
   public static Options getChild (Scanner readText, String title, String description, boolean firstOp){
      boolean noOptions = title.contains("$");
      boolean hasThirdOp= title.contains("*");
      if(noOptions){
         return new Options(title, description);
      }
      String currentLine = "";
      if(hasThirdOp){
         if(!firstOp){
            while(!(currentLine.equals(title))){
               currentLine = readText.nextLine();
               if((currentLine + title).substring(0, title.length()).equals(title)){
                  currentLine = title;
               }
            }
         }
         String titleA = readText.nextLine();
         String descripA= readText.nextLine();
         String titleB = readText.nextLine();
         String descripB= readText.nextLine();        
         String titleC = readText.nextLine();
         String descripC= readText.nextLine();
         return new Options(title, description,getChild(readText, titleA, descripA, false), getChild(readText, titleB, descripB, false), getChild(readText, titleC, descripC, false));
      }
      else{
         if(!firstOp){
            while(!(currentLine.equals(title))){
               currentLine = readText.nextLine();
               if((currentLine + title).equals(title)){
                  currentLine = title;
               }
            }
         }
         String titleA= readText.nextLine();
         String descripA= readText.nextLine();
         String titleB = readText.nextLine();
         String descripB= readText.nextLine();
         return new Options(title, description,getChild(readText, titleA, descripA, false), getChild(readText, titleB, descripB, false));
      }   
   }
}
