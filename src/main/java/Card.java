import java.util.ArrayList;

public class Card {
    char suit;
    int value;
    static ArrayList<String> spadeImages = new ArrayList<>();
    static ArrayList<String> heartImages = new ArrayList<>();
    static ArrayList<String> clubImages = new ArrayList<>();
    static ArrayList<String> diamondImages = new ArrayList<>();

    public Card(char suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public static void setImages(){
        String clubNames = "_of_clubs.png";
        String heartNames = "_of_hearts.png";
        String diamondNames = "_of_diamonds.png";
        String spadesNames = "_of_spades.png";

        for(int i = 2; i<15; i++){
            String clubCard = i+clubNames;
            String heartCard = i+heartNames;
            String diamondCard = i+diamondNames;
            String spadeCard = i+spadesNames;
            clubImages.add(clubCard);
            spadeImages.add(spadeCard);
            heartImages.add(heartCard);
            diamondImages.add(diamondCard);
        }
    }


}
