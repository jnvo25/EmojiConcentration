package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class HighscoresModel {

    private ArrayList<Integer> scores;

    HighscoresModel() {
        scores = new ArrayList<>();
        try {
            FileInputStream inFile = new FileInputStream("highscores.ser");
            ObjectInputStream in = new ObjectInputStream(inFile);

            // Make sure file's array list is valid
            ArrayList<?> temp = (ArrayList<?>)in.readObject();
            for (Object x : temp) {
                scores.add((Integer) x);
            }

            in.close();
            inFile.close();
        } catch(IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    void save() {
        try {
            FileOutputStream outFile = new FileOutputStream("highscores.ser");
            ObjectOutputStream out = new ObjectOutputStream(outFile);
            out.writeObject(scores);
            out.close();
            outFile.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void add(int score) {
        scores.add(score);
        Collections.sort(scores);
        Collections.reverse(scores);
    }

    int getScore(int index) {
        return scores.get(index);
    }
}
