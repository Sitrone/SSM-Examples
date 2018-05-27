package com.sununiq.snippet.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class UniqueMorseRepresentations {
    public int uniqueMorseRepresentations(String[] words) {
        String[] index = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> hashSet = new HashSet<>(words.length);
        for (String word : words) {
            StringBuilder mode = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                mode.append(index[word.charAt(i) - 'a']);
            }
            hashSet.add(mode.toString());
        }

        return hashSet.size();
    }

    public int uniqueMorseRepresentations2(String[] words) {
        String[] index = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        return Stream.of(words).map(word -> {
            StringBuilder mode = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                mode.append(index[word.charAt(i) - 'a']);
            }
            return mode;
        }).collect(toSet()).size();
    }
}
