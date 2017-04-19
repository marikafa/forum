package fi.marika.ritari.service;

import java.util.List;

import fi.marika.ritari.model.Message;

public interface MessageService {
	
	public MessageDto addMessage(NewMessageDto m, int topicID);
	public void updateMessage(int messageID, EditMessageDto newContent); 
	public List<MessageDto> listMessages(int topicID); 
	public MessageDto getMessageById(int id); 
	public void removeMessage(int id); 
	
	public List<MessageDto> convertEntitiesToDtos(List<Message> entities);

}
