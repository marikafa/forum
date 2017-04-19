package fi.marika.ritari.dao;

import fi.marika.ritari.model.Message;
import fi.marika.ritari.model.Topic;

public interface MessageDao {
	
    public Message addMessage(String content, Topic topic); 
	public Message getMessageById(int messageID);    
    public void removeMessage(int id);
}
