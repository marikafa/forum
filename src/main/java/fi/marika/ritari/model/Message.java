package fi.marika.ritari.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id", insertable=false, updatable=false)
	private int id;

	@Column
	private LocalDateTime createTime;

	@Column (length=1000, nullable=false)
	private String content;
		
	@ManyToOne
	@JoinColumn(name="topic", nullable=false)
	private Topic topic;
	
	 
	public Message() {
		
	}
	public Message(String content, LocalDateTime createTime, Topic t) {
		this.content = content;
		this.createTime = createTime;
		this.topic = t;
		
	}
	
	public Topic getTopic() {
		return topic;
	}	

	public int getId() { 
		return id;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}  	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getTopicID() { 
		return topic.getId(); 
	}
}