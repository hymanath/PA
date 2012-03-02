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


@Entity
@Table(name= "position_scope")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PositionScope extends BaseModel implements Serializable {

	private static final long serialVersionUID = 6248515321731193028L;
	private Long positionScopeId;
	private ElectionScope electionScope;
	private ElectionGoverningBodyPosition electionGoverningBodyPosition;
	private Set<ElectionGoverningBody> electionGoverningBody = new HashSet<ElectionGoverningBody>(0);
	private MinisterType ministerType;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="position_scope_id", unique = true, nullable = false)
	public Long getPositionScopeId() {
		return positionScopeId;
	}
	public void setPositionScopeId(Long positionScopeId) {
		this.positionScopeId = positionScopeId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "election_scope_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ElectionScope getElectionScope() {
		return electionScope;
	}
	public void setElectionScope(ElectionScope electionScope) {
		this.electionScope = electionScope;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "election_governing_body_position_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ElectionGoverningBodyPosition getElectionGoverningBodyPosition() {
		return electionGoverningBodyPosition;
	}
	public void setElectionGoverningBodyPosition(
			ElectionGoverningBodyPosition electionGoverningBodyPosition) {
		this.electionGoverningBodyPosition = electionGoverningBodyPosition;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "electionGoverningBodyPosition")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ElectionGoverningBody> getElectionGoverningBody() {
		return electionGoverningBody;
	}
	public void setElectionGoverningBody(
			Set<ElectionGoverningBody> electionGoverningBody) {
		this.electionGoverningBody = electionGoverningBody;
	}
	
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name = "minister_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MinisterType getMinisterType() {
		return ministerType;
	}
	public void setMinisterType(MinisterType ministerType) {
		this.ministerType = ministerType;
	}
	
	
}
