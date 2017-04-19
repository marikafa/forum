package fi.marika.ritari.dao;

import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fi.marika.ritari.model.Message;
import fi.marika.ritari.model.Topic;

@Repository
public class MessageDaoImpl implements MessageDao {

	@Autowired
	private EntityManager em;

	@Override
	public Message addMessage(String content, Topic t) {
	    LocalDateTime date = LocalDateTime.now();
		Message m = new Message(content, date, t);
		em.persist(m);
		return m;
	}

	@Override
	public Message getMessageById(int messageID) {
		Message m = em.getReference(Message.class, messageID);
		return m;
	}

	@Override
	public void removeMessage(int messageID) {
		Message m = getMessageById(messageID);
		em.remove(m);
	}
}