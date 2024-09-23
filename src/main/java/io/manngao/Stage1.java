package io.manngao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Stage1
 * <p>
 * For a given string that only contains alphabet characters a-z, if 3 or more consecutive
 * characters are identical, remove them from the string. Repeat this process until
 * there is no more than 3 identical characters sitting besides each other.
 * Example:
 * Input: aabcccbbad
 * Output:
 * -> aabbbad
 * -> aaad
 * -> d
 *
 * @author gaoming
 */
public class Stage1 {

    private static final Pattern PATTERN_ALPHABET = Pattern.compile("[a-z]+");

    public static String remove(String input) throws Exception {
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
        List<String> list = new ArrayList<>();
        removeRecur(input, list);
        // output
        String result = "";
        System.out.println("Output:");
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                System.out.println("->" + str);
            }
            result = list.get(list.size() - 1);
        } else {
            System.err.println("There's nothing to remove!");
            result = input;
        }
        return result;
    }

    private static void removeRecur(String input, List<String> list) {
        char[] charArray = input.toCharArray();
        char curChar = 0;
        StringBuilder identical = new StringBuilder();
        List<String> removeList = new ArrayList<>();
        for (int i = 0, len = charArray.length; i < len; i++) {
            char c = charArray[i];
            if (curChar == c) {
                identical.append(c);
            } else {
                if (identical.length() >= 3) {
                    removeList.add(identical.toString());
                    identical.setLength(0);
                    identical.append(c);
                }
                curChar = c;
                identical.setLength(0);
                identical.append(c);
            }
            if (i == len - 1 && identical.length() >= 3) {
                removeList.add(identical.toString());
            }
        }
        if (removeList != null && !removeList.isEmpty()) {
            for (String replace : removeList) {
                input = input.replace(replace, "");
            }
            list.add(input);
            removeRecur(input, list);
        }
    }

    private static boolean isAlphabet(String input) {
        return PATTERN_ALPHABET.matcher(input).matches();
    }

}
