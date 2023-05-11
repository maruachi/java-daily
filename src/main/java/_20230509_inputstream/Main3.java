package _20230509_inputstream;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main3 {
    public static void main(String[] args) {
        String emptyString = "";
        System.out.println(Arrays.toString(emptyString.getBytes(StandardCharsets.UTF_8)));
        System.out.println(emptyString.getBytes(StandardCharsets.UTF_8).length);

        byte[] bytes = new byte[0];

        System.out.println(new String(bytes, StandardCharsets.UTF_8).equals(""));
    }
}
