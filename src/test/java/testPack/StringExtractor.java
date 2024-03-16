package testPack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExtractor {

    private static String extractNumber(String input) {
        // Define a regular expression pattern for extracting numbers with commas and periods
        String regex = "(\\d{1,3}(,\\d{3})*(\\.\\d+)?)|\\d+";

        // Use a regular expression to match the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Find the first match
        if (matcher.find()) {
            return matcher.group().replace(",", ""); // Remove commas
        }

        // If no match is found, return an empty string
        return "";
    }

    public static String extractProductName(String url) {
        return url.replaceAll(".*/product/(.*?)/.*", "$1");
    }

}