package fi.marika.ritari.dao;

import static org.junit.Assert.fail;

import java.util.List;

import javax.transaction.Transactional;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fi.marika.ritari.dao.TopicDao;
import fi.marika.ritari.model.Topic;


public class TopicDaoImplTest extends DaoImplTestBase {
	
	@Autowired
	private TopicDao topicDao;

	@Test
	@FlywayTest
	@Transactional	
	public void testAddTopic() {	
		
		String title = "testi title JUnit";
		Topic t = topicDao.addTopic(title);
		Assert.assertNotNull(t);
		                
		List<Topic> topics = getAllTopics();	
	
		 Assert.assertEquals(t, topics.get(0));
		 Assert.assertEquals(title, t.getTitle());				
	}	
	

	@Test
	@Transactional
	@FlywayTest
	public void testGetAllTopicsAndVerifyData() {		
		String title1 = "a_testititle1";
		String title2 = "b_testititle2";
		
		ef.newTopic(title1);
		ef.newTopic(title2);		
		
		List<Topic> topics = topicDao.getAllTopics();
		
		Assert.assertNotNull(topics); 
		Assert.assertEquals(2, topics.size()); 		
	}
	
	
	@Test
	@Transactional
	@FlywayTest
	public void tryRemovingTopicThatNoLongerExists() { 
		Topic t = ef.newTopic("remove_me");
		int id = t.getId();
				
		topicDao.removeTopic(id);
			
		List<Topic> topics = getAllTopics();
		Assert.assertEquals(0, topics.size());
				
		try {		
			topicDao.removeTopic(id);		 	
			
			fail("Should have thrown");
		} 
		catch (Exception e) {			
		}
	}
	

	@Test
	@Transactional
	@FlywayTest
	public void testRemoveTopic() {	
	
		String title = "testititle";
		ef.newTopic(title);			
		
		List<Topic> topics = getAllTopics();		
					
		topicDao.removeTopic(topics.get(0).getId());		
		
		List<Topic> topics2 = getAllTopics();
		
		Assert.assertEquals(0, topics2.size()); 
		
	}
	
	@Test
	@Transactional
	@FlywayTest
	public void testRemoveTopicRemovesOnlySelectedTopic() {
		Topic t1 = ef.newTopic("eka");
		int t1Id = t1.getId();
		Topic t2 = ef.newTopic("toka");
		String t2Title = t2.getTitle();	
		
		List<Topic> topics = getAllTopics();
		
		Assert.assertEquals(2, topics.size());
		
		topicDao.removeTopic(t1Id);
		
		List<Topic> topics2 = getAllTopics();	
		
		Assert.assertEquals(1, topics2.size()); 
		Assert.assertEquals(t2Title, topics2.get(0).getTitle()); 	
		
	}

		 
	@Test 
	@Transactional
	@FlywayTest
	public void testGettingTopicbyIdWhenTopicExistsWithThatID() {
		
		Topic t = ef.newTopic("joku");
		int id = t.getId();
		String title = t.getTitle();			
	
		Topic t2 = topicDao.getTopicById(id); 
		
		Assert.assertEquals(title, t2.getTitle()); 			
	}
	

	@Test 
	@Transactional
	@FlywayTest	 
	public void testGetTopicByIdWhenTopicDoesNotExistWithThatID() {		
		int id = 200; 		
		try {
			Topic t = topicDao.getTopicById(id);
			t.getId(); // exception comes when instance state first accessed
			fail("Should have thrown");
		} catch (Exception e) {}
	}
}