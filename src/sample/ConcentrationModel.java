package sample;

import java.util.ArrayList;
import java.util.Collections;

class ConcentrationModel {

    private ArrayList<Card> cards;
    private int moves;
    private Card card1, card2;

    ConcentrationModel() {
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

    void flipCard(int cardIndex) {
        moves++;
        System.out.println(cards.get(cardIndex).getValue());
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
        System.out.println(card1.getValue());
        System.out.println(card2.getValue());
        System.out.println(card1.compareTo(card2));
        if (card1.getValue() == card2.getValue()) {
            card1.match();
            card2.match();
        } else {
            card1.flip();
            card2.flip();
        }
        System.out.println("A");

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

}
