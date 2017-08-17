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
@Table(name = "debate_participant_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DebateParticipantLocation extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = 2770484105252236590L;			
	
//`debate_id`int, `start_time` TIMESTAMP ,`end_time` TIMESTAMP,`channel_id`,`telecast_type_id`,`is_deleted` ENUM,`summary` TEXT	
	
	//fields
	private Long debateParticipantLocationId;
	private Integer debateParticipantId;
	private DebateParticipant debateParticipant;
	private Long addressId;
	private UserAddress address;
	private String isDeleted;
	
	//setters and getters.
    @Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	@Column(name = "debate_participant_location_id", unique = true, nullable = false)
	public Long getDebateParticipantLocationId() {
		return debateParticipantLocationId;
	}
	public void setDebateParticipantLocationId(Long debateParticipantLocationId) {
		this.debateParticipantLocationId = debateParticipantLocationId;
	}
	@Column(name="debate_participant_id")
	public Integer getDebateParticipantId() {
		return debateParticipantId;
	}
	public void setDebateParticipantId(Integer debateParticipantId) {
		this.debateParticipantId = debateParticipantId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "debate_participant_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public DebateParticipant getDebateParticipant() {
		return debateParticipant;
	}
	public void setDebateParticipant(DebateParticipant debateParticipant) {
		this.debateParticipant = debateParticipant;
	}
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public UserAddress getAddress() {
		return address;
	}
	public void setAddress(UserAddress address) {
		this.address = address;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
