package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IJbCommitteeConfirmRuleConditionDAO;
import com.itgrids.partyanalyst.dao.IJbCommitteeDAO;
import com.itgrids.partyanalyst.dao.IJbCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.IJbCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.IJbCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IParliamentAssemblyDAO;
import com.itgrids.partyanalyst.dao.ITehsilConstituencyDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.dao.hibernate.JbCommitteeStatusDAO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.VoterSearchVO;
import com.itgrids.partyanalyst.model.JbCommittee;
import com.itgrids.partyanalyst.model.JbCommitteeLevel;
import com.itgrids.partyanalyst.model.JbCommitteeMember;
import com.itgrids.partyanalyst.model.JbCommitteeStatus;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.IJanmabhoomiCommitteeService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class JanmabhoomiCommitteeService implements IJanmabhoomiCommitteeService{

	
	private final static Logger LOG = Logger.getLogger(JanmabhoomiCommitteeService.class);
	private IJbCommitteeDAO jbCommitteeDAO;
	private CommonMethodsUtilService commonMethodsUtilService ;
	private IJbCommitteeLevelDAO jbCommitteeLevelDAO ;
	private IJbCommitteeRoleDAO jbCommitteeRoleDAO;
	private IJbCommitteeMemberDAO jbCommitteeMemberDAO;
	private DateUtilService dateUtilService;
	private ICadreCommitteeService cadreCommitteeService;
	private ICadreRegistrationService cadreRegistrationService;
	private List<VoterSearchVO> voterVoList = new ArrayList<VoterSearchVO>();
	private ICasteStateDAO casteStateDAO;
	private ICasteCategoryDAO casteCategoryDAO;
	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
	private IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO;
	private IJbCommitteeConfirmRuleConditionDAO jbCommitteeConfirmRuleConditionDAO;
	private JbCommitteeStatusDAO jbCommitteeStatusDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IConstituencyDAO constituencyDAO;
	private IParliamentAssemblyDAO parliamentAssemblyDAO;
	private ITehsilConstituencyDAO tehsilConstituencyDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IPanchayatDAO panchayatDAO;
	public JbCommitteeStatusDAO getJbCommitteeStatusDAO() {
		return jbCommitteeStatusDAO;
	}

	public void setJbCommitteeStatusDAO(JbCommitteeStatusDAO jbCommitteeStatusDAO) {
		this.jbCommitteeStatusDAO = jbCommitteeStatusDAO;
	}

	public IJbCommitteeConfirmRuleConditionDAO getJbCommitteeConfirmRuleConditionDAO() {
		return jbCommitteeConfirmRuleConditionDAO;
	}

	public void setJbCommitteeConfirmRuleConditionDAO(
			IJbCommitteeConfirmRuleConditionDAO jbCommitteeConfirmRuleConditionDAO) {
		this.jbCommitteeConfirmRuleConditionDAO = jbCommitteeConfirmRuleConditionDAO;
	}

	public IUserDistrictAccessInfoDAO getUserDistrictAccessInfoDAO() {
		return userDistrictAccessInfoDAO;
	}

	public void setUserDistrictAccessInfoDAO(
			IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO) {
		this.userDistrictAccessInfoDAO = userDistrictAccessInfoDAO;
	}

	public IUserConstituencyAccessInfoDAO getUserConstituencyAccessInfoDAO() {
		return userConstituencyAccessInfoDAO;
	}

	public void setUserConstituencyAccessInfoDAO(
			IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
		this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
	}

	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public IJbCommitteeRoleDAO getJbCommitteeRoleDAO() {
		return jbCommitteeRoleDAO;
	}

	public void setJbCommitteeRoleDAO(IJbCommitteeRoleDAO jbCommitteeRoleDAO) {
		this.jbCommitteeRoleDAO = jbCommitteeRoleDAO;
	}
 
	
	public IJbCommitteeMemberDAO getJbCommitteeMemberDAO() {
		return jbCommitteeMemberDAO;
	}

	public void setJbCommitteeMemberDAO(IJbCommitteeMemberDAO jbCommitteeMemberDAO) {
		this.jbCommitteeMemberDAO = jbCommitteeMemberDAO;
	}
	
	public IJbCommitteeLevelDAO getJbCommitteeLevelDAO() {
		return jbCommitteeLevelDAO;
	}
	public void setJbCommitteeLevelDAO(IJbCommitteeLevelDAO jbCommitteeLevelDAO) {
		this.jbCommitteeLevelDAO = jbCommitteeLevelDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public IJbCommitteeDAO getJbCommitteeDAO() {
		return jbCommitteeDAO;
	}
	public void setJbCommitteeDAO(IJbCommitteeDAO jbCommitteeDAO) {
		this.jbCommitteeDAO = jbCommitteeDAO;
	}

	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}

	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
   
	public List<VoterSearchVO> getVoterVoList() {
		return voterVoList;
	}
	public void setVoterVoList(List<VoterSearchVO> voterVoList) {
		this.voterVoList = voterVoList;
	}
	
	
	public ICasteStateDAO getCasteStateDAO() {
		return casteStateDAO;
	}

	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}

	public ICasteCategoryDAO getCasteCategoryDAO() {
		return casteCategoryDAO;
	}

	public void setCasteCategoryDAO(ICasteCategoryDAO casteCategoryDAO) {
		this.casteCategoryDAO = casteCategoryDAO;
	}

	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IParliamentAssemblyDAO getParliamentAssemblyDAO() {
		return parliamentAssemblyDAO;
	}

	public void setParliamentAssemblyDAO(
			IParliamentAssemblyDAO parliamentAssemblyDAO) {
		this.parliamentAssemblyDAO = parliamentAssemblyDAO;
	}

	public ITehsilConstituencyDAO getTehsilConstituencyDAO() {
		return tehsilConstituencyDAO;
	}

	public void setTehsilConstituencyDAO(
			ITehsilConstituencyDAO tehsilConstituencyDAO) {
		this.tehsilConstituencyDAO = tehsilConstituencyDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	@SuppressWarnings("unused")
		@Override
		public JanmabhoomiCommitteeMemberVO getJanmabhoomiCommitteeOverview(Long committeId, String fromDateStr, String toDateStr) {
			JanmabhoomiCommitteeMemberVO committeeVO = new JanmabhoomiCommitteeMemberVO();
			Map<Long,JanmabhoomiCommitteeMemberVO> designationVOMap=new LinkedHashMap<Long, JanmabhoomiCommitteeMemberVO>();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				Date startDate = null;
				Date endDate = null;
				if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
					startDate = sdf.parse(fromDateStr);
					endDate = sdf.parse(toDateStr);
				}
				
				
				List<Object[]> designationsobjList = jbCommitteeRoleDAO.getDesignationsIdsByCommitteeId(committeId);
				 //0 jbCommitteeRoleId,1 jbMemberTypeId,2 memberType, 3 maxMembers,4 jbCommitteeConfirmRuleId
				   //5 districtId,6 districtName,7 constituencyId,8 constituencyName,9 parliamentConstituencyId, 10 parliamentConstituencyName,
				   //11 mandalId,12 mandalName,13 panchayatId,14 panchayatName,15 localElectionBodyId,16 localElectionBodyName,17 wardId,18 wardName
				//19 publicRepresentativeTypeId,20 jbCommitteeLevelId,21 jbCommitteeLevelValue,22 stateId,23 stateName,24 statusId,25 status
				Long jbCommitteeConfirmRuleId = 0l;
	            List<Long> committeeRoleIdsList=new ArrayList<Long>();
				if(designationsobjList!=null && designationsobjList.size() >0){
					for(Object[] param:designationsobjList){
						committeeVO.setStatusId(commonMethodsUtilService.getLongValueForObject(param[24]));
						committeeVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[25]));
						jbCommitteeConfirmRuleId = commonMethodsUtilService.getLongValueForObject(param[4]);
						committeeRoleIdsList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
						Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
						committeeVO.setLevelId(commonMethodsUtilService.getLongValueForObject(param[20]));
						committeeVO.setLevelValue(commonMethodsUtilService.getLongValueForObject(param[21]));
						JanmabhoomiCommitteeMemberVO designationVO = designationVOMap.get(designationId);
						if(designationVO == null){
							designationVO = new JanmabhoomiCommitteeMemberVO();
							designationVO.setDesignationId(designationId);
							designationVO.setRoleMemberCount(commonMethodsUtilService.getLongValueForObject(param[3]));
							designationVO.setDesignationName(commonMethodsUtilService.getStringValueForObject(param[2]));
							designationVO.setPublicRepresentativeTypeId(commonMethodsUtilService.getLongValueForObject(param[19]));
							committeeVO.setRoleMemberCount(designationVO.getRoleMemberCount()+committeeVO.getRoleMemberCount());
							designationVOMap.put(designationId, designationVO);
							
							committeeVO.setDistrictId(param[5]!=null ?commonMethodsUtilService.getLongValueForObject(param[5]):null);
							committeeVO.setDistrictName(param[6]!=null ?commonMethodsUtilService.getStringValueForObject(param[6]):null);
							committeeVO.setConstituencyId(param[7]!=null?commonMethodsUtilService.getLongValueForObject(param[7]):null);
							committeeVO.setConstituencyName(param[8]!=null ?commonMethodsUtilService.getStringValueForObject(param[8]):null);
							committeeVO.setParliamentConstituencyId(param[9]!=null?commonMethodsUtilService.getLongValueForObject(param[9]):null);
							committeeVO.setParliamentConstituencyName(param[10]!=null ?commonMethodsUtilService.getStringValueForObject(param[10]):null);
							committeeVO.setMandalId(param[11]!=null?commonMethodsUtilService.getLongValueForObject(param[11]):null);
							committeeVO.setMandalName(param[12]!=null ?commonMethodsUtilService.getStringValueForObject(param[12]):null);
							committeeVO.setPanchayatId(param[13]!=null?commonMethodsUtilService.getLongValueForObject(param[13]):null);
							committeeVO.setPanchayatName(param[14]!=null ?commonMethodsUtilService.getStringValueForObject(param[14]):null);
							committeeVO.setLocalElectionBodyId(param[15]!=null?commonMethodsUtilService.getLongValueForObject(param[15]):null);
							committeeVO.setLocalElectionBodyName(param[16]!=null ?commonMethodsUtilService.getStringValueForObject(param[16]):null);
							committeeVO.setWardId(param[17]!=null?commonMethodsUtilService.getLongValueForObject(param[17]):null);
							committeeVO.setWardName(param[18]!=null ?commonMethodsUtilService.getStringValueForObject(param[18]):null);
							committeeVO.setStateId(param[22]!=null?commonMethodsUtilService.getLongValueForObject(param[22]):null);
							committeeVO.setStateName(param[23]!=null ?commonMethodsUtilService.getStringValueForObject(param[23]):null);
							
						}
					}
					
				}
				List<Object[]> categories = null;
				if(jbCommitteeConfirmRuleId != null && jbCommitteeConfirmRuleId.longValue() > 0l){
					categories = jbCommitteeConfirmRuleConditionDAO.getCasteCategoryMinMembersForCommittee(jbCommitteeConfirmRuleId);
				}
				Map<String,Long> ctegories = null;
				if(commonMethodsUtilService.isListOrSetValid(categories)){
					ctegories = new HashMap<String,Long>();
					for (Object[] objects : categories) {
						Long minMembrs = ctegories.get(commonMethodsUtilService.getStringValueForObject(objects[1]));
						if(minMembrs == null)
							ctegories.put(commonMethodsUtilService.getStringValueForObject(objects[1]), commonMethodsUtilService.getLongValueForObject(objects[2]));
					}
				}
				Map<String,Long> addedMemCategory  = new HashMap<String,Long>();
				List<Object[]> objList = jbCommitteeMemberDAO.getCommitteMemebersByRoleIds(committeeRoleIdsList, startDate, endDate);
				//0 jbCommitteeMemberId,1 jbMemberTypeId,2 memberName, 3 mobileNo,4 isActive,5 status,6 voterIDCardNo
				if(objList!=null && objList.size() >0){
					for(Object[] param:objList){
						Long designationId = commonMethodsUtilService.getLongValueForObject(param[1]);
						JanmabhoomiCommitteeMemberVO designationVO = designationVOMap.get(designationId);
						if(designationVO !=null){
							designationVO.getDesinationMebersVOList().add(createMemberVO(param,committeeVO));
							Long memAddedCategory = addedMemCategory.get(commonMethodsUtilService.getStringValueForObject(param[8]));
							if(commonMethodsUtilService.getStringValueForObject(param[4]).equalsIgnoreCase("Y") && (commonMethodsUtilService.getStringValueForObject(param[5]).equalsIgnoreCase("F")
									|| commonMethodsUtilService.getStringValueForObject(param[5]).equalsIgnoreCase("P"))){
								if(memAddedCategory == null){
									addedMemCategory.put(commonMethodsUtilService.getStringValueForObject(param[8]), 1l);
								}else if(memAddedCategory.longValue() > 0l){
									addedMemCategory.put(commonMethodsUtilService.getStringValueForObject(param[8]), memAddedCategory.longValue()+1l);
								}
							}
							//committeeVO.setTotalMemberCount(committeeVO.getTotalMemberCount()+1L);
						}
					}
				}
				
				committeeVO.getDesinationVOList().addAll(designationVOMap.values());
				if(committeeVO.getDesinationVOList() !=null && committeeVO.getDesinationVOList().size() >0){
					for(JanmabhoomiCommitteeMemberVO designationVO:committeeVO.getDesinationVOList()){
						Long designationId = designationVO.getDesignationId();
						Long roleCount = designationVO.getRoleMemberCount();
						if(roleCount >Long.valueOf(designationVO.getDesinationMebersVOList().size())){
							Long memebersNotAddedCount = roleCount.longValue()-designationVO.getDesinationMebersVOList().size();
							for(int i=0;i<memebersNotAddedCount;i++){
								JanmabhoomiCommitteeMemberVO notAddedMemeberVO=new JanmabhoomiCommitteeMemberVO();
								designationVO.getDesinationMebersVOList().add(notAddedMemeberVO);
							}
						}
					}
				}
				
				if(ctegories !=null && ctegories.size() >0){
					for(Entry<String, Long>  entrySet : ctegories.entrySet()){
						Long addedMemeCount = addedMemCategory.get(entrySet.getKey());
						if(addedMemeCount != null && entrySet.getValue().longValue() <= addedMemeCount.longValue()){
							setCasteStatusToVO(committeeVO,entrySet.getKey(),"contains");
						}else{
							setCasteStatusToVO(committeeVO,entrySet.getKey(),"notContains");
						}
					}
				}
				if(committeeVO.getNotAddedMemberCount().longValue() ==0l)
					committeeVO.setNotAddedMemberCount(committeeVO.getRoleMemberCount());
				if(committeeVO.getAddedMemberCount().longValue() ==committeeVO.getRoleMemberCount().longValue())
					committeeVO.setNotAddedMemberCount(0L);
				
				//committeeVO = setStatusToVO(committeId,committeeVO);
			} catch (Exception e) {
				LOG.error("Entered into getJanmabhoomiCommitteeOverview method in JanmabhoomiCommitteeService ",e);
			}
			return committeeVO;
		}

	public void setCasteStatusToVO(JanmabhoomiCommitteeMemberVO committeeVO,String casteName,String status){
		if(casteName.equalsIgnoreCase("OC")){
			committeeVO.setOcType(status);
		}else if(casteName.equalsIgnoreCase("BC")){
			committeeVO.setBcType(status);
		}else if(casteName.equalsIgnoreCase("SC")){
			committeeVO.setScType(status);
		}else if(casteName.equalsIgnoreCase("ST")){
			committeeVO.setStType(status);
		}
	}
		public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}

	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}

	public String calculatePercentage(Long totalVoters,Long count)
	{
		try{
			if(totalVoters != null && totalVoters.longValue() > 0l && count != null && count.longValue()>0L)
			  return (new BigDecimal((count * 100.0)/totalVoters.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			else{
				return "0";
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in calculatePercentage() method, Exception - ",e);
		}
		return null;
	}
	
		
		public JanmabhoomiCommitteeMemberVO createMemberVO(Object[] param,JanmabhoomiCommitteeMemberVO committeeVO){
					JanmabhoomiCommitteeMemberVO memeberVO = new JanmabhoomiCommitteeMemberVO();
					memeberVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					memeberVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[1]));
					memeberVO.setMemeberName(commonMethodsUtilService.getStringValueForObject(param[2]));
					memeberVO.setMobileNumber(commonMethodsUtilService.getStringValueForObject(param[3]));
					memeberVO.setVoterCardNo(commonMethodsUtilService.getStringValueForObject(param[6]));
					memeberVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(param[7]));
					memeberVO.setCategoryName(commonMethodsUtilService.getStringValueForObject(param[8]));
					memeberVO.setCasteId(commonMethodsUtilService.getLongValueForObject(param[9]));
					memeberVO.setCasteName(commonMethodsUtilService.getStringValueForObject(param[10]));
					memeberVO.setPartyId(commonMethodsUtilService.getLongValueForObject(param[11]));
					memeberVO.setPartyName(commonMethodsUtilService.getStringValueForObject(param[12]));
					memeberVO.setMemberShipCardId(commonMethodsUtilService.getStringValueForObject(param[13]));
					memeberVO.setVoterId(commonMethodsUtilService.getLongValueForObject(param[14]));
					memeberVO.setImageUrl(param[15] !=null ?commonMethodsUtilService.getStringValueForObject(param[15]):null);
					if(commonMethodsUtilService.getStringValueForObject(param[4]).equalsIgnoreCase("Y") && commonMethodsUtilService.getStringValueForObject(param[5]).equalsIgnoreCase("F")){
						memeberVO.setStatus("Approved");
						committeeVO.setAddedMemberCount(committeeVO.getAddedMemberCount()+1);
						committeeVO.setNotAddedMemberCount(committeeVO.getRoleMemberCount()-committeeVO.getAddedMemberCount());
					}
					else if(commonMethodsUtilService.getStringValueForObject(param[4]).equalsIgnoreCase("Y") && commonMethodsUtilService.getStringValueForObject(param[5]).equalsIgnoreCase("P")){
						memeberVO.setStatus("Inprogress");
						committeeVO.setAddedMemberCount(committeeVO.getAddedMemberCount()+1);
						committeeVO.setNotAddedMemberCount(committeeVO.getRoleMemberCount()-committeeVO.getAddedMemberCount());
						//committeeVO.setNotAddedMemberCount(committeeVO.getNotAddedMemberCount()+1);
					}else{
						memeberVO.setStatus("Rejected");
						committeeVO.setRejectedMemberCount(committeeVO.getRejectedMemberCount()+1);
					}
					return memeberVO;
		}

	public JanmabhoomiCommitteeVO getDistrictWiseCommitteeDetails(String fromDate,String endDate,String type,Long userId){
		//List<JanmabhoomiCommitteeVO> returnList = new ArrayList<JanmabhoomiCommitteeVO>(); 
		JanmabhoomiCommitteeVO mainVO = new JanmabhoomiCommitteeVO();
		Map<Long,JanmabhoomiCommitteeVO> locationMapsWithLevel = null;
		try{ 
			
			
			List<JbCommitteeLevel> committeeLvls = jbCommitteeLevelDAO.getAll();
			
			List<Object[]> districtUserLocaVals = null;
			Set<Long> userAccessLocVals = null;
			districtUserLocaVals =userDistrictAccessInfoDAO.getLocationIdList(userId);
			if(districtUserLocaVals !=null && districtUserLocaVals.size() >0){
					userAccessLocVals = new TreeSet<Long>();
					getUserAccessVals(districtUserLocaVals,userAccessLocVals);// set districtIds
					type="district";
					List<Object[]> list = jbCommitteeDAO.getDistrictWiseCommitteeDetails(null,null,type,userAccessLocVals,null);
					// 0 levelId,1 levelName,2 count,3 statusId,4 status ,5 ''
					locationMapsWithLevel = new HashMap<Long,JanmabhoomiCommitteeVO>();
					setLocationDataToList(mainVO.getDistrictsList(),list,locationMapsWithLevel,committeeLvls,type);
				
				List<Object[]> constituencyUserLocaVals = constituencyDAO.getConstituencysByDistrictId(new ArrayList<Long>(userAccessLocVals));
					userAccessLocVals = new TreeSet<Long>();
					Set<Long> allconstIds = new TreeSet<Long>();// store constituencyds for bringing commitess count of that parliaments
					getUserAccessVals(constituencyUserLocaVals,userAccessLocVals);// set constituencyIds
					getUserAccessVals(constituencyUserLocaVals,allconstIds);// set constituencyIds
					type="constituency";
					List<Object[]> constList = jbCommitteeDAO.getDistrictWiseCommitteeDetails(null,null,type,userAccessLocVals,null);
					locationMapsWithLevel = new HashMap<Long,JanmabhoomiCommitteeVO>();
					setLocationDataToList(mainVO.getConstsList(),constList,locationMapsWithLevel,committeeLvls,type);
					
			   List<Object[]> parliamentConstIds = parliamentAssemblyDAO.getParliamntIdByConsIds(new ArrayList<Long>(userAccessLocVals));
				   userAccessLocVals = new TreeSet<Long>();
				   getUserAccessVals(parliamentConstIds,userAccessLocVals);// set parliamentConstIds
				   type="parliament";
				   List<Object[]> parliamentList = jbCommitteeDAO.getDistrictWiseCommitteeDetails(null,null,type,userAccessLocVals,allconstIds);
				   locationMapsWithLevel = new HashMap<Long,JanmabhoomiCommitteeVO>();
				   setLocationDataToList(mainVO.getParliamentsList(),parliamentList,locationMapsWithLevel,committeeLvls,type);
			}else{
				List<Object[]> constituencyUserLocaVals = userConstituencyAccessInfoDAO.findByUser(userId);
				if(constituencyUserLocaVals !=null && constituencyUserLocaVals.size() >0){
					userAccessLocVals = new TreeSet<Long>();
					getUserAccessVals(constituencyUserLocaVals,userAccessLocVals);// set constituencyIds
					type="constituency";
					List<Object[]> constList = jbCommitteeDAO.getDistrictWiseCommitteeDetails(null,null,type,userAccessLocVals,null);
					locationMapsWithLevel = new HashMap<Long,JanmabhoomiCommitteeVO>();
					setLocationDataToList(mainVO.getConstsList(),constList,locationMapsWithLevel,committeeLvls,type);
				}else{
					type="district";
					List<Object[]> list = jbCommitteeDAO.getDistrictWiseCommitteeDetails(null,null,type,null,null);
					// 0 levelId,1 levelName,2 count,3 statusId,4 status ,5 ''
					locationMapsWithLevel = new HashMap<Long,JanmabhoomiCommitteeVO>();
					setLocationDataToList(mainVO.getDistrictsList(),list,locationMapsWithLevel,committeeLvls,type);
					
					type="constituency";
					List<Object[]> constList = jbCommitteeDAO.getDistrictWiseCommitteeDetails(null,null,type,null,null);
					locationMapsWithLevel = new HashMap<Long,JanmabhoomiCommitteeVO>();
					setLocationDataToList(mainVO.getConstsList(),constList,locationMapsWithLevel,committeeLvls,type);
					
					type="parliament";
					List<Object[]> parlmntList = jbCommitteeDAO.getDistrictWiseCommitteeDetails(null,null,type,null,null);
					locationMapsWithLevel = new HashMap<Long,JanmabhoomiCommitteeVO>();
					setLocationDataToList(mainVO.getParliamentsList(),parlmntList,locationMapsWithLevel,committeeLvls,type);
				}
			}
		}catch (Exception e) {
			LOG.error("Excepting Occured in getDistrictWiseCommitteeDetails() of JanmabhoomiCommitteeService ", e);
		}
		return mainVO;
	}
	public void setLocationDataToList(List<JanmabhoomiCommitteeVO> locationList,List<Object[]> list,Map<Long,JanmabhoomiCommitteeVO> locationMapsWithLevel,List<JbCommitteeLevel> committeeLvls,String type){
		if(commonMethodsUtilService.isListOrSetValid(list)){
			for(Object[] param :list){
			JanmabhoomiCommitteeVO locVO = locationMapsWithLevel.get(commonMethodsUtilService.getLongValueForObject(param[6]));
				if(locVO == null ){
					 locVO = new JanmabhoomiCommitteeVO(commonMethodsUtilService.getLongValueForObject(param[6]),commonMethodsUtilService.getStringValueForObject(param[7]));
					 locVO.setList(setCommitteeLevels(committeeLvls));
					 if(type !=null && !type.equalsIgnoreCase("parliament"))
					 setlocationLevelsToVO(locVO,param,type);
					
					 if(commonMethodsUtilService.getLongValueForObject(param[6]) >0l){
					locationMapsWithLevel.put(commonMethodsUtilService.getLongValueForObject(param[6]), locVO);
					locationList.add(locVO);
					 }
				}
				Long committeeLvlId = commonMethodsUtilService.getLongValueForObject(param[0]);
				
				if(committeeLvlId.longValue()==1l ){
					locVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[4]));
					locVO.setStatusId(commonMethodsUtilService.getLongValueForObject(param[3]));
					locVO.setColor(commonMethodsUtilService.getStringValueForObject(param[14]));
					locVO.setCommitteeId(commonMethodsUtilService.getLongValueForObject(param[5]));
				}
				JanmabhoomiCommitteeVO levelVO = getMatchedStatusOrLevelVO(locVO.getList(),commonMethodsUtilService.getLongValueForObject(param[0]));
				if(levelVO !=null){
					JanmabhoomiCommitteeVO statusVO = getMatchedStatusOrLevelVO(levelVO.getPositinsList(),commonMethodsUtilService.getLongValueForObject(param[3]));
					if(statusVO !=null){
						statusVO.setCount(statusVO.getCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
					}
					levelVO.setCount(levelVO.getCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
		}
	}
	public void getUserAccessVals(List<Object[]> userLocaVals,Set<Long> userAccessLocVals){
		try {
			if(commonMethodsUtilService.isListOrSetValid(userLocaVals)){
				
				for (Object[] userAccessVal : userLocaVals) {
					userAccessLocVals.add(commonMethodsUtilService.getLongValueForObject(userAccessVal[0]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Excepting Occured in getUserAccessVals() of JanmabhoomiCommitteeService ", e);
		}
	}
	public List<JanmabhoomiCommitteeVO> setCommitteeLevels(List<JbCommitteeLevel> committeeLvls){
		List<JanmabhoomiCommitteeVO> returnList = new ArrayList<JanmabhoomiCommitteeVO>();
		try{
			List<JbCommitteeStatus> committeeStatusList = jbCommitteeStatusDAO.getAll();
			if(commonMethodsUtilService.isListOrSetValid(committeeLvls)){
				for(JbCommitteeLevel committeeLvl :committeeLvls){
					if(committeeLvl.getJbCommitteeLevelId().longValue() != 1l){
						JanmabhoomiCommitteeVO committeeLvlVO = new JanmabhoomiCommitteeVO(committeeLvl.getJbCommitteeLevelId(),committeeLvl.getName());
						committeeLvlVO.setColor("#000");
						committeeLvlVO.setPositinsList(setCommitteeStatus(committeeStatusList));
						returnList.add(committeeLvlVO);
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Excepting Occured in setCommitteeLevels() of JanmabhoomiCommitteeService ", e);
		}
		return returnList;
	}
	
	public List<JanmabhoomiCommitteeVO> setCommitteeStatus(List<JbCommitteeStatus> committeeStatusList){
		List<JanmabhoomiCommitteeVO> returnList = new ArrayList<JanmabhoomiCommitteeVO>();
		try{
			if(commonMethodsUtilService.isListOrSetValid(committeeStatusList)){
				for(JbCommitteeStatus committeeStatus :committeeStatusList){
					//if(!returnList.contains(new JanmabhoomiCommitteeVO(committeeStatus.getJbCommitteeStatusId(),committeeStatus.getStatus()))){
						JanmabhoomiCommitteeVO statusVO =new JanmabhoomiCommitteeVO(committeeStatus.getJbCommitteeStatusId(),committeeStatus.getStatus());
						statusVO.setColor(committeeStatus.getColour());
						returnList.add(statusVO);
					//}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Excepting Occured in setCommitteeLevels() of JanmabhoomiCommitteeService ", e);
		}
		return returnList;
	}
	public Map<Long, Map<Long ,JanmabhoomiCommitteeMemberVO>> getLocationLevelWiseCommiteeStatusCounts(String type,Set<Long> userAccessLocVals) {
		Map<Long,Map<Long,Map<Long,JanmabhoomiCommitteeMemberVO>>> levelCommiteeCounts = new HashMap<Long,Map<Long, Map<Long,JanmabhoomiCommitteeMemberVO>>>();
		Map<Long, Map<Long ,JanmabhoomiCommitteeMemberVO>> returnMap = new HashMap<Long, Map<Long ,JanmabhoomiCommitteeMemberVO>>();
		List<Object[]> committeeWiseTotalMemeberCountObjList = jbCommitteeRoleDAO.getCommitteeWiseTotalMemberCount(type,userAccessLocVals);
		if (committeeWiseTotalMemeberCountObjList != null && committeeWiseTotalMemeberCountObjList.size() > 0) {
			for (Object[] param : committeeWiseTotalMemeberCountObjList) {
				Long locId = commonMethodsUtilService.getLongValueForObject(param[4]);
				Long levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				Long commiteeId = commonMethodsUtilService.getLongValueForObject(param[2]);
				
				Map<Long,Map<Long,JanmabhoomiCommitteeMemberVO>> locationMap= levelCommiteeCounts.get(locId);
				if(locationMap == null){
					locationMap = new HashMap<Long,Map<Long, JanmabhoomiCommitteeMemberVO>>();
					levelCommiteeCounts.put(locId, locationMap);
				}
				Map<Long,JanmabhoomiCommitteeMemberVO> commiteeMap= locationMap.get(levelId);
				if(commiteeMap == null){
					 commiteeMap = new HashMap<Long, JanmabhoomiCommitteeMemberVO>();
					 locationMap.put(levelId, commiteeMap);
				}
				JanmabhoomiCommitteeMemberVO levelVO =commiteeMap.get(commiteeId);
				if(levelVO == null){
					 levelVO = new JanmabhoomiCommitteeMemberVO();
					levelVO.setId(levelId);
					levelVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					commiteeMap.put(commiteeId, levelVO);
				}
				levelVO.setTotalMemberCount(commonMethodsUtilService.getLongValueForObject(param[3]));
				
			}
		}
		
		List<Object[]> committeeWiseTotalMemeberAddedCountObjList = jbCommitteeMemberDAO.getCommitteeWiseTotalMemberAddedCount(type,userAccessLocVals);
		if (committeeWiseTotalMemeberAddedCountObjList != null && committeeWiseTotalMemeberAddedCountObjList.size() > 0) {
			for (Object[] param : committeeWiseTotalMemeberAddedCountObjList) {
				Long locId = commonMethodsUtilService.getLongValueForObject(param[6]);
				Long levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				Long commiteeId = commonMethodsUtilService.getLongValueForObject(param[1]);
				Map<Long,Map<Long,JanmabhoomiCommitteeMemberVO>> locationMap= levelCommiteeCounts.get(locId);
				if(locationMap != null){
				Map<Long,JanmabhoomiCommitteeMemberVO> commiteeMap= locationMap.get(levelId);
				if(commiteeMap != null){
					JanmabhoomiCommitteeMemberVO committeeVO =commiteeMap.get(commiteeId);
					if(committeeVO != null){
						 Long addedMems = commonMethodsUtilService.getLongValueForObject(param[2]);
						 Long maxMems = committeeVO.getTotalMemberCount();
						 if(maxMems.longValue() == addedMems.longValue() && commonMethodsUtilService.getStringValueForObject(param[3]).equalsIgnoreCase("N") && commonMethodsUtilService.getStringValueForObject(param[5]) == "" ){
							 committeeVO.setStatus("Ready for apply");
						 }else if(maxMems.longValue() > addedMems.longValue() && commonMethodsUtilService.getStringValueForObject(param[3]).equalsIgnoreCase("N") && commonMethodsUtilService.getStringValueForObject(param[5]) == "" ) {
							 committeeVO.setStatus("InProgress");
						 }
					}
				}
			}
		}
		}
		if(commonMethodsUtilService.isMapValid(levelCommiteeCounts)){
			for (Entry<Long, Map<Long,Map<Long, JanmabhoomiCommitteeMemberVO>>> entry : levelCommiteeCounts.entrySet()) {
				Map<Long, JanmabhoomiCommitteeMemberVO> levelMap = new HashMap<Long, JanmabhoomiCommitteeMemberVO>();
				returnMap.put(entry.getKey(), levelMap);
				Map<Long,Map<Long, JanmabhoomiCommitteeMemberVO>> levelcommitteeMap = entry.getValue();
				
					for (Entry<Long, Map<Long,JanmabhoomiCommitteeMemberVO>> committee : levelcommitteeMap.entrySet()) {
						JanmabhoomiCommitteeMemberVO  levelVO = new JanmabhoomiCommitteeMemberVO();
						Map<Long, JanmabhoomiCommitteeMemberVO> committeeMap = committee.getValue();
						levelMap.put(committee.getKey(), levelVO);
						for (Entry<Long, JanmabhoomiCommitteeMemberVO> committeesVO : committeeMap.entrySet()) {
							JanmabhoomiCommitteeMemberVO committeeVO = committeesVO.getValue();
							levelVO.setId(committeeVO.getId());
							levelVO.setName(committeeVO.getName());
							//levelVO.getCommitteeIds().add(committeeVO.getId());
							if(committeeVO.getStatus() != null && committeeVO.getStatus().equalsIgnoreCase("Ready for apply")){
								levelVO.setRoleMemberCount(levelVO.getRoleMemberCount()+1l);
							}else if(committeeVO.getStatus() != null && committeeVO.getStatus().equalsIgnoreCase("InProgress")){
								levelVO.setAddedMemberCount(levelVO.getAddedMemberCount()+1l);
							}
						}
					//levelVO.setTotalMemberCount(Long.valueOf(committeeMap.size()));
					
				}
			}
		}
		return returnMap;
	}
	
	public ResultStatus saveJanmabhoomiCommitteeMember(final JanmabhoomiCommitteeMemberVO janmabhoomiCommitteeMemberVO){
		ResultStatus resultStatus  = new ResultStatus();
		try {
			
			if(janmabhoomiCommitteeMemberVO != null){
				JbCommitteeMember jbCommitteeMember = null;
				if(janmabhoomiCommitteeMemberVO.getId() != null){
				 jbCommitteeMember = jbCommitteeMemberDAO.get(janmabhoomiCommitteeMemberVO.getId());
				}
				if(jbCommitteeMember == null){
					jbCommitteeMember = new JbCommitteeMember();
				}
				String status =janmabhoomiCommitteeMemberVO.getStatus() ;
				String statusType ="";
				if(status.equalsIgnoreCase("approval")){
					statusType = "F";
				}else if(status.equalsIgnoreCase("reject")){
					statusType = "R";
				}/*else if(status.equalsIgnoreCase("proposal")){
					statusType = "P";
				}*/
				if(statusType != null &&  statusType.equalsIgnoreCase("R")){
					jbCommitteeMember.setStatus(statusType);
					jbCommitteeMember.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					jbCommitteeMember.setUpdatedUserId(janmabhoomiCommitteeMemberVO.getUserId());
					jbCommitteeMember.setEndDate(dateUtilService.getCurrentDateAndTime());
					jbCommitteeMember.setIsActive("N");
					jbCommitteeMember.setComment(janmabhoomiCommitteeMemberVO.getComment());
					JbCommittee jbCommittee = jbCommitteeDAO.get(janmabhoomiCommitteeMemberVO.getCommitteeId());
					if(jbCommittee != null){
							jbCommittee.setJbCommitteeStatusId(2l);
							jbCommitteeDAO.save(jbCommittee);
					}
					
				}else if(statusType.equalsIgnoreCase("F")){
					JbCommittee jbCommittee = jbCommitteeDAO.get(janmabhoomiCommitteeMemberVO.getCommitteeId());
					if(jbCommittee != null){
						if(jbCommittee.getStartDate() == null){
							jbCommittee.setStartDate(dateUtilService.getCurrentDateAndTime());
							jbCommittee.setJbCommitteeStatusId(2l);
							jbCommitteeDAO.save(jbCommittee);
						}
					}
					String saveStatus = avoidingDuplicateMemberAdding(janmabhoomiCommitteeMemberVO);
					if(saveStatus.equalsIgnoreCase("duplicate")){
						resultStatus.setExceptionMsg("DUPLICATE");
						return resultStatus;
					}
					jbCommitteeMember.setJbCommitteeRoleId(janmabhoomiCommitteeMemberVO.getDesignationId());
					jbCommitteeMember.setMemberName(janmabhoomiCommitteeMemberVO.getName());
					//jbCommitteeMember.setAge(null);
					jbCommitteeMember.setCasteStateId(janmabhoomiCommitteeMemberVO.getCasteId());
					jbCommitteeMember.setCasteCategoryId(janmabhoomiCommitteeMemberVO.getCategoryId());
					jbCommitteeMember.setMobileNo(janmabhoomiCommitteeMemberVO.getMobileNumber());
					//jbCommitteeMember.setDescripation(null);
					if(janmabhoomiCommitteeMemberVO.getTdpCadreId() !=null && janmabhoomiCommitteeMemberVO.getTdpCadreId().longValue() >0L)
						jbCommitteeMember.setTdpCadreId(janmabhoomiCommitteeMemberVO.getTdpCadreId());
					if(janmabhoomiCommitteeMemberVO.getVoterId() !=null && janmabhoomiCommitteeMemberVO.getVoterId().longValue() >0L)
						jbCommitteeMember.setVoterId(janmabhoomiCommitteeMemberVO.getVoterId());
					if(janmabhoomiCommitteeMemberVO.getPartyId() !=null && janmabhoomiCommitteeMemberVO.getPartyId().longValue() >0L)
						jbCommitteeMember.setPartyId(janmabhoomiCommitteeMemberVO.getPartyId());
					jbCommitteeMember.setStartDate(dateUtilService.getCurrentDateAndTime());
					//jbCommitteeMember.setEndDate(dateUtilService.getCurrentDateAndTime());
					jbCommitteeMember.setIsActive("Y");;
					jbCommitteeMember.setImagePath(janmabhoomiCommitteeMemberVO.getImageUrl() !=null?janmabhoomiCommitteeMemberVO.getImageUrl():null);
					jbCommitteeMember.setJbCommitteeEnrollmentId(janmabhoomiCommitteeMemberVO.getEnrollmentYrId());
					jbCommitteeMember.setInsertedUserId(janmabhoomiCommitteeMemberVO.getUserId());
					jbCommitteeMember.setUpdatedUserId(janmabhoomiCommitteeMemberVO.getUserId());
					jbCommitteeMember.setStatus(statusType);
					jbCommitteeMember.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					jbCommitteeMember.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					
				}
				
				jbCommitteeMemberDAO.save(jbCommitteeMember);
				resultStatus.setExceptionMsg("SUCCESS");
			}
		} catch (Exception e) {
			resultStatus.setExceptionMsg("FAIL");
			e.printStackTrace();
			LOG.error("Excepting Occured in saveJanmabhoomiCommitteeMember() of JanmabhoomiCommitteeService ", e);
		}
		return resultStatus;
		
	}
	
	public String avoidingDuplicateMemberAdding(JanmabhoomiCommitteeMemberVO janmabhoomiCommitteeMemberVO){
		String status = "";
		try{
			Long commiteeId = janmabhoomiCommitteeMemberVO.getCommitteeId();
			List<Long> tdpCadreIdsList = new ArrayList<Long>();
			List<String> voterIdCardsList = new ArrayList<String>();
			List<Object[]> commiteesMembersList = jbCommitteeMemberDAO.getCommitteeMembersByCommiteeId(commiteeId);
			////0 jbCommitteeMemberId,1 jbCommitteeRoleId,2 memberName,3 voterIDCardNo,4 tdpCadreId
			if(commiteesMembersList !=null && commiteesMembersList.size() >0) {
				for(Object[] param: commiteesMembersList){
					if(commonMethodsUtilService.getStringValueForObject(param[3]) !="")
					voterIdCardsList.add(commonMethodsUtilService.getStringValueForObject(param[3]));
					if(commonMethodsUtilService.getStringValueForObject(param[4]) !="")
						tdpCadreIdsList.add(commonMethodsUtilService.getLongValueForObject(param[4]));
				}
			}
			if(janmabhoomiCommitteeMemberVO.getTdpCadreId() !=null){
				if(tdpCadreIdsList.contains(janmabhoomiCommitteeMemberVO.getTdpCadreId())){
					status = "duplicate";
				}
			}else if(janmabhoomiCommitteeMemberVO.getVoterCardNo() !=null){
				if(voterIdCardsList.contains(janmabhoomiCommitteeMemberVO.getVoterCardNo())){
					status = "duplicate";
				}
			}
			
		}catch (Exception e) {
			LOG.error("Excepting Occured in avoidingDuplicateMemberAdding() of JanmabhoomiCommitteeService ", e);
		}
		return status;
	}
	public List<JanmabhoomiCommitteeVO> getJanmabhoomiCommitteesByLocIdAndCommLvlId(String fromDate,String endDate,Long locationId,Long locLvlId,Long committeeLvlId,Long status,Long userId){
		 List<JanmabhoomiCommitteeVO> returnList = new ArrayList<JanmabhoomiCommitteeVO>();
	try {
		List<Long> matchedConstIds = new ArrayList<Long>();
		Map<Long,JanmabhoomiCommitteeVO> committeeMaps = null;
		List<Object[]> list = null;
		if (locLvlId != null && locLvlId.longValue() >0l && locationId != null && locationId.longValue() >0) { // location wise 
           List<Long> locationIdList = new ArrayList<Long>();
           locationIdList.add(locationId);
			if (locLvlId != null && locLvlId.longValue() == 10l) { 
				List<Object[]> districtUserLocaVals = districtUserLocaVals = userDistrictAccessInfoDAO.getLocationIdList(userId);
				if (districtUserLocaVals != null && districtUserLocaVals.size() > 0) { // district user
					Set<Long> userDistrictAccessLocVals = new TreeSet<Long>();
					getUserAccessVals(districtUserLocaVals,userDistrictAccessLocVals);// set districtIds

					List<Object[]> constituencyUserLocaVals = constituencyDAO.getConstituencysByDistrictId(new ArrayList<Long>(userDistrictAccessLocVals));

					Set<Long> userConstAccessLocVals = new TreeSet<Long>();
					getUserAccessVals(constituencyUserLocaVals,userConstAccessLocVals);// set constituencyIds
					List<Long> constIds = parliamentAssemblyDAO.getConstituencyIdsByParliamntId(locationId); // constituencyIds based on district user parliament constituencies
					if (constituencyUserLocaVals != null && constituencyUserLocaVals.size() > 0) {
						for (Long userConstIds : userConstAccessLocVals) {
							if (constIds.contains(userConstIds))
								matchedConstIds.add(userConstIds);
						}
					}
				}
			}
			list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, locationIdList, committeeLvlId,status,matchedConstIds);
		}else{// dashboard block (committee level wise)
			boolean districtUserStatus = false;  
			boolean districtUserConstStatus = false; 
			boolean constUserStatus = false;
			
			
			List<Object[]> districtUserLocaVals = districtUserLocaVals = userDistrictAccessInfoDAO.getLocationIdList(userId);
			Set<Long> userDistrictAccessLocVals = new TreeSet<Long>(); // districtids of districtUser
			
			Set<Long> ditrictUserConstIdsAccessLocVals = new TreeSet<Long>(); // constIds of districtUser
			
			
			List<Object[]> constituencyUserLocaVals = userConstituencyAccessInfoDAO.findByUser(userId);
			Set<Long> userConstAccessLocVals = new TreeSet<Long>(); // constIds of constUser
			
			if(districtUserLocaVals !=null && districtUserLocaVals.size() >0){
				districtUserStatus = true;
				getUserAccessVals(districtUserLocaVals,userDistrictAccessLocVals);// set districtIds
				
				List<Object[]> ditrictUserConstIdsLsit = constituencyDAO.getConstituencysByDistrictId(new ArrayList<Long>(userDistrictAccessLocVals));
				getUserAccessVals(ditrictUserConstIdsLsit,ditrictUserConstIdsAccessLocVals);// set constituencyIds
				
				if(districtUserLocaVals !=null && districtUserLocaVals.size() >0){
					districtUserConstStatus = true;
				}
			}
			if(constituencyUserLocaVals !=null && constituencyUserLocaVals.size() >0){
				constUserStatus = true;
				getUserAccessVals(constituencyUserLocaVals,userConstAccessLocVals);// set districtIds
			}
			
			if(committeeLvlId == 1l){
				if(districtUserStatus){ // district user
					locLvlId =3l;
					list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, new ArrayList<Long>(userDistrictAccessLocVals), committeeLvlId,status,matchedConstIds);
				}else{// for any user
					list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, null, committeeLvlId,status,matchedConstIds);					
				}
			}else if(committeeLvlId == 2l){
				if(districtUserStatus){ // district user
					List<Long> teshilIdsList = tehsilConstituencyDAO.getTehsilIdsByConstituencyId(new ArrayList<Long>(ditrictUserConstIdsAccessLocVals));
					locLvlId =5l;
					list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, teshilIdsList, committeeLvlId,status,matchedConstIds);					
				}else if(constUserStatus){//constituency user
					List<Long> teshilIdsList = tehsilConstituencyDAO.getTehsilIdsByConstituencyId(new ArrayList<Long>(userConstAccessLocVals));
					locLvlId =5l;
					list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, teshilIdsList, committeeLvlId,status,matchedConstIds);					
				}else{ // for any user
					list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, null, committeeLvlId,status,matchedConstIds);					

				}
			}else if(committeeLvlId == 3l || committeeLvlId == 4l){
				if(districtUserStatus){// district user
					List<Long> localElectionIdsList = localElectionBodyDAO.getLocalElectionBodyIdsByDistrictIdsList(new ArrayList<Long>(userDistrictAccessLocVals));
					locLvlId =7l;
					list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, localElectionIdsList, committeeLvlId,status,matchedConstIds);					
				}else if(constUserStatus){//constituency user
					List<Long> teshilIdsList = tehsilConstituencyDAO.getTehsilIdsByConstituencyId(new ArrayList<Long>(userConstAccessLocVals));
					List<Long> localElectionIdsList = localElectionBodyDAO.getMuncipalitiesAndCorporationsForConstituency(teshilIdsList);
					locLvlId =7l;
					list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, localElectionIdsList, committeeLvlId,status,matchedConstIds);					
				}else{ // for any user
					list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, null, committeeLvlId,status,matchedConstIds);					

				}
			}else if(committeeLvlId == 5l){
				if(districtUserStatus){// district user
					if(districtUserConstStatus){
						List<Long> teshilIdsList = tehsilConstituencyDAO.getTehsilIdsByConstituencyId(new ArrayList<Long>(ditrictUserConstIdsAccessLocVals));
						List<Long> panchayatIdsList = panchayatDAO.getPanchayatIdsBytehsilIdsList(teshilIdsList);
						locLvlId =6l;
						list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, panchayatIdsList, committeeLvlId,status,matchedConstIds);					
					}
				}else if(constUserStatus){//constituency user
					List<Long> teshilIdsList = tehsilConstituencyDAO.getTehsilIdsByConstituencyId(new ArrayList<Long>(userConstAccessLocVals));
					List<Long> panchayatIdsList = panchayatDAO.getPanchayatIdsBytehsilIdsList(teshilIdsList);
					locLvlId =6l;
					list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, panchayatIdsList, committeeLvlId,status,matchedConstIds);		
				}else{ // for any user
					list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, null, committeeLvlId,status,matchedConstIds);					

				}
			}else if(committeeLvlId == 6l || committeeLvlId == 7l){
				if(districtUserStatus){// district user
					if(districtUserConstStatus){
						locLvlId =8l;
						list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, new ArrayList<Long>(ditrictUserConstIdsAccessLocVals), committeeLvlId,status,matchedConstIds);					
					}
				}else if(constUserStatus){//constituency user
					locLvlId =8l;
					list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, new ArrayList<Long>(userConstAccessLocVals), committeeLvlId,status,matchedConstIds);		
				}else{ // for any user
					list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, null, committeeLvlId,status,matchedConstIds);					
				}
			}
		}
		
			committeeMaps = new LinkedHashMap<Long,JanmabhoomiCommitteeVO>();
			setCommitteesData(committeeMaps,list,status,"");
		
		if(commonMethodsUtilService.isMapValid(committeeMaps)){
			returnList.addAll(committeeMaps.values());
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		LOG.error("Excepting Occured in saveJanmabhoomiCommitteeMember() of JanmabhoomiCommitteeService ", e);
	}
	return returnList;
}
	
	public void setCommitteesData(Map<Long,JanmabhoomiCommitteeVO> committeeMaps,List<Object[]> list,Long status,String type){
		
		try {
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] param : list) {
					JanmabhoomiCommitteeVO committeeVO = committeeMaps.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(committeeVO == null){
						committeeVO = new JanmabhoomiCommitteeVO();
						committeeMaps.put(commonMethodsUtilService.getLongValueForObject(param[0]), committeeVO);
					}
						committeeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						committeeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						if(type != null && type.equalsIgnoreCase("maxMember")){
							committeeVO.setTotalCommitteeCnt(commonMethodsUtilService.getLongValueForObject(param[2]));//maxMemCount
						}
						if(type != null && type.equalsIgnoreCase("addedMember")){
							Long addedMem = commonMethodsUtilService.getLongValueForObject(param[2]);
							String isConfirmed = commonMethodsUtilService.getStringValueForObject(param[3]);
							String completedDate = commonMethodsUtilService.getStringValueForObject(param[5]);
							committeeVO.setInprogressCommitteePerc(completedDate);
							committeeVO.setNotStartedCommitteePerc(isConfirmed);
							committeeVO.setNotStartedCommitteeCnt(addedMem);
						}
						
					
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Excepting Occured in setCommitteesData() of JanmabhoomiCommitteeService ", e);
		}
	}
	
	public JanmabhoomiCommitteeMemberVO searchByMemberIdOrVoterId(Long locationLevel,Long locationValue,String memberShipCardNo,String voterCardNo){
		JanmabhoomiCommitteeMemberVO mainVO=new JanmabhoomiCommitteeMemberVO();
		try {
			//searchTdpCadreDetailsBySearchCriteriaForCadreCommitte(Long locationLevel,Long locationId, String searchName,String memberShipCardNo,
			//String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender,int startIndex,int maxIndex,boolean isRemoved,Long enrollmentId,String searchType)
			CadreCommitteeVO cadreCommitteeVO = cadreCommitteeService.searchTdpCadreDetailsBySearchCriteriaForCadreCommitte(locationLevel,locationValue, "",memberShipCardNo, 
					voterCardNo, "", "",0L,"",0L,0L,"","",0,50,false,0L,"NominatedPostSearch");
		    if(cadreCommitteeVO !=null && cadreCommitteeVO.getPreviousElections() !=null && cadreCommitteeVO.getPreviousElections().size() ==0 && cadreCommitteeVO.getPreviousRoles() == null){
				
		    	//getOnliCadRegistrSearchVoteDetails(constencyId,mandalId,villageId,boothId,type,typeVal)
		    	if(voterCardNo !=null && voterCardNo.length() >1){
		    		
			    	//voterVoList=cadreRegistrationService.getOnliCadRegistrSearchVoteDetails(constencyId,mandalId,villageId,null,"voterId",voterCardNo);
			    	List<Object[]> voterObjList = boothPublicationVoterDAO.getVoteDetailsByLocation(locationValue,locationLevel,"voterId",voterCardNo);
			    	//0 voterId,1 name,2 relationshipType,3 relativeName,4gender,5 age,6 voterIDCardNo,7 houseNo,8 imagePath
			    	if(voterObjList !=null && voterObjList.size() >0){
			    		for(Object[] param:voterObjList){
			    			mainVO.setName(param[1] !=null?commonMethodsUtilService.getStringValueForObject(param[1]):null);
		    				mainVO.setRelationshipType(param[2] !=null?commonMethodsUtilService.getStringValueForObject(param[2]):null);
		    				mainVO.setRelativeName(param[3] !=null?commonMethodsUtilService.getStringValueForObject(param[3]):null);
		    				mainVO.setGender(param[4] !=null?commonMethodsUtilService.getStringValueForObject(param[4]):null);
		    				mainVO.setAge(param[5] !=null?commonMethodsUtilService.getLongValueForObject(param[5]):null);
		    				mainVO.setHouseNo(param[7] !=null?commonMethodsUtilService.getStringValueForObject(param[7]):null);
							//mainVO.setMobileNumber(param[0] !=null?commonMethodsUtilService.getStringValueForObject(param[1]):null);
							mainVO.setVoterCardNo(param[6] !=null?commonMethodsUtilService.getStringValueForObject(param[6]):null);
							
							mainVO.setVoterId(param[0] !=null?commonMethodsUtilService.getLongValueForObject(param[0]):null);
							//mainVO.setMemberShipCardId(param[0] !=null?commonMethodsUtilService.getStringValueForObject(param[1]):null);
							//mainVO.setTdpCadreId(param[0] !=null?commonMethodsUtilService.getLongValueForObject(param[1]):null);
							
							if((param[8]) !=null)
							 mainVO.setImageURL("https://mytdp.com/voter_images/"+commonMethodsUtilService.getStringValueForObject(param[8]));
							
			    		 }
			    	 }
			    	/*if(voterVoList !=null && voterVoList.size() >0){
			    		for(VoterSearchVO voterDetailsVO:voterVoList){
			    			if(voterDetailsVO !=null){
			    				mainVO.setName(voterDetailsVO.getName());
			    				mainVO.setRelationshipType(voterDetailsVO.getRelationshipType() != null ? voterDetailsVO.getRelationshipType():null);
			    				mainVO.setRelativeName(voterDetailsVO.getRelativeName() != null ? voterDetailsVO.getRelativeName():null);
			    				mainVO.setGender(voterDetailsVO.getGender() != null ? voterDetailsVO.getGender():null);
			    				mainVO.setAge(voterDetailsVO.getAge() != null ? voterDetailsVO.getAge():null);
			    				mainVO.setHouseNo(voterDetailsVO.getHouseNo() != null ? voterDetailsVO.getHouseNo():null);
								mainVO.setMobileNumber(voterDetailsVO.getActualMobiNumber() != null?voterDetailsVO.getActualMobiNumber():null);
								mainVO.setVoterCardNo(voterDetailsVO.getVoterIDCardNo()!= null ? voterDetailsVO.getVoterIDCardNo():null);
								if(voterDetailsVO.getVoterId()!= null)
								mainVO.setVoterId(Long.valueOf(voterDetailsVO.getVoterId().toString()));
								mainVO.setMemberShipCardId(voterDetailsVO.getMemberShipNo()!= null ? voterDetailsVO.getMemberShipNo():null);
								mainVO.setTdpCadreId(voterDetailsVO.getTdpCadreId() == null?null:voterDetailsVO.getTdpCadreId());
								if(mainVO.getMemberShipCardId() !=null){ //from tdpcadre table
									if(voterDetailsVO.getTotalImagePathStr() !=null)
									   mainVO.setImageURL("https://www.mytdp.com/images/cadre_images/"+voterDetailsVO.getTotalImagePathStr());
								}else{ // from voter table
									if(voterDetailsVO.getTotalImagePathStr() !=null)
										   mainVO.setImageURL("https://mytdp.com/voter_images/"+voterDetailsVO.getTotalImagePathStr());
								}
								
			    			}
			    		}
			    	}*/
		    	}
			}else {
				for(CadreCommitteeVO candidateDetailsVO:cadreCommitteeVO.getPreviousRoles()){
					mainVO.setName(candidateDetailsVO.getCadreName());
					mainVO.setMemberShipCardId(candidateDetailsVO.getMemberShipCardId() == null?null:candidateDetailsVO.getMemberShipCardId()); // memberShipCardId
					mainVO.setTdpCadreId(candidateDetailsVO.getTdpCadreId() == null?null:candidateDetailsVO.getTdpCadreId());
					if(candidateDetailsVO.getImageURL() !=null){
						mainVO.setImageURL("https://www.mytdp.com/images/cadre_images/"+candidateDetailsVO.getImageURL());
					}else{
						mainVO.setImageURL(candidateDetailsVO.getImageURL() == null?null:candidateDetailsVO.getImageURL());
					}
					mainVO.setMobileNumber(candidateDetailsVO.getMobileNo() == null?null:candidateDetailsVO.getMobileNo());
					mainVO.setVoterCardNo(candidateDetailsVO.getVoterCardNo() == null?null:candidateDetailsVO.getVoterCardNo());
					if(candidateDetailsVO.getCadreVoterId() !=null)
					mainVO.setVoterId(candidateDetailsVO.getCadreVoterId());
				}
			}
	   } catch (Exception e) {
			LOG.error("Excepting Occured in searchByMemberIdOrVoterId() of JanmabhoomiCommitteeService ", e);
		}
		if(mainVO.getVoterCardNo() !=null && mainVO.getVoterCardNo().isEmpty()){
			mainVO.setVoterCardNo(voterCardNo);
		}
		if(mainVO.getName() == null && mainVO.getMemberShipCardId()==null && mainVO.getTdpCadreId() ==0 && mainVO.getVoterId() ==null)
			mainVO = null;
		return mainVO;
	}

	@Override
	public List<JanmabhoomiCommitteeVO> getStatewiseCastNamesByCasteCategoryGroupId(List<Long> categoryGrouId) {
		List<JanmabhoomiCommitteeVO> casteVOList =new ArrayList<JanmabhoomiCommitteeVO>();
		List<Object[]> casteWiseObjList = casteStateDAO.getStatewiseCastNamesByCasteCategoryGroupId(categoryGrouId,1L);//state 1 as static
		try{
			if(casteWiseObjList !=null && casteWiseObjList.size() >0){
				for(Object[] param:casteWiseObjList){
					JanmabhoomiCommitteeVO casteVO = new JanmabhoomiCommitteeVO();
					casteVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
					casteVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					casteVOList.add(casteVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Excepting Occured in getStatewiseCastNamesByCasteCategoryGroupId() of JanmabhoomiCommitteeService ", e);
		}
		return casteVOList;
	}

	@Override
	public List<JanmabhoomiCommitteeVO> getCategories() {
		List<JanmabhoomiCommitteeVO> categotiesVOList =new ArrayList<JanmabhoomiCommitteeVO>();
		List<Object[]> categories = casteCategoryDAO.getAllCasteCategoryDetails();
		try{
			if(categories !=null && categories.size() >0){
				for(Object[] param:categories){
					JanmabhoomiCommitteeVO categotiesVO = new JanmabhoomiCommitteeVO();
					categotiesVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					categotiesVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					categotiesVOList.add(categotiesVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Excepting Occured in getCategories() of JanmabhoomiCommitteeService ", e);
		}
		return categotiesVOList;
	}
	
	public ResultStatus updateCommitteStatusByCommiteeId(Long committeeId,String status){
		ResultStatus resultStatus  = new ResultStatus();
		try {
			Long totalRoleCount = jbCommitteeRoleDAO.getTotalRoleMemberCountByCommitteId(committeeId);
			Long totalApprovedRoleCount = jbCommitteeMemberDAO.getMemberApprovedCountByCommitteId(committeeId);
			if(totalRoleCount !=null && totalApprovedRoleCount !=null && totalRoleCount.longValue() == totalApprovedRoleCount.longValue() && status != null && status.equalsIgnoreCase("approve")){
				JbCommittee jbCommittee = jbCommitteeDAO.get(committeeId);
				if(jbCommittee !=null){
					if(jbCommittee.getIsDeleted().equalsIgnoreCase("N")){
						jbCommittee.setIsCommitteeConfirmed("Y");
						jbCommittee.setCompletedDate(dateUtilService.getCurrentDateAndTime());
						jbCommittee.setJbCommitteeStatusId(4l);
						JbCommittee updatedJbCommittee = jbCommitteeDAO.save(jbCommittee);
						if(updatedJbCommittee !=null){
							resultStatus.setExceptionMsg("SUCCESS");
						}
					}else{
						resultStatus.setExceptionMsg("Committee Not in Active");
					}
				}
			}else if(totalRoleCount !=null && totalApprovedRoleCount !=null && totalRoleCount.longValue() == totalApprovedRoleCount.longValue() && status != null && status.equalsIgnoreCase("readyforapproval")){
				JbCommittee jbCommittee = jbCommitteeDAO.get(committeeId);
				if(jbCommittee !=null){
					if(jbCommittee.getIsDeleted().equalsIgnoreCase("N")){
						jbCommittee.setJbCommitteeStatusId(3l);
						JbCommittee updatedJbCommittee = jbCommitteeDAO.save(jbCommittee);
						if(updatedJbCommittee !=null){
							resultStatus.setExceptionMsg("SUCCESS");
						}
					}else{
						resultStatus.setExceptionMsg("Committee Not in Active");
					}
				}
				//resultStatus.setExceptionMsg("NotFilled");
			}else if(totalRoleCount !=null && totalApprovedRoleCount !=null && totalRoleCount.longValue() == totalApprovedRoleCount.longValue() && status != null && status.equalsIgnoreCase("reject")){
				JbCommittee jbCommittee = jbCommitteeDAO.get(committeeId);
				if(jbCommittee !=null){
					if(jbCommittee.getIsDeleted().equalsIgnoreCase("N")){
						List<JbCommitteeMember> members = jbCommitteeMemberDAO.updateMembersToRejectStatus(committeeId);
						if(commonMethodsUtilService.isListOrSetValid(members)){
							for (JbCommitteeMember jbCommitteeMember : members) {
								jbCommitteeMember.setStatus("R");
								jbCommitteeMember.setIsActive("N");
								jbCommitteeMemberDAO.save(jbCommitteeMember);
							}
						}
						jbCommittee.setJbCommitteeStatusId(2l);
						JbCommittee updatedJbCommittee = jbCommitteeDAO.save(jbCommittee);
						resultStatus.setExceptionMsg("SUCCESS");
						
					}else{
						resultStatus.setExceptionMsg("Committee Not in Active");
					}
				}
				//resultStatus.setExceptionMsg("NotFilled");
			}
		}catch (Exception e) {
			resultStatus.setExceptionMsg("FAIL");
			e.printStackTrace();
			LOG.error("Excepting Occured in updateCommitteStatusByCommiteeId() of JanmabhoomiCommitteeService ", e);
		}
		return resultStatus;
	}
	
	public JanmabhoomiCommitteeMemberVO setStatusToVO(Long commiteeId,JanmabhoomiCommitteeMemberVO committeeVO){
		String status = "";
		List<Object[]> commiteeStatusObjList = jbCommitteeDAO.getJbCommitteeStatusByCommiteeId(commiteeId);
		//0 jbCommitteeId,1 committeeName,2 isCommitteeConfirmed,3 startDate,4 completedDate
		  if(commiteeStatusObjList !=null && commiteeStatusObjList.size() >0){
			  for(Object[] param:commiteeStatusObjList){
				  	String confirmedStatus = commonMethodsUtilService.getStringValueForObject(param[2]);
					String startDate = commonMethodsUtilService.getStringValueForObject(param[3]);
					String completedDate = commonMethodsUtilService.getStringValueForObject(param[4]);
					
					if(confirmedStatus.equalsIgnoreCase("N") && startDate == ""){
						status = "Not Started";
						committeeVO.setStatus(status);
						return committeeVO;
					}else if(confirmedStatus.equalsIgnoreCase("Y")  && completedDate != ""){
						status = "Approved";
						committeeVO.setStatus(status);
						return committeeVO;
					}
					
			  }
		  }
		  
		  if(committeeVO.getAddedMemberCount() != null && committeeVO.getAddedMemberCount().longValue() >0l && committeeVO.getAddedMemberCount().longValue() < committeeVO.getRoleMemberCount().longValue()) {
			  status = "InProgress";
			  committeeVO.setStatus(status);
		     return committeeVO;
		  }
		  else if(committeeVO.getRoleMemberCount() != null && committeeVO.getRoleMemberCount().longValue() >0l && committeeVO.getAddedMemberCount().longValue() == committeeVO.getRoleMemberCount().longValue()) {
			  status = "Ready For Approval";
			  committeeVO.setStatus(status);
		     return committeeVO;
		  }else if(committeeVO.getRoleMemberCount() != null && committeeVO.getRoleMemberCount().longValue() >0l && committeeVO.getAddedMemberCount().longValue() == 0L) {
			   status = "Not Started";
			  committeeVO.setStatus(status);
		     return committeeVO;
		  }
		  return committeeVO;
	}
	
	public void setlocationLevelsToVO(JanmabhoomiCommitteeVO locVO,Object[] param,String type){
		int i=8;
		if(type != null && !type.equalsIgnoreCase("district") && !type.equalsIgnoreCase("parliament")){
			if(param[i] !=null){
				locVO.setDistrictId(!param[i].toString().isEmpty()?commonMethodsUtilService.getLongValueForObject(param[i]):null);
				i++;
				locVO.setDistrictName(!param[i].toString().isEmpty()?commonMethodsUtilService.getStringValueForObject(param[i]):null);
				i++;
			}
		}
		if(type != null && !type.equalsIgnoreCase("constituency")){
			if(param[i] !=null){
				locVO.setConstituencyId(!param[i].toString().isEmpty()?commonMethodsUtilService.getLongValueForObject(param[i]):null);
				i++;
				locVO.setConstituencyName(!param[i].toString().isEmpty()?commonMethodsUtilService.getStringValueForObject(param[i]):null);
				i++;
			}
		}
		if(type != null && !type.equalsIgnoreCase("mandal")){
		 if(param[i] !=null){
				locVO.setMandalId(!param[i].toString().isEmpty()?commonMethodsUtilService.getLongValueForObject(param[i]):null);
				i++;
				locVO.setMandalName(!param[i].toString().isEmpty()?commonMethodsUtilService.getStringValueForObject(param[i]):null);
				i++;
		  }
		}
		if(type != null && !type.equalsIgnoreCase("panchayat")){
			if(param[i] !=null){
				locVO.setPanchayatId(!param[i].toString().isEmpty()?commonMethodsUtilService.getLongValueForObject(param[i]):null);
				i++;
				locVO.setPanchayatName(!param[i].toString().isEmpty()?commonMethodsUtilService.getStringValueForObject(param[i]):null);
				i++;
			}
		}
	}


public JanmabhoomiCommitteeVO getJbCommitteeStatusCount(String fromDateStr, String toDateStr,Long userId) {
    JanmabhoomiCommitteeVO  mainVO = new JanmabhoomiCommitteeVO();
    boolean districtStatus = false;
    boolean constituencyStatus = false;
    String type = null;
    List<Object[]> districtUserLocaVals = districtUserLocaVals = userDistrictAccessInfoDAO.getLocationIdList(userId);
    List<Object[]> constituencyUserLocaVals = userConstituencyAccessInfoDAO.findByUser(userId);
	Set<Long> userAccessLocVals = null;
	
	if(districtUserLocaVals != null && districtUserLocaVals.size() >0) {
		districtStatus = true;
		type = "district";
		userAccessLocVals = new TreeSet<Long>();
		getUserAccessVals(districtUserLocaVals,userAccessLocVals);// set districtIds
	}else if(constituencyUserLocaVals != null && constituencyUserLocaVals.size() >0) {
		constituencyStatus = true;
		type = "constituency";
		userAccessLocVals = new TreeSet<Long>();
		getUserAccessVals(constituencyUserLocaVals,userAccessLocVals);// set constituencyIds
	}
   try{     
          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date startDate = null;
        Date endDate = null;
        if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
          startDate = sdf.parse(fromDateStr);
          endDate = sdf.parse(toDateStr);
        } 
     
   List<JbCommitteeStatus> committeeStatusList= jbCommitteeStatusDAO.getAll();
   mainVO.setCommitteeStatusVOList(getCommitteeStatusList(committeeStatusList));
   
   Long districtKey = null;
   Map<Long,JanmabhoomiCommitteeVO> locationDtlsMap =new HashMap<Long, JanmabhoomiCommitteeVO>(0);   
   List<JbCommitteeLevel> committeeLevels= jbCommitteeLevelDAO.getAll();
   if(commonMethodsUtilService.isListOrSetValid(committeeLevels)){
     for (JbCommitteeLevel jbCommitteeLevel : committeeLevels) {
        JanmabhoomiCommitteeVO vo=new JanmabhoomiCommitteeVO();
         vo.setId(jbCommitteeLevel.getJbCommitteeLevelId());
         vo.setName(jbCommitteeLevel.getName());
         if(jbCommitteeLevel.getName().equalsIgnoreCase("District"))
        	 districtKey = jbCommitteeLevel.getJbCommitteeLevelId();
         vo.setCommitteeStatusVOList(getCommitteeStatusList(committeeStatusList));
         locationDtlsMap.put(vo.getId(),vo);
        }
   }  
   
   if(constituencyStatus == true){
	   locationDtlsMap.remove(districtKey);
   }
   
   List<Object[]> committeeList = null;
   if(districtStatus == true || constituencyStatus == true){
	   committeeList = jbCommitteeDAO.getJbCommitteeStatusCount(type,userAccessLocVals);
   	  //0 count(jbCommitteeId),1 isCommitteeConfirmed,2 startDate,3 completedDate,
	 //4 jbCommitteeLevelId,5 name,6 jbCommitteeStatusId,7 status
   }else{
	   committeeList = jbCommitteeDAO.getJbCommitteeStatusCount(null,null);// get all level data
   }
   if (committeeList!=null && committeeList.size()>0){
      for (Object[] objects : committeeList) {
       JanmabhoomiCommitteeVO vo = locationDtlsMap.get(commonMethodsUtilService.getLongValueForObject(objects[4]));
            if(vo != null){
            	JanmabhoomiCommitteeVO matchedStatusVoForCommiteeLevel = getMatchedStatusVO(vo.getCommitteeStatusVOList(),commonMethodsUtilService.getLongValueForObject(objects[6]));
            	  if(matchedStatusVoForCommiteeLevel !=null){
            		  matchedStatusVoForCommiteeLevel.setStatusCount(commonMethodsUtilService.getLongValueForObject(objects[0]));
            		  vo.setTotalCommitteeCnt(vo.getTotalCommitteeCnt()+commonMethodsUtilService.getLongValueForObject(objects[0]));
            	  }
                }
            
        	JanmabhoomiCommitteeVO matchedStatusVoForAllCommiteeLevels = getMatchedStatusVO(mainVO.getCommitteeStatusVOList(),commonMethodsUtilService.getLongValueForObject(objects[6]));
            if(matchedStatusVoForAllCommiteeLevels !=null){
            	matchedStatusVoForAllCommiteeLevels.setStatusCount(matchedStatusVoForAllCommiteeLevels.getStatusCount()+commonMethodsUtilService.getLongValueForObject(objects[0]));
            	mainVO.setTotalCommitteeCnt(mainVO.getTotalCommitteeCnt()+commonMethodsUtilService.getLongValueForObject(objects[0]));
            }
           }
          }
      mainVO.getLevelWisecommitteeStatusVOList().addAll(locationDtlsMap.values());
   
       List<JanmabhoomiCommitteeVO> statusVo= mainVO.getCommitteeStatusVOList();
	   for(JanmabhoomiCommitteeVO mainStatusVO:statusVo){
		   mainStatusVO.setStatusPercentage(calculatePercentage(mainVO.getTotalCommitteeCnt(), mainStatusVO.getStatusCount()));
		}
	   
	   List<JanmabhoomiCommitteeVO> levelstatusVo=  mainVO.getLevelWisecommitteeStatusVOList();;
	   for(JanmabhoomiCommitteeVO levelWiseStatusVO:levelstatusVo){
		   for(JanmabhoomiCommitteeVO innerlevelStatusVO:levelWiseStatusVO.getCommitteeStatusVOList()){
			   innerlevelStatusVO.setStatusPercentage(calculatePercentage(levelWiseStatusVO.getTotalCommitteeCnt(), innerlevelStatusVO.getStatusCount()));
		   }
		}
  
   }catch(Exception e){
      Log.error("Exception raised in JanmabhoomiCommitteeService method of JanmabhoomiCommitteeService"+e);
  }
  return mainVO;
  }

 public List<JanmabhoomiCommitteeVO> getCommitteeStatusList(List<JbCommitteeStatus> committeeStatusList){
	List<JanmabhoomiCommitteeVO> statusTempList =new ArrayList<JanmabhoomiCommitteeVO>();
	 if(committeeStatusList !=null && committeeStatusList.size() >0){
		   for(JbCommitteeStatus commiteeStatus :committeeStatusList){
			   JanmabhoomiCommitteeVO committeeStatusVO = new JanmabhoomiCommitteeVO();
			   committeeStatusVO.setStatusId(commiteeStatus.getJbCommitteeStatusId());
			   committeeStatusVO.setStatus(commiteeStatus.getStatus());
			   committeeStatusVO.setColor(commiteeStatus.getColour());
			   statusTempList.add(committeeStatusVO);
		   }
	   }
	 return statusTempList;
 }
 
 public JanmabhoomiCommitteeVO getMatchedStatusVO(List<JanmabhoomiCommitteeVO> voList, Long id) {
	    if (voList != null && voList.size() > 0 && id != null && id > 0l) {
	      for (JanmabhoomiCommitteeVO statusVO : voList) {
	        if (statusVO.getStatusId().equals(id))
	          return statusVO;
	      }
	    }
	    return null;
	  }
 
 public JanmabhoomiCommitteeVO getMatchedStatusOrLevelVO(List<JanmabhoomiCommitteeVO> voList, Long id) {
	    if (voList != null && voList.size() > 0 && id != null && id > 0l) {
	      for (JanmabhoomiCommitteeVO statusVO : voList) {
	        if (statusVO.getId().equals(id))
	          return statusVO;
	      }
	    }
	    return null;
	  }
}