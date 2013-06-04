package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import com.itgrids.partyanalyst.model.BaseModel;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="publication_date")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PublicationDate implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 
	 private Long publicationDateId;
	 private Date date;
	 private Integer month;
	 private Integer year;
	 private String comments;
	 private Set<Booth> booths = new HashSet<Booth>(0);
	/* private Set<VoterInfo> voterInfos = new HashSet<VoterInfo>(0);
	 private Set<VoterAgeInfo> voterAgeInfos = new HashSet<VoterAgeInfo>(0);
	 private Set<VoterFamilyInfo> voterFamilyInfos = new HashSet<VoterFamilyInfo>(0);
	 private Set<PublicationElection> publicationElections = new HashSet<PublicationElection>(0);*/
	 private String name;
	 
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name="publication_date_id", unique=true, nullable=false)
	 public Long getPublicationDateId() {
		return publicationDateId;
	 }
		public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	 }
		
		
	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	@Column(name="month" ,length = 2)
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	@Column(name="year" ,length = 2)
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	@Column(name="comments" ,length = 500)
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publicationDate")
	public Set<Booth> getBooths() {
		return booths;
	}
	public void setBooths(Set<Booth> booths) {
		this.booths = booths;
	}
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publicationDate")
	public Set<VoterInfo> getVoterInfos() {
		return voterInfos;
	}
	public void setVoterInfos(Set<VoterInfo> voterInfos) {
		this.voterInfos = voterInfos;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publicationDate")
	public Set<VoterAgeInfo> getVoterAgeInfos() {
		return voterAgeInfos;
	}
	public void setVoterAgeInfos(Set<VoterAgeInfo> voterAgeInfos) {
		this.voterAgeInfos = voterAgeInfos;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publicationDate")
	public Set<VoterFamilyInfo> getVoterFamilyInfos() {
		return voterFamilyInfos;
	}
	public void setVoterFamilyInfos(Set<VoterFamilyInfo> voterFamilyInfos) {
		this.voterFamilyInfos = voterFamilyInfos;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publicationDate")
	public Set<PublicationElection> getPublicationElections() {
		return publicationElections;
	}
	public void setPublicationElections(
			Set<PublicationElection> publicationElections) {
		this.publicationElections = publicationElections;
	}*/
	
	@Column(name = "name", length=25)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
