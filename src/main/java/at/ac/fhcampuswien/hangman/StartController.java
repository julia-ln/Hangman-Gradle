package at.ac.fhcampuswien.hangman;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    @FXML
    private ChoiceBox<String> categoryButton;

    private WordProvider wordProvider;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wordProvider = new WordProvider();
        categoryButton.getItems().addAll(wordProvider.getCategories());
    }

    @FXML
    public void onPlayButtonClick(ActionEvent event){
        String selectedCategory = categoryButton.getValue();
        if (selectedCategory != null){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("hangman-view.fxml"));
                AnchorPane root = loader.load();

                HangmanController hangmanController = loader.getController();
                hangmanController.setCategory(selectedCategory);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}