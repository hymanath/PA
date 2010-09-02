package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.columns.enums.CadreColumnNames;
import com.itgrids.partyanalyst.dao.columns.enums.EducationQualificationColumnNames;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreDAOHibernateTest extends BaseDaoTestCase {
	ICadreDAO cadreDAO;
	
		
	public ICadreDAO getCadreDAO() {
		return cadreDAO;
	}

	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}

	/*
	public void testFindCadresByLevels()
	{
		List result = cadreDAO.findCadresByLevels(7l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.println(parms[1].toString());
			
		}
		
	}*/
	
	@SuppressWarnings("unchecked")
	public void testFindCadreSearchCriteria(){
		//List result = cadreDAO.findCadreDetailsByLevelAndProperty(new Long(7), "currentAddress", "district", "districtId", new Long(1));
		List<Long> cadreIds = new ArrayList<Long>();
		cadreIds.add(new Long(1));
		cadreIds.add(new Long(2));
		
		List<Long> propertyIds = new ArrayList<Long>();
		propertyIds.add(new Long(1));
		propertyIds.add(new Long(2));
		
		//List result = cadreDAO.findCadreIdsByMemberTypeAndCadreList("Normal", cadreIds);
		//List result = cadreDAO.findCadreByPropertyValueAndCadreIds("education", "eduQualificationId", new Long(6), cadreIds);
		//List result = cadreDAO.findCadreByPropertyValueAndUser(new Long(7),"education", "eduQualificationId", new Long(7));
		//List result = cadreDAO.findCadreByPartyWorkingCommitteeAndCadreIds(new Long(1), cadreIds);
		//List result = cadreDAO.findCadreByPartyWorkingCommitteeAndUser(new Long(7), new Long(1));
		//List result = cadreDAO.findCadreByUserAndCadreLevel(new Long(7), new Long(2));
		
		//List result = cadreDAO.findCadreByPropertyValueListAndUser(new Long(7),"education", "eduQualificationId", propertyIds);
		
		//List result = cadreDAO.findCadreByPropertyValueListAndCadreIds("education", "eduQualificationId", propertyIds, cadreIds);
		
		//List result = cadreDAO.findCadreByPartyWorkingCommitteeListAndUser(new Long(7), propertyIds);
		
		//List result = cadreDAO.findCadreByPartyWorkingCommitteeListAndCadreIds(propertyIds, cadreIds);
		
		//List result = cadreDAO.findCadreByPartyWorkingCommitteeDesignationListAndUser(new Long(7), propertyIds);
		
		List result = cadreDAO.findCadreByPartyWorkingCommitteeDesignationListAndCadreIds(propertyIds, cadreIds);
		
		System.out.println(" Result Size :" + result.size());
	}
}
