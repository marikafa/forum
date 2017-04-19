package fi.marika.ritari.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class NewMessageDto { 
	
	public String content;	
			
	NewMessageDto() { // for Jackson
        
    }

    public NewMessageDto(String content) {
		this.content = content;			
	}	

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("content", content);		
		return builder.toString();
	}
}
