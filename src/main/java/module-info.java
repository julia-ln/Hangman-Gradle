module at.ac.fhcampuswien.hangman {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.ac.fhcampuswien.hangman to javafx.fxml;
    exports at.ac.fhcampuswien.hangman;
}