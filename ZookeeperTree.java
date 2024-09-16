//Sartaj Mahal

import java.util.*; 
public class ZookeeperTree{
   private ZookeeperNode overallRoot; //this is the beginning of the tree
   private Map<String, String> map;
   
   public ZookeeperTree(String max){
      overallRoot = new ZookeeperNode(max, null, null);
      overallRoot.letter = "b"; //this is the base question
      map = new HashMap<>(); //we make it a hashmap so that we can connect a letter to a user's input

   }
      
 
   public void addNode(ZookeeperNode base, String root, boolean left){ //this method adds a node to the tree with an l or r notation depending on the user's input (Left = no, right = yes)
      if (left){
         base.left = new ZookeeperNode(root, null, null);
         base.left.letter = base.letter + "l";
      }
      else{
         base.right = new ZookeeperNode(root, null, null);
         base.right.letter = base.letter + "r";
      }

   }
   
   public ZookeeperNode getNode(String letter){
      ZookeeperNode declaration = overallRoot;
      while (letter.length() != 0){
         if (letter.substring(0, 1).equals("l")){
            declaration = declaration.left;
         }
         else if (letter.substring(0, 1).equals("r")){
            declaration = declaration.right;
         }
         letter = letter.substring(0, letter.length()-1);
      }
      return declaration;
   }
   
   public Map<String, String> traverse(ZookeeperNode root){ //recursively traverses through the map and then returns it
      if (root!= null){
         map.put(root.letter, root.input);
         traverse(root.left);
         traverse(root.right);
      }
      return map;
   }

}