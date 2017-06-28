package com.itgrids.core.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.itgrids.core.api.service.ILocationDashboardService;
import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeEnrollmentDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CandidateInfoForConstituencyVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.dto.StatusTrackingVO;
import com.itgrids.partyanalyst.model.CasteCategory;
import com.itgrids.partyanalyst.model.EnrollmentYear;
import com.itgrids.partyanalyst.model.TdpCommitteeEnrollment;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;

public class LocationDashboardService implements ILocationDashboardService {
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

	@SuppressWarnings("unchecked")
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

						for (LocationVotersVO casteVO : casteGroupVO.getLocationVotersVOList()) {
							casteVO.setMaleVotersPerc(((casteVO.getMaleVoters() * 100) / maleTotalVoters) + " %");
							casteVO.setFemaleVotersPerc(((casteVO.getFemaleVoters() * 100) / femaleTotalVoters) + " %");
							casteVO.setTotalCadres(casteVO.getMaleCadres() + casteVO.getFemaleCadres());
							casteVO.setTotalCadrePerc(((casteVO.getTotalCadres() * 100) / totalCadres) + " %");
							casteVO.setMaleCadrePerc(((casteVO.getMaleCadres() * 100) / maleTotalCadres) + " %");
							casteVO.setFemaleCadrePerc(((casteVO.getFemaleCadres() * 100) / femaleTotalCadres) + " &%");
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
					entry.getValue()
							.setTotalVotersPerc(((entry.getValue().getTotalVoters() * 100.00) / totalVoters) + " %");
					entry.getValue()
							.setMaleVotersPerc(((entry.getValue().getMaleVoters() * 100.00) / totalMaleVoters) + " %");
					entry.getValue().setFemaleVotersPerc(
							((entry.getValue().getFemaleVoters() * 100.00) / totalFemaleVoters) + " %");
					entry.getValue()
							.setTotalCadrePerc(((entry.getValue().getTotalCadres() * 100.00) / totalCadres) + " %");
					entry.getValue()
							.setMaleCadrePerc(((entry.getValue().getMaleCadres() * 100.00) / totalMaleCadres) + " %");
					entry.getValue().setFemaleCadrePerc(
							((entry.getValue().getFemaleCadres() * 100.00) / totalFemaleCadres) + " %");
				}

				voList.addAll(map.values());
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCasteNAgeWiseVoterNCadreCounts", e);
		}
		return voList;
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

	@Override
	public CommitteeBasicVO getLocationWiseCommitteesCount(String locationType, Long locationId,
			Long enrollmentId) {

		CommitteeBasicVO committeeCounts = new CommitteeBasicVO();
		try {
			// 0-tdp_base_comitteeId,1-levelId,2-levelName,3-committeeConfrimed,4-start
			// Date,5-completed Date
			List<Object[]> objList = tdpCommitteeDAO.getLocationWiseCommittees(locationType, locationId, enrollmentId);
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
					if ((Long)objects[0] == 1l) {
						if ((Long)objects[1] == 5l || (Long)objects[1] == 7l || (Long)objects[1] == 9l) {
							if (objects[3].toString().trim().equalsIgnoreCase("Y")) {
								mainMandalCompletedCount++;
							} else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]!=null) {
								mainMandalStartCount++;
							}else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								mainMandalNotStarted++;
							}

						} else if ((Long)objects[1] == 6l || (Long)objects[1] == 8l) {
							if (objects[3].toString().trim().equalsIgnoreCase("Y")) {
								mainVillageCompletedCount++;
							} else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]!=null) {
								mainVillageStartCount++;
							}else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								mainVillageNotStarted++;
							}
						}
					} else {
						
						
						if ((Long)objects[1] == 5l || (Long)objects[1] == 7l || (Long)objects[1] == 9l) {
							if (objects[3].toString().trim().equalsIgnoreCase("Y")) {
								affliatedMandalCompletedCount++;
							} else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]!=null) {
								affliatedMandalStartCount++;
							}else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								affliatedMandalNotStarted++;
							}

						} else if ((Long)objects[1] == 6l || (Long)objects[1] == 8l) {
							if (objects[3].toString().trim().equalsIgnoreCase("Y")) {
								affliatedVillageCompletedCount++;
							} else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]!=null)  {
								affliatedVillageStartCount++;
							}else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								affliatedVillageNotStarted++;
							}
						}
					}
					
				}
				committeeCounts.setMainCommCompletedCount(mainMandalCompletedCount);//main committee mandal/town/division count
				committeeCounts.setMainCommStartedCount(mainMandalStartCount);//main committee village/ward count
				committeeCounts.setMainMandalTotal(mainMandalCompletedCount+mainMandalStartCount+mainMandalNotStarted);
				committeeCounts.setMainCommTotalCount(mainVillageCompletedCount);//main committee village/ward count
				committeeCounts.setMainCommTotalMembers(mainVillageStartCount);//main committee village/ward count
				committeeCounts.setMainVillageTotal(mainVillageCompletedCount+mainVillageStartCount+mainVillageNotStarted);
				committeeCounts.setMainCommNotYetStarted(mainMandalNotStarted);//main committee not yet started count 
				committeeCounts.setMainCommVillageNotStarted(mainVillageNotStarted);//main committee not started count
				committeeCounts.setMainMandalCompletePer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainMandalTotal(), mainMandalCompletedCount))));
				committeeCounts.setMainMandalStartPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainMandalTotal(), mainMandalStartCount))));
				committeeCounts.setMainVillageCompletePer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainVillageTotal(), mainVillageCompletedCount))));
				committeeCounts.setMainMandalStartPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainVillageTotal(), mainVillageStartCount))));
				
				committeeCounts.setAffiCommCompletedCount(affliatedMandalCompletedCount);
				committeeCounts.setAffiCommStartedCount(affliatedMandalStartCount);
				committeeCounts.setAffMandalTotal(affliatedMandalCompletedCount+affliatedMandalStartCount);
				committeeCounts.setAffiCommTotalCount(affliatedVillageCompletedCount);//affliated committee village/ward count
				committeeCounts.setAffiCommTotalMembers(affliatedVillageStartCount);//affliated committee village/ward count
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

	@Override
	public List<TdpCommitteeEnrollment> getEnrollmentIds() {
		List<TdpCommitteeEnrollment> TdpCommitteeEnrollment = tdpCommitteeEnrollmentDAO.getAll();
		return TdpCommitteeEnrollment;
	}
}