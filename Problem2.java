import java.util.*;

// Time Complexity : O(N) where N is the length of the output string
// Space Complexity : O(N) where N is the length of the output string
// Did this code successfully run on Leetcode : YES
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

/**
 * We have 4 types of characters in the input string: digits, alphabets, '[', and ']'.
 * We will use 2 stacks to solve this problem. One stack to store the number and
 * another stack to store the string. When we meet the '[', we will push the number
 * and the string to the stack and reset the currNum and currStr to process the
 * next substring. When we meet the ']', we will pop the number and the string
 * from the stack and multiply the string by the number and set this new string
 * to the currStr. If It's a digit, we will create the number from the characters.
 * If it's an alphabet, we will directly append it to the currStr.
 * We will keep doing this until we reach the end of the string.
 */

public class Problem2 {

    //Iterative DFS
    public String decodeString(String s) {
        Stack<Integer> numSt = new Stack<>();
        Stack<StringBuilder> strSt = new Stack<>();

        StringBuilder currStr = new StringBuilder();
        int currNum = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currNum = currNum * 10 + c - '0';
            } else if (c == '[') { //push
                numSt.push(currNum);
                strSt.push(currStr);
                //reset
                currStr = new StringBuilder();
                currNum = 0;
            } else if (c == ']') { //pop
                int cnt = numSt.pop();
                StringBuilder parent = strSt.pop();
                for (int k = 0; k < cnt; k++) {
                    parent.append(currStr);
                }
                currStr = parent;
            } else {
                currStr.append(c);
            }
        }

        return currStr.toString();

    }


    private int i = 0;

    public String decodeStringRecursiveDFS(String s) {
        StringBuilder currStr = new StringBuilder();
        int currNum = 0;

        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currNum = currNum * 10 + c - '0';
                i++;
            } else if (c == '[') {
                i++;
                String decoded = decodeStringRecursiveDFS(s);
                currStr.append(String.valueOf(decoded).repeat(Math.max(0, currNum)));
                //reset
                currNum = 0;
            } else if (c == ']') {
                i++;
                return currStr.toString();
            } else {
                currStr.append(c);
                i++;
            }
        }
        return currStr.toString();
    }
}
