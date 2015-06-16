import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
public class AdventureGUI {

   public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException{
      Scanner readText = new Scanner (new File("Erazem.txt"));
      Scanner console = new Scanner (System.in);
      //intro
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
      String firstTitle = firstOption.getTitle();
      String firstDescription= firstOption.getDescription();
      //JOptionPane.showMessageDialog(null, firstDescription);
         JScrollPane scrollPane= new JScrollPane(new JLabel(firstDescription));
         scrollPane.setPreferredSize(new Dimension(500,250));
         Object message = scrollPane;         
         JTextArea textArea= new JTextArea(firstDescription);
         textArea.setLineWrap(true);
         textArea.setWrapStyleWord(true);
         textArea.setMargin(new Insets(5,5,5,5));
         scrollPane.getViewport().setView(textArea);
         message= scrollPane;
         String userChoice = JOptionPane.showInputDialog(null,message);               
          
           
      Options choice = firstOption.getAction(userChoice);
      while(userIsAlive){
         boolean thirdOp= false; 
         String nextDescription = choice.getDescription();
         //separateLines(nextDescription);
         
         if(choice.getAction("a") == null){
             break;     
         }
         else{
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
            //userChoice = JOptionPane.showInputDialog(null,nextDescription);        
            Options nextChoice= choice.getAction(userChoice);
            //else{
               choice = nextChoice;
            //}
         }
      }     
   }
 
   public static void separateLines (String text){
      System.out.print(" ");
      for(int i = 0; i < text.length(); i++){ 
         char currentChar = text.charAt(i);
         if((currentChar + "" ).equals("%")){                  
            System.out.println();
         }
         else{
            System.out.print(currentChar + "" );
         }
      }
   }
   
   public static boolean playAgain (Scanner console){
      JOptionPane.showMessageDialog(null,"Do you want to play again?");
      boolean userWantsToPlay;
      String wantsToPlay= console.nextLine();
      if(wantsToPlay.startsWith("y") || wantsToPlay.startsWith("Y")){
         userWantsToPlay= true;
      }
      else{
         userWantsToPlay=false;
      }
      return userWantsToPlay;
   }
   
   public static Options getChild (Scanner readText, String title, String description, boolean firstOp){
      boolean noOptions = title.contains("$");
      boolean hasThirdOp= title.contains("*");
      if(noOptions){
         return new Options(title, description);
         //String descrip= readText.nextLine(); 
         //JOptionPane.showMessageDialog(null, descrip);
         //return null;
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
