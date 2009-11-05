package com.itgrids.partyanalyst.model;

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

import org.hibernate.annotations.NotFoundAction;

/**
 * 
 * @author Narender
 *
 */
@Entity
@Table(name = "delimitation_constituency")
public class DelimitationConstituency extends BaseModel {
	private Long delimitationConstituencyID;
	private Constituency constituency;
	private Long year;
	private Long constituencyNO;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "delimitation_constituency_id", unique = true, nullable = false)
	public Long getDelimitationConstituencyID() {
		return delimitationConstituencyID;
	}
	public void setDelimitationConstituencyID(Long delimitationConstituencyID) {
		this.delimitationConstituencyID = delimitationConstituencyID;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	@Column(name = "year", length = 15)
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	 @Column(name = "constutuency_no", length = 15)
	public Long getConstituencyNO() {
		return constituencyNO;
	}
	public void setConstituencyNO(Long constituencyNO) {
		this.constituencyNO = constituencyNO;
	}
	
}
