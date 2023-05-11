package _20230509_inputstream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Stack;

public class Main2 {
    public static void main(String[] args) {
        String string = "hello world";
        InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));

    }
}
