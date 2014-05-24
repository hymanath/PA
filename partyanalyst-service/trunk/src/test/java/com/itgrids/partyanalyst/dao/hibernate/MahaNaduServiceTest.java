package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.service.impl.MahaNaduService;

public class MahaNaduServiceTest extends BaseDaoTestCase{
	private MahaNaduService mahaNaduService;
	
	
	
	public MahaNaduService getMahaNaduService() {
		return mahaNaduService;
	}



	public void setMahaNaduService(MahaNaduService mahaNaduService) {
		this.mahaNaduService = mahaNaduService;
	}

	public void testCadreSave(){
		
		//MahaNaduService mahaNaduService = new MahaNaduService();
		CadreVo cadreInfo = new CadreVo();
		
		List<Long> designationIds = new ArrayList<Long>();
		designationIds.add(1l);
		designationIds.add(2l);
		List<Long> govtIds = new ArrayList<Long>();
		govtIds.add(1l);
		govtIds.add(2l);
		
		cadreInfo.setFirstName("fname");
		cadreInfo.setLastName("lname");
		cadreInfo.setAge(23l);
		cadreInfo.setBloodGroupId(1l);
		cadreInfo.setGender("M");
		cadreInfo.setNoOfFamilyMembers("5");
		cadreInfo.setNoOfVoters("4");
		cadreInfo.setFatherName("father");
		cadreInfo.setMobileNo("9999999999");
		cadreInfo.setLandNo("0123456");
		cadreInfo.setEmailId("abc@gmail.com");
		cadreInfo.setHno("1-10");
		cadreInfo.setStreet("street");
		cadreInfo.setDistrictId(10l);
		cadreInfo.setConstituencyId(232l);
		cadreInfo.setMandalId(1l);
		cadreInfo.setVillageId(2l);
		cadreInfo.setPinCode("507210");
		cadreInfo.setBoothNo(201l);
		cadreInfo.setEducationId(2l);
		cadreInfo.setAnnualIncome("10000");
		cadreInfo.setSourceIncome("10000");
		cadreInfo.setProfessionId(1l);
		cadreInfo.setCasteCategory(1l);
		cadreInfo.setActiveDateField("02/05/2014");
		mahaNaduService.saveCadreInfoForMahaNadu(cadreInfo);
	}
	
}
