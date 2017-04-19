package fi.marika.ritari.factory;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import fi.marika.ritari.model.Message;
import fi.marika.ritari.model.Topic;

@Component
public class EntityFactory {
	
	@PersistenceContext
	protected EntityManager em;	
	

	public Message newMessage(String content, LocalDateTime date, Topic t) {
		Message m = new Message(content, date, t);
		em.persist(m);
		return m;
	}
	
	public Message newMessage(String content, LocalDateTime date) {
		Message m = new Message(content, date, newTopic("test"));
		em.persist(m);
		return m;
	}
	
	public Topic newTopic(String title) {
		Topic topic = new Topic();
		topic.setTitle(title);
		em.persist(topic);		
		return topic;
	}

}
