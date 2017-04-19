package fi.marika.ritari.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import fi.marika.ritari.Main;
import fi.marika.ritari.controller.TopicController;
import fi.marika.ritari.factory.EntityFactory;
import fi.marika.ritari.model.Topic;
import fi.marika.ritari.service.NewTopicDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Main.class)
@TestExecutionListeners({ TransactionalTestExecutionListener.class, 
					DependencyInjectionTestExecutionListener.class, 
					FlywayTestExecutionListener.class })
public class TopicControllerTest {
	
	private MockMvc mockMvc;
	private ObjectMapper mapper;
	
	@Autowired
	private EntityFactory ef;
	
	@Autowired
	private TopicController controller;
	
	 @Before 
     public void setup() {
         mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
         mapper = new ObjectMapper();   
  
     }


	@Test
	@FlywayTest
	public void testGettingAllTopics() throws Exception { 	
		
			MvcResult result = mockMvc.perform(get("/topics"))			
			      .andExpect(status().isOk())			    
			      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			      .andReturn();				
		
			MockHttpServletResponse response = result.getResponse();			
			Assert.assertEquals(0, response.getContentLengthLong());	
	}
	
	
	@Test
	@FlywayTest
	public void postTopic() throws Exception {
		
		NewTopicDto topic = new NewTopicDto("test post topic");
		
		String topicJsonStr = "";		
		topicJsonStr = mapper.writeValueAsString(topic);	
		
		mockMvc.perform(post("/topics")
				  .contentType(MediaType.APPLICATION_JSON_UTF8)	
				  .content(topicJsonStr))
			      .andExpect(status().isNoContent())
			      .andReturn();	
		  			

	}
	
	@Test
	@FlywayTest
	@Transactional
	public void testGettingTopicByID() throws Exception {		
		Topic topic = ef.newTopic("joku topicci");
		long id = topic.getId();	
		
		MvcResult result = mockMvc.perform(get("/topics/" + id))			
			      .andExpect(status().isOk())
			      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			      .andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		 String s = response.getContentAsString();
		 Assert.assertTrue(s.contains(topic.getTitle()));
		 Assert.assertTrue(s.contains(String.valueOf(topic.getId())));				  
      
	}

}
