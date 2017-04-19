package fi.marika.ritari.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TopicDto { 
	
	public final int id;  
	public final String title;   
	
	public TopicDto(int id, String title) {
		this.id = id;
		this.title = title;
	}

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("id", id);
        builder.append("title", title);
        return builder.toString();
    }
}