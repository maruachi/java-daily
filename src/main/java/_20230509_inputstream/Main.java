package _20230509_inputstream;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = new ByteArrayInputStream("한hello world\n".getBytes(StandardCharsets.UTF_8));
        InputStream bis = new BufferedInputStream(inputStream, 8192);

        StringBuilder stringBuilder = new StringBuilder(100);
        stringBuilder.append("바이트 배열:");
        byte[] buffer = new byte[100];
        try {
            int len = bis.read(buffer);
            for (int i = 0; i < len; i++) {
                stringBuilder.append(' ').append(buffer[i]);
            }
            String string = new String(buffer, 0, len, StandardCharsets.UTF_8);
            System.out.print("문자열 배열:");
            for (int i = 0; i < string.length(); i++) {
                System.out.print(" "+(int) string.charAt(i));
            }
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(stringBuilder);
    }
}
