package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICandidateDetailsDAO;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.CandidateDetails;
import com.itgrids.partyanalyst.model.Caste;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.EducationalQualifications;
import com.itgrids.partyanalyst.service.ICandidateUpdationDetailsService;

public class CandidateUpdationDetailsService implements ICandidateUpdationDetailsService{

private static final Logger LOG = Logger.getLogger(CandidateUpdationDetailsService.class);

@Autowired
private INominationDAO nominationDAO;

private IElectionDAO electionDAO;
private IDistrictDAO districtDAO;
private TransactionTemplate transactionTemplate;
private ICandidateDetailsDAO candidateDetailsDAO;
private ICasteStateDAO casteStateDAO;
private IEducationalQualificationsDAO educationalQualificationsDAO;
private ICasteDAO casteDAO;



public IEducationalQualificationsDAO getEducationalQualificationsDAO() {
	return educationalQualificationsDAO;
}

public void setEducationalQualificationsDAO(
		IEducationalQualificationsDAO educationalQualificationsDAO) {
	this.educationalQualificationsDAO = educationalQualificationsDAO;
}

public ICasteStateDAO getCasteStateDAO() {
	return casteStateDAO;
}

public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
	this.casteStateDAO = casteStateDAO;
}

public ICandidateDetailsDAO getCandidateDetailsDAO() {
	return candidateDetailsDAO;
}

public void setCandidateDetailsDAO(ICandidateDetailsDAO candidateDetailsDAO) {
	this.candidateDetailsDAO = candidateDetailsDAO;
}


public INominationDAO getNominationDAO() {
	return nominationDAO;
}

public void setNominationDAO(INominationDAO nominationDAO) {
	this.nominationDAO = nominationDAO;
}

public TransactionTemplate getTransactionTemplate() {
	return transactionTemplate;
}

public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
	this.transactionTemplate = transactionTemplate;
}

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


public ICasteDAO getCasteDAO() {
	return casteDAO;
}

public void setCasteDAO(ICasteDAO casteDAO) {
	this.casteDAO = casteDAO;
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

public List<CandidateVO> gettingCandidateDetails(Long electionScopeId,Long electionId,List<Long> districtIds)
{
LOG.debug("Entered in to gettingCandidateDetails() method in CandidateUpdationDetailsService class");
 List<CandidateVO> candidateVOList=null;
 Long publicationId=11l;
 try {
	    if(electionScopeId==3l)
		{
			List<Object[]> list=electionDAO.getCandidateDetailsForMPTC(publicationId,electionScopeId,electionId,districtIds);
			candidateVOList=getMatchedList(list);
			List<GenericVO> castesList=gettingAllCastes(); 
			List<GenericVO> eduList=gettingEducationDetails();
			CandidateVO candidateVO=candidateVOList.get(0);
			candidateVO.setCandidatescastes(castesList);
			candidateVO.setCandidatesEducations(eduList);
			
		}
		else if(electionScopeId==4l)
		{
			 List<Object[]> list=electionDAO.getCandidateDetailsForZPTC(publicationId,electionScopeId,electionId,districtIds);
			 candidateVOList=	getMatchedList(list);
			 List<GenericVO> castesList=gettingAllCastes(); 
			 List<GenericVO> eduList=gettingEducationDetails();
			 CandidateVO candidateVO=candidateVOList.get(0);
			 candidateVO.setCandidatescastes(castesList);
			 candidateVO.setCandidatesEducations(eduList);
		}
		else if(electionScopeId==5l)
		{
			 List<Object[]> list=electionDAO.getCandidateDetailsForMunicipality(electionId,districtIds);
			 candidateVOList   =	getMatchedList1(list);
			 List<GenericVO> castesList=gettingAllCastes(); 
			 List<GenericVO> eduList=gettingEducationDetails();
			 CandidateVO candidateVO=candidateVOList.get(0);
			 candidateVO.setCandidatescastes(castesList);
			 candidateVO.setCandidatesEducations(eduList);
		}
		else if(electionScopeId==6l)
		{
			 List<Object[]> list=electionDAO.getCandidateDetailsForCorporation(electionId,districtIds);
			 candidateVOList   =	getMatchedList1(list);
			 List<GenericVO> castesList=gettingAllCastes(); 
			 List<GenericVO> eduList=gettingEducationDetails();
			 CandidateVO candidateVO=candidateVOList.get(0);
			 candidateVO.setCandidatescastes(castesList);
			 candidateVO.setCandidatesEducations(eduList);
		}
	     return candidateVOList;
	    } 
    
    catch (Exception e)
    {
	 LOG.error("Exception in gettingCandidateDetails() of CandidateUpdateDetailsSerrvice class" + e);
	
    }	
	return candidateVOList;
}
public   List<GenericVO> gettingAllCastes()
{
 List<Caste> casteList= casteDAO.getAll();
 List<GenericVO> genericVOList=new ArrayList<GenericVO>();
 for(Caste caste:casteList)
 {
   GenericVO genericVO=new GenericVO();
   genericVO.setId(caste.getCasteId());
   genericVO.setName(caste.getCasteName());
   genericVOList.add(genericVO);
 }
 return genericVOList;
}
public List<GenericVO> gettingEducationDetails()
{
	List<EducationalQualifications> educationalQualificationsList=educationalQualificationsDAO.getAll();
	List<GenericVO> genericVOList=new ArrayList<GenericVO>();
	for(EducationalQualifications educationalQualifications:educationalQualificationsList)
	{
	 GenericVO genericVO=new GenericVO();
	 genericVO.setId(educationalQualifications.getEduQualificationId());
	 genericVO.setName(educationalQualifications.getQualification());
	 genericVOList.add(genericVO);	
	}
   return genericVOList;
}

public List<CandidateVO> getMatchedList(List<Object[]> list)
{
	//0-cid(mptcid),1-dname,2-cname,3-tname,4-mptcname,5-lastname,6-candidateDetailsId,7-shortname(partyname),8-rank,
	// 9-caste,10-edqua,11-howlong,12-mobileno
    List<CandidateVO> candidateVOList=new ArrayList<CandidateVO>();
	for(Object[] obj:list)
	{
	 CandidateVO vo=new CandidateVO();
	 vo.setId((Long)obj[0]);//mptcid or zptcid
	 vo.setState(obj[4].toString());//MPTC nameor zptc name
	 vo.setDistrict(obj[1].toString());//District
	 vo.setConstituencyName(obj[2].toString());//assembly
	 vo.setTehsilName(obj[3].toString());//Tehsil
	 vo.setCandidateId((Long)obj[6]);//candidateDetailsId
	 vo.setCandidateName(obj[5].toString());//cname
	 vo.setParty(obj[7].toString());//Party
	 vo.setRank((Long)obj[8]);//rank
	 vo.setCaste(obj[9].toString());//Caste
	 vo.setEducation(obj[10].toString());//Education
	 vo.setHowLongWorkingInParty((Double)obj[11]);//HowLongWorkingInParty
	 vo.setMobileNo((String)obj[12]);//MobileNo
	 candidateVOList.add(vo);
	} 
	 Collections.sort(candidateVOList,new MyComparator());
	 return candidateVOList;
}
public List<CandidateVO> getMatchedList1(List<Object[]> list)
{
	//select 0-constituency_id,1-d.district_name,2-t.tehsil_name,3-leb.name,4-co.name,5-c.lastname,
	//6-candidateDetailsId,7-p.short_name,8-cr.votes_earned,9-cr.rank,10-caste,11-edqua,12-howlong,13-mobileno
    List<CandidateVO> candidateVOList=new ArrayList<CandidateVO>();
	for(Object[] obj:list)
	{
	 CandidateVO vo=new CandidateVO();
	 vo.setId((Long)obj[0]);//ward id
	 vo.setState(obj[4].toString());//ward name
	 vo.setDistrict(obj[1].toString());//dname
	 vo.setTehsilName(obj[2].toString());//tname
	 vo.setLocalBodyName(obj[3].toString());// leb name
	 vo.setCandidateId((Long)obj[6]);//candidateDetailsId
	 vo.setCandidateName(obj[5].toString());//cname
	 vo.setParty(obj[7].toString());//party
	 vo.setVotesEarned((Double)obj[8]);//votesearned
	 vo.setRank((Long)obj[9]);//rank
	 vo.setCaste(obj[10].toString());//Caste
	 vo.setEducation(obj[11].toString());//Education
	 vo.setHowLongWorkingInParty((Double)obj[12]);//setHowLongWorkingInParty
	 vo.setMobileNo((String)obj[13]);//mobile
	 candidateVOList.add(vo);
	} 
	 Collections.sort(candidateVOList,new MyComparator());
	 return candidateVOList;
}
public ResultStatus updateDetailsofACandidate(final List<CandidateVO> candidateVOList)
{
 ResultStatus status = new ResultStatus();
try
{
if(candidateVOList!=null&& candidateVOList.size()>0)
{
for (CandidateVO candidateVO : candidateVOList)
{
CandidateDetails candidateDetails =candidateDetailsDAO.get(candidateVO.getCandidateId());

if( candidateVO.getHowLongWorkingInParty()!=candidateDetails.getHowLongWorkingInParty())
   candidateDetails.setHowLongWorkingInParty(candidateVO.getHowLongWorkingInParty());

if(candidateVO.getMobileNo()!=candidateDetails.getMobileno())
  candidateDetails.setMobileno(candidateVO.getMobileNo());

if( (candidateVO.getCasteId())!=(candidateDetails.getCasteState().getCaste().getCasteId()) )
{
	List<CasteState> casteStateList=casteStateDAO.gettingCasteStateBasedOnCaste(candidateVO.getCasteId());
	if(casteStateList!=null && casteStateList.size()>0)
	{
	  CasteState casteState	=casteStateList.get(0);
	  candidateDetails.setCasteState(casteState);
	  
	}
}
if( (candidateVO.getEducationId())!=(candidateDetails.getEducationalQualifications().getEduQualificationId()) )
{
 EducationalQualifications educationalQualifications=educationalQualificationsDAO.get(candidateVO.getEducationId());
 candidateDetails.setEducationalQualifications(educationalQualifications);	
}


candidateDetailsDAO.save(candidateDetails);
  
					 
}//each
				 
}//if
				  
status.setResultCode(0);
status.setMessage("Candidate  Details Updated Successfully...");
}
catch (Exception e)
{
 status.setResultCode(1);
 status.setMessage("Error occured while Updating Candidate Details...");
 LOG.error("exception occured in updateDetailsofACandidate() in UserService class. ",e);
 e.printStackTrace();
}
return status;
		
}


}
class MyComparator implements Comparator<CandidateVO>
{
   //Sorting according to ascending order of ids.
  //If same ids then sort according to ascending order of ranks.
	@Override
	public int compare(CandidateVO obj1, CandidateVO obj2)
	{
	    if( (obj1.getId()-obj2.getId())==0 )
	    {
	    	return (int) (obj1.getRank()-obj2.getRank());
	    	
	    }
	    else 
	    	return (int)(obj1.getId()-obj2.getId());
	}
	
	
}
