import java.util.*;
public class Options{
      
   ArrayList<Options> actions;
   private String title;
   private String description;
   
   //INPUT: string title, string description
   //OUTUPUT: Virtually nothing, is a constructor
   public Options(String title, String description){
      this(title, description, null, null, null);
   }
   
   //INPUT:String actionA, actionB, title, description for option
   //OUTPUT: second constructor, this time with four parameters
   public Options(String title, String description, Options actionA, Options actionB){
      this(title, description, actionA, actionB, null);
   }
   
   //INPUT:actionA, actionB, actionC
   //OUTPUT: third constructor, this time with five parameters
   public Options(String title, String description, Options actionA, Options actionB, Options actionC){
      this.title = title;
      this.description= description;
      actions = new ArrayList<Options>();
      add(actionA, actionB, actionC);
   }
   
   //INPUT: actionA, actionB, actionC
   //OUTPUT: adds each action the the arrayList of actions  
   private void add(Options actionA, Options actionB, Options actionC){
      actions.add(actionA);
      actions.add(actionB);
      if ((actionC != null)){
         actions.add(actionC);
      }
   }
   
   //INTPUT: user choice in the form of a string
   //OUTPUT: returns the corresponding strip of text for each action
   public Options getAction(String userChoice){
      if((userChoice.equals("A")) || (userChoice.equals("a"))){
         return actions.get(0);
      }
      if((userChoice.equals("B")) || (userChoice.equals("b"))){
         
      
         return actions.get(1);
      }
      else if ((userChoice.equals("C")) || (userChoice.equals("c"))){
       
      
         return actions.get(2);
      }
      else{
         return null;
      }
   }
   
   //INPUT: nada
   //OUTPUT: returns the description
   //nate-getDiscription
   public String getDescription(){
      return this.description;
   }
   
   public String getTitle(){
      return this.title;
   }
}

