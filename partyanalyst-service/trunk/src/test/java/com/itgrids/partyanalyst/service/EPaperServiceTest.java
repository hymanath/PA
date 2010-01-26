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
import com.itgrids.partyanalyst.dao.IEPaperDAO;
import com.itgrids.partyanalyst.service.impl.EPaperService;

public class EPaperServiceTest {

	private IEPaperDAO epaperDAO;
	private IConstituencyDAO constituencyDAO;
		
	@Before
	public void init(){
		epaperDAO = EasyMock.createMock(IEPaperDAO.class);
		constituencyDAO = EasyMock.createMock(IConstituencyDAO.class);
	}
	
	@Test
	public void testEpaper(){
		EPaperService service = new EPaperService();
		List<Long> district = new ArrayList<Long>();			
		List url = new ArrayList();	
		
		Long obj1 = 19L;
		
		Object[] set1 = new Object[5];
		Object[] set2 = new Object[5];
		
		
		set1[0] = "http://www.eenadu.net/district/districtshow1.asp?dis=adilabad";
		set1[1] = "http://www.eenadu.com/";
		set1[2] = "ADILABAD";
		set1[3] = "Telugu";
		set1[4] = "eenadu.jpg";
		
		set2[0] = "http://www.andhrajyothy.net/district/districtshow1.asp?dis=guntur";
		set2[1] = "http://www.andhrajyothy.com/";
		set2[2] = "guntur";
		set2[3] = "Telugu";
		set2[4] = "andhrajyothy.jpg";
		
		
		district.add(obj1); 		
		url.add(set1);
		url.add(set2);
		
		
		EasyMock.expect(constituencyDAO.getDistrictIdByConstituencyId(new Long(19))).andReturn(district);
		EasyMock.expect(epaperDAO.findEPapersForDistrictByDistrictId(new Long(19))).andReturn(url);
		EasyMock.replay(constituencyDAO);
		service.setConstituencyDAO(constituencyDAO);
		
		
		EasyMock.replay(epaperDAO);
		service.setEpaperDAO(epaperDAO);		
		
		List EPaperVO = service.getEPapers("mla", new Long(19));	
		Assert.assertEquals(2, EPaperVO.size());
	}
	
	@Test
	public void testGetEPapersForDistrict(){
		
		EPaperService service = new EPaperService();
		List<Long> district = new ArrayList<Long>();			
		List url = new ArrayList();	
		
		Long obj1 = 17L;
		
		Object[] set1 = new Object[5];
		Object[] set2 = new Object[5];
		
		
		set1[0] = "http://www.eenadu.net/district/districtshow1.asp?dis=adilabad";
		set1[1] = "http://www.eenadu.com/";
		set1[2] = "ADILABAD";
		set1[3] = "Telugu";
		set1[4] = "eenadu.jpg";
		
		set2[0] = "http://www.andhrajyothy.net/district/districtshow1.asp?dis=guntur";
		set2[1] = "http://www.andhrajyothy.com/";
		set2[2] = "guntur";
		set2[3] = "Telugu";
		set2[4] = "andhrajyothy.jpg";
		
		district.add(obj1); 		
		url.add(set1);
		url.add(set2);
			
		EasyMock.expect(epaperDAO.findEPapersForDistrictByDistrictId(new Long(17))).andReturn(url);
				
		EasyMock.replay(epaperDAO);
		service.setEpaperDAO(epaperDAO);		
		
		List EPaperVO = service.getEPapersForDistrict(new Long(17));	
		Assert.assertEquals(2, EPaperVO.size());

	}	
}
