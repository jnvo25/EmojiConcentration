package sample;

public class Card implements Comparable<Card> {

    private int value;
    private boolean matched, flipped;

    @Override
    public int compareTo(Card o) {
        if(value == o.value)
            return 0;
        else return 1;
    }

    Card(int passedValue) {
        value = passedValue;
        matched = false;
    }

    boolean isMatched() {
        return matched;
    }

    boolean isFlipped() {
        return flipped;
    }

    int getValue() { return value; }

    void setMatched() {
        matched = true;
    }

    void flip() {
        flipped = !flipped;
    }

}
