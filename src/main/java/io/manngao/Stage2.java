package io.manngao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Stage 2 - advanced requirement
 * Instead of removing the consecutively identical characters, replace them with a
 * single character that comes before it alphabetically.
 * Example:
 * ccc -> b
 * bbb -> a
 * <p>
 * Input: abcccbad
 * Output:
 * -> abbbad, ccc is replaced by b
 * -> aaad, bbb is replaced by a
 * -> d
 *
 * @author gaoming
 */
public class Stage2 {

    private static final Pattern PATTERN_ALPHABET = Pattern.compile("[a-z]+");

    public static String replace(String input) throws Exception {
        System.out.println("Input:" + input);
        //  valid
        if (input == null || input.isEmpty()) {
            throw new Exception("input empty!");
        } else if (!isAlphabet(input)) {
            throw new Exception("exist no alphabet!");
        } else if (input.length() < 3) {
            return input;
        }
        // recur
        Map<String, String[]> trace = new LinkedHashMap<>();
        String result = "";
        result = replaceRecur(input, trace);
        // output
        System.out.println("Output:");
        if (trace == null || trace.isEmpty()) {
            System.err.println("There's nothing to replace!");
            result = input;
        } else {
            for (Map.Entry<String, String[]> entry : trace.entrySet()) {
                String replaceResult = entry.getKey();
                String replaced = entry.getValue()[0];
                String replace = entry.getValue()[1];
                System.out.println("->" + replaceResult + ", " + replaced + " is replaced by " + (replace.isEmpty() ? null : replace));
            }
        }
        return result;
    }

    /**
     * Replacement Recuroursely
     *
     * @param input: last replace result
     * @param map:   "-> $key, $value[0] is replaced by $value[1]"
     * @return
     */
    private static String replaceRecur(String input, Map<String, String[]> map) {
        char[] charArray = input.toCharArray();
        char curChar = 0;
        StringBuilder identical = new StringBuilder();
        Map<String, String> replaceMap = new HashMap<>();
        for (int i = 0, len = charArray.length; i < len; i++) {
            char c = charArray[i];
            if (curChar == c) {
                identical.append(c);
            } else {
                if (identical.length() >= 3) {
                    buildReplaceMap(curChar, identical, replaceMap);
                    identical.setLength(0);
                    identical.append(c);
                }
                curChar = c;
                identical.setLength(0);
                identical.append(c);
            }
            // identical at last
            if (i == len - 1 && identical.length() >= 3) {
                buildReplaceMap(curChar, identical, replaceMap);
            }
        }
        if (replaceMap != null && !replaceMap.isEmpty()) {
            for (Map.Entry<String, String> entry : replaceMap.entrySet()) {
                input = input.replace(entry.getKey(), entry.getValue());
                map.put(input, new String[]{entry.getKey(), entry.getValue()});
            }
            return replaceRecur(input, map);
        }
        return input;
    }

    private static void buildReplaceMap(char curChar, StringBuilder identical, Map<String, String> replaceMap) {
        String replace = "";
        if (curChar > 97) {
            replace = String.valueOf(Character.toChars(curChar - 1));
        }
        replaceMap.put(identical.toString(), replace);
    }

    private static boolean isAlphabet(String input) {
        return PATTERN_ALPHABET.matcher(input).matches();
    }

}
