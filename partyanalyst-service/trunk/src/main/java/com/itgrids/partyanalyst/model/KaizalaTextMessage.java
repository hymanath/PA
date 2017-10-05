package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "kaizala_text_message")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaTextMessage {
	
	private Long kaizalaTextMessageId;
	private String textMessage;
	private Long kaizalaEventsResponseId;
	
	private KaizalaEventsResponse kaizalaEventsResponse;
	
	
	
	@Id
	@Column(name="kaizala_text_message_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaTextMessageId() {
		return kaizalaTextMessageId;
	}
	public void setKaizalaTextMessageId(Long kaizalaTextMessageId) {
		this.kaizalaTextMessageId = kaizalaTextMessageId;
	}
	
	@Column(name="kaizala_events_response_id")
	public String getTextMessage() {
		return textMessage;
	}
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	
	@Column(name="kaizala_events_response_id")
	public Long getKaizalaEventsResponseId() {
		return kaizalaEventsResponseId;
	}
	public void setKaizalaEventsResponseId(Long kaizalaEventsResponseId) {
		this.kaizalaEventsResponseId = kaizalaEventsResponseId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "kaizala_events_response_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public KaizalaEventsResponse getKaizalaEventsResponse() {
		return kaizalaEventsResponse;
	}
	public void setKaizalaEventsResponse(KaizalaEventsResponse kaizalaEventsResponse) {
		this.kaizalaEventsResponse = kaizalaEventsResponse;
	}
}