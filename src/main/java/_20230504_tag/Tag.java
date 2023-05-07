package _20230504_tag;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tag implements Comparable{
    public static final int FAIL = -1;
    private final int value;

    public Tag(int value) {
        this.value = value;
    }

    public static Tag crateFailTag() {
        return new Tag(FAIL);
    }

    public static Tag of(String string) {
        return new Tag(Integer.parseInt(string));
    }

    public static List<Tag> createDefault() {
        return Stream.of(1,2,3,4,5,6,7,8,9)
                .map(value -> new Tag(value))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return value == tag.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            throw new RuntimeException();
        }

        if (!(o instanceof Tag)) {
            throw new RuntimeException();
        }

        return this.value - ((Tag) o).value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
