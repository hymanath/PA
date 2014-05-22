package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "consti_caste_group_perc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ConstiCasteGroupPerc extends BaseModel implements Serializable {
	
private Long constiCasteGroupPercId;
private CasteGroup casteGroup;
//private Constituency constituency;
private Long constituency;
private Double groupPerc;
private String considerCaste;
private Long rank;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "consti_caste_group_perc_id", unique = true, nullable = false)
public Long getConstiCasteGroupPercId() {
	return constiCasteGroupPercId;
}
public void setConstiCasteGroupPercId(Long constiCasteGroupPercId) {
	this.constiCasteGroupPercId = constiCasteGroupPercId;
}
/*@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "caste_group_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)

@Column(name = "caste_group_id")*/

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "caste_group_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)

public CasteGroup getCasteGroup() {
	return casteGroup;
}
public void setCasteGroup(CasteGroup casteGroup) {
	this.casteGroup = casteGroup;
}

/*@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "constituency_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)

public Constituency getConstituency() {
	return constituency;
}
public void setConstituency(Constituency constituency) {
	this.constituency = constituency;
}*/
@Column(name = "group_perc")
public Double getGroupPerc() {
	return groupPerc;
}

public void setGroupPerc(Double groupPerc) {
	this.groupPerc = groupPerc;
}

@Column(name = "consider_caste")
public String getConsiderCaste() {
	return considerCaste;
}
public void setConsiderCaste(String considerCaste) {
	this.considerCaste = considerCaste;
}
@Column(name = "rank")
public Long getRank() {
	return rank;
}
public void setRank(Long rank) {
	this.rank = rank;
}

/*@Column(name = "caste_group_id")
public Long getCasteGroup() {
	return casteGroup;
}
public void setCasteGroup(Long casteGroup) {
	this.casteGroup = casteGroup;
}
*/
@Column(name = "constituency_id")
public Long getConstituency() {
	return constituency;
}
public void setConstituency(Long constituency) {
	this.constituency = constituency;
}



}
