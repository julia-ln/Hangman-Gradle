package at.ac.fhcampuswien.hangman;

import java.util.*;

public class WordProvider {
        private final Map<String, List<String>> wordCategories;

        public WordProvider() {
            wordCategories = new HashMap<>();

            wordCategories.put("Animals", Arrays.asList("Dog","Cat","Elephant","Lion","Tiger","Monkey","Giraffe","Donkey","Goat","Goose","Horse","Sheep","Turtle","Zebra","Dolphin","Kangaroo","Snake","Eagle","Whale","Octopus","Butterfly","Spider","Mouse","Rabbit","Squirrel"));
            wordCategories.put("Countries", Arrays.asList("Austria","Germany","France","Spain","Denmark","England","Canada","Finland","Sweden","Norway","Italy","Netherlands","Greece","India","Ireland","Iceland","Japan","China","Croatia","Mexico","Poland","Russia","Switzerland","Hungary","Portugal","Belgium","Australia"));
            wordCategories.put("Sports", Arrays.asList("Basketball","Football","Tennis","Badminton","Baseball","Bowling","Boxing","Climbing","Cycling","Golf","Hockey","Handball","Hiking","Skiing","Surfing","Swimming","Volleyball","Diving","Darts","Running"));
            wordCategories.put("Colours", Arrays.asList("Black","Blue","Green","Yellow","Orange","Purple","Pink","Brown","Grey","Turquoise","Khaki","Azure","Cyan","Magenta","Beige"));
            wordCategories.put("Fruits", Arrays.asList("Pineapple","Apple","Banana","Cherry","Orange","Pear","Blackberry","Blueberry","Strawberry","Raspberry","Kiwi","Coconut","Mango","Watermelon","Peach","Grape","Lemon","Plum","Lychee","Dragonfruit","Pomegranate","Papaya"));
        }

        public List<String> getCategories(){
            return new ArrayList<>(wordCategories.keySet());
        }

        public String getRandomWord(String category){
            List<String> words = wordCategories.get(category);
            if (words == null || words.isEmpty()){
                return null;
            }
            Random random = new Random();
            return words.get(random.nextInt(words.size()));
        }

        public List<String> getWordsForCategory(String category){
            return wordCategories.getOrDefault(category, Collections.emptyList());
        }


}
