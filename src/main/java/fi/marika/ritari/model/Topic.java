package fi.marika.ritari.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Topic { 

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", insertable=false, updatable=false)
    private int id;

   
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages;  

    @Column(length=100, nullable=false)  
    private String title;

    public Topic() {
    }

    public Topic(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }    
    
    public List<Message> getMessages() { 
        return messages;
    }
	
    
    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }    
    
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("id", id);
        builder.append("messages", messages);
        builder.append("title", title);
        return builder.toString();
    }

}
