// Aaron Alden
// CSCI 360
// Dr. Ericson
// Main.java

import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Main {
  public static ArrayList<Block> blockchain = new ArrayList<Block>();
  public static int difficulty = 4;

  public static void main(String[] args) {
    System.out.println(" ");
    blockchain.add(new Block("Aaron Alden's block", "0"));
    System.out.println("Attempting to mine block 1... ");
    blockchain.get(0).mineBlock(difficulty);

    blockchain.add(new Block("Porter Garbert-Smithe's block",
        blockchain.get(blockchain.size() - 1).hash));
    System.out.println("Attempting to mine block 2... ");
    blockchain.get(1).mineBlock(difficulty);

    blockchain.add(new Block(
        "Barnabus Wilson's block", blockchain.get(blockchain.size() - 1).hash));
    System.out.println("Attempting to mine block 3... ");
    blockchain.get(2).mineBlock(difficulty);

    String blockchainJson =
        new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
    System.out.println("\nThe block chain: ");
    System.out.println(blockchainJson);
    System.out.println(" ");
  }

  public static Boolean isChainValid() {
    Block currentBlock;
    Block previousBlock;

    for (int i = 1; i < blockchain.size(); i++) {
      currentBlock = blockchain.get(i);
      previousBlock = blockchain.get(i - 1);

      if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
        System.out.println("Current Hashes not equal");
        return false;
      }

      if (!previousBlock.hash.equals(currentBlock.previousHash)) {
        System.out.println("Previous Hashes are not equal");
        return false;
      }
    }
    return true;
  }
}