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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "locality")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Locality implements java.io.Serializable{
	
private long LocalityId;

private String name;

private Hamlet hamlet;	

private LocalElectionBody localElectionBody;

private User user;

private String isGlobal;

private Set<UserVoterDetails> uservoterdetails = new HashSet<UserVoterDetails>(0);

//Default Constructor
public Locality()
{
	
}
//Full Constructor
public Locality(Long LocalityId,String name,Hamlet hamlet,LocalElectionBody localElectionBody,User user,String isGlobal)
{
this.LocalityId = LocalityId;
this.name = name;
this.hamlet = hamlet;
this.localElectionBody = localElectionBody;
this.user = user;
this.isGlobal = isGlobal;
}
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "locality_id", unique = true, nullable = false)
public long getLocalityId() {
	return LocalityId;
}
public void setLocalityId(long localityId) {
	LocalityId = localityId;
}
@Column(name="name",length=150)
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name = "hamlet_id")
public Hamlet getHamlet() {
	return hamlet;
}
public void setHamlet(Hamlet hamlet) {
	this.hamlet = hamlet;
}
@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name = "local_election_body_id")
public LocalElectionBody getLocalElectionBody() {
	return localElectionBody;
}
public void setLocalElectionBody(LocalElectionBody localElectionBody) {
	this.localElectionBody = localElectionBody;
}
@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name = "user_id")
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
@Column(name="is_global",length=10)
public String getIsGlobal() {
	return isGlobal;
}
public void setIsGlobal(String isGlobal) {
	this.isGlobal = isGlobal;
}
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "locality")
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
public Set<UserVoterDetails> getUservoterdetails() {
	return uservoterdetails;
}
public void setUservoterdetails(Set<UserVoterDetails> uservoterdetails) {
	this.uservoterdetails = uservoterdetails;
}

}
