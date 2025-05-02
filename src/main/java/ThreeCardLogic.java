import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ThreeCardLogic {

    /* returns  an integer value representing the value of the hand passed in
     * 0 if the hand just has a high card
     * 1 for a straight flush
     * 2 for three of a kind
     * 3 for a straight
     * 4 for a flush
     * 5 for a pair */
    public static int evalHand(ArrayList<Card> hand){

        ArrayList<Integer> valuesSorted = new ArrayList<>();
        Set<Integer> cardValues = new HashSet<>();
        Set<Character> cardSuits = new HashSet<>();

        for (Card c : hand){
            cardValues.add(c.value);
            valuesSorted.add(c.value);
            cardSuits.add(c.suit);
        }

        // sorts cards in ascending order
        valuesSorted.sort(null);
        // finds differences between cards to check for straights
        int firstTwoDiff = valuesSorted.get(1) - valuesSorted.get(0);
        int secondTwoDiff = valuesSorted.get(2) - valuesSorted.get(1);

        // evaluating hands
        if(cardValues.size() == 1){ // three of a kind
            return 2;
        }
        if (cardValues.size() == 2){ // pair
            return 5;
        }
        if (firstTwoDiff == 1 & secondTwoDiff == 1){ // straights
            if (cardSuits.size() == 1){ // straight flush
                return 1;
            }
            else{ // straight
                return 3;
            }
        }
        if(valuesSorted.get(2) == 14){ // straights a-2-3
            if (valuesSorted.get(0) == 2 & valuesSorted.get(1) == 3){
                if(cardSuits.size() == 1){// straight flush
                    return 1;
                }
                else{ // straight
                    return 3;
                }
            }
        }
        if(cardSuits.size() == 1){ // flush
            return 4;
        }

        return 0;
    }


    /* The method evalPPWinnings will return the amount won for the PairPlus bet. It will
    evaluate the hand and then evaluate the winnings and return the amount won. If the
    player lost the Pair Plus bet, it will just return 0. */
    public static int evalPPWinnings(ArrayList<Card> hand, int bet){
        int handValue = evalHand(hand);
        if (handValue == 1){
            return 40*bet;
        }
        else if(handValue == 2){
            return 30*bet;
        }
        else if(handValue == 3){
            return 6*bet;
        }
        else if(handValue == 4){
            return 3*bet;
        }
        else if(handValue == 5){
            return bet;
        }
        else{
            return 0;
        }
    }

    /* The method compareHands will compare the two hands passed in and return an integer based on which hand won:
    * 0 if neither hand won
    * 1 if the dealer hand won
    * 2 if the player hand won */
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player){
        // do i put the queen or higher stuff here or do we check that condition before even getting here

        int dealerHandEval = evalHand(dealer);
        int playerHandEval = evalHand(player);

        // if higher card, take care of that first so it doesn't mess with conditionals for other rankings
        // ties in higher card can be dealt with in the larger if block
        if(dealerHandEval == 0 | playerHandEval == 0){
            if(dealerHandEval == 0 & playerHandEval != 0){
                return 2;
            }
            if(dealerHandEval != 0 & playerHandEval == 0){
                return 1;
            }
        }

        if (dealerHandEval < playerHandEval){
            return 1;
        }
        else if (dealerHandEval > playerHandEval){
            return 2;
        }
        else{ // same hand, look at cards to identify winner
            // ties broken by card value so sort card values
            ArrayList<Integer> dealerSorted = new ArrayList<>();
            for (Card d : dealer){
                dealerSorted.add(d.value);
            }
            dealerSorted.sort(null);
            ArrayList<Integer> playerSorted = new ArrayList<>();
            for (Card p : player){
                playerSorted.add(p.value);
            }
            playerSorted.sort(null);
            if(dealerHandEval == 1 | dealerHandEval == 3){ // break straight or straight flush tie
                // higher straight wins
                int dealerMin = dealerSorted.get(0);
                int playerMin = playerSorted.get(0);
                if (dealerMin > playerMin){
                    return 1;
                }
                else if (dealerMin < playerMin){
                    return 2;
                }
                else{ // if same values, tie
                    return 0;
                }
            }
            else if(dealerHandEval == 4 | dealerHandEval == 2 | dealerHandEval == 0){ // break flush or three of a kind tie or high card
                // higher card wins
                if(dealerSorted.get(2) > playerSorted.get(2)){
                    return 1;
                }
                else if (dealerSorted.get(2) < playerSorted.get(2)){
                    return 2;
                }
                else{ // if same high card, tie (three of a kind can never tie)
                    return 0;
                }
            }
            else { // regular pair
                int dealerSingle;
                int playerSingle;
                // higher pair wins
                if (dealerSorted.get(1) > playerSorted.get(1)){
                    return 1;
                }
                else if (dealerSorted.get(1) < playerSorted.get(1)){
                    return 2;
                }
                else{ // if same pair, higher 3rd card wins
                    if(dealerSorted.get(0) == dealerSorted.get(1)){ // figure out what value the dealer pair is
                        dealerSingle = dealerSorted.get(2);
                    }
                    else{
                        dealerSingle = dealerSorted.get(0);
                    }
                    if(playerSorted.get(0) == playerSorted.get(1)){ // figure out what value the player pair is
                        playerSingle = playerSorted.get(2);
                    }
                    else {
                        playerSingle = playerSorted.get(0);
                    }
                    if (dealerSingle > playerSingle){
                        return 1;
                    }
                    else if (dealerSingle < playerSingle){
                        return 2;
                    }
                    else{ // if same 3rd card, tie
                        return 0;
                    }
                }
            }
        }
    }
}
