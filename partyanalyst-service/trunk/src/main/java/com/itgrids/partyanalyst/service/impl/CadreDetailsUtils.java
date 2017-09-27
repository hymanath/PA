package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPublicRepresentativeDAO;
import com.itgrids.partyanalyst.dao.IPublicRepresentativeTypeDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpBasicCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolRolesDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolsDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.ITdpRolesDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.service.ICadreDetailsUtils;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreDetailsUtils implements ICadreDetailsUtils{
	
	private static final Logger   LOG = Logger.getLogger(CadreCommitteeService.class);
	private DateUtilService dateUtilService = new DateUtilService();
	private IStateDAO 						stateDAO;
	private IDistrictDAO 					districtDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO 
											delimitationConstituencyAssemblyDetailsDAO;
	private IDelimitationConstituencyDAO 	delimitationConstituencyDAO;
	private IConstituencyDAO 				constituencyDAO;
	private ILocalElectionBodyDAO 			localElectionBodyDAO;
	private ITehsilDAO 						tehsilDAO;
	private IPanchayatDAO 					panchayatDAO;
	private IBoothDAO                       boothDAO;
	
	private IVoterDAO						voterDAO;
	private ITdpCadreDAO					tdpCadreDAO;
	
	private ICadreCommitteeRoleDAO 			cadreCommitteeRoleDAO;
	private ITdpCommitteeDAO 				tdpCommitteeDAO;
	private ITdpCommitteeRoleDAO 			tdpCommitteeRoleDAO;
	private ITdpCommitteeMemberDAO 			tdpCommitteeMemberDAO;
	private ITdpCommitteeElectrolsDAO 		tdpCommitteeElectrolsDAO;
	private ITdpCommitteeElectrolRolesDAO 	tdpCommitteeElectrolRolesDAO;
	private ITdpBasicCommitteeDAO			tdpBasicCommitteeDAO;
	private ITdpCommitteeLevelDAO 			tdpCommitteeLevelDAO;
	private ITdpRolesDAO 					tdpRolesDAO;
	private IPublicRepresentativeTypeDAO 	publicRepresentativeTypeDAO;
	private IPublicRepresentativeDAO 		publicRepresentativeDAO;
	private ITdpCadreCandidateDAO 			tdpCadreCandidateDAO;
	private IDelimitationConstituencyMandalDAO 			delimitationConstituencyMandalDAO;
	
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}
	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public IStateDAO getStateDAO() {
		return stateDAO;
	}
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}
	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	public ICadreCommitteeRoleDAO getCadreCommitteeRoleDAO() {
		return cadreCommitteeRoleDAO;
	}
	public void setCadreCommitteeRoleDAO(
			ICadreCommitteeRoleDAO cadreCommitteeRoleDAO) {
		this.cadreCommitteeRoleDAO = cadreCommitteeRoleDAO;
	}
	public ITdpCommitteeDAO getTdpCommitteeDAO() {
		return tdpCommitteeDAO;
	}
	public void setTdpCommitteeDAO(ITdpCommitteeDAO tdpCommitteeDAO) {
		this.tdpCommitteeDAO = tdpCommitteeDAO;
	}
	public ITdpCommitteeRoleDAO getTdpCommitteeRoleDAO() {
		return tdpCommitteeRoleDAO;
	}
	public void setTdpCommitteeRoleDAO(ITdpCommitteeRoleDAO tdpCommitteeRoleDAO) {
		this.tdpCommitteeRoleDAO = tdpCommitteeRoleDAO;
	}
	public ITdpCommitteeMemberDAO getTdpCommitteeMemberDAO() {
		return tdpCommitteeMemberDAO;
	}
	public void setTdpCommitteeMemberDAO(
			ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
		this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
	}
	public ITdpCommitteeElectrolsDAO getTdpCommitteeElectrolsDAO() {
		return tdpCommitteeElectrolsDAO;
	}
	public void setTdpCommitteeElectrolsDAO(
			ITdpCommitteeElectrolsDAO tdpCommitteeElectrolsDAO) {
		this.tdpCommitteeElectrolsDAO = tdpCommitteeElectrolsDAO;
	}
	public ITdpCommitteeElectrolRolesDAO getTdpCommitteeElectrolRolesDAO() {
		return tdpCommitteeElectrolRolesDAO;
	}
	public void setTdpCommitteeElectrolRolesDAO(
			ITdpCommitteeElectrolRolesDAO tdpCommitteeElectrolRolesDAO) {
		this.tdpCommitteeElectrolRolesDAO = tdpCommitteeElectrolRolesDAO;
	}
	public ITdpBasicCommitteeDAO getTdpBasicCommitteeDAO() {
		return tdpBasicCommitteeDAO;
	}
	public void setTdpBasicCommitteeDAO(ITdpBasicCommitteeDAO tdpBasicCommitteeDAO) {
		this.tdpBasicCommitteeDAO = tdpBasicCommitteeDAO;
	}
	public ITdpCommitteeLevelDAO getTdpCommitteeLevelDAO() {
		return tdpCommitteeLevelDAO;
	}
	public void setTdpCommitteeLevelDAO(ITdpCommitteeLevelDAO tdpCommitteeLevelDAO) {
		this.tdpCommitteeLevelDAO = tdpCommitteeLevelDAO;
	}
	public ITdpRolesDAO getTdpRolesDAO() {
		return tdpRolesDAO;
	}
	public void setTdpRolesDAO(ITdpRolesDAO tdpRolesDAO) {
		this.tdpRolesDAO = tdpRolesDAO;
	}
	public IPublicRepresentativeTypeDAO getPublicRepresentativeTypeDAO() {
		return publicRepresentativeTypeDAO;
	}
	public void setPublicRepresentativeTypeDAO(
			IPublicRepresentativeTypeDAO publicRepresentativeTypeDAO) {
		this.publicRepresentativeTypeDAO = publicRepresentativeTypeDAO;
	}
	public IPublicRepresentativeDAO getPublicRepresentativeDAO() {
		return publicRepresentativeDAO;
	}
	public void setPublicRepresentativeDAO(
			IPublicRepresentativeDAO publicRepresentativeDAO) {
		this.publicRepresentativeDAO = publicRepresentativeDAO;
	}
	public ITdpCadreCandidateDAO getTdpCadreCandidateDAO() {
		return tdpCadreCandidateDAO;
	}
	public void setTdpCadreCandidateDAO(ITdpCadreCandidateDAO tdpCadreCandidateDAO) {
		this.tdpCadreCandidateDAO = tdpCadreCandidateDAO;
	}
	
	public Map<Long,List<IdNameVO>> getAreaWiseList(String searchType,Long stateId,String stateType,boolean isRequuredAreaNo)
	{
		 Map<Long,List<IdNameVO>> returnInfoMap = new LinkedHashMap<Long, List<IdNameVO>>(0);
		try {
			
			if(searchType != null)
			{
				Map<Long,Long> constiNoMap = new LinkedHashMap<Long,Long>(0);
				if(isRequuredAreaNo)
				{
					 List<Object[]> constiNoList = delimitationConstituencyDAO.getConstiNoByConstiId(2009L);
					 if(constiNoList != null && constiNoList.size()>0)
					 {
						 for(Object[] data : constiNoList)
						 {
							 constiNoMap.put(data[0] != null ? Long.valueOf(data[0].toString().trim()):0L, data[1] != null ? Long.valueOf(data[1].toString().trim()):0L);
						 }
					 }
				}
				
				if(!searchType.isEmpty() && searchType.trim().equalsIgnoreCase("Parliament"))
				{
					List<Object[]> parliaments = delimitationConstituencyAssemblyDetailsDAO.getLatestParliamentByStateIdForregion(searchType, stateId, stateType);
					 if(parliaments != null && parliaments.size()>0)
					 {
						 for (Object[] parliament : parliaments) {
							 Long parliamentId = parliament[0] != null ? Long.valueOf(parliament[0].toString().trim()):0L;
							 List<IdNameVO> parliamentInfoList = new ArrayList<IdNameVO>();
							 if(returnInfoMap.get(parliamentId) != null)
							 {
								 parliamentInfoList = returnInfoMap.get(parliamentId);
							 }
							 Long constituencyNo =  null;
							 if(isRequuredAreaNo)
								 constituencyNo = constiNoMap.get(parliamentId);//delimitationConstituencyDAO.getConstituencyNo(parliamentId,2009L);
							 
							 IdNameVO parliamentVO = new IdNameVO();
							 parliamentVO.setId(parliamentId);
							 parliamentVO.setName(parliament[1] != null ?parliament[1].toString().trim():"");
							 parliamentVO.setDistrictid(parliament[3] != null ? Long.valueOf(parliament[3].toString().trim()):0L);
							 if(constituencyNo != null)
							 { 
								 parliamentVO.setName(constituencyNo+"_"+parliamentVO.getName());
							 }
							 parliamentInfoList.add(parliamentVO);
							 IdNameVO districtVO = new IdNameVO();
							 districtVO.setId(parliamentId);
							 districtVO.setName(parliament[2] != null ? parliament[2].toString().trim():"");
							 districtVO.setDistrictid(parliament[3] != null ? Long.valueOf(parliament[3].toString().trim()):0L);
							 parliamentInfoList.add(districtVO);
							 
							 returnInfoMap.put(parliamentId, parliamentInfoList);
							 
						}
					 }
				}
				else if(!searchType.isEmpty() && searchType.trim().equalsIgnoreCase("Assembly"))
				{
					 List<Object[]> assemblys = constituencyDAO.getLatestConstituenciesByStateIdForregion(searchType, stateId, stateType,null,null);
					 if(assemblys != null && assemblys.size()>0)
					 {
						 for (Object[] assembly : assemblys) {
							 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
							 Long constituencyNo =  null;
							 if(isRequuredAreaNo)
								 constiNoMap.get(assemblyId);;//delimitationConstituencyDAO.getConstituencyNo(assemblyId,2009L);
							 List<IdNameVO> assemblyInfoList = new ArrayList<IdNameVO>();
							 if(returnInfoMap.get(assemblyId) != null)
							 {
								 assemblyInfoList = returnInfoMap.get(assemblyId);
							 }
							 
							 IdNameVO parliamentVO = new IdNameVO();
							 parliamentVO.setId(assemblyId);
							 parliamentVO.setName(assembly[1] != null ? assembly[1].toString().trim():"");
							 parliamentVO.setDistrictid(assembly[3] != null ? Long.valueOf(assembly[3].toString().trim()):0L);
							 if(constituencyNo != null)
							 { 
								 parliamentVO.setName(constituencyNo+"_"+parliamentVO.getName());
							 }
							 assemblyInfoList.add(parliamentVO);
							 
							 IdNameVO districtVO = new IdNameVO();
							 districtVO.setId(assemblyId);
							 districtVO.setName(assembly[2] != null ? assembly[2].toString().trim():"");
							 districtVO.setDistrictid(assembly[3] != null ? Long.valueOf(assembly[3].toString().trim()):0L);
							 assemblyInfoList.add(districtVO);
							 
							 returnInfoMap.put(assemblyId, assemblyInfoList);
							 
						}
					 }
				}
				else if(!searchType.isEmpty() && searchType.trim().equalsIgnoreCase("MPTCORZPTC"))
				{
					List<Integer> availableMPTCZPTCLocations = publicRepresentativeDAO.getRepresentativesByPositions(null,new ArrayList<Long>(0),IConstants.MPTC_ELCTION_TYPE_ID); // MPTC,ZPTC,MPP Locations
					 List<Long> areaIdsList = new ArrayList<Long>(0);
					 if(availableMPTCZPTCLocations !=null && availableMPTCZPTCLocations.size()>0)
					 {
						 for (Integer locationId : availableMPTCZPTCLocations) {
							 areaIdsList.add(Long.valueOf(String.valueOf(locationId)));
						}
					 }
					 
					List<Long> tehsilIdsList =  delimitationConstituencyMandalDAO.getStateWideDelimitationConstituencyIdsList(1L);
					List<Object[]> MPTCZPTCAreaList = constituencyDAO.getMPTCZPTCLocationAreaDetails(areaIdsList,tehsilIdsList);
					 
					 if(MPTCZPTCAreaList != null && MPTCZPTCAreaList.size()>0)
					 {
						 for (Object[] assembly : MPTCZPTCAreaList) {
							 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
							 Long constituencyNo =  null;// constiNoMap.get(assemblyId);//delimitationConstituencyDAO.getConstituencyNo(assemblyId,2009L);
							 List<IdNameVO> assemblyInfoList = new ArrayList<IdNameVO>();
							 if(returnInfoMap.get(assembly[6] != null ? Long.valueOf(assembly[6].toString().trim()):0L) != null)
							 {
								 assemblyInfoList = returnInfoMap.get(assembly[6] != null ? Long.valueOf(assembly[6].toString().trim()):0L);
							 }
							 
							 IdNameVO assemblyVO = new IdNameVO();
							 assemblyVO.setId(assemblyId);
							 assemblyVO.setName(assembly[1] != null ? assembly[1].toString().trim():"");
							 assemblyVO.setDistrictid(assembly[2] != null ? Long.valueOf(assembly[2].toString().trim()):0L);
							 if(constituencyNo != null)
							 { 
								 assemblyVO.setName(constituencyNo+"_"+assemblyVO.getName());
							 }
							 assemblyInfoList.add(assemblyVO);
							 
							 IdNameVO districtVO = new IdNameVO();
							 districtVO.setId(assembly[2] != null ? Long.valueOf(assembly[2].toString().trim()):0L);
							 districtVO.setName(assembly[3] != null ? assembly[3].toString().trim():"");
							 districtVO.setDistrictid(assembly[2] != null ? Long.valueOf(assembly[2].toString().trim()):0L);
							 assemblyInfoList.add(districtVO);
							 
							 IdNameVO mandalVO = new IdNameVO();
							 mandalVO.setId(assembly[4] != null ? Long.valueOf(assembly[4].toString().trim()):0L);
							 mandalVO.setName(assembly[5] != null ? assembly[5].toString().trim():"");
							 mandalVO.setDistrictid(assembly[2] != null ? Long.valueOf(assembly[2].toString().trim()):0L);
							 assemblyInfoList.add(mandalVO);
							 
							 returnInfoMap.put(assembly[6] != null ? Long.valueOf(assembly[6].toString().trim()):0L, assemblyInfoList);
						}
					 }
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getAreaWiseList() method, Exception - ",e);
		}
		
		return returnInfoMap;
	}
	
	
	public String getCommitteeLocationNameByLocationTypeAndId(Long committeeLevelId,Long locationValue)
	{
		String location = null;
		try {
			if(locationValue != null && locationValue.longValue()>0L)
			{
				if(committeeLevelId.longValue() == IConstants.VILLAGE_COMMITTEE_LEVEL_ID)
				{
					location = panchayatDAO.get(locationValue).getPanchayatName()+" Panchayat ";
				}
				else if(committeeLevelId.longValue() == IConstants.WARD_COMMITTEE_LEVEL_ID)
				{
					Constituency constituency = constituencyDAO.get(locationValue);
		    		location = constituency.getName()+" ( "+constituency.getLocalElectionBody().getName()+" "+constituency.getLocalElectionBody().getElectionType().getElectionType()+" )";
				}
				else if(committeeLevelId.longValue() == IConstants.MANDAL_COMMITTEE_LEVEL_ID)
				{
					location = tehsilDAO.get(locationValue).getTehsilName()+" Mandal ";
				}
				else if(committeeLevelId.longValue() == IConstants.TOWN_COMMITTEE_LEVEL_ID )
				{
					LocalElectionBody localbody = localElectionBodyDAO.get(locationValue);
		    		if(localbody.getElectionType().getElectionTypeId() != 7L)
		    			location = localbody.getName()+" "+localbody.getElectionType().getElectionType();
				}	
				else if( committeeLevelId.longValue() == IConstants.DIVISION_COMMITTEE_LEVEL_ID)
				{
					Constituency ward = constituencyDAO.get(locationValue);
		    			location = ward.getName()+" ";
				}
				else if(committeeLevelId.longValue() == IConstants.DISTRICT_COMMITTEE_LEVEL_ID)
				{
					location = districtDAO.get(locationValue).getDistrictName();
				}
				else if(committeeLevelId.longValue() == IConstants.STATE_COMMITTEE_LEVEL_ID)
				{
					location = stateDAO.get(locationValue).getStateName();
				}
				else if(committeeLevelId.longValue() == 12L)
				{
					location = " Central Committee ";
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getLocationNameByLocationTypeAndId() method, Exception - ",e);
		}
		
		return location;
	}
	
	public String getPublicRepresentativesLocationNameByLocationTypeAndId(Long levelId,Long locationValue)
	{
		String location = null;
		try {
			/*
			1	PARLIAMENT
			2	ASSEMBLY
			3	MPTC
			4	ZPTC
			5	DISTRICT
			6	STATE
			7	MANDAL
			8	LOCAL ELECTION BODY

			*/
			if(locationValue != null && locationValue.longValue()>0L)
			{
				if(levelId.longValue() == IConstants.ASSEMBLY_ELECTION_TYPE_ID || levelId.longValue() == 1L)
				{
					String type = levelId == 1L?" Parliament":" Assembly";
					Constituency constituency = constituencyDAO.get(locationValue);
		    		location = constituency.getName()+" "+type;
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getPublicRepresentativesLocationNameByLocationTypeAndId() method, Exception - ",e);
		}
		return location;
	}
	
}
