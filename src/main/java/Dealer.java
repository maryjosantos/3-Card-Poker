import java.util.ArrayList;

public class Dealer {
    Deck theDeck;
    ArrayList<Card> dealersHand;
    int winnings;

    Dealer(){
        this.theDeck = new Deck();
//        this.dealersHand = dealHand();
        this.winnings = 0;
    }

    /* returns an ArrayList<Card> of three cards removed from theDeck. Before each game starts, the Dealer class must
    check to see if there are more than 34 cards left in the deck. If not, theDeck must be reshuffled with a new set
    of 52 cards in random order. */
    public ArrayList<Card> dealHand(){
        // this is fine here because it is exactly 2 rounds of dealing 9 cards in -- there's no chance of someone getting
        // dealt cards from a new deck halfway through the round unless one player quits which we're not allowing for

        if (this.theDeck.size() <= 34){
            this.theDeck = this.theDeck.newDeck();
        }
        ArrayList<Card> hand = new ArrayList<>();

        // removing 3 cards but each time removing the first one so index doesn't change
        for (int i = 0; i<3; i++){
            hand.add(this.theDeck.get(0));
            this.theDeck.remove(0);
        }
        return hand;
    }


}
