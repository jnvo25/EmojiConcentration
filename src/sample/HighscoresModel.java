package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class HighscoresModel {

    private ArrayList<Record> scores;

    /**
     * Constructor
     * Checks if file exists and loads existing data of highscores
     */
    HighscoresModel() {
        scores = new ArrayList<>();
        if((new File("highscores.ser")).exists()) {
            try {
                FileInputStream inFile = new FileInputStream("highscores.ser");
                ObjectInputStream in = new ObjectInputStream(inFile);

                // Make sure file's array list is valid
                ArrayList<?> temp = (ArrayList<?>) in.readObject();
                for (Object x : temp) {
                    scores.add((Record) x);
                }

                in.close();
                inFile.close();
            } catch (IOException | ClassNotFoundException ex) {
                save();
            }
        }
    }

    // Writes highscore to file
    void save() {
        try {
            FileOutputStream outFile = new FileOutputStream("highscores.ser");
            ObjectOutputStream out = new ObjectOutputStream(outFile);
            out.writeObject(scores);
            out.close();
            outFile.close();
        } catch (IOException ex) {
            System.out.println("ERROR SAVING FILE");
        }
    }

    // Add highscore to list and sort
    void add(String name, int score) {
        scores.add(new Record(name, score));
        Collections.sort(scores);
    }

    // Returns whether score is a new highscore
    boolean checkHighscore(int score) {
        return score < scores.get(0).getScore();
    }

    // Getters
    Record getRecord(int index) {
        return scores.get(index);
    }

    Record getLastHighestRecord() {
        return scores.get(0);
    }

    int getSize() {
        return scores.size();
    }
}
