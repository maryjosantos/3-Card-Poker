import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {

    /* creates a new deck of 52 cards that have been sorted in random order */
    Deck(){
        ArrayList<Character> suits = new ArrayList<>();
        suits.add('C');
        suits.add('D');
        suits.add('S');
        suits.add('H');

        for (int i = 2; i<15; i++){
            for (Character c : suits){
                Card oneCard = new Card(c,i);
                this.add(oneCard);
            }
        }
        Collections.shuffle(this);
    }

    /* clears all the cards and create a brand new deck of 52 cards sorted in random order */
    Deck newDeck(){ // it doesn't say return type so I gave it one??? we can make it void
        // * CLEAR ALL CARDS*
        this.clear(); // is that correct?? like i need to get all the cards "back" though so I guess i call methods
        // to clear all the dealer's hands and stuff too?
        // but i can't access the dealer object without knowing what it's called so i guess i do nothing?
        return new Deck();
    }
}
