package fi.marika.ritari.dao;

import java.util.List;

import fi.marika.ritari.model.Topic;

public interface TopicDao {

	public Topic addTopic(String title);
	public Topic getTopicById(int id);	
   	public List<Topic>getAllTopics();       
	public void removeTopic(int id);

}