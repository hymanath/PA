package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "event_gender_day_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EventGenderDayInfo extends BaseModel implements Serializable {
	
        private Long eventGenderDayInfoId;
        private Long eventGenderInfoId;
        private Long total;
    	private Long invitee;
    	private Long nonInvitee;
    	private String dayStr;
    	
        private EventGenderInfo eventGenderInfo;
        @Id
    	@GeneratedValue(strategy = GenerationType.AUTO)
    	@Column(name = "event_gender_day_info_id", unique = true, nullable = false)
		public Long getEventGenderDayInfoId() {
			return eventGenderDayInfoId;
		}
		public void setEventGenderDayInfoId(Long eventGenderDayInfoId) {
			this.eventGenderDayInfoId = eventGenderDayInfoId;
		}
		@Column(name = "event_gender_info_id")
		public Long getEventGenderInfoId() {
			return eventGenderInfoId;
		}
		public void setEventGenderInfoId(Long eventGenderInfoId) {
			this.eventGenderInfoId = eventGenderInfoId;
		}
		@Column(name = "total")
		public Long getTotal() {
			return total;
		}
		public void setTotal(Long total) {
			this.total = total;
		}
		@Column(name = "invitee")
		public Long getInvitee() {
			return invitee;
		}
		public void setInvitee(Long invitee) {
			this.invitee = invitee;
		}
		@Column(name = "non_invitee")
		public Long getNonInvitee() {
			return nonInvitee;
		}
		public void setNonInvitee(Long nonInvitee) {
			this.nonInvitee = nonInvitee;
		}
		@Column(name = "day_str")
		public String getDayStr() {
			return dayStr;
		}
		public void setDayStr(String dayStr) {
			this.dayStr = dayStr;
		}
		
		@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
		@JoinColumn(name = "event_gender_info_id")
		@LazyToOne(LazyToOneOption.NO_PROXY)
		@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
		public EventGenderInfo getEventGenderInfo() {
			return eventGenderInfo;
		}
		public void setEventGenderInfo(EventGenderInfo eventGenderInfo) {
			this.eventGenderInfo = eventGenderInfo;
		}
        
        
}
