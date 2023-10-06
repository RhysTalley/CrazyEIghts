/** Deck.java
*   Author: Rhys Talley (rt2860)
*   
*   Models a typical deck of playing cards
*   To be used with Card class
*
*/
import java.lang.Math;
class Deck{

    private Card[] deck; // contains the cards to play with
    private int top; // controls the "top" of the deck to deal from

    // constructs a default Deck
    public Deck(){
        // your code here
        // for deck, nested for loop, iterate through suits and ranks
        deck = new Card[52];
        char[] arr = {'s','c','d','h'};
        for(int i=1, k=0; i<14; i++){
            for(char j:arr){
                deck[k] = new Card(j,i);
                k++;
            }
        }
        top = 0;
    }

    // Deals the top card off the deck
    public Card deal(){
        // your code here
        // will always deal the top card and then increment top
        return(deck[top++]);
    }


    // returns true provided there is a card left in the deck to deal
    public boolean canDeal(){
        // your code here
        // checks to see if the value of top exceeds the size of deck
        return(top<52);
    }

    // Shuffles the deck
    public void shuffle(){
        // your code here
        // pick two random cards in the deck, swap them with each other many times
        // utilize a for loop and Math.random

        for(int i = 0; i<10000; i++){
            int random1 = (int) (Math.random()*52);
            int random2 = (int) (Math.random()*52);
            Card temp = deck[random1];
            deck[random1]=deck[random2];
            deck[random2]=temp;
        }
    }

    // Returns a string representation of the whole deck
    public String toString(){
       // your code here
       String stringy = "";
       for(int i=0; i<deck.length; i++){
            stringy = stringy + deck[i] + "\n";
        }
        return stringy;
    }

    // you may wish to have more helper methods to simplify
    // and shorten the methods above.
}