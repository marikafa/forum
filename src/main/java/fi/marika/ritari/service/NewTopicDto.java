package fi.marika.ritari.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class NewTopicDto {

	public String title;
	
	public NewTopicDto() { // for Jackson
	    
	}
	public NewTopicDto(String title) {
		 this.title= title;
	}	


	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("title", title);
		return builder.toString();
	}

}
