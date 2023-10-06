/** Card.java
*   Author: Rhys Talley (rt2860)
*   
*   
*   Models a typical playing card
*
*/

class Card{
    
    private char suit;
    private int rank;

    // Initializes a card instance
    public Card(char suit, int rank){
        // your code here
        this.suit = suit;
        this.rank = rank;
    }

    // Accessor for suit
    public char getSuit(){
        // your code here;
        return suit;
    }
    
    // Accessor for rank
    public int getRank(){
        // your code here;
        return rank;
    }

    // Returns a human readable form of the card (eg. King of Diamonds)
    public String toString(){
        // your code here
        String rankString = switchRank();
        String suitString = switchSuit();
        return(rankString+suitString);
    }

    private String switchRank(){ // to make toString under 20 lines
        String rankString;
        switch(rank) {
            case 1:
                rankString = "Ace of ";
                break;
            case 11:
                rankString = "Jack of ";
                break;
            case 12:
                rankString = "Queen of ";
                break;
            case 13:
                rankString = "King of ";
                break;
            default:
                rankString = rank + " of ";
        }
        return rankString;
    }

    private String switchSuit(){ // to make toString under 20 lines
        String suitString;
        switch(suit) {
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
        return suitString;
    }
}