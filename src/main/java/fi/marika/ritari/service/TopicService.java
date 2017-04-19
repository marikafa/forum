package fi.marika.ritari.service;

import java.util.List;

import fi.marika.ritari.model.Topic;

public interface TopicService {	

	public TopicDto addTopic(NewTopicDto topic); 
	public void updateTopic(int topicID, EditTopicDto newTitle) ; 
	public void removeTopic(int id); 	
	public TopicDto getTopicById(int id); 	
	public List<TopicDto> listTopics(); 
	public List<TopicDto> convertEntitiesToDtos(List<Topic> entities);
}
