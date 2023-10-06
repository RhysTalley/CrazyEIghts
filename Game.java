/** Game.java
*   Author: Rhys Talley (rt2860)
*   
*   
*   Game class for playing crazy eights in commandline
*   To be used with Player, Card, Deck classes
*
*/


import java.util.Scanner;
import java.util.ArrayList;

class Game{

    private char currentSuit; // need in case an 8 is played
    private Card faceup; 
    private Scanner input;
    private Player p1;
    private ArrayList<Card> compHand;
    private Deck cards;
    
    // sets up the Game object for play
    public Game(){
        // your code here
        cards = new Deck();
        input = new Scanner(System.in);
        p1 = new Player();
        compHand = new ArrayList<Card>();
        faceup = faceupInitialize();
        currentSuit = faceup.getSuit();
    }

   

    // Plays a game of crazy eights. 
    // Returns true to continue playing and false to stop playing
    public boolean play(){
        // your code here
        startingMessage();
        startingHands();
        while(p1.getHand().size()-1>0 && compHand.size()-1>0 && cards.canDeal()){
            printTop();
            boolean playerPlayed = true;
            while(playerPlayed){
                playerPlayed = hasntPlayed();
            }
            if(p1.getHand().size()-1==0){
                whoWins();
                return keepPlaying();
            }
            faceup = computerTurn();
        }
        whoWins();
        return keepPlaying();
    }

    /* Naive computer player AI that does one of two actions:
        1) Plays the first card in their hand that is a valid play
        2) If no valid cards, draws until they can play

        You may choose to use a different approach if you wish but
        this one is fine and will earn maximum marks
     */
     
     private Card computerTurn(){
        // your code here
        while(true){
            if(cards.canDeal()){
                for(int i=0; i<compHand.size(); i++){
                    if(!cards.canDeal()){
                        return compHand.get(0);
                    }
                    else if(compHand.get(i).getRank()==8){
                        return eightComputer(compHand.get(i));
                    }
                    else if (shorteningCharacters(i)){ // too long
                        return notEightComputer(compHand.get(i));
                    }
                }
                compHand.add(cards.deal());
                System.out.println("The computer draws a card\n");
            }
            else{break;}
        }
        return compHand.get(0);
    }
    
// you will likely wish to have several more helper methods to simplify
// and shorten the methods above.
    public void startingHands() {
        for(int i = 0; i<8; i++){
            p1.addCard(cards.deal());
            compHand.add(cards.deal());
        }
    }

    public Card faceupInitialize() {
        cards.shuffle();
        return(cards.deal());
    }

    public void startingMessage(){
        System.out.println("\n\nWelcome to Crazy Eights! You'll start with 7 cards.");
        System.out.print("Your job is to match a card in your hand "); 
        System.out.println("with the up card.");
        System.out.println("You can match it by suit or rank.");
        System.out.println("If you play an 8, you can switch the active suit.");
        System.out.println("If you run out of cards, you win!");
        System.out.println("If you make it through the whole deck then whoever has");
        System.out.println("the fewest cards left wins!");
        System.out.println("Good luck! \n\n");
    }

    public void printTop() {
        String suitString = "";
        switch(currentSuit) {
            case 'c':
                suitString = "Clubs";
                break;
            case 'h':
                suitString = "Hearts";
                break;
            case 's':
                suitString = "Spades";
                break;
            case 'd':
                suitString = "Diamonds";
                break;
            default:
                suitString = "Invalid suit";
        }
        System.out.println("** The up card is the " + faceup.toString() + "**");
        System.out.println("The current suit is " + suitString +"\n \n");
    }

    public boolean hasntPlayed(){
        Card playerCard = p1.playsTurn(cards);
            if(!cards.canDeal()){
                return false;
            }
            else if(playerCard.getRank()==8){
                p1.removeValue(playerCard);
                System.out.println("You have played the " + playerCard + "\n");
                faceup = playerCard;
                return eightPlayer();
            }
            else if(playerCard.getSuit()==currentSuit||playerCard.getRank()==faceup.getRank()){
                p1.removeValue(playerCard);
                System.out.println("You have played the " + playerCard + "\n");
                faceup = playerCard;
                currentSuit = faceup.getSuit();
                return false;
            }
            else{System.out.println("Invalid Card Selection");}
        return true;
    }

    public boolean eightPlayer(){
        while(true){
            System.out.println("\nWhat suit do you want to change it to? (c,s,d,h)\n");
            char playerSelecton = input.next().charAt(0);
            if(playerSelecton=='c' || playerSelecton=='d'){ 
                currentSuit = playerSelecton;
                printTop();
                return false;
            }
            // split into if and else if statements to shorten # of characters
            else if (playerSelecton=='s' || playerSelecton=='h'){
                currentSuit = playerSelecton;
                printTop();
                return false;
            }
            else{
                System.out.println("Invalid suit choice. Please enter c, s, d, or h.");
            }
        }
    }

    public Card eightComputer(Card c){
        int computerRandom = (int) (Math.random()*4);
        System.out.println("\n" + "The computer has played: " + c + "\n");
        faceup = c;
        compHand.remove(c);
        eightComputerText(computerRandom);
        return c;
    }

    public void whoWins(){
        if(p1.getHand().size()-1==0){
            System.out.println("\nYou have played all of your cards.\n");
            System.out.println("\n\n*** You Win! ***\n\n");
        }
        else if (compHand.size()-1==0){
            System.out.println("\nComputer has played all of its cards.\n");
            System.out.println("\n\n*** Computer Wins! ***\n\n");
        } 
        else if (!cards.canDeal()){
            System.out.println("\nThe deck ran out of cards.\n");
            if(p1.getHand().size()<compHand.size()){
                System.out.println("\n\n*** You Win! ***\n\n");
            }
            else if(p1.getHand().size()>compHand.size()){
                System.out.println("\n\n*** Computer Wins! ***\n\n");
            }
            else{System.out.println("\n\n*** It's a Draw! ***\n\n");}
        }
    }
    public boolean keepPlaying(){
        System.out.println("Do you want to play again? (y/n)");
        char yesOrNo = input.next().charAt(0);
        if(yesOrNo == 'y'){
            return true;
        }
        else{
            return false;
        }
    }

    public Card notEightComputer(Card c){
        System.out.println("\n" + "The computer has played: " + c + "\n");
        currentSuit=c.getSuit();
        compHand.remove(c);
        return c;
    }

    public boolean shorteningCharacters(int i){
        if (compHand.get(i).getSuit()==currentSuit){
            return true;
        }
        else if (compHand.get(i).getRank()==faceup.getRank()){
            return true;
        }
        else{
            return false;
        }
    }

    public void eightComputerText(int computerRandom){
        if(computerRandom==0){
            currentSuit = 'd';
            System.out.println("The computer changes the suit to Diamonds\n");
        }
        else if(computerRandom==1){
            currentSuit = 'h';
            System.out.println("The computer changes the suit to Hearts\n");
        }
        else if(computerRandom==2){
            currentSuit = 'c';
            System.out.println("The computer changes the suit to Clubs\n");
        }
        else if(computerRandom==3){
            currentSuit = 's';
            System.out.println("The computer changes the suit to Spades\n");
        }
    }
}