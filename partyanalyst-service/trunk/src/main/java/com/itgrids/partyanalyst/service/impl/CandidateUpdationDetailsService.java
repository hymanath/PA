package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.service.ICandidateUpdationDetailsService;

public class CandidateUpdationDetailsService implements ICandidateUpdationDetailsService{

private static final Logger LOG = Logger.getLogger(CandidateUpdationDetailsService.class);

@Autowired
private INominationDAO nominationDAO;

private IElectionDAO electionDAO;
private IDistrictDAO districtDAO;


public IDistrictDAO getDistrictDAO() {
return districtDAO;
}

public void setDistrictDAO(IDistrictDAO districtDAO) {
this.districtDAO = districtDAO;
}

public IElectionDAO getElectionDAO() {
return electionDAO;
}

public void setElectionDAO(IElectionDAO electionDAO) {
this.electionDAO = electionDAO;
}


public CandidateVO getCandidateDetailsForElection(Long electionId,Long districtId)
{
CandidateVO resultVo = new CandidateVO();




return resultVo;
}

public Object gettingElectionYears(Long electionTypeId)
{

LOG.debug("Entered in to gettingElectionYears() method in CandidateUpdationDetailsService class");
List<GenericVO> genericVOList=new ArrayList<GenericVO>();
try{
List list=electionDAO.findElectionYearsForElectionTypeAndState(electionTypeId,1L);


if(list!=null && list.size()>0)
{

for(Object obj:list)
{
Object[] objArray=(Object[])obj;
GenericVO VO=new GenericVO();
VO.setId((Long)objArray[0]);
VO.setName(objArray[1].toString());
genericVOList.add(VO);

}

}
}catch(Exception e)
{
LOG.error("Exception raised please check the log for details" + e);

}
return genericVOList;

}
public Object getAllDistrictsForAState(Long stateId)
{
LOG.debug("Entered in to getAllDistrictsForAState() method in CandidateUpdationDetailsService class");
List<GenericVO> genericVOList=new ArrayList<GenericVO>();
try{
List<District> list=districtDAO.findByStateId(stateId);

if(list!=null && list.size()>0)
{

for(District district:list)
{

GenericVO VO=new GenericVO();
VO.setId(district.getDistrictId());
VO.setName(district.getDistrictName());
genericVOList.add(VO);

}

}
}catch(Exception e)
{
LOG.error("Exception raised please check the log for details" + e);

}
return genericVOList;
}

}