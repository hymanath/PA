package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kaizala_events")
public class KaizalaEvents {
	private Long kaizalaEventsId;
	private String event;
	
	@Id
	@Column(name="kaizala_events_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaEventsId() {
		return kaizalaEventsId;
	}
	public void setKaizalaEventsId(Long kaizalaEventsId) {
		this.kaizalaEventsId = kaizalaEventsId;
	}
	@Column(name="event")
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
}
