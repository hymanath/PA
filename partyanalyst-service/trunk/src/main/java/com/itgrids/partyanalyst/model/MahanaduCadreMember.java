package com.itgrids.partyanalyst.model;

import java.util.Date;

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
@Table(name = "mahanadu_cadre_member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MahanaduCadreMember implements java.io.Serializable{

	private Long mahanaduCadreMemberId;
	private TdpCadre tdpCadre;
	private Long tdpCadreId;
	private MahanaduGroup mahanaduGroup;
	private Long mahanaduGroupId;
	private Date  insertedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mahanadu_cadre_member_id", unique = true, nullable = false)
	public Long getMahanaduCadreMemberId() {
		return mahanaduCadreMemberId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tdp_cadre_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	
	public void setMahanaduCadreMemberId(Long mahanaduCadreMemberId) {
		this.mahanaduCadreMemberId = mahanaduCadreMemberId;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mahanadu_group_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MahanaduGroup getMahanaduGroup() {
		return mahanaduGroup;
	}

	public void setMahanaduGroup(MahanaduGroup mahanaduGroup) {
		this.mahanaduGroup = mahanaduGroup;
	}

	@Column(name="mahanadu_group_id")
	public Long getMahanaduGroupId() {
		return mahanaduGroupId;
	}

	public void setMahanaduGroupId(Long mahanaduGroupId) {
		this.mahanaduGroupId = mahanaduGroupId;
	}
}
