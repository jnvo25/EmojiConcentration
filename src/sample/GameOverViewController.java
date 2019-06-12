package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameOverViewController implements Initializable {
    public Label moves_label;
    public Label lowestMoves_label;
    public Label name_label;
    public AnchorPane highscore_anchorPane;

    void initData(int moves) {
        moves_label.setText(String.valueOf(moves));

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Emoji Concentration");
        dialog.setHeaderText("Enter your name:");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();

        String passedName = "N/A";
        if(result.isPresent()) {
            passedName = result.get();
        }

        // Write to file
        HighscoresModel highscores = new HighscoresModel();
        highscores.add(passedName, moves);
        highscores.save();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        HighscoresModel highscores = new HighscoresModel();
        if(highscores.getSize() > 0) {
            highscore_anchorPane.setVisible(true);
            Record highscore = highscores.getLastHighestRecord();
            lowestMoves_label.setText(String.valueOf(highscore.getScore()));
            name_label.setText(highscore.getName());
        } else {
            highscore_anchorPane.setVisible(false);
        }
    }

    public void playAgainButton_pressed(ActionEvent actionEvent) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("GameView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void viewHighscoreButton_pressed(ActionEvent actionEvent) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("HighscoresView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void exitButton_pressed(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


}
