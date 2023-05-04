package _20230504_tag;

import java.util.Queue;
import java.util.Set;

public class Create implements Commandable{
    @Override
    public ResultDto run(Queue<Tag> tags, Set<Tag> usableTags) {
        if (tags.isEmpty()) {
            return new ResultDto(Tag.crateFailTag(), false);
        }

        Tag tag = tags.poll();
        usableTags.add(tag);
        return new ResultDto(tag, true);
    }
}
