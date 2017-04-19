package fi.marika.ritari.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class EditTopicDto {

    public String title;    
    

    EditTopicDto() { // for Jackson
       
    }

    public EditTopicDto(String title) {
        this.title = title;

    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("title", title);
        return builder.toString();
    }
}