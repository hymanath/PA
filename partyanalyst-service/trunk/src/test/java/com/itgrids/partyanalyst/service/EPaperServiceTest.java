/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 20,2010
 */
package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;


import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEPaperDAO;
import com.itgrids.partyanalyst.dao.IEPaperUrlDAO;
import com.itgrids.partyanalyst.dto.EPaperVO;
import com.itgrids.partyanalyst.model.EPaper;
import com.itgrids.partyanalyst.model.EPaperUrl;
import com.itgrids.partyanalyst.service.impl.EPaperService;

public class EPaperServiceTest {

	private IEPaperDAO epaperDAO;
	private IConstituencyDAO constituencyDAO;
	private IDistrictDAO districtDAO;
	private IEPaperUrlDAO epaperUrlDAO;
		
	@Before
	public void init(){
		epaperDAO = EasyMock.createMock(IEPaperDAO.class);
		constituencyDAO = EasyMock.createMock(IConstituencyDAO.class);
		districtDAO =  EasyMock.createMock(IDistrictDAO.class);
		epaperUrlDAO =  EasyMock.createMock(IEPaperUrlDAO.class);
	}
	
	@Test
	public void testGetEPapersForDistrict(){
		EPaperService service = new EPaperService();
		List<Long> district = new ArrayList<Long>();			
		List<EPaper> url = new ArrayList<EPaper>();	
		List<EPaperUrl> epaperUrl = new ArrayList<EPaperUrl>();	
		List districtName = new ArrayList();
		List<Long> stateId = new ArrayList<Long>();
		Long obj1 = 19L;
		
		Object[] set1 = new Object[7];
		Object[] set2 = new Object[7];
		Object[] set3 = new Object[2];
		
		EPaper epaper = new EPaper();
		epaper.setClassification("np");
		epaper.setName("Hindu");
		epaper.setStateUrl("http://www.hindu.com/");
		epaper.setCountryUrl("www.hindu.com");
		epaper.setLanguage("English");
		epaper.setImage("hindulogo.jpg");
		url.add(epaper);
		
		EPaperUrl epaperur = new EPaperUrl();
		epaperur.setDistrictUrl("www.sakshi.com/adilabad");
		epaperur.setEpaper(epaper);	
		
		EPaperUrl epaperurl = new EPaperUrl();
		epaperurl.setDistrictUrl("www.eenadu.com/adilabad");
		epaperurl.setEpaper(epaper);
		
		epaperUrl.add(epaperurl);
		epaperUrl.add(epaperur);
		
		
		set3[0] = "adilabad";
		set3[1] = new Long(1);
	
		
		district.add(obj1);		
		districtName.add(set3);

		
		EasyMock.expect(districtDAO.getDistrictNameByDistrictId(new Long(19))).andReturn(districtName);
		EasyMock.expect(epaperDAO.findByStateId(new Long(1))).andReturn(url);
		EasyMock.expect(epaperUrlDAO.findEPapersForDistrictByDistrictId(new Long(19))).andReturn(epaperUrl);

		
		EasyMock.replay(districtDAO);
		service.setDistrictDAO(districtDAO);
		
		EasyMock.replay(epaperDAO);
		service.setEpaperDAO(epaperDAO);
		
		EasyMock.replay(epaperUrlDAO);
		service.setEpaperUrlDAO(epaperUrlDAO);	
		
		List<EPaperVO> EPaperVO = service.getEPapersForDistrict(new Long(19));	
		Assert.assertEquals(3, EPaperVO.size());
	}
		

}
