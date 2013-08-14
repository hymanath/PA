package com.itgrids.partyanalyst.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.model.BloodGroup;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.CadreLevel;
import com.itgrids.partyanalyst.model.Caste;
import com.itgrids.partyanalyst.model.CasteCategory;
import com.itgrids.partyanalyst.model.CasteCategoryGroup;
import com.itgrids.partyanalyst.model.ConstituencyHierarchyInfo;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.EducationalQualifications;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.Tehsil;
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
import com.itgrids.partyanalyst.dao.IBloodGroupDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreLevelDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryGroupDAO;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyHierarchyInfoDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterBasicInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dao.IVotingTrendzDAO;
import com.itgrids.partyanalyst.dao.IVotingTrendzPartiesResultDAO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;

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

  public ResultStatus createDataDumpFileForSelectedConstituency(Long constituencyId)
  {
	 ResultStatus resultStatus = new ResultStatus();
	try{
	File f= new File("c://Program Files/Apache Software Foundation/Tomcat 6.0/webapps/PartyAnalyst/sqlTempFile.sql");	
	BufferedWriter outPut = new BufferedWriter(new FileWriter(f));
	StringBuilder str = new StringBuilder();
	ResourceBundle rb = ResourceBundle.getBundle("mobileDBScripts");
	Enumeration<String> keysList =   rb.getKeys();
	
	while(keysList.hasMoreElements())
	{
		str.append(rb.getString(keysList.nextElement()));
		str.append("\n");
	}
	
	str.append("\n \n");
	List<BloodGroup> bloodGroupList = bloodGroupDAO.getBloodGroupList();
	if(bloodGroupList != null && bloodGroupList.size()>0)
	{
	  for(BloodGroup bloodGroup:bloodGroupList)
	  {
	    str.append(" INSERT INTO blood_group(blood_group_id, blood_group) VALUES ('"+bloodGroup.getBloodGroupId()+"','"+bloodGroup.getBloodGroup()+"')");
	    str.append("\n");
	  }
	}
	
	str.append("\n\n");
	
	List<Booth> boothList = boothDAO.getBoothsListByConstituencyId(constituencyId);
	if(boothList != null && boothList.size() > 0)
	{
	  for(Booth booth:boothList)
	  {
		  str.append(" INSERT INTO booth(booth_id,part_no,part_name,location,village_covered,tehsil_id,male_voters,female_voters,total_voters,constituency_id,year,local_election_body_id,publication_date_id,panchayat_id,ward_id) " +
		  		"VALUES ('"+booth.getBoothId()+"','"+booth.getPartNo()+"','"+booth.getPartName()+"','"+booth.getLocation()+"','"+booth.getVillagesCovered()+"','"+booth.getTehsil().getTehsilId()+"','"+booth.getMaleVoters()+"','"+booth.getFemaleVoters()+"'" +
		  				",'"+booth.getTotalVoters()+"','"+booth.getConstituency().getConstituencyId()+"','"+booth.getYear()+"',");
		  if(booth.getLocalBody() != null) 
		   str.append("'"+booth.getLocalBody().getLocalElectionBodyId()+"')");
		  else
			str.append("null");
		  str.append(",'"+booth.getPublicationDate().getPublicationDateId()+"',");
		  
		  if(booth.getPanchayat() != null)
			str.append("'"+booth.getPanchayat().getPanchayatId()+"'");
		  else
			str.append("null");
		  
		  if(booth.getLocalBodyWard() != null)
			str.append(",'"+booth.getLocalBodyWard().getConstituencyId()+"'");
		  else
			str.append("null");
		  
		    str.append("\n");
	  }
	}
	
	str.append("\n\n");
	
	List<CadreLevel> cadreLevelList = cadreLevelDAO.getCadreLevelList();
	if(cadreLevelList != null && cadreLevelList.size() > 0)
	{
	 for(CadreLevel cadreLevel:cadreLevelList)
	 {
	  str.append(" INSERT INTO CadreLevel(cadre_level_id,level,order_no) VALUES ('"+cadreLevel.getCadreLevelID()+"','"+cadreLevel.getLevel()+"',");
	  if(cadreLevel.getOrderNo() != null)
	   str.append("'"+cadreLevel.getOrderNo()+"') ");
	  else
	   str.append("null");
	  str.append("\n");
	 }
	}
	
    str.append("\n\n");
	
	List<Caste> casteList = casteDAO.getCasteList();
	if(casteList != null && casteList.size() > 0)
	{
	  for(Caste caste:casteList)
	  {
		str.append(" INSERT INTO caste(caste_id,caste_name) VALUES ('"+caste.getCasteId()+"','"+caste.getCasteName()+"')");
		str.append("\n");  
	  }
	}
	str.append("\n\n");
	
	
	List<Object[]> casteStateList = casteStateDAO.getCasteStateList();
	if(casteStateList != null && casteStateList.size() > 0)
	{
	 for(Object[] params:casteStateList)
	 {
		str.append(" INSERT INTO caste_state(caste_state_id,state_id,caste_category_group_id,caste_id) VALUES ('"+(Long)params[0]+"','"+(Long)params[1]+"','"+(Long)params[2]+"','"+(Long)params[3]+"')");
		str.append("\n");
	 }
	}
		
	str.append("\n\n");
	 List<CasteCategory> casteCategoryList = casteCategoryDAO.getCasteCategoryList();
	 if(casteCategoryList != null && casteCategoryList.size() > 0)
	 {
	  for(CasteCategory casteCategory:casteCategoryList)
	  {
		str.append(" INSERT INTO caste_category (caste_category_id,category_name,description) VALUES ('"+casteCategory.getCasteCategoryId()+"','"+casteCategory.getCategoryName()+"','"+casteCategory.getDescription()+"')");
		str.append("\n");
	  }
	 }
	 str.append("\n\n");
	 
	 List<ConstituencyHierarchyInfo> constituencyHierarchyInfoList = constituencyHierarchyInfoDAO.getConstituencyHierarchyInfoList(constituencyId, 1L);
	 if(constituencyHierarchyInfoList != null && constituencyHierarchyInfoList.size() > 0)
	 {
		for(ConstituencyHierarchyInfo hierarchyInfo:constituencyHierarchyInfoList)
		{
		 str.append(" INSERT INTO constituency_hierarchy_info(constituency_hierarchy_info_id,constituency_id,mandals,municipalities,panchayats,hamlets,wards,booths,publication_date_id,report_level_id,report_level_value) " +
		 		"VALUES ('"+hierarchyInfo.getConstituencyHierarchyInfoId()+"','"+hierarchyInfo.getConstituencyId()+"','"+hierarchyInfo.getMandals()+"','"+hierarchyInfo.getMunicipalities()+"','"+hierarchyInfo.getPanchayats()+"'" +
		 				",'"+hierarchyInfo.getHamlets()+"','"+hierarchyInfo.getWards()+"','"+hierarchyInfo.getBooths()+"','"+hierarchyInfo.getPublicationDateId()+"','"+hierarchyInfo.getReportLevelId()+"','"+hierarchyInfo.getReportLevelValue()+"')");
		 str.append("\n");	
		}
	 }
	 
	 str.append("\n\n");
	 
	 /*List<Object[]> constituencyList = constituencyDAO.getConstituencyDetails();
	 if(constituencyList != null && constituencyList.size() > 0)
	 {
		for(Object[] params:constituencyList)
		{
		  str.append(" INSERT INTO constituency(constituency_id,name,election_scope_id,state_id,district_id,tehsil_id,local_election_body_id,area_type) " +
			 		"VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"','"+(Long)params[2]+"','"+hierarchyInfo.getMunicipalities()+"','"+hierarchyInfo.getPanchayats()+"'" +
			 				",'"+hierarchyInfo.getHamlets()+"','"+hierarchyInfo.getWards()+"','"+hierarchyInfo.getBooths()+"','"+hierarchyInfo.getPublicationDateId()+"','"+hierarchyInfo.getReportLevelId()+"','"+hierarchyInfo.getReportLevelValue()+"')");
		  str.append("\n");	
		}
	 }
	 str.append("\n\n");*/
	 
	 List<CasteCategoryGroup> list = casteCategoryGroupDAO.getCasteCategoryGroupList();
	 if(list != null && list.size() > 0)
	 {
		for(CasteCategoryGroup casteCategoryGroup:list)
		{
		 str.append(" INSERT INTO caste_category_group(caste_category_group_id,caste_category_id,caste_category_group_name) " +
		 		" VALUES ('"+casteCategoryGroup.getCasteCategoryGroupId()+"','"+casteCategoryGroup.getCasteCategory().getCasteCategoryId()+"','"+casteCategoryGroup.getCasteCategoryGroupName()+"')");
		 str.append("\n");
		}
	 }
	 str.append("\n\n");
	 
	List<Object[]> districtList = districtDAO.getDistrictList();
	if(districtList != null && districtList.size() > 0)
	{
	 for(Object[] params:districtList)
	 {
	  str.append(" INSERT INTO district(district_id,district_name,state_id) VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"','"+(Long)params[2]+"')");
	  str.append(" \n ");
	 }
	}
	str.append("\n\n");
	
	List<EducationalQualifications> eduQualifList = educationalQualificationsDAO.getEducationalQualificationsList();
	if(eduQualifList != null && eduQualifList.size() > 0)
	{
	  for(EducationalQualifications eList:eduQualifList)
	  {
		str.append(" INSERT INTO educational_qualifications(educational_qualification_id,qualification) VALUES ('"+eList.getEduQualificationId()+"','"+eList.getQualification()+"')");
		str.append("\n");
	  }
	}
	str.append("\n\n");
	
	List<ElectionType> electionTypeList = electionTypeDAO.getElectionTypeList();
	if(electionTypeList != null && electionTypeList.size() > 0)
	{
	  for(ElectionType electionType:electionTypeList)
	  {
		str.append(" INSERT INTO election_type(election_type_id,election_type) VALUES ('"+electionType.getElectionTypeId()+"','"+electionType.getElectionType()+"')");
		str.append("\n");
	  }
	}
	str.append("\n\n");
	
	List<Object[]> localElectionBodieList = localElectionBodyDAO.getLocationElectionBodyList();
	if(localElectionBodieList != null && localElectionBodieList.size() > 0)
	{
	  for(Object[] params:localElectionBodieList)
	  {
		 str.append(" INSERT INTO local_election_body(local_election_body_id,name,election_type_id,tehsil_id,district_id,no_of_wards) " +
		 		"VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"','"+(Long)params[2]+"','"+(Long)params[3]+"','"+(Long)params[4]+"') ");
		 str.append("\n");
	  }
	}
	str.append("\n\n");
	
	List<Occupation> occupationList = occupationDAO.getOccupationList();
	if(occupationList != null && occupationList.size() > 0)
	{
	  for(Occupation occupation:occupationList)
	  {
		 str.append(" INSERT INTO occupation(occupation_id,occupation) VALUES ('"+occupation.getOccupationId()+"','"+occupation.getOccupation()+"')");
		 str.append("\n");
	  }
	}
	str.append("\n\n");
	
	List<Object[]> panchayatList = panchayatDAO.getPanchayatDetails();
	if(panchayatList != null && panchayatList.size() > 0)
	{
	  for(Object[] params:panchayatList)
	  {
		str.append(" INSERT INTO panchayat(panchayat_id,panchayat_name,tehsil_id) VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"','"+(Long)params[2]+"')");
		str.append("\n");  
	  }
	}
	
	str.append("\n\n");
	
	List<Object[]> partyList = partyDAO.getPartyShortName();
	if(partyList != null && partyList.size() > 0)
	{
	 for(Object[] params:partyList)
	 {
	  str.append(" INSERT INTO party(party_id,short_name) VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"') ");
	  str.append("\n");
	 }
	}
	
	str.append("\n\n");
	
	List<Object[]> stateList = stateDAO.getStateNames();
	if(stateList != null && stateList.size() > 0)
	{
	  for(Object[] params:stateList)
	  {
		 str.append(" INSERT INTO state(state_id,state_name) VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"') ");
		 str.append("\n");
	  }
	}
	str.append("\n\n");
	
	List<Object[]> tehsilList = tehsilDAO.getTehsilList();
	if(tehsilList != null && tehsilList.size() > 0)
	{
	  for(Object[] params:tehsilList)
	  {
		str.append(" INSERT INTO tehsil(tehsil_id,tehsil_name,district_id) VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"','"+(Long)params[2]+"')");
		str.append("\n");
	  }	
	}
	
	str.append("\n\n");
	
	List<VoterAgeInfo> voterAgeInfoList = voterAgeInfoDAO.getVoterAgeInfoList(constituencyId);
	if(voterAgeInfoList != null && voterAgeInfoList.size() > 0)
	{
	 for(VoterAgeInfo ageInfo:voterAgeInfoList)
	 {
		str.append(" INSERT INTO voter_age_info(voter_age_info_id,report_level_id,report_level_value,age_range_id,total_voters,total_voters_percentage,male_voters,male_voters_percentage,female_voters,female_voters_percentage,publication_date_id,constituency_id) " +
				"VALUES ('"+ageInfo.getVoterAgeInfoId()+"','"+ageInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+ageInfo.getReportLevelValue()+"','"+ageInfo.getVoterAgeRange().getVoterAgeRangeId()+"','"+ageInfo.getTotalVoters()+"','"+ageInfo.getTotalVotersPercentage()+"'" +
						",'"+ageInfo.getMaleVoters()+"','"+ageInfo.getMaleVotersPercentage()+"','"+ageInfo.getFemaleVoters()+"','"+ageInfo.getFemaleVotersPercentage()+"','"+ageInfo.getPublicationDate().getPublicationDateId()+"','"+ageInfo.getConstituencyId()+"')");
		str.append("\n");
	 }
	}
	
	str.append("\n\n");
	
	List<VoterAgeRange> voterAgeRangeList = voterAgeRangeDAO.getVoterAgeRangeList();
	if(voterAgeRangeList != null && voterAgeRangeList.size() > 0)
	{
	  for(VoterAgeRange voterAgeRange:voterAgeRangeList)
	  {
		str.append(" INSERT INTO voter_age_range(voter_age_range_id,age_range) VALUES ('"+voterAgeRange.getVoterAgeRangeId()+"','"+voterAgeRange.getAgeRange()+"')");
		str.append("\n");  
	  }
	}
	str.append("\n\n");
	
	List<VoterBasicInfo> voterBasicInfoList = voterBasicInfoDAO.getVoterBasicInfoList(constituencyId);
	if(voterBasicInfoList != null && voterBasicInfoList.size() > 0)
	{
	  for(VoterBasicInfo basicInfo:voterBasicInfoList)
	  {
		str.append(" INSERT INTO voter_basic_info(voter_basic_info_id,constituency_id,report_level_id,report_level_value,year,booths,total,male,female,total_diff,male_diff,female_diff,type,order_no)" +
				" VALUES ('"+basicInfo.getVoterBasicInfoId()+"','"+basicInfo.getConstituency().getConstituencyId()+"','"+basicInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+basicInfo.getReportLevelValue()+"'" +
						",'"+basicInfo.getYear()+"','"+basicInfo.getBooths()+"','"+basicInfo.getTotalVoters()+"','"+basicInfo.getMaleVoters()+"','"+basicInfo.getFemaleVoters()+"','"+basicInfo.getTotalDiff()+"','"+basicInfo.getMaleDiff()+"'" +
								",'"+basicInfo.getFemaleDiff()+"','"+basicInfo.getType()+"','"+basicInfo.getOrderNo()+"')");
		str.append("\n");
	  }
	}
	str.append("\n\n");
	
	List<VoterCastInfo> voterCasteInfoList = voterCastInfoDAO.getVoterCasteInfoList(constituencyId, 1L);
	if(voterCasteInfoList != null && voterCasteInfoList.size() > 0)
	{
	  for(VoterCastInfo castInfo:voterCasteInfoList)
	  {
		str.append(" INSERT INTO voter_caste_info(voter_cast_info_id,report_level_id,report_level_value,user_id,caste_state_id,caste_voters,caste_male_voters,caste_female_voters,caste_percentage,publication_date_id,constituency_id,sub_leve_caste_percentage) " +
				"VALUES ('"+castInfo.getVoterCastInfoId()+"','"+castInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+castInfo.getReportLevelValue()+"','"+castInfo.getUserId()+"','"+castInfo.getCasteState().getCasteStateId()+"','"+castInfo.getCasteVoters()+"'" +
						",'"+castInfo.getCasteMaleVoters()+"','"+castInfo.getCasteFemaleVoters()+"','"+castInfo.getCastePercentage()+"','"+castInfo.getPublicationDateId()+"','"+castInfo.getConstituency().getConstituencyId()+"'");
		if(castInfo.getSubLeveCastePercentage() != null)
		 str.append(",'"+castInfo.getSubLeveCastePercentage()+"')");
		else
		 str.append(",null)");
		
		str.append("\n");   
	  }
	}
	
	str.append("\n\n");
	
	List<VoterCastBasicInfo> voterCasteList = voterCastBasicInfoDAO.getVoterCastBasicInfoList(constituencyId, 1L);
	if(voterCasteList != null && voterCasteList.size() > 0)
	{
		for(VoterCastBasicInfo basicInfo:voterCasteList)
		{
		  str.append(" INSERT INTO voter_caste_basic_info(voter_cast_basic_info_id,report_level_id,report_level_value,user_id,total_castes,caste_assigned_voters,caste_not_assigned_voters,oc_voters,bc_voters,sc_voters,st_voters,publication_date_id,constituency_id) " +
		  		"VALUES ('"+basicInfo.getVoterCastBasicInfoId()+"','"+basicInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+basicInfo.getReportLevelValue()+"','"+basicInfo.getUserId()+"','"+basicInfo.getTotalCastes()+"','"+basicInfo.getCasteAssignedVoters()+"','"+basicInfo.getCasteNotAssignedVoters()+"'" +
		  				",'"+basicInfo.getOcVoters()+"','"+basicInfo.getBcVoters()+"','"+basicInfo.getScVoters()+"','"+basicInfo.getStVoters()+"','"+basicInfo.getPublicationDateId()+"','"+basicInfo.getConstituency().getConstituencyId()+"') " );
		  str.append("\n"); 
		}
	}
	str.append("\n\n");
	
	List<VoterFamilyInfo> voterFamilyInfoList = voterFamilyInfoDAO.getVoterFamilyInfoList(constituencyId);
	if(voterFamilyInfoList != null && voterFamilyInfoList.size() > 0)
	{
	  for(VoterFamilyInfo familyInfo:voterFamilyInfoList)
	  {
		str.append(" INSERT INTO voter_family_info(voter_family_info_id,report_level_id,report_level_value,family_range_id,total_families,families_percentage,publication_date_id,constituency_id) " +
				"VALUES ('"+familyInfo.getVoterFamilyInfoId()+"','"+familyInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+familyInfo.getReportLevelValue()+"','"+familyInfo.getVoterFamilyRange().getVoterFamilyRangeId()+"'" +
						",'"+familyInfo.getTotalFamilies()+"','"+familyInfo.getFamiliesPercentage()+"','"+familyInfo.getPublicationDate().getPublicationDateId()+"','"+familyInfo.getConstituencyId()+"')");
		str.append("\n");   
	  }
	}
	str.append("\n\n");
	
	List<VoterFamilyRange> familyRangeList = voterFamilyRangeDAO.getVoterFamilyRangeList();
	if(familyRangeList != null && familyRangeList.size() > 0)
	{
	  for(VoterFamilyRange familyRange:familyRangeList)
	  {
		str.append(" INSERT INTO voter_family_range(voter_family_range_id,family_range) VALUES ('"+familyRange.getVoterFamilyRangeId()+"','"+familyRange.getFamilyRange()+"')");
		str.append("\n");  
	  }
	}
	str.append("\n\n");
	
	List<VoterInfo> voterInfoList = voterInfoDAO.getVoterInfoList(constituencyId);
	if(voterInfoList != null && voterInfoList.size() > 0)
	{
	 for(VoterInfo voterInfo:voterInfoList)
	 {
		str.append(" INSERT INTO voter_info(voter_info_id,report_level_id,report_level_value,total_voters,total_voters_percentage,male_voters,male_voters_percentage,female_voters,female_voters_percentage,total_families,families_percentage,publication_date_id,constituency_id) " +
				"VALUES ('"+voterInfo.getVoterInfoId()+"','"+voterInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+voterInfo.getReportLevelValue()+"','"+voterInfo.getTotalVoters()+"',");
		if(voterInfo.getTotalVotersPercentage() != null)
		str.append("'"+voterInfo.getTotalVotersPercentage()+"'");
		else
		 str.append("null");
		str.append(",'"+voterInfo.getMaleVoters()+"','"+voterInfo.getMaleVotersPercentage()+"'" +
						",'"+voterInfo.getFemaleVoters()+"','"+voterInfo.getFemaleVotersPercentage()+"','"+voterInfo.getTotalFamilies()+"','"+voterInfo.getFamiliesPercentage()+"','"+voterInfo.getPublicationDate().getPublicationDateId()+"','"+voterInfo.getConstituencyId()+"')");
		str.append("\n"); 
	 }
	}
	
	str.append("\n\n");
	
	List<VoterReportLevel> reportLevelList = voterReportLevelDAO.getVoterReportLevelList();
	if(reportLevelList != null && reportLevelList.size() > 0)
	{
	  for(VoterReportLevel reportLevel:reportLevelList)
	  {
		str.append(" INSERT INTO voter_report_leve(voter_report_level_id,report_leve,order_no) VALUES ('"+reportLevel.getVoterReportLevelId()+"','"+reportLevel.getReportLevel()+"','"+reportLevel.getOrderNo()+"')");
		str.append("\n");  
	  }
	}
	
	str.append("\n\n");
	
	List<VotingTrendz> votingTrendZList = votingTrendzDAO.getVotingTrendzList(constituencyId);
	if(votingTrendZList != null && votingTrendZList.size() > 0)
	{
	  for(VotingTrendz trendz:votingTrendZList)
	  {
		str.append(" INSERT INTO voting_trendz(voting_trendz_id,constituency_id,report_level_id,report_level_value,election_type,year,total_booths,total_votes,votes_polled,election_type_id,order_no) " +
				"VALUES ('"+trendz.getVotingTrendzId()+"','"+trendz.getConstituency().getConstituencyId()+"','"+trendz.getVoterReportLevel().getVoterReportLevelId()+"','"+trendz.getReportLevelValue()+"','"+trendz.getElectionType().getElectionType()+"'" +
						",'"+trendz.getYear()+"','"+trendz.getTotalBooths()+"','"+trendz.getTotalVotes()+"','"+trendz.getVotesPolled()+"','"+trendz.getElectionType().getElectionTypeId()+"','"+trendz.getOrderNo()+"')");
		str.append("\n");   
	  }
	}
	str.append("\n\n");
	
	List<VotingTrendzPartiesResult> list1 = votingTrendzPartiesResultDAO.getVotingTrendzPartiesResultList();
	if(list1 != null && list1.size() > 0)
	{
	 for(VotingTrendzPartiesResult partiesResult:list1)
	 {
		str.append(" INSERT INTO voting_trendz_parties_result(voting_trendz_parties_result_id,voting_trendz_id,party_id,votes_gained) " +
				"VALUES ('"+partiesResult.getVotingTrendzPartiesResultId()+"','"+partiesResult.getVotingTrendz().getVotingTrendzId()+"','"+partiesResult.getParty().getPartyId()+"','"+partiesResult.getVotesGained()+"')");
		str.append("\n");  
	 }
	}
	
	outPut.write(str.toString());
	outPut.close();
	resultStatus.setResultCode(0);
	 return resultStatus;
	}catch (Exception e) {
	 e.printStackTrace();
	 LOG.error("ExceptionOccured in createDataDumpFileForSelectedConstituency() method, Exception - "+e);
	 resultStatus.setResultCode(1);
	 return resultStatus;
	}
 }
 
}
