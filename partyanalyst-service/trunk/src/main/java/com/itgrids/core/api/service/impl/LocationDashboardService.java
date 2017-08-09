package com.itgrids.core.api.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.core.api.service.ILocationDashboardService;
import com.itgrids.partyanalyst.dao.IActivityDAO;
import com.itgrids.partyanalyst.dao.IBoardLevelDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeCommitteeDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyCensusDetailsDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictConstituenciesDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.IEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.IGovtSchemeBeneficiaryDetailsDAO;
import com.itgrids.partyanalyst.dao.IInsuranceStatusDAO;
import com.itgrids.partyanalyst.dao.INominatedPostApplicationDAO;
import com.itgrids.partyanalyst.dao.INominatedPostDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsNewDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationNewDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationTargetDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalToursMonthDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeEnrollmentDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.BenefitCandidateVO;
import com.itgrids.partyanalyst.dto.BoothInchargesVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CandidateInfoForConstituencyVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.ConstituencyCadreVO;
import com.itgrids.partyanalyst.dto.ElectionInformationVO;
import com.itgrids.partyanalyst.dto.GrivenceStatusVO;
import com.itgrids.partyanalyst.dto.InsuranceStatusCountsVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MeetingsVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.model.CasteCategory;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.EnrollmentYear;
import com.itgrids.partyanalyst.model.PublicationDate;
import com.itgrids.partyanalyst.model.TdpCommitteeEnrollment;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class LocationDashboardService  implements ILocationDashboardService  {
	private final static Logger LOG = Logger.getLogger(LocationDashboardService.class);
	private INominationDAO nominationDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IVoterAgeInfoDAO voterAgeInfoDAO;
	private ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO;
	private IVoterCastInfoDAO voterCastInfoDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IEnrollmentYearDAO enrollmentYearDAO;
	private ICasteCategoryDAO casteCategoryDAO;
	private IPartyMeetingStatusDAO partyMeetingStatusDAO;
	private ITdpCommitteeDAO tdpCommitteeDAO;
	private ICadreDetailsService cadreDetailsService;
	private ITdpCommitteeEnrollmentDAO tdpCommitteeEnrollmentDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
	//tour daos
	private ISelfAppraisalCandidateLocationNewDAO selfAppraisalCandidateLocationNewDAO;
	private ISelfAppraisalDesignationTargetDAO selfAppraisalDesignationTargetDAO;
	private ISelfAppraisalCandidateDetailsNewDAO selfAppraisalCandidateDetailsNewDAO;
	private ISelfAppraisalToursMonthDAO selfAppraisalToursMonthDAO;

	private IGovtSchemeBeneficiaryDetailsDAO govtSchemeBeneficiaryDetailsDAO;
	private ITdpCadreEnrollmentInfoDAO tdpCadreEnrollmentInfoDAO;
	private IInsuranceStatusDAO insuranceStatusDAO;
	private INominatedPostDAO nominatedPostDAO;
	private INominatedPostApplicationDAO nominatedPostApplicationDAO;
	private IBoardLevelDAO boardLevelDAO;
	private IElectionTypeDAO electionTypeDAO;
	private IPublicationDateDAO publicationDateDAO;
	//Activity
	private IActivityDAO activityDAO;
	private IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO;
	private IDistrictDAO districtDAO;
	private IDistrictConstituenciesDAO districtConstituenciesDAO;
	private ICandidateDAO candidateDAO;
	private ICadreCommitteeService cadreCommitteeService;
	private IBoothInchargeCommitteeDAO boothInchargeCommitteeDAO;
	private IBoothInchargeDAO boothInchargeDAO;
	 

	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}

	public IPublicationDateDAO getPublicationDateDAO() {
		return publicationDateDAO;
	}
	public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
		this.publicationDateDAO = publicationDateDAO;
	}
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IElectionTypeDAO getElectionTypeDAO() {
		return electionTypeDAO;
	}
	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}
	public void setConstituencyCensusDetailsDAO(IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO) {
		this.constituencyCensusDetailsDAO = constituencyCensusDetailsDAO;
	}
	public void setCensusDAO(ICensusDAO censusDAO) {
		this.censusDAO = censusDAO;
	}
	private ICensusDAO censusDAO;

	public IConstituencyCensusDetailsDAO getConstituencyCensusDetailsDAO(){
		return constituencyCensusDetailsDAO;

	}
	public ICensusDAO getCensusDAO(){
		return censusDAO;

	}
	public IActivityDAO getActivityDAO() {
		return activityDAO;
	}
	public void setActivityDAO(IActivityDAO activityDAO) {
		this.activityDAO = activityDAO;
	}
	public void setInsuranceStatusDAO(IInsuranceStatusDAO insuranceStatusDAO) {
		this.insuranceStatusDAO = insuranceStatusDAO;
	}
	public void setTdpCommitteeEnrollmentDAO(ITdpCommitteeEnrollmentDAO tdpCommitteeEnrollmentDAO) {
		this.tdpCommitteeEnrollmentDAO = tdpCommitteeEnrollmentDAO;
	}

	public ICadreDetailsService getCadreDetailsService() {
		return cadreDetailsService;
	}

	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}

	public ITdpCommitteeDAO getTdpCommitteeDAO() {
		return tdpCommitteeDAO;
	}

	public void setTdpCommitteeDAO(ITdpCommitteeDAO tdpCommitteeDAO) {
		this.tdpCommitteeDAO = tdpCommitteeDAO;
	}

	public IPartyMeetingStatusDAO getPartyMeetingStatusDAO() {
		return partyMeetingStatusDAO;
	}

	public void setPartyMeetingStatusDAO(IPartyMeetingStatusDAO partyMeetingStatusDAO) {
		this.partyMeetingStatusDAO = partyMeetingStatusDAO;
	}

	public ICasteCategoryDAO getCasteCategoryDAO() {
		return casteCategoryDAO;
	}

	public void setCasteCategoryDAO(ICasteCategoryDAO casteCategoryDAO) {
		this.casteCategoryDAO = casteCategoryDAO;
	}

	public IEnrollmentYearDAO getEnrollmentYearDAO() {
		return enrollmentYearDAO;
	}

	public void setEnrollmentYearDAO(IEnrollmentYearDAO enrollmentYearDAO) {
		this.enrollmentYearDAO = enrollmentYearDAO;
	}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public IVoterCastInfoDAO getVoterCastInfoDAO() {
		return voterCastInfoDAO;
	}

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}

	public ITdpCadreEnrollmentYearDAO getTdpCadreEnrollmentYearDAO() {
		return tdpCadreEnrollmentYearDAO;
	}

	public void setTdpCadreEnrollmentYearDAO(ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO) {
		this.tdpCadreEnrollmentYearDAO = tdpCadreEnrollmentYearDAO;
	}

	public IVoterAgeInfoDAO getVoterAgeInfoDAO() {
		return voterAgeInfoDAO;
	}

	public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
		this.voterAgeInfoDAO = voterAgeInfoDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public INominatedPostDAO getNominatedPostDAO() {
		return nominatedPostDAO;
	}

	public void setNominatedPostDAO(INominatedPostDAO nominatedPostDAO) {
		this.nominatedPostDAO = nominatedPostDAO;
	}

	public INominatedPostApplicationDAO getNominatedPostApplicationDAO() {
		return nominatedPostApplicationDAO;
	}
	public void setSelfAppraisalCandidateLocationNewDAO(
			ISelfAppraisalCandidateLocationNewDAO selfAppraisalCandidateLocationNewDAO) {
		this.selfAppraisalCandidateLocationNewDAO = selfAppraisalCandidateLocationNewDAO;
	}

	public void setSelfAppraisalDesignationTargetDAO(
			ISelfAppraisalDesignationTargetDAO selfAppraisalDesignationTargetDAO) {
		this.selfAppraisalDesignationTargetDAO = selfAppraisalDesignationTargetDAO;
	}

	public void setSelfAppraisalCandidateDetailsNewDAO(
			ISelfAppraisalCandidateDetailsNewDAO selfAppraisalCandidateDetailsNewDAO) {
		this.selfAppraisalCandidateDetailsNewDAO = selfAppraisalCandidateDetailsNewDAO;
	}

	public void setSelfAppraisalToursMonthDAO(
			ISelfAppraisalToursMonthDAO selfAppraisalToursMonthDAO) {
		this.selfAppraisalToursMonthDAO = selfAppraisalToursMonthDAO;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public void setGovtSchemeBeneficiaryDetailsDAO(
			IGovtSchemeBeneficiaryDetailsDAO govtSchemeBeneficiaryDetailsDAO) {
		this.govtSchemeBeneficiaryDetailsDAO = govtSchemeBeneficiaryDetailsDAO;
	}
	public void setTdpCadreEnrollmentInfoDAO(
			ITdpCadreEnrollmentInfoDAO tdpCadreEnrollmentInfoDAO) {
		this.tdpCadreEnrollmentInfoDAO = tdpCadreEnrollmentInfoDAO;
	}

	public void setNominatedPostApplicationDAO(
			INominatedPostApplicationDAO nominatedPostApplicationDAO) {
		this.nominatedPostApplicationDAO = nominatedPostApplicationDAO;
	}

	public IBoardLevelDAO getBoardLevelDAO() {
		return boardLevelDAO;
	}

	public void setBoardLevelDAO(IBoardLevelDAO boardLevelDAO) {
		this.boardLevelDAO = boardLevelDAO;
	}

	public IDistrictConstituenciesDAO getDistrictConstituenciesDAO() {
		return districtConstituenciesDAO;
	}
	public void setDistrictConstituenciesDAO(
			IDistrictConstituenciesDAO districtConstituenciesDAO) {
		this.districtConstituenciesDAO = districtConstituenciesDAO;
	}

	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}
	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}
	public IBoothInchargeCommitteeDAO getBoothInchargeCommitteeDAO() {
		return boothInchargeCommitteeDAO;
	}
	public void setBoothInchargeCommitteeDAO(
			IBoothInchargeCommitteeDAO boothInchargeCommitteeDAO) {
		this.boothInchargeCommitteeDAO = boothInchargeCommitteeDAO;
	}
	public IBoothInchargeDAO getBoothInchargeDAO() {
		return boothInchargeDAO;
	}
	public void setBoothInchargeDAO(IBoothInchargeDAO boothInchargeDAO) {
		this.boothInchargeDAO = boothInchargeDAO;
	}
	
	public CandidateDetailsForConstituencyTypesVO getCandidateAndPartyInfoForConstituency(Long constituencyId) {
		String electionType = "";
		String deformDate = "";
		String isnew = "false";
		CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituencyTypesVO = new CandidateDetailsForConstituencyTypesVO();

		/**
		 * DAO method call to get the election type and delimitation info.If
		 * delimitation info is null returning null.
		 */
		List constituencyTypeDetails = constituencyDAO
				.getConstituencyTypeAndDelimitationInfoByConstituencyId(constituencyId);
		if (constituencyTypeDetails != null && constituencyTypeDetails.size() > 0) {
			Object[] obj = (Object[]) constituencyTypeDetails.get(0);
			electionType = (String) obj[0];
			if (obj[1] != null)
				deformDate = obj[1].toString();
		}
		LOG.info(" Election Type :" + electionType);
		LOG.info(" Deform Date :" + deformDate);
		if (!deformDate.equalsIgnoreCase("") || deformDate == null)
			return null;

		// ---------------
		List candidateList = null;
		if (electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)) {
			candidateList = nominationDAO.getCandidateAndPartyInfoForParliament(constituencyId, electionType, 1L);
			List<String> yearList = nominationDAO.getCandidateAndPartyInfoForParliamentYear(constituencyId,
					electionType);
			if (yearList.size() > 0 && candidateList.size() > 0) {
				Object[] values = (Object[]) candidateList.get(0);
				if (!(yearList.get(0).equalsIgnoreCase(values[11].toString()))) {
					isnew = "true";
				}
			}
		} else {
			/*
			 * Long stateID = consti.getElectionScope().getState().getStateId();
			 * candidateList =
			 * nominationDAO.getCandidateNPartyInfo(constituencyId.toString(),
			 * electionType, 1L, IConstants.ELECTION_SUBTYPE_MAIN, stateID);
			 */
			candidateList = nominationDAO.getCandidateAndPartyInfo(constituencyId, electionType, 1L);
			List<String> yearList = nominationDAO.getCandidateAndPartyInfoYear(constituencyId, electionType);
			if (yearList.size() > 0 && candidateList.size() > 0) {
				Object[] values = (Object[]) candidateList.get(0);
				if (!(yearList.get(0).equalsIgnoreCase(values[11].toString()))) {
					isnew = "true";
				}
			}
		}

		LOG.info("Nomination List :" + candidateList.size());
		if (candidateList.size() == 0)
			return null;
		List<CandidateInfoForConstituencyVO> candidateInfoList = extractCandidateNPartyDataFromList(candidateList);
		LOG.info("Candidate Info :" + candidateInfoList.size());

		if (IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType)) {
			candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(candidateInfoList);

			List list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constituencyId);
			if (list != null && list.size() > 0) {
				Object[] listData = (Object[]) list.get(0);
				Long asemblyId = (Long) listData[0];
				List result = nominationDAO.getParliamentCandidateNPartyInfo(asemblyId,
						IConstants.PARLIAMENT_ELECTION_TYPE, 1L);
				if (result.size() != 0) {
					candidateDetailsForConstituencyTypesVO
					.setParliamentCandidateInfo(extractCandidateNPartyDataFromList(result).get(0));
				} else {
					LOG.error("Assembly candidate data for this constituency is not present");
					candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(null);
				}
			} else {
				candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(null);
			}

		} else if (IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionType)) {
			candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(candidateInfoList.get(0));

			List assembliesData = delimitationConstituencyAssemblyDetailsDAO
					.findAssembliesConstituencies(constituencyId);
			/* Constituency assemConsti = null; */

			if (assembliesData != null && assembliesData.size() > 0) {
				/*
				 * StringBuilder idString = new StringBuilder(); for(int j = 0 ;
				 * j < assembliesData.size() ; j++) { Object[] ids = (Object[])
				 * assembliesData.get(j);
				 * idString.append(IConstants.COMMA).append((Long)ids[0]);
				 * 
				 * if(j == 0) assemConsti = constituencyDAO.get((Long)ids[0]); }
				 * 
				 * if(idString.length() > 0) { Long stateId = 0L; if(assemConsti
				 * != null) stateId =
				 * assemConsti.getElectionScope().getState().getStateId(); List
				 * result =
				 * nominationDAO.getCandidateNPartyInfo(idString.substring(1),
				 * IConstants.ASSEMBLY_ELECTION_TYPE, 1L,
				 * IConstants.ELECTION_SUBTYPE_MAIN,stateId);
				 */

				List<Object[]> result = new ArrayList<Object[]>(0);
				for (int j = 0; j < assembliesData.size(); j++) {
					try {
						Long constId = (Long) ((Object[]) assembliesData.get(j))[0];
						result.add(nominationDAO
								.getCandidateAndPartyInfo(constId, IConstants.ASSEMBLY_ELECTION_TYPE, 1L).get(0));
					} catch (Exception e) {
						// e.printStackTrace();
					}
				}
				if (result.size() != 0) {
					candidateDetailsForConstituencyTypesVO
					.setAssemblyCandidateInfo(extractCandidateNPartyDataFromList(result));
				} else {
					LOG.error("Parliament candidate data for this constituency is not present");
					candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(null);
				}
				/* } */
			} else {
				candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(null);
			}
		}
		candidateDetailsForConstituencyTypesVO.setIspartial(isnew);
		return candidateDetailsForConstituencyTypesVO;
	}

	public List<CandidateInfoForConstituencyVO> extractCandidateNPartyDataFromList(List candidateList) {
		List<CandidateInfoForConstituencyVO> candidateInfoList = new ArrayList<CandidateInfoForConstituencyVO>();

		for (int i = 0; i < candidateList.size(); i++) {
			String candidateFullName = "";
			CandidateInfoForConstituencyVO candidateInfo1 = new CandidateInfoForConstituencyVO();
			Object[] values = (Object[]) candidateList.get(i);
			candidateInfo1.setConstituencyId((Long) values[0]);
			candidateInfo1.setConstituencyName(values[1].toString());
			candidateInfo1.setCandidateId((Long) values[2]);

			if (!StringUtils.isBlank((String) values[3]))
				candidateFullName = candidateFullName + ((String) values[3]) + " ";
			if (!StringUtils.isBlank((String) values[4]))
				candidateFullName = candidateFullName + ((String) values[4]) + " ";
			if (!StringUtils.isBlank((String) values[5]))
				candidateFullName = candidateFullName + ((String) values[5]);

			candidateInfo1.setCandidateName(candidateFullName);
			candidateInfo1.setPartyId((Long) values[6]);
			candidateInfo1.setParty(values[7].toString());
			candidateInfo1.setConstituencyType(values[9].toString());
			if (values[8] == null || values[8].toString().length() == 0)
				candidateInfo1.setDeformDate("");
			else
				candidateInfo1.setDeformDate(values[9].toString());
			if (values[10] != null) {
				candidateInfo1.setPartyFlag(values[10].toString());
			}
			candidateInfo1.setLatestElecYear(values[11].toString());
			candidateInfoList.add(candidateInfo1);
		}

		return candidateInfoList;
	}

	public List<LocationVotersVO> getVotersAndcadreAgeWiseCount(Long constituencyId, Long publicationDateId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			Map<String, LocationVotersVO> map = new LinkedHashMap<String, LocationVotersVO>();
			List<Object[]> votersObjList = voterAgeInfoDAO.getVotersAgeWiseCount(constituencyId, publicationDateId);
			if (votersObjList != null && votersObjList.size() > 0) {
				for (Object[] objects : votersObjList) {
					LocationVotersVO vo = new LocationVotersVO();
					vo.setAgeRangeId((Long) objects[0]);
					vo.setAgeRange(objects[1].toString());
					vo.setTotalVoters(objects[2] != null ? (Long) objects[2] : 0l);
					vo.setTotalVotersPerc(objects[3] != null ? objects[3].toString() + " %" : "");
					vo.setMaleVoters(objects[4] != null ? (Long) objects[4] : 0l);
					vo.setMaleVotersPerc(objects[5] != null ? objects[5].toString() + " %" : "");
					vo.setFemaleVoters(objects[6] != null ? (Long) objects[6] : 0l);
					vo.setFemaleVotersPerc(objects[7] != null ? objects[7].toString() + " %" : "");
					map.put(objects[1].toString(), vo);
				}
			}

			List<Object[]> cadreObjList = tdpCadreEnrollmentYearDAO.getGenderAndAgeGroupWiseCadreCount(constituencyId);
			if (cadreObjList != null && cadreObjList.size() > 0) {
				for (Object[] objects : cadreObjList) {
					if (map.get(objects[1].toString()) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						map.put(objects[1].toString(), inVO);
					}

					if (objects[2].toString().trim().equalsIgnoreCase("M")) {
						map.get(objects[1].toString()).setMaleCadres((Long) objects[3]);
					} else if (objects[2].toString().trim().equalsIgnoreCase("F")) {
						map.get(objects[1].toString()).setFemaleCadres((Long) objects[3]);
					}
				}
			}

			if (map != null && map.size() > 0) {
				LocationVotersVO voForTotalCounts = new LocationVotersVO();
				Long totalCadres = 0l, maleTotalCadres = 0l, femaleTotalCadres = 0l;
				for (Entry<String, LocationVotersVO> entry : map.entrySet()) {
					entry.getValue()
					.setTotalCadres(entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres());
					totalCadres = totalCadres + entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres();
					maleTotalCadres = maleTotalCadres + entry.getValue().getMaleCadres();
					femaleTotalCadres = femaleTotalCadres + entry.getValue().getFemaleCadres();
				}

				for (Entry<String, LocationVotersVO> entry : map.entrySet()) {
					if (totalCadres > 0l)
						entry.getValue()
						.setTotalCadrePerc(((entry.getValue().getTotalCadres() * 100) / totalCadres) + "");
					if (maleTotalCadres > 0l)
						entry.getValue()
						.setMaleCadrePerc(((entry.getValue().getMaleCadres() * 100) / maleTotalCadres) + "");
					if (femaleTotalCadres > 0l)
						entry.getValue().setFemaleCadrePerc(
								((entry.getValue().getFemaleCadres() * 100) / femaleTotalCadres) + "");

					voForTotalCounts
					.setTotalVoters(voForTotalCounts.getTotalVoters() + entry.getValue().getTotalVoters());
					voForTotalCounts
					.setTotalCadres(voForTotalCounts.getTotalCadres() + entry.getValue().getTotalCadres());
					voForTotalCounts.setMaleVoters(voForTotalCounts.getMaleVoters() + entry.getValue().getMaleVoters());
					voForTotalCounts.setMaleCadres(voForTotalCounts.getMaleCadres() + entry.getValue().getMaleCadres());
					voForTotalCounts
					.setFemaleVoters(voForTotalCounts.getFemaleVoters() + entry.getValue().getFemaleVoters());
					voForTotalCounts
					.setFemaleCadres(voForTotalCounts.getFemaleCadres() + entry.getValue().getFemaleCadres());
				}

				voList.addAll(map.values());
				voList.add(voList.size(), voForTotalCounts);
			}

		} catch (Exception e) {
			LOG.error("Exception raised at votersAndcadreAgeWiseCount", e);
		}
		return voList;
	}

	public List<LocationVotersVO> getVotersAndCadreCasteWiseCount(String type, Long constituencyId,
			Long publicationDateId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			if (type.equalsIgnoreCase("voter")) {
				voList = getVotersCasteWiseCount(constituencyId, publicationDateId);
			} else if (type.equalsIgnoreCase("cadre")) {
				voList = getCadreCasteWiseCount(constituencyId);
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getVotersAndCadreCasteWiseCount", e);
		}
		return voList;
	}

	public List<LocationVotersVO> getVotersCasteWiseCount(Long constituencyId, Long publicationId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			// 0-castegroupId,1-castegroup,2-casteId,3-castegroup,4-voterscount,5-percentage
			List<Object[]> votersObjList = voterCastInfoDAO.getVotersCasteWiseCount(constituencyId, publicationId);

			if (votersObjList != null && votersObjList.size() > 0) {
				Map<String, LocationVotersVO> casteGroupMap = new LinkedHashMap<String, LocationVotersVO>();
				Map<String, LocationVotersVO> casteMap = new LinkedHashMap<String, LocationVotersVO>();
				for (Object[] objects : votersObjList) {
					if (casteGroupMap.get(objects[1].toString()) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long) objects[0]);
						inVO.setAgeRange(objects[1].toString());
						inVO.setMaleVotersPerc("casteGroup");
						casteGroupMap.put(objects[1].toString(), inVO);
					}
					LocationVotersVO matchedVO = casteGroupMap.get(objects[1].toString());
					matchedVO.setTotalVoters(matchedVO.getTotalVoters() + (Long) objects[4]);
					matchedVO
					.setTotalVotersPerc(
							matchedVO.getTotalVotersPerc() != ""
							? (Float.parseFloat(matchedVO.getTotalVotersPerc())
									+ Float.parseFloat(objects[5].toString())) + ""
									: objects[5].toString());

					if (casteMap.get(objects[3].toString()) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long) objects[2]);
						inVO.setAgeRange(objects[3].toString());
						inVO.setMaleCadrePerc("caste");
						casteMap.put(objects[3].toString(), inVO);
					}
					LocationVotersVO matchedVO1 = casteMap.get(objects[3].toString());
					matchedVO1.setTotalVoters(matchedVO.getTotalVoters() + (Long) objects[4]);
					matchedVO1
					.setTotalVotersPerc(
							matchedVO1.getTotalVotersPerc() != ""
							? (Float.parseFloat(matchedVO1.getTotalVotersPerc())
									+ Float.parseFloat(objects[5].toString())) + ""
									: objects[5].toString());

				}
				voList.addAll(casteGroupMap.values());
				voList.addAll(casteMap.values());
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getVotersCasteWiseCount", e);
		}
		return voList;
	}

	public List<LocationVotersVO> getCadreCasteWiseCount(Long constituencyId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			// 0-casteCategoryId,1-casteCategory,2-casteId,3-caste,4-cadreCount
			List<Object[]> objList = tdpCadreEnrollmentYearDAO.getCasteWiseCadreCounts(constituencyId);

			if (objList != null && objList.size() > 0) {
				Map<String, LocationVotersVO> casteGroupMap = new LinkedHashMap<String, LocationVotersVO>();
				Map<String, LocationVotersVO> casteMap = new LinkedHashMap<String, LocationVotersVO>();
				Long totalCadreCount = 0l;
				for (Object[] objects : objList) {
					totalCadreCount = totalCadreCount + (Long) objects[4];
					if (casteGroupMap.get(objects[1].toString()) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long) objects[0]);
						inVO.setAgeRange(objects[1].toString());
						inVO.setMaleVotersPerc("casteGroup");
						casteGroupMap.put(objects[1].toString(), inVO);
					}
					LocationVotersVO matchedVO = casteGroupMap.get(objects[1].toString());
					matchedVO.setTotalVoters(matchedVO.getTotalVoters() + (Long) objects[4]);

					if (casteMap.get(objects[3].toString()) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long) objects[2]);
						inVO.setAgeRange(objects[3].toString());
						inVO.setMaleCadrePerc("caste");
						casteMap.put(objects[3].toString(), inVO);
					}
					LocationVotersVO matchedVO1 = casteMap.get(objects[3].toString());
					matchedVO1.setTotalVoters(matchedVO.getTotalVoters() + (Long) objects[4]);

				}

				if (casteGroupMap != null && casteGroupMap.size() > 0 && totalCadreCount > 0) {
					for (Entry<String, LocationVotersVO> entry : casteGroupMap.entrySet()) {
						entry.getValue()
						.setTotalVotersPerc(((entry.getValue().getTotalVoters() * 100) / totalCadreCount) + "");
					}
					voList.addAll(casteGroupMap.values());
				}

				if (casteMap != null && casteMap.size() > 0 && totalCadreCount > 0) {
					for (Entry<String, LocationVotersVO> entry : casteMap.entrySet()) {
						entry.getValue()
						.setTotalVotersPerc(((entry.getValue().getTotalVoters() * 100) / totalCadreCount) + "");
					}
					voList.addAll(casteMap.values());
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCadreCasteWiseCount", e);
		}
		return voList;
	}

	public List<LocationVotersVO> getCasteGroupNAgeWiseVoterNCadreCounts(Long constituencyId, Long publicationDateId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			// 0-castegroupId,1-castegroup,2-casteId,3-castegroup,4-voterscount,5-percentage,6-maleVotersCount,7-femaleVotersCount
			List<Object[]> votersObjList = voterCastInfoDAO.getVotersCasteWiseCount(constituencyId, publicationDateId);

			if (votersObjList != null && votersObjList.size() > 0) {
				for (Object[] objects : votersObjList) {
					LocationVotersVO matchedCGVO = getMatchedVO(voList, (Long) objects[0]);
					if (matchedCGVO == null) {
						matchedCGVO = new LocationVotersVO();
						matchedCGVO.setAgeRangeId((Long) objects[0]);
						matchedCGVO.setAgeRange(objects[1].toString());

						LocationVotersVO casteVO = new LocationVotersVO();
						casteVO.setAgeRangeId((Long) objects[2]);
						casteVO.setAgeRange(objects[3].toString());
						casteVO.setTotalVoters((Long) objects[4]);
						casteVO.setTotalVotersPerc(objects[5].toString());
						casteVO.setMaleVoters((Long) objects[6]);
						casteVO.setFemaleVoters((Long) objects[7]);

						matchedCGVO.getLocationVotersVOList().add(casteVO);

						voList.add(matchedCGVO);
					} else {
						LocationVotersVO casteVO = new LocationVotersVO();
						casteVO.setAgeRangeId((Long) objects[2]);
						casteVO.setAgeRange(objects[3].toString());
						casteVO.setTotalVoters((Long) objects[4]);
						casteVO.setTotalVotersPerc(objects[5].toString());
						casteVO.setMaleVoters((Long) objects[6]);
						casteVO.setFemaleVoters((Long) objects[7]);

						matchedCGVO.getLocationVotersVOList().add(casteVO);
					}
				}
			}

			// 0-casteCategoryId,1-casteCategory,2-casteId,3-caste,4-gender,5-cadreCount
			List<Object[]> cadresObjList = tdpCadreEnrollmentYearDAO.getCasteNGenderWiseCadreCounts(constituencyId);
			if (cadresObjList != null && cadresObjList.size() > 0) {
				for (Object[] objects : cadresObjList) {
					LocationVotersVO matchedCGVO = getMatchedVO(voList, (Long) objects[0]);
					if (matchedCGVO == null) {
						matchedCGVO = new LocationVotersVO();
						matchedCGVO.setAgeRangeId((Long) objects[0]);
						matchedCGVO.setAgeRange(objects[1].toString());

						LocationVotersVO casteVO = new LocationVotersVO();
						casteVO.setAgeRangeId((Long) objects[2]);
						casteVO.setAgeRange(objects[3].toString());
						if (objects[4].toString().equalsIgnoreCase("M")) {
							casteVO.setMaleCadres((Long) objects[5]);
						} else if (objects[4].toString().equalsIgnoreCase("F")) {
							casteVO.setFemaleCadres((Long) objects[5]);
						}

						matchedCGVO.getLocationVotersVOList().add(casteVO);

						voList.add(matchedCGVO);
					} else {
						LocationVotersVO matchedCVO = getMatchedVO(matchedCGVO.getLocationVotersVOList(),
								(Long) objects[2]);
						if (matchedCVO == null) {
							matchedCVO = new LocationVotersVO();
							matchedCVO.setAgeRangeId((Long) objects[2]);
							matchedCVO.setAgeRange(objects[3].toString());
							if (objects[4].toString().equalsIgnoreCase("M")) {
								matchedCVO.setMaleCadres((Long) objects[5]);
							} else if (objects[4].toString().equalsIgnoreCase("F")) {
								matchedCVO.setFemaleCadres((Long) objects[5]);
							}
							matchedCGVO.getLocationVotersVOList().add(matchedCVO);
						} else {
							if (objects[4].toString().equalsIgnoreCase("M")) {
								matchedCVO.setMaleCadres((Long) objects[5]);
							} else if (objects[4].toString().equalsIgnoreCase("F")) {
								matchedCVO.setFemaleCadres((Long) objects[5]);
							}
						}
					}
				}
			}

			// calculating totals and %'s
			if (voList != null && voList.size() > 0) {
				for (LocationVotersVO casteGroupVO : voList) {
					if (casteGroupVO.getLocationVotersVOList() != null
							&& casteGroupVO.getLocationVotersVOList().size() > 0) {
						Long maleTotalVoters = 0l, femaleTotalVoters = 0l, totalCadres = 0l, maleTotalCadres = 0l,
								femaleTotalCadres = 0l;
						for (LocationVotersVO casteVO : casteGroupVO.getLocationVotersVOList()) {
							maleTotalVoters = maleTotalVoters + casteVO.getMaleVoters();
							femaleTotalVoters = femaleTotalVoters + casteVO.getFemaleVoters();
							maleTotalCadres = maleTotalCadres + casteVO.getMaleCadres();
							femaleTotalCadres = femaleTotalCadres + casteVO.getFemaleCadres();
							totalCadres = totalCadres + casteVO.getMaleCadres() + casteVO.getFemaleCadres();
						}
						casteGroupVO.setTotalVoters(maleTotalVoters+femaleTotalVoters);
						casteGroupVO.setTotalCadres(totalCadres);
						for (LocationVotersVO casteVO : casteGroupVO.getLocationVotersVOList()) {
							casteVO.setMaleVotersPerc(((casteVO.getMaleVoters() * 100) / maleTotalVoters) + "%");
							casteVO.setFemaleVotersPerc(((casteVO.getFemaleVoters() * 100) / femaleTotalVoters) + "%");
							casteVO.setTotalCadres(casteVO.getMaleCadres() + casteVO.getFemaleCadres());
							casteVO.setTotalCadrePerc(((casteVO.getTotalCadres() * 100) / totalCadres) + "%");
							casteVO.setMaleCadrePerc(((casteVO.getMaleCadres() * 100) / maleTotalCadres) + "%");
							casteVO.setFemaleCadrePerc(((casteVO.getFemaleCadres() * 100) / femaleTotalCadres) + "%");
						}
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getCasteGroupNAgeWiseVoterNCadreCounts", e);
		}
		return voList;
	}

	public LocationVotersVO getMatchedVO(List<LocationVotersVO> voList, Long id) {
		if (voList != null && voList.size() > 0 && id != null && id > 0l) {
			for (LocationVotersVO locationVotersVO : voList) {
				if (locationVotersVO.getAgeRangeId().equals(id))
					return locationVotersVO;
			}
		}
		return null;
	}

	public List<LocationVotersVO> getCasteNAgeWiseVoterNCadreCounts(Long constituencyId, Long publicationDateId,
			Long casteGroupId, Long casteId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			Map<Long, LocationVotersVO> map = new LinkedHashMap<Long, LocationVotersVO>();

			// 0-ageRangeId,1-ageRange,2-gender,3-votersCount
			List<Object[]> votersObjList = userVoterDetailsDAO.getVotersCasteNAgeGroupWiseCount(casteGroupId, casteId,
					constituencyId, publicationDateId);

			if (votersObjList != null && votersObjList.size() > 0) {
				for (Object[] objects : votersObjList) {
					if (map.get((Long) objects[0]) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long) objects[0]);
						inVO.setAgeRange(objects[1].toString());
						map.put((Long) objects[0], inVO);
					}

					if (objects[2].toString().equalsIgnoreCase("M")) {
						map.get((Long) objects[0]).setMaleVoters((Long) objects[3]);
					} else if (objects[2].toString().equalsIgnoreCase("F")) {
						map.get((Long) objects[0]).setFemaleVoters((Long) objects[3]);
					}

				}
			}

			List<Object[]> cadresObjList = tdpCadreEnrollmentYearDAO.getCadresCasteNAgeGroupWiseCounts(casteGroupId,
					casteId, constituencyId);

			if (cadresObjList != null && cadresObjList.size() > 0) {
				for (Object[] objects : cadresObjList) {
					if (map.get((Long) objects[0]) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long) objects[0]);
						inVO.setAgeRange(objects[1].toString());
						map.put((Long) objects[0], inVO);
					}

					if (objects[2].toString().equalsIgnoreCase("M")) {
						map.get((Long) objects[0]).setMaleCadres((Long) objects[3]);
					} else if (objects[2].toString().equalsIgnoreCase("F")) {
						map.get((Long) objects[0]).setFemaleCadres((Long) objects[3]);
					}
				}
			}

			if (map != null && map.size() > 0) {
				Long totalVoters = 0l, totalMaleVoters = 0l, totalFemaleVoters = 0l, totalCadres = 0l,
						totalMaleCadres = 0l, totalFemaleCadres = 0l;
				for (Entry<Long, LocationVotersVO> entry : map.entrySet()) {
					entry.getValue()
					.setTotalVoters(entry.getValue().getMaleVoters() + entry.getValue().getFemaleVoters());
					entry.getValue()
					.setTotalCadres(entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres());

					totalVoters = totalVoters + entry.getValue().getMaleVoters() + entry.getValue().getFemaleVoters();
					totalMaleVoters = totalMaleVoters + entry.getValue().getMaleVoters();
					totalFemaleVoters = totalFemaleVoters + entry.getValue().getFemaleVoters();
					totalCadres = totalCadres + entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres();
					totalMaleCadres = totalMaleCadres + entry.getValue().getMaleCadres();
					totalFemaleCadres = totalFemaleCadres + entry.getValue().getFemaleCadres();
				}

				for (Entry<Long, LocationVotersVO> entry : map.entrySet()) {
					entry.getValue().setTotalVotersPerc(round(((entry.getValue().getTotalVoters() * 100.00) / totalVoters),2) + "%");
					entry.getValue().setMaleVotersPerc(round(((entry.getValue().getMaleVoters() * 100.00) / totalMaleVoters),2) + "%");
					entry.getValue().setFemaleVotersPerc(round(((entry.getValue().getFemaleVoters() * 100.00) / totalFemaleVoters),2) + "%");
					entry.getValue().setTotalCadrePerc(round(((entry.getValue().getTotalCadres() * 100.00) / totalCadres),2) + "%");
					entry.getValue().setMaleCadrePerc(round(((entry.getValue().getMaleCadres() * 100.00) / totalMaleCadres),2) + "%");
					entry.getValue().setFemaleCadrePerc(round(((entry.getValue().getFemaleCadres() * 100.00) / totalFemaleCadres),2) + "%");
				}

				voList.addAll(map.values());
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCasteNAgeWiseVoterNCadreCounts", e);
		}
		return voList;
	}

	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public List<KeyValueVO> getEnrollmentYearWiseCadres() {
		List<KeyValueVO> voList = new LinkedList<KeyValueVO>();
		try {
			List<Object[]> objList = tdpCadreEnrollmentYearDAO.getEnrollmentYearWiseCadres();

			if (objList != null && objList.size() > 0) {
				Map<Long, List<Long>> resultMap = new LinkedHashMap<Long, List<Long>>();
				for (Object[] objects : objList) {
					if (resultMap.get((Long) objects[0]) == null) {
						resultMap.put((Long) objects[0], new ArrayList<Long>(0));
					}
					resultMap.get((Long) objects[0]).add((Long) objects[1]);
				}

				Set<Long> keySet = resultMap.keySet();
				List<Long> ketList = new ArrayList<Long>(0);
				ketList.addAll(keySet);

				for (int i = 0; i < ketList.size(); i++) {
					List<Long> firstList = resultMap.get(ketList.get(i));
					List<Long> secondList = resultMap.get(ketList.get(i + 1));

					KeyValueVO resultVO = new KeyValueVO();
					resultVO.setId(ketList.get(i));
					resultVO.setCount(Long.parseLong(firstList.size() + ""));

					for (Long long1 : firstList) {
						if (secondList.contains(firstList)) {
							resultVO.setTotalCount(resultVO.getTotalCount() + 1l);
						} else {
							resultVO.setScopeValue(resultVO.getScopeValue() + 1l);
						}
					}

					voList.add(resultVO);
				}

				KeyValueVO lastVO = new KeyValueVO();
				lastVO.setId(ketList.get(ketList.size() - 1));
				lastVO.setCount(Long.parseLong(resultMap.get(ketList.get(ketList.size() - 1)).size() + ""));
				voList.add(lastVO);

				List<EnrollmentYear> enrolmentYear = enrollmentYearDAO.getAll();
				Map<Long, String> enrollmentMap = new HashMap<Long, String>();
				for (EnrollmentYear enrollmentYear : enrolmentYear) {
					enrollmentMap.put(enrollmentYear.getEnrollmentYearId(), enrollmentYear.getDescription());
				}

				if (voList != null && voList.size() > 0) {
					for (KeyValueVO kvv : voList) {
						kvv.setName(enrollmentMap.get(kvv.getId()));
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getEnrollmentYearWiseCadres", e);
		}
		return voList;
	}

	public List<LocationVotersVO> getEnrollmentYearAgeGroupWiseCadres(Long constituencyId, Long enrollmentYearId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			// 0-voterAgeRangeId,1-ageRange,2-gender,3-casteCategoryId,4-categoryName,5-count
			List<Object[]> objList = tdpCadreEnrollmentYearDAO.getEnrollmentYearAgeGroupWiseCadres(constituencyId,
					enrollmentYearId);

			Map<Long, LocationVotersVO> ageRangeMap = new LinkedHashMap<Long, LocationVotersVO>();
			if (objList != null && objList.size() > 0) {
				List<CasteCategory> ccList = casteCategoryDAO.getAll();
				for (Object[] objects : objList) {
					if (ageRangeMap.get((Long) objects[0]) == null) {
						LocationVotersVO vo = new LocationVotersVO();
						vo.setAgeRangeId((Long) objects[0]);
						vo.setAgeRange(objects[1].toString());
						if (objects[2].toString().equalsIgnoreCase("M")) {
							vo.setMaleCadres(vo.getMaleCadres() + (Long) objects[5]);
						} else if (objects[2].toString().equalsIgnoreCase("F")) {
							vo.setFemaleCadres(vo.getFemaleCadres() + (Long) objects[5]);
						}

						vo.setLocationVotersVOList(getCasteCategorySkeleton(ccList));

						LocationVotersVO matchedCasteVO = getMatchedVO(vo.getLocationVotersVOList(), (Long) objects[3]);
						if (matchedCasteVO != null) {
							matchedCasteVO.setTotalCadres((Long) objects[5]);
						}

						ageRangeMap.put((Long) objects[0], vo);
					} else {
						LocationVotersVO vo = ageRangeMap.get((Long) objects[0]);
						if (objects[2].toString().equalsIgnoreCase("M")) {
							vo.setMaleCadres(vo.getMaleCadres() + (Long) objects[5]);
						} else if (objects[2].toString().equalsIgnoreCase("F")) {
							vo.setFemaleCadres(vo.getFemaleCadres() + (Long) objects[5]);
						}

						LocationVotersVO matchedCasteVO = getMatchedVO(vo.getLocationVotersVOList(), (Long) objects[3]);
						if (matchedCasteVO != null) {
							matchedCasteVO.setTotalCadres(matchedCasteVO.getTotalCadres() + (Long) objects[5]);
						}
					}

				}
			}

			if (ageRangeMap != null && ageRangeMap.size() > 0) {
				voList.addAll(ageRangeMap.values());
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getEnrollmentYearAgeGroupWiseCadres", e);
		}
		return voList;
	}

	public List<LocationVotersVO> getCasteCategorySkeleton(List<CasteCategory> ccList) {
		List<LocationVotersVO> voList = new ArrayList<LocationVotersVO>(0);
		if (ccList != null && ccList.size() > 0) {
			for (CasteCategory cc : ccList) {
				LocationVotersVO vo = new LocationVotersVO();
				vo.setAgeRangeId(cc.getCasteCategoryId());
				vo.setAgeRange(cc.getCategoryName());
				voList.add(vo);
			}
		}
		return voList;
	}

	public List<LocationVotersVO> getLocationWiseMeetingsCount(String locationType, Long constituencyId) {
		List<LocationVotersVO> voList = new ArrayList<LocationVotersVO>(0);
		try {
			Map<String, LocationVotersVO> finalMap = new LinkedHashMap<String, LocationVotersVO>();

			finalMap.put("Y", null);
			finalMap.put("N", null);
			finalMap.put("M", null);
			finalMap.put("NU", null);

			// 0-meetingStatus,1-levelId,2-level,3-count
			List<Object[]> objList = partyMeetingStatusDAO.getLocationWiseMeetings(locationType, constituencyId);
			if (objList != null && objList.size() > 0) {
				for (Object[] objects : objList) {
					if (finalMap.get(objects[0].toString()) == null) {
						finalMap.put(objects[0].toString(), getPartyMeetingSkeleton(objects[0].toString()));
					}

					LocationVotersVO matchedLocationVO = getMatchedLocationVO(
							finalMap.get(objects[0].toString()).getLocationVotersVOList(), objects[2].toString());

					if (matchedLocationVO != null) {
						matchedLocationVO.setTotalVoters((Long) objects[3]);
					} else {
						matchedLocationVO = new LocationVotersVO();
						matchedLocationVO.setAgeRangeId((Long) objects[1]);
						matchedLocationVO.setAgeRange(objects[2].toString());
						matchedLocationVO.setTotalVoters((Long) objects[3]);
						finalMap.get(objects[0].toString()).getLocationVotersVOList().add(matchedLocationVO);
					}
				}

				if (finalMap != null && finalMap.size() > 0) {
					voList.addAll(finalMap.values());
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseMeetingsCount", e);
		}
		return voList;
	}

	public LocationVotersVO getMatchedLocationVO(List<LocationVotersVO> voList, String str) {
		if (voList != null && voList.size() > 0) {
			for (LocationVotersVO locationVotersVO : voList) {
				String s[] = locationVotersVO.getAgeRange().split("/");
				for (int i = 0; i < s.length; i++) {
					if (s[i].equalsIgnoreCase(str))
						return locationVotersVO;
				}
			}
		}
		return null;
	}

	public LocationVotersVO getPartyMeetingSkeleton(String type) {
		String type1 = type.equals("Y") ? "YES"
				: type.equals("N") ? "NO" : type.equals("M") ? "MAYBE" : type.equals("NU") ? "NOT UPDATED" : type;

		LocationVotersVO vo = new LocationVotersVO();
		vo.setAgeRange(type1);
		vo.setTotalVotersPerc(type);

		LocationVotersVO vwVO = new LocationVotersVO();
		vwVO.setAgeRangeId(7l);
		vwVO.setAgeRange("Village/Ward");
		vo.getLocationVotersVOList().add(vwVO);

		LocationVotersVO mtdVO = new LocationVotersVO();
		mtdVO.setAgeRangeId(4l);
		mtdVO.setAgeRange("Mandal/Town/Divison");
		vo.getLocationVotersVOList().add(mtdVO);

		LocationVotersVO constVO = new LocationVotersVO();
		constVO.setAgeRangeId(3l);
		constVO.setAgeRange("Constituency");
		vo.getLocationVotersVOList().add(constVO);

		return vo;
	}
	/* @param String locationType
	 * @param Long locationId
	 * @param Long enrollmentId
	 * @author R Nagarjuna Gowd
	 * @return CommitteeBasicVO object
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getLocationWiseCommitteesCount(java.lang.String, java.lang.Long, java.lang.Long)
	 */

	public CommitteeBasicVO getLocationWiseCommitteesCount(String locationType, Long locationId,Long tdpCommitteeEnrollmentYearId) {

		CommitteeBasicVO committeeCounts = new CommitteeBasicVO();
		try {
			// 0-tdp_base_comitteeId,1-levelId,2-levelName,3-committeeConfrimed,4-start
			// Date,5-completed Date
			List<Object[]> objList = tdpCommitteeDAO.getLocationWiseCommittees(locationType, locationId, tdpCommitteeEnrollmentYearId);
			Long mainMandalCompletedCount = 0l;
			Long mainMandalStartCount = 0l;
			Long mainVillageCompletedCount = 0l;
			Long mainVillageStartCount = 0l;
			Long mainMandalNotStarted = 0l;
			Long mainVillageNotStarted = 0l;

			Long affliatedMandalCompletedCount = 0l;
			Long affliatedMandalStartCount = 0l;
			Long affliatedVillageCompletedCount = 0l;
			Long affliatedVillageStartCount = 0l;
			Long affliatedMandalNotStarted = 0l;
			Long affliatedVillageNotStarted = 0l;

			if (objList != null) {
				for (Object[] objects : objList) {
					if (commonMethodsUtilService.getLongValueForObject(objects[0]) == 1l) {
						if (commonMethodsUtilService.getLongValueForObject(objects[1]) == 5l || commonMethodsUtilService.getLongValueForObject(objects[1]) == 7l ||commonMethodsUtilService.getLongValueForObject(objects[1]) == 9l) {
							if (commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("Y")) {
								mainMandalCompletedCount++;
							} else if(commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("N") && objects[4]!=null && objects[5]==null  ) {
								mainMandalStartCount++;
							}else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								mainMandalNotStarted++;
							}

						} else if (commonMethodsUtilService.getLongValueForObject(objects[1]) == 6l || commonMethodsUtilService.getLongValueForObject(objects[1]) == 8l) {
							if (commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("Y")) {
								mainVillageCompletedCount++;
							} else if(commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("N") && objects[4]!=null && objects[5]==null ) {
								mainVillageStartCount++;
							}else if(commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								mainVillageNotStarted++;
							}
						}
					} else {
						if (commonMethodsUtilService.getLongValueForObject(objects[1]) == 5l || commonMethodsUtilService.getLongValueForObject(objects[1]) == 7l || commonMethodsUtilService.getLongValueForObject(objects[1]) == 9l) {
							if (commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("Y")) {
								affliatedMandalCompletedCount++;
							} else if(commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("N") && objects[4]!=null && objects[5]==null  ) {
								affliatedMandalStartCount++;
							}else if(commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								affliatedMandalNotStarted++;
							}

						} else if (commonMethodsUtilService.getLongValueForObject(objects[1]) == 6l || commonMethodsUtilService.getLongValueForObject(objects[1]) == 8l) {
							if (commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("Y")) {
								affliatedVillageCompletedCount++;
							} else if(commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("N") && objects[4]!=null && objects[5]==null  )  {
								affliatedVillageStartCount++;
							}else if(commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								affliatedVillageNotStarted++;
							}
						}
					}

				}
				committeeCounts.setMainMandalCompletedCount(mainMandalCompletedCount);//main committee mandal/town/division count
				committeeCounts.setMainMandalStartedCount(mainMandalStartCount);//main committee mandal/town/division count
				committeeCounts.setMainMandalTotal(mainMandalCompletedCount+mainMandalStartCount+mainMandalNotStarted);
				committeeCounts.setMainvillageCompletedCount(mainVillageCompletedCount);//main committee village/ward count
				committeeCounts.setMainVillageStartedCount(mainVillageStartCount);//main committee village/ward count
				committeeCounts.setMainVillageTotal(mainVillageCompletedCount+mainVillageStartCount+mainVillageNotStarted);
				committeeCounts.setMainMandalNotYetStartedCount(mainMandalNotStarted);//main committee not yet started count 
				committeeCounts.setMainVillageNotYetStartedCount(mainVillageNotStarted);//main committee not started count
				committeeCounts.setMainMandalCompletePer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainMandalTotal(), mainMandalCompletedCount))));
				committeeCounts.setMainMandalStartPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainMandalTotal(), mainMandalStartCount))));
				committeeCounts.setMainVillageCompletePer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainVillageTotal(), mainVillageCompletedCount))));
				committeeCounts.setMainVillageStartPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainVillageTotal(), mainVillageStartCount))));

				committeeCounts.setAffliatedMandalCompletedCount(affliatedMandalCompletedCount);
				committeeCounts.setAffliatedMandalStartedCount(affliatedMandalStartCount);
				committeeCounts.setAffMandalTotal(affliatedMandalCompletedCount+affliatedMandalStartCount);
				committeeCounts.setAffliatedVillageCompletedCount(affliatedVillageCompletedCount);//affliated committee village/ward count
				committeeCounts.setAffliatedVillageStartedCount(affliatedVillageStartCount);//affliated committee village/ward count
				committeeCounts.setAffVillageTotal(affliatedVillageCompletedCount+affliatedVillageStartCount);
				committeeCounts.setAffiCommMandalNotStarted(affliatedMandalNotStarted);
				committeeCounts.setAffiCommVillageNotStarted(affliatedVillageNotStarted);
				committeeCounts.setAffMandalCompletePer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getAffMandalTotal(), affliatedMandalCompletedCount))));
				committeeCounts.setAffMandalStartPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getAffMandalTotal(), affliatedMandalStartCount))));
				committeeCounts.setAffVillageCompletePer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getAffVillageTotal(), affliatedVillageCompletedCount))));
				committeeCounts.setAffVillageStartPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getAffVillageTotal(), affliatedVillageStartCount))));
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseCommitteesCount", e);
		}

		return committeeCounts;
	}
	/*
	 * @author R Nagarjuna Gowd
	 * @return BasicVO object contains enrollmentIds and Years
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getEnrollmentIds()
	 */

	public List<BasicVO> getEnrollmentIds() {
		List<BasicVO> finalList = new ArrayList<BasicVO>();
		List<TdpCommitteeEnrollment> tdpCommitteeEnrollment = tdpCommitteeEnrollmentDAO.getAll();
		for (TdpCommitteeEnrollment tdpCommitteeEnrollment2 : tdpCommitteeEnrollment) {
			BasicVO enrollmentList = new BasicVO();
			enrollmentList.setId(tdpCommitteeEnrollment2.getTdpCommitteeEnrollmentId());
			enrollmentList.setDescription(tdpCommitteeEnrollment2.getDescription());
			finalList.add(enrollmentList);
		}
		return finalList;
	}

	/*
	 * @author R Nagarjuna Gowd
	 * @return BasicVO object contains electionTypes table data
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getElectionTypes()
	 * Date 02-08-2017
	 */

	public List<BasicVO> getElectionTypes() {
		List<BasicVO> finalList = new ArrayList<BasicVO>();
		List<ElectionType> electionTypes = electionTypeDAO.getAll();
		for (ElectionType electionType : electionTypes) {
			BasicVO electionTypesData = new BasicVO();
			electionTypesData.setId(electionType.getElectionTypeId());
			electionTypesData.setName(electionType.getElectionType());
			electionTypesData.setDescription(electionType.getScope());
			finalList.add(electionTypesData);
		}
		return finalList;
	}


	/*
	 * @author R Nagarjuna Gowd
	 * @return BasicVO object contains publication table data
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getElectionTypes()
	 * Date 02-08-2017
	 */

	public List<BasicVO> getPublications() {
		List<BasicVO> finalList = new ArrayList<BasicVO>();
		List<PublicationDate> publications = publicationDateDAO.getAll();
		for (PublicationDate publication : publications) {
			BasicVO publicationData = new BasicVO();
			publicationData.setId(publication.getPublicationDateId());
			publicationData.setName(publication.getName());
			publicationData.setDate(publication.getDate().toString());
			publicationData.setDay(publication.getMonth().longValue());//month
			publicationData.setTotalResult(publication.getYear().longValue());//year
			finalList.add(publicationData);
		}
		return finalList;
	}


	/*
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationType
	 * @param Long locationValue
	 * @author R Nagarjuna Gowd
	 * @return List<AlertOverviewVO> we have three list in final list 1.village/ward counts 2.mandal/town/division list 3.Constituency counts list
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getLevelWiseMeetingStatusCounts(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 */

	public List<MeetingsVO> getLevelWiseMeetingStatusCounts(String fromDateStr, String toDateStr, Long locationTypeId,
			List<Long> locationValues,String year) {
		List<MeetingsVO> meetingStatusCounts = new ArrayList<MeetingsVO>();
		MeetingsVO vwStatus = new MeetingsVO();
		vwStatus.setLocationName("village/ward");
		MeetingsVO mtdStatus = new MeetingsVO();
		mtdStatus.setLocationName("mandal/town/division");
		MeetingsVO cStatus = new MeetingsVO();
		cStatus.setLocationName("constituncy");
		Date frmDate = null;
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//Here converting string to date formatte
		if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
			try {
				frmDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// 0-partyMeetingLevelId,1-LevelName,2-meeting Status,3-Meetings Count
		List<Object[]> objList = partyMeetingStatusDAO.getLevelWiseMeetingStatusCount(frmDate, toDate, locationTypeId,
				locationValues,year);
		try {
			if (objList != null) {
				for (Object[] objects : objList) {
					if (commonMethodsUtilService.getLongValueForObject(objects[0]) == 7l || commonMethodsUtilService.getLongValueForObject(objects[0]) == 8l) {

						if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("Y")) {
							vwStatus.setYesCount(vwStatus.getYesCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("N")) {
							vwStatus.setNoCount(vwStatus.getNoCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("NU")) {
							vwStatus.setNotUpDatedCount(vwStatus.getNotUpDatedCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("M")) {
							vwStatus.setMayBeCount(vwStatus.getMayBeCount()+(Long)objects[3]);
						}
					} else if (commonMethodsUtilService.getLongValueForObject(objects[0]) == 4l || commonMethodsUtilService.getLongValueForObject(objects[0]) == 5l || commonMethodsUtilService.getLongValueForObject(objects[0]) == 6l) {
						if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("Y")) {
							mtdStatus.setYesCount(mtdStatus.getYesCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("N")) {
							mtdStatus.setNoCount(mtdStatus.getNoCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("NU")) {
							mtdStatus.setNotUpDatedCount(mtdStatus.getNotUpDatedCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("M")) {
							mtdStatus.setMayBeCount(mtdStatus.getMayBeCount()+(Long)objects[3]);
						}
					} else if (commonMethodsUtilService.getLongValueForObject(objects[0]) == 3l) {
						if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("Y")) {
							cStatus.setYesCount(cStatus.getYesCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("N")) {
							cStatus.setNoCount(cStatus.getNoCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("NU")) {
							cStatus.setNotUpDatedCount(cStatus.getNotUpDatedCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("M")) {
							cStatus.setMayBeCount(cStatus.getMayBeCount()+(Long)objects[3]);
						}
					}
				}
				meetingStatusCounts.add(vwStatus);
				meetingStatusCounts.add(mtdStatus);
				meetingStatusCounts.add(cStatus);

			}
		} catch (Exception e) {
			Log.error("Exception raised in meetings method"+e);
		}
		return meetingStatusCounts;
	} 
	/*
	 * Swadhin K Lenka
	 */

	public List<KeyValueVO> getNominatedPostStatusWiseCount(Long locationTypeId,List<Long> locationValuesList,String fromDateStr, String toDateStr,String year){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			List<Object[]> nominatedPostList = nominatedPostDAO.getNominatedPostStatusWiseCount(locationTypeId, locationValuesList, startDate, endDate,year);
			List<KeyValueVO> keyValueVOs = new ArrayList<KeyValueVO>();
			KeyValueVO keyValueVO = null;
			if(nominatedPostList != null && nominatedPostList.size() > 0){
				for(Object[] param : nominatedPostList){
					keyValueVO = new KeyValueVO();
					keyValueVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					keyValueVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					keyValueVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					keyValueVOs.add(keyValueVO);
				}
			}
			return keyValueVOs;
		}catch(Exception e){
			Log.error("Exception raised in getNominatedPostStatusWiseCount method of LocationDashboardService"+e);
		}
		return null;
	}
	/*
	 * Swadhin K Lenka
	 */

	public List<KeyValueVO> getNominatedPostApplicationStatusWiseCount(Long locationTypeId,List<Long> locationValuesList,String fromDateStr,
			String toDateStr,String year){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			List<Object[]> nominatedPostApplicationList = nominatedPostApplicationDAO.getNominatedPostApplicationStatusWiseCount(locationTypeId,locationValuesList,startDate,endDate);
			List<KeyValueVO> keyValueVOs = new CopyOnWriteArrayList<KeyValueVO>();
			KeyValueVO keyValueVO = null;
			if(nominatedPostApplicationList != null && nominatedPostApplicationList.size() > 0){
				for(Object[] param : nominatedPostApplicationList){
					keyValueVO = new KeyValueVO();
					keyValueVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					keyValueVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					keyValueVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					keyValueVOs.add(keyValueVO);
				}
			}
			//combine :Rejected,Rejected in Final Review,Rejected in Finalized
			if(keyValueVOs != null && keyValueVOs.size() > 0){
				keyValueVO = new KeyValueVO();
				keyValueVO.setId(2L);
				keyValueVO.setName("Rejected");
				for(KeyValueVO param : keyValueVOs){
					if(param.getId().longValue() == 2L || param.getId().longValue() == 4L || param.getId().longValue() == 8L){
						keyValueVO.setCount(keyValueVO.getCount()+param.getCount());
						keyValueVOs.remove(param);
					}
				}
				keyValueVOs.add(keyValueVO);
			}
			ArrayList<KeyValueVO> tempArray = null;
			if(keyValueVOs != null && keyValueVOs.size() > 0){
				tempArray = new ArrayList<KeyValueVO>(keyValueVOs);
				Collections.sort(tempArray, new Comparator<KeyValueVO>(){

					public int compare(KeyValueVO obj1,KeyValueVO obj2) {
						return obj1.getId().compareTo(obj2.getId());
					}
				});
			}
			return tempArray;
		}catch(Exception e){
			Log.error("Exception raised in getNominatedPostApplicationStatusWiseCount method of LocationDashboardService"+e);
		}
		return null;
	}
	/*
	 * Swadhin K Lenka
	 */

	public List<KeyValueVO> getPositionWiseMemberCount(List<Long> locationValues,String fromDateStr, String toDateStr,Long locationTypeId,String year){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			//build a template
			List<Object[]> list = boardLevelDAO.getBoardLevels();
			List<KeyValueVO> finalList = new CopyOnWriteArrayList<KeyValueVO>();
			buildTemplate(list,finalList);
			List<Object[]> positionList = nominatedPostDAO.getPositionWiseMemberCount(locationValues,startDate,endDate,locationTypeId,year);
			List<KeyValueVO> keyValueVOs = new CopyOnWriteArrayList<KeyValueVO>();
			KeyValueVO keyValueVO = null;
			if(positionList != null && positionList.size() > 0){
				for(Object[] param : positionList){
					keyValueVO = new KeyValueVO();
					keyValueVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					keyValueVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					keyValueVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					keyValueVOs.add(keyValueVO);
				}
			}
			//combine :Mandal,Muncipality/Corporation
			if(keyValueVOs != null && keyValueVOs.size() > 0){
				keyValueVO = new KeyValueVO();
				keyValueVO.setId(5L);
				keyValueVO.setName("Mandal/Muncipality/Corporation");
				for(KeyValueVO param : keyValueVOs){
					if(param.getId().longValue() == 5L || param.getId().longValue() == 6L){
						keyValueVO.setCount(keyValueVO.getCount()+param.getCount());
						keyValueVOs.remove(param);
					}
				}
				keyValueVOs.add(keyValueVO);
			}

			//combine :Village,Ward
			if(keyValueVOs != null && keyValueVOs.size() > 0){
				keyValueVO = new KeyValueVO();
				keyValueVO.setId(7L);
				keyValueVO.setName("Panchayat/Ward/Division");
				for(KeyValueVO param : keyValueVOs){
					if(param.getId().longValue() == 7L || param.getId().longValue() == 8L){
						keyValueVO.setCount(keyValueVO.getCount()+param.getCount());
						keyValueVOs.remove(param);
					}
				}
				keyValueVOs.add(keyValueVO);
			}
			//push data into template.
			if(finalList != null && finalList.size() > 0){
				for(KeyValueVO param : finalList){
					keyValueVO = getMatchedVO1(keyValueVOs,param.getId());
					if(keyValueVO != null){
						param.setCount(keyValueVO.getCount());
					}
				}
			}
			if(finalList != null && finalList.size() > 0){
				ArrayList<KeyValueVO> tempArray = new ArrayList<KeyValueVO>(finalList);
				Collections.sort(tempArray, new Comparator<KeyValueVO>(){

					public int compare(KeyValueVO obj1,KeyValueVO obj2) {
						return obj1.getId().compareTo(obj2.getId());
					}

				});
			}
			return finalList;
		}catch(Exception e){
			Log.error("Exception raised in getPositionWiseMemberCount method of LocationDashboardService"+e);
		}
		return null;
	}
	public void buildTemplate(List<Object[]> list,List<KeyValueVO> finalList){
		try{
			KeyValueVO keyValueVO= null;
			if(list != null && list.size() > 0){
				for(Object[] param : list){
					keyValueVO = new KeyValueVO();
					keyValueVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					keyValueVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					finalList.add(keyValueVO);
				}
			}
			//combine :Mandal,Muncipality/Corporation
			if(finalList != null && finalList.size() > 0){
				keyValueVO = new KeyValueVO();
				keyValueVO.setId(5L);
				keyValueVO.setName("Mandal/Muncipality/Corporation");
				for(KeyValueVO param : finalList){
					if(param.getId().longValue() == 5L || param.getId().longValue() == 6L){
						finalList.remove(param);
					}
				}
				finalList.add(keyValueVO);
			}

			//combine :Village,Ward
			if(finalList != null && finalList.size() > 0){
				keyValueVO = new KeyValueVO();
				keyValueVO.setId(7L);
				keyValueVO.setName("Mandal/Muncipality/Corporation");
				for(KeyValueVO param : finalList){
					if(param.getId().longValue() == 7L || param.getId().longValue() == 8L){
						finalList.remove(param);
					}
				}
				finalList.add(keyValueVO);
			}
		}catch(Exception e){
			Log.error("Exception raised in buildTemplate method of LocationDashboardService"+e);
		}
	}
	public KeyValueVO getMatchedVO1(List<KeyValueVO> keyValueVOs,Long id){
		try{
			if(keyValueVOs != null && keyValueVOs.size() > 0){
				for(KeyValueVO param : keyValueVOs){
					if(param.getId().longValue() == id){
						return param;
					}
				}
			}
		}catch(Exception e){
			Log.error("Exception raised in getMatchedVO1 method of LocationDashboardService"+e);
		}
		return null;
	}
	/**
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param String locationType
	 * @param String locationValue
	 * @return List<ToursBasicVO>
	 * @author Santosh 
	 * @Description :This Service Method is used to get tour leader over all complaince details designation wise. 
	 *  @since 7-JULY-2017
	 */
	public List<ToursBasicVO> getLocationWiseTourMembersComplainceDtls(final Long locationTypeId,final List<Long> locationValues,final String fromDateStr,final String toDateStr,final String year){
		List<ToursBasicVO> resultList = new ArrayList<ToursBasicVO>(0);
		Map<Long,Map<String,List<ToursBasicVO>>> designationWiseTargetMap = new HashMap<Long, Map<String,List<ToursBasicVO>>>(0);
		Map<String,String> categoryIdNameMap = new HashMap<String, String>(0);
		Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap = new HashMap<Long, Map<Long,ToursBasicVO>>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate=null;
		Date toDate=null;

		try{
			LOG.info("Entered into getLocationWiseTourMembersComplainceDtls() in LocationDashboardService class");

			if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}

			//Get month year in string format based on fromDate and toDate
			List<String> monthYear = selfAppraisalToursMonthDAO.getMonthAndYear(fromDate, toDate);
			//Get month year ids based on month year 
			List<Long> monthyearIds = selfAppraisalToursMonthDAO.getMonthYearByTourMonths(monthYear);

			List<Object[]> rtrnCandiateObjLst = selfAppraisalCandidateLocationNewDAO.getLocationWiseTourMemberDetails(getUserAccessLevel(locationTypeId), locationTypeId, locationValues,year);
			//getting designation wise target
			ToursBasicVO basicDtlsVO = getRequiredData(rtrnCandiateObjLst);
			if(monthyearIds != null && monthyearIds.size() > 0 && basicDtlsVO.getComplaincandidateIdsSet() != null && basicDtlsVO.getNoNComplaincandidateIdsSet() != null){
				List<Object[]> dsgntnWsTurCtgryTrgtObjLst = selfAppraisalDesignationTargetDAO.getCategoryWiseTargetCnt(monthyearIds, "tourCategory", new ArrayList<Long>(basicDtlsVO.getComplaincandidateIdsSet()));//getComplaincandidateIdsSet() set contain designationIds
				setDesignationWiseTarget(dsgntnWsTurCtgryTrgtObjLst,designationWiseTargetMap,categoryIdNameMap,"tourCategory");
				List<Object[]> dsgntnWsTurTypeTrgtObjLst  = selfAppraisalDesignationTargetDAO.getCategoryWiseTargetCnt(monthyearIds, "tourType", new ArrayList<Long>(basicDtlsVO.getComplaincandidateIdsSet()));//getComplaincandidateIdsSet() set contain designationIds
				setDesignationWiseTarget(dsgntnWsTurTypeTrgtObjLst,designationWiseTargetMap,categoryIdNameMap,"tourType");
			}
			//setting candidate wise target
			setCandidateDtls(rtrnCandiateObjLst,candiateDtlsMap,designationWiseTargetMap,categoryIdNameMap);
			//getting tour submitted details candidate wise
			if(monthyearIds != null && monthyearIds.size() > 0 && basicDtlsVO.getComplaincandidateIdsSet() != null && basicDtlsVO.getNoNComplaincandidateIdsSet() != null){
				List<Object[]> tourSubmittedDtlsObjLst = selfAppraisalCandidateDetailsNewDAO.getCategoryWiseLeaderTourSubmittedCnt("tourCategory", monthyearIds,  new ArrayList<Long>(basicDtlsVO.getComplaincandidateIdsSet()),  new ArrayList<Long>(basicDtlsVO.getNoNComplaincandidateIdsSet()));//getComplaincandidateIdsSet() set contain designationIds and getNoNComplaincandidateIdsSet() contains candidate ids
				setMonthWiseComplainceDetails(tourSubmittedDtlsObjLst,candiateDtlsMap,"tourCategory");
				List<Object[]> rtrnGovtDaysToursObjLst = selfAppraisalCandidateDetailsNewDAO.getCategoryWiseLeaderTourSubmittedCnt("tourType", monthyearIds,  new ArrayList<Long>(basicDtlsVO.getComplaincandidateIdsSet()),  new ArrayList<Long>(basicDtlsVO.getNoNComplaincandidateIdsSet()));//getComplaincandidateIdsSet() set contain designationIds and getNoNComplaincandidateIdsSet() contains candidate ids
				setMonthWiseComplainceDetails(rtrnGovtDaysToursObjLst,candiateDtlsMap,"tourType");
			}

			//Calculating Category wise complaince
			calculateCategoryWiseComplaince(candiateDtlsMap);
			//Calculating OverAll percentage
			calculatteOverAllPercentage(candiateDtlsMap);

			//Preparing final list
			if(candiateDtlsMap != null && candiateDtlsMap.size() > 0){
				for(Entry<Long,Map<Long,ToursBasicVO>> entry:candiateDtlsMap.entrySet()){
					ToursBasicVO designationVO = new ToursBasicVO();  
					designationVO.setDesignationId(entry.getKey());
					if(basicDtlsVO != null){
						designationVO.setDesignation(basicDtlsVO.getSubMap().get(entry.getKey()));  
					}
					designationVO.setIsComplaince("False");
					if(entry.getValue() != null && entry.getValue().size() > 0){
						for(Entry<Long,ToursBasicVO> candiateEntry:entry.getValue().entrySet()){
							if(candiateEntry.getValue().getComplaincePer()>=100d){
								designationVO.setComplainceCnt(designationVO.getComplainceCnt()+1);
								designationVO.setIsComplaince("True");
							}
							designationVO.getSubList().add(candiateEntry.getValue());
						}
					}
					resultList.add(designationVO);
				}
			}

		}catch(Exception e){
			Log.error("Exception Occured at getLocationWiseTourMembersComplainceDtls() in LocationDashboardService class",e);
		}
		return resultList;
	}
	public void setMonthWiseComplainceDetails(List<Object[]> objList, Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap,String type){
		try{
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					Long candidateId = commonMethodsUtilService.getLongValueForObject(param[2]);
					String idStr = commonMethodsUtilService.getStringValueForObject(param[3]);//categoryId or tourTypeId 
					if(type.equalsIgnoreCase("tourType")){
						idStr = "0"+idStr;/* We are appending 0 before tourTypeId for Identification purpose because in single list
		                      					i am sending tour category and tour type which both has same id */ 
					}
					Long monthId = commonMethodsUtilService.getLongValueForObject(param[4]);
					Long tourDaysCntPerMonth = commonMethodsUtilService.getLongValueForObject(param[5]);

					if(candiateDtlsMap != null && candiateDtlsMap.size() > 0){

						Map<Long,ToursBasicVO> candiateMap = candiateDtlsMap.get(designationId);

						if(candiateMap != null && candiateMap.size() > 0){

							ToursBasicVO candiateVO = candiateMap.get(candidateId); 

							if(candiateVO != null ){

								ToursBasicVO categoryVO = getCategoryMatchVO(candiateVO.getSubList3(),idStr);

								if(categoryVO != null){

									ToursBasicVO monthVO = getMonthMatchVO(categoryVO.getMonthList(),monthId);

									if(monthVO != null){

										if(tourDaysCntPerMonth >= monthVO.getTargetDays()){

											monthVO.setComplainceDays(tourDaysCntPerMonth);
											Double complaincePer = calculatePercantage(monthVO.getComplainceDays(),monthVO.getTargetDays());
											if(complaincePer > 100d){
												monthVO.setComplaincePer(100d);
											}else{
												monthVO.setComplaincePer(complaincePer);	 
											}
										}else{

											monthVO.setComplainceDays(tourDaysCntPerMonth);
											Double complaincePer = calculatePercantage(monthVO.getComplainceDays(),monthVO.getTargetDays());
											monthVO.setComplaincePer(complaincePer);	 
										}
									}
								}	    
							}

						}  
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in setMonthWiseComplainceDetails() in LocationDashboardService  : ",e);	 
		}
	}
	public void setDesignationWiseTarget(List<Object[]> objLst,Map<Long,Map<String,List<ToursBasicVO>>> designationWiseTargetMap,Map<String,String> categoryIdNameMap,String type){
		try{
			if(objLst != null && objLst.size() > 0){
				for(Object[] param:objLst){
					Map<String,List<ToursBasicVO>> categoryMap = designationWiseTargetMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(categoryMap == null){
						categoryMap = new LinkedHashMap<String, List<ToursBasicVO>>(0);
						designationWiseTargetMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), categoryMap);
					}
					String idStr = commonMethodsUtilService.getStringValueForObject(param[2]);
					if(type.equalsIgnoreCase("tourType")){
						idStr = "0"+idStr;/* We are appending 0 before tourTypeId for Identification purpose because in single list
							                      i am sending tour category and tour type which both has same id */ 
					}
					List<ToursBasicVO> monthList = categoryMap.get(idStr);
					if(monthList == null){
						monthList = new ArrayList<ToursBasicVO>();
						categoryIdNameMap.put(idStr, commonMethodsUtilService.getStringValueForObject(param[3]));
						categoryMap.put(idStr, monthList);
					}
					ToursBasicVO monthVO = new ToursBasicVO();
					monthVO.setId(commonMethodsUtilService.getLongValueForObject(param[4]));//monthId
					String year = commonMethodsUtilService.getStringValueForObject(param[7]);
					monthVO.setName(commonMethodsUtilService.getStringValueForObject(param[5]).substring(0, 3)+"-"+year.substring(year.length()-2));//monthName & Year
					monthVO.setYear(commonMethodsUtilService.getLongValueForObject(param[7]));
					monthVO.setTargetDays(commonMethodsUtilService.getLongValueForObject(param[6]));
					monthList.add(monthVO);
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in setDesignationWiseTarget() in LocationDashboardService  : ",e);
		}
	}
	public void setCandidateDtls(List<Object[]> rtrnMemberDtls,Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap, Map<Long,Map<String,List<ToursBasicVO>>> designationWiseTargetMap, Map<String,String> categoryIdNameMap){
		try{
			if(rtrnMemberDtls != null && rtrnMemberDtls.size() > 0){
				for(Object[] param:rtrnMemberDtls){
					Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					Map<Long,ToursBasicVO> candidateMap = candiateDtlsMap.get(designationId);
					if(candidateMap == null ){
						candidateMap = new LinkedHashMap<Long,ToursBasicVO>();
						candiateDtlsMap.put(designationId, candidateMap);
					}
					ToursBasicVO candiateVO = candidateMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));//candiateId
					if(candiateVO == null ){
						candiateVO = new ToursBasicVO();
						candiateVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[0]));
						candiateVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[1]));
						candiateVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
						candiateVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
						candiateVO.setLocationScopeId(commonMethodsUtilService.getLongValueForObject(param[4]));
						List<ToursBasicVO> categoryList = setRequiredTargetDesignationWise(candiateVO.getDesignationId(),designationWiseTargetMap,categoryIdNameMap);
						if(categoryList != null && categoryList.size() > 0){
							candiateVO.setSubList3(new ArrayList<ToursBasicVO>(categoryList));
						}
						candidateMap.put(candiateVO.getId(), candiateVO);
					}
					candiateVO.getLocationSet().add(commonMethodsUtilService.getLongValueForObject(param[5]));//location value
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in setCandidateDtls() in LocationDashboardService  : ",e);
		}
	}
	public List<ToursBasicVO> setRequiredTargetDesignationWise(Long designationId,Map<Long,Map<String,List<ToursBasicVO>>> designationWiseTargetMap,Map<String,String> categoryIdNameMap){
		try{
			if(designationWiseTargetMap != null && designationWiseTargetMap.size() > 0){

				List<ToursBasicVO> categoryList = new ArrayList<ToursBasicVO>();

				Map<String,List<ToursBasicVO>> categroyMap = designationWiseTargetMap.get(designationId);

				if(categroyMap != null && categroyMap.size() > 0){

					for(Entry<String, List<ToursBasicVO>> entry:categroyMap.entrySet()){

						ToursBasicVO categoryVO = new ToursBasicVO();
						categoryVO.setIdStr(entry.getKey());
						if(categoryIdNameMap != null && categoryIdNameMap.size() > 0){
							categoryVO.setName(categoryIdNameMap.get(entry.getKey()));	 
						}
						if(entry.getValue() != null && entry.getValue().size() > 0){
							for(ToursBasicVO mntVO:entry.getValue()){
								ToursBasicVO monthVO = new ToursBasicVO(); 
								monthVO.setId(mntVO.getId());
								monthVO.setName(mntVO.getName());
								monthVO.setYear(mntVO.getYear());
								monthVO.setTargetDays(mntVO.getTargetDays());
								categoryVO.getMonthList().add(monthVO);
							}
						}
						categoryList.add(categoryVO);
					}
				}
				return categoryList;
			} 
		}catch(Exception e){
			LOG.error("Exception Occured in setRequiredTargetDesignationWise() in LocationDashboardService  : ",e);	 
		}
		return null;
	}
	public void calculateCategoryWiseComplaince(Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap){
		try{
			if(candiateDtlsMap != null && candiateDtlsMap.size() > 0){

				for(Entry<Long,Map<Long,ToursBasicVO>> designationEntry:candiateDtlsMap.entrySet()){

					if(designationEntry.getValue() != null && designationEntry.getValue().size() > 0){

						for(Entry<Long,ToursBasicVO> candiateEntry:designationEntry.getValue().entrySet()){

							ToursBasicVO candiateVO = candiateEntry.getValue();

							if(candiateVO != null){

								if(candiateVO.getSubList3() != null && candiateVO.getSubList3().size() > 0){

									for(ToursBasicVO categoryVO:candiateVO.getSubList3()){

										List<ToursBasicVO> monthList = categoryVO.getMonthList();

										if(monthList != null && monthList.size() > 0){

											Double totalPer= 0.0d;
											Long targetDays =0l;
											Long complainceDays =0l;

											for(ToursBasicVO monthVO:monthList){

												totalPer = totalPer+monthVO.getComplaincePer();
												targetDays = targetDays + monthVO.getTargetDays();
												complainceDays = complainceDays + monthVO.getComplainceDays();
											}

											Integer totalCount =0;

											if(monthList != null && monthList.size() > 0){

												totalCount = monthList.size() * 100;   
											}

											Double percentage = calculatePercantageBasedOnDouble(totalPer,totalCount.doubleValue());
											if(percentage > 100){
												categoryVO.setComplaincePer(100d);
											}else{
												categoryVO.setComplaincePer(percentage);   
											}
											categoryVO.setTargetDays(targetDays);
											categoryVO.setComplainceDays(complainceDays);
										}
									}	 
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in calculateCategoryWiseComplaince() in LocationDashboardService  : ",e); 
		}
	}
	public void calculatteOverAllPercentage(Map<Long,Map<Long,ToursBasicVO>> memberDtlsMap){
		try{
			if(memberDtlsMap != null && memberDtlsMap.size() > 0){

				for(Entry<Long,Map<Long,ToursBasicVO>> designationEntry:memberDtlsMap.entrySet()){

					if(designationEntry != null && designationEntry.getValue().size() >0){

						for(Entry<Long,ToursBasicVO> candiateEntry:designationEntry.getValue().entrySet()){

							List<ToursBasicVO> categoryList = candiateEntry.getValue().getSubList3();

							if(categoryList != null && categoryList.size() > 0){

								Double totalPer =0.0d;
								Long complainceDays=0l;
								for(ToursBasicVO VO:categoryList){
									totalPer = totalPer+VO.getComplaincePer(); 
									complainceDays = complainceDays +VO.getComplainceDays();
								}
								Integer totalCount =0;
								if(categoryList != null && categoryList.size() > 0){
									totalCount = categoryList.size() * 100;   
								}

								Double percentage = calculatePercantageBasedOnDouble(totalPer,totalCount.doubleValue());
								candiateEntry.getValue().setComplainceDays(complainceDays);
								if(percentage > 100d){
									candiateEntry.getValue().setComplaincePer(100d);
								}else{
									candiateEntry.getValue().setComplaincePer(percentage);  
								}
							}
						}
					}
				}
			}	 
		}catch(Exception e){
			LOG.error("Exception Occured in calculatteOverAllPercentage() in LocationDashboardService  : ",e);	 
		}
	}
	public ToursBasicVO getCategoryMatchVO(List<ToursBasicVO> categoryList,String id){
		try{
			if(categoryList == null || categoryList.size() == 0)
				return null;
			for(ToursBasicVO vo:categoryList){
				if(vo.getIdStr().equalsIgnoreCase(id.trim())){
					return vo;
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in getCategoryMatchVO() in LocationDashboardService  : ",e);	 
		}
		return null;
	}
	public ToursBasicVO getMonthMatchVO(List<ToursBasicVO> monthList,Long id){
		try{
			if(monthList == null || monthList.size() == 0)
				return null;
			for(ToursBasicVO vo:monthList){
				if(vo.getId().equals(id)){
					return vo;
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in getCategoryMatchVO() in LocationDashboardService  : ",e);	 
		}
		return null;
	}
	public Double calculatePercantage(Long subCount,Long totalCount){
		Double d=0.0d;
		if(subCount.longValue()>0l && totalCount.longValue()==0l)
			LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

		if(totalCount.longValue() > 0l){
			d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
	}
	public Double calculatePercantageBasedOnDouble(Double subCount,Double totalCount){
		Double d=0.0d;
		if(subCount.longValue()>0l && totalCount.longValue()==0l)
			LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

		if(totalCount.longValue() > 0l){
			d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
	}
	public Long getUserAccessLevel(Long locationTypeId){
		Long userAccessLevelId=0l;
		/*if(locationTypeId != null){
			if(locationType.equalsIgnoreCase("District")){
				userAccessLevelId = 3l;
			}else if(locationType.equalsIgnoreCase("ParliamentConstituency")){
				userAccessLevelId = 10l;
			}else if(locationType.equalsIgnoreCase("Constituency")){
				userAccessLevelId = 4l;
			}
		}*/
		userAccessLevelId = locationTypeId;
		return userAccessLevelId;
	}
	public ToursBasicVO getRequiredData(List<Object[]> objList){
		ToursBasicVO vo = new ToursBasicVO();
		try{
			if(objList != null && objList.size() > 0){
				vo.setComplaincandidateIdsSet(new java.util.HashSet<Long>(0));//adding designationIds
				vo.setNoNComplaincandidateIdsSet(new java.util.HashSet<Long>(0));//adding candidateIds
				vo.setSubMap(new HashMap<Long, String>(0));
				for (Object[] param : objList) {
					vo.getSubMap().put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					vo.getComplaincandidateIdsSet().add(commonMethodsUtilService.getLongValueForObject(param[0]));
					vo.getNoNComplaincandidateIdsSet().add(commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
		}catch(Exception e){
			Log.error("Exception Occured at getRequiredData() in LocationDashboardService class",e);
		}
		return vo;
	}
	/**
	 * @param String locationType
	 * @param String locationValue
	 * @return List<BenefitCandidateVO>
	 * @author Santosh 
	 * @Description :This Service Method is used for getting government scheme wise benefit member count 
	 *  @since 7-JULY-2017
	 */
	public List<BenefitCandidateVO> getGovtSchemeWiseBenefitMembersCount(final Long locationType, final Long locationValue) {
		List<BenefitCandidateVO> resultList = new ArrayList<BenefitCandidateVO>(0);
		try {
			List<Object[]> benefitMemberObjLst = govtSchemeBeneficiaryDetailsDAO.getGovtSchemeWiseBenefitMemberCount(locationType,locationValue);
			List<Object[]> censusPopList = getCensusPopulation(benefitMemberObjLst,"constituency",locationValue);
			resultList = getGovtSchemeBenefitMemberDlstList(benefitMemberObjLst,censusPopList,"constituency");
		} catch (Exception e) {
			Log.error("Exception Occured at getGovtSchemeWiseBenefitMembersCount() in LocationDashboardService class",e);
		}
		return resultList;
	}

	private List<Object[]> getCensusPopulation(List<Object[]> benefitMemberObjLst,String locationType, Long locationValue) {

		Set<Long> locationIdSet = new HashSet<Long>();
		List<Object[]> censusPopList = null;
		if(locationType == "constituency"){	
			locationIdSet.add(locationValue);
			censusPopList= constituencyCensusDetailsDAO.getTotalCensusPopulation(locationIdSet, 2011l);
		}else if(locationType == "tehsil"){
			if (benefitMemberObjLst != null && benefitMemberObjLst.size() > 0) {
				for (Object[] param : benefitMemberObjLst) {
					locationIdSet.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				}
			}
			censusPopList= censusDAO.getTotalCensusPopulation(locationIdSet, 2011l);
		}
		return censusPopList;

	}
	/**
	 * @param String locationType
	 * @param String locationValue
	 * @return List<BenefitCandidateVO>
	 * @author Santosh 
	 * @Description :This Service Method is used for getting mandal wise benefit member count 
	 *  @since 7-JULY-2017
	 */
	public List<BenefitCandidateVO> getMandalWiseBenefitMembersCount(final Long locationType, final Long locationValue,final Long govtSchemeId) {
		List<BenefitCandidateVO> resultList = new ArrayList<BenefitCandidateVO>(0);
		try {
			List<Object[]> benefitMemberObjLst = govtSchemeBeneficiaryDetailsDAO.getMandalWiseBenefitMemberCountByGovtScheme(locationType,locationValue, govtSchemeId);
			List<Object[]> censusPopList = getCensusPopulation(benefitMemberObjLst,"tehsil",locationValue);
			resultList = getGovtSchemeBenefitMemberDlstList(benefitMemberObjLst,censusPopList,"tehsil");
		} catch (Exception e) {
			Log.error("Exception Occured at getMandalWiseBenefitMembersCount() in LocationDashboardService class",e);
		}
		return resultList;
	}

	public List<BenefitCandidateVO> getGovtSchemeBenefitMemberDlstList(List<Object[]> objList, List<Object[]> censusPopList, String locationType) {
		List<BenefitCandidateVO> returnList = new ArrayList<BenefitCandidateVO>(0);
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					BenefitCandidateVO vo = new BenefitCandidateVO();
					for(Object[] censusParam : censusPopList){
						if(locationType!= null && locationType.equalsIgnoreCase("constituency")){
							vo.setTotalPopulation(commonMethodsUtilService.getLongValueForObject(censusParam[0]));
							vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							vo.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[2]));
							returnList.add(vo);
						}if(locationType.equalsIgnoreCase("tehsil")){
							Long l2 = commonMethodsUtilService.getLongValueForObject(censusParam[1]);
							if(commonMethodsUtilService.getLongValueForObject(param[0]).compareTo(l2)==0){
								vo.setTotalPopulation(commonMethodsUtilService.getLongValueForObject(censusParam[0]));
								vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
								vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
								vo.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[2]));
								returnList.add(vo);
							}
						}

					}

				}

			}
		} catch (Exception e) {
			Log.error("Exception Occured at getGovtSchemeBenefitMemberDlstList() in LocationDashboardService class",e);
		}
		return returnList;
	}
	/*
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationId
	 * @param Long locationValue
	 * @author R Nagarjuna Gowd
	 * @return InsuranceStatusCountsVO object with status counts
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getLocationWiseInsuranceStatusCounts(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 */

	public GrivenceStatusVO getLocationWiseInsuranceStatusCounts(String fromDateStr, String toDateStr, Long locationTypeId,List<Long> locationValues,String year) {
		GrivenceStatusVO grivenceStatusCount = new GrivenceStatusVO();
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			// Here converting stirng to date formatte 
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			//0-locationValue(DIstrict or ConstituencyId),1-locationName,2-Status,3-StatusId,4-Count
			List<Object[]> insuranceStatus = insuranceStatusDAO.getConstituencyWiseInsuranceStatusCounts(fromDate, toDate,locationTypeId,locationValues,year);
			List<Object[]> statusList = insuranceStatusDAO.grievanceInsuranceStatusId();
			Map<Long,GrivenceStatusVO> InsuranceStatusMap = new LinkedHashMap<Long, GrivenceStatusVO>(0);
			if(insuranceStatus!=null){
				//Here set the values for object 
				for (Object[] objects : insuranceStatus) {
					if(!commonMethodsUtilService.isMapValid(InsuranceStatusMap) && commonMethodsUtilService.isListOrSetValid(statusList)){
						for (Object[] status : statusList) {
							GrivenceStatusVO vo = new GrivenceStatusVO();
							vo.setName(commonMethodsUtilService.getStringValueForObject(status[1]));
							vo.setCount(0L);
							InsuranceStatusMap.put(commonMethodsUtilService.getLongValueForObject(status[0]), vo);
						}
					}
					
					GrivenceStatusVO vo = InsuranceStatusMap.get(commonMethodsUtilService.getLongValueForObject(objects[3]));
					if(vo != null){
						vo.setCount(vo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[4]));
					}
				
				}
				if(commonMethodsUtilService.isMapValid(InsuranceStatusMap)){
					grivenceStatusCount.setGrivenceType("Insurance");
					for (Long status : InsuranceStatusMap.keySet()) {
						GrivenceStatusVO list= InsuranceStatusMap.get(status);
						grivenceStatusCount.getSubList().add(list);
					}
				}
				
			}
		}catch(Exception e){
			Log.error("Exception raised at insurance status counts service"+e);
		}
		return grivenceStatusCount;
	}
	/* @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationId
	 * @param Long locationValue
	 * @author R Nagarjuna Gowd
	 * @return List<List<GrivenceStatusVO>> we have two lists in final list 1.Grivence counts(Govt,party,welfare) 2.Trust counts
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getGrivenceTrustStatusCounts(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 */

	public List<GrivenceStatusVO> getGrivenceTrustStatusCounts(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year) {
		List<GrivenceStatusVO> finalList = new ArrayList<GrivenceStatusVO>();
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			// Here converting stirng to date formatte
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			//0-consId,1-Status,2-typeOfIssue,3-count
			List<Object[]> grivenceTrustList = insuranceStatusDAO.getGrivenceTrustStatusCounts(fromDate, toDate,locationTypeId,locationValues,year);
			List<String> statusList = insuranceStatusDAO.getGrivenceStatus();
			if(grivenceTrustList!=null){
				//  Here set the Grivence values for object
				
				Map<String,GrivenceStatusVO> grievanceStatusMap = new LinkedHashMap<String, GrivenceStatusVO>(0);
				Map<String,GrivenceStatusVO> trustStatusMap = new LinkedHashMap<String, GrivenceStatusVO>(0);
				for (Object[] objects : grivenceTrustList) {
					if(objects[2].toString().trim().equalsIgnoreCase("Govt") || objects[2].toString().trim().equalsIgnoreCase("Party")  || 
							objects[2].toString().trim().equalsIgnoreCase("Welfare")){
						
						if(!commonMethodsUtilService.isMapValid(grievanceStatusMap) && commonMethodsUtilService.isListOrSetValid(statusList)){
							for (String status : statusList) {
								GrivenceStatusVO vo = new GrivenceStatusVO();
								vo.setName(status.toUpperCase());
								vo.setCount(0L);
								grievanceStatusMap.put(status.toUpperCase(), vo);
							}
						}
						GrivenceStatusVO vo = grievanceStatusMap.get(commonMethodsUtilService.getStringValueForObject(objects[1]).toUpperCase());
						if(vo != null){
							vo.setCount(vo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[3]));
						}
					}
					//  Here set the Trust values for object
					else if(objects[2].toString().trim().equalsIgnoreCase("Trust Education Support"))
					{
						if(!commonMethodsUtilService.isMapValid(trustStatusMap) && commonMethodsUtilService.isListOrSetValid(statusList)){
							for (String status : statusList) {
								GrivenceStatusVO vo = new GrivenceStatusVO();
								vo.setName(status.toUpperCase());
								vo.setCount(0L);
								trustStatusMap.put(status.toUpperCase(), vo);
							}
						}
						GrivenceStatusVO vo = trustStatusMap.get(commonMethodsUtilService.getStringValueForObject(objects[1]).toUpperCase());
						if(vo != null){
							vo.setCount(vo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[3]));
						}
					}
				}
				
				/*Map<String,GrivenceStatusVO> grievanceStatusMap = new LinkedHashMap<String, GrivenceStatusVO>(0);
				Map<String,GrivenceStatusVO> trustStatusMap = new LinkedHashMap<String, GrivenceStatusVO>(0);*/
				
				if(commonMethodsUtilService.isMapValid(grievanceStatusMap)){
					GrivenceStatusVO grivenceStatusCount = new GrivenceStatusVO();
					grivenceStatusCount.setGrivenceType("Grivence");
					for (String status : grievanceStatusMap.keySet()) {
						grivenceStatusCount.getSubList().add(grievanceStatusMap.get(status));
					}
					finalList.add(grivenceStatusCount);
				}
				if(commonMethodsUtilService.isMapValid(trustStatusMap)){
					GrivenceStatusVO trustStatusCount = new GrivenceStatusVO();
					trustStatusCount.setGrivenceType("NTR Trust");
					for (String status : trustStatusMap.keySet()) {
						trustStatusCount.getSubList().add(trustStatusMap.get(status));
					}
					finalList.add(trustStatusCount);
				}
				
			}
		}catch(Exception e){
			Log.error("Exception raised at grivence and trust counts service"+e);
		}
		return finalList;
	}
	/**
	 * @param  String locationType
	 * @param  Long locationValue
	 * @return List<ConstituencyCadreVO>
	 * @author Santosh 
	 * @Description :This Service Method is used to location type cadre count details 
	 *  @since 27-JUNE-2017
	 */
	public List<ConstituencyCadreVO> getLocationTypeWiseCadreCount(final Long locationTypeId,final List<Long> locationValues,final String year){
		List<ConstituencyCadreVO> resultList = new ArrayList<ConstituencyCadreVO>(0);
		try{
			List<Object[]> rtrnCaderCountObjLst = tdpCadreEnrollmentInfoDAO.getLocationTypeWiseCadreCount(locationTypeId,locationValues,year);
			if(rtrnCaderCountObjLst != null && rtrnCaderCountObjLst.size() > 0){
				for (Object[] param : rtrnCaderCountObjLst) {
					ConstituencyCadreVO enrollmentYearVO = new ConstituencyCadreVO();
					enrollmentYearVO.setEnrollmentYearId(commonMethodsUtilService.getLongValueForObject(param[0]));
					enrollmentYearVO.setEnrollmentYear(commonMethodsUtilService.getStringValueForObject(param[1]));
					enrollmentYearVO.setToalCadreCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					enrollmentYearVO.setNewCaderCount(commonMethodsUtilService.getLongValueForObject(param[3]));
					enrollmentYearVO.setRenewalCadreCount(commonMethodsUtilService.getLongValueForObject(param[4]));
					resultList.add(enrollmentYearVO);
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at getLocationTypeWiseCadreCount in LocationDashboardService class", e);
		}
		return resultList;
	}
	/**
	 * @param  String locationType
	 * @param  Long locationValue
	 * @param Long enrollmentYearId
	 * @return List<ConstituencyCadreVO>
	 * @author Santosh 
	 * @Description :This Service Method is for getting ageRange,gender,caste group by cadre count. 
	 *  @since 5-JULY-2017
	 */
	public List<ConstituencyCadreVO> getAgeRangeGenerAndCasteGroupByCadreCount(final String locationType, final Long locationValue,final Long enrollmentYearId) {
		List<ConstituencyCadreVO> resultList = new ArrayList<ConstituencyCadreVO>(0);
		try {
			List<Object[]> rtrnCaderObjLst = tdpCadreEnrollmentYearDAO.getAgeGenerAndCasteGroupWiseCadresCount(locationType,locationValue, enrollmentYearId);

			Map<Long, ConstituencyCadreVO> ageRangeMap = new LinkedHashMap<Long, ConstituencyCadreVO>(0);

			if (rtrnCaderObjLst != null && rtrnCaderObjLst.size() > 0) {
				List<Object[]> casteCategoryObjLst = casteCategoryDAO.getAllCasteCategoryDetails();
				// 0-ageRangeId,1-ageRange,2-gener,3-casteCategoryId,4-casteCategory,5-totalCount
				for (Object[] param : rtrnCaderObjLst) {
					Long totalCadreCount = commonMethodsUtilService.getLongValueForObject(param[5]);
					if (!ageRangeMap.containsKey(commonMethodsUtilService.getLongValueForObject(param[0]))) {
						ConstituencyCadreVO ageRangeVO = new ConstituencyCadreVO();
						ageRangeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						ageRangeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1])+ " "+"Years");
						ageRangeVO.setCasteGroupList(getCasteCategoryList(casteCategoryObjLst));
						ageRangeMap.put(ageRangeVO.getId(), ageRangeVO);
					}

					ConstituencyCadreVO ageRangeVO = ageRangeMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if (ageRangeVO != null) {
						ageRangeVO.setToalCadreCount(ageRangeVO.getToalCadreCount() + totalCadreCount);
						if (commonMethodsUtilService.getStringValueForObject(param[2]).equalsIgnoreCase("M")) {
							ageRangeVO.setMaleCount(ageRangeVO.getMaleCount()+ totalCadreCount);
						} else if (commonMethodsUtilService.getStringValueForObject(param[2]).equalsIgnoreCase("F")) {
							ageRangeVO.setFemaleCount(ageRangeVO.getFemaleCount() + totalCadreCount);
						}
						ConstituencyCadreVO casteCategoryVO = getCasteCategoryMatchVO(ageRangeVO.getCasteGroupList(), commonMethodsUtilService.getLongValueForObject(param[3]));
						if (casteCategoryVO != null) {
							casteCategoryVO.setToalCadreCount(casteCategoryVO.getToalCadreCount()+totalCadreCount);
						}
					}
				}
			}
			// Calculating Percentage
			if (ageRangeMap.size() > 0) {
				for (Entry<Long, ConstituencyCadreVO> entry : ageRangeMap.entrySet()) {
					entry.getValue().setMalePercentage(calculatePercantage(entry.getValue().getMaleCount(), entry.getValue().getToalCadreCount()));
					entry.getValue().setFemalePercentage(calculatePercantage(entry.getValue().getFemaleCount(), entry.getValue().getToalCadreCount()));
					if (entry.getValue() != null && entry.getValue().getCasteGroupList() != null) {
						for (ConstituencyCadreVO casteCategoryVO : entry.getValue().getCasteGroupList()) {
							casteCategoryVO.setPercentage(calculatePercantage(casteCategoryVO.getToalCadreCount(), entry.getValue().getToalCadreCount()));
						}
					}
				}
			}
			resultList.addAll(ageRangeMap.values());
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationTypeWiseCadreCount in getAgeRangeGenerAndCasteGroupByCadreCount class",e);
		}
		return resultList;
	}
	public ConstituencyCadreVO getCasteCategoryMatchVO(List<ConstituencyCadreVO> categoryList,Long id){
		try{
			if(categoryList == null || categoryList.size() == 0)
				return null;
			for(ConstituencyCadreVO vo:categoryList){
				if(vo.getId().equals(id)){
					return vo;
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in getCasteCategoryMatchVO() in LocationDashboardService  : ",e);	 
		}
		return null;
	}
	public List<ConstituencyCadreVO> getCasteCategoryList(List<Object[]> objList) {
		List<ConstituencyCadreVO> casteCategoryList = new ArrayList<ConstituencyCadreVO>(0);
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					ConstituencyCadreVO casteCategoryVO = new ConstituencyCadreVO();
					casteCategoryVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					casteCategoryVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					casteCategoryList.add(casteCategoryVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCasteCategoryList in getCasteCategoryList class",e);
		}
		return casteCategoryList;
	}
	@Override
	public List<BasicVO> getLocationWiseActivitysStatus(String fromDateStr, String toDateStr, String year,List<Long> locationValues, Long locationTypeId) {
		List<BasicVO> finalList = new ArrayList<BasicVO>();
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			//0-activity_scope_id 1-activity_name 2-level 3-total
			List<Object[]> activityTotal=activityDAO.getActivitysTotal(fromDate, toDate, year, locationValues, locationTypeId);
			//0-activity_scope_id 1-activity_name 2-level 3-conducted_count
			List<Object[]> activityConductedCount=activityDAO.getActivitysConductedCount(fromDate, toDate, year, locationValues, locationTypeId);
			//0-activity_scope_id 1-activity_name 2-level 3-total
			List<Object[]> conductedInfoTotal = activityDAO.getConductedInfoTotal(fromDate, toDate, year, locationValues, locationTypeId);
			//0-activity_scope_id 1-activity_name 2-level 3-conductedCount
			List<Object[]> conductedCount = activityDAO.getConductedInfoCount(fromDate, toDate, year, locationValues, locationTypeId);

			for (Object[] object1 : activityTotal) {
				for (Object[] object2 : activityConductedCount) {
					BasicVO vo = new BasicVO();
					if(((Long)object2[0]).equals((Long)object1[0])){
						vo.setPerc(calculatePercantage((Long)object2[3], (Long)object1[3]));
						vo.setName(object1[1].toString());
						vo.setDescription(object1[2].toString());
						vo.setTotalVoters((Long)object1[3]);
						vo.setTotalResult((Long)object2[3]);
					}
					if(vo.getPerc()!=null){
						finalList.add(vo);
					}
				}
			}
			for (Object[] object3 : conductedInfoTotal) {
				for (Object[] object4 : conductedCount) {
					BasicVO vo = new BasicVO();
					if(((Long)object3[0]).equals((Long)object4[0])){
						vo.setPerc(calculatePercantage((Long)object4[3], (Long)object3[3]));
						vo.setName(object3[1].toString());
						vo.setDescription(object3[2].toString());
						vo.setTotalVoters((Long)object3[3]);
						vo.setTotalResult((Long)object3[3]);
					}
					if(vo.getPerc()!=null){
						finalList.add(vo);
					}
				}
			}

		}catch(Exception e){
			LOG.error("Exception raised at getActivityStatusList() in LocationDashBoardService class",e);
		}
		return finalList;
	}

	/**
	 * @param  Long stateId
	 * @return List<LocationWiseBoothDetailsVO>
	 * @author sanjeev 
	 * @Description :This Service Method is used to get All Distrits in a State
	 *  @since 02-AUG-2017
	 */
	
	public List<LocationWiseBoothDetailsVO> getAllDistricts(Long stateId){
		List<LocationWiseBoothDetailsVO> idNameVOList = new ArrayList<LocationWiseBoothDetailsVO>();
		try {
			List<Long> newDistrictArr = new ArrayList<Long>();
			Long[] ids = IConstants.AP_NEW_DISTRICTS_IDS;
			for (Long param : ids) {
				newDistrictArr.add(param);
			}
			List<Object[]> districtList = districtDAO.getAllNewDistrictDetailsForAState(1l, newDistrictArr);
			if (districtList != null && districtList.size() > 0) {
				for (Object[] objects : districtList) {
					LocationWiseBoothDetailsVO idNameVO = new LocationWiseBoothDetailsVO();
					idNameVO.setLocationId((Long) objects[0]);
					idNameVO.setLocationName(objects[1].toString());
					idNameVOList.add(idNameVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getAllDistricts", e);
		}
		return idNameVOList;
	}

	/**
	 * @param  Long districtId
	 * @return List<LocationWiseBoothDetailsVO>
	 * @author sanjeev 
	 * @Description :This Service Method is used to get All Constituencies in a District
	 *  @since 02-AUG-2017
	 */
	
	public List<LocationWiseBoothDetailsVO> getAllConstituenciesByDistrict(Long districtId) {
		List<LocationWiseBoothDetailsVO> idNameVOList = new ArrayList<LocationWiseBoothDetailsVO>();
		try {
			if (districtId.compareTo(IConstants.VISHAKAPATNAM_IDS.get(1)) != 0) {
				List<Object[]> constituencyList = constituencyDAO.getAllConstituenciesInADistrict(districtId);
				if (constituencyList != null && constituencyList.size() > 0) {
					for (Object[] objects : constituencyList) {
						LocationWiseBoothDetailsVO idNameVO = new LocationWiseBoothDetailsVO();
						idNameVO.setLocationId((Long) objects[0]);
						idNameVO.setLocationName(objects[1].toString());
						idNameVOList.add(idNameVO);
					}
				}
			}
			if (IConstants.VISHAKAPATNAM_IDS.contains(districtId)) {
				List<Object[]> constituencyDistrictList = districtConstituenciesDAO.getAllConstituenciesInADistrict(IConstants.VISHAKAPATNAM_IDS.get(1));
				if (districtId.compareTo(IConstants.VISHAKAPATNAM_IDS.get(0)) == 0) {
					for (Object[] param : constituencyDistrictList) {
						for (int i = 0; i <= idNameVOList.size() - 1; i++) {
							if (idNameVOList.get(i).getLocationId().compareTo(commonMethodsUtilService.getLongValueForObject(param[0])) == 0) {
								idNameVOList.remove(i);
							}
						}

					}
				} else if (districtId.compareTo(IConstants.VISHAKAPATNAM_IDS.get(1)) == 0) {
					if (constituencyDistrictList != null && constituencyDistrictList.size() > 0) {
						for (Object[] objects : constituencyDistrictList) {
							LocationWiseBoothDetailsVO idNameVO = new LocationWiseBoothDetailsVO();
							idNameVO.setLocationId((Long) objects[0]);
							idNameVO.setLocationName(objects[1].toString());
							idNameVOList.add(idNameVO);
						}
					}
				}

			}
		} catch (Exception e) {
			LOG.error("Exception raised in getAllConstituencysForADistrict", e);
		}
		return idNameVOList;
	}

	/**
	 * @param  String fromDate,toDate
	 * @param loctionType,locationValue
	 * @param electionScopeIds
	 * @return List<ElectionInformationVO>
	 * @author Sanjeev 
	 * @Description :This Service Method is used to get All party results based on location
	 *  @since 02-AUG-2017
	 */
	
	public List<ElectionInformationVO> getElectionInformationLocationWise(String fromDateStr, String toDateStr, Long locationTypeId,Long locationValue, List<Long> electionScopeIds) {
		List<ElectionInformationVO> electionInformationVOList= new ArrayList<ElectionInformationVO>();
		try{
			List<Long> yearsList=  new ArrayList<Long>();
			List<Long> tehsilIds = new ArrayList<Long>();
			List<Long> electionBodyIds =new ArrayList<Long>();
			List<LocationWiseBoothDetailsVO> mandalsAndElecBdy= cadreCommitteeService.getMandalsByConstituency(locationValue);
			for (LocationWiseBoothDetailsVO locationWiseBoothDetailsVO : mandalsAndElecBdy) {
				if(locationWiseBoothDetailsVO != null){
					if(locationWiseBoothDetailsVO.getLocationId().compareTo(0l)!=0){
						char c= locationWiseBoothDetailsVO.getLocationId().toString().charAt(0);
						if(c=='2'){
							Long tehsilId=Long.valueOf(locationWiseBoothDetailsVO.getLocationId().toString().substring(1));
							tehsilIds.add(tehsilId);
						}else if(c=='1'){
							electionBodyIds.add(Long.valueOf(locationWiseBoothDetailsVO.getLocationId().toString().substring(1)));
						}
					}

				}
			}

			if(fromDateStr != null && !fromDateStr.trim().isEmpty() && toDateStr != null && !toDateStr.trim().isEmpty()){
				Long fromYear = Long.parseLong(fromDateStr.split("-")[2]);
				Long toYear = Long.parseLong(toDateStr.split("-")[2]);

				for (Long i = fromYear; i <= toYear; i++) {
					yearsList.add(i);
				}
			}
			List<Object[]> resultArray= candidateDAO.getElectionInformationLocationWise(yearsList, locationTypeId, locationValue, electionScopeIds, electionBodyIds, tehsilIds);
			for (Object[] param : resultArray) {
				if(param!=null){
					ElectionInformationVO electionInformationVO = new ElectionInformationVO();
					electionInformationVO.setPartyId(commonMethodsUtilService.getLongValueForObject(param[0]));
					electionInformationVO.setPartyName(commonMethodsUtilService.getStringValueForObject(param[1]));
					electionInformationVO.setElectionId(commonMethodsUtilService.getLongValueForObject(param[2]));
					electionInformationVO.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[3]));
					electionInformationVO.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[4]));
					electionInformationVO.setElectionType(commonMethodsUtilService.getStringValueForObject(param[5]));
					electionInformationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[6]));
					electionInformationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[7]));
					electionInformationVO.setTotalVoters(commonMethodsUtilService.getLongValueForObject(param[8]));
					electionInformationVO.setValidVoters(commonMethodsUtilService.getLongValueForObject(param[9]));
					electionInformationVO.setMissedVotes(commonMethodsUtilService.getLongValueForObject(param[10]));
					electionInformationVO.setRejectedVotes(commonMethodsUtilService.getLongValueForObject(param[11]));
					electionInformationVO.setEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[12]));
					electionInformationVO.setEarnedVotesPerc(commonMethodsUtilService.getLongValueForObject(param[13]));
					electionInformationVO.setMarginVotes(commonMethodsUtilService.getLongValueForObject(param[14]));
					electionInformationVO.setWonSeatsCount(commonMethodsUtilService.getLongValueForObject(param[15]));
					electionInformationVOList.add(electionInformationVO);
				}

			}

		}catch(Exception e){
			LOG.error("Exception raised in getElectionInformationLocationWise", e);

		}

		return electionInformationVOList;
	}

	/**
	 *
	 * @param  String fromDate,toDate
	 * @param loctionType,locationValue
	 * @param electionScopeIds
	 * @return BoothInchargesVO
	 * @author Sanjeev 
	 * @Description :This Service Method is used to get All party results based on location
	 * @since 06-AUG-2017
	 * 
	 */
	public BoothInchargesVO getBoothAssignInchargeCount(String fromDateStr, String toDateStr, Long locationTypeId,Long locationValue,
			List<Long> committeeEnrollmentYearsIdsLst){
		BoothInchargesVO inchargeVo = new BoothInchargesVO();
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			Long startedCount= boothInchargeCommitteeDAO.getElectionBoothDetails(fromDate, toDate,locationTypeId, locationValue, "started",
					committeeEnrollmentYearsIdsLst);
			Long completedCount= boothInchargeCommitteeDAO.getElectionBoothDetails(fromDate, toDate,locationTypeId, locationValue, "completed",
					committeeEnrollmentYearsIdsLst);
			Long totalCount= boothInchargeCommitteeDAO.getElectionBoothDetails(fromDate, toDate,locationTypeId, locationValue, null,
					committeeEnrollmentYearsIdsLst);
			inchargeVo.setTotalCount(totalCount);
			inchargeVo.setStartedCount(startedCount);
			inchargeVo.setCompletedCount(completedCount);
			inchargeVo.setNotStartedCount(totalCount-(completedCount+startedCount));
			
		}catch(Exception e){
			LOG.error("Exception raised in getBoothAssignInchargeCount", e);

		}
		return inchargeVo;
	}
	/**
	 *
	 * @param  String fromDate,toDate
	 * @param loctionType,locationValue
	 * @param boothCommitteeEnrollmentYearsIdsLst
	 * @return List<BoothInchargesVO>
	 * @author Sanjeev 
	 * @Description :This Service Method is used to get All party results based on location
	 * @since 07-AUG-2017
	 * 
	 */
	@Override
	public List<BoothInchargesVO>getBoothCommitteeInchargesCount(Long locationId,Long locationValue,List<Long> boothCommitteeEnrollmentYearsIdsLst,String fromDateStr,String toDateStr){
		List<BoothInchargesVO> returnList = new ArrayList<BoothInchargesVO>(0);
		try{
			Date startDate = null;
			Date endDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
		    List<Object[]> boothInchargs = boothInchargeCommitteeDAO.getBoothInchargeCountDetails(locationId, locationValue, boothCommitteeEnrollmentYearsIdsLst, startDate, endDate);
		    setCountForBoothCommitteeIncharges(returnList,"defaultData",0l,null);
		    Map<Long,Long> boothMap = new HashMap<Long,Long>();
		    if(boothInchargs != null && boothInchargs.size() >0){
		    	for(Object[] obj :boothInchargs){
		    		Long cnt =boothMap.get(obj[0]);
		    		
		    		if(cnt != null && cnt.longValue() >0l){
		    			boothMap.put((Long)obj[0], cnt+1l);
		    		}else{
		    			boothMap.put((Long)obj[0], 1l);
		    		}
		    		
		    	}
		    }
		    
		    if(boothMap != null && boothMap.size() >0){
		    	for(Object[] obj :boothInchargs){
		    		Long cnt =boothMap.get(obj[0]);
		    		if(cnt != null && cnt.longValue() >0l){
		    			//boothMap.put((Long)obj[0], cnt+1l);
		    			setCountForBoothCommitteeIncharges(returnList,"matchedVO",cnt,obj);
		    		}else{
		    			//boothMap.put((Long)obj[0], 1l);
		    			setCountForBoothCommitteeIncharges(returnList,"matchedVO",1l,obj);
		    		}
		    	}
		    }
		    
		}catch(Exception e){
			e.printStackTrace();
			Log.error("Exception raised at getBoothCommitteeInchargesCount", e);
		}
		
		return returnList;
	}

	public void setCountForBoothCommitteeIncharges(List<BoothInchargesVO> returnList, String type, Long cnt,
			Object[] obj) {
		try {

			if (type != null && type.equalsIgnoreCase("defaultData")) {
				List<String> boothIncharges = new ArrayList<String>(0);
				boothIncharges.add("1");
				boothIncharges.add("2");
				boothIncharges.add("3");
				boothIncharges.add("4");
				boothIncharges.add("5-10");
				boothIncharges.add("10-Above");
				for (String str : boothIncharges) {
					BoothInchargesVO vo = new BoothInchargesVO();
					vo.setBoothInchargesAssnd(str);
					returnList.add(vo);
				}
			} else {
				BoothInchargesVO matchedVO = null;
				if (cnt != null && cnt == 1l) {
					matchedVO = getMatchVO(returnList, "1");
				} else if (cnt != null && cnt == 2l) {
					matchedVO = getMatchVO(returnList, "2");
				} else if (cnt != null && cnt == 3l) {
					matchedVO = getMatchVO(returnList, "3");
				} else if (cnt != null && cnt == 4l) {
					matchedVO = getMatchVO(returnList, "4");
				} else if (cnt != null && cnt >= 5l && cnt <= 10l) {
					matchedVO = getMatchVO(returnList, "5-10");
				} else if (cnt != null && cnt >= 10l) {
					matchedVO = getMatchVO(returnList, "10-Above");
				}

				if (matchedVO != null) {
					Long boothId = (Long) obj[0];
					matchedVO.getBoothIds().add(boothId);// booths
					if (matchedVO.getTdpCadreId() != null && matchedVO.getTdpCadreId().longValue() > 0)
						matchedVO.setTdpCadreId(matchedVO.getTdpCadreId() + 1l);// no  of booth  incharges
					else
						matchedVO.setTdpCadreId(1l);// no of booth incharges
					if (obj[2] != null) {
						String gender = (String) obj[2];
						if (gender.equalsIgnoreCase("M")) {
							if (matchedVO.getMaleCnt() != null && matchedVO.getMaleCnt().longValue() > 0)
								matchedVO.setMaleCnt(matchedVO.getMaleCnt() + 1l);
							else
								matchedVO.setMaleCnt(1l);
						} else if (gender.equalsIgnoreCase("F")) {
							if (matchedVO.getFemaleCnt() != null && matchedVO.getFemaleCnt().longValue() > 0)
								matchedVO.setFemaleCnt(matchedVO.getFemaleCnt() + 1l);
							else
								matchedVO.setFemaleCnt(1l);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BoothInchargesVO getMatchVO(List<BoothInchargesVO> returnList, String boothInchrgAss) {
		if (returnList == null || returnList.size() == 1)
			return null;
		for (BoothInchargesVO boothIncharg : returnList) {
			if (boothIncharg.getBoothInchargesAssnd().equalsIgnoreCase(
					boothInchrgAss)) {
				return boothIncharg;
			}
		}
		return null;
	}

}