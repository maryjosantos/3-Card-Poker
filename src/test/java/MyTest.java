import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

class MyTest {

	Deck deck;
	Dealer dealer;
	ArrayList<Card> straightFlush;
	ArrayList<Card> threeOfAKind;
	ArrayList<Card> straight;
	ArrayList<Card> flush;
	ArrayList<Card> pair;
	ArrayList<Card> highCard;

	@BeforeEach
	void init(){
		deck = new Deck();
		dealer = new Dealer();

		Card straightFlush1 = new Card('H', 6);
		Card straightFlush2 = new Card('H', 7);
		Card straightFlush3 = new Card('H', 8);
		straightFlush = new ArrayList<>();
		straightFlush.add(straightFlush1);
		straightFlush.add(straightFlush2);
		straightFlush.add(straightFlush3);

		Card threeOfAKind1 = new Card('C', 3);
		Card threeOfAKind2 = new Card('H', 3);
		Card threeOfAKind3 = new Card('D', 3);
		threeOfAKind = new ArrayList<>();
		threeOfAKind.add(threeOfAKind1);
		threeOfAKind.add(threeOfAKind2);
		threeOfAKind.add(threeOfAKind3);

		Card straight1 = new Card('S', 14);
		Card straight2 = new Card('H', 2);
		Card straight3 = new Card('S', 3);
		straight = new ArrayList<>();
		straight.add(straight1);
		straight.add(straight2);
		straight.add(straight3);

		Card flush1 = new Card('H', 10);
		Card flush2 = new Card('H', 5);
		Card flush3 = new Card('H', 7);
		flush = new ArrayList<>();
		flush.add(flush1);
		flush.add(flush2);
		flush.add(flush3);

		Card pair1 = new Card('C', 10);
		Card pair2 = new Card('D', 10);
		Card pair3 = new Card('D', 5);
		pair = new ArrayList<>();
		pair.add(pair1);
		pair.add(pair2);
		pair.add(pair3);

		Card highCard1 = new Card('H', 11);
		Card highCard2 = new Card('C', 7);
		Card highCard3 = new Card('D', 8);
		highCard = new ArrayList<>();
		highCard.add(highCard1);
		highCard.add(highCard2);
		highCard.add(highCard3);
	}

	// Deck Tests

	// deck includes all cards
	@Test // 1
	void initializeDeck() {
		// Make arraylists to check if each card was created and added to the deck
		// Index 0-12 = Correspond to Card numbers 2-14 in increasing order
		ArrayList<Boolean> checkSpades = new ArrayList<>();
		ArrayList<Boolean> checkDiamonds = new ArrayList<>();
		ArrayList<Boolean> checkHearts = new ArrayList<>();
		ArrayList<Boolean> checkClubs = new ArrayList<>();
		ArrayList<Boolean> allCardsPresent = new ArrayList<>();
		for (int i = 0; i<13; i++){
			checkSpades.add(false);
			checkDiamonds.add(false);
			checkHearts.add(false);
			checkClubs.add(false);
			allCardsPresent.add(true);
		}
		for (Card c : deck){
			if(c.suit == 'S'){
				checkSpades.set(c.value-2, true);
			}
			if(c.suit == 'D'){
				checkDiamonds.set(c.value-2, true);
			}
			if(c.suit == 'H'){
				checkHearts.set(c.value-2, true);
			}
			if(c.suit == 'C'){
				checkClubs.set(c.value-2, true);
			}
		}
		Assertions.assertEquals(allCardsPresent, checkSpades, "spade card missing or duplicate");
		Assertions.assertEquals(allCardsPresent, checkDiamonds, "diamond card missing or duplicate");
		Assertions.assertEquals(allCardsPresent, checkHearts, "heart card missing or duplicate");
		Assertions.assertEquals(allCardsPresent, checkClubs, "club card missing or duplicate");
	}

	// deck is shuffled
	@Test // 2
	void deckShuffling(){
		ArrayList<Card> unshuffledDeck = new ArrayList<>();
		ArrayList<Character> suits = new ArrayList<>();
		suits.add('C');
		suits.add('D');
		suits.add('S');
		suits.add('H');

		for (int i = 2; i<15; i++){
			for (Character c : suits){
				Card oneCard = new Card(c,i);
				unshuffledDeck.add(oneCard);
			}
		}
		Assertions.assertNotEquals(unshuffledDeck, deck, "deck is not shuffled");
	}

	//newDeck() tests
	// size of new deck
	@Test // 3
	void sizeNewDeck(){

		Assertions.assertEquals(52, dealer.theDeck.size(), "deck is incorrect size");
		dealer.dealHand();
		Assertions.assertEquals(49, dealer.theDeck.size(), "deck is incorrect size");

		dealer.theDeck = dealer.theDeck.newDeck();
		//dealer.theDeck.newDeck();
		Assertions.assertEquals(52, dealer.theDeck.size(), "new deck is incorrect size");
	}

	// object at dealer.theDeck got replaced
	@Test // 4
	void newActualDeck(){
		ArrayList<Card> oldDeck = dealer.theDeck;
		dealer.theDeck = dealer.theDeck.newDeck();
		//dealer.theDeck.newDeck();
		Assertions.assertNotEquals(oldDeck, dealer.theDeck, "deck has not been replaced");
	}

	// Dealer tests
	// dealer constructor tests
	@Test // 5
	void dealerConstructor(){
		Assertions.assertEquals(52, dealer.theDeck.size(), "deck is incorrect size");
	}

	// dealHand() deals hand properly
	@Test // 6
	void dealHandUniqueCards(){
		dealer.dealersHand = dealer.dealHand();
		Assertions.assertEquals(3, dealer.dealersHand.size(), "dealers hand is incorrect size");
		boolean uniqueCards = true;
		int cardValue = dealer.dealersHand.get(0).value;
		char cardSuit = dealer.dealersHand.get(0).suit;
		for (int i = 1; i<3; i++){
			if (cardValue == dealer.dealersHand.get(i).value & cardSuit == dealer.dealersHand.get(i).suit){
				uniqueCards = false;
				break;
			}
		}
		Assertions.assertTrue(uniqueCards, "dealers hand has multiple identical cards");
	}

	// dealHand() checks for size before dealing
	@Test // 7
	void dealHandChecksSize(){
		ArrayList<Card> hand1 = dealer.dealHand();
		ArrayList<Card> hand2 = dealer.dealHand();
		ArrayList<Card> hand3 = dealer.dealHand();
		ArrayList<Card> hand4 = dealer.dealHand();
		ArrayList<Card> hand5 = dealer.dealHand();
		ArrayList<Card> hand6 = dealer.dealHand();
		Assertions.assertEquals(34, dealer.theDeck.size(), "incorrect number of cards remaining after dealing 6 hands");
		ArrayList<Card> hand7 = dealer.dealHand();
		Assertions.assertEquals(49, dealer.theDeck.size(), "deck not refreshed after reaching 34 cards");
	}

	// dealHand() only calls newDeck() if size too small
	@Test // 8
	void onlyNewDeckIfTooSmall(){
		ArrayList<Card> hand1 = dealer.dealHand();
		ArrayList<Card> hand2 = dealer.dealHand();
		ArrayList<Card> hand3 = dealer.dealHand();
		Assertions.assertEquals(43, dealer.theDeck.size(), "incorrect number of cards remaining after dealing 6 hands");
		ArrayList<Card> hand4 = dealer.dealHand();
		ArrayList<Card> hand5 = dealer.dealHand();
		ArrayList<Card> hand6 = dealer.dealHand();
		Assertions.assertEquals(34, dealer.theDeck.size(), "incorrect number of cards remaining after dealing 6 hands");
		ArrayList<Card> hand7 = dealer.dealHand();
		Assertions.assertEquals(49, dealer.theDeck.size(), "deck not refreshed after reaching 34 cards");
	}

	// dealHand removes cards from deck each time
	@Test // 9
	void dealHandRemovesCards(){
		ArrayList<Card> hand1 = dealer.dealHand();
		Assertions.assertEquals(49, dealer.theDeck.size(), "incorrect number of cards remaining after dealing hand");
		ArrayList<Card> hand2 = dealer.dealHand();
		Assertions.assertEquals(46, dealer.theDeck.size(), "incorrect number of cards remaining after dealing hand");
		ArrayList<Card> hand3 = dealer.dealHand();
		Assertions.assertEquals(43, dealer.theDeck.size(), "incorrect number of cards remaining after dealing hand");
	}

	// hand has correct number of cards
	@Test // 10
	void dealtHandsCorrectSize(){
		ArrayList<Card> hand1 = dealer.dealHand();
		ArrayList<Card> hand2 = dealer.dealHand();
		ArrayList<Card> hand3 = dealer.dealHand();
		Assertions.assertEquals(3, hand1.size(), "incorrect number of cards in hand");
		Assertions.assertEquals(3, hand2.size(), "incorrect number of cards in hand");
		Assertions.assertEquals(3, hand3.size(), "incorrect number of cards in hand");
	}

	// dealHand is removing cards from top of deck (check card values, then deal, then compare)
	@Test // 11
	void correctCardsDealt(){
		ArrayList<Character> hand1Suits = new ArrayList<>();
		ArrayList<Integer> hand1Values = new ArrayList<>();

		for(int i = 0; i<3; i++){
			hand1Suits.add(dealer.theDeck.get(i).suit);
			hand1Values.add(dealer.theDeck.get(i).value);
		}
		ArrayList<Card> hand1 = dealer.dealHand();
		boolean hand1CardsMatch = true;
		for (int i = 0; i<3; i++){
			if (hand1.get(i).value != hand1Values.get(i) | hand1.get(i).suit != hand1Suits.get(i)){
				hand1CardsMatch = false;
				break;
			}
		}
		Assertions.assertTrue(hand1CardsMatch, "cards dealt in hand are not correct");

		ArrayList<Character> hand2Suits = new ArrayList<>();
		ArrayList<Integer> hand2Values = new ArrayList<>();
		for(int i = 0; i<3; i++){
			hand2Suits.add(dealer.theDeck.get(i).suit);
			hand2Values.add(dealer.theDeck.get(i).value);
		}
		ArrayList<Card> hand2 = dealer.dealHand();

		boolean hand2CardsMatch = true;
		for (int i = 0; i<3; i++){
			if (hand2.get(i).value != hand2Values.get(i) | hand2.get(i).suit != hand2Suits.get(i)){
				hand2CardsMatch = false;
				break;
			}
		}
		Assertions.assertTrue(hand2CardsMatch, "cards dealt in hand are not correct");
	}

	// tests Card.setImages() (card image file names set correctly in Card class)
	@Test // 12
	void cardImageNames(){
		Card.setImages();
		ArrayList<String> realClubNames = new ArrayList<>();
		Collections.addAll(realClubNames, "2_of_clubs.png", "3_of_clubs.png", "4_of_clubs.png", "5_of_clubs.png",
				"6_of_clubs.png", "7_of_clubs.png", "8_of_clubs.png", "9_of_clubs.png", "10_of_clubs.png",
				"11_of_clubs.png", "12_of_clubs.png", "13_of_clubs.png", "14_of_clubs.png");

		boolean clubsCorrect = true;
		for(int i = 0; i<Card.clubImages.size(); i++){
			if(! Card.clubImages.get(i).equals(realClubNames.get(i))){
				clubsCorrect = false;
				break;
			}
		}
		Assertions.assertTrue(clubsCorrect, "club image name incorrect");
		Assertions.assertEquals(13, Card.clubImages.size());

		ArrayList<String> realSpadeNames = new ArrayList<>();
		Collections.addAll(realSpadeNames, "2_of_spades.png", "3_of_spades.png", "4_of_spades.png", "5_of_spades.png",
				"6_of_spades.png", "7_of_spades.png", "8_of_spades.png", "9_of_spades.png", "10_of_spades.png",
				"11_of_spades.png", "12_of_spades.png", "13_of_spades.png", "14_of_spades.png");

		boolean spadesCorrect = true;
		for(int i = 0; i<Card.spadeImages.size(); i++){
			if(! Card.spadeImages.get(i).equals(realSpadeNames.get(i))){
				spadesCorrect = false;
				break;
			}
		}
		Assertions.assertTrue(spadesCorrect, "spade image name incorrect");
		Assertions.assertEquals(13, Card.spadeImages.size());

		ArrayList<String> realDiamondNames = new ArrayList<>();
		Collections.addAll(realDiamondNames, "2_of_diamonds.png", "3_of_diamonds.png", "4_of_diamonds.png", "5_of_diamonds.png",
				"6_of_diamonds.png", "7_of_diamonds.png", "8_of_diamonds.png", "9_of_diamonds.png", "10_of_diamonds.png",
				"11_of_diamonds.png", "12_of_diamonds.png", "13_of_diamonds.png", "14_of_diamonds.png");

		boolean diamondsCorrect = true;
		for(int i = 0; i<Card.diamondImages.size(); i++){
			if(! Card.diamondImages.get(i).equals(realDiamondNames.get(i))){
				diamondsCorrect = false;
				break;
			}
		}
		Assertions.assertTrue(diamondsCorrect, "diamond image name incorrect");
		Assertions.assertEquals(13, Card.diamondImages.size());

		ArrayList<String> realHeartNames = new ArrayList<>();
		Collections.addAll(realHeartNames, "2_of_hearts.png", "3_of_hearts.png", "4_of_hearts.png", "5_of_hearts.png",
				"6_of_hearts.png", "7_of_hearts.png", "8_of_hearts.png", "9_of_hearts.png", "10_of_hearts.png",
				"11_of_hearts.png", "12_of_hearts.png", "13_of_hearts.png", "14_of_hearts.png");

		boolean heartsCorrect = true;
		for(int i = 0; i<Card.heartImages.size(); i++){
			if(! Card.heartImages.get(i).equals(realHeartNames.get(i))){
				heartsCorrect = false;
				break;
			}
		}

		Assertions.assertTrue(heartsCorrect, "heart image name incorrect");
		Assertions.assertEquals(13, Card.heartImages.size());
	}

	// ThreeCardLogic tests
	// evaluate a pair
	@Test // 1
	void evaluatePair(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c4 = new Card('C', 4);
		Card h4 = new Card('H', 4);
		Card d7 = new Card('D', 7);
		testHand.add(c4);
		testHand.add(h4);
		testHand.add(d7);

		Assertions.assertEquals(5, ThreeCardLogic.evalHand(testHand), "did not evaluate as a pair");
	}

	// evaluate three of a kind
	@Test // 2
	void evaluateThreeOfAKind(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c4 = new Card('C', 4);
		Card h4 = new Card('H', 4);
		Card d4 = new Card('D', 4);
		testHand.add(c4);
		testHand.add(h4);
		testHand.add(d4);

		Assertions.assertEquals(2, ThreeCardLogic.evalHand(testHand), "did not evaluate as 3 of a kind");
	}

	// evaluate a straight with an a-2-3
	@Test // 3
	void evaluateStraightA23(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c3 = new Card('C', 3);
		Card h2 = new Card('H', 2);
		Card d14 = new Card('D', 14);
		testHand.add(c3);
		testHand.add(h2);
		testHand.add(d14);

		Assertions.assertEquals(3, ThreeCardLogic.evalHand(testHand), "did not evaluate as straight");
	}

	// evaluate a straight with q-k-a
	@Test // 4
	void evaluateStraightQKA(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c14 = new Card('C', 14);
		Card h13 = new Card('H', 13);
		Card d12 = new Card('D', 12);
		testHand.add(c14);
		testHand.add(h13);
		testHand.add(d12);

		Assertions.assertEquals(3, ThreeCardLogic.evalHand(testHand), "did not evaluate as straight");
	}

	// evaluate a straight of numbers
	@Test // 5
	void evaluateStraight(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c8 = new Card('C', 8);
		Card h10 = new Card('H', 10);
		Card s9 = new Card('S', 9);
		testHand.add(c8);
		testHand.add(h10);
		testHand.add(s9);

		Assertions.assertEquals(3, ThreeCardLogic.evalHand(testHand), "did not evaluate as straight");
	}

	// evaluate a straight flush with an a-2-3
	@Test // 6
	void evaluateStraightFlushA23(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c3 = new Card('C', 3);
		Card c2 = new Card('C', 2);
		Card c14 = new Card('C', 14);
		testHand.add(c3);
		testHand.add(c2);
		testHand.add(c14);

		Assertions.assertEquals(1, ThreeCardLogic.evalHand(testHand), "did not evaluate as straight flush");
	}

	// evaluate a straight flush with q-k-a
	@Test // 7
	void evaluateStraightFlushQKA(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c14 = new Card('C', 14);
		Card c13 = new Card('C', 13);
		Card c12 = new Card('C', 12);
		testHand.add(c14);
		testHand.add(c13);
		testHand.add(c12);

		Assertions.assertEquals(1, ThreeCardLogic.evalHand(testHand), "did not evaluate as straight flush");
	}

	// evaluate a straight flush of numbers
	@Test // 8
	void evaluateStraightFlush(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c8 = new Card('C', 8);
		Card c10 = new Card('C', 10);
		Card c9 = new Card('C', 9);
		testHand.add(c8);
		testHand.add(c10);
		testHand.add(c9);

		Assertions.assertEquals(1, ThreeCardLogic.evalHand(testHand), "did not evaluate as straight flush");
	}

	// evaluate a flush
	@Test // 9
	void evaluateFlush(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c8 = new Card('C', 8);
		Card c10 = new Card('C', 10);
		Card c11 = new Card('C', 11);
		testHand.add(c8);
		testHand.add(c10);
		testHand.add(c11);

		Assertions.assertEquals(4, ThreeCardLogic.evalHand(testHand), "did not evaluate as a flush");
	}

	// evaluate a high card
	@Test // 10
	void evaluateHighCard(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c3 = new Card('C', 8);
		Card d4 = new Card('D', 4);
		Card d14 = new Card('D', 14);
		testHand.add(c3);
		testHand.add(d4);
		testHand.add(d14);

		Assertions.assertEquals(0, ThreeCardLogic.evalHand(testHand), "did not evaluate as high card");
	}

	// get pair plus winnings for each of those hands (just one straight flush, just one flush)

	// pair plus for straight flush
	@Test // 11
	void pairPlusStraightFlush(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c11 = new Card('C', 11);
		Card c13 = new Card('C', 13);
		Card c12 = new Card('C', 12);
		testHand.add(c11);
		testHand.add(c13);
		testHand.add(c12);
		int bet = 10;

		Assertions.assertEquals(400, ThreeCardLogic.evalPPWinnings(testHand, bet), "incorrect pair plus winnings for straight flush");
	}

	// pair plus for three of a kind
	@Test // 12
	void pairPlusThreeOfAKind(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c5 = new Card('C', 5);
		Card h5 = new Card('H', 5);
		Card d5 = new Card('D', 5);
		testHand.add(c5);
		testHand.add(h5);
		testHand.add(d5);
		int bet = 10;

		Assertions.assertEquals(300, ThreeCardLogic.evalPPWinnings(testHand, bet), "incorrect pair plus winnings for three of a kind");
	}

	// pair plus for straight
	@Test // 13
	void pairPlusStraight(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c8 = new Card('C', 8);
		Card d10 = new Card('D', 10);
		Card d9 = new Card('D', 9);
		testHand.add(c8);
		testHand.add(d10);
		testHand.add(d9);
		int bet = 10;

		Assertions.assertEquals(60, ThreeCardLogic.evalPPWinnings(testHand, bet), "incorrect pair plus winnings for straight");
	}

	// pair plus for flush
	@Test // 14
	void pairPlusFlush(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card d8 = new Card('D', 8);
		Card d11 = new Card('D', 11);
		Card d9 = new Card('D', 9);
		testHand.add(d8);
		testHand.add(d11);
		testHand.add(d9);
		int bet = 10;

		Assertions.assertEquals(30, ThreeCardLogic.evalPPWinnings(testHand, bet), "incorrect pair plus winnings for flush");
	}

	// pair plus for pair
	@Test // 15
	void pairPlusPair(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c14 = new Card('C', 14);
		Card d14= new Card('D', 14);
		Card d9 = new Card('D', 9);
		testHand.add(c14);
		testHand.add(d14);
		testHand.add(d9);
		int bet = 10;

		Assertions.assertEquals(10, ThreeCardLogic.evalPPWinnings(testHand, bet), "incorrect pair plus winnings for pair");
	}

	// pair plus for high card
	@Test // 16
	void pairPlusHighCard(){
		ArrayList<Card> testHand = new ArrayList<>();
		Card c14 = new Card('C', 14);
		Card d10= new Card('D', 10);
		Card d9 = new Card('D', 9);
		testHand.add(c14);
		testHand.add(d10);
		testHand.add(d9);
		int bet = 10;

		Assertions.assertEquals(0, ThreeCardLogic.evalPPWinnings(testHand, bet), "incorrect pair plus winnings for high card");
	}

	// compare hands!
	// 0 vs 1, 2, 3, 4, 5
	// 1 vs 2, 3, 4, 5
	// 2 vs 3, 4, 5
	// 3 vs 4, 5
	// 4 vs 5

	@Test // 17
	void compareHighCardAgainstBetter(){
		ArrayList<Card> dealer1 = highCard;
		ArrayList<Card> player1 = straightFlush;
		Assertions.assertEquals(2, ThreeCardLogic.compareHands(dealer1, player1),
				"dealer high card should lose to player straight flush");

		ArrayList<Card> dealer2 = highCard;
		ArrayList<Card> player2 = threeOfAKind;
		Assertions.assertEquals(2, ThreeCardLogic.compareHands(dealer2, player2),
				"dealer high card should lose to player three of a kind");

		ArrayList<Card> dealer3 = highCard;
		ArrayList<Card> player3 = straight;
		Assertions.assertEquals(2, ThreeCardLogic.compareHands(dealer3, player3),
				"dealer high card should lose to player straight");

		ArrayList<Card> player4 = highCard;
		ArrayList<Card> dealer4 = flush;
		Assertions.assertEquals(1, ThreeCardLogic.compareHands(dealer4, player4),
				"dealer flush should win to player high card");

		ArrayList<Card> player5 = highCard;
		ArrayList<Card> dealer5 = pair;
		Assertions.assertEquals(1, ThreeCardLogic.compareHands(dealer5, player5),
				"dealer pair should lose to player pair");
	}

	@Test // 18
	void compareHandsStraightFlushAndWorse(){
		ArrayList<Card> dealer1 = straightFlush;
		ArrayList<Card> player1 = threeOfAKind;
		Assertions.assertEquals(1, ThreeCardLogic.compareHands(dealer1, player1),
				"dealer straight flush should win to player three of a kind");

		ArrayList<Card> dealer2 = straightFlush;
		ArrayList<Card> player2 = straight;
		Assertions.assertEquals(1, ThreeCardLogic.compareHands(dealer2, player2),
				"dealer straight flush should win to player straight");

		ArrayList<Card> dealer3 = flush;
		ArrayList<Card> player3 = straightFlush;
		Assertions.assertEquals(2, ThreeCardLogic.compareHands(dealer3, player3),
				"dealer flush should lose to player straight flush");

		ArrayList<Card> dealer4 = pair;
		ArrayList<Card> player4 = straightFlush;
		Assertions.assertEquals(2, ThreeCardLogic.compareHands(dealer4, player4),
				"dealer pair should lose to player straight flush");
	}

	@Test // 19
	void compareHandsThreeOfAKindAndWorse(){
		ArrayList<Card> dealer1 = straight;
		ArrayList<Card> player1 = threeOfAKind;
		Assertions.assertEquals(2, ThreeCardLogic.compareHands(dealer1, player1),
				"dealer straight should lose to player three of a kind");

		ArrayList<Card> dealer2 = flush;
		ArrayList<Card> player2 = threeOfAKind;
		Assertions.assertEquals(2, ThreeCardLogic.compareHands(dealer2, player2),
				"dealer flush should lose to player three of a kind");

		ArrayList<Card> dealer3 = threeOfAKind;
		ArrayList<Card> player3 = pair;
		Assertions.assertEquals(1, ThreeCardLogic.compareHands(dealer3, player3),
				"dealer three of a kind should win to player pair");
	}

	@Test // 20
	void compareHandsStraightAndWorse(){
		ArrayList<Card> dealer1 = straight;
		ArrayList<Card> player1 = flush;
		Assertions.assertEquals(1, ThreeCardLogic.compareHands(dealer1, player1),
				"dealer straight should win to player flush");

		ArrayList<Card> dealer2 = pair;
		ArrayList<Card> player2 = straight;
		Assertions.assertEquals(2, ThreeCardLogic.compareHands(dealer2, player2),
				"dealer pair should lose to player straight");
	}

	@Test // 21
	void compareHandsFlushAndWorse(){
		ArrayList<Card> dealer1 = pair;
		ArrayList<Card> player1 = flush;
		Assertions.assertEquals(2, ThreeCardLogic.compareHands(dealer1, player1),
				"dealer pair should lose to player flush");
	}

	// compare ties
	@Test // 22
	void tiesStraightAndStraightFlush(){
		ArrayList<Card> dealer1 = straightFlush; // H 6, 7, 8
		ArrayList<Card> player1 = new ArrayList<>();
		Card d8 = new Card('D', 8);
		Card d9 = new Card('D', 9);
		Card d10 = new Card('D', 10);
		player1.add(d8);
		player1.add(d9);
		player1.add(d10);
		Assertions.assertEquals(2, ThreeCardLogic.compareHands(dealer1, player1),
				"dealer straight flush should lose to player straight flush");

		ArrayList<Card> player2 = new ArrayList<>();
		Card s6 = new Card('S', 6);
		Card s7 = new Card('S', 7);
		Card s8 = new Card('S', 8);
		player2.add(s6);
		player2.add(s7);
		player2.add(s8);
		Assertions.assertEquals(0, ThreeCardLogic.compareHands(dealer1, player2),
				"dealer straight flush should tie with player straight flush");

		ArrayList<Card> dealer2 = new ArrayList<>();
		Card s2 = new Card('S', 2);
		Card s14 = new Card('S', 14);
		Card h3 = new Card('H', 3);
		dealer2.add(s2);
		dealer2.add(s14);
		dealer2.add(h3);

		ArrayList<Card> player3 = new ArrayList<>();
		Card h12 = new Card('H', 12);
		Card h13 = new Card('H', 13);
		Card c14 = new Card('C', 14);
		player3.add(c14);
		player3.add(h12);
		player3.add(h13);

		Assertions.assertEquals(2, ThreeCardLogic.compareHands(dealer2, player3),
				"dealer  flush a23 should lose to player flush qka");
	}

	@Test // 23
	void tiesThreeOfAKind(){
		ArrayList<Card> dealer1 = threeOfAKind; // C3, H3, D3
		ArrayList<Card> player1 = new ArrayList<>();
		Card d8 = new Card('D', 8);
		Card h8 = new Card('H', 8);
		Card s8 = new Card('S', 8);
		player1.add(d8);
		player1.add(h8);
		player1.add(s8);
		Assertions.assertEquals(2, ThreeCardLogic.compareHands(dealer1, player1),
				"dealer three of a kind should lose to player three of a kind");
	}

	@Test // 24
	void tiesFlush(){
		ArrayList<Card> dealer1 = flush; // H 10, 5, 7
		ArrayList<Card> player1 = new ArrayList<>();

		Card d8 = new Card('D', 8);
		Card d6 = new Card('D', 6);
		Card d10 = new Card('D', 10);
		player1.add(d8);
		player1.add(d6);
		player1.add(d10);
		Assertions.assertEquals(0, ThreeCardLogic.compareHands(dealer1, player1),
				"dealer flush should tie with player flush");

		ArrayList<Card> player2 = new ArrayList<>();
		Card d9 = new Card('D', 9);
		player2.add(d8);
		player2.add(d6);
		player2.add(d9);
		Assertions.assertEquals(1, ThreeCardLogic.compareHands(dealer1, player2),
				"dealer flush should win to player flush");
	}

	@Test // 25
	void tiesPair(){
		ArrayList<Card> dealer1 = pair; // C10, D10, D5
		ArrayList<Card> player1 = new ArrayList<>();

		Card h10 = new Card('H', 10);
		Card c5 = new Card('C', 5);
		Card s10 = new Card('S', 10);
		player1.add(h10);
		player1.add(c5);
		player1.add(s10);
		Assertions.assertEquals(0, ThreeCardLogic.compareHands(dealer1, player1),
				"dealer pair should tie with player pair");

		ArrayList<Card> player2 = new ArrayList<>();
		Card d9 = new Card('D', 9);
		player2.add(h10);
		player2.add(d9);
		player2.add(s10);
		Assertions.assertEquals(2, ThreeCardLogic.compareHands(dealer1, player2),
				"dealer pair should lose to player pair");

		ArrayList<Card> player3 = new ArrayList<>();
		Card s5 = new Card('S', 5);
		Card h5 = new Card('H', 5);
		Card c6 = new Card('C', 6);
		player3.add(s5);
		player3.add(h5);
		player3.add(c6);
		Assertions.assertEquals(1, ThreeCardLogic.compareHands(dealer1, player3),
				"dealer pair should win to player pair");
	}

	@Test // 26
	void tiesHighCard(){
		ArrayList<Card> dealer1 = highCard; // H11, C7, D8
		ArrayList<Card> player1 = new ArrayList<>();
		Card h9 = new Card('H', 9);
		Card c10 = new Card('C', 10);
		Card s12 = new Card('S', 12);
		player1.add(c10);
		player1.add(s12);
		player1.add(h9);

		Assertions.assertEquals(2, ThreeCardLogic.compareHands(dealer1, player1),
				"dealer high card should lose to player high card");

		ArrayList<Card> player2 = new ArrayList<>();
		Card c8 = new Card('C', 8);
		Card s11 = new Card('S', 11);
		player2.add(c8);
		player2.add(s11);
		player2.add(h9);

		Assertions.assertEquals(0, ThreeCardLogic.compareHands(dealer1, player2),
				"dealer high card should tie with player high card");
	}


}
