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
@Table(name = "Castecontain_Constituency")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class CasteContainConstituency extends BaseModel implements Serializable{
private static final long serialVersionUID = 4117259734954203835L;

private Long casteContainConstituencyId;	


private Constituency constituency;

/** Default Constructor */

public CasteContainConstituency()
{
	
}

/** default constructor */
public CasteContainConstituency(Long casteContainConstituencyId, Constituency constituency) {
	
	this.casteContainConstituencyId = casteContainConstituencyId;
	
	this.constituency = constituency;
	}

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "castecontain_Constituency_id", unique = true, nullable = false)

public Long getCasteContainConstituencyId() {
	return casteContainConstituencyId;
}

public void setCasteContainConstituencyId(Long casteContainConstituencyId) {
	this.casteContainConstituencyId = casteContainConstituencyId;
}

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "constituency_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
public Constituency getConstituency() {
	return constituency;
}

public void setConstituency(Constituency constituency) {
	this.constituency = constituency;
}
}
