package _20230504_stream;

import java.util.Map;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        int sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .reduce(0, (a, b) -> a + b);

        Map<String, String> map = Map.of("1", "2", "5", "4", "3", "2");
        String string = map.entrySet().stream()
                        .reduce(new StringBuilder(100), (sb, entry) -> sb.append(entry.getKey()).append("(").append(entry.getValue()).append(")").append(" "), (sb1, sb2)-> sb1.append(sb2)).toString();

        System.out.println(sum);
        System.out.println(string);
    }
}
