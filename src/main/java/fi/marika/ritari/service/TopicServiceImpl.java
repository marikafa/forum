package fi.marika.ritari.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.marika.ritari.dao.TopicDao;
import fi.marika.ritari.model.Topic;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicDAO;

    public List<TopicDto> convertEntitiesToDtos(List<Topic> entities) {
        List<TopicDto> results = new ArrayList<TopicDto>();

        for (Topic t : entities) {
            TopicDto dto = new TopicDto(t.getId(), t.getTitle());
            results.add(dto);
        }
        return results;
    }

    @Override
    @Transactional
    public TopicDto addTopic(NewTopicDto topic) {
        Topic t = topicDAO.addTopic(topic.title);
        return new TopicDto(t.getId(), t.getTitle());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TopicDto> listTopics() {
        return convertEntitiesToDtos(topicDAO.getAllTopics());

    }

    @Override
    @Transactional(readOnly = true)
    public TopicDto getTopicById(int id) {
        Topic t = this.topicDAO.getTopicById(id);
        return new TopicDto(t.getId(), t.getTitle());
    }

    @Override
    @Transactional
    public void removeTopic(int id) {
        this.topicDAO.removeTopic(id);
    }

    @Override
    @Transactional
    public void updateTopic(int topicID, EditTopicDto newTitle) {

        Topic t = topicDAO.getTopicById(topicID);
        t.setTitle(newTitle.title);
    }
}