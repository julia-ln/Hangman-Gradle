package at.ac.fhcampuswien.hangman;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameLogic {
    private final String wordToGuess;
    private final Set<Character> guessedLetters = new HashSet<>();
    private final List<Character> remainingLetters = new ArrayList<>();
    private int attemptsLeft;
    private boolean hintUsed = false;

    public GameLogic(String wordToGuess, int maxAttempts) {
        this.wordToGuess = wordToGuess.toUpperCase();
        this.attemptsLeft = maxAttempts;

        for (char c : wordToGuess.toCharArray()) {
            if (!remainingLetters.contains(c)) {
                remainingLetters.add(c);
            }
        }
    }

    public String getWordToGuess() {
        return wordToGuess;
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
        remainingLetters.remove((Character) guess);
        return true;
    }


    public String getCurrentWordState() {
        StringBuilder currentState = new StringBuilder();
        for (char c : wordToGuess.toCharArray()) {
            currentState.append(guessedLetters.contains(c) ? c : '*');
        }
        return currentState.toString();
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


    public boolean isHintUsed() {
        return hintUsed;
    }


    public char getHint() {
        if (hintUsed) {
            return ' ';
        }

        hintUsed = true;

        if (!remainingLetters.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(remainingLetters.size());
            char hintLetter = remainingLetters.get(index);
            remainingLetters.remove((Character) hintLetter);
            return hintLetter;
        } else {
            return ' ';
        }
    }
}
