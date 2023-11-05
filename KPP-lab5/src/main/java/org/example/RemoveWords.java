package org.example;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveWords {

    public String removeSpecifiedWords(String input) {

        String regex = "(?i)\\b(a|the|or|are|on|in|out)\\b\\s?";
        String modifiedInput = input.replaceAll(regex, "").replaceAll("\\s+", " ").trim();

        // Capitalize the first letter of each sentence
        Pattern sentencePattern = Pattern.compile("(^|(?<=[.!?]\\s))\\w");
        Matcher matcher = sentencePattern.matcher(modifiedInput);

        StringBuilder capitalizedText = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(capitalizedText, matcher.group().toUpperCase());
        }
        matcher.appendTail(capitalizedText);

        return capitalizedText.toString();
    }
}
