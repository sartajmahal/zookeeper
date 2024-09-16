//Sartaj Mahal

public class ZookeeperNode{

   public String letter; //sets public fields
   public String input;
   public ZookeeperNode left;
   public ZookeeperNode right;

   public ZookeeperNode(String input){ //constructor for the node
      this(input, null, null);
   }
   
   
   public ZookeeperNode(String input, ZookeeperNode left, ZookeeperNode right){ //this is the meat and potatoes of the node creation which sets the left and right node declaration of each branch of the tree and sets the string
      this.input = input;
      this.left = left;
      this.right = right;
   }



}