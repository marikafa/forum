package fi.marika.ritari.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fi.marika.ritari.dao.MessageDao;
import fi.marika.ritari.model.Message;
import fi.marika.ritari.model.Topic;

public class MessageDaoImplTest extends DaoImplTestBase {

    @Autowired
    private MessageDao messageDao;

    @Test
    @FlywayTest
    @Transactional
    public void testGettingAllMessagesWhenNoMessagesInDatabase() {
        List<Message> messages = getAllMessages();
        Assert.assertEquals(0, messages.size());
    }

    @Test
    @FlywayTest
    @Transactional
    public void testAddMessage() {

        Message m = messageDao.addMessage("testiviesti", ef.newTopic("otsikko"));
        Assert.assertNotNull(m);

        List<Message> messages = getAllMessages();
        Assert.assertEquals(1, messages.size());
        Assert.assertEquals(m, messages.get(0));

    }

    @Test
    @Transactional
    @FlywayTest
    public void testRemoveMessage() {

        String messageContent = "hello world englanniksi on hei maailma suomeksi";
        LocalDateTime createTime = LocalDateTime.now();
        Topic t = ef.newTopic("testi topic");

        ef.newMessage(messageContent, createTime, t);

        List<Message> messages = getAllMessages();
        Assert.assertEquals(createTime, messages.get(0).getCreateTime());

        messageDao.removeMessage(messages.get(0).getId());

        List<Message> messages2 = getAllMessages();
        Assert.assertEquals(0, messages2.size());

    }
}
