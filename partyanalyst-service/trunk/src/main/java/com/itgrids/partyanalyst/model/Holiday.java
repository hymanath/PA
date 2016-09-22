package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name="holiday")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Holiday extends BaseModel implements Serializable {
	private Long holidayId;
	private Date date;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "holiday_id", unique = true, nullable = false)
	public Long getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(Long holidayId) {
		this.holidayId = holidayId;
	}
	@Column(name = "date")
	public Date getDate() {  
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
