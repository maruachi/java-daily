package _20230504_versioncheck;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");

        List<Integer> list = List.of(0, 1, 2, 3, 4).stream()
                .collect(Collectors.toUnmodifiableList());

        System.out.println(list);
    }
}
