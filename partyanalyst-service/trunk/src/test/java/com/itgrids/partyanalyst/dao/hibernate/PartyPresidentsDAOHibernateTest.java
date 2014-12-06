package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyPresidentsDAO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyPresidentsDAOHibernateTest  extends BaseDaoTestCase{

	private IPartyPresidentsDAO partyPresidentsDAO;

	public void setPartyPresidentsDAO(IPartyPresidentsDAO partyPresidentsDAO) {
		this.partyPresidentsDAO = partyPresidentsDAO;
	}
	
	public void testDetails()
	{
		try {
			List<Long> mobileNumbers = partyPresidentsDAO.getMobileNumebrsBylocation(65L, 0L, IConstants.CONSTITUENCY);
			
			if(mobileNumbers != null && mobileNumbers.size()>0)
			{
				String phoneNumbersStr = "";
				for (int i = 0; i < mobileNumbers.size(); i++) {
					phoneNumbersStr = phoneNumbersStr + ","+mobileNumbers.get(i).toString();
				}
				String[] phoneNumbersStr1 = phoneNumbersStr.split(",");
				System.out.println(phoneNumbersStr1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
