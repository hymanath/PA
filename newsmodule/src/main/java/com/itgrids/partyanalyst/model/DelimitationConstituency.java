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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * 
 * @author Narender
 *
 */
@Entity
@Table(name = "delimitation_constituency")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DelimitationConstituency extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long delimitationConstituencyID;
	private Constituency constituency;
	private Long year;
	//private Long constituencyNO;
	//private DelimitationYear delimitationYear;
	//private CensusYear censusYear;
	//private Set<DelimitationConstituencyAssemblyDetails> delimitationConstituencyAssemblyDetailses = new HashSet<DelimitationConstituencyAssemblyDetails>(0);
	//private Set<DelimitationConstituencyTown> delimitationConstituencyTown = new HashSet<DelimitationConstituencyTown>(0);
	public DelimitationConstituency(){
		
	}
	
	public DelimitationConstituency(Long delimitationConstituencyID){
		this.delimitationConstituencyID = delimitationConstituencyID;
	}
	
	
	
	public DelimitationConstituency(
			Long delimitationConstituencyID,
			Constituency constituency,
			Long year,
			Long constituencyNO,
			Set<DelimitationConstituencyAssemblyDetails> delimitationConstituencyAssemblyDetailses) {
		this.delimitationConstituencyID = delimitationConstituencyID;
		this.constituency = constituency;
		this.year = year;
		//this.constituencyNO = constituencyNO;
		//this.delimitationConstituencyAssemblyDetailses = delimitationConstituencyAssemblyDetailses;
	}

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
	/* @Column(name = "constituency_no", length = 15)
	public Long getConstituencyNO() {
		return constituencyNO;
	}
	public void setConstituencyNO(Long constituencyNO) {
		this.constituencyNO = constituencyNO;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "delimitationConstituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DelimitationConstituencyAssemblyDetails> getDelimitationConstituencyAssemblyDetailses() {
		return delimitationConstituencyAssemblyDetailses;
	}

	public void setDelimitationConstituencyAssemblyDetailses(
			Set<DelimitationConstituencyAssemblyDetails> delimitationConstituencyAssemblyDetailses) {
		this.delimitationConstituencyAssemblyDetailses = delimitationConstituencyAssemblyDetailses;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="delimitation_year_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DelimitationYear getDelimitationYear() {
		return delimitationYear;
	}

	public void setDelimitationYear(DelimitationYear delimitationYear) {
		this.delimitationYear = delimitationYear;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="census_year_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CensusYear getCensusYear() {
		return censusYear;
	}

	public void setCensusYear(CensusYear censusYear) {
		this.censusYear = censusYear;
	}*/

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "delimitationConstituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DelimitationConstituencyTown> getDelimitationConstituencyTown() {
		return delimitationConstituencyTown;
	}

	public void setDelimitationConstituencyTown(
			Set<DelimitationConstituencyTown> delimitationConstituencyTown) {
		this.delimitationConstituencyTown = delimitationConstituencyTown;
	}*/
	
}
