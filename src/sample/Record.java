package sample;

import java.io.Serializable;

@SuppressWarnings("WeakerAccess")
public class Record implements Comparable<Record>, Serializable {

    private String name;
    private int score;

    Record(String passedName, int passedScore) {
       name = passedName;
       score = passedScore;
    }

    // Getters for property value factory
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }


    @Override
    public int compareTo(Record o) {
        return Integer.compare(score, o.getScore());
    }
}
