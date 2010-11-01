package com.itgrids.partyanalyst.dao.hibernate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.ICustomMessageDAO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.CustomMessage;
import com.itgrids.partyanalyst.utils.IConstants;


public class AnanymousUserDAOHibernateTest extends BaseDaoTestCase {
	
	private IAnanymousUserDAO ananymousUserDAO;
	private ICustomMessageDAO customMessageDAO;
	
	
	public ICustomMessageDAO getCustomMessageDAO() {
		return customMessageDAO;
	}

	public void setCustomMessageDAO(ICustomMessageDAO customMessageDAO) {
		this.customMessageDAO = customMessageDAO;
	}

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
		List<Long> details =  new ArrayList<Long>(0);
		details.add(2l);
		List<AnanymousUser> detailsList = ananymousUserDAO.getDetailsForUsers(details);	
		for(AnanymousUser result : detailsList){
			System.out.println(result.getLastName());
		}
		System.out.println(detailsList.size());
	}*/
	
	public void testGetAllUsersBasedOnLocation(){
		List<Long> area =  new ArrayList<Long>(0);
		area.add(232l);
		List<Object> detailsList = ananymousUserDAO.getAllUsersInSelectedLocations(area,IConstants.CONSTITUENCY_LEVEL,IConstants.MAX_ANONYMOUS_USER_DISPLAY);
		List<CandidateVO> candidateDetails = setFriendsListForAUser(detailsList,39L,IConstants.NOTCONNECTED);
		System.out.println(candidateDetails);
	}
	
	public List<CandidateVO> setFriendsListForAUser(List result,Long loginId,String status){
		List<CandidateVO> candidateDetails = new ArrayList<CandidateVO>();
		List<Long> candidates = new ArrayList<Long>();
		Map<Long, CandidateVO> userIdAndRelationShipWithLogedUser = new HashMap<Long, CandidateVO>();
		DataTransferVO dataTransferVO = new DataTransferVO();
		ResultStatus resultStatus = new ResultStatus();
		try{
			if(result!=null && result.size()!=0){
				for(int i=0;i<result.size();i++){
					Object[] parms = (Object[])result.get(i);
					CandidateVO candidateVO = new CandidateVO();
					Long userId = new Long(parms[2].toString());
					String lastName="";
					if(parms[1]!=null){
						lastName = parms[1].toString();
					}
					candidateVO.setCandidateName(parms[0].toString().concat(" ").concat(lastName));
					candidateVO.setId(userId);
					candidateVO.setStatus(IConstants.NOTCONNECTED);
					candidateVO.setConstituencyId(new Long(parms[4].toString()));
					candidateVO.setConstituencyName(parms[3].toString());
					if(loginId!=0){
						candidates.add(userId);
						userIdAndRelationShipWithLogedUser.put(userId,candidateVO);
					}else{
						candidateDetails.add(candidateVO);
					}				
				}
				if(loginId!=0){					
					List<Object> detailsList = customMessageDAO.getRelationShipBetweenTheUsers(candidates,loginId,status);	
					for(int i=0;i<detailsList.size();i++){
						Object[] parms = (Object[])detailsList.get(i);				
						Long userId = new Long(parms[0].toString());			
						if(userIdAndRelationShipWithLogedUser.get(userId)!=null){
							userIdAndRelationShipWithLogedUser.get(userId).setStatus(parms[1].toString());	
						}
											
					}
					if(userIdAndRelationShipWithLogedUser.get(loginId)!=null){
						userIdAndRelationShipWithLogedUser.get(loginId).setStatus(IConstants.LOGGED_USER);
					}					
					for(Map.Entry<Long, CandidateVO> data : userIdAndRelationShipWithLogedUser.entrySet()){
						if(status.equalsIgnoreCase(IConstants.ALL)){
							candidateDetails.add(data.getValue());
						}else{
							if(status.equalsIgnoreCase(data.getValue().getStatus())){
								candidateDetails.add(data.getValue());
							}
						}
																		
					}
				}
				dataTransferVO.setCandidateVO(candidateDetails);
				resultStatus.setResultPartial(false);
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				dataTransferVO.setResultStatus(resultStatus);	
			}else{				
				resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
				resultStatus.setResultPartial(true);
				dataTransferVO.setResultStatus(resultStatus);
			}
		}catch(Exception e){
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			dataTransferVO.setResultStatus(resultStatus);
		}
		return candidateDetails;
	}
}
