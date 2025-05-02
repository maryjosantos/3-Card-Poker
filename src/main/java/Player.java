/*This class represents a player in the game. It keeps track of each games current hand
and current bets as well as the total winnings for that player across multiple games. If
the player has lost more than he/she has won, that number can be negative.*/
import java.util.ArrayList;

public class Player {
    ArrayList<Card> hand;
    int anteBet;
    int playBet;
    int pairPlusBet;
    int totalWinnings;

    Player(){
        // can't touch this.hand until there is a dealer object so that would be
        // this.hand = dealerObject.dealHand() but that happens outside this class
        this.anteBet = 0;
        this.playBet = 0;
        this.pairPlusBet = 0;
        this.totalWinnings = 0;
    }

    void setAnteBet(int anteBet){
        this.anteBet = anteBet;
    }

    void setPlayBet(int playBet){
        this.playBet = playBet;
    }

    void setPairPlusBet(int pairPlusBet){
        this.pairPlusBet = pairPlusBet;
    }

    void updateTotalWinnings(int newWinnings){
        this.totalWinnings += newWinnings;
    }

    void setHand(ArrayList<Card> hand){
        this.hand = hand;
    }

}
