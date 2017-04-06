package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerNewDAO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.service.IAlertManagementSystemService;

public class AlertManagementSystemService implements IAlertManagementSystemService{
	
	private static final Logger LOG = Logger.getLogger(AlertManagementSystemService.class);

	private IAlertAssignedOfficerNewDAO alertAssignedOfficerNewDAO; 
	
	public List<IdNameVO> getDepartmentStatus(){	
		
		List<IdNameVO> finalList = new ArrayList<IdNameVO>(); 
		
		try {
			
			IdNameVO VO = new IdNameVO();
			VO.setId(1l);
			VO.setName("Pending");
			finalList.add(VO);
			
			List<Object[]> statusList = alertAssignedOfficerNewDAO.getAssignedStatuses();
			
			if(statusList !=null && statusList.size()>0){
				for (Object[] param : statusList) {
					
					IdNameVO vo = new IdNameVO();
					if(param[0] !=null){
						vo.setId((Long)param[0]);
						vo.setName(param[1] !=null  ? param[1].toString():"");
						finalList.add(vo);
					}	
				}
			}
			
			
		} catch (Exception e) {
			LOG.error("Error occured getDepartmentStatus() method of AlertManagementSystemService{}");
		}
		return finalList;
		
	}
	
}
