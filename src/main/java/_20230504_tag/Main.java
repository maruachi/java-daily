package _20230504_tag;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static final int BUFFER_SIZE = 8192;
    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static void main(String[] args) {
        List<String> lines = readLines();

        List<String[]> parsedLines = lines.stream()
                .map(line -> parseLine(line))
                .collect(Collectors.toUnmodifiableList());

        List<Commandable> commands = parsedLines.stream()
                .map(lineElements -> createCommand(lineElements))
                .collect(Collectors.toUnmodifiableList());

        Queue<Tag> tags = new PriorityQueue<>(Tag.createDefault());
        Set<Tag> usableTags = new HashSet<>();
        List<ResultDto> resultDtos = commands.stream()
                .map(command -> command.run(tags, usableTags))
                .collect(Collectors.toUnmodifiableList());

        Map<Tag, Integer> failCount = resultDtos.stream()
                .filter(resultDto -> resultDto.isFail())
                .collect(Collectors.toMap(r -> r.getTag(), r -> 1, Integer::sum));

        StringBuilder stringBuilder = new StringBuilder(100);

        stringBuilder.setLength(0);
        stringBuilder.append("대기중인 태그:");
        String tagsResult = tags.stream()
                .reduce(stringBuilder,
                        (sb, tag) -> sb.append(' ').append(tag.toString()),
                        (sb1, sb2) -> sb1.append(sb2))
                .toString();
        System.out.println(tagsResult);

        stringBuilder.setLength(0);
        String createFailResult = stringBuilder.append("생성실패횟수:").append(' ')
                .append(failCount.getOrDefault(Tag.crateFailTag(), 0))
                .toString();
        System.out.println(createFailResult);

        stringBuilder.setLength(0);
        stringBuilder.append("실행실패이력:");
        String tagRunFailResult = failCount.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(Tag.crateFailTag()))
                .sorted((entry1, entry2)->{
                    int compareTo = entry2.getValue().compareTo(entry1.getValue());
                    if (compareTo == 0) {
                        return entry1.getKey().compareTo(entry2.getKey());
                    }
                    return compareTo;
                })
                .reduce(stringBuilder,
                        (sb, entry) -> sb.append(' ').append(entry.getKey()).append('(').append(entry.getValue()).append(')'),
                        (sb1, sb2) -> sb1.append(sb2))
                .toString();
        System.out.println(tagRunFailResult);
    }

    private static Commandable createCommand(String[] lineElements) {
        int numElement = lineElements.length;
        if (numElement == 0) {
            throw new RuntimeException();
        }

        Command command = Command.create(lineElements[0]);

        if (numElement == 1 && command == Command.CREATE) {
            return new Create();
        }

        if (numElement == 2 && command == Command.EXEUCTE) {
            return new Execute(Tag.createByString(lineElements[1]));
        }

        throw new RuntimeException();
    }

    private static String[] parseLine(String line) {
        return Arrays.stream(line.split(" "))
                .filter(element -> !element.isEmpty())
                .toArray(String[]::new);
    }

    private static List<String> readLines() {
        BufferedReader reader = toReader(System.in);
        try {
            IntFunction<String> readLine = i -> {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };

            int N = Integer.parseInt(reader.readLine());
            return IntStream.range(0, N)
                    .mapToObj(readLine)
                    .collect(Collectors.toUnmodifiableList());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new RuntimeException(ioException);
        }
    }

    private static BufferedReader toReader(InputStream in) {
        InputStream bis = new BufferedInputStream(in, BUFFER_SIZE);
        InputStreamReader reader = new InputStreamReader(bis, UTF_8);
        return new BufferedReader(reader, BUFFER_SIZE);
    }
}
