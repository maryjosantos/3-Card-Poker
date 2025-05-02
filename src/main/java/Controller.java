import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Controller implements Initializable {


    Player playerOne;
    Player playerTwo;
    Dealer theDealer;

    @FXML private VBox root;
    @FXML private HBox containHeader;

    @FXML private Label gameHeading;
    @FXML private HBox containPlayerSettings;

    @FXML private VBox player1Setup;
    @FXML private Label player1Title;

    @FXML private Button p1SaveName;

    @FXML private VBox player2Setup;
    @FXML private Label player2Title;

    @FXML private Button p2SaveName;

    @FXML private Button startGame;
    @FXML private Label startSpace;
    @FXML private Button exitGame;

    @FXML private Region multiUseSpace;
    @FXML private Region multiUseSpace1;
    @FXML private Region multiUseSpace2;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }


    public void playerOneInitialize(ActionEvent e) throws IOException {
        p1SaveName.setDisable(true);
        if (p2SaveName.isDisabled()){
            startGame.setDisable(false);
        }
    }

    public void playerTwoInitialize(ActionEvent e) throws IOException {
        p2SaveName.setDisable(true);
        if (p1SaveName.isDisabled()){
            startGame.setDisable(false);
        }
    }


    // Game Play

    // menu setup
    @FXML private MenuBar menuBar;
    @FXML private Menu options;
    @FXML private MenuItem exitMenu;
    @FXML private MenuItem freshStart;
    @FXML private CheckMenuItem newLook;

    @FXML private Pane exitScreen;
    @FXML private VBox exitDisplay;
    @FXML private Label prompt;
    @FXML private Label promptSpace1;
    @FXML private Button resume;
    @FXML private Label promptSpace2;
    @FXML private Button exit;

    boolean showedPlayerCards = false;
    boolean showedDealerCards = false;

    public void setFreshStart(ActionEvent e) throws IOException {

        cleanNextRound();
        p1Winnings.setText("Total Net Winnings: 0");
        p2Winnings.setText("Total Net Winnings: 0");
        dealerWinnings.setText("Winnings: 0");

        resetCards();

        playerActions = 0;
        p1PlaceAnte.clear();
        p2PlaceAnte.clear();
        p1PlacePairPlus.clear();
        p2PlacePairPlus.clear();

        player1PlayFoldBox.setVisible(false);
        player2PlayFoldBox.setVisible(false);
        readyToDeal.setVisible(false);
        nextRound.setVisible(false);

        storeItemsInListView.clear();
        commentary.setItems(storeItemsInListView);

        gamePlayArea.setVisible(false);
        startGameButton.setVisible(true);
        startGameButton.setDisable(false);


    }

    boolean darkMode = true;
    public void setNewLook(ActionEvent e) throws IOException {

        if (((CheckMenuItem)e.getSource()).isSelected()) {
            darkMode = false;
            System.out.println("Fresh Look Selected");
            root2.getStylesheets().clear();
            root2.getStylesheets().add("/styles/gamePlayV2.css");

        }
        else {
            darkMode = true;
            System.out.println("Fresh Look DeSelected");
            root2.getStylesheets().clear();
            root2.getStylesheets().add("/styles/gamePlayV1.css");
        }

        if(!showedDealerCards){
            Background dealerCardBgdColor;
            if(darkMode){
                Color maroon = Color.MAROON;
                dealerCardBgdColor = new Background(new BackgroundFill(maroon, new CornerRadii(5), null));
            }
            else{
                Color lavender = Color.web("#91a8d1");
                dealerCardBgdColor = new Background(new BackgroundFill(lavender, new CornerRadii(5), null));
            }

            dealerCard1.setBackground(dealerCardBgdColor);
            dealerCard2.setBackground(dealerCardBgdColor);
            dealerCard3.setBackground(dealerCardBgdColor);
        }

    }

    @FXML private BorderPane root2;

    @FXML private Button startGameButton;
    @FXML private Label rules;

    @FXML private HBox gamePlayArea;
    @FXML private VBox playerOnePlay;
    @FXML private Label pOneName;

    @FXML private HBox player1Hand;
    @FXML private Button p1Card1;
    @FXML private Label p1space1;
    @FXML private Button p1Card2;
    @FXML private Label p1space2;
    @FXML private Button p1Card3;

    @FXML private Label p1Ante;
    @FXML private Label p1PlayWager;
    @FXML private Label p1PairPlus;
    @FXML private Label p1Winnings;

    @FXML private Label p1BetRule;
    @FXML private TextField p1PlaceAnte;
    @FXML private TextField p1PlacePairPlus;
    @FXML private Button p1PlaceBets;

    @FXML private Region space1;
    @FXML private VBox dealerPlay;
    @FXML private Label dealerName;
    @FXML private HBox dealerWinningsAndDeck;
    @FXML private Button deckIcon;
    @FXML private Label deckSpace;

    @FXML private Label dealerHandSpace;

    @FXML private HBox dealerHand;
    @FXML private Button dealerCard1;
    @FXML private Label dealerSpace1;
    @FXML private Button dealerCard2;
    @FXML private Label dealerSpace2;
    @FXML private Button dealerCard3;
    @FXML private Button readyToDeal;

    @FXML private Label dealerWinnings;

    @FXML private Button setWinnings;
    @FXML private Region space2;
    @FXML private VBox playerTwoPlay;
    @FXML private Label pTwoName;
    @FXML private HBox player2Hand;
    @FXML private Button p2Card1;
    @FXML private Label p2space1;
    @FXML private Button p2Card2;
    @FXML private Label p2space2;
    @FXML private Button p2Card3;

    @FXML private Label p2Ante;
    @FXML private Label p2PlayWager;
    @FXML private Label p2PairPlus;
    @FXML private Label p2Winnings;

    @FXML private Label p2BetRule;
    @FXML private TextField p2PlaceAnte;
    @FXML private TextField p2PlacePairPlus;
    @FXML private Button p2PlaceBets;

    @FXML private ListView<String> commentary = new ListView<>();
    ObservableList<String> storeItemsInListView = FXCollections.observableArrayList();


    public void beginGame(ActionEvent e) throws IOException {
        // go to gameplay FXML page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/gamePlay.fxml"));
        Controller controller = loader.getController();
        Parent root2 = loader.load();
        root2.getStylesheets().add("/styles/gamePlayV1.css");
        root.getScene().setRoot(root2);
    }

    public void exitGame(ActionEvent e) throws IOException {
        Stage stage = (Stage) exitGame.getScene().getWindow();
        stage.close();
    }

    private Integer playerActions = 0;
    public void showGamePlay(ActionEvent e) throws IOException {
        startGameButton.setVisible(false);
        gamePlayArea.setVisible(true);
        theDealer = new Dealer();
        playerOne = new Player();
        playerTwo = new Player();
        Card.setImages();
    }

    public void setPlayerOneBets(ActionEvent e) throws IOException {
        boolean validAnteBet = false;
        int p1AnteBet = 0;
        if(playerOne.anteBet == 0){
            p1AnteBet = Integer.parseInt(p1PlaceAnte.getText());
            validAnteBet = false;
            if (p1AnteBet >= 5 & p1AnteBet <= 25){
                validAnteBet = true;
            }
        }
        else{
            p1AnteBet = playerOne.anteBet;
            validAnteBet = true;
        }


        boolean validPairPlusBet = false;
        int p1PairPlusBet = 0;
        if(p1PlacePairPlus.getText().length() == 0){
            validPairPlusBet = true;
        }
        else{
            p1PairPlusBet = Integer.parseInt(p1PlacePairPlus.getText());
            if(p1PairPlusBet >= 5 & p1PairPlusBet <= 25){
                validPairPlusBet = true;
            }
        }

        if(validAnteBet & validPairPlusBet){
            playerOne.setAnteBet(p1AnteBet);
            playerOne.setPairPlusBet(p1PairPlusBet);

//            playerOne.updateTotalWinnings(playerOne.anteBet*-1);
//            playerOne.updateTotalWinnings(playerOne.pairPlusBet*-1);

            p1Ante.setText("Ante Bet: " + playerOne.anteBet);
            p1PairPlus.setText("Pair Plus Bet: " + playerOne.pairPlusBet);
            p1Winnings.setText("Total Net Winnings: "+playerOne.totalWinnings);

            p1PlaceBets.setDisable(true);
            p1BetRule.setVisible(false);
            p1PlaceAnte.setVisible(false);
            p1PlacePairPlus.setVisible(false);
            p1PlaceBets.setVisible(false);

            storeItemsInListView.add("Player 1 places ante bet: $" + playerOne.anteBet);
            if (playerOne.pairPlusBet>0){
                storeItemsInListView.add("Player 1 places Pair Plus bet: $" + playerOne.pairPlusBet);
            }
            commentary.setItems(storeItemsInListView);

            if(p2PlaceBets.isDisabled()){
                readyToDeal.setVisible(true);
            }
        }
    }

    public void setPlayerTwoBets(ActionEvent e) throws IOException {
        boolean validAnteBet = false;
        int p2AnteBet = 0;
        if(playerTwo.anteBet == 0){
            p2AnteBet = Integer.parseInt(p2PlaceAnte.getText());
            validAnteBet = false;
            if (p2AnteBet >= 5 & p2AnteBet <= 25){
                validAnteBet = true;
            }
        }
        else{
            p2AnteBet = playerTwo.anteBet;
            validAnteBet = true;
        }

        boolean validPairPlusBet = false;
        int p2PairPlusBet = 0;
        if(p2PlacePairPlus.getText().length() == 0){
            validPairPlusBet = true;
        }
        else{
            p2PairPlusBet = Integer.parseInt(p2PlacePairPlus.getText());
            if(p2PairPlusBet >= 5 & p2PairPlusBet <= 25){
                validPairPlusBet = true;
            }
        }

        if(validAnteBet & validPairPlusBet){
            playerTwo.setAnteBet(p2AnteBet);
            playerTwo.setPairPlusBet(p2PairPlusBet);

//            playerTwo.updateTotalWinnings(p2AnteBet*-1);
//            playerTwo.updateTotalWinnings(p2PairPlusBet*-1);

            p2Ante.setText("Ante Bet: " + playerTwo.anteBet);
            p2PairPlus.setText("Pair Plus Bet: " + playerTwo.pairPlusBet);
            p2Winnings.setText("Total Net Winnings: "+playerTwo.totalWinnings);

            p2PlaceBets.setDisable(true);
            p2BetRule.setVisible(false);
            p2PlaceAnte.setVisible(false);
            p2PlacePairPlus.setVisible(false);
            p2PlaceBets.setVisible(false);

            storeItemsInListView.add("Player 2 places Ante bet: $" + playerTwo.anteBet);
            if (playerTwo.pairPlusBet>0){
                storeItemsInListView.add("Player 2 places Pair Plus bet: $" + playerTwo.pairPlusBet);
            }
            commentary.setItems(storeItemsInListView);

            if(p1PlaceBets.isDisabled()){
                readyToDeal.setVisible(true);
            }
        }
    }

    @FXML private VBox player1PlayFoldBox;
    @FXML private HBox player1PlayFold;
    @FXML private Button p1Play;
    @FXML private Button p1Fold;
    @FXML private Label p1PlayLabel;

    @FXML private VBox player2PlayFoldBox;
    @FXML private HBox player2PlayFold;
    @FXML private Button p2Play;
    @FXML private Button p2Fold;
    @FXML private Label p2PlayLabel;

    public Background cardFace(Card card){
        String cardImageName="";
        char cardSuit = card.suit;
        int cardVal = card.value;
        if(cardSuit == 'S'){
            cardImageName = Card.spadeImages.get(cardVal-2);
        }
        else if(cardSuit == 'H'){
            cardImageName = Card.heartImages.get(cardVal-2);
        }
        else if(cardSuit == 'D'){
            cardImageName = Card.diamondImages.get(cardVal-2);
        }
        else if(cardSuit == 'C'){
            cardImageName = Card.clubImages.get(cardVal-2);
        }
        cardImageName = "/images/" + cardImageName;

        Image cardImage = new Image(getClass().getResource(cardImageName).toExternalForm());
        BackgroundImage bgdImage = new BackgroundImage(cardImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(60,87, false, false, true, true));
        Background cardBgd = new Background(bgdImage);
        return cardBgd;
    }

    public void showCards(ActionEvent e) throws IOException {
        showedPlayerCards = true;
        readyToDeal.setVisible(false);
        playerOne.hand = theDealer.dealHand();
        playerTwo.hand = theDealer.dealHand();
        theDealer.dealersHand = theDealer.dealHand();

        Background p1Card1Image = cardFace(playerOne.hand.get(0));
        Background p1Card2Image = cardFace(playerOne.hand.get(1));
        Background p1Card3Image = cardFace(playerOne.hand.get(2));

        Background p2Card1Image = cardFace(playerTwo.hand.get(0));
        Background p2Card2Image = cardFace(playerTwo.hand.get(1));
        Background p2Card3Image = cardFace(playerTwo.hand.get(2));

        p1Card1.setBackground(p1Card1Image);
        p1Card2.setBackground(p1Card2Image);
        p1Card3.setBackground(p1Card3Image);

        p2Card1.setBackground(p2Card1Image);
        p2Card2.setBackground(p2Card2Image);
        p2Card3.setBackground(p2Card3Image);

        PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
        pause1.setOnFinished(event -> p1Card1.setVisible(true));
        pause1.play();
        //p1Card1.setVisible(true);
        PauseTransition pause2 = new PauseTransition(Duration.seconds(2));
        pause2.setOnFinished(event -> p1Card2.setVisible(true));
        pause2.play();
        //p1Card2.setVisible(true);

        PauseTransition pause3 = new PauseTransition(Duration.seconds(3));
        pause3.setOnFinished(event -> p1Card3.setVisible(true));
        pause3.play();
        //p1Card3.setVisible(true);

        PauseTransition pause4 = new PauseTransition(Duration.seconds(4));
        pause4.setOnFinished(event -> p2Card1.setVisible(true));
        pause4.play();

        PauseTransition pause5 = new PauseTransition(Duration.seconds(5));
        pause5.setOnFinished(event -> p2Card2.setVisible(true));
        pause5.play();

        PauseTransition pause6 = new PauseTransition(Duration.seconds(6));
        pause6.setOnFinished(event -> p2Card3.setVisible(true));
        pause6.play();

        Background dealerCardBgdColor;
        if(darkMode){
            Color maroon = Color.MAROON;
            dealerCardBgdColor = new Background(new BackgroundFill(maroon, new CornerRadii(5), null));
        }
        else{
            Color lavender = Color.web("#91a8d1");
            dealerCardBgdColor = new Background(new BackgroundFill(lavender, new CornerRadii(5), null));
        }

        dealerCard1.setBackground(dealerCardBgdColor);
        dealerCard2.setBackground(dealerCardBgdColor);
        dealerCard3.setBackground(dealerCardBgdColor);

        PauseTransition pause7 = new PauseTransition(Duration.seconds(7));
        pause7.setOnFinished(event -> dealerCard1.setVisible(true));
        pause7.play();

        PauseTransition pause8 = new PauseTransition(Duration.seconds(8));
        pause8.setOnFinished(event -> dealerCard2.setVisible(true));
        pause8.play();

        PauseTransition pause9 = new PauseTransition(Duration.seconds(9));
        pause9.setOnFinished(event -> dealerCard3.setVisible(true));
        pause9.play();

        PauseTransition pause10 = new PauseTransition(Duration.seconds(10));
        pause10.setOnFinished(event -> player1PlayFoldBox.setVisible(true));
        pause10.play();

        PauseTransition pause11 = new PauseTransition(Duration.seconds(10));
        pause11.setOnFinished(event -> player2PlayFoldBox.setVisible(true));
        pause11.play();

    }

    public void p1PlayAction(ActionEvent e) throws IOException {
        playerActions++;
        playerOne.playBet = playerOne.anteBet;
        //playerOne.updateTotalWinnings(playerOne.playBet*-1);

        int totalBet = playerOne.anteBet+playerOne.playBet;
        p1Ante.setText("Ante Bet: "+playerOne.anteBet);
        p1PlayWager.setText("Play Bet: "+playerOne.playBet);
        p1Winnings.setText("Winnings: "+ playerOne.totalWinnings);

        storeItemsInListView.add("Player 1 plays");

        int pairPlusWin = ThreeCardLogic.evalPPWinnings(playerOne.hand, playerOne.pairPlusBet);
        PauseTransition pause2 = new PauseTransition(Duration.seconds(1.5));
        pause2.setOnFinished(event -> {
            if(pairPlusWin!=0){
                storeItemsInListView.add("Player 1 wins Pair Plus");
                // in pair plus, you get back what you bet plus the winnings
                //playerOne.updateTotalWinnings(playerOne.pairPlusBet);
                playerOne.updateTotalWinnings(pairPlusWin);
                theDealer.winnings-= pairPlusWin;
                dealerWinnings.setText("Winnings: "+theDealer.winnings);
                p1Winnings.setText("Winnings: "+playerOne.totalWinnings);
            }
            else{
                storeItemsInListView.add("Player 1 loses Pair Plus");
                playerOne.updateTotalWinnings(playerOne.pairPlusBet*-1); //added
                p1Winnings.setText("Winnings: "+playerOne.totalWinnings); //added
                theDealer.winnings += playerOne.pairPlusBet;
                dealerWinnings.setText("Winnings: "+theDealer.winnings);
            }
            playerOne.pairPlusBet = 0;
            p1PairPlus.setText("Pair Plus Bet: Evaluated");
            commentary.setItems(storeItemsInListView);
        });
        pause2.play();

        player1PlayFoldBox.setVisible(false);

        if(playerActions == 2){
            PauseTransition pause = new PauseTransition(Duration.seconds(4));
            pause.setOnFinished(event -> showDealerCards());
            pause.play();
        }
    }


    public void p2PlayAction(ActionEvent e) throws IOException {
        playerActions++;
        playerTwo.playBet = playerTwo.anteBet;
        int totalBet = playerTwo.anteBet+playerTwo.playBet;
        //playerTwo.updateTotalWinnings(playerTwo.playBet*-1);

        p2Ante.setText("Ante Bet: "+playerTwo.anteBet);
        p2PlayWager.setText("Play Bet: "+playerTwo.playBet);
        p2Winnings.setText("Winnings: "+playerTwo.totalWinnings);

        storeItemsInListView.add("Player 2 plays");

        int pairPlusWin = ThreeCardLogic.evalPPWinnings(playerTwo.hand, playerTwo.pairPlusBet);

        PauseTransition pause2 = new PauseTransition(Duration.seconds(1.5));
        pause2.setOnFinished(event -> {
            if(pairPlusWin!=0){
                storeItemsInListView.add("Player 2 wins Pair Plus");
                // in pair plus, you get back what you bet plus the winnings
                //playerTwo.updateTotalWinnings(playerTwo.pairPlusBet);
                playerTwo.updateTotalWinnings(pairPlusWin);
                theDealer.winnings-= pairPlusWin;
                dealerWinnings.setText("Winnings: "+theDealer.winnings);
                p2Winnings.setText("Winnings: "+playerTwo.totalWinnings);
            }
            else{
                storeItemsInListView.add("Player 2 loses Pair Plus");
                playerTwo.updateTotalWinnings(playerTwo.pairPlusBet*-1); // added
                p2Winnings.setText("Winnings: "+playerTwo.totalWinnings); // added
                theDealer.winnings += playerTwo.pairPlusBet;
                dealerWinnings.setText("Winnings: "+theDealer.winnings);
            }
            playerTwo.pairPlusBet = 0;
            p2PairPlus.setText("Pair Plus Bet: Evaluated");
            commentary.setItems(storeItemsInListView);
        });
        pause2.play();

        player2PlayFoldBox.setVisible(false);

        if(playerActions == 2){
            PauseTransition pause = new PauseTransition(Duration.seconds(4));
            pause.setOnFinished(event -> showDealerCards());
            pause.play();
        }
    }

    public void p1FoldAction(ActionEvent e) throws IOException {
        playerActions++;

        theDealer.winnings += playerOne.anteBet;
        theDealer.winnings += playerOne.pairPlusBet;
        dealerWinnings.setText("Winnings: "+theDealer.winnings);

        playerOne.updateTotalWinnings(playerOne.anteBet*-1); // added
        playerOne.updateTotalWinnings(playerOne.pairPlusBet*-1); // added

        playerOne.anteBet = 0;
        playerOne.pairPlusBet = 0;

        p1Ante.setText("Ante Bet: Folded");
        p1PlayWager.setText("Play Bet: Folded");
        p1PairPlus.setText("Pair Plus Bet: Folded");
        p1Winnings.setText("Winnings: "+playerOne.totalWinnings);

        storeItemsInListView.add("Player 1 folds");
        commentary.setItems(storeItemsInListView);
        player1PlayFoldBox.setVisible(false);

        if(playerActions == 2){
            //showDealerCards();
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> showDealerCards());
            pause.play();
        }
    }

    public void p2FoldAction(ActionEvent e) throws IOException {
        playerActions++;

        theDealer.winnings += playerTwo.anteBet;
        theDealer.winnings += playerTwo.pairPlusBet;
        dealerWinnings.setText("Winnings: "+theDealer.winnings);

        playerTwo.updateTotalWinnings(playerTwo.anteBet*-1); // added
        playerTwo.updateTotalWinnings(playerTwo.pairPlusBet*-1); // added

        playerTwo.anteBet = 0;
        playerTwo.pairPlusBet = 0;

        p2Ante.setText("Ante Bet: Folded");
        p2PlayWager.setText("Play Bet: Folded");
        p2PairPlus.setText("Pair Plus Bet: Folded");
        p2Winnings.setText("Winnings: "+playerTwo.totalWinnings);

        storeItemsInListView.add("Player 2 folds");
        commentary.setItems(storeItemsInListView);
        player2PlayFoldBox.setVisible(false);


        if(playerActions == 2){
            //showDealerCards();
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> showDealerCards());
            pause.play();
        }
    }

    public void showDealerCards(){
        showedDealerCards = true;
        playerActions = 0;
        Card.setImages();
        Background d1CardImage = cardFace(theDealer.dealersHand.get(0));
        Background d2CardImage = cardFace(theDealer.dealersHand.get(1));
        Background d3CardImage = cardFace(theDealer.dealersHand.get(2));

        PauseTransition pause = new PauseTransition(Duration.seconds(0));
        pause.setOnFinished(event -> dealerCard1.setBackground(d1CardImage));
        pause.play();

        PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
        pause2.setOnFinished(event -> dealerCard2.setBackground(d2CardImage));
        pause2.play();

        PauseTransition pause3 = new PauseTransition(Duration.seconds(2));
        pause3.setOnFinished(event -> dealerCard3.setBackground(d3CardImage));
        pause3.play();

        PauseTransition pause4 = new PauseTransition(Duration.seconds(4));
        pause4.setOnFinished(event -> evaluateAllHands());
        pause4.play();

    }

    @FXML private Button nextRound;

    public void evaluateAllHands(){
        int dealerHandEval = ThreeCardLogic.evalHand(theDealer.dealersHand);
        boolean queenOrHigher = false;

        if(dealerHandEval>0){
            queenOrHigher = true;
        }
        else{
            for (Card c : theDealer.dealersHand) {
                if(c.value >= 12){
                    queenOrHigher = true;
                }
            }
        }

        if(!queenOrHigher){
            if(playerOne.anteBet != 0 | playerTwo.anteBet != 0){
                storeItemsInListView.add("Dealer does not have at least Queen high; ante wager is pushed");
                // if pushed, no change in winnings, ante just stays the same
                if(playerOne.anteBet != 0){
                    // playerOne.updateTotalWinnings(playerOne.playBet);
                    playerOne.playBet = 0;
                    p1Ante.setText("Ante Bet: "+playerOne.anteBet);
                    p1PlayWager.setText("Play Bet: Returned"); // no change in winnings
                    p1Winnings.setText("Total Net Winnings: "+playerOne.totalWinnings);
                }
                if(playerTwo.anteBet != 0){
                    // playerTwo.updateTotalWinnings(playerTwo.playBet);
                    playerTwo.playBet = 0;
                    p2Ante.setText("Ante Bet: "+playerTwo.anteBet);
                    p2PlayWager.setText("Play Bet: Returned"); // no change in winnings
                    p2Winnings.setText("Total Net Winnings: "+playerTwo.totalWinnings);
                }
            }
            commentary.setItems(storeItemsInListView);
        }
        else{
            int dealerVSplayer1 = -1;
            int dealerVSplayer2 = -1;

            if(playerOne.anteBet != 0){
                dealerVSplayer1 = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
                if(dealerVSplayer1 == 0){
                    storeItemsInListView.add("Dealer and Player 1 tied");
                    // if tied, no winning or losing
                    //playerOne.updateTotalWinnings(playerOne.anteBet);
                    //playerOne.updateTotalWinnings(playerOne.playBet);
                    p1Winnings.setText("Total Net Winnings: "+playerOne.totalWinnings);
                }
                else if(dealerVSplayer1 == 1){
                    storeItemsInListView.add("Dealer beats Player 1");
                    // if lose, player loses their bets
                    playerOne.updateTotalWinnings(playerOne.anteBet*-1);
                    playerOne.updateTotalWinnings(playerOne.playBet*-1);
                    p1Winnings.setText("Total Net Winnings: "+playerOne.totalWinnings);

                    theDealer.winnings = theDealer.winnings + playerOne.anteBet*2;
                }
                else{
                    storeItemsInListView.add("Player 1 beats Dealer");
                    // player wins twice their ante plus twice their play
                    playerOne.updateTotalWinnings(playerOne.playBet);
                    playerOne.updateTotalWinnings(playerOne.anteBet);
                    p1Winnings.setText("Total Net Winnings: "+playerOne.totalWinnings);

                    theDealer.winnings = theDealer.winnings - playerOne.anteBet*2;
                }

                p1Ante.setText("Ante Bet: Evaluated");
                p1PlayWager.setText("Play Bet: Evaluated");
                p1Winnings.setText("Total Net Winnings: "+playerOne.totalWinnings);
                dealerWinnings.setText("Winnings: "+theDealer.winnings);
            }

            if(playerTwo.anteBet != 0){
                dealerVSplayer2 = ThreeCardLogic.compareHands(theDealer.dealersHand, playerTwo.hand);
                if(dealerVSplayer2 == 0){
                    // if tie, no change in winnings for either
                    storeItemsInListView.add("Dealer and Player 2 tied");
//                    playerTwo.updateTotalWinnings(playerTwo.anteBet);
//                    playerTwo.updateTotalWinnings(playerTwo.playBet);
                    p2Winnings.setText("Total Net Winnings: "+playerTwo.totalWinnings);
                }
                else if(dealerVSplayer2 == 1){
                    storeItemsInListView.add("Dealer beats Player 2");
                    // player loses ante and play
                    playerTwo.updateTotalWinnings(playerTwo.anteBet*-1);
                    playerTwo.updateTotalWinnings(playerTwo.playBet*-1);
                    p2Winnings.setText("Total Net Winnings: "+playerTwo.totalWinnings);

                    theDealer.winnings = theDealer.winnings + playerTwo.anteBet*2;
                }
                else{
                    storeItemsInListView.add("Player 2 beats Dealer");
                    // player makes ante*2 and play *2
                    playerTwo.updateTotalWinnings(playerTwo.playBet);
                    playerTwo.updateTotalWinnings(playerTwo.anteBet);
                    p2Winnings.setText("Total Net Winnings: "+playerTwo.totalWinnings);

                    theDealer.winnings = theDealer.winnings - playerTwo.anteBet*2;

                }
                p2Ante.setText("Ante Bet: Evaluated");
                p2PlayWager.setText("Play Bet: Evaluated");
                p2Winnings.setText("Total Net Winnings: "+playerTwo.totalWinnings);
                dealerWinnings.setText("Winnings: "+theDealer.winnings);
            }

            playerOne.anteBet = 0;
            playerOne.playBet = 0;
            playerOne.pairPlusBet = 0;
            playerTwo.anteBet = 0;
            playerTwo.playBet = 0;
            playerTwo.pairPlusBet = 0;

            commentary.setItems(storeItemsInListView);

        }

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> nextRound.setVisible(true));
        pause.play();
    }

    public void resetCards(){
        p1Card1.setVisible(false);
        p1Card2.setVisible(false);
        p1Card3.setVisible(false);
        p2Card1.setVisible(false);
        p2Card2.setVisible(false);
        p2Card3.setVisible(false);

        Background dealerCardBgdColor;
        if(darkMode){
            Color maroon = Color.MAROON;
            dealerCardBgdColor = new Background(new BackgroundFill(maroon, new CornerRadii(5), null));
        }
        else{
            Color lavender = Color.web("#91a8d1");
            dealerCardBgdColor = new Background(new BackgroundFill(lavender, new CornerRadii(5), null));
        }

        dealerCard1.setVisible(false);
        dealerCard1.setBackground(dealerCardBgdColor);
        dealerCard2.setVisible(false);
        dealerCard2.setBackground(dealerCardBgdColor);
        dealerCard3.setVisible(false);
        dealerCard3.setBackground(dealerCardBgdColor);
    }

    public void selectNextRound(ActionEvent e) throws IOException {
        nextRound.setVisible(false);
        showedPlayerCards=false;
        showedDealerCards=false;
        resetCards();

        p1PlaceAnte.clear();
        p1PlacePairPlus.clear();
        p2PlaceAnte.clear();
        p2PlacePairPlus.clear();

        PauseTransition pause = new PauseTransition(Duration.seconds(0));
        if(playerOne.anteBet != 0 | playerTwo.anteBet != 0){
            pause.setOnFinished(event -> keepingAntes());
            pause.play();
        }
        else{
            pause.setOnFinished(event -> cleanNextRound());
            pause.play();
        }
    }

    public void cleanNextRound(){
        p1Ante.setText("Ante Bet: ");
        p1PlayWager.setText("Play Bet: ");
        p1PairPlus.setText("Pair Plus Bet: ");
        p1BetRule.setVisible(true);
        p1PlaceAnte.setVisible(true);
        p1PlacePairPlus.setVisible(true);
        p1PlaceBets.setVisible(true);
        p1PlaceBets.setDisable(false);

        p2Ante.setText("Ante Bet: ");
        p2PlayWager.setText("Play Bet: ");
        p2PairPlus.setText("Pair Plus Bet: ");
        p2BetRule.setVisible(true);
        p2PlaceAnte.setVisible(true);
        p2PlacePairPlus.setVisible(true);
        p2PlaceBets.setVisible(true);
        p2PlaceBets.setDisable(false);

        // once the players press placeBets buttons, should go back to normal gamePlay
    }

    public void keepingAntes(){
        if(playerOne.anteBet == 0){
            p1Ante.setText("Ante Bet: ");
            p1PlaceAnte.setVisible(true);
        }
        else{
            p1Ante.setText("Ante Bet: "+playerOne.anteBet);
            //playerOne.updateTotalWinnings(playerOne.anteBet); if we were doing balance
        }
        p1PlayWager.setText("Play Bet: ");
        p1PairPlus.setText("Pair Plus Bet: ");
        p1BetRule.setVisible(true);
        p1PlacePairPlus.setVisible(true);
        p1PlaceBets.setVisible(true);
        p1PlaceBets.setDisable(false);

        if(playerTwo.anteBet == 0){
            p2Ante.setText("Ante Bet: ");
            p2PlaceAnte.setVisible(true);
        }
        else{
            p2Ante.setText("Ante Bet: "+playerTwo.anteBet);
            //playerTwo.updateTotalWinnings(playerTwo.anteBet); if we were doing balance
        }
        p2PlayWager.setText("Play Bet: ");
        p2PairPlus.setText("Pair Plus Bet: 0");
        p2BetRule.setVisible(true);
        p2PlacePairPlus.setVisible(true);
        p2PlaceBets.setVisible(true);
        p2PlaceBets.setDisable(false);
    }

    @FXML private VBox root3;
    @FXML private Button quitButton;
    @FXML private Button returnToGameButton;

    private Stage exitStage = new Stage();
    public void promptExit(ActionEvent e) throws IOException {
        Parent root3 = FXMLLoader.load(getClass().getResource("/FXML/exitOption.fxml"));
        if(darkMode){
            root3.getStylesheets().add("/styles/exitPageV1.css");
        }
        else{
            root3.getStylesheets().add("/styles/exitPageV2.css");
        }
        exitStage.setTitle("Three Card Poker Game");
        Scene s2 = new Scene(root3, 750,400);
        exitStage.setScene(s2);
        exitStage.show();
    }

    public void returnToGame(ActionEvent e) throws IOException {
        exitStage.close();
    }

    public void exitGameOption(ActionEvent e) throws IOException {
        Platform.exit();
    }

}
