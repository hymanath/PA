package com.itgrids.partyanalyst.model;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "debate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Debate extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = 2770484105252236590L;			
	
//`debate_id`int, `start_time` TIMESTAMP ,`end_time` TIMESTAMP,`channel_id`,`telecast_type_id`,`is_deleted` ENUM,`summary` TEXT	
	
	//fields
	private Long debateId;
	private Date startTime;
	private Date endTime;
	private Channel  channel;
	private TelecastType telecastType;
	private String isDeleted;
	private String summary;
	private Date createdDate;
	
	//private Set<DebateObserver> debateObserver = new HashSet<DebateObserver>(0);
	//private Set<DebateParticipant> debateParticipant = new HashSet<DebateParticipant>(0);
	private Font font;
	
	//default constructor.
	 
    public Debate() {}


    //setters and getters.
    @Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	@Column(name = "debate_id", unique = true, nullable = false)
	public Long getDebateId() {
		return debateId;
	}


	public void setDebateId(Long debateId) {
		this.debateId = debateId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time")
	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "channel_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Channel getChannel() {
		return channel;
	}


	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "telecast_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TelecastType getTelecastType() {
		return telecastType;
	}


	public void setTelecastType(TelecastType telecastType) {
		this.telecastType = telecastType;
	}

	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name="summary")
	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}

	
	@Column(name="created_date")
	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "debate")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DebateObserver> getDebateObserver() {
		return debateObserver;
	}


	public void setDebateObserver(Set<DebateObserver> debateObserver) {
		this.debateObserver = debateObserver;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "debate")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DebateParticipant> getDebateParticipant() {
		return debateParticipant;
	}


	public void setDebateParticipant(Set<DebateParticipant> debateParticipant) {
		this.debateParticipant = debateParticipant;
	}*/
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "font_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	
	
	
	
}
