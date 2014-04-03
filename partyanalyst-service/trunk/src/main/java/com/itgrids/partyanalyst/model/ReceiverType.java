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
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "receiver_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReceiverType extends BaseModel implements java.io.Serializable  {

	private static final long serialVersionUID = 2770484105252236590L;
	
	// Fields
	
	private Long receiverTypeId;
	private String type;
	
	private Set<UserSmsReceiver> userSmsReceiver = new HashSet<UserSmsReceiver>(0);
	public ReceiverType()
	{
		
	}
	public ReceiverType(Long receiverTypeId,String type) {
		this.receiverTypeId = receiverTypeId;
		this.type = type;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "receiver_type_id", unique = true, nullable = false)
	public Long getReceiverTypeId() {
		return receiverTypeId;
	}
	public void setReceiverTypeId(Long receiverTypeId) {
		this.receiverTypeId = receiverTypeId;
	}
	
	@Column(name="type" , length = 45)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "receiverType")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserSmsReceiver> getUserSmsReceiver() {
		return userSmsReceiver;
	}
	public void setUserSmsReceiver(Set<UserSmsReceiver> userSmsReceiver) {
		this.userSmsReceiver = userSmsReceiver;
	}
}
