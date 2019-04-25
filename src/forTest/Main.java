package forTest;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String args[]) {
        String data = "aaabbbcccdddffffrss";
        char[] charArray = data.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : charArray) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        System.out.println(map);
    }
}
