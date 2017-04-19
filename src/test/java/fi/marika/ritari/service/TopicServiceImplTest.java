package fi.marika.ritari.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import fi.marika.ritari.Main;
import fi.marika.ritari.factory.EntityFactory;
import fi.marika.ritari.model.Topic;
import fi.marika.ritari.service.NewTopicDto;
import fi.marika.ritari.service.TopicDto;
import fi.marika.ritari.service.TopicService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Main.class)
@TestExecutionListeners({ TransactionalTestExecutionListener.class, 
					DependencyInjectionTestExecutionListener.class, 
					FlywayTestExecutionListener.class })
public class TopicServiceImplTest {
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private EntityFactory ef;
	
	@Test
	@FlywayTest	
	@Transactional 
	public void testAddTopic() {              
	
		NewTopicDto newTopicDto = new NewTopicDto("uusi topic dto");
		
		TopicDto dto = topicService.addTopic(newTopicDto);
		
		Assert.assertNotNull(dto);
		Assert.assertEquals("uusi topic dto", dto.title);
	}


	@Test
	@FlywayTest	
	@Transactional
	public void testConvertEntitiesToDtos() { 
		
		Topic first = ef.newTopic("eka");
		Topic second = ef.newTopic("toka");
		Topic third = ef.newTopic("kolmas");
		
		List<Topic> topics = new ArrayList<Topic>();
		topics.add(first);
		topics.add(second);
		topics.add(third);
		
		List<TopicDto> convertedDtos = topicService.convertEntitiesToDtos(topics);
		
		Assert.assertEquals(3, convertedDtos.size()); 
		Assert.assertEquals(first.getTitle(), convertedDtos.get(0).title);
		Assert.assertEquals(second.getTitle(), convertedDtos.get(1).title);
		Assert.assertEquals(third.getTitle(), convertedDtos.get(2).title);
		
	}
}
