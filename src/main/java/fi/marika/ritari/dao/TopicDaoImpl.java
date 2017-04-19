package fi.marika.ritari.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fi.marika.ritari.model.Topic;

@Repository
public class TopicDaoImpl implements TopicDao {

	@Autowired
	private EntityManager em;

	@Override
	public Topic addTopic(String title) {
		Topic topic = new Topic();
		topic.setTitle(title);
		em.persist(topic);		
		return topic;
	}

	@Override
	public Topic getTopicById(int id) {
		Topic t = em.getReference(Topic.class, id);
		return t;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Topic> getAllTopics() {
		Query q = em.createQuery("SELECT t FROM Topic t ORDER BY t.title");
		return q.getResultList();
	}

	@Override
	public void removeTopic(int topicID) {
		Topic t = getTopicById(topicID);
		em.remove(t);
	}
}