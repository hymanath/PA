package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="hh_options")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HHOptions extends BaseModel implements Serializable {
	
	private Long optionsId;
	private String options;
	//private String description;
	private Long orderId;
	private String isDelete;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "options_id", unique = true, nullable = false)
	public Long getOptionsId() {
		return optionsId;
	}
	public void setOptionsId(Long optionsId) {
		this.optionsId = optionsId;
	}
	
	@Column(name = "options", length = 500)
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	/*public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}*/
	
	@Column(name = "order_id", length = 100)
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	@Column(name="is_delete" , length = 5)
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
}
