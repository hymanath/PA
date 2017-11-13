package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeVO;



public interface IJanmabhoomiCommitteeService {
public List<JanmabhoomiCommitteeVO> getDistrictWiseCommitteeDetails(String fromDate,String endDate,String type);	
public  JanmabhoomiCommitteeVO  getJbCommitteeStatusCount(String fromDateStr, String toDateStr);	
}
