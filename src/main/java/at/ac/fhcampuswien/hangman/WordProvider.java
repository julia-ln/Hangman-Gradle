package at.ac.fhcampuswien.hangman;

import java.util.*;

public class WordProvider {
        private final Map<String, List<String>> wordCategories;

        public WordProvider() {
            wordCategories = new HashMap<>();

            wordCategories.put("Animals", Arrays.asList("Okapi","Pangolin","Elephant","Gecko","Axolotl","Monkey","Giraffe","Donkey","Goat","Goose","Horse","Sheep","Turtle","Aardvark","Dolphin","Kangaroo","Snake","Eagle","Whale","Octopus","Butterfly","Spider","Mouse","Rabbit","Squirrel"));
            wordCategories.put("Countries", Arrays.asList("Austria","Bhutan","Mozambique","Kyrgyzstan","Laos","Denmark","Somalia","Paraguay","Finland","Thailand","Norway","Kasachstan","Netherlands","Myanmar","Jemen","Ireland","Iceland","Japan","Pakistan","Croatia","Mexico","Poland","Vietnam","Switzerland","Hungary","Usbekistan","Belgium","Australia"));
            wordCategories.put("Sports", Arrays.asList("Curling","Taekwondo","Parkour","Badminton","Baseball","Bowling","Boxing","Climbing","Cycling","Golf","Hockey","Handball","Hiking","Skiing","Surfing","Swimming","Judo","Diving","Darts","Running"));
            wordCategories.put("Colours", Arrays.asList("Black","Blue","Mauve","Yellow","Orange","Purple","Pink","Brown","Grey","Turquoise","Khaki","Azure","Cyan","Magenta","Beige"));
            wordCategories.put("Fruits", Arrays.asList("Pineapple","Durian","Banana","Cherry","Orange","Pear","Blackberry","Blueberry","Strawberry","Raspberry","Kiwi","Coconut","Mango","Watermelon","Peach","Grape","Lemon","Plum","Lychee","Dragonfruit","Pomegranate","Papaya"));
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
