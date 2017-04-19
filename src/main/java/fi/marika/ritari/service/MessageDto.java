package fi.marika.ritari.service;

import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class MessageDto {

    public final int id;
    public final LocalDateTime createTime;
    public final String content;
    public final int topicId;

    public MessageDto(int id, LocalDateTime createTime, String content, int topicId) {
        this.id = id;
        this.createTime = createTime;
        this.content = content;
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("id", id);
        builder.append("create time", createTime);
        builder.append("content:" + content);
        builder.append("topic:" + topicId);
        return builder.toString();
    }

}
