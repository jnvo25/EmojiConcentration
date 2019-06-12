package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HighscoresViewController  implements Initializable {
    @FXML private TableView<Record> tableView;
    @FXML private TableColumn rankColumn;
    @FXML private TableColumn<Record, SimpleStringProperty> nameColumn;
    @FXML private TableColumn<Record, SimpleStringProperty> scoreColumn;

    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL location, ResourceBundle resources) {
        rankColumn.setCellFactory(new LineNumbersCellFactory());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        tableView.setItems(getRecords());
    }

    private ObservableList<Record> getRecords() {
        ObservableList<Record> records = FXCollections.observableArrayList();
        HighscoresModel highscores = new HighscoresModel();
        for(int i=0; i<highscores.getSize(); i++) {
            records.add(highscores.getRecord(i));
        }
        return records;
    }

    public void menuButton_pressed(ActionEvent actionEvent) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainMenuView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
