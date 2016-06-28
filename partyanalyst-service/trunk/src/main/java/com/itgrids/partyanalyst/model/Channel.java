package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "channel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Channel extends BaseModel implements java.io.Serializable{

	
	//fields
	private Long channelId;
	private String channelName;
	private String isDeleted;
	private Set<Debate> debate = new HashSet<Debate>(0);

	
	//default constructor.
	
	public Channel() {
	}

  //setters and getters.
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "channel_id", unique = true, nullable = false)
	public Long getChannelId() {
		return channelId;
	}

	
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	@Column(name = "channel_name", length = 50)
	public String getChannelName() {
		return channelName;
	}


	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	@Column(name ="is_deleted",length = 1,nullable=true,updatable=true)
    public String getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
    
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "channel")
	public Set<Debate> getDebate() {
		return debate;
	}

	public void setDebate(Set<Debate> debate) {
		this.debate = debate;
	}
	
	
	
	
	
	
	
	
	
}
