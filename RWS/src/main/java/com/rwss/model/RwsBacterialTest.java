package com.rwss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aprwssuser.rws_min_bacterial_test_view")
public class RwsBacterialTest {

	private Long bacterial;
	private Long year;
	
	@Column(name="BACTERIAL")
	public Long getBacterial() {
		return bacterial;
	}
	public void setBacterial(Long bacterial) {
		this.bacterial = bacterial;
	}
	@Id
	@Column(name="YEAR")
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
}
