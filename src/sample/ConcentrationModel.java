package sample;

import java.util.ArrayList;
import java.util.Collections;

class ConcentrationModel {

    private ArrayList<Card> cards;
    private int moves;
    private Card card1, card2;

    ConcentrationModel() {
        resetGame();
    }

    void flipCard(int cardIndex) {
        moves++;
        if(card1 == null) {
            card1 = cards.get(cardIndex);
            if(!card1.isFlipped())
                card1.flip();
        } else if(card2 == null) {
            card2 = cards.get(cardIndex);
            if(!card2.isFlipped())
                card2.flip();
        }
    }

    void checkCards() {
        if (card1.compareTo(card2) == 0) {
            card1.setMatched();
            card2.setMatched();
        } else {
            card1.flip();
            card2.flip();
        }
        card1 = null;
        card2 = null;
    }

    boolean card2Present() {
        return (card2 != null);
    }

    boolean getCardFlip(int cardIndex) {
        return cards.get(cardIndex).isFlipped();
    }

    boolean getCardMatch(int cardIndex) {
        return cards.get(cardIndex).isMatched();
    }

    int getCardValue(int cardIndex) {
        return cards.get(cardIndex).getValue();
    }

    int getMoves() {
        return moves;
    }

    boolean gameOver() {
        for(int i=0; i<12; i++) {
            if(!cards.get(i).isMatched())
                return false;
        }
        return true;
    }

    void resetGame() {
        moves = 0;
        cards = new ArrayList<>();
        for(int i=0; i<6; i++) {
            Card card1 = new Card(i);
            Card card2 = new Card(i);
            cards.add(card1);
            cards.add(card2);
        }
        Collections.shuffle(cards);
    }

}
