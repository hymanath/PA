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
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
/**
 * ministry_scope entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="ministry_scope")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MinistryScope extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = -7050307384697047590L;
	
	private Long ministriScopeId;
	private Ministry ministry;
	private ElectionScope electionScope;
	private String type;
	private Set<ElectionMinisters> electionMinisters = new HashSet<ElectionMinisters>(0);
	
	public MinistryScope()
	{}
	
	public MinistryScope(Ministry ministry,ElectionScope electionScope,String type)
	{
		this.ministry = ministry;
		this.electionScope = electionScope;
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ministry_scope_id", unique=true, nullable=false)
	public Long getMinistriScopeId() {
		return ministriScopeId;
	}

	public void setMinistriScopeId(Long ministriScopeId) {
		this.ministriScopeId = ministriScopeId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ministry_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Ministry getMinistry() {
		return ministry;
	}

	public void setMinistry(Ministry ministry) {
		this.ministry = ministry;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="election_scope_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ElectionScope getElectionScope() {
		return electionScope;
	}

	public void setElectionScope(ElectionScope electionScope) {
		this.electionScope = electionScope;
	}

	@Column(name="type",length=10)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ministryScope")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ElectionMinisters> getElectionMinisters() {
		return electionMinisters;
	}

	public void setElectionMinisters(Set<ElectionMinisters> electionMinisters) {
		this.electionMinisters = electionMinisters;
	}
	

}
