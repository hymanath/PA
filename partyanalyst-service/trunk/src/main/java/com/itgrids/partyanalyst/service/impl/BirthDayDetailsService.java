package com.itgrids.partyanalyst.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ILeaderOccasionDAO;
import com.itgrids.partyanalyst.dao.ILeaderOccasionWishDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.BirthDayDetailsVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.model.LeaderOccasionWishDetails;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IBirthDayDetailsService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

public class BirthDayDetailsService implements IBirthDayDetailsService {
	private final static Logger LOG = Logger.getLogger(BirthDayDetailsService.class);
	private ILeaderOccasionDAO leaderOccasionDAO;
	private ILeaderOccasionWishDetailsDAO leaderOccasionWishDetailsDAO;
	private TransactionTemplate transactionTemplate = null;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private ITdpCadreCandidateDAO tdpCadreCandidateDAO;
	private ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	private ITdpCadreDAO tdpCadreDAO;
	private SetterAndGetterUtilService setterAndGetterUtilService = new SetterAndGetterUtilService();
	
	
	
	
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	public ITdpCommitteeMemberDAO getTdpCommitteeMemberDAO() {
		return tdpCommitteeMemberDAO;
	}
	public void setTdpCommitteeMemberDAO(
			ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
		this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
	}
	public ITdpCadreCandidateDAO getTdpCadreCandidateDAO() {
		return tdpCadreCandidateDAO;
	}
	public void setTdpCadreCandidateDAO(ITdpCadreCandidateDAO tdpCadreCandidateDAO) {
		this.tdpCadreCandidateDAO = tdpCadreCandidateDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public ILeaderOccasionWishDetailsDAO getLeaderOccasionWishDetailsDAO() {
		return leaderOccasionWishDetailsDAO;
	}
	public void setLeaderOccasionWishDetailsDAO(ILeaderOccasionWishDetailsDAO leaderOccasionWishDetailsDAO) {
		this.leaderOccasionWishDetailsDAO = leaderOccasionWishDetailsDAO;
	}
	private DateUtilService dateUtilService = new DateUtilService();
	
	public ILeaderOccasionDAO getLeaderOccasionDAO() {
		return leaderOccasionDAO;
	}

	public void setLeaderOccasionDAO(ILeaderOccasionDAO leaderOccasionDAO) {
		this.leaderOccasionDAO = leaderOccasionDAO;
	}

	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	
public List<BirthDayDetailsVO> getLeaderOccasionDetails(Long occastionTypeId,String dataBuildTypeStr,String memberTypeStr,Long userId){
		 List<BirthDayDetailsVO> returnList = new LinkedList<BirthDayDetailsVO>();
		try {
			
			if(occastionTypeId == null && dataBuildTypeStr == null && memberTypeStr == null && userId == null ){
				List<Object[]> lederOcanDtailsThirtyList  = leaderOccasionDAO.getLeaderOccasionDetailsForTaday("previous",0L,1L);
				BirthDayDetailsVO dayVO = new BirthDayDetailsVO();
				dayVO.setName(" Today ");
				dayVO.setTotalCount(Long.valueOf(String.valueOf(lederOcanDtailsThirtyList.size())));
				returnList.add(dayVO);
			}else{
				Long totalCount = 0l;
				List<Long> tdpCadreIdsList = new ArrayList<Long>();
				//dataBuildTypeStr ="Today";
				if(dataBuildTypeStr.trim().length() ==0)
					dataBuildTypeStr=" Today ";
				
				int[] daysArr={30,7,1,0,1,7,30};
				String searchTypeStr="previous";
				
				Date date = new Date(); // your date
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(date);
			    int year = cal.get(Calendar.YEAR);
			     
				int i = 0;
				for ( ;i < daysArr.length;i++) {
					BirthDayDetailsVO dayVO = new BirthDayDetailsVO();
					
					if(i>3){
						 searchTypeStr="Next";
						 	if(Long.valueOf(String.valueOf(daysArr[i])) ==0L ){
								dayVO.setName(" Today ");
								//searchTypeStr="";
							 }else if(Long.valueOf(String.valueOf(daysArr[i])) ==1L ){
								dayVO.setName(" Tomorrow ");
							 }else if(Long.valueOf(String.valueOf(daysArr[i])) !=0L && searchTypeStr.equalsIgnoreCase("Next")){
								dayVO.setName(" Next "+daysArr[i]+" Days");
							 }
					}
					else{
						 if(Long.valueOf(String.valueOf(daysArr[i])) ==0L ){
							dayVO.setName(" Today ");
							//searchTypeStr="";
						 }else if(Long.valueOf(String.valueOf(daysArr[i])) ==1L ){
							dayVO.setName(" Yesterday ");
						 }else if(Long.valueOf(String.valueOf(daysArr[i])) !=0L && searchTypeStr.equalsIgnoreCase("Previous")){
							dayVO.setName(" Last "+daysArr[i]+" Days");
						 }
					}
					List<Object[]> lederOcanDtailsThirtyList  = leaderOccasionDAO.getLeaderOccasionDetailsForTaday(searchTypeStr,Long.valueOf(String.valueOf(daysArr[i])),occastionTypeId);
					List<Long> prevThirtyIds = new ArrayList<Long>(0);
					if(lederOcanDtailsThirtyList != null && lederOcanDtailsThirtyList.size() > 0){
						for (Object[] obj : lederOcanDtailsThirtyList) {
							prevThirtyIds.add(Long.parseLong(obj[0].toString()));
						}
					}
					List<Long> wishList =  new ArrayList<Long>(0);
					List<Long> totalTdpCadreIdsList = new ArrayList<Long>(0);
					if(prevThirtyIds != null && prevThirtyIds.size() >0){
						List<Object[]> wishList1  = leaderOccasionWishDetailsDAO.getTotalDaysCountsForWishedCount(prevThirtyIds, String.valueOf(year).trim(),userId);
						if(wishList1 != null && wishList1.size() > 0){
							
							for (Object[] obj : wishList1) {
								wishList.add((Long)obj[0]);
								totalTdpCadreIdsList.add((Long)obj[1]);
							}
						}
					}
					
					Long lederOcanDtailsCount  = Long.valueOf(String.valueOf(prevThirtyIds.size()));
					
					Long wishedCount  = Long.valueOf(String.valueOf(wishList.size()));
					
					if(dataBuildTypeStr.equalsIgnoreCase(dayVO.getName()))
					{
						List<BirthDayDetailsVO> memberDetailsVOLst = new ArrayList<BirthDayDetailsVO>(0);
						if(commonMethodsUtilService.isListOrSetValid(lederOcanDtailsThirtyList)){
							for (Object[] param : lederOcanDtailsThirtyList) {
								Long tdpCadreId=commonMethodsUtilService.getLongValueForObject(param[0]);
								String cadreName =commonMethodsUtilService.getStringValueForObject(param[1]);
								String mobileNO= commonMethodsUtilService.getStringValueForObject(param[2]);
								String imageStr= commonMethodsUtilService.getStringValueForObject(param[3]);
								//String birthDate = commonMethodsUtilService.getStringValueForObject(param[4].toString());
								
								String inputDate = commonMethodsUtilService.getStringValueForObject(param[4].toString());
							    String myFormat= "yyyy MMM dd";
							    String finalString = "";
							    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							    Date date1 = (Date) formatter .parse(inputDate);
							    SimpleDateFormat newFormat = new SimpleDateFormat(myFormat);
							    finalString= newFormat .format(date1);
							    Long occasionId = commonMethodsUtilService.getLongValueForObject(param[5]);
								
								BirthDayDetailsVO cadreVO = new BirthDayDetailsVO();
								cadreVO.setName(cadreName);
								cadreVO.setId(tdpCadreId);
								if(!tdpCadreIdsList.add(tdpCadreId));
								tdpCadreIdsList.add(tdpCadreId);
								cadreVO.setMobileNo(mobileNO);
								cadreVO.setImageStr(imageStr);
								cadreVO.setbDayDate(finalString.toString().trim().substring(4));
								cadreVO.setOccasionId(occasionId);
								if(totalTdpCadreIdsList.contains(tdpCadreId))
										cadreVO.setWished(true);
								else
									cadreVO.setWished(false);
								memberDetailsVOLst.add(cadreVO);
							}	
						}
						
						Map<Long,String> publicRepMap = new LinkedHashMap<Long, String>(0);
						if(prevThirtyIds != null && prevThirtyIds.size() >0){
						List<Object[]> publicRepDertails = tdpCadreCandidateDAO.getPublicRepresentativeDetailsByCadreIds(prevThirtyIds);
					      if(commonMethodsUtilService.isListOrSetValid(publicRepDertails)){
					        for(Object[] obj:publicRepDertails){
					          Long cadrePositionId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					          String positiontype = obj[2] != null ? obj[2].toString():"";
					          String position = publicRepMap.get(cadrePositionId);
					          if(position != null && position.trim().length() > 0l){
					            position = position+" , "+positiontype;
					          }
					          else{
					            position = positiontype;
					          }
					          publicRepMap.put(cadrePositionId, position);
					        }
					      }
						}
						if(publicRepMap !=null && publicRepMap.size()>0){
							for (Map.Entry<Long,String> entry : publicRepMap.entrySet()){
								Long id = entry.getKey();
								String value = entry.getValue();
								BirthDayDetailsVO Vo = getMatchedVOById(memberDetailsVOLst,id);
								if(Vo != null){
									 Vo.setDesignation(value);
								}
								
							}
						}
						
					      Map<Long,String> partyPostMap = new LinkedHashMap<Long, String>(0);
					      if(prevThirtyIds != null && prevThirtyIds.size() >0){
					      List<Object[]> partyPositionDetails= tdpCommitteeMemberDAO.getPartyPositionsBycadreIdsList(prevThirtyIds);
					       if(commonMethodsUtilService.isListOrSetValid(partyPositionDetails)){
					         for (Object[] obj : partyPositionDetails) {
					           
					           String level = obj[0] != null ? obj[0].toString() : "" ;
					           String role = obj[1] != null ? obj[1].toString() : "";
					           Long cadreId = Long.valueOf(obj[5] != null ? obj[5].toString():"0");
					           String state = commonMethodsUtilService.getStringValueForObject(obj[6]);
					           String commiteestr = obj[2] != null ? obj[2].toString() : "";
					           if(level != null && !level.isEmpty()&&level.equalsIgnoreCase("state"))
					           {
					             level = state+" "+level;
					           }
					           String partyPositionStr = level +" " +role+" ( "+commiteestr+" )";
					           partyPostMap.put(cadreId, partyPositionStr);
					        }
					      }
					    } 
					      if(partyPostMap !=null && partyPostMap.size()>0){
								for (Map.Entry<Long,String> entry : partyPostMap.entrySet()){
									Long id = entry.getKey();
									String value = entry.getValue();
									BirthDayDetailsVO Vo = getMatchedVOById(memberDetailsVOLst,id);
									if(Vo != null){
										 Vo.setPubRepDesignation(value);
									}
									
								}
							}
					      Map<String,Long> partyTotalMap = new LinkedHashMap<String, Long>(0);
					      if(commonMethodsUtilService.isMapValid(publicRepMap)){
					    	  for (Map.Entry<Long,String> entry : publicRepMap.entrySet()){
									String value[] = entry.getValue().split(",");
									for (String string : value) {
										Long count = partyTotalMap.get(string.trim());
										if(count != null && count.longValue() > 0l){
											count=count+1l;
											totalCount = totalCount+1l;
										}
										else{
											count = 1l;
											totalCount = totalCount+1l;
										}
										partyTotalMap.put(string.trim(), count);
									}
									
						      }
					      }
					      if(commonMethodsUtilService.isMapValid(partyPostMap)){
					    	  for (Map.Entry<Long,String> entry : partyPostMap.entrySet()){
									String value = entry.getValue();
									Long count = partyTotalMap.get(value);
									if(count != null && count.longValue() > 0l){
										count=count+1l;
										totalCount = totalCount+1l;
									}
									else{
										count = 1l;
										totalCount = totalCount+1l;
									}
									partyTotalMap.put(value, count);
						      }
					      }
						
					      List<IdNameVO> positionList = new ArrayList<IdNameVO>();
					      if(commonMethodsUtilService.isMapValid(partyTotalMap)){
					    	  for (Map.Entry<String,Long> entry : partyTotalMap.entrySet()){
					    		  IdNameVO vo = new IdNameVO();
					    		  vo.setName(entry.getKey());
					    		  vo.setCount(entry.getValue());
					    		    positionList.add(vo);
					    		 
					    	  }
					      }
					      if(commonMethodsUtilService.isListOrSetValid(positionList))
					    	  Collections.sort(positionList, new Comparator<IdNameVO>() {
						          public int compare(final IdNameVO vo1, final IdNameVO vo2) {
						              return vo1.getName().compareTo(vo2.getName());
						          }
						   });
					      
					      //Add Location For Cadre
					     if(memberDetailsVOLst != null && memberDetailsVOLst.size() > 0)
					  	setAddressForCadre(memberDetailsVOLst,tdpCadreIdsList);
						dayVO.setSubList(memberDetailsVOLst);
						
						dayVO.setIdNameVOList(positionList);
						dayVO.setTotalPosCount(totalCount);
					}
					dayVO.setTotalCount(lederOcanDtailsCount);
					dayVO.setWishCount(wishedCount);
					returnList.add(dayVO);
				}
				
				List<BirthDayDetailsVO> designationList = new ArrayList<BirthDayDetailsVO>();
				if(returnList != null && returnList.size() > 0 && dataBuildTypeStr != null && !dataBuildTypeStr.trim().isEmpty() && memberTypeStr != null && !memberTypeStr.trim().isEmpty()){
					for (BirthDayDetailsVO bDayVo : returnList) {
						
						if((!memberTypeStr.equalsIgnoreCase("Total")) && bDayVo.getName().equalsIgnoreCase(dataBuildTypeStr)){
							
							for (BirthDayDetailsVO subVo : bDayVo.getSubList()) {
								if(memberTypeStr.trim().equalsIgnoreCase("Others") && subVo.getDesignation() == null && subVo.getPubRepDesignation() == null){
										designationList.add(subVo);
								}else if((subVo.getDesignation() != null && subVo.getDesignation().trim().contains(memberTypeStr.trim())) || (subVo.getPubRepDesignation() != null && subVo.getPubRepDesignation().trim().contains(memberTypeStr.trim()))){
									designationList.add(subVo);
								}
							}
							bDayVo.setSubList(designationList);
						}
					}
				}
				
				//No designation candidates adding to others
				IdNameVO othersVO = new IdNameVO();
				othersVO.setName("Others");
				if(returnList != null && returnList.size() > 0){
					for (BirthDayDetailsVO birthDayDetailsVO : returnList) {
						if(birthDayDetailsVO.getName() != null && birthDayDetailsVO.getName().trim().equalsIgnoreCase(dataBuildTypeStr.trim())){
							if(birthDayDetailsVO.getSubList() != null && birthDayDetailsVO.getSubList().size() > 0){
								for (BirthDayDetailsVO dbsubVo : birthDayDetailsVO.getSubList()) {
									if((dbsubVo.getDesignation() == null || dbsubVo.getDesignation().isEmpty()) && (dbsubVo.getPubRepDesignation() == null || dbsubVo.getPubRepDesignation().isEmpty())){
										othersVO.setCount(othersVO.getCount()+1l);
									}
								}
							}
							
							if(othersVO.getCount() != null && othersVO.getCount() > 0l){
								birthDayDetailsVO.getIdNameVOList().add(othersVO);
							}
						}
					}
				}
			
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getLeaderOccasionDetails in BirthDayService service:-", e);
		}
		
		return returnList;
	}

public void setAddressForCadre(List<BirthDayDetailsVO> cadreList,List<Long> tdpCadreIdsList)
{
	
	try{
		
		 List<Object[]> list = tdpCadreDAO.getUserAddressForCadre(tdpCadreIdsList);
	
		 for(Object[] params : list)
		 {
			 AddressVO vo = null;
			 BirthDayDetailsVO cadreVO = (BirthDayDetailsVO) setterAndGetterUtilService.getMatchedVOfromList(cadreList, "id", params[0].toString());
				if(cadreVO != null)
				{
					if(params[1] != null)
						{
						 vo = new AddressVO();
						UserAddress userAddress = (UserAddress) params[1];
						vo.setConstituencyId(Long.valueOf(userAddress.getConstituency() != null ? userAddress.getConstituency().getConstituencyId().toString():"0"));
						vo.setConstituencyName(userAddress.getConstituency() != null ?userAddress.getConstituency().getName() : "");
						vo.setDistrictId(Long.valueOf(userAddress.getDistrict() != null ? userAddress.getDistrict().getDistrictId().toString():"0"));
						vo.setDistrictName(userAddress.getDistrict() != null ? userAddress.getDistrict().getDistrictName() : "");
						vo.setMandalId(Long.valueOf(userAddress.getTehsil() != null ? userAddress.getTehsil().getTehsilId().toString():"0"));
						vo.setMandalName(userAddress.getTehsil() != null ? userAddress.getTehsil().getTehsilName() : "");
						vo.setPanchaytId(Long.valueOf(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getPanchayatId().toString():"0"));
						vo.setPanchayatName(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getPanchayatName() : "");
						vo.setLocalElectionBodyId(Long.valueOf(userAddress.getLocalElectionBody() != null ? userAddress.getLocalElectionBody().getLocalElectionBodyId().toString():"0"));
						vo.setLocalElectionBodyName(userAddress.getLocalElectionBody() != null ? userAddress.getLocalElectionBody().getName() : "");
						vo.setWardId(Long.valueOf(userAddress.getWard() != null ? userAddress.getWard().getConstituencyId().toString():"0"));
						vo.setWardName(userAddress.getWard() != null ? userAddress.getWard().getName() : "");
						}
					
				}
				cadreVO.setAddressVO(vo);
		 }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}
public BirthDayDetailsVO getMatchedVOById(List<BirthDayDetailsVO> memberDetailsVOLst,Long id)
{
	try{
		if(memberDetailsVOLst == null || memberDetailsVOLst.size() == 0)
			return null;
		for(BirthDayDetailsVO vo : memberDetailsVOLst)
		{
			if(vo.getId().longValue()== id.longValue())
				return vo;
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return null;
}
	
public String getWishingDetails(Long searchId,Long userId) {
	String status = null;
	List<TdpCadreVO> cadreVOs = new ArrayList<TdpCadreVO>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	String year = sdf.format(new Date());
	Date date = new DateUtilService().getCurrentDateAndTime(); // your date
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int calyear = cal.get(Calendar.YEAR);
     try{
    	// TdpCadreVO tdpCadreVO = new TdpCadreVO();
    	 LeaderOccasionWishDetails leaderOcassion = leaderOccasionWishDetailsDAO.getLeaderOccassiobnWishngDetails(searchId,String.valueOf(calyear),userId);
    	 	if(leaderOcassion == null){
	    		 LeaderOccasionWishDetails  leaderocsnWishDtls = new LeaderOccasionWishDetails();
	    		 leaderocsnWishDtls.setLeaderOccasionId(searchId);
	    		 leaderocsnWishDtls.setWishTime(dateUtilService.getCurrentDateAndTime());
	    		 leaderocsnWishDtls.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
	    		 leaderocsnWishDtls.setIsDeleted("false");
	    		 leaderocsnWishDtls.setYear(year);
	    		 leaderocsnWishDtls.setWishedBy(userId);
	    		 leaderOccasionWishDetailsDAO.save(leaderocsnWishDtls);	
    	 	}
    	 	else {
    	 			if(leaderOcassion.getIsDeleted().equalsIgnoreCase("false")){
    	 				leaderOcassion.setIsDeleted("true");
    	 			}
    	 			else
    	 			{
    	 				leaderOcassion.setIsDeleted("false");
    	 			}
    	 			leaderOccasionWishDetailsDAO.save(leaderOcassion);	
    	 	}		
	 status = "success";
  }
	 catch (Exception e) {
		 e.printStackTrace();
		 status = "failure";
		LOG.error("Exception ocured in gettingUserDetails()"+e);
	}
    
	return status;
}
	
	/**
	 * @param value
	 * @return
	 */
	private Date getBirthdaySearchfromDateBasedOnValue(Integer value) {
		Date fromDate = null;
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.DATE, value);
		fromDate = calender.getTime();
		return fromDate;
	}
	
	/**
	 * @param value
	 * @return
	 */
	private Date getBirthdaySearchToDateBasedOnValue(Integer value){
		Date toDate = null;
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.DATE, value);
		toDate = calender.getTime();
		return toDate;
	}
	
	}


