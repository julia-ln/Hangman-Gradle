package at.ac.fhcampuswien.hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class HangmanController {
    @FXML
    private Text categoryText;

    @FXML
    private Text wordText;

    @FXML
    private ImageView hangmanImage;

    private GameLogic gameLogic;
    private WordProvider wordProvider = new WordProvider();
    private int wrongGuesses = 0;

    public void setCategory(String category) {
        categoryText.setText(category.toUpperCase());
        startGame(category);
    }
    public void startGame(String category) {
        String wordToGuess = wordProvider.getRandomWord(category);
        gameLogic = new GameLogic(wordToGuess, 6);  // 6 max wrong attempts
        updateWordText();
    }


    @FXML
    public void onQuitButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("start-view.fxml"));
            AnchorPane root = loader.load();

            Stage stage = (Stage) categoryText.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onLetterClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        char guessedLetter = clickedButton.getText().charAt(0);

        boolean isCorrect = gameLogic.checkGuess(guessedLetter);

        if (isCorrect) {
            clickedButton.setStyle("-fx-background-color: green; -fx-background-border: 8");
        } else {
            clickedButton.setStyle("-fx-background-color: red; -fx-background-border: 8");
            wrongGuesses++;
            updateHangmanImage();
        }
        clickedButton.setDisable(true);
        updateWordText();
        checkGameStatus();
    }
    private void updateWordText() {
        wordText.setText(gameLogic.getCurrentWordState());
    }

    @FXML
    private void updateHangmanImage(){
        String imageName = "Galgen" + (wrongGuesses + 1) + ".png";
        InputStream inputStream = getClass().getResourceAsStream("/at/ac/fhcampuswien/hangman/images/" + imageName);
        
        Image image = new Image(inputStream);
        hangmanImage.setImage(image);
    }

    private void checkGameStatus() {
        if (gameLogic.isWin()) {
            loadWinScreen();
        } else if (gameLogic.isGameOver()) {
            loadGameOverScreen();

        }
    }
    private void loadWinScreen(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("win-view.fxml"));
            AnchorPane root = loader.load();

            GameWinController winController = loader.getController();
            winController.setCategory(categoryText.getText().toUpperCase());
            winController.setWord(gameLogic.getWordToGuess().toUpperCase());

            Stage stage = (Stage) categoryText.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadGameOverScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game-over-view.fxml"));
            AnchorPane root = loader.load();

            GameOverController gameOverController = loader.getController();
            gameOverController.setCategory(categoryText.getText().toUpperCase());
            gameOverController.setWord(gameLogic.getWordToGuess().toUpperCase());

            Stage stage = (Stage) categoryText.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

