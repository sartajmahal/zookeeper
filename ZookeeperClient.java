//Sartaj Mahal

import java.util.*;
import java.io.*;

public class ZookeeperClient{
   public static void main(String args[]) throws FileNotFoundException{
      Scanner scan = new Scanner(System.in); 
      Scanner file = new Scanner(new File("zookeeper.txt")); //loads in file
      String fileContent = file.nextLine(); //loads in file content
      ZookeeperTree t = new ZookeeperTree(fileContent.substring(0, fileContent.indexOf(": ")));
      
      while (file.hasNextLine()){ //runs until file is exhausted
         fileContent = file.nextLine(); //loads in file content
         String path = fileContent.substring(fileContent.indexOf(": ") + 2, fileContent.length());
         fileContent = fileContent.substring(0, fileContent.indexOf(": "));
         path = path.substring(1, path.length());
         ZookeeperNode base = t.getNode("b"); //this is basically the current tree head or the current question being asked to the user
         while (path.length() > 1){
            if (path.substring(0, 1).equals("l")){
               base = base.left;
            }
            else{
               base = base.right;
            }
            path = path.substring(1, path.length());
         }   
         boolean yes = path.equals("l");  //checks which side to add the node
         t.addNode(base, fileContent, yes); //adds this to the tree    
      }      
      
      ZookeeperNode base = t.getNode("b"); //continues to add to the tree
      while (base.left != null || base.right != null){
         System.out.println(base.input);
         String ans = scan.nextLine();
         if (ans.equalsIgnoreCase("yes")){
            base = base.right;
         }
         else if (ans.equalsIgnoreCase("no")){
            base = base.left;
         }
      }   
      System.out.println("Is it a(n) " + base.input + "?"); //makes a guess
      String ans = scan.nextLine();
      if (ans.equalsIgnoreCase("no")){ //if it's wrong then it tries to correct itself for the next encounter by asking what question to ask the next person
         System.out.println("What animal were you thinking of?");
         String temp = base.input;
         ans = scan.nextLine();
         System.out.println("If said yes to, what question would better describe a(n) " + base.input + " rather than a(n) " + ans + "?");
         base.input = scan.nextLine();
         t.addNode(base, temp, false); //adds it to the tree
         t.addNode(base, ans, true);
         System.out.println("Noted!");
         printToFile(t); //adds to the text file
      }
      
      else {
         System.out.println("Let's go, I guessed it!"); //succeeds in making a guess
      }
      
   }
   
   
   public static void printToFile(ZookeeperTree t) throws FileNotFoundException{ //this basically transports all the tree info to the text file
      PrintStream out = new PrintStream("zookeeper.txt");    
      List<String> fileContentL = new ArrayList<>();
      List<String> pathL = new ArrayList<>();      
      Map<String, String> map = t.traverse(t.getNode("b")); //this goes through and gets the entire map
      for (String key : map.keySet()){ // this is why I used a hashmap, so we could use the key set method
         pathL.add(key);
         fileContentL.add(map.get(key));
      } 
      for (int j = pathL.size()-1; j > 0; j--){ //this allows the array lists to hold the correct tree values that we get from reviewing out hashmap
         for (int i = 0; i < j; i++){
            if (pathL.get(i).length() > pathL.get(i+1).length()){
               String tempPath = pathL.get(i);
               String tempQues = fileContentL.get(i);
               pathL.set(i, pathL.get(i+1));
               pathL.set(i+1, tempPath);
               fileContentL.set(i, fileContentL.get(i+1));
               fileContentL.set(i+1, tempQues);
            }
         }
      }
         
      for (int i = 0; i < pathL.size(); i++){ //this prints the questions asked or the animals guess and the path that it took to get to that b + a number of "l"s or "r"s
         out.println(fileContentL.get(i) + ": " + pathL.get(i));
      } 
   }
}