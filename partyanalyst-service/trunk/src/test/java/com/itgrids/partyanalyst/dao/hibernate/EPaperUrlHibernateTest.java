package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;


import com.itgrids.partyanalyst.dao.IEPaperUrlDAO;
import com.itgrids.partyanalyst.model.EPaperUrl;

public class EPaperUrlHibernateTest extends BaseDaoTestCase {
	
	private IEPaperUrlDAO epaperUrlDAO;

	public IEPaperUrlDAO getEpaperUrlDAO() {
		return epaperUrlDAO;
	}

	public void setEpaperUrlDAO(IEPaperUrlDAO epaperUrlDAO) {
		this.epaperUrlDAO = epaperUrlDAO;
	}

	
	@SuppressWarnings("unchecked")
	public void testFindEPapersForDistrictByDistrictId(){
		List<EPaperUrl> result = epaperUrlDAO.findEPapersForDistrictByDistrictId(1l);
		for(EPaperUrl parms :result){
			
			System.out.println("PaperName"+parms.getEpaper().getName().toString());
			System.out.println("DistrictUrl"+parms.getDistrictUrl().toString());
			System.out.println("StateUrl"+parms.getEpaper().getStateUrl().toString());
			System.out.println("Language"+parms.getEpaper().getLanguage());
			System.out.println("Image"+parms.getEpaper().getImage());
			
		
			
			System.out.println("paperName \t"+parms.getEpaper().getName().toString());
			System.out.println("district url \t"+parms.getDistrictUrl().toString());
			System.out.println("state url \t"+parms.getEpaper().getStateUrl().toString());
			System.out.println("National \t"+parms.getEpaper().getCountryUrl().toString());
			System.out.println("language \t"+parms.getEpaper().getLanguage().toString());
			System.out.println("Image \t"+parms.getEpaper().getImage().toString());
		}
		assertEquals(0,0);
	}
}
