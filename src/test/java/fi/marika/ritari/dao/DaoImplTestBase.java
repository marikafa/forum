package fi.marika.ritari.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import fi.marika.ritari.Main;
import fi.marika.ritari.factory.EntityFactory;
import fi.marika.ritari.model.Message;
import fi.marika.ritari.model.Topic;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Main.class)
@TestExecutionListeners({ TransactionalTestExecutionListener.class, 
					DependencyInjectionTestExecutionListener.class, 
				FlywayTestExecutionListener.class })
public abstract class DaoImplTestBase {
	
	@Autowired
	protected EntityFactory ef;
	
	@PersistenceContext
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	protected List<Topic> getAllTopics() {
		Query q = em.createQuery("SELECT t FROM Topic t");
		return q.getResultList();	
	}
	
	@SuppressWarnings("unchecked")
	protected List<Message> getAllMessages() {
		Query q = em.createQuery("SELECT m FROM Message m");
		return q.getResultList();
	}
}
