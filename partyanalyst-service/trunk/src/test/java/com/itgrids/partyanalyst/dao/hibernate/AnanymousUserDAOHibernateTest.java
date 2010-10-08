package com.itgrids.partyanalyst.dao.hibernate;


import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.utils.IConstants;


public class AnanymousUserDAOHibernateTest extends BaseDaoTestCase {
	
	private IAnanymousUserDAO ananymousUserDAO;
	
	public IAnanymousUserDAO getAnanymousUserDAO() {
		return ananymousUserDAO;
	}

	public void setAnanymousUserDAO(IAnanymousUserDAO ananymousUserDAO) {
		this.ananymousUserDAO = ananymousUserDAO;
	}

	/*public void testAnonymousUserLogin(){		
		List<AnanymousUser> detailsList = ananymousUserDAO.checkAnonymousUserLogin("ravi","kiran");	
		assertEquals(detailsList.size(), 1);
	}*/

	/*public void testAvailabityOfUserNameForAnonymousUser(){		
		List<AnanymousUser> detailsList = ananymousUserDAO.checkForUserNameAvailabiity("ravi");	
		assertEquals(detailsList.size(), 1);
	}*/
	
	public void testUserDataRetrivalByLocation(){		
		List details = new ArrayList();
		details.add(19l);
		DataTransferVO detailsList = getAllRegisteredAnonymousUserBasedOnLocation(details,IConstants.DISTRICT_LEVEL);	
		System.out.println(detailsList.getCandidateVO().size());
	}
	
	public DataTransferVO getAllRegisteredAnonymousUserBasedOnLocation(List<Long> locationIds,String locationType){
		ResultStatus resultStatus = new ResultStatus();
		DataTransferVO dataTransferVO = new DataTransferVO();;
		List<CandidateVO> candidateDetails = new ArrayList<CandidateVO>();
		List<Object> result = new ArrayList<Object>();
		try{
			result = ananymousUserDAO.getAllUsersInSelectedLocations(locationIds, locationType);			
			if(result!=null && result.size()!=0){
				for(int i=0;i<result.size();i++){
					Object[] parms = (Object[])result.get(i);
					CandidateVO candidateVO = new CandidateVO();
					String lastName="";
					if(parms[1]!=null){
						lastName = parms[1].toString();
					}
					candidateVO.setCandidateName(parms[0].toString().concat(" ").concat(lastName));
					candidateVO.setId(new Long(parms[2].toString()));
					candidateDetails.add(candidateVO);
				}
			}			
			dataTransferVO.setCandidateVO(candidateDetails);
			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			dataTransferVO.setResultStatus(resultStatus);		
			System.out.println(dataTransferVO.getCandidateVO().size());
		}catch(Exception e){
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			dataTransferVO.setResultStatus(resultStatus);	
		}
	return dataTransferVO;
	} 
}
