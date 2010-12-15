/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 13, 2010
 */
package com.itgrids.partyanalyst.model;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * delimitation_block entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="delimitation_block")
public class DelimitationBlock extends BaseModel implements java.io.Serializable{
	
	private static final long serialVersionUID = 5338675105287704002L;
	private Long delimitationBlockId;
	private DelimitationWard delimitationWard;
	private Block block;
	private Long isPartial;
	private String description;
	private Date updatedDate;
	
	public DelimitationBlock(){
	}
	
	public DelimitationBlock(DelimitationWard delimitationWard,Block block,Long isPartial,String description,Date updatedDate){
		
		this.delimitationWard = delimitationWard;
		this.block = block;
		this.isPartial = isPartial;
		this.description = description;
		this.updatedDate = updatedDate;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="delimitation_block_id", unique=true, nullable=false)
	public Long getDelimitationBlockId() {
		return delimitationBlockId;
	}
	public void setDelimitationBlockId(Long delimitationBlockId) {
		this.delimitationBlockId = delimitationBlockId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="delimitation_ward_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DelimitationWard getDelimitationWard() {
		return delimitationWard;
	}
	public void setDelimitationWard(DelimitationWard delimitationWard) {
		this.delimitationWard = delimitationWard;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="block_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Block getBlock() {
		return block;
	}
	public void setBlock(Block block) {
		this.block = block;
	}
	
	@Column(name="is_partial",length=10)
	public Long getIsPartial() {
		return isPartial;
	}
	public void setIsPartial(Long isPartial) {
		this.isPartial = isPartial;
	}
	
	@Column(name="description",length=100)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date", length = 10)
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
