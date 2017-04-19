package fi.marika.ritari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fi.marika.ritari.service.EditMessageDto;
import fi.marika.ritari.service.EditTopicDto;
import fi.marika.ritari.service.MessageDto;
import fi.marika.ritari.service.MessageService;
import fi.marika.ritari.service.NewMessageDto;
import fi.marika.ritari.service.NewTopicDto;
import fi.marika.ritari.service.TopicDto;
import fi.marika.ritari.service.TopicService;

@Controller
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	@Autowired
    private MessageService messageService;

	@RequestMapping(value = "/topics", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<TopicDto> getAllTopics() {
		List<TopicDto> topics = topicService.listTopics();

		return topics;
	}

	@RequestMapping(value = "/topics/{topicID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TopicDto getTopic(@PathVariable int topicID) {
		return topicService.getTopicById(topicID);
	}

	@RequestMapping(value = "/topics/{topicID}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void editTopic(@PathVariable int topicID, @RequestBody EditTopicDto editTopicDto) {
		topicService.updateTopic(topicID, editTopicDto);
	}

	@RequestMapping(value = "/topics", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void addTopic(@RequestBody NewTopicDto topic) {
	    topicService.addTopic(topic);
	}

	@RequestMapping(value = "/topics/{topicID}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void removeTopic(@PathVariable int topicID) {
		topicService.removeTopic(topicID);
	}
	
	@RequestMapping(value = "/topics/{topicID}/messages", method = RequestMethod.GET)
    public @ResponseBody List<MessageDto> getAllMessagesForTopic(@PathVariable int topicID) {
        List<MessageDto> messages = messageService.listMessages(topicID);
        return messages;
    }

    @RequestMapping(value = "/topics/{topicID}/messages/{messageID}", method = RequestMethod.GET)
    public @ResponseBody MessageDto getMessage(@PathVariable int messageID) {
        return messageService.getMessageById(messageID);
    }

    @RequestMapping(value = "/topics/{topicID}/messages", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addMessage(@RequestBody NewMessageDto message, @PathVariable int topicID) {    
        messageService.addMessage(message, topicID);
    }

    @RequestMapping(value = "/topics/{topicID}/messages/{messageID}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editMessage(@PathVariable int messageID, @RequestBody EditMessageDto editMessageDto) {
        messageService.updateMessage(messageID, editMessageDto);
    }

    @RequestMapping(value = "/topics/{topicID}/messages/{messageID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT) 
    public void deleteMessage(@PathVariable int messageID) {
        messageService.removeMessage(messageID);
    }

}