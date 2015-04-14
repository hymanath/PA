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
@Table(name = "mahanadu_public_representative")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MahanaduPublicRepresentative implements java.io.Serializable{

	private Long mahanaduPublicRepresentativeId;
	private PublicRepresentative publicRepresentative;
	private Long publicRepresentativeId;
	private MahanaduGroup mahanaduGroup;
	private Long mahanaduGroupId;
	private Date insertedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mahanadu_public_representative_id", unique = true, nullable = false)
	public Long getMahanaduPublicRepresentativeId() {
		return mahanaduPublicRepresentativeId;
	}
	public void setMahanaduPublicRepresentativeId(
			Long mahanaduPublicRepresentativeId) {
		this.mahanaduPublicRepresentativeId = mahanaduPublicRepresentativeId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "public_representative_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PublicRepresentative getPublicRepresentative() {
		return publicRepresentative;
	}
	public void setPublicRepresentative(PublicRepresentative publicRepresentative) {
		this.publicRepresentative = publicRepresentative;
	}
	
	@Column(name="public_representative_id")
	public Long getPublicRepresentativeId() {
		return publicRepresentativeId;
	}
	public void setPublicRepresentativeId(Long publicRepresentativeId) {
		this.publicRepresentativeId = publicRepresentativeId;
	}
	
	@Column(name="inserted_date")
	public Date getInsertedTime() {
		return insertedTime;
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
