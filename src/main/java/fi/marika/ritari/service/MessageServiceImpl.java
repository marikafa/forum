package fi.marika.ritari.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.marika.ritari.dao.MessageDao;
import fi.marika.ritari.dao.TopicDao;
import fi.marika.ritari.model.Message;
import fi.marika.ritari.model.Topic;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDAO;

    @Autowired
    private TopicDao topicDAO;

    public List<MessageDto> convertEntitiesToDtos(List<Message> entities) {
        List<MessageDto> results = new ArrayList<MessageDto>();

        for (Message m : entities) {
            MessageDto dto = new MessageDto(m.getId(), m.getCreateTime(), m.getContent(), m.getTopic().getId());
            results.add(dto);
        }

        return results;
    }

    @Override
    @Transactional
    public MessageDto addMessage(NewMessageDto m, int topicID) {
        Topic t = topicDAO.getTopicById(topicID);     
        Message message = messageDAO.addMessage(m.content, t);

        return new MessageDto(message.getId(), message.getCreateTime(), message.getContent(), message.getTopic().getId());

    }

    @Override
    @Transactional(readOnly=true)
    public List<MessageDto> listMessages(int topicID) {
        return convertEntitiesToDtos(topicDAO.getTopicById(topicID).getMessages());
    }

    @Override
    @Transactional(readOnly=true)
    public MessageDto getMessageById(int id) {
        Message m = this.messageDAO.getMessageById(id);
        return new MessageDto(m.getId(), m.getCreateTime(), m.getContent(), m.getTopic().getId());
    }

    @Override
    @Transactional
    public void removeMessage(int id) {
        this.messageDAO.removeMessage(id);
    }

    @Override
    @Transactional
    public void updateMessage(int messageID, EditMessageDto newContent) {
        Message m = messageDAO.getMessageById(messageID);
        m.setContent(newContent.content);
    }
}