package at.ac.fhcampuswien.hangman;

import java.util.HashSet;
import java.util.Set;

public class GameLogic {
    private final String wordToGuess;
    private final Set<Character> guessedLetters = new HashSet<>();
    private int attemptsLeft;

    public GameLogic(String wordToGuess, int maxAttempts) {
        this.wordToGuess = wordToGuess.toUpperCase();
        this.attemptsLeft = maxAttempts;
    }

    public boolean checkGuess(char guess) {
        guess = Character.toUpperCase(guess);
        if (guessedLetters.contains(guess)) {
            return false;
        }
        guessedLetters.add(guess);
        if (!wordToGuess.contains(String.valueOf(guess))) {
            attemptsLeft--;
            return false;
        }
        return true;
    }

    public String getCurrentWordState() {
        StringBuilder currentState = new StringBuilder();
        for (char c : wordToGuess.toCharArray()) {
            currentState.append(guessedLetters.contains(c) ? c : "*");
        }
        return currentState.toString();
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public boolean isGameOver() {
        return attemptsLeft <= 0 || isWin();
    }

    public boolean isWin() {
        for (char c : wordToGuess.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }


}
