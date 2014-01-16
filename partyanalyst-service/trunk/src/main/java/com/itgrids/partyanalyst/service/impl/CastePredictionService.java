package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.ICastePredictionService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CastePredictionService implements ICastePredictionService {
	private static final Logger log = Logger.getLogger(CastePredictionService.class);
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}
	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}
	
	
	public List<VoterVO> getCountForCaste(String userType)
	{
		
		List<VoterVO>  result = new ArrayList<VoterVO>();
		
		try{
			if(userType.equalsIgnoreCase("Admin"))
			{
			List<Object[]> list = userVoterDetailsDAO.getCountForCasteType();
			if(list !=null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					VoterVO voterVO = new VoterVO();
					voterVO.setTotalVoters((Long)params[0]);
					voterVO.setType(params[1].toString());
					result.add(voterVO);
					
				}
			}
			}
		}
		catch(Exception e)
		{
			log.error("Exception Occured in getCountForCaste () method in CastePredictionService",e) ;
		}
		return result;
	}

}
