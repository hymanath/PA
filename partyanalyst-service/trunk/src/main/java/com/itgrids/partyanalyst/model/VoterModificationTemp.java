package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * voter_modifications_temp entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="voter_modification_temp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterModificationTemp extends BaseModel implements Serializable{

	private static final long serialVersionUID = -2808744385401981543L;
	
	private Long id;
	private String voterId;
	private String status;
	private Long constituencyId;
	private String costituencyName;
	private Long partNo;
	
	public VoterModificationTemp()
	{}
	
	public VoterModificationTemp(String voterId,String status,Long constituencyId,
			String costituencyName,Long partNo)
	{
		this.voterId = voterId;
		this.status = status;
		this.constituencyId = constituencyId;
		this.costituencyName = costituencyName;
		this.partNo = partNo;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", unique=true, nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	@Column(name="voter_id",length=25)
	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	@Column(name="status",length=10)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	@Column(name="costituency_name",length=100)
	public String getCostituencyName() {
		return costituencyName;
	}

	public void setCostituencyName(String costituencyName) {
		this.costituencyName = costituencyName;
	}

	@Column(name="part_no")
	public Long getPartNo() {
		return partNo;
	}

	public void setPartNo(Long partNo) {
		this.partNo = partNo;
	}

}
