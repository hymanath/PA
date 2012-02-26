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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.poi.hssf.record.formula.functions.True;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "minister_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MinisterType extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 77249446505788293L;
	private Long ministerTypeId;
	private String ministerType;
	private Long orderNo;
	private Set<PositionScope> positionScope = new HashSet<PositionScope>(0);
	
	
	/** default constructor */
	public MinisterType(){
		}
	/** full constructor */
	public MinisterType(String ministerType, Long orderNo){
		this.ministerType = ministerType;
		this.orderNo = orderNo;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="minister_type_id" , unique = true, nullable = false)
	public Long getMinisterTypeId() {
		return ministerTypeId;
	}
	public void setMinisterTypeId(Long ministerTypeId) {
		this.ministerTypeId = ministerTypeId;
	}
	@Column(name = "minister_type" , length = 100)
	public String getMinisterType() {
		return ministerType;
	}
	public void setMinisterType(String ministerType) {
		this.ministerType = ministerType;
	}
	
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "ministerType")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PositionScope> getPositionScope() {
		return positionScope;
	}
	
	public void setPositionScope(Set<PositionScope> positionScope) {
		this.positionScope = positionScope;
	}

}
