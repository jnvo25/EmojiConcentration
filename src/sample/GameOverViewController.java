package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverViewController {
    public Label moves_label;

    void initData(int moves) {
        moves_label.setText(String.valueOf(moves));

        // Write to file
        HighscoresModel highscores = new HighscoresModel();
        highscores.add(moves);
        highscores.save();
    }

    public void playAgainButton_pressed(ActionEvent actionEvent) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("GameView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void viewHighscoreButton_pressed(ActionEvent actionEvent) {

    }

    public void exitButton_pressed(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
