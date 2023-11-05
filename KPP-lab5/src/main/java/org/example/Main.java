package org.example;

public class Main {
    public static void main(String[] args) {
        var removeWordsUtil = new RemoveWords();
        String text = "The quick brown fox jumps over the lazy dog. Or does it? A fox is in the garden.";
        String result = removeWordsUtil.removeSpecifiedWords(text);
        System.out.println(result);
    }
}
