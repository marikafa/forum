package fi.marika.ritari.service;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class EditMessageDto {

	public String content;
	private LocalDateTime editTime;	
	
	EditMessageDto() { // for Jackson
	    
	}

	public EditMessageDto(String content) {
		this.content = content;
		editTime = LocalDateTime.now();
	}
	

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("content", content);
		builder.append("editTime", editTime); 
		return builder.toString();
	}
}