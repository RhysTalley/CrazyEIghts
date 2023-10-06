/** Player.java
*   Author: Rhys Talley (rt2860)
*   
*   Player class as part of Crazy Eights
*   To be used with Game, Card, Deck classes
*
*/

import java.util.ArrayList;
import java.util.Scanner;

class Player{
    
    private ArrayList<Card> hand; // the player's hand
    private Scanner input;

    public Player(){
        // your code here
        hand = new ArrayList<Card>();
        input = new Scanner(System.in);
    }

    // Adds a card to the player's hand
    public void addCard(Card c){
        // your code here
        hand.add(c);
    }
   
    // Covers all the logic regarding a human player's turn
    // public so it may be called by the Game class
    // returns Card that is played
    public Card playsTurn(Deck d){
        // your code here
        while(true){
            System.out.println(handToString());
            System.out.print("Type 'draw' to draw a card, or type number next to the");
            System.out.println(" left of the card in your hand that you wish to play\n");
            String playerInput = input.nextLine(); 
            if (playerInput.equals("draw")) {
                if(d.canDeal()){addCard(d.deal());}
                else{return (hand.get(0));}
            }
            else{
                try{
                    int number = Integer.parseInt(playerInput);
                    return hand.get(number);
                }
                catch(Exception e){
                    System.out.println("Invalid input");
                }
            }
        }
    }

    
    // Accessor for the players hand
    public ArrayList<Card> getHand(){
        // your code here
        return hand;
    }

    // Returns a printable string representing the player's hand
    public String handToString(){
        // your code here
        String yourHand = "Your hand is: \n\n";
        for(int i = 1; i<hand.size(); i++){
            yourHand = yourHand + i + "\t" + hand.get(i) + "\n";
        }
        return(yourHand);
    }

// you will likely wish to have several more helper methods to simplify
// and shorten the methods above.
    public void removeValue(Card c){
        hand.remove(c);
    }
} // end
