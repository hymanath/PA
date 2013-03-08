package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
@Table(name = "voter_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterStatus extends BaseModel implements Serializable{

	
	private static final long serialVersionUID = -3523388403235167569L;
	private Long voterStatusId;
	private String status;
	private Set<VoterModificationInfo> voterModificationInfos = new HashSet<VoterModificationInfo>();
	
	public VoterStatus(){}
	
	public VoterStatus(String status)
	{
		this.status = status;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_status_id", unique = true , nullable = false)
	public Long getVoterStatusId() {
		return voterStatusId;
	}
	public void setVoterStatusId(Long voterStatusId) {
		this.voterStatusId = voterStatusId;
	}
	@Column(name = "status" , length = 25)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voterStatus")
	public Set<VoterModificationInfo> getVoterModificationInfos() {
		return voterModificationInfos;
	}

	public void setVoterModificationInfos(
			Set<VoterModificationInfo> voterModificationInfos) {
		this.voterModificationInfos = voterModificationInfos;
	}
	
	
}
