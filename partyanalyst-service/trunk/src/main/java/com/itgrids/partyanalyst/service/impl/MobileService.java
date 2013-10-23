package com.itgrids.partyanalyst.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBloodGroupDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICadreLevelDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryGroupDAO;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyHierarchyInfoDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.dao.IInfluencingPeoplePositionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserAccessDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserProfileDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterBasicInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dao.IVotingTrendzDAO;
import com.itgrids.partyanalyst.dao.IVotingTrendzPartiesResultDAO;
import com.itgrids.partyanalyst.dao.hibernate.CadreDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.BloodGroup;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreLevel;
import com.itgrids.partyanalyst.model.Caste;
import com.itgrids.partyanalyst.model.CasteCategory;
import com.itgrids.partyanalyst.model.CasteCategoryGroup;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyHierarchyInfo;
import com.itgrids.partyanalyst.model.EducationalQualifications;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.InfluencingPeoplePosition;
import com.itgrids.partyanalyst.model.MobileAppUser;
import com.itgrids.partyanalyst.model.MobileAppUserAccess;
import com.itgrids.partyanalyst.model.MobileAppUserProfile;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.PublicationDate;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.VoterAgeInfo;
import com.itgrids.partyanalyst.model.VoterAgeRange;
import com.itgrids.partyanalyst.model.VoterBasicInfo;
import com.itgrids.partyanalyst.model.VoterCastBasicInfo;
import com.itgrids.partyanalyst.model.VoterCastInfo;
import com.itgrids.partyanalyst.model.VoterFamilyInfo;
import com.itgrids.partyanalyst.model.VoterFamilyRange;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.model.VoterReportLevel;
import com.itgrids.partyanalyst.model.VotingTrendz;
import com.itgrids.partyanalyst.model.VotingTrendzPartiesResult;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class MobileService implements IMobileService{
	
 private static final Logger LOG = Logger.getLogger(MobileService.class);
 private IBloodGroupDAO bloodGroupDAO;
 private ICadreLevelDAO cadreLevelDAO;
 private ICasteCategoryDAO casteCategoryDAO;
 private ICasteCategoryGroupDAO casteCategoryGroupDAO;
 private IDistrictDAO districtDAO;
 private IEducationalQualificationsDAO educationalQualificationsDAO;
 private IElectionTypeDAO electionTypeDAO;
 private ILocalElectionBodyDAO localElectionBodyDAO;
 private IOccupationDAO occupationDAO;
 private IPartyDAO partyDAO;
 private IStateDAO stateDAO;
 private ITehsilDAO tehsilDAO;
 private IVoterAgeRangeDAO voterAgeRangeDAO;
 private IVoterFamilyRangeDAO voterFamilyRangeDAO;
 private IVoterReportLevelDAO voterReportLevelDAO;
 private ICasteDAO casteDAO;
 private IVoterInfoDAO voterInfoDAO; 
 private IConstituencyDAO constituencyDAO;  
 private IBoothDAO boothDAO;
 private ICasteStateDAO casteStateDAO; 
 private IPanchayatDAO panchayatDAO;
 private IVoterAgeInfoDAO voterAgeInfoDAO;
 private IVoterBasicInfoDAO voterBasicInfoDAO;
 private IVoterCastInfoDAO voterCastInfoDAO;
 private IVoterCastBasicInfoDAO voterCastBasicInfoDAO;
 private IVoterFamilyInfoDAO voterFamilyInfoDAO;
 private IVotingTrendzDAO votingTrendzDAO;
 private IVotingTrendzPartiesResultDAO votingTrendzPartiesResultDAO;
 private IConstituencyHierarchyInfoDAO constituencyHierarchyInfoDAO;
 private IHamletDAO hamletDAO;
 private IPanchayatHamletDAO panchayatHamletDAO;
 private IBoothPublicationVoterDAO boothPublicationVoterDAO;
 private IUserVoterDetailsDAO userVoterDetailsDAO;
 private IVoterModificationDAO voterModificationDAO;
 private IVoterModificationInfoDAO voterModificationInfoDAO;
 private IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO;
 private IPublicationDateDAO publicationDateDAO;
 private ICadreDAO cadreDAO;
 private IInfluencingPeoplePositionDAO influencingPeoplePositionDAO;
 private IInfluencingPeopleDAO influencingPeopleDAO;
 private IMobileAppUserDAO mobileAppUserDAO;
 private IMobileAppUserAccessDAO mobileAppUserAccessDAO;
 private IMobileAppUserProfileDAO mobileAppUserProfileDAO;
 private TransactionTemplate transactionTemplate;
 
 
  public TransactionTemplate getTransactionTemplate() {
	return transactionTemplate;
}

public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
	this.transactionTemplate = transactionTemplate;
}

public IMobileAppUserDAO getMobileAppUserDAO() {
	return mobileAppUserDAO;
}

public void setMobileAppUserDAO(IMobileAppUserDAO mobileAppUserDAO) {
	this.mobileAppUserDAO = mobileAppUserDAO;
}

public IMobileAppUserAccessDAO getMobileAppUserAccessDAO() {
	return mobileAppUserAccessDAO;
}

public void setMobileAppUserAccessDAO(
		IMobileAppUserAccessDAO mobileAppUserAccessDAO) {
	this.mobileAppUserAccessDAO = mobileAppUserAccessDAO;
}

public IMobileAppUserProfileDAO getMobileAppUserProfileDAO() {
	return mobileAppUserProfileDAO;
}

public void setMobileAppUserProfileDAO(
		IMobileAppUserProfileDAO mobileAppUserProfileDAO) {
	this.mobileAppUserProfileDAO = mobileAppUserProfileDAO;
}

public IInfluencingPeopleDAO getInfluencingPeopleDAO() {
	return influencingPeopleDAO;
}

public void setInfluencingPeopleDAO(IInfluencingPeopleDAO influencingPeopleDAO) {
	this.influencingPeopleDAO = influencingPeopleDAO;
}

public IInfluencingPeoplePositionDAO getInfluencingPeoplePositionDAO() {
	return influencingPeoplePositionDAO;
}

public void setInfluencingPeoplePositionDAO(
		IInfluencingPeoplePositionDAO influencingPeoplePositionDAO) {
	this.influencingPeoplePositionDAO = influencingPeoplePositionDAO;
}

public ICadreDAO getCadreDAO() {
	return cadreDAO;
}

public void setCadreDAO(ICadreDAO cadreDAO) {
	this.cadreDAO = cadreDAO;
}

public IPublicationDateDAO getPublicationDateDAO() {
	return publicationDateDAO;
}

public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
	this.publicationDateDAO = publicationDateDAO;
}

public IVoterModificationAgeInfoDAO getVoterModificationAgeInfoDAO() {
	return voterModificationAgeInfoDAO;
}

public void setVoterModificationAgeInfoDAO(
		IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO) {
	this.voterModificationAgeInfoDAO = voterModificationAgeInfoDAO;
}

public IVoterModificationInfoDAO getVoterModificationInfoDAO() {
	return voterModificationInfoDAO;
}

public void setVoterModificationInfoDAO(
		IVoterModificationInfoDAO voterModificationInfoDAO) {
	this.voterModificationInfoDAO = voterModificationInfoDAO;
}

public IVoterModificationDAO getVoterModificationDAO() {
	return voterModificationDAO;
}

public void setVoterModificationDAO(IVoterModificationDAO voterModificationDAO) {
	this.voterModificationDAO = voterModificationDAO;
}

public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
	return userVoterDetailsDAO;
}

public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
	this.userVoterDetailsDAO = userVoterDetailsDAO;
}

public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
	return boothPublicationVoterDAO;
}

public void setBoothPublicationVoterDAO(
		IBoothPublicationVoterDAO boothPublicationVoterDAO) {
	this.boothPublicationVoterDAO = boothPublicationVoterDAO;
}

public IPanchayatHamletDAO getPanchayatHamletDAO() {
	return panchayatHamletDAO;
}

public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
	this.panchayatHamletDAO = panchayatHamletDAO;
}

public IHamletDAO getHamletDAO() {
	return hamletDAO;
}

public void setHamletDAO(IHamletDAO hamletDAO) {
	this.hamletDAO = hamletDAO;
}

public IBloodGroupDAO getBloodGroupDAO() {
	return bloodGroupDAO;
  }

  public void setBloodGroupDAO(IBloodGroupDAO bloodGroupDAO) {
	this.bloodGroupDAO = bloodGroupDAO;
 }

  public ICadreLevelDAO getCadreLevelDAO() {
	return cadreLevelDAO;
 }

  public void setCadreLevelDAO(ICadreLevelDAO cadreLevelDAO) {
	this.cadreLevelDAO = cadreLevelDAO;
  }

  public ICasteCategoryDAO getCasteCategoryDAO() {
	return casteCategoryDAO;
  }

  public void setCasteCategoryDAO(ICasteCategoryDAO casteCategoryDAO) {
	this.casteCategoryDAO = casteCategoryDAO;
  }


  public ICasteCategoryGroupDAO getCasteCategoryGroupDAO() {
	return casteCategoryGroupDAO;
  }

  public void setCasteCategoryGroupDAO(
		ICasteCategoryGroupDAO casteCategoryGroupDAO) {
	this.casteCategoryGroupDAO = casteCategoryGroupDAO;
  }

  public IDistrictDAO getDistrictDAO() {
	return districtDAO;
  }

  public void setDistrictDAO(IDistrictDAO districtDAO) {
	this.districtDAO = districtDAO;
  }

  public IEducationalQualificationsDAO getEducationalQualificationsDAO() {
	return educationalQualificationsDAO;
  }

  public void setEducationalQualificationsDAO(
		IEducationalQualificationsDAO educationalQualificationsDAO) {
	this.educationalQualificationsDAO = educationalQualificationsDAO;
  }

  public IElectionTypeDAO getElectionTypeDAO() {
	return electionTypeDAO;
  }

  public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
	this.electionTypeDAO = electionTypeDAO;
  }

  public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
	return localElectionBodyDAO;
  }

  public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
	this.localElectionBodyDAO = localElectionBodyDAO;
  }

  public IOccupationDAO getOccupationDAO() {
	return occupationDAO;
  }

  public void setOccupationDAO(IOccupationDAO occupationDAO) {
	this.occupationDAO = occupationDAO;
  }

  public IPartyDAO getPartyDAO() {
	return partyDAO;
  }

  public void setPartyDAO(IPartyDAO partyDAO) {
	this.partyDAO = partyDAO;
  }

  public IStateDAO getStateDAO() {
	return stateDAO;
  }

  public void setStateDAO(IStateDAO stateDAO) {
	this.stateDAO = stateDAO;
  }

  public ITehsilDAO getTehsilDAO() {
	return tehsilDAO;
  }

  public void setTehsilDAO(ITehsilDAO tehsilDAO) {
	this.tehsilDAO = tehsilDAO;
  }

  public IVoterAgeRangeDAO getVoterAgeRangeDAO() {
	return voterAgeRangeDAO;
  }

  public void setVoterAgeRangeDAO(IVoterAgeRangeDAO voterAgeRangeDAO) {
	this.voterAgeRangeDAO = voterAgeRangeDAO;
  }

  public IVoterFamilyRangeDAO getVoterFamilyRangeDAO() {
	return voterFamilyRangeDAO;
  }

  public void setVoterFamilyRangeDAO(IVoterFamilyRangeDAO voterFamilyRangeDAO) {
	this.voterFamilyRangeDAO = voterFamilyRangeDAO;
  }

  public IVoterReportLevelDAO getVoterReportLevelDAO() {
	return voterReportLevelDAO;
  }

  public void setVoterReportLevelDAO(IVoterReportLevelDAO voterReportLevelDAO) {
	this.voterReportLevelDAO = voterReportLevelDAO;
  }
  
  
  public IVoterInfoDAO getVoterInfoDAO() {
	return voterInfoDAO;
  }

  public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
	this.voterInfoDAO = voterInfoDAO;
  }

  public ICasteDAO getCasteDAO() {
	return casteDAO;
 }

  public void setCasteDAO(ICasteDAO casteDAO) {
	this.casteDAO = casteDAO;
 }

  public IConstituencyDAO getConstituencyDAO() {
	return constituencyDAO;
  }

  public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
	this.constituencyDAO = constituencyDAO;
  }

  
  public IBoothDAO getBoothDAO() {
	return boothDAO;
  }

  public void setBoothDAO(IBoothDAO boothDAO) {
	this.boothDAO = boothDAO;
  }

  public ICasteStateDAO getCasteStateDAO() {
	return casteStateDAO;
  }

  public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
	this.casteStateDAO = casteStateDAO;
  }

  public IPanchayatDAO getPanchayatDAO() {
	return panchayatDAO;
  }

  public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
	this.panchayatDAO = panchayatDAO;
  }

  public IVoterAgeInfoDAO getVoterAgeInfoDAO() {
	return voterAgeInfoDAO;
  }

  public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
	this.voterAgeInfoDAO = voterAgeInfoDAO;
  }

  public IVoterBasicInfoDAO getVoterBasicInfoDAO() {
	return voterBasicInfoDAO;
  }

  public void setVoterBasicInfoDAO(IVoterBasicInfoDAO voterBasicInfoDAO) {
	this.voterBasicInfoDAO = voterBasicInfoDAO;
  }

  public IVoterCastInfoDAO getVoterCastInfoDAO() {
	return voterCastInfoDAO;
  }

  public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
	this.voterCastInfoDAO = voterCastInfoDAO;
  }

  public IVoterCastBasicInfoDAO getVoterCastBasicInfoDAO() {
	return voterCastBasicInfoDAO;
  }

  public void setVoterCastBasicInfoDAO(
		IVoterCastBasicInfoDAO voterCastBasicInfoDAO) {
	this.voterCastBasicInfoDAO = voterCastBasicInfoDAO;
  }

  public IVoterFamilyInfoDAO getVoterFamilyInfoDAO() {
	return voterFamilyInfoDAO;
  }

  public void setVoterFamilyInfoDAO(IVoterFamilyInfoDAO voterFamilyInfoDAO) {
	this.voterFamilyInfoDAO = voterFamilyInfoDAO;
  }

  public IVotingTrendzDAO getVotingTrendzDAO() {
	return votingTrendzDAO;
  }

  public void setVotingTrendzDAO(IVotingTrendzDAO votingTrendzDAO) {
	this.votingTrendzDAO = votingTrendzDAO;
  }

  public IVotingTrendzPartiesResultDAO getVotingTrendzPartiesResultDAO() {
	return votingTrendzPartiesResultDAO;
  }

  public void setVotingTrendzPartiesResultDAO(
		IVotingTrendzPartiesResultDAO votingTrendzPartiesResultDAO) {
	this.votingTrendzPartiesResultDAO = votingTrendzPartiesResultDAO;
  }
  
  public IConstituencyHierarchyInfoDAO getConstituencyHierarchyInfoDAO() {
	return constituencyHierarchyInfoDAO;
  }

  public void setConstituencyHierarchyInfoDAO(
		IConstituencyHierarchyInfoDAO constituencyHierarchyInfoDAO) {
	this.constituencyHierarchyInfoDAO = constituencyHierarchyInfoDAO;
  }

public List<SelectOptionVO> getConstituencyList()
  {
	  List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>();
	 try{
		 List<Object[]> list = null;
		List<Long> constituencyIds = voterInfoDAO.getConstituencyIds();
		if(constituencyIds != null && constituencyIds.size() > 0)
		 list = constituencyDAO.getConstityencyByConstituencyids(constituencyIds);
		if(list != null && list.size() > 0)
		 for(Object[] params:list)
		  selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
		
	  return selectOptionVOList;
	 }catch (Exception e) {
	  e.printStackTrace();
	  LOG.error(" Exception Occured in getConstituencyList() method, Exception - "+e);
	  return selectOptionVOList;
	}
  }

  public ResultStatus createDataDumpFileForSelectedConstituency(Long constituencyId,String path,final RegistrationVO reVo)
  {
	 LOG.info("Entered into createDataDumpFileForSelectedConstituency Method ");
	 ResultStatus resultStatus = new ResultStatus();
	try{
	saveUserData(reVo);
	File f= new File(path);	
	BufferedWriter outPut = new BufferedWriter(new FileWriter(f));
	StringBuilder str = new StringBuilder();
	ResourceBundle rb = ResourceBundle.getBundle("mobileDBScripts");
	Enumeration<String> keysList =   rb.getKeys();
	Long latestPublicationId = boothDAO.getLatestPublicationDateIdForAConstituency(constituencyId);
	
	while(keysList.hasMoreElements())
	{
		str.append(rb.getString(keysList.nextElement()));
		str.append("\n");
	}
	
	str.append("\n \n");
	
	LOG.info("Properties File is read ");
	
	List<BloodGroup> bloodGroupList = bloodGroupDAO.getBloodGroupList();
	if(bloodGroupList != null && bloodGroupList.size()>0)
	{
	  for(BloodGroup bloodGroup:bloodGroupList)
	    str.append("INSERT INTO blood_group(blood_group_id, blood_group) VALUES ('"+bloodGroup.getBloodGroupId()+"','"+bloodGroup.getBloodGroup()+"');\n");
	}
	
	str.append("\n\n");
	
	LOG.info("Bloo Group Data Completed.");
	//List<Booth> boothList = boothDAO.getBoothsListByConstituencyId(constituencyId);
	List<Booth> boothList = boothDAO.getBoothOfAConstituencyInAPublication(constituencyId, latestPublicationId);
			
	if(boothList != null && boothList.size() > 0)
	{
	  for(Booth booth:boothList)
	  {
		  try{
		  str.append("INSERT INTO booth(booth_id,part_no,part_name,location,village_covered,tehsil_id,male_voters,female_voters,total_voters,constituency_id,year,local_election_body_id,publication_date_id,panchayat_id,ward_id) VALUES (");
		  str.append(booth.getBoothId()+",'"+booth.getPartNo()+"','"+booth.getPartName()+"','"+booth.getLocation()+"','"+booth.getVillagesCovered()+"',");
		  str.append(booth.getTehsil() != null ? booth.getTehsil().getTehsilId()+"," : "NULL,");
		  str.append(booth.getMaleVoters() != null ? booth.getMaleVoters()+"," : "NULL,");
		  str.append(booth.getFemaleVoters() != null ? booth.getFemaleVoters()+"," : "NULL,");
		  str.append(booth.getTotalVoters() != null ? booth.getTotalVoters()+"," : "NULL,");
		  str.append(booth.getConstituency() != null ? booth.getConstituency().getConstituencyId()+"," : "NULL,");
		  str.append(booth.getYear() != null ? booth.getYear()+"," : "NULL,");
		  str.append(booth.getLocalBody() != null ? booth.getLocalBody().getLocalElectionBodyId()+"," : "NULL,");
		  str.append(booth.getPublicationDate() != null ? booth.getPublicationDate().getPublicationDateId()+"," : "NULL,");
		  str.append(booth.getPanchayat() != null ? booth.getPanchayat().getPanchayatId()+"," : "NULL,");
		  str.append(booth.getLocalBodyWard() != null ? booth.getLocalBodyWard().getConstituencyId()+"," : "NULL");
		  str.append(");\n");
		  }catch(Exception e)
		  {
			  LOG.error("Exception Occured in Booth Inserts - "+e);
		  }
	  }
	  str.append("\n");
	}
	LOG.info("Booth Data Completed...");
	
	List<CadreLevel> cadreLevelList = cadreLevelDAO.getCadreLevelList();
	if(cadreLevelList != null && cadreLevelList.size() > 0)
	{
	 for(CadreLevel cadreLevel:cadreLevelList)
	 {
	  str.append("INSERT INTO cadre_level(cadre_level_id,level,order_no) VALUES ('"+cadreLevel.getCadreLevelID()+"','"+cadreLevel.getLevel()+"',");
	  str.append(cadreLevel.getOrderNo() != null ? cadreLevel.getOrderNo() : "NULL");
	  str.append(");\n");
	 }
	}
    str.append("\n");
	
    LOG.info("Cadre Level data Completed...");
    
	List<Caste> casteList = casteDAO.getCasteList();
	if(casteList != null && casteList.size() > 0)
	{
	  for(Caste caste:casteList)
		str.append("INSERT INTO caste(caste_id,caste_name) VALUES ('"+caste.getCasteId()+"','"+caste.getCasteName()+"');\n");
	}
	str.append("\n");
	
	LOG.info("Caste data Completed...");
	
	List<Object[]> casteStateList = casteStateDAO.getCasteStateList();
	if(casteStateList != null && casteStateList.size() > 0)
	{
	 for(Object[] params:casteStateList)
		str.append("INSERT INTO caste_state(caste_state_id,state_id,caste_category_group_id,caste_id) VALUES ('"+(Long)params[0]+"','"+(Long)params[1]+"','"+(Long)params[2]+"','"+(Long)params[3]+"');\n");
	}
		
	 str.append("\n");
	 
	 LOG.info("Caste State data Completed...");
	 
	 List<CasteCategory> casteCategoryList = casteCategoryDAO.getCasteCategoryList();
	 if(casteCategoryList != null && casteCategoryList.size() > 0)
	 {
	  for(CasteCategory casteCategory:casteCategoryList)
		str.append("INSERT INTO caste_category (caste_category_id,category_name,description) VALUES ('"+casteCategory.getCasteCategoryId()+"','"+casteCategory.getCategoryName()+"','"+casteCategory.getDescription()+"');\n");
	 }
	 str.append("\n");
	 
	 LOG.info("Caste Category data Completed...");
	 
	 List<ConstituencyHierarchyInfo> constituencyHierarchyInfoList = constituencyHierarchyInfoDAO.getConstituencyHierarchyInfoList(constituencyId, 1L);
	 if(constituencyHierarchyInfoList != null && constituencyHierarchyInfoList.size() > 0)
	 {
		for(ConstituencyHierarchyInfo hierarchyInfo:constituencyHierarchyInfoList)
		 str.append("INSERT INTO constituency_hierarchy_info(constituency_hierarchy_info_id,constituency_id,mandals,municipalities,panchayats,hamlets,wards,booths,publication_date_id,report_level_id,report_level_value) " +
		 		"VALUES ('"+hierarchyInfo.getConstituencyHierarchyInfoId()+"','"+hierarchyInfo.getConstituencyId()+"','"+hierarchyInfo.getMandals()+"','"+hierarchyInfo.getMunicipalities()+"','"+hierarchyInfo.getPanchayats()+"'" +
		 				",'"+hierarchyInfo.getHamlets()+"','"+hierarchyInfo.getWards()+"','"+hierarchyInfo.getBooths()+"','"+hierarchyInfo.getPublicationDateId()+"','"+hierarchyInfo.getReportLevelId()+"','"+hierarchyInfo.getReportLevelValue()+"');\n");
	 }
	 
	 str.append("\n");
	 
	 LOG.info("Constituency Hierarchy Info data Completed...");
	 
	 List<Constituency> constituencyList = constituencyDAO.getConstituencyDetails();
	 if(constituencyList != null && constituencyList.size() > 0)
	 {
		 for(Constituency constituency : constituencyList)
		 {
			 str.append("INSERT INTO constituency(constituency_id,name,election_scope_id,state_id,district_id,tehsil_id,local_election_body_id,area_type) VALUES (");
			 str.append(" "+constituency.getConstituencyId()+",'"+constituency.getName()+"',"+constituency.getElectionScope().getElectionScopeId()+",");
			 str.append(constituency.getState() != null ? constituency.getState().getStateId()+"," : "NULL,");
			 str.append(constituency.getDistrict() != null ? constituency.getDistrict().getDistrictId()+"," : "NULL,");
			 str.append(constituency.getTehsil() != null ? constituency.getTehsil().getTehsilId()+"," : "NULL,");
			 str.append(constituency.getLocalElectionBody() != null ? constituency.getLocalElectionBody().getLocalElectionBodyId()+"," : "NULL,");
			 str.append("'"+constituency.getAreaType()+"');\n");
		 }
	 }
	 
	 str.append("\n");
	 
	 LOG.info("Constituency table data Completed...");
	 
	 List<CasteCategoryGroup> list = casteCategoryGroupDAO.getCasteCategoryGroupList();
	 if(list != null && list.size() > 0)
	 {
		for(CasteCategoryGroup casteCategoryGroup:list)
		 str.append("INSERT INTO caste_category_group(caste_category_group_id,caste_category_id,caste_category_group_name) " +
		 		" VALUES ('"+casteCategoryGroup.getCasteCategoryGroupId()+"','"+casteCategoryGroup.getCasteCategory().getCasteCategoryId()+"','"+casteCategoryGroup.getCasteCategoryGroupName()+"');\n");
	 }
	 str.append("\n");
	
	 LOG.info("caste Category Group table data Completed...");
	 
	List<Object[]> districtList = districtDAO.getDistrictList();
	if(districtList != null && districtList.size() > 0)
	{
	 for(Object[] params:districtList)
	  str.append("INSERT INTO district(district_id,district_name,state_id) VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"','"+(Long)params[2]+"');\n");
	}
	str.append("\n");
	
	LOG.info("District data Completed...");
	
	List<EducationalQualifications> eduQualifList = educationalQualificationsDAO.getEducationalQualificationsList();
	if(eduQualifList != null && eduQualifList.size() > 0)
	{
	  for(EducationalQualifications eList:eduQualifList)
		str.append("INSERT INTO educational_qualifications(educational_qualification_id,qualification) VALUES ('"+eList.getEduQualificationId()+"','"+eList.getQualification()+"');\n");
	}
	str.append("\n");
	
	LOG.info("educational_qualifications data Completed...");
	
	List<ElectionType> electionTypeList = electionTypeDAO.getElectionTypeList();
	if(electionTypeList != null && electionTypeList.size() > 0)
	{
	  for(ElectionType electionType:electionTypeList)
		str.append("INSERT INTO election_type(election_type_id,election_type) VALUES ('"+electionType.getElectionTypeId()+"','"+electionType.getElectionType()+"');\n");
	}
	str.append("\n");
	
	LOG.info("Election Type data Completed...");
	
	/*List<Object[]> hamletsAndMandalsList = hamletDAO.gethamletsInAState(constituencyDAO.get(constituencyId).getState().getStateId());
	List<Object[]> hamletsAndPanchayatsList = panchayatHamletDAO.gethamletsInAState(constituencyDAO.get(constituencyId).getState().getStateId());
	Map<Long,Long> hamletsAndPanchayatsMap = new LinkedHashMap<Long,Long>();
	*/
	List<Object[]> hamletsList = boothDAO.getHamletsForAConstituencyForAPublication(constituencyId,latestPublicationId);
	
	/*if(hamletsAndPanchayatsList != null && hamletsAndPanchayatsList.size() > 0)
	{
		for(Object[] params : hamletsAndPanchayatsList)
		{
			hamletsAndPanchayatsMap.put((Long)params[0],(Long)params[1]);
		}
	}*/
	
	LOG.info("Booth data Completed...");
	
	if(hamletsList != null && hamletsList.size() > 0)
	{
		for(Object[] params : hamletsList)
		{
			try{
			str.append("INSERT INTO hamlet(hamlet_id, hamlet_name, panchayat_id, tehsil_id) VALUES (");	
			str.append(""+params[0].toString()+",'"+params[1].toString()+"',"+params[2].toString()+","+params[3].toString()+");\n");
			}catch(Exception e)
			{
				LOG.error("Exceprtion Occured in Hamlets inserts - "+e);
			}
		}
		str.append("\n");
	}
	
	LOG.info("hamlet table data Completed...");
	
	List<Object[]> localElectionBodieList = localElectionBodyDAO.getLocationElectionBodyList();
	if(localElectionBodieList != null && localElectionBodieList.size() > 0)
	{
	  for(Object[] params:localElectionBodieList)
		 str.append("INSERT INTO local_election_body(local_election_body_id,name,election_type_id,tehsil_id,district_id,no_of_wards) " +
		 		"VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"','"+(Long)params[2]+"','"+(Long)params[3]+"','"+(Long)params[4]+"',"+params[4].toString()+");\n");
	}
	str.append("\n");
	
	LOG.info("Local Election Body data Completed...");
	
	List<Occupation> occupationList = occupationDAO.getOccupationList();
	if(occupationList != null && occupationList.size() > 0)
	{
	  for(Occupation occupation:occupationList)
	  {
		 str.append("INSERT INTO occupation(occupation_id,occupation) VALUES ('"+occupation.getOccupationId()+"','"+occupation.getOccupation()+"');");
		 str.append("\n");
	  }
	}
	str.append("\n");
	
	LOG.info("Occupation data Completed...");
	
	List<Object[]> panchayatList = boothDAO.getPanchayatsForAConstituencyForAPublication(constituencyId,latestPublicationId);
	if(panchayatList != null && panchayatList.size() > 0)
	{
	  for(Object[] params:panchayatList)
		str.append("INSERT INTO panchayat(panchayat_id,panchayat_name,tehsil_id) VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"','"+(Long)params[2]+"');\n");
	}
	
	str.append("\n");
	
	LOG.info("Panchayat table data Completed...");
	
	List<Object[]> partyList = partyDAO.getPartyShortName();
	if(partyList != null && partyList.size() > 0)
	{
	 for(Object[] params:partyList)
	  str.append("INSERT INTO party(party_id,short_name) VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"');\n");
	}
	
	str.append("\n");
	
	LOG.info("party table data Completed...");
	
	List<Object[]> stateList = stateDAO.getStateNames();
	if(stateList != null && stateList.size() > 0)
	{
	  for(Object[] params:stateList)
		 str.append("INSERT INTO state(state_id,state_name) VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"');\n");
	}
	str.append("\n");
	
	LOG.info("state table data Completed...");
	
	List<Object[]> tehsilList = boothDAO.getTehsilsForAConstituencyForAPublication(constituencyId,latestPublicationId);
	if(tehsilList != null && tehsilList.size() > 0)
	{
	  for(Object[] params:tehsilList)
		str.append("INSERT INTO tehsil(tehsil_id,tehsil_name,district_id) VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"','"+(Long)params[2]+"');\n");
	}
	
	str.append("\n");
	
	LOG.info("tehsil table data Completed...");
	
	List<VoterAgeInfo> voterAgeInfoList = voterAgeInfoDAO.getVoterAgeInfoList(constituencyId);
	if(voterAgeInfoList != null && voterAgeInfoList.size() > 0)
	{
	 for(VoterAgeInfo ageInfo:voterAgeInfoList)
		str.append("INSERT INTO voter_age_info(voter_age_info_id,report_level_id,report_level_value,age_range_id,total_voters,total_voters_percentage,male_voters,male_voters_percentage,female_voters,female_voters_percentage,publication_date_id,constituency_id) " +
				"VALUES ('"+ageInfo.getVoterAgeInfoId()+"','"+ageInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+ageInfo.getReportLevelValue()+"','"+ageInfo.getVoterAgeRange().getVoterAgeRangeId()+"','"+ageInfo.getTotalVoters()+"','"+ageInfo.getTotalVotersPercentage()+"'" +
						",'"+ageInfo.getMaleVoters()+"','"+ageInfo.getMaleVotersPercentage()+"','"+ageInfo.getFemaleVoters()+"','"+ageInfo.getFemaleVotersPercentage()+"','"+ageInfo.getPublicationDate().getPublicationDateId()+"','"+ageInfo.getConstituencyId()+"');\n");
	}
	
	str.append("\n");
	
	LOG.info("voter age info table data Completed...");
	
	List<VoterAgeRange> voterAgeRangeList = voterAgeRangeDAO.getVoterAgeRangeList();
	if(voterAgeRangeList != null && voterAgeRangeList.size() > 0)
	{
	  for(VoterAgeRange voterAgeRange:voterAgeRangeList)
		str.append("INSERT INTO voter_age_range(voter_age_range_id,age_range) VALUES ('"+voterAgeRange.getVoterAgeRangeId()+"','"+voterAgeRange.getAgeRange()+"');\n");
	}
	str.append("\n");
	
	LOG.info("voter age range table data Completed...");
	
	List<VoterBasicInfo> voterBasicInfoList = voterBasicInfoDAO.getVoterBasicInfoList(constituencyId);
	if(voterBasicInfoList != null && voterBasicInfoList.size() > 0)
	{
	  for(VoterBasicInfo basicInfo:voterBasicInfoList)
		str.append("INSERT INTO voter_basic_info(voter_basic_info_id,constituency_id,report_level_id,report_level_value,year,booths,total,male,female,total_diff,male_diff,female_diff,type,order_no)" +
				" VALUES ('"+basicInfo.getVoterBasicInfoId()+"','"+basicInfo.getConstituency().getConstituencyId()+"','"+basicInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+basicInfo.getReportLevelValue()+"'" +
						",'"+basicInfo.getYear()+"','"+basicInfo.getBooths()+"','"+basicInfo.getTotalVoters()+"','"+basicInfo.getMaleVoters()+"','"+basicInfo.getFemaleVoters()+"','"+basicInfo.getTotalDiff()+"','"+basicInfo.getMaleDiff()+"'" +
								",'"+basicInfo.getFemaleDiff()+"','"+basicInfo.getType()+"','"+basicInfo.getOrderNo()+"');\n");
	}
	str.append("\n");
	
	LOG.info("voter basic info table data Completed...");
	
	List<VoterCastInfo> voterCasteInfoList = voterCastInfoDAO.getVoterCasteInfoList(constituencyId,latestPublicationId,1L);
	if(voterCasteInfoList != null && voterCasteInfoList.size() > 0)
	{
	  for(VoterCastInfo castInfo:voterCasteInfoList)
	  {
		  try{
			str.append("INSERT INTO voter_caste_info(voter_cast_info_id,report_level_id,report_level_value,user_id,caste_state_id,caste_voters,caste_male_voters,caste_female_voters,caste_percentage,publication_date_id,constituency_id,sub_leve_caste_percentage) " +
					"VALUES ('"+castInfo.getVoterCastInfoId()+"','"+castInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+castInfo.getReportLevelValue()+"','"+castInfo.getUserId()+"','"+castInfo.getCasteState().getCasteStateId()+"','"+castInfo.getCasteVoters()+"'" +
							",'"+castInfo.getCasteMaleVoters()+"','"+castInfo.getCasteFemaleVoters()+"','"+castInfo.getCastePercentage()+"','"+castInfo.getPublicationDateId()+"','"+castInfo.getConstituency().getConstituencyId()+"'");
			if(castInfo.getSubLeveCastePercentage() != null)
				str.append(",'"+castInfo.getSubLeveCastePercentage()+"');");
			else
				str.append(",null);");
			
			str.append("\n");
		  }catch(Exception e)
			{
				LOG.error("Exception occured in voter_caste_info with voter_caste_info_id = "+castInfo.getVoterCastInfoId()+" Exception is - "+e);
			}
	  }
	
	}
	str.append("\n");
	
	LOG.info("voter caste info table data Completed...");
	
	List<VoterCastBasicInfo> voterCasteList = voterCastBasicInfoDAO.getVoterCastBasicInfoList(constituencyId,latestPublicationId,1L);
	if(voterCasteList != null && voterCasteList.size() > 0)
	{
		for(VoterCastBasicInfo basicInfo:voterCasteList)
		{
		  str.append("INSERT INTO voter_caste_basic_info(voter_cast_basic_info_id,report_level_id,report_level_value,user_id,total_castes,caste_assigned_voters,caste_not_assigned_voters,oc_voters,bc_voters,sc_voters,st_voters,publication_date_id,constituency_id) " +
		  		"VALUES ('"+basicInfo.getVoterCastBasicInfoId()+"','"+basicInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+basicInfo.getReportLevelValue()+"','"+basicInfo.getUserId()+"','"+basicInfo.getTotalCastes()+"','"+basicInfo.getCasteAssignedVoters()+"','"+basicInfo.getCasteNotAssignedVoters()+"'" +
		  				",'"+basicInfo.getOcVoters()+"','"+basicInfo.getBcVoters()+"','"+basicInfo.getScVoters()+"','"+basicInfo.getStVoters()+"','"+basicInfo.getPublicationDateId()+"','"+basicInfo.getConstituency().getConstituencyId()+"');" );
		  str.append("\n"); 
		}
	}
	str.append("\n");
	
	List<VoterFamilyInfo> voterFamilyInfoList = voterFamilyInfoDAO.getVoterFamilyInfoList(constituencyId);
	if(voterFamilyInfoList != null && voterFamilyInfoList.size() > 0)
	{
	  for(VoterFamilyInfo familyInfo:voterFamilyInfoList)
		str.append("INSERT INTO voter_family_info(voter_family_info_id,report_level_id,report_level_value,family_range_id,total_families,families_percentage,publication_date_id,constituency_id) " +
				"VALUES ('"+familyInfo.getVoterFamilyInfoId()+"','"+familyInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+familyInfo.getReportLevelValue()+"','"+familyInfo.getVoterFamilyRange().getVoterFamilyRangeId()+"'" +
						",'"+familyInfo.getTotalFamilies()+"','"+familyInfo.getFamiliesPercentage()+"','"+familyInfo.getPublicationDate().getPublicationDateId()+"','"+familyInfo.getConstituencyId()+"');\n");
	}
	str.append("\n");
	
	LOG.info("voter family info table data Completed...");
	
	List<VoterFamilyRange> familyRangeList = voterFamilyRangeDAO.getVoterFamilyRangeList();
	if(familyRangeList != null && familyRangeList.size() > 0)
	{
	  for(VoterFamilyRange familyRange:familyRangeList)
		str.append("INSERT INTO voter_family_range(voter_family_range_id,family_range) VALUES ('"+familyRange.getVoterFamilyRangeId()+"','"+familyRange.getFamilyRange()+"');\n");
	}
	str.append("\n");
	
	LOG.info("voter family range table data Completed...");
	
	List<VoterInfo> voterInfoList = voterInfoDAO.getVoterInfoList(constituencyId);
	if(voterInfoList != null && voterInfoList.size() > 0)
	{
	 for(VoterInfo voterInfo:voterInfoList)
	 {
		str.append("INSERT INTO voter_info(voter_info_id,report_level_id,report_level_value,total_voters,total_voters_percentage,male_voters,male_voters_percentage,female_voters,female_voters_percentage,total_families,families_percentage,publication_date_id,constituency_id) " +
				"VALUES ('"+voterInfo.getVoterInfoId()+"','"+voterInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+voterInfo.getReportLevelValue()+"','"+voterInfo.getTotalVoters()+"',");
		if(voterInfo.getTotalVotersPercentage() != null)
		str.append("'"+voterInfo.getTotalVotersPercentage()+"'");
		else
		 str.append("null");
		str.append(",'"+voterInfo.getMaleVoters()+"','"+voterInfo.getMaleVotersPercentage()+"'" +
						",'"+voterInfo.getFemaleVoters()+"','"+voterInfo.getFemaleVotersPercentage()+"','"+voterInfo.getTotalFamilies()+"','"+voterInfo.getFamiliesPercentage()+"','"+voterInfo.getPublicationDate().getPublicationDateId()+"','"+voterInfo.getConstituencyId()+"');");
		str.append("\n"); 
	 }
	}
	
	str.append("\n");
	
	LOG.info("voter info table data Completed...");
	
	List<VoterReportLevel> reportLevelList = voterReportLevelDAO.getVoterReportLevelList();
	if(reportLevelList != null && reportLevelList.size() > 0)
	{
	  for(VoterReportLevel reportLevel:reportLevelList)
		str.append("INSERT INTO voter_report_level(voter_report_level_id,report_level,order_no) VALUES ('"+reportLevel.getVoterReportLevelId()+"','"+reportLevel.getReportLevel()+"','"+reportLevel.getOrderNo()+"');\n");
	}
	
	str.append("\n");
	
	LOG.info("voter report level table data Completed...");
	
	List<VotingTrendz> votingTrendZList = votingTrendzDAO.getVotingTrendzList(constituencyId);
	if(votingTrendZList != null && votingTrendZList.size() > 0)
	{
	  for(VotingTrendz trendz:votingTrendZList)
		str.append(" INSERT INTO voting_trendz(voting_trendz_id,constituency_id,report_level_id,report_level_value,election_type,year,total_booths,total_votes,votes_polled,election_type_id,order_no) " +
				"VALUES ('"+trendz.getVotingTrendzId()+"','"+trendz.getConstituency().getConstituencyId()+"','"+trendz.getVoterReportLevel().getVoterReportLevelId()+"','"+trendz.getReportLevelValue()+"','"+trendz.getElectionType().getElectionType()+"'" +
						",'"+trendz.getYear()+"','"+trendz.getTotalBooths()+"','"+trendz.getTotalVotes()+"','"+trendz.getVotesPolled()+"','"+trendz.getElectionType().getElectionTypeId()+"','"+trendz.getOrderNo()+"');\n");
	}
	str.append("\n");
	
	LOG.info("voting trendz table data Completed...");
	
	try{
	List<VotingTrendzPartiesResult> list1 = votingTrendzPartiesResultDAO.getVotingTrendzPartiesResultList(constituencyId);
	if(list1 != null && list1.size() > 0)
	{
	 for(VotingTrendzPartiesResult partiesResult:list1)
		str.append(" INSERT INTO voting_trendz_parties_result(voting_trendz_parties_result_id,voting_trendz_id,party_id,votes_gained) " +
				"VALUES ('"+partiesResult.getVotingTrendzPartiesResultId()+"','"+partiesResult.getVotingTrendz().getVotingTrendzId()+"','"+partiesResult.getParty().getPartyId()+"','"+partiesResult.getVotesGained()+"');\n");
	}
	}catch(Exception e){
		LOG.error("Exception occured in VotingTrendzPartiesResult inserting - "+e);
	}
	str.append("\n");
	
	LOG.info("voting trendz parties result table data Completed...");
	
	try{
		List<Object[]> votersAndSerialNosList = boothPublicationVoterDAO.getRecordsFromBoothPublicationVoter(constituencyId, latestPublicationId);
		
		if(votersAndSerialNosList != null && votersAndSerialNosList.size() > 0)
		{
			for(Object[] params : votersAndSerialNosList)
			{
				try{
					str.append("INSERT INTO booth_publication_voter(booth_publication_voter_id, booth_id, voter_id, serial_no) VALUES (");
					str.append(params[0].toString()+","+params[1].toString()+","+params[2].toString()+",");
					str.append(params[3] != null ? params[3].toString() : "0");
					str.append(");\n");
				}catch(Exception e){
					LOG.error("Error Occured in inserting records in BoothPublicationVoter - "+e);
				}
			}
			str.append("\n");
		}
	}catch(Exception e){}
	
	LOG.info("booth punlication voter table data Completed...");
	
	try{
		List<Object[]> votersList = boothPublicationVoterDAO.getVoterDetailsOfAConstituency(constituencyId,latestPublicationId,1L);
		
		if(votersList != null && votersList.size() > 0)
		{
			StringBuilder strTemp = null;
			for(Object[] params : votersList)
			{
				try{
					strTemp = new StringBuilder();
					strTemp.append("INSERT INTO voter(voter_id,house_no,name,relationship_type,relative_name,gender,age,voter_id_card_no,mobile_no) VALUES (");
					strTemp.append(params[0].toString()+",'"+params[1].toString()+"','"+params[2].toString()+"','"+params[3].toString()+"','"+params[4].toString()+"','");
					strTemp.append(params[5].toString()+"',"+params[6].toString()+",'"+params[7].toString()+"',");
					strTemp.append(params[8] != null ? "'"+params[8].toString()+"'" : "NULL");
					strTemp.append(");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exception occured in inserting voters Data with voter ID - "+params[0]+" Exception - "+e);
				}
			}
			str.append("\n");
		}
	}catch(Exception e){}
	
	LOG.info("voter table data Completed...");
	
	try{
		List<UserVoterDetails> userVoterList = userVoterDetailsDAO.getUserVoterDetailsOfAConstituencyForAPublication(constituencyId,latestPublicationId,1L);
		
		if(userVoterList != null && userVoterList.size() > 0)
		{
			StringBuilder strTemp = null;
			for(UserVoterDetails userVoter : userVoterList)
			{
				try{
					strTemp = new StringBuilder();
					strTemp.append("INSERT INTO user_voter_details(user_voter_details_id,voter_id,caste_state_id,locality_id,hamlet_id,ward_id,constituency_id) VALUES (");
					strTemp.append(userVoter.getUserVoterDetailsId()+","+userVoter.getVoter().getVoterId()+",");
					strTemp.append(userVoter.getCasteState() != null ? userVoter.getCasteState().getCasteStateId()+"," : "NULL,");
					strTemp.append(userVoter.getLocality() != null ? userVoter.getLocality().getLocalityId()+"," : "NULL,");
					strTemp.append(userVoter.getHamlet() != null ? userVoter.getHamlet().getHamletId()+"," : "NULL,");
					strTemp.append(userVoter.getWard() != null ? userVoter.getWard().getConstituencyId()+"," : "NULL,");
					strTemp.append(userVoter.getConstituency() != null ? userVoter.getConstituency().getConstituencyId() : "NULL");
					strTemp.append(");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exception occured in inserting user_voter_details Data with user_voter_details ID - "+userVoter.getUserVoterDetailsId()+" Exception - "+e);
				}
			}
			str.append("\n");
		}
	}catch(Exception e){}
	
	LOG.info("user voter details table data Completed...");
	
	try{
		List<Object[]> voterModificationList = voterModificationDAO.getVoterModificationDetailsOfAConstituencyForAPublication(constituencyId,latestPublicationId);
		
		if(voterModificationList != null && voterModificationList.size() > 0)
		{
			StringBuilder strTemp = null;
			for(Object[] params : voterModificationList)
			{
				try{
					strTemp = new StringBuilder();
					strTemp.append("INSERT INTO voter_modification(voter_modification_id,voter_id,status,part_no,constituency_id,publication_date_id,voter_status_id) VALUES (");
					strTemp.append(params[0]+","+params[1]+",'"+params[2]+"',"+params[3]+","+params[4]+","+params[5]+","+params[6]+");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exceprtion occured in VoterModification insert with voter_modification_id - "+params[0]+" Exception is -"+e);
				}
			}
			str.append("\n");
		}
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Voter Modification Inserts");
	}
	
	LOG.info("voter modification table data Completed...");
	
	try{
		List<Object[]> voterModificationInfoList = voterModificationInfoDAO.getVoterModificationInfoOfAConstituencyForAPublication(constituencyId,latestPublicationId);
		
		if(voterModificationInfoList != null && voterModificationInfoList.size() > 0)
		{
			StringBuilder strTemp = null;
			for(Object[] params : voterModificationInfoList)
			{
				try{
					strTemp = new StringBuilder();
					strTemp.append("INSERT INTO voter_modification_info(voter_modification_info_id,report_level_id,report_level_value,publication_date_id,constituency_id,voter_status_id,total_voters,male_voters,female_voters) VALUES (");
					strTemp.append(params[0]+","+params[1]+","+params[2]+","+params[3]+","+params[4]+","+params[5]+","+params[6]+","+params[7]+","+params[8]+");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exceprtion occured in VoterModificationInfo insert with voter_modification_info_id - "+params[0]+" Exception is -"+e);
				}
			}
			str.append("\n");
		}
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Voter Modification Info Inserts");
	}
	
	LOG.info("voter modification info table data Completed...");
	
	try{
		List<Object[]> voterModificationAgeInfoList = voterModificationAgeInfoDAO.getVoterModificationAgeInfoDetailsOfAConstituencyForAPublication(constituencyId,latestPublicationId);
		
		if(voterModificationAgeInfoList != null && voterModificationAgeInfoList.size() > 0)
		{
			StringBuilder strTemp = null;
			for(Object[] params : voterModificationAgeInfoList)
			{
				try{
					strTemp = new StringBuilder();
					strTemp.append("INSERT INTO voter_modification_age_info(voter_modification_age_info_id,voter_modification_info_id,voter_age_range_id,total_voters,male_voters,female_voters) VALUES (");
					strTemp.append(params[0]+","+params[1]+","+params[2]+","+params[3]+","+params[4]+","+params[5]+");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exceprtion occured in VoterModificationAgeInfo insert with voter_modification_age_info_id - "+params[0]+" Exception is -"+e);
				}
			}
			str.append("\n");
		}
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Voter Modification Age Info Inserts");
	}
	
	LOG.info("voter modification age info table data Completed...");
	
	try{
		List<Object[]> partiesListForVT = votingTrendzPartiesResultDAO.getpartiesListForVotingTrendz(constituencyId);
		
		if(partiesListForVT != null && partiesListForVT.size() > 0)
		{
			StringBuilder strTemp = new StringBuilder();
			List<String> partiesList = new ArrayList<String>(0);
			
			for(Object[] params : partiesListForVT)
			{
				partiesList.add(params[1].toString());
				strTemp.append("INSERT INTO voting_trendz_party(party_id,party) VALUES ("+params[0]+",'"+params[1]+"');\n");
			}
			strTemp.append("\n");
			
			List<VotingTrendz> VTList = votingTrendzDAO.getVotingTrendzList(constituencyId);
			List<VotingTrendzPartiesResult> VTPRList = votingTrendzPartiesResultDAO.getVotingTrendzPartiesResultList(constituencyId);
			
			strTemp.append("CREATE TABLE parties_voting_trendz('parties_voting_trendz_id' INTEGER PRIMARY KEY  NOT NULL,'constituency_id' INTEGER, 'report_level_id' INTEGER, 'report_level_value' INTEGER, 'year' INTEGER, 'total_booths' INTEGER, 'total_votes' INTEGER, 'votes_polled' INTEGER, 'election_type_id' INTEGER, 'order_no' INTEGER");
			for(String pstr : partiesList)
				strTemp.append(",'"+pstr+"' INTEGER");
			strTemp.append(");\n");
			
			for(VotingTrendz votingTrendz : VTList)
			{
				try{
				strTemp.append("INSERT INTO parties_voting_trendz(parties_voting_trendz_id,constituency_id,report_level_id,report_level_value,year,total_booths,total_votes,votes_polled,election_type_id,order_no");
				for(String pstr : partiesList)
					strTemp.append(","+pstr);
				strTemp.append(") VALUES (");
				strTemp.append(votingTrendz.getVotingTrendzId()+","+votingTrendz.getConstituency().getConstituencyId()+","+votingTrendz.getVoterReportLevel().getVoterReportLevelId()+","+votingTrendz.getReportLevelValue()+","+votingTrendz.getYear()+",");
				strTemp.append(votingTrendz.getTotalBooths()+","+votingTrendz.getTotalVotes()+","+votingTrendz.getVotesPolled()+","+votingTrendz.getElectionType().getElectionTypeId()+","+votingTrendz.getOrderNo());
				for(String pstr : partiesList)
					strTemp.append(","+getPartiesResultFromVTPRList(VTPRList,votingTrendz.getVotingTrendzId(),pstr));
				strTemp.append(");\n");
				}catch(Exception e)
				{
					LOG.error("Exception occured in inserting parties_voting_trendz with - "+votingTrendz.getVotingTrendzId());
					LOG.error("Exception is - "+e);
				}
			}
			str.append(strTemp);
		}
		
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Parties Voting Trendz Inserts."+e);
	}
	
	LOG.info("parties voting trendz table data Completed...");
	
	try{
		List<PublicationDate> publicationsList = publicationDateDAO.getAll();
		
		if(publicationsList != null && publicationsList.size() > 0)
		{
			StringBuilder strTemp = new StringBuilder();
			for(PublicationDate publicationDate : publicationsList)
			{
				try{
					strTemp.append("INSERT INTO publication_date(publication_date_id,name,month,year) VALUES (");
					strTemp.append(publicationDate.getPublicationDateId()+",'"+publicationDate.getName()+"',"+publicationDate.getMonth()+","+publicationDate.getYear()+");\n");
				}catch(Exception e){	LOG.error(e);	}
			}
			strTemp.append("\n");
			str.append(strTemp);
		}
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Publication Date Inserts."+e);
	}
	
	LOG.info("publication date table data Completed...");
	
	try{
		List<Cadre> cadreList = cadreDAO.getCadreDetailsInAConstituency(1L, constituencyId);
		
		if(cadreList != null && cadreList.size() > 0)
		{
			for(Cadre cadre : cadreList)
			{
				try{
				
					StringBuilder strTemp = new StringBuilder();
					strTemp.append("INSERT INTO user_address(user_address_id,state_id,district_id,constituency_id,tehsil_id,hamlet_id,local_election_body_id,ward_id,booth_id,booth_part_no) VALUES (");
					strTemp.append(cadre.getCurrentAddress().getUserAddressId()+",");
					strTemp.append(cadre.getCurrentAddress().getState() != null ? cadre.getCurrentAddress().getState().getStateId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getDistrict() != null ? cadre.getCurrentAddress().getDistrict().getDistrictId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getConstituency() != null ? cadre.getCurrentAddress().getConstituency().getConstituencyId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getTehsil() != null ? cadre.getCurrentAddress().getTehsil().getTehsilId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getHamlet() != null ? cadre.getCurrentAddress().getHamlet().getHamletId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getLocalElectionBody() != null ? cadre.getCurrentAddress().getLocalElectionBody().getLocalElectionBodyId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getWard() != null ? cadre.getCurrentAddress().getWard().getConstituencyId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getBooth() != null ? cadre.getCurrentAddress().getBooth().getBoothId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getBooth() != null ? cadre.getCurrentAddress().getBooth().getPartNo() : "null");
					strTemp.append(");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exception Occured in inserting user_address table",e);
				}
			}
			str.append("\n");
			
			LOG.info("User Address For Cadre table data Completed...");
			
			for(Cadre cadre : cadreList)
			{
				try{
					StringBuilder strTemp = new StringBuilder();
					strTemp.append("INSERT INTO cadre(cadre_id,firstname,lastname,relative_name,gender,date_of_birth,age,blood_group_id,no_of_family_members,no_of_voters,mobile_no,email,house_no,street_name,user_address_id,education_id,occupation_id,");
					strTemp.append("caste_state_id,member_type,voter_id,image_path,cadre_level_id,cadre_level_value) VALUES (");
					strTemp.append(cadre.getCadreId()+",");
					strTemp.append("'"+cadre.getFirstName()+"',");
					strTemp.append("'"+cadre.getLastName()+"',");
					strTemp.append("'"+cadre.getFatherOrSpouseName()+"',");
					strTemp.append("'"+cadre.getGender()+"',");
					strTemp.append(cadre.getDateOfBirth() != null ? "'"+cadre.getDateOfBirth().toString()+"'," : "null,");
					strTemp.append("null,");
					strTemp.append(cadre.getBloodGroup() != null ? cadre.getBloodGroup().getBloodGroupId()+"," : "null,");
					strTemp.append(cadre.getNoOfFamilyMembers() != null ? cadre.getNoOfFamilyMembers()+"," : "0,");
					strTemp.append(cadre.getNoOfVoters() != null ? cadre.getNoOfVoters()+"," : "0,");
					strTemp.append(cadre.getMobile() != null ? "'"+cadre.getMobile().toString()+"'," : "null,");
					strTemp.append(cadre.getEmail() != null ? "'"+cadre.getEmail()+"'," : "null,");
					strTemp.append(cadre.getCurrentAddress().getHouseNo() != null ? "'"+cadre.getCurrentAddress().getHouseNo()+"'," : "null,");
					strTemp.append(cadre.getCurrentAddress().getStreet() != null ? "'"+cadre.getCurrentAddress().getStreet()+"'," : "null,");
					strTemp.append(cadre.getCurrentAddress().getUserAddressId()+",");
					strTemp.append(cadre.getEducation() != null ? cadre.getEducation().getEduQualificationId()+"," : "null,");
					strTemp.append(cadre.getOccupation() != null ? cadre.getOccupation().getOccupationId()+"," : "null,");
					strTemp.append("null,");
					strTemp.append("'"+cadre.getMemberType()+"',");
					strTemp.append(cadre.getVoter() != null ? cadre.getVoter().getVoterId()+"," : "null,");
					strTemp.append(cadre.getImage() != null && cadre.getImage().trim().length() > 0 ? cadre.getImage()+"," : "null,");
					strTemp.append(cadre.getCadreLevel() != null ? cadre.getCadreLevel().getCadreLevelID()+"," : "null,");
					strTemp.append(cadre.getCadreLevelValue() != null ? cadre.getCadreLevelValue() : "null");
					strTemp.append(");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exception Occured in inserting cadre table",e);
				}
			}
			str.append("\n");
			
			LOG.info("Cadre table data Completed...");
		}
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Cadre Tables Inserts");
	}
	
	try{
		List<InfluencingPeoplePosition> influencingPeoplePostionsList = influencingPeoplePositionDAO.getPositionNameByUserId(1L);
		
		if(influencingPeoplePostionsList != null && influencingPeoplePostionsList.size() > 0)
		{
			for(InfluencingPeoplePosition position : influencingPeoplePostionsList)
			{
				try{
					StringBuilder strTemp = new StringBuilder();
					strTemp.append("INSERT INTO influencing_people_position(influencing_people_position_id,position_type) VALUES (");
					strTemp.append(position.getInfluencingPeoplePositionId()+",");
					strTemp.append(position.getPosition() != null ? "'"+position.getPosition()+"'" : "NULL");
					strTemp.append(");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exception Occured in Creating Influencing People Position Table Data");
					LOG.error("Exception is - ",e);
				}
			}
			str.append("\n");
			LOG.info("Influencing People Position Table Data Completed...");
		}
	}catch(Exception e)
	{
		LOG.error("Exception Ocuured in Inserting Influencing People Position Table");
	}
	
	try{
		List<InfluencingPeople> influencingPeopleList = influencingPeopleDAO.getInfluencingPeopleInAConstituencyForAUser(1L,constituencyId);
		
		if(influencingPeopleList != null && influencingPeopleList.size() > 0)
		{
			Map<String,Long> levelsMap = getCadreValuesMap();
			for(InfluencingPeople people : influencingPeopleList)
			{
				try{
					StringBuilder strTemp = new StringBuilder();
					strTemp.append("INSERT INTO user_address(user_address_id,state_id,district_id,constituency_id,tehsil_id,hamlet_id,local_election_body_id,ward_id,booth_id,booth_part_no) VALUES (");
					strTemp.append(people.getUserAddress().getUserAddressId()+",");
					strTemp.append(people.getUserAddress().getState() != null ? people.getUserAddress().getState().getStateId()+"," : "null,");
					strTemp.append(people.getUserAddress().getDistrict() != null ? people.getUserAddress().getDistrict().getDistrictId()+"," : "null,");
					strTemp.append(people.getUserAddress().getConstituency() != null ? people.getUserAddress().getConstituency().getConstituencyId()+"," : "null,");
					strTemp.append(people.getUserAddress().getTehsil() != null ? people.getUserAddress().getTehsil().getTehsilId()+"," : "null,");
					strTemp.append(people.getUserAddress().getHamlet() != null ? people.getUserAddress().getHamlet().getHamletId()+"," : "null,");
					strTemp.append(people.getUserAddress().getLocalElectionBody() != null ? people.getUserAddress().getLocalElectionBody().getLocalElectionBodyId()+"," : "null,");
					strTemp.append(people.getUserAddress().getWard() != null ? people.getUserAddress().getWard().getConstituencyId()+"," : "null,");
					strTemp.append(people.getUserAddress().getBooth() != null ? people.getUserAddress().getBooth().getBoothId()+"," : "null,");
					strTemp.append(people.getUserAddress().getBooth() != null ? people.getUserAddress().getBooth().getPartNo(): "null");
					strTemp.append(");\n");
					str.append(strTemp);
					
				}catch(Exception e)
				{
					LOG.error("Exception occured in Creating User Address table for Influencing People, Exception is - ",e);
				}
			}
			
			str.append("\n");
			LOG.info("User Address table for Influencing people data Completed...");
			
			for(InfluencingPeople people : influencingPeopleList)
			{
				try{
					StringBuilder strTemp = new StringBuilder();
					strTemp.append("INSERT INTO influencing_people(influencing_people_id,firstname,lastname,relative_name,gender,mobile,email,house_no,street_name,pincode,user_address_id,occupation_id,party_id,influencing_position_id,caste_state_id,influencing_scope_id,influencing_scope_value,voter_id) VALUES (");
					strTemp.append(people.getInfluencingPeopleId()+",");
					strTemp.append(people.getFirstName() != null ? "'"+people.getFirstName()+"'," : "NULL,");
					strTemp.append(people.getLastName() != null ? "'"+people.getLastName()+"'," : "NULL,");
					strTemp.append(people.getFatherOrSpouseName() != null ? "'"+people.getFatherOrSpouseName()+"'," : "NULL,");
					strTemp.append(people.getGender() != null ? "'"+people.getGender()+"'," : "NULL,");
					strTemp.append(people.getPhoneNo() != null ? "'"+people.getPhoneNo()+"'," : "NULL,");
					strTemp.append(people.getEmail() != null ? "'"+people.getEmail()+"'," : "NULL,");
					strTemp.append(people.getUserAddress() != null && people.getUserAddress().getHouseNo() != null ? "'"+people.getUserAddress().getHouseNo() +"'," : "NULL,");
					strTemp.append(people.getUserAddress() != null && people.getUserAddress().getStreet() != null ? "'"+people.getUserAddress().getStreet() +"'," : "NULL,");
					strTemp.append(people.getUserAddress() != null && people.getUserAddress().getPinCode() != null ? "'"+people.getUserAddress().getPinCode() +"'," : "NULL,");
					strTemp.append(people.getUserAddress() != null ? people.getUserAddress().getUserAddressId()+"," : "NULL,");
					strTemp.append(people.getOccupation() != null ? people.getOccupation()+"," : "NULL,");
					strTemp.append(people.getParty() != null ? people.getParty().getPartyId()+"," : "NULL,");
					strTemp.append(people.getInfluencingPeoplePosition() != null ? people.getInfluencingPeoplePosition().getInfluencingPeoplePositionId()+"," : "NULL,");
					strTemp.append("null,");
					strTemp.append(people.getInfluencingScope() != null ? levelsMap.get(people.getInfluencingScope())+"," : "NULL,");
					strTemp.append(people.getInfluencingScopeValue() != null ? people.getInfluencingScopeValue()+"," : "NULL,");
					strTemp.append(people.getVoter() != null ? people.getVoter().getVoterId()+"" : "NULL");
					strTemp.append(");\n");
					str.append(strTemp);
					
				}catch(Exception e)
				{
					LOG.error("Exception occured in Creating User Address table for Influencing People, Exception is - ",e);
				}
			}
			str.append("\n");
			LOG.info("Influencing people table data Completed...");
		}
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Influencing People & User Address Table Inserting");
		LOG.error("Exception is - ",e);
	}
	
	
	
	try
	{	
		outPut.write(str.toString());
		outPut.close();
		resultStatus.setResultCode(0);
	}catch(Exception e)
	{
		LOG.error("Exception ocuured in writing output - exception is ",e);
	}
	 return resultStatus;
	}catch (Exception e) {
	 e.printStackTrace();
	 LOG.error("Exception Occured in createDataDumpFileForSelectedConstituency() method, Exception - "+e);
	 resultStatus.setResultCode(1);
	 return resultStatus;
	}
 }
 
  	
  	public Long getPartiesResultFromVTPRList(List<VotingTrendzPartiesResult> VTPRList, Long votingTrendzId, String partyName)
  	{
  		try{
  			for(VotingTrendzPartiesResult votingTrendzPartiesResult : VTPRList)
  			{
  				try{
  				if(votingTrendzPartiesResult.getVotingTrendz().getVotingTrendzId().equals(votingTrendzId) &&
  					votingTrendzPartiesResult.getParty().getShortName().equalsIgnoreCase(partyName))
  					return votingTrendzPartiesResult.getVotesGained();
  				}catch(Exception e){}
  			}
  			return 0L;
  		}catch(Exception e)
  		{
  			LOG.error("Exception occured in getPartiesResultFromVTPRList Method, with votingTrendzId = "+votingTrendzId);
  			LOG.error("Exception is",e);
  			return 0L;
  		}
  	}
  	
  	public Map<String,Long> getCadreValuesMap()
  	{
  		Map<String,Long> map = new HashMap<String, Long>(0);
  		try{
  			List<CadreLevel> levelsList = cadreLevelDAO.getCadreLevelList();
  			
  			if(levelsList != null && levelsList.size() > 0)
  			{
  				for(CadreLevel level : levelsList)
  				map.put(level.getLevel(),level.getCadreLevelID());
  			}
  			return map;
  		}catch(Exception e)
  		{
  			LOG.error("Exception Occured in getCadreValuesMap(), Exception is - ",e);
  			return map;
  		}
  	}
  	
  	public ResultStatus saveUserData(final RegistrationVO registrationVO)
  	{
  		final DateUtilService dateUtilService = new DateUtilService();
  		ResultStatus rs = new ResultStatus();
  		ResultStatus resultStatus = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				ResultStatus rs = new ResultStatus();
		try{
			MobileAppUser mobileAppUser = new MobileAppUser();
  			MobileAppUserAccess mobileAppUserAccess = new MobileAppUserAccess();
  			MobileAppUserProfile mobileAppUserProfile = new MobileAppUserProfile();
  			mobileAppUser.setUserName(registrationVO.getUserName());
  			mobileAppUser.setPassword(registrationVO.getPassword());
  			mobileAppUser.setUniqueCode(registrationVO.getUserType());
  			mobileAppUser = mobileAppUserDAO.save(mobileAppUser);
  			mobileAppUserAccess.setMobileAppUser(mobileAppUser);
  			mobileAppUserAccess.setIsAuthorised("true");
  			mobileAppUserAccess.setAppId(registrationVO.getAppId());
  			mobileAppUserAccess.setMacAddress(registrationVO.getAddress());
  			mobileAppUserAccess.setDeviceId(registrationVO.getMobile());
  			mobileAppUserAccess.setLastAuthorisedTime(dateUtilService.getCurrentDateAndTime());
  			mobileAppUserAccessDAO.save(mobileAppUserAccess);
  			mobileAppUserProfile.setFirstName(registrationVO.getFirstName());
  			mobileAppUserProfile.setLastName(registrationVO.getLastName());
  			mobileAppUserProfile.setMobileAppUser(mobileAppUser);
  			mobileAppUserProfile.setGender(registrationVO.getGender().toString());
  			mobileAppUserProfileDAO.save(mobileAppUserProfile);
  			rs.setResultCode(ResultCodeMapper.SUCCESS);
  			}
  		catch (Exception e) {
  			LOG.error("Exception Occured in saveUserData(), Exception is - ",e);
			e.printStackTrace();
			rs.setResultCode(ResultCodeMapper.FAILURE);
		}
		return rs;
		}
		});
  		return resultStatus;
  	}
    
}
