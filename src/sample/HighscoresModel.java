package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class HighscoresModel {

    private ArrayList<Record> scores;

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

    void add(String name, int score) {
        scores.add(new Record(name, score));
        Collections.sort(scores);
    }

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
