package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserConnectedtoDAO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.CustomMessage;
import com.itgrids.partyanalyst.utils.IConstants;

public class CustomMessageDAOHibernateTest extends BaseDaoTestCase {

	private CustomMessageDAO customMessageDAO;
	private IUserConnectedtoDAO  userConnectedtoDAO;

	public IUserConnectedtoDAO getUserConnectedtoDAO() {
		return userConnectedtoDAO;
	}

	public void setUserConnectedtoDAO(IUserConnectedtoDAO userConnectedtoDAO) {
		this.userConnectedtoDAO = userConnectedtoDAO;
	}

	public CustomMessageDAO getCustomMessageDAO() {
		return customMessageDAO;
	}

	public void setCustomMessageDAO(CustomMessageDAO customMessageDAO) {
		this.customMessageDAO = customMessageDAO;
	}
	
	/*public void testUsersRelation(){	
		
		
		List<Long> usersList = new ArrayList<Long>();
		usersList.add(44l);
		usersList.add(43l);	
		usersList.add(42l);	
		usersList.add(41l);	
		usersList.add(40l);	
		usersList.add(39l);	
		usersList.add(26l);

		List<Long> list = new ArrayList<Long>();
		list.add(42l);
		
		DataTransferVO detailsList = getAllMessagesForUser(list,IConstants.COMMENTS);	
		System.out.println(detailsList);
		
	}
	public DataTransferVO getAllMessagesForUser(List<Long> userId,String messageType){
		ResultStatus resultStatus = new ResultStatus();
		List<CandidateVO> candiateVO = new ArrayList<CandidateVO>(0); 
		DataTransferVO dataTransferVO = new DataTransferVO();
		try{
			List<Object> result = customMessageDAO.getAllMessagesForUser(userId,messageType);
			if(result!=null && result.size()!=0){
				for(int i=0;i<result.size();i++){
					Object[] parms = (Object[])result.get(i);
					CandidateVO candidateResults = new CandidateVO();
					candidateResults.setData(parms[0].toString());
					candidateResults.setId(new Long(parms[1].toString()));
					String candidateName="";
					if(parms[2]!=null){
						candidateName+=parms[2].toString().concat("  ").concat("  ");
					}
					if(parms[3]!=null){
						candidateName+=parms[3].toString();
					}					
					candidateResults.setCandidateName(candidateName);
					candiateVO.add(candidateResults);
				}
			}
			dataTransferVO.setCandidateVO(candiateVO);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			resultStatus.setResultPartial(false);
			dataTransferVO.setResultStatus(resultStatus);
			}catch(Exception e){
				resultStatus.setExceptionEncountered(e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setResultPartial(true);
				dataTransferVO.setResultStatus(resultStatus);	
			}
		return dataTransferVO;		
	}	*/
	
	/*public void testGetPendingUsersCountForAUserInAFilterView()
	{
		List<Long> locationIds = new ArrayList<Long>(0);
		locationIds.add(323l);
		System.out.println(customMessageDAO.getPendingUsersCountForAUserInAFilterView(796l,locationIds,IConstants.CONSTITUENCY, "kamal"));
	}*/
	/*public void testGetPendingUsersInSelectedLocations(){
		List<Long> locationIds=new ArrayList<Long>(0);
		locationIds.add(3L);
		List<Object> pendingUsers=customMessageDAO.getPendingUsersInSelectedLocations(411L, locationIds, IConstants.DISTRICT_LEVEL, 20L, 0L, "");
		System.out.println(pendingUsers.size());
		
		for(Object params : pendingUsers)
		{
			Object[] obj = (Object[])params;
			System.out.println();
			for(Object o : obj)
				System.out.print("\t"+o.toString());
		}		
	}*/
	
	/*public void testGetPendingUserIdsInSelectedLocations()
	{
		List<Long> locationIds=new ArrayList<Long>(0);
		locationIds.add(3L);
		List<Long> pendingUsers = customMessageDAO.getPendingUserIdsInSelectedLocations(411l, locationIds, IConstants.DISTRICT_LEVEL);
		
		System.out.println(pendingUsers.size());
		
		for(Long pendingId: pendingUsers)
		{
			System.out.println(pendingId.toString());
		}
	}*/
	
	
	
	/*public void testgetUserStatus()
	{
		List<Object[]> list = customMessageDAO.getUserStatus(1l, 807l);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]);
		}
	}
	
	public void testgetUserConnectStatus()
	{
		List<Object[]> list = customMessageDAO.getUserConnectStatus(1l, 807l);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]);
		}
	}*/
	
	public void test()
	{
		List<Long> senderId =new ArrayList<Long>();
		senderId.add(611l);
		String messageType = IConstants.COMMENTS;
		List<Object> list =customMessageDAO.getAllSentMessagesForUser(senderId,messageType);
		System.out.println(list.size());
	}
	
	
}
