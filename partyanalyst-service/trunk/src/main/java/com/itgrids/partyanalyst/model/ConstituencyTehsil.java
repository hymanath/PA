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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "constituency_tehsil")
public class ConstituencyTehsil extends BaseModel{
	
	private Long constituencyTehsilID;
	private Constituency constituency;
	private Tehsil tehsil;
	private String completeTehsil;

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "constituency_tehsil_id", unique = true, nullable = false)
	public Long getConstituencyTehsilID() {
		return constituencyTehsilID;
	}
	public void setConstituencyTehsilID(Long constituencyTehsilID) {
		this.constituencyTehsilID = constituencyTehsilID;
	}
	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "constituency_id")
	 @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "tehsil_id")
	public Tehsil getTehsil() {
		return tehsil;
	}
	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}
	@Column(name = "complete_tehsil", length = 5)
	public String getCompleteTehsil() {
		return completeTehsil;
	}
	public void setCompleteTehsil(String completeTehsil) {
		this.completeTehsil = completeTehsil;
	}
}
