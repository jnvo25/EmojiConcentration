package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {

    public ImageView imageView_1;
    public ImageView imageView_2;
    public ImageView imageView_3;
    public ImageView imageView_4;
    public ImageView imageView_5;
    public ImageView imageView_6;
    public ImageView imageView_7;
    public ImageView imageView_8;
    public ImageView imageView_9;
    public ImageView imageView_10;
    public ImageView imageView_11;
    public ImageView imageView_12;
    public Label moves_label;

    private ConcentrationModel game;

    private String[] emojis = {"bat.png", "candy.png", "clown.png", "devil.png", "ghost.png", "pumpkin.png"};
    private ArrayList<ImageView> allImages;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Add all image views into array list to iterate when updating
        allImages = new ArrayList<>();
        allImages.add(imageView_1);
        allImages.add(imageView_2);
        allImages.add(imageView_3);
        allImages.add(imageView_4);
        allImages.add(imageView_5);
        allImages.add(imageView_6);
        allImages.add(imageView_7);
        allImages.add(imageView_8);
        allImages.add(imageView_9);
        allImages.add(imageView_10);
        allImages.add(imageView_11);
        allImages.add(imageView_12);

        game = new ConcentrationModel();
        updateView();
    }

    // If image is clicked, checks and updates view accordingly
    public void imageView_clicked(MouseEvent mouseEvent) throws IOException {
        // Get the id of card of the image view clicked
        ImageView imageView = (ImageView) mouseEvent.getSource();
        int currentIndex = Integer.parseInt(imageView.getId().substring(10)) - 1;

        // Use id to check if card is flippable
        if (game.flippable(currentIndex)) {
            game.flipCard(currentIndex);
            updateView();

            // If 2 cards are selected, check, and flip back/erase cards
            // Pauses view update for 600ms so user can observe choices
            if (game.card2Present()) {
                game.checkCards();
                // Create new thread to sleep because original thread updateView will be paused as well until freed
                new Thread(() -> { //use another thread so long process does not block gui
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    updateView();
                }).start();
            }

            // If all cards are matched, go to game over view
            if(game.isAllMatched()) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("GameOverView.fxml"));
                Parent tableViewParent = loader.load();
                Scene tableViewScene = new Scene(tableViewParent);

                GameOverViewController controller = loader.getController();
                controller.initData(game.getMoves());

                Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.show();
            }


        }

    }

    // Resets game and updates view
    public void resetButton_pressed() {
        game.startGame();
        updateView();
    }

    // Updates moves label and iterates through array of image view to update image
    private void updateView() {
        moves_label.setText(String.valueOf(game.getMoves()));
        for(int i=0; i<12; i++) {
            if(game.getCardMatch(i)) {
                allImages.get(i).setImage(null);
            } else if(game.getCardFlip(i)) {
                Image image = new Image(String.valueOf(getClass().getResource("/sample/resources/" + emojis[game.getCardValue(i)])));
                allImages.get(i).setImage(image);
            } else {
                Image image = new Image(String.valueOf(getClass().getResource("/sample/resources/cardBack.png")));
                allImages.get(i).setImage(image);
            }
        }
    }

    // Go to menu button
    public void menuButton_pressed(ActionEvent actionEvent) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainMenuView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

}
