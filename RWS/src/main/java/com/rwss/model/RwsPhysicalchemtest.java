package com.rwss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aprwssuser.rws_min_phy_chem_test_view")
public class RwsPhysicalchemtest {
	
	private Long phyChemTest;
	private Long year;

	@Column(name="PHY_CHEM_TEST")
	public Long getPhyChemTest() {
		return phyChemTest;
	}
	public void setPhyChemTest(Long phyChemTest) {
		this.phyChemTest = phyChemTest;
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
